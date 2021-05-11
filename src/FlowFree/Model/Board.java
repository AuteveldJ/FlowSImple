package FlowFree.Model;

import java.io.*;

/**
 * @author Jonathan Auteveld
 * @version 1.0 10/03/2021 21:29
 * <p>
 * Hier wordt het spelbord aangemaakt aan de hand van een .csv file
 * Uitlezen van .csv file en wegschrijven naar een 2D array
 */
public class Board {

    private final int GRIDWIDTH = 50;
    private int[][] board;
    private int rij;
    private int kolom;

    public Board() {
    }

    public void readFile() {
        this.board = new int[getRij()][getKolom()];

        //inlezen file
        InputStream gameBoard = null;
        BufferedReader br = null;
        String line;
        String separator = ",";
        int row = 0;

        switch (board.length) {
            case 5:
                gameBoard = getClass().getResourceAsStream("/files/array5x5.txt");
                break;
            case 6:
                gameBoard = getClass().getResourceAsStream("/files/array6x6.txt");
                break;
            case 7:
                gameBoard = getClass().getResourceAsStream("/files/array7x7.txt");
                break;
            case 8:
                gameBoard = getClass().getResourceAsStream("/files/array8x8.txt");
                break;
            case 9:
                gameBoard = getClass().getResourceAsStream("/files/array9x9.txt");
                break;
        }
        try {
            br = new BufferedReader(new InputStreamReader(gameBoard));
            while ((line = br.readLine()) != null) {
                //elke rij uitlezen en in een 1D Array plaatsen
                //gesplitst op komma
                String[] values = line.split(separator);
                //de values in elke kolom invullen per rij
                for (int i = 0; i < values.length; i++) {
                    board[row][i] = Integer.parseInt(values[i]);
                }
                //naar de volgende rij gaan
                row++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public int getRij() {
        return rij;
    }

    public int getKolom() {
        return kolom;
    }

    public void setRij(int rij) {
        this.rij = rij;
    }

    public void setKolom(int kolom) {
        this.kolom = kolom;
    }

    public int getGRIDWIDTH() {
        return GRIDWIDTH;
    }

    public int[][] getBoard() {
        return board;
    }
}