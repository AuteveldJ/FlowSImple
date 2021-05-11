package FlowFree.View.Highscores;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * @author Jonathan Auteveld
 * @version 1.0 10/04/2021 11:11
 */
public class HighscoreView extends BorderPane {

    private Label lblHighscore;
    private Button btnBack;

    public HighscoreView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        lblHighscore = new Label();
        btnBack = new Button("Back");
    }

    private void layoutNodes() {
        lblHighscore.setPrefSize(300, 400);
        lblHighscore.setAlignment(Pos.CENTER);

        this.setPadding(new Insets(10));
        this.setCenter(lblHighscore);
        this.setBottom(btnBack);
        this.setStyle("-fx-background-color: SLATEGREY;");

        BorderPane.setAlignment(btnBack, Pos.BOTTOM_RIGHT);
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Label getLblHighscore() {
        return lblHighscore;
    }
}
