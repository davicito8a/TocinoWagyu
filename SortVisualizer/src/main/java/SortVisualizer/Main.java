package SortVisualizer;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    
    public static int windowHeight = 600;
    public static int windowWidth = 1000;
    
    public static double rectangleWidth;
    public static double rectangleMaxHeight;
    public static double separation;
    
    public static int type = 0;
        
    public static int max (ArrayList<Integer> alturas){
        int max = alturas.get(0);
        
        for(int i = 1; i < alturas.size(); i++){
            if(max < alturas.get(i))
                max = alturas.get(i);
        }
        return max;
    }
    
    public static ArrayList<StackPane> getRectangles(ArrayList<Integer> alturas) {
        rectangleWidth = 0.75 * 0.9 * windowWidth / alturas.size();
        rectangleMaxHeight = 0.2 * windowHeight;
        separation = 0.25 * 0.9 * windowWidth / alturas.size();
        
        ArrayList<StackPane> stackpanes = new ArrayList();
        
        double max = max(alturas);
        
        for(int i = 0; i < alturas.size(); i++){
            double percentage = alturas.get(i)/max;
            
            Rectangle rectangle = new Rectangle();
            rectangle.setHeight(rectangleWidth);
            rectangle.setWidth(rectangleWidth);
            rectangle.setFill(Color.rgb(101, 67, 33));
            rectangle.setStroke(Color.BLACK);
            rectangle.setStrokeWidth(50/alturas.size());
            
            Text number = new Text(alturas.get(i) + "");
            number.setFill(Color.WHITE);
            number.setFont(Font.font(15));
            
            Canvas numberDrawing = new Canvas(rectangleWidth, rectangleWidth);
            DibujarNumeros numberDrawingg = new DibujarNumeros(numberDrawing);
            numberDrawingg.dibujarNumeros(alturas.get(i));
            
            StackPane stackpane = new StackPane();
            stackpane.getChildren().addAll(rectangle, numberDrawing);
            stackpanes.add(stackpane);
            stackpane.setLayoutX(0.05 * windowWidth + (rectangleWidth + separation) * i);
            stackpane.setLayoutY(0.65 * windowHeight);
        }
        
        return stackpanes;
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("src/main/java/SortVisualizer/Input.fxml").toURI().toURL());
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setResizable(false);
        stage.setTitle("SortVisualizer");
        stage.show();
    }
    
    public static void newAnimationWindow(ArrayList<Integer> numbers, ArrayList<StackPane> stackpanes) throws MalformedURLException{
        Stage stage = new Stage();
        AnimationWindowGenerator windowGenerator = new AnimationWindowGenerator(numbers, stackpanes, type);
        stage.setScene(windowGenerator.getScene());
        stage.setWidth(windowWidth);
        stage.setHeight(windowHeight);
        stage.setResizable(false);
        stage.setTitle("SortVisualizer");
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }
}