package FlowFree.View.About;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * @author Jonathan Auteveld
 * @version 1.0 21/02/2021 21:09
 */

public class AboutView extends BorderPane /* layout type*/ {
    private Label lblAbout;
    private Button btnBack;
    private ImageView loadImage;

    public AboutView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.lblAbout = new Label("Verise 0.1 © geschreven door Jonathan Auteveld in JavaFx");
        this.btnBack = new Button("Back");
    }

    private void layoutNodes() {
        loadImage = new ImageView(new Image("/images/ffload.png"));
        this.setTop(loadImage);
        this.setCenter(lblAbout);
        this.setBottom(btnBack);
        this.setPadding(new Insets(10));
        this.setStyle("-fx-background-color: SLATEGREY;");

        BorderPane.setAlignment(btnBack, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(lblAbout, new Insets(50));
    }

    Button getBtnBack() {
        return btnBack;
    }
}
