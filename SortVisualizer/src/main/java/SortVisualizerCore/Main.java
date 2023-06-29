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

            NumberDrawer numberDrawer = new NumberDrawer(canvas);
            numberDrawer.drawNumber(numbers.get(i), Main.squareDimension / 540); //43.5 / 0.08

            coordinates.add(0.05 * windowWidth + (squareDimension + separation) * i);

            StackPane stackpane = new StackPane();
            stackpane.setTranslateX(0.05 * windowWidth + (squareDimension + separation) * i);
            stackpane.setTranslateY(0.65 * windowHeight);
            stackpane.setTranslateX((Main.sortType == 3) ? (0.05 * 910 + (squareDimension + separation) * i) : stackpane.getTranslateX());
            stackpane.setTranslateY((Main.sortType == 3) ? (0.45 * windowHeight) : stackpane.getTranslateY());

            stackpane.getChildren().addAll(canvas); // Agregar canvasBox al StackPane

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
        Wagon vagon = new Wagon(canvas1);
        vagon.DrawTrain(size);
        stackpane.setTranslateX(0.05 * 910 - (squareDimension + separation));
        stackpane.setTranslateY(0.45 * windowHeight);
        stackpane.getChildren().addAll(canvas1);

        StackPane stackpane2 = new StackPane();
        Canvas canvas2 = new Canvas(squareDimension * 1.5, squareDimension * 1);

        Wagon vagon2 = new Wagon(canvas2);
        vagon2.DrawTrain(size);
        stackpane2.setTranslateX(0.05 * 910 + (squareDimension + separation) * (n));
        stackpane2.setTranslateY(0.45 * windowHeight);

        stackpane2.setRotate(180);
        stackpane2.getChildren().addAll(canvas2);

        locomotives.add(stackpane);
        locomotives.add(stackpane2);
        return locomotives;
    }

    public static double NumberSize(int num) {
        return (0.2078 * num) + 0.3636;
    }

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
