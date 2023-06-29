package SortVisualizerCore;

import GUI.AnimationWindowController;
import GUI.Wagon;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static int windowHeight = 600;
    public static int windowWidth = 1500;

    public static ArrayList<Double> coordinates = new ArrayList();

    public static double squareDimension;
    static double separation;

    public static int modeType = 0;
    public static int sortType = 2;
    public static double size;
    public static int n;

    //Hoy es la muerte de SizeOfWagon
    public static ArrayList<StackPane> getRectangles(ArrayList<Integer> numbers) {
        n = numbers.size();
        squareDimension = 0.75 * 0.9 * windowWidth / numbers.size(); //59.55
        separation = 0.25 * 0.9 * windowWidth / numbers.size();

        squareDimension = (Main.sortType == 3) ? (0.85 * 0.9 * 910 / numbers.size()) : squareDimension;
        separation = (Main.sortType == 3) ? 1.5 : separation;

        size = NumberSize(numbers.size());
        ArrayList<StackPane> stackpanes = new ArrayList();

        for (int i = 0; i < numbers.size(); i++) {

            Canvas canvas = new Canvas(squareDimension, squareDimension);

            Wagon wagonn = new Wagon(canvas);

            wagonn.DrawWagon(canvas, squareDimension);

            //wagonn.DrawTrain(size);
            NumberDrawer numberDrawer = new NumberDrawer(canvas);
            numberDrawer.drawNumber(numbers.get(i), (Main.sortType == 3) ? (size * 1 / 0.5625) : size);

            coordinates.add(0.05 * windowWidth + (squareDimension + separation) * i);

            StackPane stackpane = new StackPane();
            stackpane.setTranslateX(0.05 * windowWidth + (squareDimension + separation) * i);
            stackpane.setTranslateY(0.65 * windowHeight);
            stackpane.setTranslateX((Main.sortType == 3) ? (0.05 * 910 + (squareDimension + separation) * i) : stackpane.getTranslateX());
            stackpane.setTranslateY((Main.sortType == 3) ? (0.45 * windowHeight) : stackpane.getTranslateY());

            stackpane.getChildren().addAll(canvas); // Agregar canvasBox al StackPane

            /*
            La variable "initialScale" representa el factor de escala inicial del objeto, 
            que en este caso es 1.0. La variable "hoverScale" representa el factor de escala al que se aumentará 
            el objeto cuando el cursor del ratón se posicione sobre él, que en este caso es 2.5.
            
            El código utiliza dos eventos del ratón: "setOnMouseEntered" y "setOnMouseExited":
            
            El primero se activa cuando el cursor del ratón entra en el área del objeto "stackpane", 
            mientras que el segundo se activa cuando el cursor del ratón sale del área del objeto.
            
            Dentro del evento "setOnMouseEntered", se establecen los valores de escala de "stackpane" a "hoverScale" 
            tanto en la dimensión horizontal como vertical, utilizando los métodos "setScaleX" y "setScaleY" del objeto 
            "stackpane".
            Dentro del evento "setOnMouseExited", se establecen los valores de escala de "stackpane" a "initialScale" 
            tanto en la dimensión horizontal como vertical, utilizando los mismos métodos mencionados anteriormente.
             */
            final double initialScale = 1.0;
            final double hoverScale = 2.5;
            stackpane.setOnMouseEntered(event -> {
                stackpane.setScaleX(hoverScale);
                stackpane.setScaleY(hoverScale);
            });
            stackpane.setOnMouseExited(event -> {
                stackpane.setScaleX(initialScale);
                stackpane.setScaleY(initialScale);
            });
            stackpanes.add(stackpane);
        }

        return stackpanes;
    }

    public static ArrayList<StackPane> getLocomotoras() {
        ArrayList<StackPane> locomotives = new ArrayList();

        StackPane stackpane = new StackPane();
        Canvas canvas1 = new Canvas(squareDimension * 2, squareDimension * 2);
        Wagon Vagon = new Wagon(canvas1);
        Vagon.DrawTrain(size);
        stackpane.setTranslateX(0.05 * 910 - (squareDimension + separation));
        stackpane.setTranslateY(0.45 * windowHeight);
        stackpane.getChildren().addAll(canvas1);

        StackPane stackpane2 = new StackPane();
        Canvas canvas2 = new Canvas(squareDimension * 1.5, squareDimension * 1);

        Wagon Vagon2 = new Wagon(canvas2);
        Vagon2.DrawTrain(size);
        //stackpane2.setTranslateX(1 * 1440 - (squareDimension + separation));
        //stackpane2.setTranslateY(-20);
        stackpane2.setTranslateX(0.05 * 910 + (squareDimension + separation) * (n));
        stackpane2.setTranslateY(0.45 * windowHeight);

        stackpane2.setRotate(180);
        stackpane2.getChildren().addAll(canvas2);

        locomotives.add(stackpane);
        locomotives.add(stackpane2);
        return locomotives;
    }

    // Método para determinar el tamaño del número a dibujar
    public static double NumberSize(int num) {
        return (0.2078 * num) + 0.3636;
    }

    /**
     *
     * Método que crea una nueva ventana de animación de ordenamiento.
     *
     * @param numbers ArrayList de números a ordenar.
     * @param stackpanes ArrayList de StackPanes que representan los valores a
     * ordenar.
     * @throws IOException Si hay un problema al cargar la ventana de animación.
     */
    public static void newAnimationWindow(ArrayList<Integer> numbers, ArrayList<StackPane> stackpanes) throws IOException {
        Stage stage = new Stage();
        AnimationWindowController windowGenerator = new AnimationWindowController(numbers, stackpanes, modeType);
        stage.setScene(windowGenerator.getScene());
        stage.setWidth(windowWidth);
        stage.setHeight(windowHeight);
        stage.setResizable(false);
        stage.setTitle("SortVisualizer");
        stage.show();
    }

    /**
     *
     * Método que se llama al iniciar la aplicación.
     *
     * @param stage Objeto Stage principal de la aplicación.
     * @throws IOException Si hay un problema al cargar la ventana de entrada.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("src/main/java/Resources/Input.fxml").toURI().toURL());
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(new File("src/main/java/Resources/Styles.css").toURI().toURL().toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("SortVisualizer");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
