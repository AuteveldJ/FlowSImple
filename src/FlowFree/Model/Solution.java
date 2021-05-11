package FlowFree.Model;

import java.io.*;

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

    public void readsolution() {
        this.solution = new int[getRij()][getKolom()];

        InputStream solutionBoard = null;
        BufferedReader br = null;
        String line;
        String separator = ",";
        int row = 0;

        switch (solution.length) {
            case 5:
                solutionBoard = getClass().getResourceAsStream("/files/solution5x5.txt");
                break;
            case 6:
                solutionBoard = getClass().getResourceAsStream("/files/solution6x6.txt");
                break;
            case 7:
                solutionBoard = getClass().getResourceAsStream("/files/solution7x7.txt");
                break;
            case 8:
                solutionBoard = getClass().getResourceAsStream("/files/solution8x8.txt");
                break;
            case 9:
                solutionBoard = getClass().getResourceAsStream("/files/solution9x9.txt");
                break;
        }

        try {
            br = new BufferedReader(new InputStreamReader(solutionBoard));

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
