# Minesweeper

Minesweeper is a puzzle game. In the game, mines are scattered throughout a board, which is divided into cells. Cells have three states: unopened, opened and flagged. Flagged cells are unopened cells marked to indicate a potential mine location.

A player selects a cell to open it. If a player opens a mined cell, the game ends in a loss. Otherwise, the opened cell displays either a number, indicating the number of mines diagonally and/or adjacent to it, or a blank tile, and all adjacent non-mined cells will automatically be opened. Players can also flag a cell, visualised by a `x` being put on the location, to denote that they believe a mine to be in that place. Flagged cells are still considered unopened, and may be unflagged.

## How to Use

Run the [App class](src/com/assodepicche/minesweeper/App.java) and the game will start with a 3 x 3 grid and 2 hidden bombs. At the beginning, the grid will only contain hash symbols to represent closed fields, when one of them has been opened it will be replaced by an empty space. Asterisk represents bombs and `x` represents flagged fields.

## How to Install

You can clone the repository on your desktop or simply download the compressed file by clicking on Code and then Download ZIP.

```bash
git clone git@github.com:AssoDePicche/minesweeper.git
```
