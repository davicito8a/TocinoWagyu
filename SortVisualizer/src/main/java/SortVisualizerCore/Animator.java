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
        this.boxes = stackpanes;
        
        translateAnimations = new ArrayList();
        pseudocodeAnimations = new ArrayList();
        
        setCrane();
        setLabels();
        //getBubbleSortTransitions();
        getInsertionSortAnimations();   
    }

   
    private void getInsertionSortAnimations(){
        int j = 1; 
        
        for(int i = 1; i < numbers.size(); i++){ 
            if (j > 0){ 
                translateAnimations.add(mover.moveInX(Main.coordinates.get(j - 1), 
                        Main.coordinates.get(i - 1), rectangleAnimation1,
                        Grua1,rectangleGrua));
            } else if (j == 0){ 
                translateAnimations.add(mover.moveInX(Main.coordinates.get(j + 1), 
                        Main.coordinates.get(i - 1), 
                        rectangleAnimation1,Grua1,rectangleGrua)); 
            }
        
            translateAnimations.add(mover.moveInX(Main.coordinates.get(j), 
                    Main.coordinates.get(i), 
                    rectangleAnimation2,Grua2,rectangleGrua2)); 
        
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
                        boxes.get(j - 1), rectangleAnimation1,Grua1,rectangleGrua)); 
            
                boxes.set(j, boxes.get(j - 1)); 
                numbers.set(j, numbers.get(j - 1)); 
            
                if(j - 2 >= 0){
                    changeLabelProperties(label2, 
                            "\twhile(" + currentNumber + " < numbers[" + (j - 1) + "])\n\t\tnumbers[" + j + "] = numbers [" + (j - 1) + "]", 
                            initialStyle, finalStyle, 
                            Duration.millis(800)); 
                    
                    translateAnimations.add(mover.moveInX(Main.coordinates.get(j), 
                            Main.coordinates.get(j - 2), 
                            rectangleAnimation1,Grua1,rectangleGrua)); 
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
                    stackpane, rectangleAnimation2,Grua2,rectangleGrua2)); 
            
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
        rectangleAnimation1.setTranslateX(Main.coordinates.get(0));
        rectangleAnimation1.setTranslateY(5);
        rectangleAnimation1.setWidth(80);
        rectangleAnimation1.setHeight(20);
        rectangleAnimation1.setLayoutX(Main.squareDimension/2 - rectangleAnimation1.getWidth()/2);
        rectangleAnimation1.setFill(Color.YELLOW);
      
        rectangleAnimation2.setTranslateX(Main.coordinates.get(1));
        rectangleAnimation2.setWidth(100);
        rectangleAnimation2.setHeight(30);
        rectangleAnimation2.setFill(Color.GREEN);
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
        // Este método se encarga de establecer los tres labels que contienen el pseudocódigo del algoritmo de ordenamiento Insertion Sort.
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
        return rectangleAnimation1;
    }

    public Rectangle getRectangleAnimation2() {
        return rectangleAnimation2;
    }

    public Line getGrua1() {
        return Grua1;
    }

    public Line getGrua2() {
        return Grua2;
    }
    
}