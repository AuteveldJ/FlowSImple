package FlowFree.View.Highscores;

import FlowFree.Model.FlowFreeModel;
import FlowFree.View.Startschem.StartschermPresenter;
import FlowFree.View.Startschem.StartschermView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

/**
 * @author Jonathan Auteveld
 * @version 1.0 10/04/2021 11:11
 */
public class HighScorePresenter {
    private FlowFreeModel model;
    private HighscoreView view;

    public HighScorePresenter(FlowFreeModel model, HighscoreView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
        view.getLblHighscore().setText(readFile());
    }

    private String readFile() {
        String highScore = "";
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream("highscore.bin")))) {
            while (dis.available() > 0) {
                String line = dis.readUTF();
                highScore += line + "\n";
            }
        } catch (Exception ignored) {
        }
        return (highScore.compareTo("") == 0) ? "No highscores available" : highScore;
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

    private void updateView() {

    }
}
