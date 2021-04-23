package FlowFree.View.Loadscherm;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * @author Jonathan Auteveld
 * @version 1.0 11/04/2021 22:57
 */
public class LoadschermView extends BorderPane {
    private Label timeDisplay;
    private ProgressBar timeProgress;
    private VBox timeBox;
    private ImageView loadImage;
    private LoadschermTransition trans;

    public LoadschermView() {
        initialiseNodes();
        layoutNodes();
        animate();
    }

    private void initialiseNodes() {
        timeDisplay = new Label("Loading: 0.0");
        timeProgress = new ProgressBar();
        timeBox = new VBox();
    }

    private void layoutNodes() {
        timeProgress.setStyle("-fx-accent: BLACK");
        timeDisplay.setStyle("-fx-text-fill: WHITE;");

        timeBox.getChildren().addAll(timeDisplay, timeProgress);
        timeBox.setAlignment(Pos.CENTER);
        timeBox.setSpacing(10);
        loadImage = new ImageView(new Image("/images/ffload.png"));

        this.setPadding(new Insets(10));
        this.setCenter(timeBox);
        this.setTop(loadImage);
        this.setStyle("-fx-background-color: SLATEGREY");

    }

    Label getTimeDisplay() {
        return (timeDisplay);
    }

    ProgressBar getTimeProgress() {
        return (timeProgress);
    }

    LoadschermTransition getTransition() {
        return trans;
    }

    private void animate() {
        trans = new LoadschermTransition(this, 5);
        trans.play();
    }
}
