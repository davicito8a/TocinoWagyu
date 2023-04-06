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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    
    ArrayList<Integer> numbers = new ArrayList();
    ArrayList<Rectangle> rectangles = new ArrayList();
    
    private final static int windowHeight = 800;
    private final static int windowWidth = 1000;
    
    public static int n = 10;
    
    public static double rectangleWidth = 0.75 * 0.9 * windowWidth / n;
    public static double rectangleMaxHeight = 0.2 * windowHeight;
    public static double separation = 0.25 * 0.9 * windowWidth / n;
    
    private int index = 0;
    
    public void llenarArreglo(){
        for(int i = 0; i < n; i++){
            numbers.add((int)(Math.random()*10 + 1));
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
            rectangle.setStrokeWidth(50/n);
            rectangles.add(rectangle);
            
            //Text number = new Text(alturas.get(i) + "");
            
            // Creación de Canvas con las mismas dimensiones del rectángulo
            Canvas numberDrawing = new Canvas(rectangleWidth, percentage * rectangleMaxHeight);
            GraphicsContext gc = numberDrawing.getGraphicsContext2D();
            gc.beginPath();
            // Empezar dibujo al medio del rectángulo
            gc.moveTo(rectangleWidth / 2, 0.3 * percentage * rectangleMaxHeight);
            // Dibujar línea, es un 1
            gc.lineTo(rectangleWidth / 2, 0.7 * percentage * rectangleMaxHeight);
            gc.stroke();
            
            // Canvas se coloca dentro de un StackPane, por lo que queda encima del rectángulo, listo para dibujar.
            StackPane stackpane = new StackPane();
            stackpane.getChildren().addAll(rectangle, numberDrawing);
            stackpanes.add(stackpane);
            stackpane.setLayoutX(0.05 * windowWidth + (rectangleWidth + separation) * i);
            stackpane.setLayoutY(50);
        }
        
        //return rectangles;
        return stackpanes;
    }
    
    public static void startVisualization(){
        
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        llenarArreglo();
       
        //ArrayList<Rectangle> rectangles = new ArrayList(getRectangles(arreglo));
        ArrayList<StackPane> stackpanes = new ArrayList(getRectangles(numbers));
 
        Group root = new Group();
        
        
        
        Button button1 = new Button("Increase");
        button1.setLayoutX(0.75 * windowWidth);
        button1.setLayoutY(0.9 * windowHeight);
        button1.setPrefWidth(75);
       
        Button button2 = new Button("Decrease");
        button2.setLayoutX(0.85 * windowWidth);
        button2.setLayoutY(0.9 * windowHeight);
        button2.setPrefWidth(75);
       
        Button button3 = new Button("Pause");
        button3.setLayoutX(0.65 * windowWidth);
        button3.setLayoutY(0.9 * windowHeight);
        button3.setPrefWidth(75);
        
        Button button4 = new Button("Play");
        button4.setLayoutX(0.55 * windowWidth);
        button4.setLayoutY(0.9 * windowHeight);
        button4.setPrefWidth(75);
        
        root.getChildren().add(button1);
        root.getChildren().add(button2);
        root.getChildren().add(button3);
        root.getChildren().add(button4);
        
       
        //root.getChildren().addAll(rectangles);
        root.getChildren().addAll(stackpanes);
        
        Scene scene = new Scene(root);
        scene.setFill(Color.GRAY);
        /*
        scene.getStylesheets().add(new File("D:\\Users\\david\\Documentos\\TocinoWagyu\\SortVisualizer\\src\\main\\java\\SortVisualizer\\Styles.css").toURI().toURL().toExternalForm());
        
        FXMLLoader fxmlLoader = new FXMLLoader(new File("D:\\Users\\david\\Documentos\\TocinoWagyu\\SortVisualizer\\src\\main\\java\\SortVisualizer\\Input.fxml").toURI().toURL());
       */
        stage.setScene(scene);
        stage.setWidth(windowWidth);
        stage.setHeight(windowHeight);
        stage.setResizable(false);
        stage.show();
        
        Stage stage2 = new Stage();
        //stage2.setScene(new Scene(fxmlLoader.load()));
       
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
        Button button5 = new Button("Forward");
        button5.setLayoutX(0.75 * windowWidth);
        button5.setLayoutY(0.9 * windowHeight);
        button5.setPrefWidth(75); 
         
        Button button6 = new Button("Backward");
        button6.setLayoutX(0.85 * windowWidth);
        button6.setLayoutY(0.9 * windowHeight);
        button6.setPrefWidth(75);
        
        root.getChildren().add(button5);
        root.getChildren().add(button6);
        
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
    
    public static void main(String[] args) {
        launch();
    }
}