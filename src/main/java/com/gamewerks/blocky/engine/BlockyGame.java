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
    
    public BlockyGame() {
        board = new Board();
        movement = Direction.NONE;
        lockCounter = 0;
        trySpawnBlock();
    }
    
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
    
    private void processClearedLines() {
        board.deleteRows(board.getCompletedRows());
    }
    
    public void step() {
        trySpawnBlock();
        processMovement();
        processGravity();
        processClearedLines();
    }
    
    public boolean[][] getWell() {
        return board.getWell();
    }
    
    public Piece getActivePiece() { return activePiece; }
    public void setDirection(Direction movement) { this.movement = movement; }
    public void rotatePiece(boolean dir) { activePiece.rotate(dir); }
}
