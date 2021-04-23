package FlowFree.View.Gamescherm;

import FlowFree.Model.FlowFreeModel;
import FlowFree.Model.Board;
import FlowFree.Model.Player;
import FlowFree.Model.Solution;
import FlowFree.View.Startschem.StartschermPresenter;
import FlowFree.View.Startschem.StartschermView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Arrays;

/**
 * @author Jonathan Auteveld
 * @version 1.0 21/02/2021 21:03
 */

public class GameschermPresenter {
    private GameschermView view;
    private FlowFreeModel model;
    private Board board = new Board();
    private Solution solution = new Solution();
    private Player speler = new Player("testspeler");

    private boolean won = false;

    private boolean selected = false; //maak een selectie op het bord
    private int startx;
    private int starty;
    private int currentx;
    private int currenty;
    private int dragx;
    private int dragy;
    private int endx;
    private int endy;
    private int pipex;
    private int pipey;


    public GameschermPresenter(FlowFreeModel model, GameschermView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StartschermView startView = new StartschermView();
                StartschermPresenter startPresenter = new StartschermPresenter(model, startView);

                view.getScene().setRoot(startView);
                startView.getScene().getWindow().sizeToScene();
                startPresenter.windowsHandler();
            }
        });
        view.getBtnRestart().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //VELD TERUG INITIALISEREN ZOALS JUIST GEKOZEN SPEL
                board.setRij(speler.getLevel());
                board.setKolom(speler.getLevel());
                board.readFile();

                solution.setRij(speler.getLevel());
                solution.setKolom(speler.getLevel());
                solution.readsolution();

                speler.setMoves(0);
                view.getLblMoves().setText("Moves: ");
                view.getLblSolution().setText("");
                createBoard();
            }
        });

        view.getVijf().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                board.setRij(5);
                board.setKolom(5);
                board.readFile();

                solution.setRij(5);
                solution.setKolom(5);
                solution.readsolution();

                speler.setMoves(0);
                speler.setLevel(5);
                view.getLblMoves().setText("Moves: ");
                view.getLblSolution().setText("");
                createBoard();
            }
        });
        view.getZes().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                board.setRij(6);
                board.setKolom(6);
                board.readFile();

                solution.setRij(6);
                solution.setKolom(6);
                solution.readsolution();

                speler.setMoves(0);
                speler.setLevel(6);
                view.getLblMoves().setText("Moves: ");
                view.getLblSolution().setText("");
                createBoard();
            }
        });
        view.getZeven().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                board.setRij(7);
                board.setKolom(7);
                board.readFile();

                solution.setRij(7);
                solution.setKolom(7);
                solution.readsolution();

                speler.setMoves(0);
                speler.setLevel(7);
                view.getLblMoves().setText("Moves: ");
                view.getLblSolution().setText("");
                createBoard();
            }
        });
        view.getAcht().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                board.setRij(8);
                board.setKolom(8);
                board.readFile();

                solution.setRij(8);
                solution.setKolom(8);
                solution.readsolution();

                speler.setMoves(0);
                speler.setLevel(8);
                view.getLblMoves().setText("Moves: ");
                view.getLblSolution().setText("");
                createBoard();
            }
        });
        view.getNegen().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                board.setRij(9);
                board.setKolom(9);
                board.readFile();

                solution.setRij(9);
                solution.setKolom(9);
                solution.readsolution();

                speler.setMoves(0);
                speler.setLevel(9);
                view.getLblMoves().setText("Moves: ");
                view.getLblSolution().setText("");
                createBoard();
            }
        });

        view.getCanvas().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                /* selectie maken van je positie waarop je nu klikt
                 * als je klikt op het spelbord ga je deze positie waarop je klikt vastleggen */
                if (!selected) {
                    /* geheel getal voor kolom en rij index verkrijgen */
                    startx = (int) (event.getX() / board.getGRIDWIDTH());
                    starty = (int) (event.getY() / board.getGRIDWIDTH());
                    currentx = startx;
                    currenty = starty;

                    /* als de positie waar je klikt een bol is ( > 0 uit de array) dan ga je deze positie selecteren */
                    if (board.getBoard()[starty][startx] > 0) {
                        selected = true;
                    }
                }

                System.out.println("testklik pressed " + "op locatie x: " + currentx + " y: " + currenty + " en selected = " + selected);

                if (selected) {
                    /* alleen als je op een bol staat drag starten */
                    event.setDragDetect(true);

                    view.getCanvas().setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            System.out.println("mouse dragged");
                            event.setDragDetect(false);

                            dragx = (int) (event.getX() / board.getGRIDWIDTH());
                            dragy = (int) (event.getY() / board.getGRIDWIDTH());

                            if (dragx >= 0 && dragy >= 0 &&
                                    dragy < board.getBoard().length && dragx < board.getBoard()[0].length && board.getBoard()[dragy][dragx] == 0) {
                                if ((dragx == currentx + 1 && dragy == currenty) || //rechts
                                        (dragx == currentx - 1 && dragy == currenty) || //links
                                        (dragx == currentx && dragy == currenty + 1) || //boven
                                        (dragx == currentx && dragy == currenty - 1)) { //onder
                                    board.getBoard()[dragy][dragx] = -board.getBoard()[starty][startx]; //negatieve waarde is een pipe
                                    currentx = dragx;
                                    currenty = dragy;
                                }
                            }

                            GraphicsContext gc = view.getCanvas().getGraphicsContext2D();
                            for (int i = 0; i < board.getBoard().length; i++) { //rij
                                for (int j = 0; j < board.getBoard()[i].length; j++) { //kolom
                                    pipex = i * board.getGRIDWIDTH();
                                    pipey = j * board.getGRIDWIDTH();

                                    int width = board.getGRIDWIDTH() - 30;
                                    int rectOffset = 15;

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

                                    //pipe tekenen, aaneenschakeling van fillrect over de negatieve waarden uit array
                                    switch (board.getBoard()[j][i]) {
                                        case -1:
                                            gc.setFill(Color.RED);
                                            gc.fillRect(pipex + rectOffset, pipey + rectOffset, width, width);

                                            if (above && below) {
                                                gc.fillRect(pipex + rectOffset, pipey, width, board.getGRIDWIDTH());
                                            }
                                            if (left && right) {
                                                gc.fillRect(pipex, pipey + rectOffset, board.getGRIDWIDTH(), width);
                                            }
                                            if (above && left) { //LINKS ONDER
                                                gc.fillRect(pipex, pipey + rectOffset, width, width);
                                                gc.fillRect(pipex + rectOffset, pipey, width, width);
                                            }
                                            if (above && right) { //RECHTS ONDER
                                                gc.fillRect(pipex + (board.getGRIDWIDTH() / 2), pipey + rectOffset, width, width);
                                                gc.fillRect(pipex + rectOffset, pipey, width, width);
                                            }
                                            if (below && left) { //LINKS BOVEN
                                                gc.fillRect(pipex + rectOffset, pipey + (board.getGRIDWIDTH() - rectOffset), width, (board.getGRIDWIDTH() - rectOffset));
                                                gc.fillRect(pipex, pipey + rectOffset, (board.getGRIDWIDTH() - rectOffset), width);
                                            }
                                            if (below && right) { //RECHTS BOVEN
                                                gc.fillRect(pipex + rectOffset, pipey + (board.getGRIDWIDTH() - rectOffset), width, (board.getGRIDWIDTH() - rectOffset));
                                                gc.fillRect(pipex + rectOffset, pipey + rectOffset, (board.getGRIDWIDTH() - rectOffset), width);
                                            }
                                            break;
                                        case -2:
                                            gc.setFill(Color.BLUE);
                                            gc.fillRect(pipex + rectOffset, pipey + rectOffset, width, width);

                                            if (above && below) {
                                                gc.fillRect(pipex + rectOffset, pipey, width, board.getGRIDWIDTH());
                                            }
                                            if (left && right) {
                                                gc.fillRect(pipex, pipey + rectOffset, board.getGRIDWIDTH(), width);
                                            }
                                            if (above && left) { //LINKS ONDER
                                                gc.fillRect(pipex, pipey + rectOffset, width, width);
                                                gc.fillRect(pipex + rectOffset, pipey, width, width);
                                            }
                                            if (above && right) { //RECHTS ONDER
                                                gc.fillRect(pipex + (board.getGRIDWIDTH() / 2), pipey + rectOffset, width, width);
                                                gc.fillRect(pipex + rectOffset, pipey, width, width);
                                            }
                                            if (below && left) { //LINKS BOVEN
                                                gc.fillRect(pipex + rectOffset, pipey + (board.getGRIDWIDTH() - rectOffset), width, (board.getGRIDWIDTH() - rectOffset));
                                                gc.fillRect(pipex, pipey + rectOffset, (board.getGRIDWIDTH() - rectOffset), width);
                                            }
                                            if (below && right) { //RECHTS BOVEN
                                                gc.fillRect(pipex + rectOffset, pipey + (board.getGRIDWIDTH() - rectOffset), width, (board.getGRIDWIDTH() - rectOffset));
                                                gc.fillRect(pipex + rectOffset, pipey + rectOffset, (board.getGRIDWIDTH() - rectOffset), width);
                                            }
                                            break;
                                        case -3:
                                            gc.setFill(Color.GREEN);
                                            gc.fillRect(pipex + rectOffset, pipey + rectOffset, width, width);

                                            if (above && below) {
                                                gc.fillRect(pipex + rectOffset, pipey, width, board.getGRIDWIDTH());
                                            }
                                            if (left && right) {
                                                gc.fillRect(pipex, pipey + rectOffset, board.getGRIDWIDTH(), width);
                                            }
                                            if (above && left) { //LINKS ONDER
                                                gc.fillRect(pipex, pipey + rectOffset, width, width);
                                                gc.fillRect(pipex + rectOffset, pipey, width, width);
                                            }
                                            if (above && right) { //RECHTS ONDER
                                                gc.fillRect(pipex + (board.getGRIDWIDTH() / 2), pipey + rectOffset, width, width);
                                                gc.fillRect(pipex + rectOffset, pipey, width, width);
                                            }
                                            if (below && left) { //LINKS BOVEN
                                                gc.fillRect(pipex + rectOffset, pipey + (board.getGRIDWIDTH() - rectOffset), width, (board.getGRIDWIDTH() - rectOffset));
                                                gc.fillRect(pipex, pipey + rectOffset, (board.getGRIDWIDTH() - rectOffset), width);
                                            }
                                            if (below && right) { //RECHTS BOVEN
                                                gc.fillRect(pipex + rectOffset, pipey + (board.getGRIDWIDTH() - rectOffset), width, (board.getGRIDWIDTH() - rectOffset));
                                                gc.fillRect(pipex + rectOffset, pipey + rectOffset, (board.getGRIDWIDTH() - rectOffset), width);
                                            }
                                            break;
                                        case -4:
                                            gc.setFill(Color.ORANGE);
                                            gc.fillRect(pipex + rectOffset, pipey + rectOffset, width, width);

                                            if (above && below) {
                                                gc.fillRect(pipex + rectOffset, pipey, width, board.getGRIDWIDTH());
                                            }
                                            if (left && right) {
                                                gc.fillRect(pipex, pipey + rectOffset, board.getGRIDWIDTH(), width);
                                            }
                                            if (above && left) { //LINKS ONDER
                                                gc.fillRect(pipex, pipey + rectOffset, width, width);
                                                gc.fillRect(pipex + rectOffset, pipey, width, width);
                                            }
                                            if (above && right) { //RECHTS ONDER
                                                gc.fillRect(pipex + (board.getGRIDWIDTH() / 2), pipey + rectOffset, width, width);
                                                gc.fillRect(pipex + rectOffset, pipey, width, width);
                                            }
                                            if (below && left) { //LINKS BOVEN
                                                gc.fillRect(pipex + rectOffset, pipey + (board.getGRIDWIDTH() - rectOffset), width, (board.getGRIDWIDTH() - rectOffset));
                                                gc.fillRect(pipex, pipey + rectOffset, (board.getGRIDWIDTH() - rectOffset), width);
                                            }
                                            if (below && right) { //RECHTS BOVEN
                                                gc.fillRect(pipex + rectOffset, pipey + (board.getGRIDWIDTH() - rectOffset), width, (board.getGRIDWIDTH() - rectOffset));
                                                gc.fillRect(pipex + rectOffset, pipey + rectOffset, (board.getGRIDWIDTH() - rectOffset), width);
                                            }
                                            break;
                                        case -5:
                                            gc.setFill(Color.YELLOW);
                                            gc.fillRect(pipex + rectOffset, pipey + rectOffset, width, width);

                                            if (above && below) {
                                                gc.fillRect(pipex + rectOffset, pipey, width, board.getGRIDWIDTH());
                                            }
                                            if (left && right) {
                                                gc.fillRect(pipex, pipey + rectOffset, board.getGRIDWIDTH(), width);
                                            }
                                            if (above && left) { //LINKS ONDER
                                                gc.fillRect(pipex, pipey + rectOffset, width, width);
                                                gc.fillRect(pipex + rectOffset, pipey, width, width);
                                            }
                                            if (above && right) { //RECHTS ONDER
                                                gc.fillRect(pipex + (board.getGRIDWIDTH() / 2), pipey + rectOffset, width, width);
                                                gc.fillRect(pipex + rectOffset, pipey, width, width);
                                            }
                                            if (below && left) { //LINKS BOVEN
                                                gc.fillRect(pipex + rectOffset, pipey + (board.getGRIDWIDTH() - rectOffset), width, (board.getGRIDWIDTH() - rectOffset));
                                                gc.fillRect(pipex, pipey + rectOffset, (board.getGRIDWIDTH() - rectOffset), width);
                                            }
                                            if (below && right) { //RECHTS BOVEN
                                                gc.fillRect(pipex + rectOffset, pipey + (board.getGRIDWIDTH() - rectOffset), width, (board.getGRIDWIDTH() - rectOffset));
                                                gc.fillRect(pipex + rectOffset, pipey + rectOffset, (board.getGRIDWIDTH() - rectOffset), width);
                                            }
                                            break;
                                        case -6:
                                            gc.setFill(Color.TURQUOISE);
                                            gc.fillRect(pipex + rectOffset, pipey + rectOffset, width, width);

                                            if (above && below) {
                                                gc.fillRect(pipex + rectOffset, pipey, width, board.getGRIDWIDTH());
                                            }
                                            if (left && right) {
                                                gc.fillRect(pipex, pipey + rectOffset, board.getGRIDWIDTH(), width);
                                            }
                                            if (above && left) { //LINKS ONDER
                                                gc.fillRect(pipex, pipey + rectOffset, width, width);
                                                gc.fillRect(pipex + rectOffset, pipey, width, width);
                                            }
                                            if (above && right) { //RECHTS ONDER
                                                gc.fillRect(pipex + (board.getGRIDWIDTH() / 2), pipey + rectOffset, width, width);
                                                gc.fillRect(pipex + rectOffset, pipey, width, width);
                                            }
                                            if (below && left) { //LINKS BOVEN
                                                gc.fillRect(pipex + rectOffset, pipey + (board.getGRIDWIDTH() - rectOffset), width, (board.getGRIDWIDTH() - rectOffset));
                                                gc.fillRect(pipex, pipey + rectOffset, (board.getGRIDWIDTH() - rectOffset), width);
                                            }
                                            if (below && right) { //RECHTS BOVEN
                                                gc.fillRect(pipex + rectOffset, pipey + (board.getGRIDWIDTH() - rectOffset), width, (board.getGRIDWIDTH() - rectOffset));
                                                gc.fillRect(pipex + rectOffset, pipey + rectOffset, (board.getGRIDWIDTH() - rectOffset), width);
                                            }
                                            break;
                                        case -7:
                                            gc.setFill(Color.PURPLE);
                                            gc.fillRect(pipex + rectOffset, pipey + rectOffset, width, width);

                                            if (above && below) {
                                                gc.fillRect(pipex + rectOffset, pipey, width, board.getGRIDWIDTH());
                                            }
                                            if (left && right) {
                                                gc.fillRect(pipex, pipey + rectOffset, board.getGRIDWIDTH(), width);
                                            }
                                            if (above && left) { //LINKS ONDER
                                                gc.fillRect(pipex, pipey + rectOffset, width, width);
                                                gc.fillRect(pipex + rectOffset, pipey, width, width);
                                            }
                                            if (above && right) { //RECHTS ONDER
                                                gc.fillRect(pipex + (board.getGRIDWIDTH() / 2), pipey + rectOffset, width, width);
                                                gc.fillRect(pipex + rectOffset, pipey, width, width);
                                            }
                                            if (below && left) { //LINKS BOVEN
                                                gc.fillRect(pipex + rectOffset, pipey + (board.getGRIDWIDTH() - rectOffset), width, (board.getGRIDWIDTH() - rectOffset));
                                                gc.fillRect(pipex, pipey + rectOffset, (board.getGRIDWIDTH() - rectOffset), width);
                                            }
                                            if (below && right) { //RECHTS BOVEN
                                                gc.fillRect(pipex + rectOffset, pipey + (board.getGRIDWIDTH() - rectOffset), width, (board.getGRIDWIDTH() - rectOffset));
                                                gc.fillRect(pipex + rectOffset, pipey + rectOffset, (board.getGRIDWIDTH() - rectOffset), width);
                                            }
                                            break;
                                        case -8:
                                            gc.setFill(Color.HOTPINK);
                                            gc.fillRect(pipex + rectOffset, pipey + rectOffset, width, width);

                                            if (above && below) {
                                                gc.fillRect(pipex + rectOffset, pipey, width, board.getGRIDWIDTH());
                                            }
                                            if (left && right) {
                                                gc.fillRect(pipex, pipey + rectOffset, board.getGRIDWIDTH(), width);
                                            }
                                            if (above && left) { //LINKS ONDER
                                                gc.fillRect(pipex, pipey + rectOffset, width, width);
                                                gc.fillRect(pipex + rectOffset, pipey, width, width);
                                            }
                                            if (above && right) { //RECHTS ONDER
                                                gc.fillRect(pipex + (board.getGRIDWIDTH() / 2), pipey + rectOffset, width, width);
                                                gc.fillRect(pipex + rectOffset, pipey, width, width);
                                            }
                                            if (below && left) { //LINKS BOVEN
                                                gc.fillRect(pipex + rectOffset, pipey + (board.getGRIDWIDTH() - rectOffset), width, (board.getGRIDWIDTH() - rectOffset));
                                                gc.fillRect(pipex, pipey + rectOffset, (board.getGRIDWIDTH() - rectOffset), width);
                                            }
                                            if (below && right) { //RECHTS BOVEN
                                                gc.fillRect(pipex + rectOffset, pipey + (board.getGRIDWIDTH() - rectOffset), width, (board.getGRIDWIDTH() - rectOffset));
                                                gc.fillRect(pipex + rectOffset, pipey + rectOffset, (board.getGRIDWIDTH() - rectOffset), width);
                                            }
                                            break;
                                        case -9:
                                            gc.setFill(Color.BROWN);
                                            gc.fillRect(pipex + rectOffset, pipey + rectOffset, width, width);
                                            if (above && below) {
                                                gc.fillRect(pipex + rectOffset, pipey, width, board.getGRIDWIDTH());
                                            }
                                            if (left && right) {
                                                gc.fillRect(pipex, pipey + rectOffset, board.getGRIDWIDTH(), width);
                                            }
                                            if (above && left) { //LINKS ONDER
                                                gc.fillRect(pipex, pipey + rectOffset, width, width);
                                                gc.fillRect(pipex + rectOffset, pipey, width, width);
                                            }
                                            if (above && right) { //RECHTS ONDER
                                                gc.fillRect(pipex + (board.getGRIDWIDTH() / 2), pipey + rectOffset, width, width);
                                                gc.fillRect(pipex + rectOffset, pipey, width, width);
                                            }
                                            if (below && left) { //LINKS BOVEN
                                                gc.fillRect(pipex + rectOffset, pipey + (board.getGRIDWIDTH() - rectOffset), width, (board.getGRIDWIDTH() - rectOffset));
                                                gc.fillRect(pipex, pipey + rectOffset, (board.getGRIDWIDTH() - rectOffset), width);
                                            }
                                            if (below && right) { //RECHTS BOVEN
                                                gc.fillRect(pipex + rectOffset, pipey + (board.getGRIDWIDTH() - rectOffset), width, (board.getGRIDWIDTH() - rectOffset));
                                                gc.fillRect(pipex + rectOffset, pipey + rectOffset, (board.getGRIDWIDTH() - rectOffset), width);
                                            }
                                            break;
                                    }
                                }
                            }
                        }
                    });

                    //NODIG?
                    view.setOnDragDetected(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            view.getCanvas().startFullDrag();
                            System.out.println("drag detected");
                        }
                    });
                }
            }
        });

        /* muis loslaten om pipe te kunnen tekenen
         * we gaan ervan uit dat je start en eind een zelfde kleur moet zijn
         * maar op andere coordinaten
         * Controle uitvoeren op ingevuld bord
         */
        view.getCanvas().setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //uitlezen van bord

                System.out.println("Ingevuld board");
                for (int[] ints : board.getBoard()) {
                    System.out.println(Arrays.toString(ints));
                }
                System.out.println("Oplossing");
                for (int[] ints : solution.getSolution()) {
                    System.out.println(Arrays.toString(ints));
                }

                if (Arrays.deepEquals(board.getBoard(), solution.getSolution())) {
                    view.getLblSolution().setStyle("-fx-font-size: 20");
                    view.getLblSolution().setText("Oplossing gevonden!");
                    model.setWon(true);
                }
                /* muis loslaten op een bol want = selected */
                if (selected) {
                    selected = false;
                    endx = -1;
                    endy = -1;

                    speler.addMove(1);
                    view.getLblMoves().setText("Moves: " + speler.getMoves());

                    /* doorzoeken van het spelbord, rij per rij, kolom per kolom
                     * startpositie is x = -1, y = -1 (linksboven) */
                    /*for (int i = 0; i < board.getBoard().length; i++) { //rij
                        for (int j = 0; j < board.getBoard()[i].length; j++) { //kolom
                            /* je hebt je eindpositie bereikt wanneer
                            je niet op je startpositie bent en
                            je op een zelfde kleur bent (index array) */
  /*                          if (!(j == startx && i == starty) && board.getBoard()[i][j] == board.getBoard()[startx][starty]) {
                                endx = (int) (event.getX() / board.getGRIDWIDTH());

                                endy = (int) (event.getY() / board.getGRIDWIDTH());

                                //EINDLOCATIE NOG NAZIEN
                            }
                        }
                    }
/*

                    /*
                    if ((currentx == endx && currenty == endy + 1) ||
                            (currentx == endx && currenty == endy - 1) ||
                            (currentx == endx + 1 && currenty == endy) ||
                            (currentx == endx - 1 && currenty == endy)) {
                        //check win
                    } else {
                        /*loslaten is reset van pipe*/
                    //NAZIEN
                    /*
                        for (int i = 0; i < board.getBoard().length; i++) {
                            for (int j = 0; j < board.getBoard()[i].length; j++) {
                                if (board.getBoard()[i][j] == -board.getBoard()[starty][startx]){
                                    board.setRij(0);
                                    board.setKolom(0);
                                }
                            }
                        }
                    }
                    */

                }
            }
        });
    }

    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                final Alert stopWindow = new Alert(Alert.AlertType.ERROR);
                try {
                    DialogPane dialogPane = stopWindow.getDialogPane();
                    dialogPane.setGraphic(new Label());
                    dialogPane.getStylesheets().add("/stylesheets/flowfree.css");
                    dialogPane.getStyleClass().add("alert-window");
                } catch (Exception e) {
                }
                try {
                    Stage stage = (Stage) stopWindow.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image("/images/fflogo.png"));
                } catch (Exception ignored) {
                }
                stopWindow.setTitle("Warning!");
                stopWindow.setHeaderText("Are you sure you want to close the game?");
                stopWindow.setContentText("The game will close without saving!");

                stopWindow.getButtonTypes().clear();
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);

                stopWindow.getButtonTypes().addAll(yes, no);
                stopWindow.showAndWait();
                if (stopWindow.getResult() == ButtonType.NO || stopWindow.getResult().equals(no)) {
                    event.consume();
                } else {
                    view.getScene().getWindow().hide();
                }

            }
        });
    }


    private void createBoard() {
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

                switch (board.getBoard()[j][i]) {
                    case 1:
                        gc.setFill(Color.RED);
                        gc.fillOval(x + circOffset, y + circOffset, diameter, diameter);
                        break;
                    case 2:
                        gc.setFill(Color.BLUE);
                        gc.fillOval(x + circOffset, y + circOffset, diameter, diameter);
                        break;
                    case 3:
                        gc.setFill(Color.GREEN);
                        gc.fillOval(x + circOffset, y + circOffset, diameter, diameter);
                        break;
                    case 4:
                        gc.setFill(Color.ORANGE);
                        gc.fillOval(x + circOffset, y + circOffset, diameter, diameter);
                        break;
                    case 5:
                        gc.setFill(Color.YELLOW);
                        gc.fillOval(x + circOffset, y + circOffset, diameter, diameter);
                        break;
                    case 6:
                        gc.setFill(Color.TURQUOISE);
                        gc.fillOval(x + circOffset, y + circOffset, diameter, diameter);
                        break;
                    case 7:
                        gc.setFill(Color.PURPLE);
                        gc.fillOval(x + circOffset, y + circOffset, diameter, diameter);
                        break;
                    case 8:
                        gc.setFill(Color.HOTPINK);
                        gc.fillOval(x + circOffset, y + circOffset, diameter, diameter);
                        break;
                    case 9:
                        gc.setFill(Color.BROWN);
                        gc.fillOval(x + circOffset, y + circOffset, diameter, diameter);
                        break;
                }
            }
        }
    }

    private void updateView() {

    }
}