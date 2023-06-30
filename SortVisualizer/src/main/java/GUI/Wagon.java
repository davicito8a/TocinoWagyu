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
    
    public void DrawTrain(double scale){
        GraphicsContext gw = canvasBox.getGraphicsContext2D();
        double SizeOfWagon=0;
            //canvasBox.setHeight(squareDimension*1.5);
            //canvasBox.setWidth(squareDimension*2);
        SizeOfWagon = (-0.04375*scale)+0.675;
            
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

          // Dibujar el cuerpo del vagón
        
           gc.setFill(Color.web("#FF6347")); // Color principal del vagón (rojo coral)
            gc.fillRect(0, 0, scale, scale / 1.4);

            // Detalles decorativos del vagón

            // Ventanas del vagón
            gc.setFill(Color.web("#F5F5F5")); // Color de las ventanas (blanco humo)

            double windowWidth = scale / 4;
            double windowHeight = scale / 1.8;
            double windowSpacing = scale / 16;
            double windowX = windowSpacing;
            double windowY = scale / 6;

            for (int i = 0; i < 4; i++) {
                gc.fillRect(windowX, windowY, windowWidth, windowHeight);
                windowX += windowWidth + windowSpacing;
            }

            // Ruedas del vagón
            gc.setFill(Color.web("#222222")); // Color de las ruedas (negro carbón)

            double wheelRadius = scale / 10;
            double wheelY = (scale / 1.4) - (wheelRadius / 2);

            double wheelX1 = scale / 6;
            double wheelX2 = (scale / 1.2) - (wheelRadius * 2);

            gc.fillOval(wheelX1, wheelY, wheelRadius, wheelRadius);
            gc.fillOval(wheelX2, wheelY, wheelRadius, wheelRadius);

            gc.setFill(Color.web("#808080")); // Color del eje de las ruedas (gris medio)
            gc.fillRect(wheelX1 + (wheelRadius / 2), wheelY, wheelRadius, wheelRadius);
            gc.fillRect(wheelX2 + (wheelRadius / 2), wheelY, wheelRadius, wheelRadius);

            // Detalles adicionales
            gc.setFill(Color.web("#D3D3D3")); // Color para detalles adicionales (plata claro)
            double detailWidth = scale / 8;
            double detailHeight = scale / 2.5;
            double detailX = scale / 2 - (detailWidth / 2);
            double detailY = scale / 6;

            gc.fillRect(detailX, detailY, detailWidth, detailHeight);

            gc.setFill(Color.web("#333333")); // Color para detalles adicionales (negro)
            double handleWidth = scale / 16;
            double handleHeight = scale / 8;
            double handleX = detailX + (detailWidth / 2) - (handleWidth / 2);
            double handleY = detailY + detailHeight - (handleHeight / 2);

            gc.fillRect(handleX, handleY, handleWidth, handleHeight);

        
        
   }

}