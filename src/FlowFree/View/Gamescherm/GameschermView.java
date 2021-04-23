package FlowFree.View.Gamescherm;

import FlowFree.Model.Board;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Jonathan Auteveld
 * @version 1.0 12/02/2021 22:47
 */
public class GameschermView extends BorderPane {
    private Button btnBack;
    private Button btnRestart;
    private Label lblSolution;
    private Label lblMoves;
    private VBox gameBox;
    private VBox gameDataBox;
    private HBox buttonBox;
    private Canvas canvas;

    private Menu menu;
    private MenuBar menubar;
    private MenuItem vijf;
    private MenuItem zes;
    private MenuItem zeven;
    private MenuItem acht;
    private MenuItem negen;

    private Board board;

    public GameschermView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        btnBack = new Button("Back");
        btnRestart = new Button("Restart");
        lblSolution = new Label("");
        lblMoves = new Label("Moves: ");
        gameBox = new VBox();
        gameDataBox = new VBox();
        buttonBox = new HBox();
        canvas = new Canvas(500, 500);

        menubar = new MenuBar();
        menu = new Menu("Levelkeuze");
        vijf = new MenuItem("5 X 5");
        zes = new MenuItem("6 X 6");
        zeven = new MenuItem("7 X 7");
        acht = new MenuItem("8 X 8");
        negen = new MenuItem("9 X 9");

        board = new Board();
    }

    private void layoutNodes() {
        buttonBox.setSpacing(100);
        buttonBox.setPadding(new Insets(0,0,10,0));
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(btnBack, btnRestart);

        gameBox.setSpacing(10);
        gameBox.getChildren().addAll(menubar, gameDataBox);

        gameDataBox.setPadding(new Insets(10,0,0,0));
        gameDataBox.setAlignment(Pos.TOP_CENTER);
        gameDataBox.setSpacing(10);
        gameDataBox.getChildren().addAll(lblMoves, lblSolution);

        menu.getItems().add(vijf);
        menu.getItems().add(zes);
        menu.getItems().add(zeven);
        menu.getItems().add(acht);
        menu.getItems().add(negen);
        menubar.getMenus().add(menu);

        this.setTop(gameBox);
        this.setBottom(buttonBox);
        this.setCenter(canvas);
        this.setStyle("-fx-background-color: SLATEGRAY;");

        BorderPane.setMargin(canvas, new Insets(10, 10, 10, 10));

    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Button getBtnRestart() {
        return btnRestart;
    }

    public MenuItem getVijf() {
        return vijf;
    }

    public MenuItem getZes() {
        return zes;
    }

    public MenuItem getZeven() {
        return zeven;
    }

    public MenuItem getAcht() {
        return acht;
    }

    public MenuItem getNegen() {
        return negen;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Label getLblMoves() {
        return lblMoves;
    }

    public Label getLblSolution(){return lblSolution;}
}
