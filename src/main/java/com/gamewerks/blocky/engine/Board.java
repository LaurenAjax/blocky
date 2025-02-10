package com.gamewerks.blocky.engine;

import java.util.LinkedList;
import java.util.List;

import com.gamewerks.blocky.util.Constants;
import com.gamewerks.blocky.util.Position;

public class Board {
    private boolean[][] well;
    
    /**
     * The well of positions the active pieces cannot move to
     * 
     */
    public Board() {
        well = new boolean[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH];
    }
    
    /**
     * Determines whether the location of the piece is valid
     * 
     * @param row the row the piece is in
     * @param col the column the piece is in
     * @return a boolean that is true if the position is valid and false
     * otherwise
     */
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row <= well.length - 4 && col >= 0 && col <= well[0].length - 1;
    }
    
    /**
     * Determines whether a piece has collied with something
     * 
     * @param p the active piece
     * @return a boolean that is true if it has collided and false otherwise
     */
    public boolean collides(Piece p) {
        return collides(p.getLayout(), p.getPosition());
    }
    
    /**
     * Determined whether the location of the piece is going to enter the well
     * and stops it if so
     * 
     * @param layout the way the piece is shaped
     * @param pos the location of the piece
     * @return whether or not the piece has hit the well
     */
    public boolean collides(boolean[][] layout, Position pos) {
        for (int row = 0; row < layout.length; row++) {
            int wellRow = pos.row - row;
            for (int col = 0; col < layout[row].length; col++) {
                int wellCol = col + pos.col;
                if (layout[row][col]) {
                    if (!isValidPosition(wellRow, wellCol)) {
                        return true;
                    } else if (well[wellRow][wellCol]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Adds the given piece to the well once it has stopped moving
     * 
     * @param p the piece to be added to the well
     */
    public void addToWell(Piece p) {
        boolean[][] layout = p.getLayout();
        Position pos = p.getPosition();
        for (int row = 0; row < layout.length; row++) {
            int wellRow = pos.row - row;
            for (int col = 0; col < layout[row].length; col++) {
                int wellCol = pos.col + col;
                if (isValidPosition(wellRow, wellCol) && layout[row][col]) {
                    well[wellRow][wellCol] = true;
                }
            }
        }
    }
    
    /**
     * Removes the row at the given index from the panel once completed
     * 
     * @param n the index of the completed row
     */
    public void deleteRow(int n) {
        for (int col = 0; col < Constants.BOARD_WIDTH; col++) {
            well[n][col] = false;
        }
        for (int row = n; row > 0; row--) {
            for (int col = 0; col < Constants.BOARD_WIDTH; col++) {
                well[row][col] = well[row - 1][col];
            }
        }
    }
    
    /**
     * Deleted all rows found in the given list
     * 
     * @param rows a list of all completed rows
     */
    public void deleteRows(List rows) {
        for (int i = 0; i < rows.size(); i++) {
            int row = (Integer) rows.get(i);
            deleteRow(row);
        }
    }
    
    /**
     * Finds a row that has been filled entirely by pieces
     * 
     * @param row the index of the completed row
     * @return a boolean indicating that the row is completed
     */
    public boolean isCompletedRow(int row) {
        boolean isCompleted = true;
        for (int col = 0; col < Constants.BOARD_WIDTH; col++) {
            isCompleted = isCompleted && well[row][col];
        }
        return isCompleted;
    }
    
    /**
     * Returns a list of the indexes of completed rows
     * 
     * @return a list of row numbers
     */
    public List getCompletedRows() {
        List completedRows = new LinkedList();
        for (int row = 0; row < Constants.BOARD_HEIGHT; row++) {
            if (isCompletedRow(row)) {
                completedRows.add(row);
            }
        }
        return completedRows;
    }
    
    /**
     * Returns the well of the panel
     * 
     * @return a boolean indicating which parts of the panel are part of the
     * well
     */
    public boolean[][] getWell() { return well; }
}
