import qualified Data.Map as Map 

data RobotPart = RobotPart
   {  name :: String
   ,  description :: String
   ,  cost :: Double 
   ,  count :: Int
   }  deriving Show 


leftArm :: RobotPart
leftArm  = RobotPart
   {  name = "left arm"
   ,  description = "left arm for face punching"
   ,  cost = 1000.00
   ,  count = 3 
   }   

rightArm :: RobotPart 
rightArm  = RobotPart
   {  name = "right arm"
   ,  description = "right arm for kind hand gestures"
   ,  cost = 1025.0
   ,  count = 5 
   }

robotHead :: RobotPart 
robotHead  = RobotPart 
   {  name = "robot head"
   ,  description = "this head looks mad"
   ,  cost = 5092.25
   ,  count = 2 
   }

type Html = String 

renderHtml :: RobotPart -> Html
renderHtml part = mconcat ["<h2>", partName, "</h2>", "<p><h3>desc</h3>", partDesc, "</p><p><h3>cost</h3>", partCost, "</p><p><h3>count</h3>", partCount, "</p>"]      
  where partName = name part 
        partDesc = description part
        partCost = show (cost part)
        partCount = show (count part)


partsDB :: Map.Map Int RobotPart
partsDB = Map.fromList keyVals
  where keys = [1,2,3]        
        vals = [leftArm, rightArm, robotHead]
        keyVals = zip keys vals 

allParts :: [RobotPart]
allParts = map snd (Map.toList partsDB)

allPartsHtml :: [Html]        
allPartsHtml = map renderHtml allParts

insertSnippet :: Maybe Html -> IO () 
insertSnippet Nothing = putStrLn "Nothing to display"
insertSnippet (Just x) = putStrLn x

partVal :: Int -> Maybe RobotPart
partVal n = Map.lookup n partsDB

-- insertSnippet (renderHtml <$> partVal 1)
-- <h2>left arm</h2><p><h3>desc</h3>left arm for face punching</p><p><h3>cost</h3>1000.0</p><p><h3>count</h3>3</p>

-- insertSnippet (renderHtml <$> partVal 5)
-- Nothing to display 


leftArmIO :: IO RobotPart
leftArmIO = return leftArm 

htmlSnippet :: IO Html
htmlSnippet = renderHtml <$> leftArmIO


-- Q 27.1 

data Box a = Box a deriving Show

instance Functor Box where 
    fmap f (Box a) = Box (f a)

morePresents :: Int -> Box a -> Box [a] 
morePresents n (Box a) = fmap (replicate n) (Box a)

-- Q 27.2 

myBox :: Box Int 
myBox = Box 1 

wrapped :: Box a -> Box (Box a)
wrapped a = fmap (Box) a 

--  putInBox (Box 5) 
--  ==> Box (Box 5)

unwrapped :: Box a -> a
unwrapped x = (\(Box a) -> a) x

-- Q 27.3

printCost :: Maybe Double -> IO () 
printCost Nothing = putStrLn "item not found"
printCost (Just cost) = print cost

main :: IO () 
main = do 
    putStrLn "enter a part number"
    partNo <- getLine
    let part = Map.lookup (read partNo) partsDB
    printCost (cost <$> part)
