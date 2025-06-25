data Color = Red | Blue | Green

say :: Color -> String
say Red   = "selected red"
say Blue  = "selected blue"
say Green = "selected green"


initials :: String -> String -> String
initials firstname lastname = [f] ++ ". " ++ [l] ++ "."
   where (f:_) = firstname
         (l:_) = lastname

initials' :: String -> String -> String
initials' (f:fx) (l:lx) = [f] ++ ". " ++ [l] ++ "."

initials2 :: String -> String -> String
initials2 firstname lastname = [head firstname] ++ ". " ++ [head lastname] ++ "."


calcBmis :: [(Double, Double)] -> [Double]
calcBmis xs = [bmi w h | (w, h) <- xs]
    where bmi weight height = weight / height ^ 2

maximum' :: (Ord a) => [a] -> a
maximum' [] = error "empty list"
maximum' [x] = x
maximum' (x:xs) = max x (maximum' xs)


describeInput :: Int -> String
describeInput x = "The input is " ++ case x of 1 -> "is one"
                                               2 -> "is two"
                                               3 -> "is three"
                                               _ -> "more"


data Person = Person { firstName :: String
                     , lastName :: String
                     , age :: Int
                     , height :: Float
                     , phoneNumber :: String
                     , flavor :: String
                     } deriving (Show)
