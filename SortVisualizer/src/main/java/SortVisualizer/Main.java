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
    
    // Definimos variables estáticas para la altura y anchura de la ventana principal
    public static int windowHeight = 600;
    public static int windowWidth = 1000;
    
    // Definimos variables estáticas para el ancho del rectángulo, la altura máxima del rectángulo
    // y la separación entre ellos.
    public static double rectangleWidth;
    public static double rectangleMaxHeight;
    public static double separation;
    
    // Definimos la variable estática para el tipo de ordenamiento.
    public static int type = 0;
        
    // Método para obtener el máximo de una lista de enteros.
    public static int max (ArrayList<Integer> alturas){
        int max = alturas.get(0);
        
        for(int i = 1; i < alturas.size(); i++){
            if(max < alturas.get(i))
                max = alturas.get(i);
        }
        return max;
    }
    
    // Método para obtener una lista de StackPanes (rectángulos con números) a partir de una lista de alturas.
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
     // Método sobreescrito de la clase Application, que se ejecuta al iniciar la aplicación.
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("src/main/java/SortVisualizer/Input.fxml").toURI().toURL());
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setResizable(false);
        stage.setTitle("SortVisualizer");
        stage.show();
    }
    
/**
 * Crea y muestra una ventana de animación para visualizar el proceso de ordenamiento de una lista de enteros en una interfaz gráfica de usuario.
 * @param numbers Lista de enteros a ordenar
 * @param stackpanes Lista de StackPanes para mostrar los elementos de la lista en la animación
 * @throws MalformedURLException si ocurre un error al crear la ventana de animación
 */
public static void newAnimationWindow(ArrayList<Integer> numbers, ArrayList<StackPane> stackpanes) throws MalformedURLException{
    // Crea una nueva instancia de la clase Stage, que es una ventana en JavaFX
    Stage stage = new Stage();

    // Crea una nueva instancia de AnimationWindowGenerator, una clase que genera una ventana de animación
    // usando los números y stackpanes dados como argumentos.
    // El tercer argumento "type" podría ser una variable global que determina qué tipo de animación se muestra,
    // o podría ser un parámetro adicional que especifica el tipo de ordenamiento a realizar (por ejemplo, burbuja, selección, etc.)
    AnimationWindowGenerator windowGenerator = new AnimationWindowGenerator(numbers, stackpanes, type);

    // Establece la escena de la ventana (el contenido que se muestra dentro de la ventana)
    // utilizando el método "getScene" de AnimationWindowGenerator
    stage.setScene(windowGenerator.getScene());

    // Establece el ancho y la altura de la ventana en píxeles
    stage.setWidth(windowWidth);
    stage.setHeight(windowHeight);

    // Establece la ventana para que no se pueda redimensionar
    stage.setResizable(false);

    // Establece el título de la ventana
    stage.setTitle("SortVisualizer");

    // Muestra la ventana
    stage.show();
}

    
    public static void main(String[] args) {
        launch();
    }
}