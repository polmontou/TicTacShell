# Class diagram

```mermaid
classDiagram
%% Enumerations
    class ControllerState {
        <<enumeration>>
        NEW
        INITIALIZED
        PLAYING
        ENDED
        EXIT
    }

    class RoundEnd {
        <<enumeration>>
        NOTHING
        WIN
        TIE
        +isWon()
    }

    class Pawn {
        <<enumeration>>
        X
        O
        -String representation
        +getRepresentation()
        +distributePawn()
    }

    class GamesPreset {
        <<enumeration>>
        TICTACTOE_PRESET
        PUISSANCE4_PRESET
        GOMOKU_PRESET
        FREESTYLE_PRESET
        -String name
        -int maxSize
        -int winRule
        -int lineMax
        -int columnMax
        +getName()
        +getWinRule()
        +getLineMax()
        +getColumnMax()
        +getMaxSize()
        +toString()
        +setWinRule()
        +setLineMax()
        +setColumnMax()
    }

%% Interface
    class PlayStrategy {
        <<interface>>
        +init()
        +isOver()
        +getName()
        +getBoard()
        +getPlayers()
    }

%% Controller
    class GameController {
        -int PLAYER_LIMIT
        -PlayStrategy currentGame
        -Menu menu
        -ControllerState state
        +GameController()
        +interact()
        -initGame()
        -play()
        -playTurn()
        -parseResults()
        -getMove()
        -createPlayerSet()
        +parseUserChoice()
        +parseUserPlayerChoice()
    }

%% Model - Board
    class Board {
        -Cell[][] board
        -int boardSizeY
        -int boardSizeX
        +Board()
        +isWon()
        -isInWinDiag()
        -checkDiag()
        +isInWinCol()
        +isInWinLine()
        +isFull()
        +updateCell()
        +checkCellAvailability()
        +checkColumnAvailability()
        +getCell()
        +getBoardSizeX()
        +getBoardSizeY()
    }

    class Cell {
        -String content
        -Player player
        -boolean empty
        +Cell()
        +setPlayer()
        -setContent()
        +toString()
        +getPlayer()
        +isEmpty()
    }

%% Model - Games
    class GameType {
        <<abstract>>
        #String name
        #Player[] players
        #Board board
        #int winRule
        #int lineMax
        #int columnMax
        #RoundEnd status
        +GameType()
        +init()
        +isOver()
        +getName()
        +getBoard()
        +getPlayers()
    }

    class TicTacToe {
        +TicTacToe()
    }

    class Puissance4 {
        +Puissance4()
    }

    class Gomoku {
        +Gomoku()
    }

    class Freestyle {
        +Freestyle()
        +init()
    }

    class GameFactory {
        <<factory>>
        +createGame()
    }

%% Model - Players
    class Player {
        <<abstract>>
        -String name
        -String pawn
        +Player()
        +chooseInt()
        +getName()
        +getPawn()
    }

    class HumanPlayer {
        +HumanPlayer()
        +chooseInt()
    }

    class BotPlayer {
        +BotPlayer()
        +chooseInt()
    }

%% View
    class Menu {
        -View view
        -UserInteraction userInteraction
        +Menu()
        +displayGameChoiceMenu()
        +displayPlayerChoiceMenu()
        +displayBoard()
        +askForInt()
        -intVerification()
        +showLog()
    }

    class View {
        +displayLog()
        +displayBoard()
    }

    class UserInteraction {
        -Scanner sc
        +getUserInt()
        +getUserString()
        +clearBuffer()
    }

%% Relationships
    GameController --> ControllerState
    GameController --> PlayStrategy
    GameController --> Menu
    GameController --> Player
    GameController --> GamesPreset
    GameController --> GameFactory

    PlayStrategy <|.. GameType
    GameType <|-- TicTacToe
    GameType <|-- Puissance4
    GameType <|-- Gomoku
    GameType <|-- Freestyle

    GameType --> Board
    GameType --> Player
    GameType --> RoundEnd

    Board --> Cell
    Cell --> Player

    Player <|-- HumanPlayer
    Player <|-- BotPlayer

    GameFactory --> PlayStrategy
    GameFactory --> GamesPreset
    GameFactory ..> TicTacToe
    GameFactory ..> Puissance4
    GameFactory ..> Gomoku
    GameFactory ..> Freestyle

    Menu --> View
    Menu --> UserInteraction
    Menu --> Board

    Cell --> Pawn

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
