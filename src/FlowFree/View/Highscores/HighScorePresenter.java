package FlowFree.View.Highscores;

import FlowFree.Model.FlowFreeModel;
import FlowFree.View.Startschem.StartschermPresenter;
import FlowFree.View.Startschem.StartschermView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
    }

    private void addEventHandlers() {
        view.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StartschermView startView = new StartschermView();
                StartschermPresenter startPresenter = new StartschermPresenter(model, startView);

                view.getScene().setRoot(startView);
                startView.getScene().getWindow().sizeToScene();
            }
        });
    }

    private void updateView() {

    }
}
