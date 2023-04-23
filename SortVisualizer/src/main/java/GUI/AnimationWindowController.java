package GUI;

import SortVisualizerCore.AnimationPlayer;
import SortVisualizerCore.Animator;
import SortVisualizerCore.Main;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class AnimationWindowController {
    private final int type;
    
    private final ArrayList<Integer> numbers;
    private final ArrayList<StackPane> stackpanes;
    
    private Animator animator;
    private AnimationPlayer animationPlayer = new AnimationPlayer();
   
    private ArrayList<Animation> translateTransitions;
    private ArrayList<Animation> pseudocodeAnimations;
    private int currentTransitionIndex = 0;
  
    private Scene scene;
    private AnchorPane root;
    private VBox pseudocodeBox;
    
    private Button play = new Button("Play");
    private Button pause = new Button("Pause");
    private Button increaseSpeed = new Button("Increase");
    private Button decreaseSpeed = new Button("Decrease");
    private Button stepForward = new Button("Forward");
    private Button stepBackward = new Button("Backward");
    private int prefWidth = Main.windowWidth/15;
    
    public AnimationWindowController(ArrayList<Integer> numbers, ArrayList<StackPane> stackpanes, int type) throws IOException{
        this.type = type;
        this.numbers = numbers;
        this.stackpanes = stackpanes;
        this.animator = new Animator(this.numbers, this.stackpanes);
        start();
    }

    private void start () throws IOException{
        getTransitions();
        
        if(type == 0){
            animationPlayer.createSequentialTransitions(translateTransitions, pseudocodeAnimations);        
        }    
   
        root = new AnchorPane();
  
        BackgroundImage background = new BackgroundImage(new Image(new File("src/main/java/Resources/background.png").toURI().toURL().toExternalForm()),
        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
      
        root.getChildren().addAll(stackpanes);
       
        root.setBackground(new Background(background));
        setButtonsLayout();
        scene = new Scene(root);
        scene.getStylesheets().add(new File("src/main/java/Resources/Styles.css").toURI().toURL().toExternalForm());
     
        root.getChildren().add(pseudocodeBox);
        setCrane();
        
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
            animationPlayer.play();
        });
        
        pause.setOnAction(event -> {
            animationPlayer.pause();
        });
        
        increaseSpeed.setOnAction(event -> {
            animationPlayer.increaseSpeed();
        });
        
        decreaseSpeed.setOnAction(event -> {
            animationPlayer.decreaseSpeed();
        });
        
        stepForward.setOnAction(event -> {
            stepForward(translateTransitions, pseudocodeAnimations);
        });
        
        stepBackward.setOnAction(event -> {
            stepBackward(translateTransitions, pseudocodeAnimations);
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
        translateTransitions = animator.getTranslateAnimations();
        pseudocodeAnimations = animator.getPseudocodeAnimations();
        pseudocodeBox = animator.getPseudocodeBox();               
    }
    
    private void stepForward(ArrayList<Animation> transitions, ArrayList<Animation> pseudocodeAnimations){
        if(currentTransitionIndex <= transitions.size() - 1){
            Animation transition = transitions.get(currentTransitionIndex);
            transition.setOnFinished(e -> currentTransitionIndex++);
            transition.setRate(1);
            transition.play();
            Animation pseudocodeAnimation = pseudocodeAnimations.get(currentTransitionIndex);
            //pseudocodeAnimation.play();
        }
    }
    
    private void stepBackward(ArrayList<Animation> transitions, ArrayList<Animation> pseudocodeAnimations){
        if(currentTransitionIndex >= 1){
            Animation transition = transitions.get(currentTransitionIndex - 1);
            transition.setOnFinished(e -> currentTransitionIndex--);
            transition.setRate(-1);
            transition.play();
            Animation pseudocodeAnimation = pseudocodeAnimations.get(currentTransitionIndex);
            //pseudocodeAnimation.play();
        }  
    }
     
    public Scene getScene(){
        return scene;
    }
    
    private void setCrane(){
        Rectangle rec1 = animator.getCraneUpperBox1();
        Rectangle rec2 = animator.getCraneUpperBox2();
        Rectangle rec3 = animator.getMagnet1();
        Rectangle rec4 = animator.getMagnet2();
        
        Line l1 = animator.getRope1();
        Line l2 = animator.getRope2();

        root.getChildren().addAll(l1,l2);
        root.getChildren().addAll(rec1,rec2,rec3,rec4);
    }
}