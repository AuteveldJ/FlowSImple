package FlowFree.View.Help;

import FlowFree.Model.FlowFreeModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Jonathan Auteveld
 * @version 1.0 21/02/2021 21:08
 */
public class HelpPresenter {
    private FlowFreeModel model;
    private HelpView view;

    public HelpPresenter(FlowFreeModel model, HelpView view) {
        this.model = model;
        this.view = view;
        view.getLblHelp().setText(readHelpFile());
    }

    private String readHelpFile() {
        String helpFile = "";
        InputStream inputStream = getClass().getResourceAsStream("/files/help.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                helpFile += line + "\n";
            }
        } catch (Exception ignored) {
        }
        return (helpFile.compareTo("") == 0) ? "No info available" : helpFile;
    }
}
