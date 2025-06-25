import System.Random

minValue :: Int
minValue = 1

maxValue :: Int
maxValue = 100

main :: IO()
main = do
  x <- randomRIO (minValue, maxValue)
  putStrLn (show x)