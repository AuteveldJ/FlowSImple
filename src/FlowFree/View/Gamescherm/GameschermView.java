package FlowFree.View.Gamescherm;

import FlowFree.Model.FlowFreeModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
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
    private Label lblInfoText;
    private VBox gameBox;
    private VBox gameDataBox;
    private HBox buttonBox;
    private Canvas canvas;

    private Menu levelMenu;
    private Menu spelMenu;
    private Menu helpMenu;
    private MenuBar menubar;
    private MenuItem vijf;
    private MenuItem zes;
    private MenuItem zeven;
    private MenuItem acht;
    private MenuItem negen;
    private MenuItem save;
    private MenuItem load;
    private MenuItem help;

    public GameschermView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        btnBack = new Button("Back");
        btnRestart = new Button("Restart");
        lblSolution = new Label("");
        lblMoves = new Label("Moves: ");
        lblInfoText = new Label("Welcome! Start a new game by selecting a level from the menu");
        gameBox = new VBox();
        gameDataBox = new VBox();
        buttonBox = new HBox();
        canvas = new Canvas(500, 500);

        menubar = new MenuBar();
        levelMenu = new Menu("Level selection");
        vijf = new MenuItem("5 X 5");
        zes = new MenuItem("6 X 6");
        zeven = new MenuItem("7 X 7");
        acht = new MenuItem("8 X 8");
        negen = new MenuItem("9 X 9");

        spelMenu = new Menu("Save/Load");
        save = new MenuItem("Save");
        load = new MenuItem("Load");

        helpMenu = new Menu("Help");
        help = new MenuItem("Help");
    }

    private void layoutNodes() {
        buttonBox.setSpacing(100);
        buttonBox.setPadding(new Insets(0, 0, 10, 0));
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(btnBack, btnRestart);

        gameBox.setSpacing(10);
        gameBox.getChildren().addAll(menubar, gameDataBox);

        gameDataBox.setPadding(new Insets(10, 0, 0, 0));
        gameDataBox.setAlignment(Pos.TOP_CENTER);
        gameDataBox.setSpacing(10);
        gameDataBox.getChildren().addAll(lblInfoText, lblMoves, lblSolution);

        levelMenu.getItems().addAll(vijf, zes, zeven, acht, negen);
        spelMenu.getItems().addAll(save, load);
        helpMenu.getItems().add(help);
        menubar.getMenus().addAll(levelMenu, spelMenu, helpMenu);

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

    public Label getLblSolution() {
        return lblSolution;
    }

    public MenuItem getHelp() {
        return help;
    }
}
