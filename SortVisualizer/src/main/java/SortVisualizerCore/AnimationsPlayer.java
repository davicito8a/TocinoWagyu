package SortVisualizerCore;

import java.util.ArrayList;
import javafx.animation.Animation;
import static javafx.animation.Animation.Status.RUNNING;
import javafx.animation.SequentialTransition;

public class AnimationsPlayer {
    
    SequentialTransition sequentialAnimations = new SequentialTransition();
    
    public void setAnimations(ArrayList<Animation> translateAnimations) {
        sequentialAnimations.getChildren().addAll(translateAnimations); 
    }

    public void play() {
        sequentialAnimations.play();
    }

    public void pause() {
        sequentialAnimations.pause();
    }

    public void increaseSpeed() {
        if (sequentialAnimations.getStatus().equals(RUNNING)) {
            sequentialAnimations.setRate(sequentialAnimations.getRate() * 1.25);
        }
    }

    public void decreaseSpeed() {
        if (sequentialAnimations.getStatus().equals(RUNNING)) {
            sequentialAnimations.setRate(sequentialAnimations.getRate() * 0.8);
        }
    }

}
