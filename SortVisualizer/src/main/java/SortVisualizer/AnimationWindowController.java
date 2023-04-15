package SortVisualizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.Animation;
import static javafx.animation.Animation.Status.RUNNING;
import javafx.animation.SequentialTransition;
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
import javafx.scene.layout.VBox;

public class AnimationWindowController {
    private final int type;
   
    private final ArrayList<Integer> numbers;
    private final ArrayList<StackPane> stackpanes;
    private ArrayList<TranslateTransition> translateTransitions;
    private ArrayList<Animation> pseudocodeAnimations;
    private SequentialTransition sequentialTranslateTransitions;
    private SequentialTransition sequentialPseudocodeAnimations = new SequentialTransition();
    private int currentTransitionIndex = 0;
    
    private Scene scene;
    private AnchorPane root;
    private VBox pseudocodeBox;
    
    private final Button play = new Button("Play");
    private final Button pause = new Button("Pause");
    private final Button increaseSpeed = new Button("Increase");
    private final Button decreaseSpeed = new Button("Decrease");
    private final Button stepForward = new Button("Forward");
    private final Button stepBackward = new Button("Backward");
    private final int prefWidth = Main.windowWidth/15;
    
    public AnimationWindowController(ArrayList<Integer> numbers, ArrayList<StackPane> stackpanes, int type) throws IOException{
        this.numbers = numbers;
        this.stackpanes = stackpanes;
        this.type = type;
        start();
    }
    
    private void start () throws IOException{
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
        root.getChildren().add(pseudocodeBox);
        
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
            play(sequentialTranslateTransitions);
        });
        
        pause.setOnAction(event -> {
            pause(sequentialTranslateTransitions);
        });
        
        increaseSpeed.setOnAction(event -> {
            increaseSpeed(sequentialTranslateTransitions);
        });
        
        decreaseSpeed.setOnAction(event -> {
            decreaseSpeed(sequentialTranslateTransitions);
        });
        
        stepForward.setOnAction(event -> {
            stepForward(translateTransitions);
        });
        
        stepBackward.setOnAction(event -> {
            stepBackward(translateTransitions);
        });   
        
        root.getChildren().addAll(play, pause, increaseSpeed, decreaseSpeed, stepForward, stepBackward);
        
        if(type == 0){
            stepForward.setVisible(false);
            stepBackward.setVisible(false);
        } else if(type == 1) {
            play.setVisible(false);
            pause.setVisible(false);
            increaseSpeed.setVisible(false);
            decreaseSpeed.setVisible(false);
        }
        
    }
    
    private void getTransitions(){
        Animator animator = new Animator(numbers, stackpanes);
        translateTransitions = animator.getTranslateTransitions();
        pseudocodeBox = animator.getPseudocodeBox();
        sequentialPseudocodeAnimations.getChildren().addAll(animator.getPseudocodeAnimations());
       
    }
    
    private void createTimeline(){
        sequentialTranslateTransitions = new SequentialTransition();
        sequentialTranslateTransitions.getChildren().addAll(translateTransitions);
    }
    
    private void increaseSpeed(SequentialTransition timeline){
        if(timeline.getStatus().equals(RUNNING)){
            timeline.setRate(timeline.getRate() * 1.25);
            sequentialPseudocodeAnimations.setRate(sequentialPseudocodeAnimations.getRate() * 1.25);
        }
    }
    
    private void decreaseSpeed(SequentialTransition timeline){
        if(timeline.getStatus().equals(RUNNING)){
            timeline.setRate(timeline.getRate() * 0.8);
            sequentialPseudocodeAnimations.setRate(sequentialPseudocodeAnimations.getRate() * 0.8);
        }
    }
    
    
    private void pause(SequentialTransition timeline){
        timeline.pause();
        sequentialPseudocodeAnimations.pause();
    }
    
    private void play(SequentialTransition timeline){
        timeline.play();
        sequentialPseudocodeAnimations.play();
    }
    
    private void stepForward(ArrayList<TranslateTransition> transitions){
        if(currentTransitionIndex <= transitions.size() - 1){
            TranslateTransition transition = transitions.get(currentTransitionIndex);
            transition.setOnFinished(e -> currentTransitionIndex++);
            transition.setRate(1);
            transition.play();
        }
    }
    
    private void stepBackward(ArrayList<TranslateTransition> transitions){
        if(currentTransitionIndex >= 1){
            TranslateTransition transition = transitions.get(currentTransitionIndex - 1);
            transition.setOnFinished(e -> currentTransitionIndex--);
            transition.setRate(-1);
            transition.play();
        }  
    }
     
    public Scene getScene(){
        return scene;
    }
    
}