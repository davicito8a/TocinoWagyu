package com.mycompany.trymuestracodigo;

import java.util.Scanner;
import java.util.ArrayList;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class App extends Application {
    
    public static int recAltura = 600;
    public static int recAncho = 600;
    public static Color ColorFondo = Color.GREY;
    public static Color ColorRec = Color.RED;
    public static Color ColorLetra = Color.WHITE;
    public static Color ColorLetra2 = Color.GOLD;
    
    @Override
    public void start(Stage stage) {
        AnchorPane ventana = new AnchorPane();
        Group root = new Group();
        Rectangle rec = new Rectangle();
        Rectangle rec2 = new Rectangle();
        Rectangle rec3 = new Rectangle();
        Rectangle rec4 = new Rectangle();
        Rectangle rec5 = new Rectangle();
        Rectangle rec6 = new Rectangle();
        Scene escena = new Scene(ventana);
        escena.setFill(ColorFondo);
        float coorsY=50,sepa=10;
        
        //Crear Rectángulo Grande
        rec.setX(50);
        rec.setY(50);
        rec.setWidth(350);
        rec.setHeight(300);
        rec.setFill(Color.BLUE);
        
        //Rec Pequeños
        rec2.setX(50);
        rec2.setY(50);
        rec2.setWidth(340);
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
        Label nametag = new Label("mark the fisrt element as Sorted".toString());
        Label nametag2 = new Label("for each unsorted element X\n" +
                                                               "\n" +
                                    "  'extract' the element X".toString());
        Label nametag3 = new Label("for j = lastSortedIndex down to 0".toString());
        Label nametag4 = new Label(" if current element j > X\n" +
                                                            "\n" +
                                    "      move sorted element to the right by 1".toString());
        Label nametag5 = new Label("break loop and insert X here".toString());
        
        //Color de Las Etiquetas:
        nametag.setTextFill(ColorLetra);
        nametag2.setTextFill(ColorLetra);
        nametag3.setTextFill(ColorLetra);
        nametag4.setTextFill(ColorLetra);
        nametag5.setTextFill(ColorLetra);
        
        //Coordenadas de Etiquetas
        nametag.relocate(60, 70);
        nametag2.relocate(80, 110);
        nametag3.relocate(100, 190);
        nametag4.relocate(120, 230);
        nametag5.relocate(140, 310);
        
        
        //Agregando a la Escena
        stage.setScene(escena);
        stage.setWidth(recAncho);
        stage.setHeight(recAltura);
        ventana.getChildren().add(rec);
        ventana.getChildren().add(rec2);
        ventana.getChildren().add(rec3);
        ventana.getChildren().add(rec4);
        ventana.getChildren().add(rec5);
        ventana.getChildren().add(rec6);
        ventana.getChildren().add(nametag);
        ventana.getChildren().add(nametag2);
        ventana.getChildren().add(nametag3);
        ventana.getChildren().add(nametag4);
        ventana.getChildren().add(nametag5);
        stage.show();
        
        
        //Cambiando Color
        int valor;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Elija una opción:");
        valor=entrada.nextInt();
        
        switch(valor){
            case 2:
                rec2.setFill(ColorLetra2);
                stage.show();
            break;
            case 3:
                rec3.setFill(ColorLetra2);
                stage.show();
            break;
            case 4:
                rec4.setFill(ColorLetra2);
                stage.show();
            break;
            case 5:
                rec5.setFill(ColorLetra2);
                stage.show();
            break;
            case 6:
                rec6.setFill(ColorLetra2);
                stage.show();
            break;
            default:
                System.out.println("Se cayó el programa");
            break;
        }
    }

    public static void main(String[] args) {
        launch();
    }

}