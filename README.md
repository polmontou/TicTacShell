# Class diagram

```mermaid
classDiagram
    direction BT
    class Board {
        + Board(int, int)
        - int boardSizeX
        - int boardSizeY
        + checkColumnAvailability(int) boolean
        + isInWinLine(int, int, int) boolean
        + isWon(int) boolean
        + isInWinCol(int, int, int) boolean
        + updateCell(int, Player) void
        + isFull(int) boolean
        - isInWinDiag(int, int, int) boolean
        - checkDiag(int, int, int, int) boolean
        + updateCell(int, int, Player) void
        + getCell(int, int) Cell
        + checkCellAvailability(int, int) boolean
        int boardSizeX
        int boardSizeY
    }
    class BotPlayer {
        + BotPlayer(String, String)
        + chooseInt(int) int
    }
    class Cell {
        + Cell()
        - Player player
        - boolean empty
        - String content
        + toString() String
        Player player
        String content
        boolean empty
    }
    class ControllerState {
        <<enumeration>>
        + ControllerState()
        + valueOf(String) ControllerState
        + values() ControllerState[]
    }
    class Freestyle {
        ~ Freestyle(String, int, int, int)
        + init(Player[]) void
    }
    class GameController {
        + GameController()
        - getMove(Player, String, int, int) int
        - createPlayerSet(int) Player[]
        - play() void
        + parseUserChoice(int, Class~E~) E
        - initGame() void
        + interact() void
        + parseUserPlayerChoice(int) Player[]
        - playTurn(Player, int, Board, int) void
        - parseResults(RoundEnd, Player) void
    }
    class GameFactory {
        + GameFactory()
        + createGame(GamesPreset, String, int, int, int) PlayStrategy
    }
    class GameType {
        + GameType(String, int, int, int)
        # Player[] players
        # Board board
        # String name
        + isOver(int) RoundEnd
        + init(Player[]) void
        String name
        Board board
        Player[] players
    }
    class GamesPreset {
        <<enumeration>>
        - GamesPreset(String, int)
        - GamesPreset(String, int, int, int)
        - int maxSize
        - int columnMax
        - String name
        - int winRule
        - int lineMax
        + valueOf(String) GamesPreset
        + toString() String
        + values() GamesPreset[]
        String name
        int columnMax
        int lineMax
        int winRule
        int maxSize
    }
    class Gomoku {
        ~ Gomoku(String, int, int, int)
    }
    class HumanPlayer {
        + HumanPlayer(String, String)
        + chooseInt(int) int
    }
    class Main {
        + Main()
        + main(String[]) void
    }
    class Menu {
        + Menu()
        + showLog(String) void
        + displayPlayerChoiceMenu() void
        + askForInt(String, int, int) int
        + displayGameChoiceMenu() void
        - intVerification(int) int
        + displayBoard(Board) void
    }
    class Pawn {
        <<enumeration>>
        - Pawn(String)
        - String representation
        + valueOf(String) Pawn
        + values() Pawn[]
        + distributePawn(int) Pawn
        String representation
    }
    class PlayStrategy {
        <<Interface>>
        + isOver(int) RoundEnd
        + init(Player[]) void
        String name
        Board board
        Player[] players
    }
    class Player {
        + Player(String, String)
        - String pawn
        - String name
        + chooseInt(int) int
        String name
        String pawn
    }
    class Puissance4 {
        ~ Puissance4(String, int, int, int)
    }
    class RoundEnd {
        <<enumeration>>
        + RoundEnd()
        + values() RoundEnd[]
        + valueOf(String) RoundEnd
        boolean won
    }
    class TicTacToe {
        ~ TicTacToe(String, int, int, int)
    }
    class UserInteraction {
        + UserInteraction()
        + clearBuffer() void
        String userString
        int userInt
    }
    class View {
        + View()
        + displayBoard(Board) void
        + displayLog(String) void
    }

    Board "1" *--> "board *" Cell
    Board  ..>  Cell : «create»
    BotPlayer  -->  Player
    Cell "1" *--> "player 1" Player
    Freestyle  ..>  Board : «create»
    Freestyle  -->  GameType
    GameController  ..>  BotPlayer : «create»
    GameController "1" *--> "state 1" ControllerState
    GameController  ..>  HumanPlayer : «create»
    GameController "1" *--> "menu 1" Menu
    GameController  ..>  Menu : «create»
    GameController "1" *--> "currentGame 1" PlayStrategy
    GameController  ..>  Player : «create»
    GameFactory  ..>  Freestyle : «create»
    GameFactory  ..>  Gomoku : «create»
    GameFactory  ..>  Puissance4 : «create»
    GameFactory  ..>  TicTacToe : «create»
    GameType "1" *--> "board 1" Board
    GameType  ..>  Board : «create»
    GameType  ..>  PlayStrategy
    GameType "1" *--> "players *" Player
    GameType "1" *--> "status 1" RoundEnd
    Gomoku  -->  GameType
    HumanPlayer  -->  Player
    Main  ..>  GameController : «create»
    Menu "1" *--> "userInteraction 1" UserInteraction
    Menu  ..>  UserInteraction : «create»
    Menu  ..>  View : «create»
    Menu "1" *--> "view 1" View
    Puissance4  -->  GameType
    TicTacToe  -->  GameType


``` 
## OLD VERSION
```classDiagram
class Cell {
-String content
-Player player
-boolean empty

+void setPlayer()
-void setContent()
}

class Player {
    <<Abstract>>
    -String name
    -String pawn

    +int chooseInt()*
}
class HumanPlayer{
    +chooseInt()
}
class BotPlayer{
    +chooseInt()
}
class Game {
    -GameType currentGame
    +void play()
}
class GameType  {
    <<Abstract>>
    #String name
    #Player[] players
    #Board board
    #Menu menu
    #int winRule
    #int lineMax
    #int columnMax

    +void init()
    +void play()
    #void getMove()
    -boolean checkMove()
    -boolean checkCellAvailability()
    -boolean checkRange()
    -RoundEnd isOver()
}
class TicTacToe {
}
class Gomoku{
}
class Freestyle{
}
class Puissance4{
    #void getMove()
    -boolean checkMove()
    -boolean checkCellAvailability()
    -boolean checkRange()
}

class Board {
    -Cell[][] board
    -int boardSizeX
    -int boardSizeY

    +updateCell()
    +getCell()
    +boolean isWon()
    +boolean isFull()
    -boolean isInWinDiag()
    -boolean checkDiag()
    -boolean isInWinLine()
    -boolean isInWinCol()
}

class Pawn {
    <<Enum>>
    X
    O
    -String representation

    +Pawn distributePawn()
}

class RoundEnd{
    <<Enum>>
    NOTHING
    TIE
    WIN
    -boolean win

    +boolean isWon()
}

class Games{
    <<Enum>>
    TICTACTOE
    PUISSANCE4
    GOMOKU
    FREESTYLE
    -String name
    -Game game
}

class Menu{
    +Game displayGameChoiceMenu()
    +Player[] displayPlayerChoiceMenu()
}

class UserInteraction {
    -getUSerInt()
    +int askForInt()
    +parseUserChoice()
    +parseUserPlayerChoice()
}

class View{
    +message()
    +displayBoard()
}

GameType <|-- TicTacToe : Inheritance
GameType <|-- Gomoku : Inheritance
GameType <|-- Freestyle : Inheritance
GameType <|-- Puissance4 : Inheritance
GameType*--Player
GameType*--Board
GameType--Menu
GameType*--RoundEnd

Board*--Cell

Player<|--BotPlayer : Inheritance
Player<|--HumanPlayer : Inheritance
Player*--Pawn
Game --> GameType

Cell <-- Player
