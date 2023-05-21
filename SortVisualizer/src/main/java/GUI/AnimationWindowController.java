package GUI;

import SortVisualizerCore.AnimationsPlayer;
import SortVisualizerCore.AnimationsGenerator;
import SortVisualizerCore.Main;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class AnimationWindowController {
    private final int type;
    
    private final ArrayList<Integer> numbers;
    private final ArrayList<StackPane> stackpanes;
    
    private final AnimationsGenerator animator;
    private final AnimationsPlayer animationPlayer = new AnimationsPlayer();
   
    private ArrayList<Animation> translateAnimations;
    private ArrayList<Animation> pseudocodeAnimations;
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
    private final Button changeBackground = new Button("BG");
    private final int prefWidth = Main.windowWidth/20;
    
    private int backgroundCounter = 1;
    
    public AnimationWindowController(ArrayList<Integer> numbers, ArrayList<StackPane> stackpanes, int type) throws IOException{
        this.type = type;
        this.numbers = numbers;
        this.stackpanes = stackpanes;
        this.animator = new AnimationsGenerator(this.numbers, this.stackpanes);
        start();
    }

    private void start () throws IOException{
        getTransitions();
        
        
        Canvas canvas = new Canvas(Main.windowHeight, Main.windowWidth);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        BackGround bgc = new BackGround(canvas); 
        bgc.drawBG(canvas);
 
        
        
        if(type == 0){
            animationPlayer.createSequentialTransitions(translateAnimations, pseudocodeAnimations);        
        }    
   
        root = new AnchorPane();
  
        BackgroundImage background = new BackgroundImage(new Image(new File("src/main/java/Resources/background.png").toURI().toURL().toExternalForm()),
        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
        
        Crane crane = new Crane();
        root.getChildren().add(canvas);
        root.getChildren().add(crane.getCanvas());
        root.getChildren().addAll(stackpanes);
        
       
       // root.setBackground(new Background(background));
        setButtonsLayout();
        scene = new Scene(root);
        scene.getStylesheets().add(new File("src/main/java/Resources/Styles.css").toURI().toURL().toExternalForm());
     
        root.getChildren().add(pseudocodeBox);
        setCrane();
        
    }
    
    private void setButtonsLayout(){
        play.setLayoutX(0.62 * Main.windowWidth);
        play.setLayoutY(0.85 * Main.windowHeight);
        play.setPrefWidth(prefWidth);
        
        pause.setLayoutX(0.695 * Main.windowWidth);
        pause.setLayoutY(0.85 * Main.windowHeight);
        pause.setPrefWidth(prefWidth);
        
        increaseSpeed.setLayoutX(0.77 * Main.windowWidth);
        increaseSpeed.setLayoutY(0.85 * Main.windowHeight);
        increaseSpeed.setPrefWidth(prefWidth);
        
        decreaseSpeed.setLayoutX(0.845 * Main.windowWidth);
        decreaseSpeed.setLayoutY(0.85 * Main.windowHeight);
        decreaseSpeed.setPrefWidth(prefWidth);
        
        stepForward.setLayoutX(0.75 * Main.windowWidth);
        stepForward.setLayoutY(0.85 * Main.windowHeight);
        stepForward.setPrefWidth(prefWidth);
        
        stepBackward.setLayoutX(0.85 * Main.windowWidth);
        stepBackward.setLayoutY(0.85 * Main.windowHeight);
        stepBackward.setPrefWidth(prefWidth);
        
        changeBackground.setLayoutX(0.9 * Main.windowWidth);
        changeBackground.setLayoutY(0.1 * Main.windowHeight);
        changeBackground.setPrefWidth(prefWidth);
        
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
            stepForward(translateAnimations, pseudocodeAnimations);
        });
        
        stepBackward.setOnAction(event -> {
            stepBackward(translateAnimations, pseudocodeAnimations);
        });  
        
        changeBackground.setOnAction(event -> {
            try {
                changeBackground();
            } catch (IOException ex) {
                Logger.getLogger(AnimationWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        changeBackground.setVisible(false);
        
        root.getChildren().addAll(play, pause, increaseSpeed, decreaseSpeed, stepForward, stepBackward, changeBackground);
        
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
        translateAnimations = animator.getTranslateAnimations();
        pseudocodeAnimations = animator.getPseudocodeAnimations();
        pseudocodeBox = animator.getPseudocodeBox();               
    }
    
    private void stepForward(ArrayList<Animation> transitions, ArrayList<Animation> pseudocodeAnimations){
        if(currentTransitionIndex <= transitions.size() - 1){
            Animation animation = transitions.get(currentTransitionIndex);
            animation.setOnFinished(e -> currentTransitionIndex++);
            animation.setRate(1);
            animation.play();
            /*if(pseudocodeAnimations.get(currentTransitionIndex) != null){
                Animation pseudocodeAnimation = pseudocodeAnimations.get(currentTransitionIndex);
                pseudocodeAnimation.play();
            }*/
        }
    }
    
    private void stepBackward(ArrayList<Animation> transitions, ArrayList<Animation> pseudocodeAnimations){
        if(currentTransitionIndex >= 1){
            Animation animation = transitions.get(currentTransitionIndex - 1);
            animation.setOnFinished(e -> currentTransitionIndex--);
            animation.setRate(-1);
            animation.play();
            /*if(pseudocodeAnimations.get(currentTransitionIndex - 1) != null){
                Animation pseudocodeAnimation = pseudocodeAnimations.get(currentTransitionIndex - 1);
                pseudocodeAnimation.play();
            }*/
        }  
    }
     
    private void setCrane(){
        Rectangle craneUpperBox1 = animator.getCraneUpperBox1();
        Rectangle craneUpperBox2 = animator.getCraneUpperBox2();
        Rectangle magnet1 = animator.getMagnet1();
        Rectangle magnet2 = animator.getMagnet2();
        
        Line rope1 = animator.getRope1();
        Line rope2 = animator.getRope2();
        
        /*
        root.getChildren().addAll(rope1,rope2);
        root.getChildren().addAll(craneUpperBox1,craneUpperBox2,magnet1,magnet2);*/
        
        if(Main.sortType==0){
            root.getChildren().addAll(rope1,rope2);
            root.getChildren().addAll(craneUpperBox1,craneUpperBox2,magnet1,magnet2);
        }else{
             root.getChildren().add(rope2);
            root.getChildren().addAll(craneUpperBox2,magnet2);
        }
    }

    
    private void changeBackground() throws IOException{
        BackgroundImage background = null;
        if(backgroundCounter % 2 == 1){
            background = new BackgroundImage(new Image(new File("src/main/java/Resources/background2.jpg").toURI().toURL().toExternalForm()),
            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
        } else {
            background = new BackgroundImage(new Image(new File("src/main/java/Resources/background.png").toURI().toURL().toExternalForm()),
            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
        }
        
        backgroundCounter++;
        root.setBackground(new Background(background));
    }
    
    public Scene getScene(){
        return scene;
    }
}