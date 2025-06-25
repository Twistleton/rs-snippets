import System.Directory
import System.Environment
import Control.Monad

{--
       checkFile.hs

       ./checkFile "Oscar.txt" "Hugo.txt"
--}

main = do
       args <- getArgs
       xs <- filterM checkFile args
       mapM createFile xs
       print xs

checkFile :: FilePath -> IO Bool
checkFile x = not <$> doesFileExist x

createFile :: FilePath -> IO ()
createFile x = writeFile x "* create dummy file"
