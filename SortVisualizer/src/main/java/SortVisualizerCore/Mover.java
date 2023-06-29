package SortVisualizerCore;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Mover {

    public Animation moveRope(Line line, double fromY, double toY) {
        Timeline lengthening = new Timeline();

        KeyValue toYValue = new KeyValue(line.endYProperty(), toY);
        KeyFrame lengtheningFrame = new KeyFrame(Duration.millis(400), toYValue);

        lengthening.getKeyFrames().addAll(lengtheningFrame);

        return lengthening;
    }

    public Animation moveInX(double fromX, double toX, Node... nodes) {
        ParallelTransition parallel = new ParallelTransition();
        for (Node node : nodes) {
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(node);
            translate.setFromX(fromX);
            translate.setToX(toX);
            parallel.getChildren().add(translate);
        }
        return parallel;
    }

    public Animation swapInX(double fromX, double toX, Node node1, Node node2) {
        ParallelTransition swapInX = new ParallelTransition();

        TranslateTransition t1 = new TranslateTransition();
        t1.setNode(node1);
        t1.setFromX(fromX);
        t1.setToX(toX);

        TranslateTransition t2 = new TranslateTransition();
        t2.setNode(node2);
        t2.setFromX(toX);
        t2.setToX(fromX);

        swapInX.getChildren().addAll(t1, t2);

        return swapInX;
    }

    public Animation moveInY(double fromY, double toY, Node... nodes) {
        ParallelTransition parallel = new ParallelTransition();
        for (Node node : nodes) {
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(node);
            translate.setFromY(fromY);
            translate.setToY(toY);
            parallel.getChildren().add(translate);
        }
        return parallel;
    }

    public Animation moveInX2(double fromX, double toX, Node... nodes) {
        ParallelTransition parallel = new ParallelTransition();
        Interpolator interpolator = Interpolator.LINEAR;
        for (Node node : nodes) {
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(node);
            translate.setToX(toX);
            translate.setInterpolator(interpolator);
            parallel.getChildren().add(translate);
        }
        return parallel;
    }

    public Animation swapInX2(double fromX, double toX, Node... nodes) {
        ParallelTransition swapInX = new ParallelTransition();
        Interpolator interpolator = Interpolator.LINEAR;

        for (int i = 0; i < nodes.length; i++) {
            TranslateTransition t = new TranslateTransition();
            t.setNode(nodes[i]);
            t.setInterpolator(interpolator);
            if (i < nodes.length / 2) {
                t.setToX(toX);
            } else {
                t.setToX(fromX);
            }
            swapInX.getChildren().add(t);
        }

        return swapInX;
    }

    public Animation moveInY2(double fromY, double toY, Node... nodes) {
        ParallelTransition parallel = new ParallelTransition();
        Interpolator interpolator = Interpolator.LINEAR;
        for (Node node : nodes) {
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(node);
            translate.setInterpolator(interpolator);
            translate.setToY(toY);
            parallel.getChildren().add(translate);
        }
        return parallel;
    }
}
