package SortVisualizerCore;

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Animator {
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
    
    public Animator(ArrayList<Integer> numbers, ArrayList<StackPane> stackpanes){
        this.numbers = numbers;
        this.boxes = stackpanes;
        
        translateAnimations = new ArrayList();
        pseudocodeAnimations = new ArrayList();
        
        setCrane();
        setLabels();
        getBubbleSortAnimations();
        getInsertionSortAnimations();   
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
        
            translateAnimations.add(mover.moveInY(0.65* Main.windowHeight, 
                    0.65* Main.windowHeight - 2 * Main.squareDimension, 
                    boxes.get(i))); 
            
            changeLabelProperties(label1, 
                    "for i = " + i, 
                    initialStyle, finalStyle, 
                    Duration.millis(400)); 
        
            while(j > 0 && currentNumber < numbers.get(j - 1)){ 
                translateAnimations.add(mover.moveInX(Main.coordinates.get(j - 1), 
                        Main.coordinates.get(j), 
                        boxes.get(j - 1), craneUpperBox1,rope1,magnet1)); 
            
                boxes.set(j, boxes.get(j - 1)); 
                numbers.set(j, numbers.get(j - 1)); 
            
                if(j - 2 >= 0){
                    changeLabelProperties(label2, 
                            "\twhile(" + currentNumber + " < numbers[" + (j - 1) + "])\n\t\tnumbers[" + j + "] = numbers [" + (j - 1) + "]", 
                            initialStyle, finalStyle, 
                            Duration.millis(800)); 
                    
                    translateAnimations.add(mover.moveInX(Main.coordinates.get(j), 
                            Main.coordinates.get(j - 2), 
                            craneUpperBox1,rope1,magnet1)); 
                } else {
                    changeLabelProperties(label2, 
                            "\twhile(" + currentNumber + " < numbers[" + (j - 1) + "])\n\t\tnumbers[" + j + "] = numbers [" + (j - 1) + "]", 
                            initialStyle, finalStyle, 
                            Duration.millis(400));
                }

                j--; 
            }
            
            translateAnimations.add(mover.moveInX(Main.coordinates.get(i), 
                    Main.coordinates.get(j), 
                    stackpane, craneUpperBox2,rope2,magnet2)); 
            
            changeLabelProperties(label3, 
                    "\tnumbers[" + j + "]" + " = " + currentNumber, 
                    initialStyle, finalStyle, 
                    Duration.millis(1600));
            
            translateAnimations.add(mover.moveInY(0.65 * Main.windowHeight - 2 * Main.squareDimension, 
                    0.65 * Main.windowHeight, 
                    stackpane));
         
            boxes.set(j, stackpane); 
            numbers.set(j, currentNumber);
        }
    }
    
    private void getBubbleSortAnimations(){
        for(int i = 0; i < numbers.size(); i++){
            for(int j = 0; j < numbers.size() - 1 - i; j++){
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
    
    private void setCrane(){
        craneUpperBox1.setTranslateX(Main.coordinates.get(0));
        craneUpperBox1.setTranslateY(5);
        craneUpperBox1.setWidth(40);
        craneUpperBox1.setHeight(20);
        craneUpperBox1.setLayoutX(Main.squareDimension/2 - craneUpperBox1.getWidth()/2);
        craneUpperBox1.setFill(Color.YELLOW);
      
        craneUpperBox2.setTranslateX(Main.coordinates.get(1));
        craneUpperBox2.setTranslateY(35);
        craneUpperBox2.setWidth(40);
        craneUpperBox2.setHeight(20);
        craneUpperBox2.setLayoutX(Main.squareDimension/2 - craneUpperBox2.getWidth()/2);
        craneUpperBox2.setFill(Color.GREEN);
       
        magnet1.setTranslateX(Main.coordinates.get(0));
        magnet1.setTranslateY(5);
        magnet1.setWidth(40);
        magnet1.setHeight(20);
        magnet1.setLayoutX(Main.squareDimension/2- magnet1.getWidth()/2);
        magnet1.setLayoutY(0.65*Main.windowHeight-25);
        magnet1.setFill(Color.ORANGE);
      
        magnet2.setTranslateX(Main.coordinates.get(1));
        magnet2.setTranslateY(35);
        magnet2.setWidth(40);
        magnet2.setHeight(20);
        magnet2.setLayoutX(Main.squareDimension/2-magnet2.getWidth()/2);
        magnet2.setLayoutY(0.65*Main.windowHeight-2*Main.squareDimension - magnet2.getTranslateY() - magnet2.getHeight());
        magnet2.setFill(Color.ORANGE);
 
        rope1 = new Line(Main.squareDimension/2,0,Main.squareDimension/2,0.65*Main.windowHeight - 5);
        rope2 = new Line(Main.squareDimension/2,0,Main.squareDimension/2,0.65*Main.windowHeight - 2 * Main.squareDimension - magnet2.getHeight() - 35);
        rope1.setTranslateY(5);
        rope1.setTranslateX(Main.coordinates.get(0));
        rope2.setTranslateX(Main.coordinates.get(1));
        rope2.setTranslateY(35);
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
      
    private void changeLabelProperties(Label label, String newText, String initialStyle, String newStyle, Duration duration){
        Timeline changeLabelPropertiesAnimation = new Timeline();
        
        KeyFrame newTextFrame = new KeyFrame(Duration.ZERO, event -> label.setText(newText));
        KeyFrame newStyleFrame = new KeyFrame(Duration.ZERO, event -> label.setStyle(newStyle));
        KeyFrame initialStyleFrame = new KeyFrame(duration, event -> label.setStyle(initialStyle));
        
        changeLabelPropertiesAnimation.getKeyFrames().addAll(newTextFrame, newStyleFrame, initialStyleFrame);
        
        pseudocodeAnimations.add(changeLabelPropertiesAnimation);
    }

    public ArrayList<Animation> getTranslateTransitions() {
        return translateAnimations;
    }

    public ArrayList<Animation> getPseudocodeAnimations() {
        return pseudocodeAnimations;
    }

    public VBox getPseudocodeBox() {
        return pseudocodeBox;
    }

    public Rectangle getRectangleAnimation1() {
        return craneUpperBox1;
    }

    public Rectangle getRectangleAnimation2() {
        return craneUpperBox2;
    }

    public Line getGrua1() {
        return rope1;
    }

    public Line getGrua2() {
        return rope2;
    }
    
    public Rectangle getRectangleGrua() {
        return magnet1;
    }

    public Rectangle getRectangleGrua2() {
        return magnet2;
    }
    
}
