package SortVisualizerCore;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public class Mover {
    
    /*
    Clase Mover ("movedor"). 
    Dentro de esta clase están los métodos que crean las animaciones de movimiento.
    Los parámetros de los métodos son las coordenadas de inicio y final del movimiento, y las nodos sobre los cuales se aplicará la animación.
    Los métodos permiten mover uno o más nodos en el eje X, Y o intercambiar la posición de dos nodos.
    Las animaciones se crean a partir de las clases KeyValue, KeyFrame y Timeline, y la propiedad translate() de los nodos.
    */
    
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
    
}
