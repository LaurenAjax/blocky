package com.gamewerks.blocky.engine;

import java.io.IOException;
import java.util.HashMap;

import com.gamewerks.blocky.util.Loader;
import com.gamewerks.blocky.util.Position;

public class Piece {
    
    private static HashMap ROTATION_DATA = null;
    
    static {
        try {
            ROTATION_DATA = Loader.loadAllRotationData();
        } catch (IOException ex) {
            System.out.println("Exception occurred loading rotation data");
            System.exit(-1);
        }
    }
    
    private PieceKind kind;
    private int orientation;
    private Position pos;
    
    /**
     * Builds a piece of the given kind at the given position
     * 
     * @param kind the type of piece
     * @param pos the location of the piece
     */
    public Piece(PieceKind kind, Position pos) {
        this.kind = kind;
        orientation = 0;
        this.pos = pos;
    }
    
    /**
     * Returns the location of a piece
     * 
     * @return the row and column where the piece is located
     */
    public Position getPosition() {
        return pos;
    }
    
    /**
     * Changes the location of a piece
     * 
     * @param p the current position of the piece
     */
    public void moveTo(Position p) {
        pos = p;
    }
    
    /**
     * Gives the shape and make of a piece
     * 
     * @return the layout of the piece
     */
    public boolean[][] getLayout() {
        return ((boolean[][][]) ROTATION_DATA.get(kind))[orientation];
    }
    
    /**
     * Indicates the orientation of a piece
     * 
     * @param dir the direction the piece is rotated
     */
    public void rotate(boolean dir) {
        if (dir) {
            orientation = (orientation + 1) % 4;
        } else {
            int k = orientation - 1;
            orientation = k < 0 ? 3 : k;
        }
    }
}
