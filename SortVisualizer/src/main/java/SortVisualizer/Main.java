package SortVisualizer;

import java.util.ArrayList;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    
    ArrayList<Integer> arreglo = new ArrayList();
    
    
    public final static int n = 25;
    public final static int windowHeight = 800;
    public final static int windowWidth = 1000;
    public static double rectangleWidth = 0.75 * 0.9 * windowWidth / n;
    public static double rectangleMaxHeight = 0.2 * windowHeight;
    public static double separation = 0.25 * 0.9 * windowWidth / n;
    
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
    
    public ArrayList<Rectangle> getRectangles(ArrayList<Integer> alturas) {
        ArrayList<Rectangle> rectangles = new ArrayList();
        
        double max = max(alturas);
        
        for(int i = 0; i < n; i++){
            double percentage = alturas.get(i)/max;
            Rectangle rectangle = new Rectangle();
            rectangle.setX(0.05 * windowWidth + (rectangleWidth + separation) * i);
            rectangle.setY(100);
            rectangle.setHeight(percentage * rectangleMaxHeight);
            rectangle.setWidth(rectangleWidth);
            rectangle.setFill(Color.GREEN);
            rectangles.add(rectangle);
        }
        
        return rectangles;
    }
    
    @Override
    public void start(Stage stage) throws InterruptedException {
        llenarArreglo();
       
        ArrayList<Rectangle> rectangles = new ArrayList(getRectangles(arreglo));
 
        Group root = new Group();
        
        
        
        Button button1 = new Button("Increase");
        button1.setLayoutX(0.9 * windowWidth);
        button1.setLayoutY(0.9 * windowHeight);
        
        Button button2 = new Button("Decrease");
        button2.setLayoutX(0.8 * windowWidth);
        button2.setLayoutY(0.9 * windowHeight);
        
        Label label = new Label(arreglo.toString());
        root.getChildren().add(label);
        root.getChildren().addAll(rectangles);
        root.getChildren().add(button1);
        root.getChildren().add(button2);
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setWidth(windowWidth);
        stage.setHeight(windowHeight);
        stage.show();
        
        InsertionSorter sorter = new InsertionSorter(rectangles);
        
        SequentialTransition timeline = new SequentialTransition();
        timeline.getChildren().addAll(sorter.getSortingTransitions());
        timeline.play();
        
        button1.setOnAction(event -> {
            increaseSpeed(timeline);
        });
        
        button2.setOnAction(event -> {
            decreaseSpeed(timeline);
        });
        
        
   
    }
    
    private void increaseSpeed(SequentialTransition timeline){
        timeline.setRate(timeline.getRate() * 1.5);
    }
    
    private void decreaseSpeed(SequentialTransition timeline){
        timeline.setRate(timeline.getRate() * 0.5);
    }
    
    public static void main(String[] args) {
        launch();
    }
}