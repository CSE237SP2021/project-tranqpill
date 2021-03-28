# cse237-project

#### Authors: Zachary Isaacson, Christina Pan, Akshay Padmanabhan, and Nick Bartochowski

## Checkers Game

For our CSE237 final project, we are implementing the game of Checkers (https://en.wikipedia.org/wiki/English_draughts). The game is played via the terminal (mac/linux) or command prompt (windows), and it can be played on any system that can run Java. We are implementing a fully functional checkers game for two players on an 8-by-8 playing board according to the standard rules of checkers. This project was created on the Eclipse IDE.

### Rules
Our implementation is based off of the standard rules of checkers. The entire game board is an 8x8 board of squares, but the playing area will only use 32 of these squares. The initial position is comprised of 12 white pieces on the bottom and 12 black pieces on the top of the board, as depicted below:<br><br>
Diagram<br><br>
During each turn, a player must select one of their own pieces to move. Each game piece (represented by "w" for white or "b" for black) is permitted to move to a diagonally adjacent, empty, forward square. A player captures the opponent's piece by jumping over it, as depicted in the following diagram:<br><br>
Diagram<br><br>
When a player's piece reaches the last row on the board, it becomes crowned as a king and obtains the additional freedom of moving diagonally backwards. Kings are represented by "W" for white or "B" for black.<br><br>
If a player captures all of the opponent's pieces, that player wins, and the game ends.
Additionally, our implementation assumes the standard jumping and tying rules, where jumps are mandatory and threefold repetitions or 50 moves without a capture lead to an automatic tie. 

### Starting a Game
To start a game, open a terminal or command prompt, navigate to the directory of the project, and type "runCheckers.sh". This will automatically start a new game of checkers between two human players.

### Playing a Game
After starting a game, the game board will be printed with the initial starting position. The first player will be prompted to enter coordinates for their first move. Coordinates are entered in the form<br><br>
**[initial square x],[intial square y]-[final square x],[final square y]**<br><br>
where x is the x-coordinate of the board with respect to the lower-left corner and y is the y-coordinate of the board with respect to the lower left corner. The game board is formatted such that the lower-left corner has coordinates (0,0) and the upper-right corner has coordinates (7,7). Each player will be continually promopted to enter a move until they enter a legal move according to the standard rules of checkers.<br><br>
Recall that jumping is mandatory. Since a sequence of several jumps could lead to a drastic change in the current position, we decided to implement jumping to be non-automatic: that is, a player will still be prompted to move, but they will only be permitted to make a jump if one or more is possible.

### Ending the Game
To stop playing a game of checkers, type "quit" when it is either player's turn; this will immediately terminate the current game.<br><br>
Otherwise, the game will automatically end when either one player wins, or when the game ends in a tie.

### Features (in progress)
- Functional on any system supporting Java
- Threefold repetition detection
- 50 move rule detection
- Rename each player
- Move inputs via coordinates
- Prints game board after each move

