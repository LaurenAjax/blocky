package com.gamewerks.blocky.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import com.gamewerks.blocky.engine.PieceKind;

public class Loader {
    
    /**
     * Figures out how the piece is to be rotated
     * 
     * @param in the key pressed to rotate the piece
     * @return the direction the piece is rotated
     */
    private static boolean[][] readRotation(Scanner in) {
        boolean[][] rotation = new boolean[4][4];
        for (int row = 3; row >= 0; row--) {
            String line = in.nextLine();
            for (int col = 0; col < 4; col++) {
                rotation[row][col] = line.charAt(col) == 'x';
            }
        }
        return rotation;
    }
    
    /**
     * Files away rotation data and returns the rotation
     * 
     * @param piece the piece that is being rotated
     * @return how the piece is being rotated
     * @throws IOException in case scanner cannot be registered
     */
    public static boolean[][][] loadRotationData(PieceKind piece) throws IOException {
        boolean[][][] data = new boolean[4][][];
        File file = new File(Constants.DATA_PATH, piece.toString() + ".data");
        Scanner in = new Scanner(file);
        for (int i = 0; i < 4; i++) {
            data[i] = readRotation(in);
            if (in.hasNextLine()) {
                in.nextLine();
            }
        }
        in.close();
        return data;
    }
    
    /**
     * Figures out how each piece should be rotated
     * 
     * @return returns the HashMap of the pieces rotation
     * @throws IOException in case HashMap cannot be done
     */
    public static HashMap loadAllRotationData() throws IOException {
        HashMap ret = new HashMap();
        for (int i = 0; i < PieceKind.ALL.length; i++) {
            PieceKind piece = PieceKind.ALL[i];
            ret.put(piece, loadRotationData(piece));
        }
        return ret;
    }
}
