package SortVisualizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static javafx.animation.Animation.Status.RUNNING;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    
    ArrayList<Integer> arreglo = new ArrayList();
    ArrayList<Rectangle> rectangles = new ArrayList();
    
    
    public final static int n = 10;
    public final static int windowHeight = 800;
    public final static int windowWidth = 1000;
    public static double rectangleWidth = 0.75 * 0.9 * windowWidth / n;
    public static double rectangleMaxHeight = 0.2 * windowHeight;
    public static double separation = 0.25 * 0.9 * windowWidth / n;
    private int index = 0;
    
    public void llenarArreglo(){
        for(int i = 0; i < n; i++){
            arreglo.add((int)(Math.random()*10 + 1));
        }
    }
    
    public int max (ArrayList<Integer> alturas){
        int max = alturas.get(0);
        
        for(int i = 1; i < alturas.size(); i++){
            if(max < alturas.get(i))
                max = alturas.get(i);
        }
        return max;
    }
    
    public ArrayList<StackPane> getRectangles(ArrayList<Integer> alturas) {
        
        ArrayList<StackPane> stackpanes = new ArrayList();
        
        double max = max(alturas);
        
        for(int i = 0; i < n; i++){
            double percentage = alturas.get(i)/max;
            
            Rectangle rectangle = new Rectangle();
            //rectangle.setX(0.05 * windowWidth + (rectangleWidth + separation) * i);
            //rectangle.setY(100);
            rectangle.setHeight(percentage * rectangleMaxHeight);
            rectangle.setWidth(rectangleWidth);
            rectangle.setFill(Color.WHITE);
            rectangle.setStroke(Color.BLACK);
            rectangle.setStrokeWidth(5);
            rectangles.add(rectangle);
            
            Text number = new Text(alturas.get(i) + "");
            
            StackPane stackpane = new StackPane();
            stackpane.getChildren().addAll(rectangle, number);
            stackpanes.add(stackpane);
            stackpane.setLayoutX(0.05 * windowWidth + (rectangleWidth + separation) * i);
            stackpane.setLayoutY(100);
            
        }
        
        //return rectangles;
        return stackpanes;
    }
    
    @Override
    public void start(Stage stage) throws InterruptedException, IOException {
        
        String style = "-fx-background-color: slateblue; -fx-text-fill: white";
        llenarArreglo();
       
        //ArrayList<Rectangle> rectangles = new ArrayList(getRectangles(arreglo));
        ArrayList<StackPane> stackpanes = new ArrayList(getRectangles(arreglo));
 
        Group root = new Group();
        
        
        
        Button button1 = new Button("Increase");
        button1.setLayoutX(0.9 * windowWidth);
        button1.setLayoutY(0.9 * windowHeight);
   
        
        Button button2 = new Button("Decrease");
        button2.setLayoutX(0.8 * windowWidth);
        button2.setLayoutY(0.9 * windowHeight);
       
        
        Button button3 = new Button("Pause");
        button3.setLayoutX(0.7 * windowWidth);
        button3.setLayoutY(0.9 * windowHeight);
        
        Button button4 = new Button("Play");
        button4.setLayoutX(0.6 * windowWidth);
        button4.setLayoutY(0.9 * windowHeight);
       
        
        Button button5 = new Button("Forward");
        button5.setLayoutX(0.8 * windowWidth);
        button5.setLayoutY(0.8 * windowHeight);

        
       
       
        
        Label label = new Label(arreglo.toString());
        root.getChildren().add(label);
        //root.getChildren().addAll(rectangles);
        root.getChildren().addAll(stackpanes);
        root.getChildren().add(button1);
        root.getChildren().add(button2);
        root.getChildren().add(button3);
        root.getChildren().add(button4);
        root.getChildren().add(button5);
        
        Scene scene = new Scene(root);
        scene.setFill(Color.GRAY);
        scene.getStylesheets().add(new File("Styles.css").toURI().toURL().toExternalForm());
        
        
        Button button6 = new Button("Backward");
        button6.setLayoutX(0.9 * windowWidth);
        button6.setLayoutY(0.8 * windowHeight);
       
       
        
        root.getChildren().add(button6);
        button6.getStyleClass().add("button");
        
        FXMLLoader fxmlLoader = new FXMLLoader(new File("Input.fxml").toURI().toURL());
        stage.setScene(new Scene(fxmlLoader.load()));
        
        
        
        stage.setScene(scene);
        stage.setWidth(windowWidth);
        stage.setHeight(windowHeight);
        stage.setResizable(false);
        
      
        stage.show();
        
        /*
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(new Group()));
        stage2.show();
        */
        
        InsertionSorter sorter = new InsertionSorter(rectangles, stackpanes);
        
        ArrayList<Transition> transitions = sorter.getSortingTransitions();
        
        
        
        SequentialTransition timeline = new SequentialTransition();
        timeline.getChildren().addAll(transitions);
        
        
        
        button1.setOnAction(event -> {
            increaseSpeed(timeline);
        });
        
        button2.setOnAction(event -> {
            decreaseSpeed(timeline);
        });
        
        button3.setOnAction(event -> {
            pause(timeline);
        });
        
         button4.setOnAction(event -> {
            play(timeline);
        });
        
        

        
       
       /*
       
        button5.setOnAction(event -> {
            stepForward(transitions);
        });
        
        button6.setOnAction(event -> {
            stepBackward(transitions);
        });
*/




        


  
    }
    
    private void increaseSpeed(SequentialTransition timeline){
        if(timeline.getStatus().equals(RUNNING))
            timeline.setRate(timeline.getRate() * 1.5);
    }
    
    private void decreaseSpeed(SequentialTransition timeline){
        if(timeline.getStatus().equals(RUNNING))
            timeline.setRate(timeline.getRate() * 0.5);
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
    
    public static void main(String[] args) {
        launch();
    }
}