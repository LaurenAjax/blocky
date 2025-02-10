package com.gamewerks.blocky.engine;

import com.gamewerks.blocky.util.Constants;
import com.gamewerks.blocky.util.Position;
import java.util.Random;

public class BlockyGame {
    private static final int LOCK_DELAY_LIMIT = 30;
    
    private Board board;
    private Piece activePiece;
    private Direction movement;
    
    private int lockCounter;
    private int currentPiece = 0;
    private PieceKind[] pieceArray = shuffle(PieceKind.ALL);
    
    /**
     * Builds a blocky game and sets up the first block
     * 
     */
    public BlockyGame() {
        board = new Board();
        movement = Direction.NONE;
        lockCounter = 0;
        trySpawnBlock();
    }
    
    /**
     * Shuffles an array containing the various different pieces
     * 
     * @param pieces a standard array of all the different pieces
     * @return returns a new array containing all the pieces of the given array
     * in a new order
     */
    private PieceKind[] shuffle(PieceKind[] pieces) {
        int arr[] = {0, 1, 2, 3, 4, 5, 6};
        PieceKind[] newPieces = new PieceKind[7];
        Random rand = new Random();
        for (int i = 0; i < 7; i++) {
            int j = rand.nextInt(7);
            if (arr[j] == -1) {
                i--;
            } else {
                newPieces[i] = pieces[arr[j]];
                arr[j] = -1;
            }
        }
        return newPieces;
    }
    
    /**
     * Spawns a random piece in the top center of the panel
     * 
     */
    private void trySpawnBlock() {
        if (activePiece == null) {
            activePiece = new Piece(pieceArray[currentPiece], new Position(Constants.BOARD_HEIGHT - 19, Constants.BOARD_WIDTH / 2 - 2));
            if (board.collides(activePiece)) {
                System.exit(0);
            }
            currentPiece++;
            if (currentPiece >= 7) {
                pieceArray = shuffle(PieceKind.ALL);
                currentPiece = 0;
            }
        }
    }
    
    /**
     * Processes and makes movement depending on keyboard inputs, unless it 
     * collides with something
     * 
     */
    private void processMovement() {
        Position nextPos;
        switch(movement) {
        case NONE:
            nextPos = activePiece.getPosition();
            break;
        case LEFT:
            nextPos = activePiece.getPosition().add(0, -1);
            break;
        case RIGHT:
            nextPos = activePiece.getPosition().add(0, 1);
            break;
        default:
            throw new IllegalStateException("Unrecognized direction: " + movement.name());
        }
        if (!board.collides(activePiece.getLayout(), nextPos)) {
            activePiece.moveTo(nextPos);
        }
    }
    
    /**
     * Allows piece to slowly descend an eventually lock in place
     * 
     */
    private void processGravity() {
        Position nextPos = activePiece.getPosition().add(1, 0);
        if (!board.collides(activePiece.getLayout(), nextPos)) {
            lockCounter = 0;
            activePiece.moveTo(nextPos);
        } else {
            if (lockCounter < LOCK_DELAY_LIMIT) {
                lockCounter += 1;
            } else {
                board.addToWell(activePiece);
                lockCounter = 0;
                activePiece = null;
            }
        }
    }
    
    /**
     * Deletes the lines that have been completed
     * 
     */
    private void processClearedLines() {
        board.deleteRows(board.getCompletedRows());
    }
    
    /**
     * Moves throught the cycle of spawning a block, moving it, and clearing 
     * any completed rows
     * 
     */
    public void step() {
        trySpawnBlock();
        processMovement();
        processGravity();
        processClearedLines();
    }
    
    /**
     * Retrieves the area of the board that is 'solid' so to speak
     * 
     * @return the well of already placed blocks
     */
    public boolean[][] getWell() {
        return board.getWell();
    }
    
    /**
     * Returns the currently active piece
     * 
     * @return a piece that is to be moved
     */
    public Piece getActivePiece() { return activePiece; }
    
    /**
     * Sets movement to whatever was entered in the keyboard
     * 
     * @param movement the direction the piece is to be moved
     */
    public void setDirection(Direction movement) { this.movement = movement; }
    
    /**
     * Rotates the piece based on keyboard input
     * 
     * @param dir whether the piece will be rotated clockwise or 
     * counterclockwise
     */
    public void rotatePiece(boolean dir) { activePiece.rotate(dir); }
}
