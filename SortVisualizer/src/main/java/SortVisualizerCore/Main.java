package SortVisualizerCore;

import GUI.AnimationWindowController;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;

public class Main extends Application {
    
    // Variables de tamaño de ventana
    public static int windowHeight = 600;
    public static int windowWidth = 1500;
    
    // Lista de coordenadas de los rectángulos
    public static ArrayList<Double> coordinates = new ArrayList();
    
    // Dimensiones de los cuadrados
    static double squareDimension;
    static double separation;
    
    // Variable para indicar el tipo de ordenamiento
    public static int type = 0;

    // Método para generar la lista de StackPanes (contenedores para los rectángulos y números)
    public static ArrayList<StackPane> getRectangles(ArrayList<Integer> numbers) {
        squareDimension = 0.75 * 0.9 * windowWidth / numbers.size();
        separation = 0.25 * 0.9 * windowWidth / numbers.size();
        int size = NumberSize(numbers.size());
        ArrayList<StackPane> stackpanes = new ArrayList();
        
        // Para cada número, se genera un rectángulo con un número dentro y se agrega a un StackPane
        for(int i = 0; i < numbers.size(); i++){
            Rectangle rectangle = new Rectangle();
            DropShadow shadow = new DropShadow();
            shadow.setRadius(10.0);
            shadow.setOffsetX(5.0);
            shadow.setOffsetY(5.0);
            shadow.setColor(Color.BLACK);
            rectangle.setEffect(shadow);
            rectangle.setHeight(squareDimension);
            rectangle.setWidth(squareDimension);
            rectangle.setFill(Color.rgb(101, 67, 33));
            rectangle.setStroke(Color.BLACK);
            rectangle.setStrokeWidth(50/numbers.size());

            Canvas canvas = new Canvas(squareDimension, squareDimension);
            NumberDrawer numberDrawer = new NumberDrawer(canvas);
            numberDrawer.drawNumber(numbers.get(i),size);
            
            coordinates.add(0.05 * windowWidth + (squareDimension + separation) * i);
            
            StackPane stackpane = new StackPane();
            stackpane.setTranslateX(0.05 * windowWidth + (squareDimension + separation) * i);
            stackpane.setTranslateY(0.65 * windowHeight);
            stackpane.getChildren().addAll(rectangle, canvas);
            stackpanes.add(stackpane);
        }
        
        return stackpanes;
    }

    // Método para determinar el tamaño del número a dibujar
    public static int NumberSize(int num) {
        if (num >= 0 && num < 10) {
            return 1; 
        } else if (num < 20) {
            return 2; 
        } else if (num < 30) {
            return 3; 
        } else if (num <= 48) {
            return 5; 
        } else if (num <= 70) {
            return 8; 
        } else {
            return 10; 
        }
    }
     // Método sobreescrito de la clase Application, que se ejecuta al iniciar la aplicación.
    
    /**

    Método que crea una nueva ventana de animación de ordenamiento.
    @param numbers ArrayList de números a ordenar.
    @param stackpanes ArrayList de StackPanes que representan los valores a ordenar.
    @throws IOException Si hay un problema al cargar la ventana de animación.
    */
    public static void newAnimationWindow(ArrayList<Integer> numbers, ArrayList<StackPane> stackpanes) throws IOException{
        Stage stage = new Stage();
        AnimationWindowController windowGenerator = new AnimationWindowController(numbers, stackpanes, type);
        stage.setScene(windowGenerator.getScene());
        stage.setWidth(windowWidth);
        stage.setHeight(windowHeight);
        stage.setResizable(false);
        stage.setTitle("SortVisualizer");
        stage.show();
    }
    /**

    Método que se llama al iniciar la aplicación.
    @param stage Objeto Stage principal de la aplicación.
    @throws IOException Si hay un problema al cargar la ventana de entrada.
    */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("src/main/java/Resources/Input.fxml").toURI().toURL());
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setResizable(false);
        stage.setTitle("SortVisualizer");
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }
}