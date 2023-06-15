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
            canvasBox.setHeight(squareDimension*1.5);
            canvasBox.setWidth(squareDimension*2);
            createTrainCanvas(0, -5, Color.RED, canvasBox, size);
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
        gw.fillOval(405* SizeOfWagon,290* SizeOfWagon,20* SizeOfWagon,20* SizeOfWagon);
        gw.fillOval(430* SizeOfWagon,290* SizeOfWagon,20* SizeOfWagon,20* SizeOfWagon);
        gw.fillOval(460* SizeOfWagon,290* SizeOfWagon,40* SizeOfWagon,40* SizeOfWagon);
        
        gw.fillOval(405* SizeOfWagon,340* SizeOfWagon,20* SizeOfWagon,20* SizeOfWagon);
        gw.fillOval(430* SizeOfWagon,340* SizeOfWagon,20* SizeOfWagon,20* SizeOfWagon);
        gw.fillOval(460* SizeOfWagon,320* SizeOfWagon,40* SizeOfWagon,40* SizeOfWagon);
        
        gw.setFill(Color.YELLOW);
        gw.fillRect(30* SizeOfWagon,20* SizeOfWagon,100* SizeOfWagon,50* SizeOfWagon);
        
        //Frontal
        gw.setFill(Color.BLACK);
        double dobleX[] = {405* SizeOfWagon,405* SizeOfWagon,380* SizeOfWagon};
        double dobleY[] = {300* SizeOfWagon,350* SizeOfWagon,325* SizeOfWagon};
        gw.fillPolygon(dobleX, dobleY, 3);
        
        gw.fillRect(450* SizeOfWagon,305* SizeOfWagon,45* SizeOfWagon,40* SizeOfWagon);
        
         gw.fillOval(415* SizeOfWagon,310* SizeOfWagon,25* SizeOfWagon,25* SizeOfWagon);       
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
           
       // Dibujar las ruedas
       gc.setFill(Color.BLACK);
       gc.fillOval(20 * SizeOfWagon, 45 * SizeOfWagon, 20 * SizeOfWagon, 20 * SizeOfWagon);
       gc.fillOval(80 * SizeOfWagon, 45 * SizeOfWagon, 20 * SizeOfWagon, 20 * SizeOfWagon);
       
       gc.fillOval(20 * SizeOfWagon, 5 * SizeOfWagon, 20 * SizeOfWagon, 20 * SizeOfWagon);
       gc.fillOval(80 * SizeOfWagon, 5 * SizeOfWagon, 20 * SizeOfWagon, 20 * SizeOfWagon);

       // Dibujar el cuerpo del vagón
       gc.setFill(color);
       gc.fillRoundRect(10 * SizeOfWagon, 10 * SizeOfWagon, 100 * SizeOfWagon, 50 * SizeOfWagon, 10 * SizeOfWagon, 10 * SizeOfWagon);

       // Dibujar el espacio para el número identificador
       gc.setFill(Color.PERU);
       gc.fillRoundRect(20 * SizeOfWagon, 20 * SizeOfWagon, 80 * SizeOfWagon, 30 * SizeOfWagon, 5 * SizeOfWagon, 5 * SizeOfWagon);

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