package SortVisualizerCore;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Mover {
    
    /*
    Clase Mover ("movedor"). 
    Dentro de esta clase están los métodos que crean las animaciones de movimiento.
    Los parámetros de los métodos son las coordenadas de inicio y final del movimiento, y los nodos sobre los cuales se aplicará la animación.
    Los métodos permiten mover uno o más nodos en el eje X, Y o intercambiar la posición de dos nodos.
    Las animaciones se crean a partir de las clases KeyValue, KeyFrame y Timeline, y la propiedad translate() de los nodos.
    */
    
    /*
    public Animation moveInX(double fromX, double toX, Node... nodes){
        Timeline moveInX = new Timeline();
        
        for(Node node: nodes){
            KeyValue fromXValue = new KeyValue(node.translateXProperty(), fromX);
            KeyValue toXValue = new KeyValue(node.translateXProperty(), toX);
       
            KeyFrame moveFromX = new KeyFrame(Duration.ZERO, fromXValue);
            KeyFrame moveToX = new KeyFrame(Duration.millis(400), toXValue);
         
            moveInX.getKeyFrames().addAll(moveFromX, moveToX);
        }
        
        return moveInX;
    }
    */
    
    public Animation moveInX(double fromX, double toX, Node... nodes){
        ParallelTransition parallel = new ParallelTransition();
        for(Node node: nodes){
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(node);
            translate.setFromX(fromX);
            translate.setToX(toX);
            parallel.getChildren().add(translate);
        }
        return parallel;
    }
    
    public Animation swapInX(double fromX, double toX, Node node1, Node node2){
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
    
    public Animation moveInY(double fromY, double toY, Node... nodes){
        ParallelTransition parallel = new ParallelTransition();
        for(Node node: nodes){
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(node);
            translate.setFromY(fromY);
            translate.setToY(toY);
            parallel.getChildren().add(translate);
        }
        return parallel;
    }
    
    public Animation moveInX2(double fromX, double toX, Node... nodes){
        ParallelTransition parallel = new ParallelTransition();
        for(Node node: nodes){
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(node);
            translate.setToX(toX);
            parallel.getChildren().add(translate);
        }
        return parallel;
    }
    
    public Animation swapInX2(double fromX, double toX,Node...nodes){
        ParallelTransition swapInX = new ParallelTransition();

        for(int i = 0; i < nodes.length; i++){
            TranslateTransition t = new TranslateTransition();
            t.setNode(nodes[i]);
            if(i < nodes.length / 2){
                t.setToX(toX);
            } else {
                t.setToX(fromX);
            }
            swapInX.getChildren().add(t);
        }

        return swapInX;
    }
    
    public Animation moveInY2(double fromY, double toY, Node... nodes){
        ParallelTransition parallel = new ParallelTransition();
        for(Node node: nodes){
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(node);
            translate.setToY(toY);
            parallel.getChildren().add(translate);
        }
        return parallel;
    }
    /*
    public Animation swapInX(double fromX, double toX, Node node1, Node node2){
        Timeline swapInX = new Timeline(); 
        
        KeyValue fromXValue1 = new KeyValue(node1.translateXProperty(), fromX);
        KeyValue toXValue1 = new KeyValue(node1.translateXProperty(), toX);
        KeyFrame moveFromX1 = new KeyFrame(Duration.ZERO, fromXValue1);
        KeyFrame moveToX1 = new KeyFrame(Duration.millis(400), toXValue1);
        
        KeyValue fromXValue2 = new KeyValue(node2.translateXProperty(), toX);
        KeyValue toXValue2 = new KeyValue(node2.translateXProperty(), fromX);
        KeyFrame moveFromX2 = new KeyFrame(Duration.ZERO, fromXValue2);
        KeyFrame moveToX2 = new KeyFrame(Duration.millis(400), toXValue2);
        
        swapInX.getKeyFrames().addAll(moveFromX1, moveToX1, moveFromX2, moveToX2);
        
        return swapInX;
    }
    */
    
    /*
    public Animation moveInY(double fromY, double toY, Node... nodes){
        Timeline moveInY = new Timeline();
        
        for(Node node: nodes){
            KeyValue fromYValue = new KeyValue(node.translateYProperty(), fromY); 
            KeyValue toYValue = new KeyValue(node.translateYProperty(), toY); 
            
            KeyFrame moveFromY = new KeyFrame(Duration.ZERO, fromYValue); 
            KeyFrame moveToY = new KeyFrame(Duration.millis(400), toYValue);
            
            moveInY.getKeyFrames().addAll(moveFromY, moveToY); 
        }
        
        return moveInY;
    }
    */
    
}
