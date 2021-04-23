package FlowFree.View.Help;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * @author Jonathan Auteveld
 * @version 1.0 21/02/2021 21:09
 */

public class HelpView extends BorderPane /* layout type*/ {
    private Label lblHelp;

    public HelpView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        lblHelp = new Label();
    }

    private void layoutNodes() {
        this.setCenter(lblHelp);
        this.setPadding(new Insets(10));
        this.setStyle("-fx-background-color: SLATEGREY;");
        BorderPane.setMargin(lblHelp, new Insets(50));
    }

    public Label getLblHelp() {
        return lblHelp;
    }
}
