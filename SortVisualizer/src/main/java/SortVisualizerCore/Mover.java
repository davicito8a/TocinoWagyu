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

    /**
     * Crea una animación para mover la línea verticalmente.
     *
     * @param line   La línea a mover.
     * @param fromY  La posición inicial en el eje Y.
     * @param toY    La posición final en el eje Y.
     * @return La animación de movimiento de la línea.
     */
    public Animation moveRope(Line line, double fromY, double toY) {
        Timeline lengthening = new Timeline();

        KeyValue toYValue = new KeyValue(line.endYProperty(), toY);
        KeyFrame lengtheningFrame = new KeyFrame(Duration.millis(400), toYValue);

        lengthening.getKeyFrames().addAll(lengtheningFrame);

        return lengthening;
    }

    /**
     * Crea una animación para mover nodos horizontalmente en el eje X.
     *
     * @param fromX  La posición inicial en el eje X.
     * @param toX    La posición final en el eje X.
     * @param nodes  Los nodos a mover.
     * @return La animación de movimiento horizontal.
     */
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

    /**
     * Crea una animación para intercambiar dos nodos horizontalmente en el eje X.
     *
     * @param fromX   La posición inicial en el eje X.
     * @param toX     La posición final en el eje X.
     * @param node1   El primer nodo a intercambiar.
     * @param node2   El segundo nodo a intercambiar.
     * @return La animación de intercambio horizontal.
     */
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

    /**
     * Crea una animación para mover nodos verticalmente en el eje Y.
     *
     * @param fromY  La posición inicial en el eje Y.
     * @param toY    La posición final en el eje Y.
     * @param nodes  Los nodos a mover.
     * @return La animación de movimiento vertical.
     */
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

    /**
     * Crea una animación para mover nodos horizontalmente en el eje X con interpolación lineal.
     *
     * @param fromX  La posición inicial en el eje X.
     * @param toX    La posición final en el eje X.
     * @param nodes  Los nodos a mover.
     * @return La animación de movimiento horizontal con interpolación lineal.
     */
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

    /**
     * Crea una animación para intercambiar nodos horizontalmente en el eje X con interpolación lineal.
     *
     * @param fromX  La posición inicial en el eje X.
     * @param toX    La posición final en el eje X.
     * @param nodes  Los nodos a intercambiar.
     * @return La animación de intercambio horizontal con interpolación lineal.
     */
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

    /**
     * Crea una animación para mover nodos verticalmente en el eje Y con interpolación lineal.
     *
     * @param fromY  La posición inicial en el eje Y.
     * @param toY    La posición final en el eje Y.
     * @param nodes  Los nodos a mover.
     * @return La animación de movimiento vertical con interpolación lineal.
     */
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
