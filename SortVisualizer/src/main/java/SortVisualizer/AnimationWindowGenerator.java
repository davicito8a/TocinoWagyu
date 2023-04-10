package SortVisualizer;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import static javafx.animation.Animation.Status.RUNNING;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;

public class AnimationWindowGenerator {
    private final int type;
   
    private final ArrayList<StackPane> stackpanes;
    private ArrayList<Transition> transitions;
    private SequentialTransition timeline;
    private int index = 0;
    
    private Scene scene;
    private AnchorPane root;
    
    private final Button play = new Button("Play");
    private final Button pause = new Button("Pause");
    private final Button increaseSpeed = new Button("Increase");
    private final Button decreaseSpeed = new Button("Decrease");
    private final Button stepForward = new Button("Forward");
    private final Button stepBackward = new Button("Backward");
    private final int prefWidth = 75;
    
    public AnimationWindowGenerator(ArrayList<StackPane> stackpanes, int type) throws MalformedURLException{
        this.stackpanes = stackpanes;
        this.type = type;
        start();
    }
    
    private void start () throws MalformedURLException{
        getTransitions();
        
        if(type == 0){
            createTimeline();
        } 
        
        root = new AnchorPane();
        BackgroundImage background = new BackgroundImage(new Image(new File("src/main/java/SortVisualizer/background.png").toURI().toURL().toExternalForm()),
        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
        root.getChildren().addAll(stackpanes);
        root.setBackground(new Background(background));
        setButtonsLayout();
        scene = new Scene(root);
        scene.getStylesheets().add(new File("src/main/java/SortVisualizer/Styles.css").toURI().toURL().toExternalForm());
    }
    
    private void setButtonsLayout(){
        play.setLayoutX(0.55 * Main.windowWidth);
        play.setLayoutY(0.85 * Main.windowHeight);
        play.setPrefWidth(prefWidth);
        
        pause.setLayoutX(0.65 * Main.windowWidth);
        pause.setLayoutY(0.85 * Main.windowHeight);
        pause.setPrefWidth(prefWidth);
        
        increaseSpeed.setLayoutX(0.75 * Main.windowWidth);
        increaseSpeed.setLayoutY(0.85 * Main.windowHeight);
        increaseSpeed.setPrefWidth(prefWidth);
        
        decreaseSpeed.setLayoutX(0.85 * Main.windowWidth);
        decreaseSpeed.setLayoutY(0.85 * Main.windowHeight);
        decreaseSpeed.setPrefWidth(prefWidth);
        
        stepForward.setLayoutX(0.75 * Main.windowWidth);
        stepForward.setLayoutY(0.85 * Main.windowHeight);
        stepForward.setPrefWidth(prefWidth);
        
        stepBackward.setLayoutX(0.85 * Main.windowWidth);
        stepBackward.setLayoutY(0.85 * Main.windowHeight);
        stepBackward.setPrefWidth(prefWidth);
        
        play.setOnAction(event -> {
            play(timeline);
        });
        
        pause.setOnAction(event -> {
            pause(timeline);
        });
        
        increaseSpeed.setOnAction(event -> {
            increaseSpeed(timeline);
        });
        
        decreaseSpeed.setOnAction(event -> {
            decreaseSpeed(timeline);
        });
        
        stepForward.setOnAction(event -> {
            stepForward(transitions);
        });
        
        stepBackward.setOnAction(event -> {
            stepBackward(transitions);
        });   
        
        root.getChildren().addAll(play, pause, increaseSpeed, decreaseSpeed, stepForward, stepBackward);
        
        if(type == 0){
            stepForward.setVisible(false);
            stepBackward.setVisible(false);
        } else {
            play.setVisible(false);
            pause.setVisible(false);
            increaseSpeed.setVisible(false);
            decreaseSpeed.setVisible(false);
        }
    }
    
    private void getTransitions(){
        InsertionSorter sorter = new InsertionSorter(Main.numbers, stackpanes);
        transitions = sorter.getSortingTransitions();
    }
    
    private void createTimeline(){
        timeline = new SequentialTransition();
        timeline.getChildren().addAll(transitions);
    }
    
    private void increaseSpeed(SequentialTransition timeline){
        if(timeline.getStatus().equals(RUNNING))
            timeline.setRate(timeline.getRate() * 1.25);
    }
    
    private void decreaseSpeed(SequentialTransition timeline){
        if(timeline.getStatus().equals(RUNNING))
            timeline.setRate(timeline.getRate() * 0.8);
    }
    
    private void pause(SequentialTransition timeline){
        timeline.pause();
    }
    
    private void play(SequentialTransition timeline){
        timeline.play();   
    }
    
    private void stepForward(ArrayList<Transition> transitions){
        if(index <= transitions.size() - 1){
            TranslateTransition transition = (TranslateTransition) transitions.get(index);
            transition.setOnFinished(e -> index++);
            transition.play();
        }
    }
    
    private void stepBackward(ArrayList<Transition> transitions){
        if(index >= 1){
            TranslateTransition transition = (TranslateTransition) transitions.get(index - 1);
            transition.setOnFinished(e -> {
                transition.setByX(-1 * transition.getByX());
                transition.setByY(-1 * transition.getByY());
                index--;
            });
            transition.setByX(-1 * transition.getByX());
            transition.setByY(-1 * transition.getByY());
            transition.play();
        }  
    }
    
    public Scene getScene(){
        return scene;
    }
}