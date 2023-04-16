package SortVisualizer;

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
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
    Line Grua1 = new Line(Main.coordinates.get(1),30,290,300);
    
    public Animator(ArrayList<Integer> numbers, ArrayList<StackPane> stackpanes){
        this.numbers = numbers;
        this.stackpanes = stackpanes;
        translateTransitions = new ArrayList();
        pseudocodeAnimations = new ArrayList();
        AnimacionesDeGrua = new ArrayList();
        
        rectangleAnimation1.setTranslateX(Main.coordinates.get(0));
        rectangleAnimation1.setWidth(Main.squareDimension);
        rectangleAnimation1.setHeight(Main.squareDimension);
        rectangleAnimation1.setFill(Color.YELLOW);//Grua que Ordena lo demás
        rectangleAnimation1.setTranslateY(5);
        
        rectangleAnimation2.setTranslateX(Main.coordinates.get(1));
        rectangleAnimation2.setWidth(Main.squareDimension);
        rectangleAnimation2.setHeight(Main.squareDimension);
        rectangleAnimation2.setFill(Color.GREEN);//Grua que levanta
        rectangleAnimation2.setTranslateY(5);

        setLabels();
        getInsertionSortTransitions();
       
    }
    
    private void getInsertionSortTransitions(){
        int j=1;
        
        for(int i = 1; i < numbers.size(); i++){
            if(j>0){
                moveInX(rectangleAnimation1,Main.coordinates.get(j-1),Main.coordinates.get(i-1));//Para la caja verde delante de la amarilla
            }
            else if (j == 0){
                moveInX(rectangleAnimation1, Main.coordinates.get(j +1), Main.coordinates.get(i - 1));//Para el caso de la primera posición.
            }
            moveInX(rectangleAnimation2,Main.coordinates.get(j),Main.coordinates.get(i));
            j = i;
            
            StackPane stackpane = stackpanes.get(i);
            int currentNumber = numbers.get(i);
            
            moveInY(stackpanes.get(i), 0.65* Main.windowHeight, 0.65* Main.windowHeight - 2 * Main.squareDimension);
            changeLabelProperties(label1, "for i = " + i, initialStyle, finalStyle, Duration.millis(400));
         
            while(j > 0 && currentNumber < numbers.get(j - 1)){
                parallelMoveInX(stackpanes.get(j-1),rectangleAnimation1, Main.coordinates.get(j - 1), Main.coordinates.get(j));
                
                changeLabelProperties(label2, "\twhile(" + currentNumber + " < numbers[" + (j - 1) + "])\n\t\tnumbers[" + j + "] = numbers [" + (j - 1) + "]", initialStyle, finalStyle, Duration.millis(400));
              
                stackpanes.set(j, stackpanes.get(j - 1));
                numbers.set(j, numbers.get(j - 1));
                if(j - 2 >= 0)
                    moveInX(rectangleAnimation1, Main.coordinates.get(j), Main.coordinates.get(j - 2));
                
                j--;    
            }
            parallelMoveInX(stackpane, rectangleAnimation2, Main.coordinates.get(i),Main.coordinates.get(j));
            
            //moveInX(rectangleAnimation2, Main.coordinates.get(i),Main.coordinates.get(i-1));
            changeLabelProperties(label3, "\tnumbers[" + j + "]" + " = " + currentNumber, initialStyle, finalStyle, Duration.millis(1200));
            //moveInX(stackpane, Main.coordinates.get(i), Main.coordinates.get(j));
            moveInY(stackpane, 0.65 * Main.windowHeight - 2 * Main.squareDimension, 0.65 * Main.windowHeight);
         
            stackpanes.set(j, stackpane); 
            numbers.set(j, currentNumber);
        }
    }
    
    public double getConstant(){
        double constant = translateTransitions.size()/AnimacionesDeGrua.size(); 
        return constant;
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
    
    private void moveInX(Node node, double fromX, double toX){
        /*
        Timeline moveInX = new Timeline();
        KeyFrame moveInXFrame = new KeyFrame(Duration.millis(400), event -> node.setTranslateX(toX));
        moveInX.getKeyFrames().addAll(moveInXFrame);
        translateTransitions.add(moveInX);
        */

        TranslateTransition moveInX = new TranslateTransition();
        moveInX.setNode(node);
        //moveInX.setFromX(fromX);
        moveInX.setToX(toX);
        translateTransitions.add(moveInX);
    }
    
    private void moveInY(Node node, double fromY, double toY){
        /*
        Timeline moveInY = new Timeline();
        KeyFrame moveInYFrame = new KeyFrame(Duration.millis(400), event -> node.setTranslateY(toY));
        moveInY.getKeyFrames().addAll(moveInYFrame);
        translateTransitions.add(moveInY);
        */
        
        TranslateTransition moveInY = new TranslateTransition();
        moveInY.setNode(node);
        moveInY.setFromY(fromY);
        moveInY.setToY(toY);
        translateTransitions.add(moveInY);
    }
    
    private void parallelMoveInX(Node node1, Node node2, double fromX, double toX){
        Timeline parallelMoveInX = new Timeline();
        KeyValue fromX1 = new KeyValue(node1.translateXProperty(), fromX);
        KeyValue toX1 = new KeyValue(node1.translateXProperty(), toX);
        KeyFrame moveFromX1 = new KeyFrame(Duration.ZERO, fromX1);
        KeyFrame moveToX1 = new KeyFrame(Duration.millis(400), toX1);
        
        KeyValue fromX2 = new KeyValue(node2.translateXProperty(), fromX);
        KeyValue toX2 = new KeyValue(node2.translateXProperty(), toX);
        KeyFrame moveFromX2 = new KeyFrame(Duration.ZERO, fromX2);
        KeyFrame moveToX2 = new KeyFrame(Duration.millis(400), toX2);
        
        parallelMoveInX.getKeyFrames().addAll(moveFromX1, moveFromX2, moveToX1, moveToX2);
        
        translateTransitions.add(parallelMoveInX);
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

}