package FlowFree.Model;

import java.io.*;
import java.util.Arrays;

/**
 * @author Jonathan Auteveld
 * @version 1.0 10/03/2021 21:29
 *
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

    public Board(int rij, int kolom) {
        this.rij = rij;
        this.kolom = kolom;
    }

    public void readFile() {
        this.board = new int[getRij()][getKolom()];

        //inlezen file
        InputStream gameBoard;
        BufferedReader br = null;
        String line;
        String separator = ",";
        int row = 0;

        if (board.length == 5) {
            try {
                gameBoard = getClass().getResourceAsStream("/files/array5x5.txt");
                br = new BufferedReader(new InputStreamReader(gameBoard));

                while ((line = br.readLine()) != null) {
                    //elke rij uitlezen en in een 1D Array plaatsen
                    //geplitst op komma
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
            // testen inlezen array
            for (int[] ints : board) {
                System.out.println(Arrays.toString(ints));
            }

        } else if (board.length == 6) {
            try {
                gameBoard = getClass().getResourceAsStream("/files/array6x6.txt");
                br = new BufferedReader(new InputStreamReader(gameBoard));


                while ((line = br.readLine()) != null) {
                    String[] values = line.split(separator);

                    for (int i = 0; i < values.length; i++) {
                        board[row][i] = Integer.parseInt(values[i]);
                    }
                    row++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ie) {
                ie.printStackTrace();
            }
            for (int[] ints : board) {
                System.out.println(Arrays.toString(ints));
            }
        } else if (board.length == 7) {
            try {
                gameBoard = getClass().getResourceAsStream("/files/array7x7.txt");
                br = new BufferedReader(new InputStreamReader(gameBoard));


                while ((line = br.readLine()) != null) {
                    String[] values = line.split(separator);

                    for (int i = 0; i < values.length; i++) {
                        board[row][i] = Integer.parseInt(values[i]);
                    }
                    row++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ie) {
                ie.printStackTrace();
            }
            for (int[] ints : board) {
                System.out.println(Arrays.toString(ints));
            }
        } else if (board.length == 8) {
            try {
                gameBoard = getClass().getResourceAsStream("/files/array8x8.txt");
                br = new BufferedReader(new InputStreamReader(gameBoard));


                while ((line = br.readLine()) != null) {
                    String[] values = line.split(separator);

                    for (int i = 0; i < values.length; i++) {
                        board[row][i] = Integer.parseInt(values[i]);
                    }
                    row++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ie) {
                ie.printStackTrace();
            }
            for (int[] ints : board) {
                System.out.println(Arrays.toString(ints));
            }
        } else if (board.length == 9) {
            try {
                gameBoard = getClass().getResourceAsStream("/files/array9x9.txt");
                br = new BufferedReader(new InputStreamReader(gameBoard));


                while ((line = br.readLine()) != null) {
                    String[] values = line.split(separator);

                    for (int i = 0; i < values.length; i++) {
                        board[row][i] = Integer.parseInt(values[i]);
                    }
                    row++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ie) {
                ie.printStackTrace();
            }
            for (int[] ints : board) {
                System.out.println(Arrays.toString(ints));
            }
        }
    }

        public int getRij () {
            return rij;
        }

        public int getKolom () {
            return kolom;
        }

        public void setRij ( int rij){
            this.rij = rij;
        }

        public void setKolom ( int kolom){
            this.kolom = kolom;
        }

        public int getGRIDWIDTH () {
            return GRIDWIDTH;
        }

        public int[][] getBoard () {
            return board;
        }
    }
