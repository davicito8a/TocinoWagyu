package SortVisualizer;

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Animator {
    
    private final ArrayList<Integer> numbers;
    private final ArrayList<StackPane> stackpanes;
    
    private final ArrayList<Animation> translateTransitions;
    private final ArrayList<Animation> pseudocodeAnimations;
    private final ArrayList<Animation> AnimacionesDeGrua;
    
    private VBox pseudocodeBox;
    private Label label1, label2, label3;
    private final String initialStyle = "-fx-text-fill: white";
    private final String finalStyle = "-fx-background-color: yellow";
    
    Rectangle rectangleAnimation1 = new Rectangle();
    Rectangle rectangleAnimation2 = new Rectangle();
    Rectangle rectangleGrua = new Rectangle();
    Rectangle rectangleGrua2 = new Rectangle();

    public Rectangle getRectangleGrua() {
        return rectangleGrua;
    }

    public Rectangle getRectangleGrua2() {
        return rectangleGrua2;
    }
    
    Line Grua1 ;
    Line Grua2 ;
    
    
    
    public Animator(ArrayList<Integer> numbers, ArrayList<StackPane> stackpanes){
        this.numbers = numbers;
        this.stackpanes = stackpanes;
        translateTransitions = new ArrayList();
        pseudocodeAnimations = new ArrayList();
        AnimacionesDeGrua = new ArrayList();
        
        setCrane();
        setLabels();
        getInsertionSortTransitions();
       
    }
    
    private void getInsertionSortTransitions(){
        int j = 1;
        
        for(int i = 1; i < numbers.size(); i++){
            if (j > 0){
                moveInX(Main.coordinates.get(j - 1), Main.coordinates.get(i - 1), rectangleAnimation1,Grua1,rectangleGrua);
            } else if (j == 0){
                moveInX(Main.coordinates.get(j + 1), Main.coordinates.get(i - 1), rectangleAnimation1,Grua1,rectangleGrua);
            }
            
            moveInX(Main.coordinates.get(j), Main.coordinates.get(i), rectangleAnimation2,Grua2,rectangleGrua2);
         
            j = i;
            
            StackPane stackpane = stackpanes.get(i);
            int currentNumber = numbers.get(i);
            
            moveInY(0.65* Main.windowHeight, 0.65* Main.windowHeight - 2 * Main.squareDimension, stackpanes.get(i));
            changeLabelProperties(label1, "for i = " + i, initialStyle, finalStyle, Duration.millis(400));
         
            while(j > 0 && currentNumber < numbers.get(j - 1)){
                moveInX(Main.coordinates.get(j - 1), Main.coordinates.get(j), stackpanes.get(j - 1), rectangleAnimation1,Grua1,rectangleGrua);
                changeLabelProperties(label2, "\twhile(" + currentNumber + " < numbers[" + (j - 1) + "])\n\t\tnumbers[" + j + "] = numbers [" + (j - 1) + "]", initialStyle, finalStyle, Duration.millis(800));
              
                stackpanes.set(j, stackpanes.get(j - 1));
                numbers.set(j, numbers.get(j - 1));
                
                
                if(j - 2 >= 0)
                    moveInX(Main.coordinates.get(j), Main.coordinates.get(j - 2), rectangleAnimation1,Grua1,rectangleGrua);
                j--;  
                
            }
            moveInX(Main.coordinates.get(i), Main.coordinates.get(j), stackpane, rectangleAnimation2,Grua2,rectangleGrua2);
            changeLabelProperties(label3, "\tnumbers[" + j + "]" + " = " + currentNumber, initialStyle, finalStyle, Duration.millis(1400));
            moveInY(0.65 * Main.windowHeight - 2 * Main.squareDimension, 0.65 * Main.windowHeight, stackpane);
         
            stackpanes.set(j, stackpane); 
            numbers.set(j, currentNumber);
        }
    }
    
    private void setCrane(){
        rectangleAnimation1.setTranslateX(Main.coordinates.get(0));
        rectangleAnimation1.setTranslateY(5);
        rectangleAnimation1.setWidth(80);
        rectangleAnimation1.setHeight(20);
        rectangleAnimation1.setLayoutX(Main.squareDimension/2 - rectangleAnimation1.getWidth()/2);
        rectangleAnimation1.setFill(Color.YELLOW);//Grua que Ordena lo demás
        
        rectangleAnimation2.setTranslateX(Main.coordinates.get(1));
        rectangleAnimation2.setWidth(100);
        rectangleAnimation2.setHeight(30);
        rectangleAnimation2.setFill(Color.GREEN);//Grua que levanta
        rectangleAnimation2.setLayoutX(Main.squareDimension/2 - rectangleAnimation2.getWidth()/2);
        rectangleAnimation2.setTranslateY(35);
        
        rectangleGrua.setTranslateX(Main.coordinates.get(0));
        rectangleGrua.setTranslateY(5);
        rectangleGrua.setHeight(22);
        rectangleGrua.setWidth(40);
        rectangleGrua.setLayoutX(Main.squareDimension/2- rectangleGrua.getWidth()/2);
        rectangleGrua.setLayoutY(0.65*Main.windowHeight-25);
        rectangleGrua.setFill(Color.ORANGE);
        
        rectangleGrua2.setTranslateX(Main.coordinates.get(1));
        rectangleGrua2.setTranslateY(35);
        rectangleGrua2.setHeight(20);
        rectangleGrua2.setWidth(40);
        rectangleGrua2.setLayoutX(Main.squareDimension/2-rectangleGrua2.getWidth()/2);
        rectangleGrua2.setLayoutY(0.65*Main.windowHeight-2*Main.squareDimension - 35 - rectangleGrua2.getHeight());
        
        rectangleGrua2.setFill(Color.ORANGE);
        
        Grua1 = new Line(Main.squareDimension/2,0,Main.squareDimension/2,0.65*Main.windowHeight - 5);
        Grua2 = new Line(Main.squareDimension/2,0,Main.squareDimension/2,0.65*Main.windowHeight - 2 * Main.squareDimension - rectangleGrua2.getHeight() - 35);
        Grua1.setTranslateY(5);
        Grua1.setTranslateX(Main.coordinates.get(0));
        Grua2.setTranslateX(Main.coordinates.get(1));
        Grua2.setTranslateY(35);
    }
    
    private void setLabels(){
        pseudocodeBox = new VBox();
        
        label1 = new Label("for i = n");
        label2 = new Label("\twhile(currentNumber < numbers[j - 1])\n\t\tnumbers[j] = numbers [j - 1]");
        label3 = new Label("\tnumbers[j] = currentNumber");
        label1.setStyle(initialStyle);
        label2.setStyle(initialStyle);
        label3.setStyle(initialStyle);
       
        pseudocodeBox.getChildren().addAll(label1, label2, label3);
        pseudocodeBox.setLayoutX(0.05 * Main.windowWidth);
        pseudocodeBox.setLayoutY(0.8 * Main.windowHeight);
    }
      
    private void moveInX(double fromX, double toX, Node... nodes){
        Timeline parallelMoveInX = new Timeline();
        for(Node node: nodes){
            KeyValue fromXValue = new KeyValue(node.translateXProperty(), fromX);
            KeyValue toXValue = new KeyValue(node.translateXProperty(), toX);
            KeyFrame moveFromX = new KeyFrame(Duration.ZERO, fromXValue);
            KeyFrame moveToX = new KeyFrame(Duration.millis(400), toXValue);
            parallelMoveInX.getKeyFrames().addAll(moveFromX, moveToX);
        }
        translateTransitions.add(parallelMoveInX);  
    }
    
    private void moveInY(double fromY, double toY, Node... nodes){
        Timeline parallelMoveInY = new Timeline();
        for(Node node: nodes){
            KeyValue fromYValue = new KeyValue(node.translateYProperty(), fromY);
            KeyValue toYValue = new KeyValue(node.translateYProperty(), toY);
            KeyFrame moveFromY = new KeyFrame(Duration.ZERO, fromYValue);
            KeyFrame moveToY = new KeyFrame(Duration.millis(400), toYValue);
            parallelMoveInY.getKeyFrames().addAll(moveFromY, moveToY);
        }
        translateTransitions.add(parallelMoveInY);
    }
    
    private void changeLabelProperties(Label label, String newText, String initialStyle, String newStyle, Duration duration){
        Timeline changeLabelPropertiesAnimation = new Timeline();
        KeyFrame newTextFrame = new KeyFrame(Duration.ZERO, event -> label.setText(newText));
        KeyFrame newStyleFrame = new KeyFrame(Duration.ZERO, event -> label.setStyle(newStyle));
        KeyFrame initialStyleFrame = new KeyFrame(duration, event -> label.setStyle(initialStyle));
        changeLabelPropertiesAnimation.getKeyFrames().addAll(newTextFrame, newStyleFrame, initialStyleFrame);
        pseudocodeAnimations.add(changeLabelPropertiesAnimation);
    }

    public ArrayList<Animation> getTranslateTransitions() {
        return translateTransitions;
    }

    public ArrayList<Animation> getPseudocodeAnimations() {
        return pseudocodeAnimations;
    }

    public VBox getPseudocodeBox() {
        return pseudocodeBox;
    }

    public Rectangle getRectangleAnimation1() {
        return rectangleAnimation1;
    }

    public Rectangle getRectangleAnimation2() {
        return rectangleAnimation2;
    }

    public ArrayList<Animation> getAnimacionesDeGrua() {
        return AnimacionesDeGrua;
    }

    public Line getGrua1() {
        return Grua1;
    }

    public Line getGrua2() {
        return Grua2;
    }
    
}