package com.gamewerks.blocky.util;

public class Position {
    public int row;
    public int col;
    
    /**
     * Sets the row and column of a piece
     * 
     * @param row the row of the piece
     * @param col the column of the piece
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    /**
     * Adds its movement to the position of a piece
     * 
     * @param row the amount moved by row
     * @param col the amount moved by column
     * @return 
     */
    public Position add(int row, int col) {
        return new Position(this.row + row, this.col + col);
    }
    
    /**
     * Prints the current location of a piece
     * 
     * @return a string stating the row and column of the piece
     */
    public String toString() { 
        return String.format("(row = %d, col = %d)", row, col); 
    } 
}
