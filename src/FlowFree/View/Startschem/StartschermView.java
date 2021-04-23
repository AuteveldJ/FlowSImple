package FlowFree.View.Startschem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


/**
 * @author Jonathan Auteveld
 * @version 1.0 12/02/2021 22:47
 */
public class StartschermView extends BorderPane /* layout type*/ {
    private Button btnStartGame;
    private Button btnHelp;
    private Button btnAbout;
    private Button btnHighscore;
    private Label lblPlayerName;
    private TextField txtPlayerName;
    private VBox buttonBox;
    private VBox playerBox;

    public StartschermView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        btnStartGame = new Button("Start Game");
        btnHelp = new Button("Help");
        btnAbout = new Button("About");
        btnHighscore = new Button("Highscores");
        lblPlayerName = new Label("Player Name: ");
        txtPlayerName = new TextField();
        buttonBox = new VBox();
        playerBox = new VBox();
    }

    private void layoutNodes() {
        buttonBox.setSpacing(50);
        buttonBox.getChildren().addAll(btnStartGame, btnHelp, btnAbout, btnHighscore);
        buttonBox.setMinSize(300, 400);
        buttonBox.setAlignment(Pos.CENTER);

        playerBox.setSpacing(20);
        playerBox.getChildren().addAll(lblPlayerName, txtPlayerName);
        playerBox.setAlignment(Pos.CENTER);

        this.setPadding(new Insets(20));
        this.setTop(playerBox);
        this.setCenter(buttonBox);
        this.setStyle("-fx-background-color: SLATEGREY;");
    }

    public Button getBtnStartGame() {
        return btnStartGame;
    }

    public Button getBtnHelp() {
        return btnHelp;
    }

    public Button getBtnAbout() {
        return btnAbout;
    }

    public Button getBtnHighscore() {
        return btnHighscore;
    }

    public TextField getTxtPlayerName() {
        return txtPlayerName;
    }

    public void setTxtPlayerName(TextField txtPlayerName) {
        this.txtPlayerName = txtPlayerName;
    }
}
