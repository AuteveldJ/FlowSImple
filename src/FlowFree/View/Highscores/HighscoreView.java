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

    private Label lblTest;
    private Button btnBack;

    public HighscoreView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        lblTest = new Label("Hier komen de highscores uit een textfile");
        btnBack = new Button("Back");
    }

    private void layoutNodes() {
        lblTest.setPrefSize(300, 400);
        lblTest.setAlignment(Pos.CENTER);

        this.setPadding(new Insets(10));
        this.setCenter(lblTest);
        this.setBottom(btnBack);
        this.setStyle("-fx-background-color: SLATEGREY;");

        BorderPane.setAlignment(btnBack, Pos.BOTTOM_RIGHT);
    }

    public Button getBtnBack() {
        return btnBack;
    }
}
