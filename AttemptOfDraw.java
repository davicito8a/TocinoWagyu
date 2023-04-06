/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package attemptofdraw;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Circle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author david
 */
public class AttemptOfDraw extends Application {
    
    
    @Override
    public void start(Stage primaryStage) {
        // 
        
        Group BG = new Group();
        Scene scene = new Scene(BG, 600, 600, Color.LIGHTSKYBLUE);
        Stage stage = new Stage();
        
        
        
        
        Rectangle r = new Rectangle();
        r.setX(100);
        r.setY(200);
        r.setWidth(100);
        r.setHeight(100);
        r.setArcWidth(20);
        r.setArcHeight(20);
        r.setFill(Color.WHITE);
        r.setStroke(Color.BLACK);
        
        
        
        
        Line DiagonalDelUno = new Line();
        DiagonalDelUno.setStartY(r.getY()+45);
        DiagonalDelUno.setStartX(r.getX()+60);
        DiagonalDelUno.setEndX(r.getX()+90);
        DiagonalDelUno.setEndY(r.getY()+10);
    
        DiagonalDelUno.setStrokeWidth(5);
        
        
       
        Line VerticalDelUno = new Line();
        
        VerticalDelUno.setStartY(r.getY()+70);
        VerticalDelUno.setStartX(r.getX()+92);
        VerticalDelUno.setEndX(r.getX()+92);
        VerticalDelUno.setEndY(r.getY()+10);
        VerticalDelUno.setStrokeWidth(5);
        
        
        
        // bezier curve
        // move to

        
        
       
        
        
        
        BG.getChildren().add(r);
        BG.getChildren().add(VerticalDelUno);
        BG.getChildren().add(DiagonalDelUno);
        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
