This file will contain documentation for all commands available in your game.

Note:  It's a good idea to also make this list available inside the game, in response to a `HELP` command.

Commands:
My game doesn't have a set of commands available at all times. Instead, the interface gives information to the user and prompts them for a response. I've included details about what responses will and won't work.
- For yes/no questions: 'Y' or 'y' will be considered a yes, anything else will be considered a no
- For during investigation/moving/guessing: 
    - 'back' will stop whatever move/investigation/guess is currently ongoing. This command is not case sensitive.
    - For room/character names, the user must type in exactly what is contained in the brackets listing the names. However, room/character names are not case sensitive. Before moving to a room or investigating a character, a list of the available rooms/characters will be displayed.
    - If the user inputs something that is not 'back' or a valid name, the computer will prompt them to try again

Map:
- The player begins the game in the [Study] on the first floor of the manor. The [Study] connects to the [Library]. 
- The [Library] connects to the [Study] and to the [Foyer]. 
- The [Foyer] connects to the [Library], the [Upstairs Hallway], and the [Dining Room].
- The [Dining Room] connects to the [Foyer] and the [Kitchen].
- The [Kitchen] connects to the [Dining Room] and the [Office].
- The [Office] connects to the [Kitchen].
- The [Upstairs Hallway] is on the second floor. It connects to the [Foyer] downstairs and to the [Master Bedroom], [Guest Bedroom], [Edward's Bedroom], [Edith's Bedroom], and [Edric's Bedroom] upstairs.
- Each of the bedrooms only connects back to the [Upstairs Hallway].

# SPOILER ALERT

If your game includes challenges that must be overcome to win, also list them below.

You win this game by guessing the murderer. You have three total guesses. The game ends when either you use up all your guesses (loss) or you guess correctly (win). In this game, the correct answer is that the detective is the murderer.