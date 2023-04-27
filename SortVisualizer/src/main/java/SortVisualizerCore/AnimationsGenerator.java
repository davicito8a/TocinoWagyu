package SortVisualizerCore;

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class AnimationsGenerator {
    private final Mover mover = new Mover();
    
    private final ArrayList<Integer> numbers;
    private final ArrayList<StackPane> boxes;
    
    private final ArrayList<Animation> translateAnimations;
    private final ArrayList<Animation> pseudocodeAnimations;
    
    private VBox pseudocodeBox;
    private Label label1, label2, label3;
    private final String initialStyle = "-fx-text-fill: white";
    private final String finalStyle = "-fx-background-color: yellow";
    
    Rectangle craneUpperBox1 = new Rectangle();
    Rectangle craneUpperBox2 = new Rectangle();
    Line rope1;
    Line rope2;
    Rectangle magnet1 = new Rectangle();
    Rectangle magnet2 = new Rectangle();
    
    public AnimationsGenerator(ArrayList<Integer> numbers, ArrayList<StackPane> stackpanes){
        this.numbers = numbers;
        this.boxes = stackpanes;
        
        translateAnimations = new ArrayList();
        pseudocodeAnimations = new ArrayList();
        
        setCrane();
        setLabels();
        
        switch (Main.sortType){
            case 0: 
                getInsertionSortAnimations(); 
                break;
            case 1: 
                getBubbleSortAnimations();
                break;
            case 2:
                getCocktailSortAnimations();
            default:
                break;
        }
    }
   
    private void getInsertionSortAnimations(){
        int j = 1; 
        
        for(int i = 1; i < numbers.size(); i++){ 
            if (j > 0){ 
                translateAnimations.add(mover.moveInX(Main.coordinates.get(j - 1), 
                        Main.coordinates.get(i - 1), craneUpperBox1,
                        rope1,magnet1));
            } else if (j == 0){ 
                translateAnimations.add(mover.moveInX(Main.coordinates.get(j + 1), 
                        Main.coordinates.get(i - 1), 
                        craneUpperBox1,rope1,magnet1)); 
            }
        
            translateAnimations.add(mover.moveInX(Main.coordinates.get(j), 
                    Main.coordinates.get(i), 
                    craneUpperBox2,rope2,magnet2)); 
        
            j = i; 
        
            StackPane stackpane = boxes.get(i); 
            int currentNumber = numbers.get(i); 
        
            translateAnimations.add(parallelAnimations(mover.moveInY(0.65* Main.windowHeight, 
                    0.65* Main.windowHeight - 2 * Main.squareDimension, 
                    boxes.get(i)), changeLabelProperties(label1, 
                    "for i = " + i, 
                    initialStyle, finalStyle, 
                    Duration.millis(400)))); 
            
             
        
            while(j > 0 && currentNumber < numbers.get(j - 1)){ 
                translateAnimations.add(parallelAnimations(mover.moveInX(Main.coordinates.get(j - 1), 
                        Main.coordinates.get(j), 
                        boxes.get(j - 1), craneUpperBox1,rope1,magnet1), changeLabelProperties(label2, 
                            "\twhile(" + currentNumber + " < numbers[" + (j - 1) + "])\n\t\tnumbers[" + j + "] = numbers [" + (j - 1) + "]", 
                            initialStyle, finalStyle, 
                            Duration.millis(400)))); 
            
                boxes.set(j, boxes.get(j - 1)); 
                numbers.set(j, numbers.get(j - 1)); 
            
                if(j - 2 >= 0){
                    translateAnimations.add(mover.moveInX(Main.coordinates.get(j), 
                    Main.coordinates.get(j - 2), 
                    craneUpperBox1,rope1,magnet1)); 
                }  
               
                j--; 
            }
            
            translateAnimations.add(mover.moveInX(Main.coordinates.get(i), 
                    Main.coordinates.get(j), 
                    stackpane, craneUpperBox2,rope2,magnet2)); 

            translateAnimations.add(parallelAnimations(mover.moveInY(0.65 * Main.windowHeight - 2 * Main.squareDimension, 
                    0.65 * Main.windowHeight, 
                    stackpane), changeLabelProperties(label3, 
                    "\tnumbers[" + j + "]" + " = " + currentNumber, 
                    initialStyle, finalStyle, 
                    Duration.millis(400))));
         
            boxes.set(j, stackpane); 
            numbers.set(j, currentNumber);
        }
    }
    
    private void getBubbleSortAnimations(){
        for(int i = numbers.size() - 1; i > 0; i--){
            for(int j = 0; j < i; j++){
                if(numbers.get(j + 1) < numbers.get(j)){
                    int currentNumber = numbers.get(j);
                    StackPane stackpane = boxes.get(j);
 
                    translateAnimations.add(mover.moveInY(0.65 * Main.windowHeight, 
                            0.65 * Main.windowHeight - 2 * Main.squareDimension, 
                            stackpane, boxes.get(j + 1)));
                    
                    translateAnimations.add(mover.swapInX(Main.coordinates.get(j), 
                            Main.coordinates.get(j + 1), 
                            stackpane, boxes.get(j + 1)));
                    
                    translateAnimations.add(mover.moveInY(0.65 * Main.windowHeight - 2 * Main.squareDimension, 
                            0.65 * Main.windowHeight, 
                            stackpane, boxes.get(j + 1)));
                    
                    numbers.set(j, numbers.get(j + 1));
                    boxes.set(j, boxes.get(j + 1));
                    
                    numbers.set(j + 1, currentNumber);
                    boxes.set(j + 1, stackpane);
                }
            }
        }
    }
    
    private void getCocktailSortAnimations(){
        boolean swapped = true;
        int start = 0;
        int end = numbers.size();
        
        while(swapped){
            swapped = false;
            
            for(int i = start; i < end - 1; i++){
                if(numbers.get(i + 1) < numbers.get(i)){
                    int currentNumber = numbers.get(i);
                    StackPane stackpane = boxes.get(i);
                    
                    translateAnimations.add(mover.moveInY(0.65 * Main.windowHeight, 
                            0.65 * Main.windowHeight - 2 * Main.squareDimension, 
                            stackpane, boxes.get(i + 1)));
                    
                    translateAnimations.add(mover.swapInX(Main.coordinates.get(i), 
                            Main.coordinates.get(i + 1), 
                            stackpane, boxes.get(i + 1)));
                    
                    translateAnimations.add(mover.moveInY(0.65 * Main.windowHeight - 2 * Main.squareDimension, 
                            0.65 * Main.windowHeight, 
                            stackpane, boxes.get(i + 1)));
                    
                    numbers.set(i, numbers.get(i + 1));
                    boxes.set(i, boxes.get(i + 1));
                    
                    numbers.set(i + 1, currentNumber);
                    boxes.set(i + 1, stackpane);
                    
                    swapped = true;
                }
            }
            
            if(swapped == false){
                break;
            }
            
            swapped = false;
            
            end = end - 1;
            
            for(int i = end - 1; i >= start; i--){
                if(numbers.get(i + 1) < numbers.get(i)){
                    int currentNumber = numbers.get(i);
                    StackPane stackpane = boxes.get(i);
                    
                    translateAnimations.add(mover.moveInY(0.65 * Main.windowHeight, 
                            0.65 * Main.windowHeight - 2 * Main.squareDimension, 
                            stackpane, boxes.get(i + 1)));
                    
                    translateAnimations.add(mover.swapInX(Main.coordinates.get(i), 
                            Main.coordinates.get(i + 1), 
                            stackpane, boxes.get(i + 1)));
                    
                    translateAnimations.add(mover.moveInY(0.65 * Main.windowHeight - 2 * Main.squareDimension, 
                            0.65 * Main.windowHeight, 
                            stackpane, boxes.get(i + 1)));
                    
                    numbers.set(i, numbers.get(i + 1));
                    boxes.set(i, boxes.get(i + 1));
                    
                    numbers.set(i + 1, currentNumber);
                    boxes.set(i + 1, stackpane);
                    
                    swapped = true;
                }
            }
            
            start = start + 1;   
        }
        
    }
    
    private void setCrane(){
        int y = 0;
        int y2 = 10;
        
        craneUpperBox1.setTranslateX(Main.coordinates.get(0));
        craneUpperBox1.setTranslateY(y);
        craneUpperBox1.setWidth(40);
        craneUpperBox1.setHeight(20);
        craneUpperBox1.setLayoutX(Main.squareDimension/2 - craneUpperBox1.getWidth()/2);
        craneUpperBox1.setFill(Color.YELLOW);
      
        craneUpperBox2.setTranslateX(Main.coordinates.get(1));
        craneUpperBox2.setTranslateY(y2);
        craneUpperBox2.setWidth(40);
        craneUpperBox2.setHeight(20);
        craneUpperBox2.setLayoutX(Main.squareDimension/2 - craneUpperBox2.getWidth()/2);
        craneUpperBox2.setFill(Color.GREEN);
       
        magnet1.setTranslateX(Main.coordinates.get(0));
        magnet1.setTranslateY(y);
        magnet1.setWidth(40);
        magnet1.setHeight(20);
        magnet1.setLayoutX(Main.squareDimension/2- magnet1.getWidth()/2);
        magnet1.setLayoutY(0.65*Main.windowHeight - magnet1.getHeight() - y);
        magnet1.setFill(Color.ORANGE);
      
        magnet2.setTranslateX(Main.coordinates.get(1));
        magnet2.setTranslateY(y2);
        magnet2.setWidth(40);
        magnet2.setHeight(20);
        magnet2.setLayoutX(Main.squareDimension/2-magnet2.getWidth()/2);
        magnet2.setLayoutY(0.65*Main.windowHeight-2*Main.squareDimension - magnet2.getHeight() - y2);
        magnet2.setFill(Color.ORANGE);
 
        rope1 = new Line(Main.squareDimension/2,0,Main.squareDimension/2,0.65*Main.windowHeight - y);
        rope2 = new Line(Main.squareDimension/2,0,Main.squareDimension/2,0.65*Main.windowHeight - 2 * Main.squareDimension - magnet2.getHeight() - y2);
        rope1.setTranslateX(Main.coordinates.get(0));
        rope1.setTranslateY(y);
        rope2.setTranslateX(Main.coordinates.get(1));
        rope2.setTranslateY(y2);
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
      
    private Animation changeLabelProperties(Label label, String newText, String initialStyle, String newStyle, Duration duration){
        Timeline changeLabelPropertiesAnimation = new Timeline();
        
        KeyFrame newTextFrame = new KeyFrame(Duration.ZERO, event -> label.setText(newText));
        KeyFrame newStyleFrame = new KeyFrame(Duration.ZERO, event -> label.setStyle(newStyle));
        KeyFrame initialStyleFrame = new KeyFrame(duration, event -> label.setStyle(initialStyle));
        
        changeLabelPropertiesAnimation.getKeyFrames().addAll(newTextFrame, newStyleFrame, initialStyleFrame);
        
        return changeLabelPropertiesAnimation;
    }
    
    private ParallelTransition parallelAnimations(Animation animation1, Animation animation2){
        ParallelTransition parallelAnimations = new ParallelTransition();
        parallelAnimations.getChildren().addAll(animation1, animation2);
        return parallelAnimations;
    }

    public ArrayList<Animation> getTranslateAnimations() {
        return translateAnimations;
    }

    public ArrayList<Animation> getPseudocodeAnimations() {
        return pseudocodeAnimations;
    }

    public VBox getPseudocodeBox() {
        return pseudocodeBox;
    }

    public Rectangle getCraneUpperBox1() {
        return craneUpperBox1;
    }

    public Rectangle getCraneUpperBox2() {
        return craneUpperBox2;
    }

    public Line getRope1() {
        return rope1;
    }

    public Line getRope2() {
        return rope2;
    }
    
    public Rectangle getMagnet1() {
        return magnet1;
    }

    public Rectangle getMagnet2() {
        return magnet2;
    }
    
}
