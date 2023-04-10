package SortVisualizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    
    static ArrayList<Integer> numbers = new ArrayList();
    ArrayList<Rectangle> rectangles = new ArrayList();
    
    final static int windowHeight = 600;
    final static int windowWidth = 1000;
    
    public static int n = 30;
    
    public static double rectangleWidth = 0.75 * 0.9 * windowWidth / n;
    public static double rectangleMaxHeight = 0.2 * windowHeight;
    public static double separation = 0.25 * 0.9 * windowWidth / n;
    
    private int index = 0;
    
    public void llenarArreglo(){
        for(int i = 0; i < n; i++){
            
            numbers.add((int)(Math.random()*98 + 1));
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
            rectangle.setHeight(rectangleWidth);
            rectangle.setWidth(rectangleWidth);
            rectangle.setFill(Color.rgb(101, 67, 33));
            rectangle.setStroke(Color.BLACK);
            rectangle.setStrokeWidth(50/n);
            rectangles.add(rectangle);
            
            Text number = new Text(alturas.get(i) + "");
            number.setFill(Color.WHITE);
            number.setFont(Font.font(15));
            
            Canvas numberDrawing = new Canvas(rectangleWidth, rectangleWidth);
            DibujarNumeros numberDrawingg = new DibujarNumeros(numberDrawing);
            numberDrawingg.dibujarNumeros(numbers.get(i));

            StackPane stackpane = new StackPane();
            stackpane.getChildren().addAll(rectangle, numberDrawing);
            stackpanes.add(stackpane);
            stackpane.setLayoutX(0.05 * windowWidth + (rectangleWidth + separation) * i);
            stackpane.setLayoutY(0.65 * windowHeight);
        }
        
        return stackpanes;
    }
    
    public static void startVisualization(){
        
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        llenarArreglo();
        ArrayList<StackPane> stackpanes = new ArrayList(getRectangles(numbers));
 
        FXMLLoader fxmlLoader = new FXMLLoader(new File("src/main/java/SortVisualizer/Input.fxml").toURI().toURL());

        AnimationWindowGenerator windowGenerator = new AnimationWindowGenerator(stackpanes, 1);
        
        stage.setScene(windowGenerator.getScene());
        stage.setWidth(windowWidth);
        stage.setHeight(windowHeight);
        stage.setResizable(false);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }
}