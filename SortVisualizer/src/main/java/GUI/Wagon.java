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

    public void DrawWagon(Canvas canvasBox, double squareDimension){
        gw = canvasBox.getGraphicsContext2D();
            System.out.println(squareDimension);
 
            
            createTrainCanvas(0, -5, Color.RED, canvasBox, squareDimension);
    }
    
    public void DrawTrain( int scale){
        GraphicsContext gw = canvasBox.getGraphicsContext2D();
        double SizeOfWagon=0;
            //canvasBox.setHeight(squareDimension*1.5);
            //canvasBox.setWidth(squareDimension*2);
            
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
            
        gw.setFill(Color.BLACK);
        //Ruedas
        gw.fillOval(12* SizeOfWagon,1* SizeOfWagon,20* SizeOfWagon,20* SizeOfWagon);
        gw.fillOval(37* SizeOfWagon,1* SizeOfWagon,20* SizeOfWagon,20* SizeOfWagon);
        gw.fillOval(60* SizeOfWagon,1* SizeOfWagon,40* SizeOfWagon,40* SizeOfWagon);
        
        gw.fillOval(12* SizeOfWagon,60* SizeOfWagon,20* SizeOfWagon,20* SizeOfWagon);
        gw.fillOval(37* SizeOfWagon,60* SizeOfWagon,20* SizeOfWagon,20* SizeOfWagon);
        gw.fillOval(60* SizeOfWagon,40* SizeOfWagon,40* SizeOfWagon,40* SizeOfWagon);
        
        gw.setFill(Color.RED);
        gw.fillRect(10* SizeOfWagon,10* SizeOfWagon,110* SizeOfWagon,60* SizeOfWagon);
        
        //Frontal
        gw.setFill(Color.BLACK);
        double dobleX[] = {10* SizeOfWagon,10* SizeOfWagon,1* SizeOfWagon};
        double dobleY[] = {10* SizeOfWagon,70* SizeOfWagon,35* SizeOfWagon};
        gw.fillPolygon(dobleX, dobleY, 3);
        
        //Cañon y cabina
        gw.fillRect(50* SizeOfWagon,15* SizeOfWagon,45* SizeOfWagon,50* SizeOfWagon);
        
        gw.fillOval(15* SizeOfWagon,25* SizeOfWagon,25* SizeOfWagon,25* SizeOfWagon);
        gw.setFill(Color.GRAY);
        gw.fillOval(17.5* SizeOfWagon,27* SizeOfWagon,20* SizeOfWagon,20* SizeOfWagon);
        
        
}
    

    private void createTrainCanvas(double x, double y, Color color, Canvas canvas, double scale) {
       GraphicsContext gc = canvas.getGraphicsContext2D();
       
       


          
        // Dibujar las ruedas
        /*
        gc.setFill(Color.BLACK);
        gc.fillOval(10 * SizeOfWagon, -3 * SizeOfWagon, 20 * SizeOfWagon, 20 * SizeOfWagon);
        gc.fillOval(60 * SizeOfWagon, -3 * SizeOfWagon, 20 * SizeOfWagon, 20 * SizeOfWagon);

        gc.fillOval(10 * SizeOfWagon, 35 * SizeOfWagon, 20 * SizeOfWagon, 20 * SizeOfWagon);
        gc.fillOval(60 * SizeOfWagon, 35 * SizeOfWagon, 20 * SizeOfWagon, 20 * SizeOfWagon);
        */
        
        
        // Dibujar el cuerpo del vagón
        gc.setFill(color);
        //gc.fillRoundRect(0, scale, scale, scale, 10, 10 );
        gc.fillRect(0, 0, scale, scale);
        // Dibujar el espacio para el número identificador
       // gc.setFill(Color.BLACK);
        //gc.fillRoundRect(11 * SizeOfWagon, 11 * SizeOfWagon, 65 * SizeOfWagon, 30 * SizeOfWagon, 5 * SizeOfWagon, 5 * SizeOfWagon);


   }

}