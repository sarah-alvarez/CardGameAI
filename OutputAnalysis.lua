----
----
----

gameDataTable = {}
io.input("gameOutput.txt")
line = io.read()
line = io.read()
line = io.read()
table.insert(gameDataTable, string.sub(line,2))
while string.sub(line,1,1) == "-" do
    line = io.read()
    if string.sub(line,1,1) == "-" then
       table.insert(gameDataTable, string.sub(line,2))
    end
    if line == nil then break end
end

----					---
---- Split the Strings on ',' delimiter ---
---- 	       	       	      		---

function fromCSV (s)
    s = s .. ','        -- ending comma
    local t = {}        -- table to collect fields
    local fieldstart = 1
    repeat
        -- next field is quoted? (start with `"'?)
        if string.find(s, ',', fieldstart) then
            local a, c
            local i  = fieldstart
            repeat
              -- find closing quote
              a, i, c = string.find(s, ',', i+1)
            until c ~= ','    -- quote not followed by quote?
            if not i then error('unmatched "') end
            local f = string.sub(s, fieldstart, i-1)
            table.insert(t, (string.gsub(f, ',', ',')))
            fieldstart = string.find(s, ',', i)+1
        else                -- unquoted; find next comma
            local nexti = string.find(s, ',', fieldstart)
            table.insert(t, string.sub(s, fieldstart, nexti-1))
            fieldstart = nexti+1
        end
        until fieldstart > string.len(s)
        return t
end


---					     ---
--- Place split strings into their own table ---
--- 	  		     	             ---

gameNumber = {}
roundsPlayed = {}
player1Points = {}
player2Points = {}
winningPlayer = {}
player1Streak = {}
player2Streak = {}

for i in pairs(gameDataTable) do
    temp = {}
    temp = fromCSV(gameDataTable[i])
    table.insert(gameNumber, temp[1])
    table.insert(roundsPlayed, temp[2])
    table.insert(player1Points, temp[3])	
    table.insert(player2Points, temp[4])
    table.insert(winningPlayer, temp[5])
    table.insert(player1Streak, temp[6])
    table.insert(player2Streak, temp[7])
end


---					     ---
--- Function to find total # of games played ---
--- 	     	     	      	      	     ---

function findTotalGames(t)
    local totalGames = 0
    for key, val in pairs(t) do
        totalGames = val
    end
    return totalGames
end


---						 ---
--- Function to find average matches of a player ---
--- 	     	     	     	     	  	 ---

function findAvgMatches(t)
    local gamesPlayed = tonumber(findTotalGames(gameNumber))
    local totalPoints = 0
    for key, val in pairs(t) do
        totalPoints = totalPoints + tonumber(val)
    end
    return totalPoints/gamesPlayed
end


---				      ---
--- Function to find the most guesses ---
--- 	     	     	      	      ---

function findMostGuesses(t)
    local numGuesses = tonumber(t[1])
    for key, val in pairs(t) do
        if tonumber(val) > numGuesses then
	    numGuesses = tonumber(val)
	end
    end
    return numGuesses
end


---				       ---
--- Function to find the least guesses ---
--- 	     	     	       	       ---

function findLeastGuesses(t)
    local numGuesses = tonumber(t[1])
    for key, val in pairs(t) do
        if tonumber(val) < numGuesses then
            numGuesses = tonumber(val)
        end
    end
    return numGuesses
end


---					  ---
--- Function to find Average # of guesses ---
--- 	     	     	       	  	  ---

function findAvgGuesses(t)
    local gamesPlayed = tonumber(findTotalGames(gameNumber))
    local totalGuesses = 0;
    for key, val in pairs(t) do
        totalGuesses = totalGuesses + tonumber(val)
    end
    return totalGuesses/gamesPlayed
end


---                                               ---
--- Function to find the player that won the most ---
---                                               ---

function findBestPlayer(t)
    local p1Wins = 0
    local p2Wins = 0
    for key, val in pairs(t) do
        if tonumber(val) == 1 then
	    p1Wins = p1Wins + 1
	elseif tonumber(val) == 2 then
            p2Wins = p2Wins + 1
	end
    end
    winner = {}
    if p1Wins > p2Wins then
        table.insert(winner, 1)
	table.insert(winner, p1Wins)
    else
        table.insert(winner, 2)
	table.insert(winner, p2Wins)
    end
    return winner
end


---						    ---
--- Function to find the longest streak of a player ---
--- 	     	     	 	 	     	    ---

function findLongestStreak(t)
    local longest = tonumber(t[1])
    for key, val in pairs(t) do
        if tonumber(val) > longest then
	    longest = tonumber(val)
	end
    end
    return longest
end




---		     ---
--- Output Game Data ---
--- 	   	     ---

winnerResult = findBestPlayer(winningPlayer)

print("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=")
print("=-           OUTPUT OF GAME DATA           -=")
print("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=")
print(" ")
print("    Total Number of Games Played = ", findTotalGames(gameNumber))
print(" ")
print("    Average Number of Matches Found by Player 1 = ", findAvgMatches(player1Points)) 
print(" ")
print("    Average Number of Matches Found by Player 2 = ", findAvgMatches(player2Points))
print(" ")
print("    Most Guesses Made in a Game = ", findMostGuesses(roundsPlayed))
print(" ")
print("    Least Guesses Made in a Game = ", findLeastGuesses(roundsPlayed))
print(" ")
print("    Average Guesses Made in a Game = ", findAvgGuesses(roundsPlayed))
print(" ")
print("    Player that had the Most Wins = Player ", winnerResult[1])
print(" ")
print("    Player Wins = ", winnerResult[2])
print(" ")
print("    Player 1 Longest Streak = ", findLongestStreak(player1Streak))
print(" ")
print("    Player 2 Longest Streak = ", findLongestStreak(player2Streak))
print(" ")
print("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=")
print("=-                END OUTPUT               -=")
print("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=")




