package SortVisualizerCore;

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Pseudocode {
    private VBox pseudocodeBox;
    private ArrayList<Label> labels = new ArrayList();
    private final String initialStyle = "-fx-text-fill: white";
    private final String newStyle = "-fx-background-color: yellow";
    
    public Pseudocode(VBox pseudocodeBox){
        this.pseudocodeBox = pseudocodeBox;
    }
    
    public void selectInsertionSortLines(){
        addLines("for i = n", 
                "\twhile(currentNumber < numbers[j - 1])\n\t\tnumbers[j] = numbers [j - 1]", 
                "\tnumbers[j] = currentNumber");
    }
    
    public void selectBubbleSortLines(){
        addLines("for i = n", 
                "\tfor j = n2", 
                "\t\tswap(j, j + 1)");
    }
    
    public void selectCocktailSortLines(){
        addLines("Rapa y Daniel: los mejores profes",
                "Y Luis Silvestre",
                "Confirmo");
    }
    
    private void addLines(String... lines){
        for(String line: lines){
            Label label = new Label(line);
            label.setStyle(initialStyle);
            label.setFont(new Font(14));
            labels.add(label);
        }
        pseudocodeBox.getChildren().addAll(labels);
        pseudocodeBox.setLayoutX(0.0 * Main.windowWidth);
        pseudocodeBox.setLayoutY(0.8 * Main.windowHeight);
        pseudocodeBox.setStyle("-fx-background-color: black");
    }
    
    public Animation changeLabelProperties(int lineNumber, String newText){
        Timeline changeLabelPropertiesAnimation = new Timeline();
        
        if(!newText.isEmpty()){
            KeyFrame newTextFrame = new KeyFrame(Duration.millis(1), event -> labels.get(lineNumber - 1).setText(newText));
            changeLabelPropertiesAnimation.getKeyFrames().addAll(newTextFrame);
        }
        
        KeyFrame newStyleFrame = new KeyFrame(Duration.millis(1), event -> labels.get(lineNumber - 1).setStyle(newStyle));
        KeyFrame initialStyleFrame = new KeyFrame(Duration.millis(400), event -> labels.get(lineNumber - 1).setStyle(initialStyle));
        
        changeLabelPropertiesAnimation.getKeyFrames().addAll(newStyleFrame, initialStyleFrame);
        
        return changeLabelPropertiesAnimation;
    }
    
}
