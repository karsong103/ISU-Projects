package hw3;

import static api.Direction.*;
import static api.Orientation.*;

import java.util.ArrayList;

import api.Cell;
import api.Direction;
import api.Move;

/**
 * Represents a board in the game. A board contains a 2D grid of cells and a
 * list of boulders that slide over the cells.
 */
public class Board {
	/**
	 * Tracks the current cell that is selected by the user
	 */
	private Cell currentGrabbedCell = null;
	/**
	 * Tracks the current block that is selected by the user as a result of grabbing a cell
	 */
	private Boulder currentGrabbedBoulder = null;
	/**
	 * Tracks the total moves made by the user 
	 */
	private int totMovesMade = 0;
	/**
	 * Boolean variable that keeps tracks if the game is over at any time
	 */
	private boolean isGameOver = false;
	/**
	 * 2D array of cells, the indexes signify (row, column) with (0, 0) representing
	 * the upper-left corner of the board.
	 */
	private Cell[][] grid;

	/**
	 * A list of boulders that are positioned on the board.
	 */
	private ArrayList<Boulder> boulders;

	/**
	 * A list of moves that have been made in order to get to the current position
	 * of boulders on the board.
	 */
	private ArrayList<Move> moveHistory;

	/**
	 * Constructs a new board from a given 2D array of cells and list of boulders. The
	 * cells of the grid should be updated to indicate which cells have boulders
	 * placed over them (i.e., placeBoulder() method of Cell). The move history should
	 * be initialized as empty.
	 * 
	 * @param grid   a 2D array of cells which is expected to be a rectangular shape
	 * @param boulders list of boulders already containing row-column position which
	 *               should be placed on the board
	 */
	public Board(Cell[][] grid, ArrayList<Boulder> boulders) {
		this.grid = new Cell[grid.length][grid[0].length];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				this.grid[i][j] = grid[i][j];
			}
		}
		
		this.boulders = boulders;
		for (Boulder b : this.boulders) {
			if (b.getOrientation() == HORIZONTAL) {
				for (int i = 0; i < b.getLength(); i++) {
					this.grid[b.getFirstRow()][b.getFirstCol()+i].placeBoulder(b);
				}
			}
			else if (b.getOrientation() == VERTICAL) {
				for (int i = 0; i < b.getLength(); i++) {
					this.grid[b.getFirstRow()+i][b.getFirstCol()].placeBoulder(b);
				}
			}
		}
		
		moveHistory = new ArrayList<Move>();
		
	}

	/**
	 * DO NOT MODIFY THIS CONSTRUCTOR
	 * <p>
	 * Constructs a new board from a given 2D array of String descriptions.
	 * 
	 * @param desc 2D array of descriptions
	 */
	public Board(String[][] desc) {
		this(GridUtil.createGrid(desc), GridUtil.findBoulders(desc));
	}

	/**
	 * Returns the number of rows of the board.
	 * 
	 * @return number of rows
	 */
	public int getRowSize() {
		return grid.length;
	}

	/**
	 * Returns the number of columns of the board.
	 * 
	 * @return number of columns
	 */
	public int getColSize() {
		return grid[0].length;
	}

	/**
	 * Returns the cell located at a given row and column.
	 * 
	 * @param row the given row
	 * @param col the given column
	 * @return the cell at the specified location
	 */
	public Cell getCellAt(int row, int col) {
		return grid[row][col];
	}

	/**
	 * Returns the total number of moves (calls to moveGrabbedBoulder which
	 * resulted in a boulder being moved) made so far in the game.
	 * 
	 * @return the number of moves
	 */
	public int getMoveCount() {
		return totMovesMade;
	}

	/**
	 * Returns a list of all boulders on the board.
	 * 
	 * @return a list of all boulders
	 */
	public ArrayList<Boulder> getBoulders() {
		return boulders;
	}

	/**
	 * Returns true if the player has completed the puzzle by positioning a boulder
	 * over an exit, false otherwise.
	 * 
	 * @return true if the game is over
	 */
	public boolean isGameOver() {
		for (Cell[] cells : grid) {
			for (Cell moreCells : cells) {
				if (moreCells.isExit() && moreCells.hasBoulder()) {
					isGameOver = true;
					return isGameOver;
				}
			}
		}
		return isGameOver;
	}
	
	/**
	 * Models the user grabbing (mouse button down) a boulder over the given row and
	 * column. The purpose of grabbing a boulder is for the user to be able to drag
	 * the boulder to a new position, which is performed by calling
	 * moveGrabbedBoulder().
	 * <p>
	 * This method should find which boulder has been grabbed (if any) and record
	 * that boulder as grabbed in some way.
	 * 
	 * @param row row to grab the boulder from
	 * @param col column to grab the boulder from
	 */
	public void grabBoulderAt(int row, int col) {
		currentGrabbedCell = grid[row][col];
		
		if (currentGrabbedCell.hasBoulder()) {
			for (Boulder b : boulders) {
				if (b == currentGrabbedCell.getBoulder()) {
					currentGrabbedBoulder = b;
				}
			}
		}
		
	}

	/**
	 * Models the user releasing (mouse button up) the currently grabbed boulder
	 * (if any). Update the object accordingly to indicate no boulder is
	 * currently being grabbed.
	 */
	public void releaseBoulder() {
		currentGrabbedBoulder = null;
	}

	/**
	 * Returns the currently grabbed boulder. If there is no currently grabbed
	 * boulder the method return null.
	 * 
	 * @return the currently grabbed boulder or null if none
	 */
	public Boulder getGrabbedBoulder() {
		return currentGrabbedBoulder;
	}

	/**
	 * Returns true if the cell at the given row and column is available for a
	 * boulder to be placed over it. Boulders can only be placed over ground
	 * and exits. Additionally, a boulder cannot be placed over a cell that is
	 * already occupied by another boulder.
	 * 
	 * @param row row location of the cell
	 * @param col column location of the cell
	 * @return true if the cell is available for a boulder, otherwise false
	 */
	public boolean isAvailable(int row, int col) {
		if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
	        return false;
	    }
	    
		return (grid[row][col].isGround() && !grid[row][col].hasBoulder()) || grid[row][col].isExit();
	}

	/**
	 * Moves the currently grabbed boulder by one cell in the given direction. A
	 * horizontal boulder is only allowed to move right and left and a vertical boulder
	 * is only allowed to move up and down. A boulder can only move over a cell that
	 * is a floor or exit and is not already occupied by another boulder. The method
	 * does nothing under any of the following conditions:
	 * <ul>
	 * <li>The game is over.</li>
	 * <li>No boulder is currently grabbed by the user.</li>
	 * <li>A boulder is currently grabbed by the user, but the boulder is not allowed to
	 * move in the given direction.</li>
	 * </ul>
	 * If none of the above conditions are meet, the method does at least the following:
	 * <ul>
	 * <li>Moves the boulder object by calling its move() method.</li>
	 * <li>Calls placeBoulder() for the grid cell that the boulder is being moved into.</li>
	 * <li>Calls removeBoulder() for the grid cell that the boulder is being moved out of.</li>
	 * <li>Adds the move (as a Move object) to the end of the move history list.</li>
	 * <li>Increments the count of total moves made in the game.</li>
	 * </ul>
	 * 
	 * @param dir the direction to move
	 */
	public void moveGrabbedBoulder(Direction dir) {
		if (!isGameOver() && currentGrabbedBoulder != null) {
			if ((dir == RIGHT) && (currentGrabbedBoulder.getOrientation() == HORIZONTAL)) {
				if (isAvailable(currentGrabbedBoulder.getFirstRow(), currentGrabbedBoulder.getFirstCol() + currentGrabbedBoulder.getLength())) {
					moveHistory.add(new Move(currentGrabbedBoulder, dir));
					currentGrabbedBoulder.move(dir);
					currentGrabbedCell = grid[currentGrabbedCell.getRow()][currentGrabbedCell.getCol() + 1];
					grid[currentGrabbedBoulder.getFirstRow()][currentGrabbedBoulder.getFirstCol() - 1].placeBoulder(null);
					grid[currentGrabbedBoulder.getFirstRow()][currentGrabbedBoulder.getFirstCol() + currentGrabbedBoulder.getLength() - 1].placeBoulder(currentGrabbedBoulder);
					totMovesMade++;
				}
			}
			
			else if ((dir == LEFT) && (currentGrabbedBoulder.getOrientation() == HORIZONTAL)) {
				if (isAvailable(currentGrabbedBoulder.getFirstRow(), currentGrabbedBoulder.getFirstCol() - 1)) {
					moveHistory.add(new Move(currentGrabbedBoulder, dir));
					currentGrabbedBoulder.move(dir);
					currentGrabbedCell = grid[currentGrabbedCell.getRow()][currentGrabbedCell.getCol() - 1];
					grid[currentGrabbedBoulder.getFirstRow()][currentGrabbedBoulder.getFirstCol()].placeBoulder(currentGrabbedBoulder);
					grid[currentGrabbedBoulder.getFirstRow()][currentGrabbedBoulder.getFirstCol() + currentGrabbedBoulder.getLength()].placeBoulder(null);
					totMovesMade++;
				}
			}
			
			else if ((dir == UP) && (currentGrabbedBoulder.getOrientation() == VERTICAL)) {
				if (isAvailable(currentGrabbedBoulder.getFirstRow() - 1, currentGrabbedBoulder.getFirstCol())) {
					moveHistory.add(new Move(currentGrabbedBoulder, dir));
					currentGrabbedBoulder.move(dir);
					currentGrabbedCell = grid[currentGrabbedCell.getRow() - 1][currentGrabbedCell.getCol()];
					grid[currentGrabbedBoulder.getFirstRow()][currentGrabbedBoulder.getFirstCol()].placeBoulder(currentGrabbedBoulder);
					grid[currentGrabbedBoulder.getFirstRow() + currentGrabbedBoulder.getLength()][currentGrabbedBoulder.getFirstCol()].placeBoulder(null);
					totMovesMade++;
				}
			}
			
			else if ((dir == DOWN) && (currentGrabbedBoulder.getOrientation() == VERTICAL)) {
				if (isAvailable(currentGrabbedBoulder.getFirstRow() + currentGrabbedBoulder.getLength(), currentGrabbedBoulder.getFirstCol())) {
					moveHistory.add(new Move(currentGrabbedBoulder, dir));
					currentGrabbedBoulder.move(dir);
					currentGrabbedCell = grid[currentGrabbedCell.getRow() + 1][currentGrabbedCell.getCol()];
					grid[currentGrabbedBoulder.getFirstRow() + currentGrabbedBoulder.getLength() - 1][currentGrabbedBoulder.getFirstCol()].placeBoulder(currentGrabbedBoulder);
					grid[currentGrabbedBoulder.getFirstRow() - 1][currentGrabbedBoulder.getFirstCol()].placeBoulder(null);
					totMovesMade++;
				}
			}
		
		}
	}

	/**
	 * Resets the state of the game back to the start, which includes the move
	 * count, the move history, and whether the game is over. The method calls the
	 * reset method of each boulder object. It also updates each grid cells by calling
	 * their placeBoulder method to either set a boulder if one is located over the cell
	 * or set null if no boulder is located over the cell.
	 */
	public void reset() {
		isGameOver = false;
		totMovesMade = 0;
		
		for (Boulder b : boulders) {
			b.reset();
		}
		
		for (Cell[] cells : grid) {
			for (Cell moreCells : cells) {
				if (moreCells.hasBoulder()) {
					moreCells.removeBoulder();
				}
			}
		}
		
		for(Boulder b : this.boulders) {
			if (b.getOrientation() == HORIZONTAL) {
				for (int i = 0; i < b.getLength(); i++) {
					this.grid[b.getFirstRow()][b.getFirstCol()+i].placeBoulder(b);
				}
			}
			else if (b.getOrientation() == VERTICAL) {
				for (int i = 0; i < b.getLength(); i++) {
					this.grid[b.getFirstRow()+i][b.getFirstCol()].placeBoulder(b);
				}
			}
		}
		
		moveHistory = new ArrayList<Move>();
		currentGrabbedCell = null;
		currentGrabbedBoulder = null;
		isGameOver = false;
	}

	/**
	 * Returns a list of all legal moves that can be made by any boulder on the
	 * current board.
	 * 
	 * @return a list of legal moves
	 */
	public ArrayList<Move> getAllPossibleMoves() {
		ArrayList<Move> allPossibleMoves = new ArrayList<Move>();
		
		if(!isGameOver) {
			for (Boulder b : boulders) {
				if (b.getOrientation() == HORIZONTAL) {
					if (isAvailable(b.getFirstRow(), b.getFirstCol() + b.getLength())) {
						allPossibleMoves.add(new Move(b, RIGHT));
					}
					if (isAvailable(b.getFirstRow(), b.getFirstCol() - 1)){
						allPossibleMoves.add(new Move(b, LEFT));
					}
				}
				else if (b.getOrientation() == VERTICAL) {
					if (isAvailable(b.getFirstRow() - 1, b.getFirstCol())) {
						allPossibleMoves.add(new Move(b, UP));
					}
					if (isAvailable(b.getFirstRow() + b.getLength(), b.getFirstCol())) {
						allPossibleMoves.add(new Move(b, DOWN));
					}
				}
			}
			
			return allPossibleMoves;
			}
		
		return null;
	}

	/**
	 * Gets the list of all moves performed to get to the current position on the
	 * board.
	 * 
	 * @return a list of moves performed to get to the current position
	 */
	public ArrayList<Move> getMoveHistory() {
		return moveHistory;
	}

	/**
	 * EXTRA CREDIT 5 POINTS
	 * <p>
	 * This method is only used by the Solver.
	 * <p>
	 * Undo the previous move. The method gets the last move on the moveHistory list
	 * and performs the opposite actions of that move, which are the following:
	 * <ul>
	 * <li>if required, sets is game over to false</li>
	 * <li>grabs the moved boulder and calls moveGrabbedBoulder passing the opposite
	 * direction</li>
	 * <li>decreases the total move count by two to undo the effect of calling
	 * moveGrabbedBoulder twice</li>
	 * <li>removes the move from the moveHistory list</li>
	 * </ul>
	 * If the moveHistory list is empty this method does nothing.
	 */
	public void undoMove() {
		// TODO
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		boolean first = true;
		for (Cell row[] : grid) {
			if (!first) {
				buff.append("\n");
			} else {
				first = false;
			}
			for (Cell cell : row) {
				buff.append(cell.toString());
				buff.append(" ");
			}
		}
		return buff.toString();
	}
}
