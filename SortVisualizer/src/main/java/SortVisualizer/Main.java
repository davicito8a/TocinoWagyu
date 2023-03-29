package SortVisualizer;

import static java.nio.file.Files.lines;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.WHITE;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    ArrayList<Integer> arreglo = new ArrayList();


    public final static int n = 8;
    public final static int windowHeight = 800;
    public final static int windowWidth = 1000;
    public static double rectangleWidth = 0.75 * 0.9 * windowWidth / n;
    public static double rectangleMaxHeight = 0.2 * windowHeight;
    public static double separation = 0.25 * 0.9 * windowWidth / n;
    public static double Velocidad = 0.5;

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
            rectangle.setFill(Color.WHITE);
             rectangles.add(rectangle);
           
        Line DiagonalDelUno = new Line();
        DiagonalDelUno.setStartY(rectangle.getY()+45);
        DiagonalDelUno.setStartX(rectangle.getX()+60);
        DiagonalDelUno.setEndX(rectangle.getX()+90);
        DiagonalDelUno.setEndY(rectangle.getY()+10);
        
    
        DiagonalDelUno.setStrokeWidth(5);
        
        
       
        Line VerticalDelUno = new Line();
        
        VerticalDelUno.setStartY(rectangle.getY()+70);
        VerticalDelUno.setStartX(rectangle.getX()+92);
        VerticalDelUno.setEndX(rectangle.getX()+92);
        VerticalDelUno.setEndY(rectangle.getY()+10);
        VerticalDelUno.setStrokeWidth(5);    
        
        
            
            //DrawingNumbers numero  = new DrawingNumbers();
            
            
            
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
        scene.setFill(BLACK);
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