import System.Environment
import Data.Time.Clock
import Data.Time.Calendar
import Data.Time.LocalTime

{--    write gemini request telegrams

       March 2019

    syntax:
       GeminiQuery <template-name>
       GeminiQuery <template-name> <start-date> <stop-date>

    examples:
       GeminiQuery gemini_request_telegram_cutter_activity
       GeminiQuery gemini_request_telegram_scanner_activity
       GeminiQuery gemini_request_telegram_production_activity

       GeminiQuery gemini_request_telegram_cutter_activity     20.10.2018 20.10.2018
       GeminiQuery gemini_request_telegram_scanner_activity    20.10.2018 20.10.2018
       GeminiQuery gemini_request_telegram_production_activity 20.10.2018 20.10.2018
--}

telegramConfiguration = "/root/Documents/gemini/GeminiQuery/GeminiQuery.conf"
templatePath="/root/Documents/gemini/GeminiQuery/templates/"
outputPath="/home/host2pc/gemini/ED/Input/"
-- outputPath="/root/Documents/gemini/GeminiQuery/output/"

type GregorianDate = (Integer, Int, Int)
type XmlContent = String

data GeminiTelegramRequest = GeminiTelegramRequest {
    templateName :: String
  , startDate    :: GregorianDate
  , endDate      :: GregorianDate
  , nextId       :: Integer } deriving (Show)

getYesterday :: IO UTCTime
getYesterday = fmap (addUTCTime (-nominalDay)) getCurrentTime

getGregorianDate :: TimeZone -> UTCTime -> (Integer, Int, Int)
getGregorianDate zone utc = (toGregorian . localDay) $ utcToLocalTime zone utc

getRequest :: [[Char]] -> GregorianDate -> Integer -> GeminiTelegramRequest
getRequest [] _ _ = error ("missing template-name from command line \n\n" ++ showHelp)
getRequest (templateName:startDate:stopDate:[]) _ nextId = GeminiTelegramRequest templateName (genDate startDate) (genDate stopDate) nextId
getRequest (templateName:[]) sysdate              nextId = GeminiTelegramRequest templateName sysdate sysdate nextId
getRequest _ _ _ = error ("wrong args from command line\n \n" ++ showHelp)

showHelp = "GeminiQuery <template-name> \nGeminiQuery <template-name> <start-date> <stop-date> \n\n "

date :: (Show a1, Show a2, Show a3) => (a1, a2, a3) -> (String, String, String)
date(y, m, d) = (show y, show m, show d)

-- replace string

replace :: Eq a => [a] -> [a] -> [a] -> [a]
replace needle replacement haystack
  = case begins haystack needle of
      Just remains -> replacement ++ remains
      Nothing      -> case haystack of
                        []     -> []
                        x : xs -> x : replace needle replacement xs

begins :: Eq a => [a] -> [a] -> Maybe [a]
begins haystack []                = Just haystack
begins (x : xs) (y : ys) | x == y = begins xs ys
begins _        _                 = Nothing

-- generate gregorian date

genDate :: [Char] -> GregorianDate -- "20.10.2018" -> (2018, 10, 20)
genDate x = convertDate $ tuplify3 $ wordsWhen (=='.') x

wordsWhen     :: (Char -> Bool) -> String -> [String]
wordsWhen p s =  case dropWhile p s of
                      "" -> []
                      s' -> w : wordsWhen p s''
                            where (w, s'') = break p s'

tuplify3 :: [a] -> (a,a,a)
tuplify3 [x,y,z] = (x,y,z)

convertDate :: ([Char], [Char], [Char]) -> GregorianDate
convertDate (x,y,z) = (read z :: Integer, read y :: Int, read x :: Int)

removeFileExtention :: [a] -> [a]
removeFileExtention = reverse . (drop 4) . reverse

getTelegramContent :: XmlContent -> GeminiTelegramRequest -> XmlContent
getTelegramContent content request = newContent
        where (start_year, start_month, start_day) = date (startDate request)
              (stop_year,  stop_month,  stop_day)  = date (endDate request)
              newContent = replace "{id}"             (show (nextId request))    $
                           replace "{username}"      "********"                  $
                           replace "{password}"      "********"                  $
                           replace "{start_year}"     start_year                 $
                           replace "{start_month}"    start_month                $
                           replace "{start_day}"      start_day                  $
                           replace "{stop_year}"      stop_year                  $
                           replace "{stop_month}"     stop_month                 $
                           replace "{stop_day}"       stop_day                   $  content

main = do
    args <- getArgs
    yesterdayUTCTime <- getYesterday
    timeZone <- getCurrentTimeZone

    let yesterday = getGregorianDate timeZone yesterdayUTCTime

    lastId <- readFile telegramConfiguration

    let request = getRequest args yesterday $ succ (read lastId :: Integer)

    content <- readFile $ templatePath ++ (templateName request)

    let telegramContent = getTelegramContent content request

    writeFile (outputPath ++ (removeFileExtention (templateName request)) ++ "_" ++ (show (nextId request)) ++ ".xml") telegramContent
    writeFile telegramConfiguration $ show (nextId request)
    print request