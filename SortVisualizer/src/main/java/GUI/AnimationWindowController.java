package GUI;

import SortVisualizerCore.AnimationsPlayer;
import SortVisualizerCore.AnimationsGenerator;
import SortVisualizerCore.Main;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class AnimationWindowController {
    private final int type;

    private final ArrayList<Integer> numbers;
    private final ArrayList<StackPane> stackpanes;

    private final AnimationsGenerator animator;
    private final AnimationsPlayer animationPlayer = new AnimationsPlayer();

    private ArrayList<Animation> translateAnimations;
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
    private final int prefWidth = Main.windowWidth / 20;

    public AnimationWindowController(ArrayList<Integer> numbers, ArrayList<StackPane> stackpanes, int type) throws IOException {
        this.type = type;
        this.numbers = numbers;
        this.stackpanes = stackpanes;
        this.animator = new AnimationsGenerator(this.numbers, this.stackpanes);
        start();
    }

    private void start() throws IOException {
        getTransitions();

        Canvas canvas = new Canvas(Main.windowHeight, Main.windowWidth);
        BackGround bgc = new BackGround(canvas);

        if (Main.sortType == 3) {
            bgc.Desert(canvas);

        } else {
            bgc.drawBG(canvas);

        }

        if (type == 0) {
            animationPlayer.setAnimations(translateAnimations);
        }

        root = new AnchorPane();

        root.getChildren().add(canvas);

        if (Main.sortType != 3) {
            Crane crane = new Crane();
            root.getChildren().add(crane.getCanvas());
            setCrane();
        }

        root.getChildren().addAll(stackpanes);
        root.getChildren().add(pseudocodeBox);
        setButtonsLayout();
        scene = new Scene(root);
        scene.getStylesheets().add(new File("src/main/java/Resources/WindowStyles.css").toURI().toURL().toExternalForm());

    }

    private void setButtonsLayout() {
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
        
        play.setOnAction(event -> {
            if(animationPlayer.isReplay()){
                for(StackPane box: stackpanes){
                    box.setRotate(0);
                }
            }
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
            stepForward(translateAnimations);
        });

        stepBackward.setOnAction(event -> {
            stepBackward(translateAnimations);
        });

        root.getChildren().addAll(play, pause, increaseSpeed, decreaseSpeed, stepForward, stepBackward);

        if (type == 0) {
            stepForward.setVisible(false);
            stepBackward.setVisible(false);
        } else if (type == 1) {
            play.setVisible(false);
            pause.setVisible(false);
            increaseSpeed.setVisible(false);
            decreaseSpeed.setVisible(false);
        }

    }

    private void getTransitions() {
        translateAnimations = animator.getTranslateAnimations();
        pseudocodeBox = animator.getPseudocodeBox();
    }

    private void stepForward(ArrayList<Animation> transitions) {
        if (currentTransitionIndex <= transitions.size() - 1) {
            Animation animation = transitions.get(currentTransitionIndex);
            animation.setOnFinished(e -> currentTransitionIndex++);
            animation.setRate(1);
            animation.play();
        }
    }

    private void stepBackward(ArrayList<Animation> transitions) {
        if (currentTransitionIndex >= 1) {
            Animation animation = transitions.get(currentTransitionIndex - 1);
            animation.setOnFinished(e -> currentTransitionIndex--);
            animation.setRate(-1);
            animation.play();
        }
    }

    private void setCrane() {
        Rectangle craneUpperBox1 = animator.getCraneUpperBox1();
        Rectangle craneUpperBox2 = animator.getCraneUpperBox2();
        Rectangle magnet1 = animator.getMagnet1();
        Rectangle magnet2 = animator.getMagnet2();

        Line rope1 = animator.getRope1();
        Line rope2 = animator.getRope2();

        if (Main.sortType == 0) {
            root.getChildren().addAll(rope1, rope2);
            root.getChildren().addAll(craneUpperBox1, craneUpperBox2, magnet1, magnet2);
        } else {
            root.getChildren().add(rope2);
            root.getChildren().addAll(craneUpperBox2, magnet2);
        }
    }
    
    public Scene getScene() {
        return scene;
    }
}
