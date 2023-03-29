package SortVisualizer;

import java.util.ArrayList;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
            //No queremos distintos tamaños de rectangulos(btw queremos cuadrados )
            rectangle.setHeight( rectangleMaxHeight);
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
        
        Button button = new Button();
        button.setLayoutX(0.9 * windowWidth);
        button.setLayoutY(0.9 * windowHeight);
        button.setOnAction(event -> {
            switchScene(stage);
        });
        
        Label label = new Label(arreglo.toString());
        root.getChildren().add(label);
        root.getChildren().addAll(rectangles);
        root.getChildren().add(button);
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setWidth(windowWidth);
        stage.setHeight(windowHeight);
        stage.show();
        
        InsertionSorter sorter = new InsertionSorter(rectangles);
        
        SequentialTransition timeline = new SequentialTransition();
        timeline.getChildren().addAll(sorter.getSortingTransitions());
        timeline.play();
        
        
        
        
    }
    
    private void switchScene(Stage stage){
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}