package SortVisualizer;

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Animator {
    
    private final ArrayList<Integer> numbers;
    private final ArrayList<StackPane> stackpanes;
    
    private final ArrayList<TranslateTransition> translateTransitions;
    private final ArrayList<Animation> pseudocodeAnimations;
    
    private VBox pseudocodeBox;
    private Label label1, label2, label3;
    private final String initialStyle = "-fx-text-fill: white";
    private final String finalStyle = "-fx-background-color: yellow";
    
    public Animator(ArrayList<Integer> numbers, ArrayList<StackPane> stackpanes){
        this.numbers = numbers;
        this.stackpanes = stackpanes;
        translateTransitions = new ArrayList();
        pseudocodeAnimations = new ArrayList();
        setLabels();
        getInsertionSortTransitions();
       
    }
    
    private void getInsertionSortTransitions(){
        int j;
        
        for(int i = 1; i < numbers.size(); i++){
            j = i;
            
            StackPane stackpane = stackpanes.get(i);
            int currentNumber = numbers.get(i);
            
            moveInY(stackpanes.get(i), 0.65* Main.windowHeight, 0.65* Main.windowHeight - 2 * Main.squareDimension);
            changeLabelProperties(label1, "for i = " + i, initialStyle, finalStyle, Duration.millis(400));
         
            while(j > 0 && currentNumber < numbers.get(j - 1)){
                moveInX(stackpanes.get(j - 1), Main.coordinates.get(j - 1), Main.coordinates.get(j));
                changeLabelProperties(label2, "\twhile(" + currentNumber + " < numbers[" + (j - 1) + "])\n\t\tnumbers[" + j + "] = numbers [" + (j - 1) + "]", initialStyle, finalStyle, Duration.millis(400));
              
                stackpanes.set(j, stackpanes.get(j - 1));
                numbers.set(j, numbers.get(j - 1));
                
                j--;    
            }
            
            changeLabelProperties(label3, "\tnumbers[" + j + "]" + " = " + currentNumber, initialStyle, finalStyle, Duration.millis(800));
            moveInX(stackpane, Main.coordinates.get(i), Main.coordinates.get(j));
            moveInY(stackpane, 0.65 * Main.windowHeight - 2 * Main.squareDimension, 0.65 * Main.windowHeight);
         
            stackpanes.set(j, stackpane); 
            numbers.set(j, currentNumber);
        }
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
        TranslateTransition moveInX = new TranslateTransition();
        moveInX.setNode(node);
        moveInX.setFromX(fromX);
        moveInX.setToX(toX);
        translateTransitions.add(moveInX);
    }
    
    private void moveInY(Node node, double fromY, double toY){
        TranslateTransition moveInY = new TranslateTransition();
        moveInY.setNode(node);
        moveInY.setFromY(fromY);
        moveInY.setToY(toY);
        translateTransitions.add(moveInY);
    }
    
    private void changeLabelProperties(Label label, String newText, String initialStyle, String newStyle, Duration duration){
        Timeline changeLabelPropertiesAnimation = new Timeline();
        KeyFrame newTextFrame = new KeyFrame(Duration.ZERO, event -> label.setText(newText));
        KeyFrame newStyleFrame = new KeyFrame(Duration.ZERO, event -> label.setStyle(newStyle));
        KeyFrame initialStyleFrame = new KeyFrame(duration, event -> label.setStyle(initialStyle));
        changeLabelPropertiesAnimation.getKeyFrames().addAll(newTextFrame, newStyleFrame, initialStyleFrame);
        pseudocodeAnimations.add(changeLabelPropertiesAnimation);
    }

    public ArrayList<TranslateTransition> getTranslateTransitions() {
        return translateTransitions;
    }

    public ArrayList<Animation> getPseudocodeAnimations() {
        return pseudocodeAnimations;
    }

    public VBox getPseudocodeBox() {
        return pseudocodeBox;
    }
    
}