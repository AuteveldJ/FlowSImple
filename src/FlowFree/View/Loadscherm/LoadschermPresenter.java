package FlowFree.View.Loadscherm;

import FlowFree.Model.FlowFreeModel;
import FlowFree.View.Startschem.StartschermPresenter;
import FlowFree.View.Startschem.StartschermView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Jonathan Auteveld
 * @version 1.0 11/04/2021 22:57
 */
public class LoadschermPresenter {
    private FlowFreeModel model;
    private LoadschermView view;

    public LoadschermPresenter(FlowFreeModel model, LoadschermView view) {
        this.model = model;
        this.view = view;
        updateView();
        EventHandlers();
    }

    private void updateView() {
    }

    private void EventHandlers() {
        view.getTransition().setOnFinished(new EventHandler<ActionEvent>() {
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
                } catch (Exception ignored) { }

                stopWindow.setTitle("Error!");
                stopWindow.setHeaderText("You can't close the game just yet!");
                stopWindow.setContentText("Try again after the program has been loaded!");
                stopWindow.showAndWait();
                event.consume();
            }
        });
    }
}
