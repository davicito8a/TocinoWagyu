package SortVisualizer;

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Pseudocode {
    private ArrayList<Integer> numbers = new ArrayList();
    private VBox pseudocodeBox = new VBox();
    private Label l1, l2, l3, l4;
    private SequentialTransition sq = new SequentialTransition();
    private ArrayList<Animation> animations = new ArrayList();
    
    public Pseudocode(ArrayList<Integer> numbers){
        this.numbers = numbers;
        l1 = new Label("for i = a");
        l2 = new Label("\twhile(currentNumber < numbers[j - 1])\n\t\tnumbers[j] = numbers [j - 1]");
        l4 = new Label("\tnumbers[j] = currentNumber");
       
       
        pseudocodeBox.getChildren().addAll(l1, l2, l4);
        pseudocodeBox.setLayoutX(0.1 * Main.windowWidth);
        pseudocodeBox.setLayoutY(0.1 * Main.windowHeight);
    }
    
    public SequentialTransition getPseudocodeTransitions(){
        int j;
        int currentNumber;
        for(int i = 1; i < numbers.size(); i++){
            j = i;
            currentNumber = numbers.get(i);
            final int currentNumber2 = currentNumber;
            final int finalI = i;
            Timeline move = new Timeline(
            new KeyFrame(Duration.millis(1), evt -> l1.setStyle("-fx-background-color: yellow;")),
            new KeyFrame(Duration.millis(1), evt -> l1.setText("for i = " + finalI)),
            new KeyFrame(Duration.millis(400), evt -> l1.setStyle("")));
           
            sq.getChildren().add(move);
           
           
            while(j > 0 && currentNumber < numbers.get(j - 1)){ 
                final int h = j;
                Timeline move2 = new Timeline(
                    new KeyFrame(Duration.millis(1), evt -> l2.setStyle("-fx-background-color: yellow;")),
                    new KeyFrame(Duration.millis(1), evt -> l2.setText("\twhile(" + currentNumber2 + " < numbers[" + (h - 1) + "])\n\t\tnumbers[" + (h) + "] = numbers [" + (h - 1) + "]")),
                    new KeyFrame(Duration.millis(400), evt -> l2.setStyle("")));
                System.out.println(move2);
                
                
                numbers.set(j, numbers.get(j - 1));
                sq.getChildren().add(move2);
                j--;   
               
            }
            
            final int b = j;
            
            
           
         
            Timeline move3 = new Timeline(
                    new KeyFrame(Duration.millis(1), evt -> l4.setStyle("-fx-background-color: yellow;")),
                    new KeyFrame(Duration.millis(1), evt -> l4.setText("\tnumbers[" + b + "]" + " = " + currentNumber2)),
                    new KeyFrame(Duration.millis(800), evt -> l4.setStyle("")));
                    sq.getChildren().add(move3);
            numbers.set(j, currentNumber);
            
        }
  
     
        return sq;
    }
    
    public VBox getVBox(){
        return pseudocodeBox;
        
    }
}