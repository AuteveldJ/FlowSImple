package FlowFree.View.Loadscherm;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.util.Duration;

/**
 * @author Jonathan Auteveld
 * @version 1.0 11/04/2021 22:52
 */
public class LoadschermTransition extends Transition {

    private final LoadschermView view;

    LoadschermTransition(LoadschermView view, int maxDuration){
        this.view = view;
        this.setCycleDuration(Duration.seconds(maxDuration));
        this.setCycleCount(1);
        this.setInterpolator(Interpolator.LINEAR);
    }

    @Override
    protected void interpolate(double frac) {
        this.view.getTimeDisplay().setText(String.format("Loading: %.1f", frac * 100));
        this.view.getTimeProgress().setProgress(frac);
    }
}
