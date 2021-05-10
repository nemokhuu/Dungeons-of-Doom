# Dungeons of Doom

This is a Java text based game called Dungeons of Doom, the aim of the game
is for one player to collect the gold necessary in a dungeon and exit the
dungeon before a computer-controlled player catches you. The game starts 
after the message "Welcome to the Dungeons of Doom: Default Map" is printed,
where the user can then enter in one of 6 commands to control the player/game:

HELLO - Displays gold required to win the game
GOLD - Shows number of gold the player currently has
MOVE (N, S, E, W) - The player can only input either MOVE N for move 'north' etc., moves the player's character one space to the direction told
PICKUP - If the player is on a gold tile, the player can pickup the gold and 'SUCCESS' is printed, if not on a gold tile, 'FAIL' is printed to the user
LOOK - Displays a 5x5 grid of the map around the player
QUIT - Game is quit, 'WIN' followed by success message printed if player enters command on exit tile with required gold, otherwise 'LOSE' printed

The player is designated the letter 'P' on the map and the bot 'B'
Gold is denoted by 'G'
'#' denotes the walls of the map that the user cannot pass through
'E' denotes the exit tile that the user must be on to win the game and escape the dungeon.

Enjoy playing the Dungeons of Doom! 
