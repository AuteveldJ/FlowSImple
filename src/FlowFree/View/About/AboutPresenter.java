package FlowFree.View.About;

import FlowFree.Model.FlowFreeModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 * @author Jonathan Auteveld
 * @version 1.0 21/02/2021 21:08
 */
public class AboutPresenter {
    private FlowFreeModel model;
    private AboutView view;

    public AboutPresenter(FlowFreeModel model, AboutView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               view.getScene().getWindow().hide();
            }
        });
    }

    private void updateView() {

    }
}