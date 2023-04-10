package com.mycompany.trymuestracodigo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

//Este es el de la Carpeta Git
public class App extends Application {
    
    public static int recAltura = 250;
    public static int recAncho = 350;
    public static Color ColorFondo = Color.BLUE;
    public static Color ColorRec = Color.RED;
    public static Color ColorLetra = Color.WHITE;
    public static Color ColorLetra2 = Color.GOLD;
    private Label[] pasos = new Label[7];
    private int cont = 1;
    private int contador_indice = 0;
    
@Override
public void start(Stage stage) {
    AnchorPane ventana = new AnchorPane();

    // Etiquetas
    pasos[0] = new Label("mark first element as sorted");
    pasos[1] = new Label("for each unsorted element X");
    pasos[2] = new Label("'extract' the element X");
    pasos[3] = new Label("for j = lastSortedIndex down to 0");
    pasos[4] = new Label("if current element j > X");
    pasos[5] = new Label("move sorted element to the right by 1");
    pasos[6] = new Label(" break loop and insert X here");

    // Color de Las Etiquetas:
    String coloretiqueta = "-fx-background-color: #32CD32; -fx-text-fill: white";
    for (Label step : pasos) {
        step.setStyle(coloretiqueta);
    }

    // Usando TimeLine
    VBox root = new VBox(pasos);
    Scene escena = new Scene(ventana);

    ventana.getChildren().add(root);
    ventana.setStyle("-fx-background-color:#9E0015");

    // root.getChildren().add(rec2);
    Timeline grafica = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
        pasos[0].setText("mark first element as sorted ");
        DestacarLinea(0);
    }), new KeyFrame(Duration.seconds(2), event -> DestacarLinea(1)),
            new KeyFrame(Duration.seconds(3), event -> DestacarLinea(2)),
            new KeyFrame(Duration.seconds(4), event -> DestacarLinea(3)),
            new KeyFrame(Duration.seconds(5), event -> DestacarLinea(4)),
            new KeyFrame(Duration.seconds(6), event -> DestacarLinea(5)),
            new KeyFrame(Duration.seconds(7), event -> DestacarLinea(6)));

    grafica.setCycleCount(Timeline.INDEFINITE);
    grafica.play();

    // Coordenadas de Etiquetas

    // Tamaño letra etiqueta
    pasos[0].setFont(new Font("Georgia", 20));
    pasos[1].setFont(new Font("Georgia", 20));
    pasos[2].setFont(new Font("Georgia", 20));
    pasos[3].setFont(new Font("Georgia", 20));
    pasos[4].setFont(new Font("Georgia", 20));
    pasos[5].setFont(new Font("Georgia", 20));
    pasos[6].setFont(new Font("Georgia", 20));
    // Agregando a la Escena
    stage.setScene(escena);
    stage.setWidth(recAncho);
    stage.setHeight(recAltura);
    stage.setTitle("Intento Muestra Codigo");
    stage.show();
}

public void DestacarLinea(int posicion) {
    if (contador_indice >= 0 && contador_indice < pasos.length) {
        pasos[contador_indice].setStyle("-fx-background-color: #32CD32; -fx-text-fill: white");
    }
    if (posicion >= 0 && posicion < pasos.length) {
        pasos[posicion].setStyle("-fx-background-color: green; -fx-text-fill: gold");
        contador_indice = posicion;
    }
}

public static void main(String[] args) {
    launch();
}

}