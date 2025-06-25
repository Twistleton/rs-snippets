import System.Environment
import Data.Time.Clock
import Data.Time.Calendar
import Data.Time.LocalTime

type GregorianDate = (Integer, Int, Int)

getYesterday :: IO UTCTime
getYesterday = fmap (addUTCTime (-nominalDay)) getCurrentTime

getGregorianDate :: TimeZone -> UTCTime -> (Integer, Int, Int)
getGregorianDate zone utc = (toGregorian . localDay) $ utcToLocalTime zone utc

main = do
    today <- getCurrentTime
    yesterdayUTCTime <- getYesterday
    timeZone <- getCurrentTimeZone
    let yesterday = getGregorianDate timeZone yesterdayUTCTime
    print (today, yesterday)