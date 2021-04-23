package FlowFree.Model;

import java.io.*;
import java.util.Arrays;

/**
 * @author Jonathan Auteveld
 * @version 1.0 13/04/2021 22:38
 */
public class Solution {
    private int[][] solution;
    private int rij;
    private int kolom;

    public Solution() {
    }

    public Solution(int rij, int kolom) {
        this.rij = rij;
        this.kolom = kolom;
    }

    public void readsolution() {
        this.solution = new int[getRij()][getKolom()];

        InputStream gameBoard;
        BufferedReader br = null;
        String line;
        String separator = ",";
        int row = 0;

        if (solution.length == 5) {
            try {
                gameBoard = getClass().getResourceAsStream("/files/solution5x5.txt");
                br = new BufferedReader(new InputStreamReader(gameBoard));

                while ((line = br.readLine()) != null) {
                    String[] values = line.split(separator);
                    for (int i = 0; i < values.length; i++) {
                        solution[row][i] = Integer.parseInt(values[i]);
                    }
                    row++;
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ie) {
                ie.printStackTrace();
            }

        } else if (solution.length == 6) {
            try {
                gameBoard = getClass().getResourceAsStream("/files/solution6x6.txt");
                br = new BufferedReader(new InputStreamReader(gameBoard));

                while ((line = br.readLine()) != null) {
                    String[] values = line.split(separator);

                    for (int i = 0; i < values.length; i++) {
                        solution[row][i] = Integer.parseInt(values[i]);
                    }
                    row++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ie) {
                ie.printStackTrace();
            }
            for (int[] ints : solution) {
                System.out.println(Arrays.toString(ints));
            }
        } else if (solution.length == 7) {
            try {
                gameBoard = getClass().getResourceAsStream("/files/solution7x7.txt");
                br = new BufferedReader(new InputStreamReader(gameBoard));

                while ((line = br.readLine()) != null) {
                    String[] values = line.split(separator);

                    for (int i = 0; i < values.length; i++) {
                        solution[row][i] = Integer.parseInt(values[i]);
                    }
                    row++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ie) {
                ie.printStackTrace();
            }
            for (int[] ints : solution) {
                System.out.println(Arrays.toString(ints));
            }
        } else if (solution.length == 8) {
            try {
                gameBoard = getClass().getResourceAsStream("/files/solution8x8.txt");
                br = new BufferedReader(new InputStreamReader(gameBoard));

                while ((line = br.readLine()) != null) {
                    String[] values = line.split(separator);

                    for (int i = 0; i < values.length; i++) {
                        solution[row][i] = Integer.parseInt(values[i]);
                    }
                    row++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ie) {
                ie.printStackTrace();
            }
            for (int[] ints : solution) {
                System.out.println(Arrays.toString(ints));
            }
        } else if (solution.length == 9) {
            try {
                gameBoard = getClass().getResourceAsStream("/files/solution9x9.txt");
                br = new BufferedReader(new InputStreamReader(gameBoard));

                while ((line = br.readLine()) != null) {
                    String[] values = line.split(separator);

                    for (int i = 0; i < values.length; i++) {
                        solution[row][i] = Integer.parseInt(values[i]);
                    }
                    row++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ie) {
                ie.printStackTrace();
            }
            for (int[] ints : solution) {
                System.out.println(Arrays.toString(ints));
            }
        }
    }

    public int[][] getSolution() {
        return solution;
    }

    public int getRij() {
        return rij;
    }

    public void setRij(int rij) {
        this.rij = rij;
    }

    public int getKolom() {
        return kolom;
    }

    public void setKolom(int kolom) {
        this.kolom = kolom;
    }
}
