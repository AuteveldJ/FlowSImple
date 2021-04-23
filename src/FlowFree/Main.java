package FlowFree;

import FlowFree.Model.FlowFreeModel;
import FlowFree.View.Loadscherm.LoadschermPresenter;
import FlowFree.View.Loadscherm.LoadschermView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Jonathan Auteveld
 * @version 1.0 12/02/2021 22:39
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {

        FlowFreeModel model = new FlowFreeModel();
        LoadschermView view = new LoadschermView();
        LoadschermPresenter presenter = new LoadschermPresenter(model, view);
        Scene scene = new Scene(view);

        scene.getStylesheets().add("/stylesheets/flowfree.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("FlowFree");
        primaryStage.getIcons().add(new Image("/images/fflogo.png"));
        presenter.windowsHandler();
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
