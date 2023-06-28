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

    public void DrawWagon(Canvas canvasBox, double squareDimension, double size){
        gw = canvasBox.getGraphicsContext2D();
        //System.out.println(squareDimension);
            canvasBox.setHeight(squareDimension*0.6);
            canvasBox.setWidth(squareDimension*1.2);
            createTrainCanvas(0, -5, Color.RED, canvasBox, size);
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
        //System.out.println("scale: " +   scale);
        double SizeOfWagon = 0;
        SizeOfWagon = (-0.04375*scale)+0.675;
        if(Main.sortType==3){
            SizeOfWagon*=0.7;
        }
          
        // Dibujar las ruedas
        gc.setFill(Color.BLACK);
        gc.fillOval(10 * SizeOfWagon, -3 * SizeOfWagon, 20 * SizeOfWagon, 20 * SizeOfWagon);
        gc.fillOval(60 * SizeOfWagon, -3 * SizeOfWagon, 20 * SizeOfWagon, 20 * SizeOfWagon);

        gc.fillOval(10 * SizeOfWagon, 35 * SizeOfWagon, 20 * SizeOfWagon, 20 * SizeOfWagon);
        gc.fillOval(60 * SizeOfWagon, 35 * SizeOfWagon, 20 * SizeOfWagon, 20 * SizeOfWagon);
        
        // Dibujar el cuerpo del vagón
        gc.setFill(color);
        gc.fillRoundRect(1 * SizeOfWagon, 1 * SizeOfWagon, 85 * SizeOfWagon, 50 * SizeOfWagon, 10 * SizeOfWagon, 10 * SizeOfWagon);

        // Dibujar el espacio para el número identificador
        gc.setFill(Color.PERU);
        gc.fillRoundRect(11 * SizeOfWagon, 11 * SizeOfWagon, 65 * SizeOfWagon, 30 * SizeOfWagon, 5 * SizeOfWagon, 5 * SizeOfWagon);

        // Dibujar el número identificador
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 18 * SizeOfWagon));
        gc.setTextAlign(TextAlignment.CENTER);


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