package FlowFree.Model;

import FlowFree.View.Gamescherm.GameschermView;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jonathan Auteveld
 * @version 1.0 12/02/2021 22:43
 */
public class FlowFreeModel {
    private boolean won = false;
    private List<Player> playerList;
    private Player gamePlayer;
    private Board board = new Board();
    private LocalDate date = LocalDate.now();

    private int currentx;
    private int currenty;
    private int pipex;
    private int pipey;

    private int pipeWidth;
    private final int rectOffset = 15;
    private int boardSize;

    public FlowFreeModel() {

    }

    public void setBoard(int row, int column) {
        this.boardSize = row;
        this.board.setRij(row);
        this.board.setKolom(column);
        this.board.readFile();
    }

    public void createBoard(GameschermView view) {
        //canvas eerst leegmaken met clearrect
        //canvas size aanpassen zodat deze mooi in het midden van het veld komt
        GraphicsContext gc = view.getCanvas().getGraphicsContext2D();
        gc.clearRect(0, 0, view.getCanvas().getWidth(), view.getCanvas().getHeight());
        view.getCanvas().setWidth(board.getGRIDWIDTH() * board.getRij() + 10);
        view.getCanvas().setHeight(board.getGRIDWIDTH() * board.getKolom() + 10);

        //bord aanmaken door vierkanten te tekenen op het canvas
        //5X5 = 5 kleuren, 6x6, 7X7, 8X8 = 6 kleuren ,9X9 = 9 kleuren
        //board tekenen
        gc.setStroke(Color.WHITE);

        for (int i = 0; i < board.getBoard().length; i++) { //rij
            for (int j = 0; j < board.getBoard()[i].length; j++) { //kolom
                int x = i * board.getGRIDWIDTH();
                int y = j * board.getGRIDWIDTH();

                int diameter = board.getGRIDWIDTH() - 10;
                int circOffset = 5;

                gc.strokeRect(x, y, board.getGRIDWIDTH(), board.getGRIDWIDTH());

                int val = board.getBoard()[j][i];
                if (val < 1 || val > 9) continue; //waarden hierbuiten uitsluiten
                Color color = Color.WHITE;

                switch (val) {
                    case 1:
                        color = Color.RED;
                        break;
                    case 2:
                        color = Color.BLUE;
                        break;
                    case 3:
                        color = Color.GREEN;
                        break;
                    case 4:
                        color = Color.ORANGE;
                        break;
                    case 5:
                        color = Color.YELLOW;
                        break;
                    case 6:
                        color = Color.TURQUOISE;
                        break;
                    case 7:
                        color = Color.PURPLE;
                        break;
                    case 8:
                        color = Color.HOTPINK;
                        break;
                    case 9:
                        color = Color.BROWN;
                        break;
                }
                gc.setFill(color);
                gc.fillOval(x + circOffset, y + circOffset, diameter, diameter);
            }
        }
    }

    /* als de positie op het spelbord waar je klikt een bol is ( > 0 uit de array)
     * dan ga je deze positie selecteren om een pipe te starten */
    public boolean dotSelection(int x, int y) {
        if (board.getBoard()[y][x] > 0) {
            return true;
        } else
            return false;
    }

    /* positie bepalen waar je muis naar beweegt
     * deze plaats wordt een negatieve waarde van de overeenkomende bol dat geselecteerd is
     * hierover zal een pipe getekend worden.
     * Dit wordt voorgesteld als een negatieve waarde van de Board array */
    public void drag(int currentx, int currenty, int dragx, int dragy, int startx, int starty) {
        if (dragx >= 0 && dragy >= 0 &&
                dragy < board.getBoard().length && dragx < board.getBoard()[0].length && board.getBoard()[dragy][dragx] == 0) {
            if ((dragx == currentx + 1 && dragy == currenty) || //rechts
                    (dragx == currentx - 1 && dragy == currenty) || //links
                    (dragx == currentx && dragy == currenty + 1) || //boven
                    (dragx == currentx && dragy == currenty - 1)) { //onder
                board.getBoard()[dragy][dragx] = -board.getBoard()[starty][startx]; //negatieve waarde zal een pipe worden

                this.currentx = dragx;
                this.currenty = dragy;
            }
        }
    }


    public int getCurrentx() {
        return currentx;
    }

    public int getCurrenty() {
        return currenty;
    }

    public void drawPipe(GameschermView view) {
        GraphicsContext gc = view.getCanvas().getGraphicsContext2D();
        for (int i = 0; i < board.getBoard().length; i++) { //rij
            for (int j = 0; j < board.getBoard()[i].length; j++) { //kolom
                pipex = i * board.getGRIDWIDTH();
                pipey = j * board.getGRIDWIDTH();

                /* Richting bepalen waar je naar gaat */
                boolean left = false;
                boolean right = false;
                boolean above = false;
                boolean below = false;

                if (i > 0 && Math.abs(board.getBoard()[j][i]) == Math.abs(board.getBoard()[j][i - 1])) {
                    left = true;
                }
                if (j > 0 && Math.abs(board.getBoard()[j][i]) == Math.abs(board.getBoard()[j - 1][i])) {
                    above = true;
                }
                if (i < board.getBoard().length - 1 && Math.abs(board.getBoard()[j][i]) == Math.abs(board.getBoard()[j][i + 1])) {
                    right = true;
                }
                if (j < board.getBoard().length - 1 && Math.abs(board.getBoard()[j][i]) == Math.abs(board.getBoard()[j + 1][i])) {
                    below = true;
                }

                int val = board.getBoard()[j][i];
                if (val < -9 || val > -1) continue; //waarden hierbuiten uitsluiten
                Color color = Color.WHITE;

                switch (val) {
                    case -1:
                        color = Color.RED;
                        break;
                    case -2:
                        color = Color.BLUE;
                        break;
                    case -3:
                        color = Color.GREEN;
                        break;
                    case -4:
                        color = Color.ORANGE;
                        break;
                    case -5:
                        color = Color.YELLOW;
                        break;
                    case -6:
                        color = Color.TURQUOISE;
                        break;
                    case -7:
                        color = Color.PURPLE;
                        break;
                    case -8:
                        color = Color.HOTPINK;
                        break;
                    case -9:
                        color = Color.BROWN;
                        break;
                }

                gc.setFill(color);
                gc.fillRect(pipex + getRectOffset(), pipey + getRectOffset(), getPipeWidth(), getPipeWidth());

                if (above && below) {
                    gc.fillRect(pipex + getRectOffset(), pipey, getPipeWidth(), board.getGRIDWIDTH());
                }
                if (left && right) {
                    gc.fillRect(pipex, pipey + getRectOffset(), board.getGRIDWIDTH(), getPipeWidth());
                }
                if (above && left) { //LINKS ONDER
                    gc.fillRect(pipex, pipey + getRectOffset(), getPipeWidth(), getPipeWidth());
                    gc.fillRect(pipex + getRectOffset(), pipey, getPipeWidth(), getPipeWidth());
                }
                if (above && right) { //RECHTS ONDER
                    gc.fillRect(pipex + (board.getGRIDWIDTH() / 2), pipey + getRectOffset(), getPipeWidth(), getPipeWidth());
                    gc.fillRect(pipex + getRectOffset(), pipey, getPipeWidth(), getPipeWidth());
                }
                if (below && left) { //LINKS BOVEN
                    gc.fillRect(pipex + getRectOffset(), pipey + (board.getGRIDWIDTH() - getRectOffset()), getPipeWidth(), (board.getGRIDWIDTH() - getRectOffset()));
                    gc.fillRect(pipex, pipey + getRectOffset(), (board.getGRIDWIDTH() - getRectOffset()), getPipeWidth());
                }
                if (below && right) { //RECHTS BOVEN
                    gc.fillRect(pipex + getRectOffset(), pipey + (board.getGRIDWIDTH() - getRectOffset()), getPipeWidth(), (board.getGRIDWIDTH() - getRectOffset()));
                    gc.fillRect(pipex + getRectOffset(), pipey + getRectOffset(), (board.getGRIDWIDTH() - getRectOffset()), getPipeWidth());
                }
            }
        }
    }

    public void saveGame(int boardSize) {
        this.boardSize = boardSize;
        String highScore = "highscore.bin";
        Path highscorePath = Paths.get(highScore);

        if (Files.exists(highscorePath)) {
            List<String> logList = new ArrayList<>();
            List<String> playerList = new ArrayList<>();

            try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(highScore)))) {
                while (dis.available() > 0) {
                    String log = dis.readUTF();
                    logList.add(log);
                    playerList.add(log.split("-")[3]);
                }
            } catch (Exception e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
                throw new FreeFlowException(e);
            }
            try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(highScore)))) {
                for (String log : logList) {
                    dos.writeUTF(log);
                }
                dos.writeUTF((boardSize + "x" + boardSize + " - " + date.toString() + " - " + gamePlayer.getName() + " - " + gamePlayer.getMoves() + "\n"));
            } catch (Exception e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
                throw new FreeFlowException(e);
            }
        } else {
            try {
                Files.createFile(highscorePath);
            } catch (IOException io) {
                throw new FreeFlowException(io);
            }
            try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(highScore)))) {
                dos.writeUTF("Boardsize - Date - Playername - Moves\n");
                dos.writeUTF((boardSize + "x" + boardSize + " - " + date.toString() + " - " + gamePlayer.getName() + " - " + gamePlayer.getMoves() + "\n"));
            } catch (Exception e) {
                throw new FreeFlowException(e);
            }
        }
    }

    public void createPlayerlist(String name) {
        playerList = new ArrayList<>();
        Player player = new Player(name);

        playerList.add(player);
        gamePlayer = playerList.get(0);
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public int getPipeWidth() {
        pipeWidth = board.getGRIDWIDTH() - 30;
        return pipeWidth;
    }

    public int getRectOffset() {
        return rectOffset;
    }

    public Player getGamePlayer() {
        return gamePlayer;
    }

    public int getBoardSize() {
        return boardSize;
    }
}
