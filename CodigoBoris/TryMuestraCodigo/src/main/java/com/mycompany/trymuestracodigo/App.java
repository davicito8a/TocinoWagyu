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
        Rectangle rec = new Rectangle();
        Rectangle rec2 = new Rectangle();
        Rectangle rec3 = new Rectangle();
        Rectangle rec4 = new Rectangle();
        Rectangle rec5 = new Rectangle();
        Rectangle rec6 = new Rectangle();
        //Scene escena = new Scene(ventana);
        //escena.setFill(ColorFondo);
        
        //Crear Rectángulo Grande
        rec.setX(0);
        rec.setY(0);
        rec.setWidth(400);
        rec.setHeight(400);
        rec.setFill(Color.BLUE);
        
        //Rec Pequeños
        rec2.setX(0);
        rec2.setY(0);
        rec2.setWidth(30);
        rec2.setHeight(60);
        rec2.setFill(ColorRec);
        
        rec3.setX(50);
        rec3.setY(110);
        rec3.setWidth(340);
        rec3.setHeight(60);
        rec3.setFill(ColorRec);
        
        rec4.setX(50);
        rec4.setY(170);
        rec4.setWidth(340);
        rec4.setHeight(60);
        rec4.setFill(ColorRec);
        
        rec5.setX(50);
        rec5.setY(230);
        rec5.setWidth(340);
        rec5.setHeight(60);
        rec5.setFill(ColorRec);
        
        rec6.setX(50);
        rec6.setY(290);
        rec6.setWidth(340);
        rec6.setHeight(60);
        rec6.setFill(ColorRec);

        //Etiquetas
        pasos[0] = new Label("mark first element as sorted");
        pasos[1] = new Label("for each unsorted element X");
        pasos[2] = new Label("'extract' the element X");
        pasos[3] = new Label("for j = lastSortedIndex down to 0");
        pasos[4] = new Label("if current element j > X");
        pasos[5] = new Label("move sorted element to the right by 1");
        pasos[6] = new Label(" break loop and insert X here");
        
        //Color de Las Etiquetas:
        String coloretiqueta = "-fx-background-color: red; -fx-text-fill: gold";
        for (Label step:pasos) {
            step.setStyle(coloretiqueta);
        }
        
        //Usando TimeLine
        VBox root = new VBox(pasos);
        Scene escena = new Scene(ventana);
        
        
        ventana.getChildren().add(root);
        ventana.setStyle("-fx-background-color:orange");
        
        //root.getChildren().add(rec2);
        Timeline grafica = new Timeline(new KeyFrame(Duration.seconds(1), event->{
                           pasos[0].setText("mark first element as sorted ");
                           DestacarLinea(0);      
                           }),
                           new KeyFrame(Duration.seconds(2),event->DestacarLinea(1)),
                           new KeyFrame(Duration.seconds(3),event->DestacarLinea(2)), 
                           new KeyFrame(Duration.seconds(4),event->DestacarLinea(3)),
                           new KeyFrame(Duration.seconds(5),event->DestacarLinea(4)),
                           new KeyFrame(Duration.seconds(6),event->DestacarLinea(5)),
                           new KeyFrame(Duration.seconds(7),event->DestacarLinea(6)));
        
        grafica.setCycleCount(Timeline.INDEFINITE);
        grafica.play();
        
        
        //Coordenadas de Etiquetas
         
        //Tamaño letra etiqueta
        pasos[0].setFont(new Font("Georgia",20));
        pasos[1].setFont(new Font("Georgia",20));
        pasos[2].setFont(new Font("Georgia",20));
        pasos[3].setFont(new Font("Georgia",20));
        pasos[4].setFont(new Font("Georgia",20));
        pasos[5].setFont(new Font("Georgia",20));
        pasos[6].setFont(new Font("Georgia",20));
        //Agregando a la Escena
        stage.setScene(escena);
        stage.setWidth(recAncho);
        stage.setHeight(recAltura);
        stage.setTitle("Intento Muestra Codigo");    
        stage.show(); 
    }
    
    public void DestacarLinea(int posicion){
        if(contador_indice>=0 && contador_indice<pasos.length){
            pasos[contador_indice].setStyle("-fx-background-color: red; -fx-text-fill: gold");
        }
        if(posicion>=0 && posicion<pasos.length){
            pasos[posicion].setStyle("-fx-background-color: blue; -fx-text-fill: white");
            contador_indice=posicion;
        }
    }
    
    public static void main(String[] args) {
        launch();
    }

}