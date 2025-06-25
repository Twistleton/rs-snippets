{--

	speller.hs - FUNCTIONAL PROGRAMMING IN HASKELL UNIVERSITY OF GLASGOW

--}

main :: IO ()
main = do
  test []
  test ["abacus"]
  test ["coffee", "tea"]
  test ["apple", "banana", "coconut"]


-- Code to run test on several definions
test :: [String] -> IO ()
test l = do
  putStrLn ("\nTesting with the list: " ++ show l)
  runTest "With speller: " speller
  -- runTest "With speller':" speller'
  where
    runTest message f = do
      putStrLn message
      putStrLn (f l)


-- speller.hs - FUNCTIONAL PROGRAMMING IN HASKELL UNIVERSITY OF GLASGOW

-- The 'spellWord' function spells out an individual word. For example, "a is for apple"
spellWord :: String -> String
spellWord [] = []
spellWord word = head word : " is for " ++ word

-- The 'speller' function generates text from a list of words like speller ["apple", "banana", "coconut"]
speller :: [String] -> String
speller []              = ""                                                -- empty list
speller [singleWord]    = spellWord singleWord                              -- single element list
speller [word1, word2]  = spellWord word1 ++ ", and " ++ spellWord word2    -- two elements list
speller (word:words)    = spellWord word ++ ", " ++ speller words           -- 3-or-more-element list - do recursion
