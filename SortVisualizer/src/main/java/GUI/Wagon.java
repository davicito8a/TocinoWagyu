package GUI;

import SortVisualizerCore.Main;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class Wagon {

    GraphicsContext gw;
    private final Canvas canvasBox;
    
    
    public Wagon(Canvas canvasBox) {
        this.canvasBox = canvasBox;
    }
  
    public void DrawWagon(Canvas canvasBox, double squareDimension, int size){
        gw = canvasBox.getGraphicsContext2D();
        //System.out.println(squareDimension);
            canvasBox.setHeight(squareDimension);
            canvasBox.setWidth(squareDimension*1.25);
            createTrainCanvas(0, -5, Color.RED, canvasBox, size);
    }

    private void createTrainCanvas(double x, double y, Color color, Canvas canvas, int scale) {
       GraphicsContext gc = canvas.getGraphicsContext2D();
        //System.out.println("scale: " +   scale);
        double SizeOfWagon = 0;

           switch (scale) {
               case 4:
                   SizeOfWagon = .5;
                   break;
               case 5:
                  SizeOfWagon = .4;
                   break;
               case 7:
                  SizeOfWagon = .25;
                   break;
               case 10:
                  SizeOfWagon = .2;
                   break;
               case 12:
                  SizeOfWagon = .15;
                   break;
               default:
                   System.out.println("Caso invalido");
                   break;
           }


       // Dibujar el cuerpo del vagón
       gc.setFill(color);
       gc.fillRoundRect(10 * SizeOfWagon, 10 * SizeOfWagon, 100 * SizeOfWagon, 50 * SizeOfWagon, 10 * SizeOfWagon, 10 * SizeOfWagon);

       // Dibujar el espacio para el número identificador
       gc.setFill(Color.WHITE);
       gc.fillRoundRect(20 * SizeOfWagon, 20 * SizeOfWagon, 80 * SizeOfWagon, 30 * SizeOfWagon, 5 * SizeOfWagon, 5 * SizeOfWagon);

       // Dibujar el número identificador
       gc.setFill(Color.BLACK);
       gc.setFont(Font.font("Arial", FontWeight.BOLD, 18 * SizeOfWagon));
       gc.setTextAlign(TextAlignment.CENTER);

       // Dibujar las ruedas
       gc.setFill(Color.BLACK);
       gc.fillOval(20 * SizeOfWagon, 60 * SizeOfWagon, 20 * SizeOfWagon, 20 * SizeOfWagon);
       gc.fillOval(80 * SizeOfWagon, 60 * SizeOfWagon, 20 * SizeOfWagon, 20 * SizeOfWagon);

       // Configurar la posición del canvas dentro del StackPane
       int tamanoint =(int)Main.size;
       double Centrar=0;
       switch(tamanoint){
            case 4:
                Centrar = -2;
                
                break;
            case 5:
                Centrar = 4;
                
                break;
            case 7:
                Centrar = 13;
                
                break;
            case 10:
                Centrar = 16.5;
                
                break;
            case 12:
                Centrar = 19;
                
                break;
       }
       
       canvas.setTranslateX(x+Centrar);
       canvas.setTranslateY(y);
   }

}