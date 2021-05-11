package FlowFree.View.Startschem;

import FlowFree.Model.FlowFreeModel;
import FlowFree.View.About.AboutPresenter;
import FlowFree.View.About.AboutView;
import FlowFree.View.Gamescherm.GameschermPresenter;
import FlowFree.View.Gamescherm.GameschermView;
import FlowFree.View.Help.HelpPresenter;
import FlowFree.View.Help.HelpView;
import FlowFree.View.Highscores.HighScorePresenter;
import FlowFree.View.Highscores.HighscoreView;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Jonathan Auteveld
 * @version 1.0 12/02/2021 22:49
 */
public class StartschermPresenter {
    private FlowFreeModel model;
    private StartschermView view;

    public StartschermPresenter(FlowFreeModel model, StartschermView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getBtnStartGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (view.getTxtPlayerName().getText().isEmpty()) {
                    final Alert stopAlert = new Alert(Alert.AlertType.ERROR);
                    try {
                        DialogPane dialog = stopAlert.getDialogPane();
                        dialog.setGraphic(new Label());
                        dialog.getStylesheets().add("/stylesheets/flowfree.css");
                        dialog.getStyleClass().add("alert-window");
                    } catch (Exception e) {
                    }
                    stopAlert.setTitle("Error");
                    stopAlert.setHeaderText("Unable to start game");
                    stopAlert.setContentText("Fill in a player name");
                    stopAlert.showAndWait();
                } else {

                    model.createPlayerlist(view.getTxtPlayerName().getText());
                    GameschermView gameschermView = new GameschermView();
                    GameschermPresenter gameschermPresenter = new GameschermPresenter(model, gameschermView);

                    view.getScene().setRoot(gameschermView);
                    gameschermView.getScene().getWindow().sizeToScene();
                    gameschermPresenter.windowsHandler();
                }
            }
        });

        view.getBtnHighscore().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HighscoreView highscoreView = new HighscoreView();
                HighScorePresenter highScorePresenter = new HighScorePresenter(model, highscoreView);

                view.getScene().setRoot(highscoreView);
                highscoreView.getScene().getWindow().sizeToScene();
                highScorePresenter.windowsHandler();
            }
        });

        view.getBtnAbout().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AboutView aboutView = new AboutView();
                AboutPresenter aboutPresenter = new AboutPresenter(model, aboutView);
                Scene scene = new Scene(aboutView);
                scene.getStylesheets().add("/stylesheets/flowfree.css");
                Stage aboutStage = new Stage();

                aboutStage.initOwner(view.getScene().getWindow());
                aboutStage.initModality(Modality.APPLICATION_MODAL);
                aboutStage.setScene(scene);
                aboutStage.setTitle("About");
                aboutStage.getIcons().add(new Image("/images/fflogo.png"));
                aboutStage.showAndWait();
            }
        });

        view.getBtnHelp().setOnAction(new EventHandler<ActionEvent>() {
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
    }

    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                handleCloseEvent(event);
            }
        });
    }

    private void handleCloseEvent(Event event) {
        view.getScene().getWindow().hide();
    }

    private void updateView() {

    }
}
