package FlowFree.View.Gamescherm;

import FlowFree.Model.FlowFreeModel;
import FlowFree.Model.Board;
import FlowFree.Model.FreeFlowException;
import FlowFree.Model.Solution;
import FlowFree.View.Help.HelpPresenter;
import FlowFree.View.Help.HelpView;
import FlowFree.View.Startschem.StartschermPresenter;
import FlowFree.View.Startschem.StartschermView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
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

    private boolean won = false;

    private boolean selected = false;
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
                board.setRij(model.getGamePlayer().getLevel());
                board.setKolom(model.getGamePlayer().getLevel());
                board.readFile();

                solution.setRij(model.getGamePlayer().getLevel());
                solution.setKolom(model.getGamePlayer().getLevel());
                solution.readsolution();

                model.getGamePlayer().setMoves(0);
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

                model.getGamePlayer().setMoves(0);
                model.getGamePlayer().setLevel(5);
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

                model.getGamePlayer().setMoves(0);
                model.getGamePlayer().setLevel(6);
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

                model.getGamePlayer().setMoves(0);
                model.getGamePlayer().setLevel(7);
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

                model.getGamePlayer().setMoves(0);
                model.getGamePlayer().setLevel(8);
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

                model.getGamePlayer().setMoves(0);
                model.getGamePlayer().setLevel(9);
                view.getLblMoves().setText("Moves: ");
                view.getLblSolution().setText("");
                createBoard();
            }
        });
        view.getHelp().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HelpView helpView = new HelpView();
                HelpPresenter helpPresenter = new HelpPresenter(model, helpView);
                Scene scene = new Scene(helpView);
                scene.getStylesheets().add("/stylesheets/flowfree.css");
                Stage helpStage = new Stage();

                helpStage.initOwner(view.getScene().getWindow());
                helpStage.initModality(Modality.APPLICATION_MODAL);
                helpStage.setScene(scene);
                helpStage.setTitle("Help");
                helpStage.getIcons().add(new Image("/images/fflogo.png"));
                helpStage.showAndWait();
            }
        });

        view.getCanvas().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                /* selectie maken van je positie waarop je nu klikt
                 * wanneer je klikt op het spelbord ga je deze positie waarop je klikt vastleggen */

                if (board.getBoard() != null) {
                if (!selected) {

                    /* geheel getal voor kolom en rij index verkrijgen */
                    startx = (int) (event.getX() / 50);
                    starty = (int) (event.getY() / 50);
                    currentx = startx;
                    currenty = starty;

                    if (board.getBoard()[starty][startx] > 0) {
                        selected = true;
                    }

                    /* selectie maken van een bol */
                    // selected = model.dotSelection(startx, starty);
                }
                }

                if (selected) {
                    /* alleen als je op een bol staat drag starten */
                    event.setDragDetect(true);
                    view.getCanvas().setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            System.out.println("mouse dragged");
                            event.setDragDetect(false);

                            dragx = (int) (event.getX() / 50);
                            dragy = (int) (event.getY() / 50);
/*
                            model.drag(currentx, currenty, dragx, dragy, startx, starty);
                            currentx = model.getCurrentx();
                            currenty = model.getCurrenty();
*/

                            if (dragx >= 0 && dragy >= 0 &&
                                    dragy < board.getBoard().length && dragx < board.getBoard()[0].length && board.getBoard()[dragy][dragx] == 0) {
                                if ((dragx == currentx + 1 && dragy == currenty) || //rechts
                                        (dragx == currentx - 1 && dragy == currenty) || //links
                                        (dragx == currentx && dragy == currenty + 1) || //boven
                                        (dragx == currentx && dragy == currenty - 1)) { //onder
                                    board.getBoard()[dragy][dragx] = -board.getBoard()[starty][startx]; //negatieve waarde zal een pipe worden

                                    currentx = dragx;
                                    currenty = dragy;
                                }
                            }


                            GraphicsContext gc = view.getCanvas().getGraphicsContext2D();
                            for (int i = 0; i < board.getBoard().length; i++) { //rij
                                for (int j = 0; j < board.getBoard()[i].length; j++) { //kolom
                                    pipex = i * 50;
                                    pipey = j * 50;

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
                                    gc.fillRect(pipex + model.getRectOffset(), pipey + model.getRectOffset(), model.getPipeWidth(), model.getPipeWidth());

                                    if (above && below) {
                                        gc.fillRect(pipex + model.getRectOffset(), pipey, model.getPipeWidth(), 50);
                                    }
                                    if (left && right) {
                                        gc.fillRect(pipex, pipey + model.getRectOffset(), 50, model.getPipeWidth());
                                    }
                                    if (above && left) { //LINKS ONDER
                                        gc.fillRect(pipex, pipey + model.getRectOffset(), model.getPipeWidth(), model.getPipeWidth());
                                        gc.fillRect(pipex + model.getRectOffset(), pipey, model.getPipeWidth(), model.getPipeWidth());
                                    }
                                    if (above && right) { //RECHTS ONDER
                                        gc.fillRect(pipex + (50 / 2), pipey + model.getRectOffset(), model.getPipeWidth(), model.getPipeWidth());
                                        gc.fillRect(pipex + model.getRectOffset(), pipey, model.getPipeWidth(), model.getPipeWidth());
                                    }
                                    if (below && left) { //LINKS BOVEN
                                        gc.fillRect(pipex + model.getRectOffset(), pipey + (50 - model.getRectOffset()), model.getPipeWidth(), (50 - model.getRectOffset()));
                                        gc.fillRect(pipex, pipey + model.getRectOffset(), (50 - model.getRectOffset()), model.getPipeWidth());
                                    }
                                    if (below && right) { //RECHTS BOVEN
                                        gc.fillRect(pipex + model.getRectOffset(), pipey + (50 - model.getRectOffset()), model.getPipeWidth(), (50 - model.getRectOffset()));
                                        gc.fillRect(pipex + model.getRectOffset(), pipey + model.getRectOffset(), (50 - model.getRectOffset()), model.getPipeWidth());
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

                if (board.getBoard() != null) {
                    if (Arrays.deepEquals(board.getBoard(), solution.getSolution())) {
                        view.getLblSolution().setStyle("-fx-font-size: 20");
                        view.getLblSolution().setText("Oplossing gevonden!");
                        model.setWon(true);

                        try {
                            model.saveGame(board.getRij());
                        } catch (FreeFlowException e) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            try {
                                DialogPane dialogPane = alert.getDialogPane();
                                dialogPane.setGraphic(new Label());
                                dialogPane.getStylesheets().add("/stylesheets/flowfree.css");
                                dialogPane.getStyleClass().add("alert-window");
                            } catch (Exception ignored) {
                            }
                            alert.setTitle("Error");
                            alert.setContentText(e.getMessage());
                            try {
                                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                                stage.getIcons().add(new Image("/images/fflogo.png"));
                            } catch (Exception ignored) {
                            }

                            alert.show();
                        }
                    }
                }

                /* muis loslaten op een bol want = selected */
                if (selected) {
                    selected = false;
                    endx = -1;
                    endy = -1;

                    model.getGamePlayer().addMove(1);
                    view.getLblMoves().setText("Moves: " + model.getGamePlayer().getMoves());

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

    private void updateView() {

    }
}