package GUI;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;


/**
 *
 * @author david
 */
public class Wagon {

    GraphicsContext gw;
    private final Canvas canvasBox;
    
    
    public Wagon(Canvas canvasBox) {
        this.canvasBox = canvasBox;
    }
    
    
    
    public void DrawWagon(Canvas canvasBox, double squareDimension){
        gw = canvasBox.getGraphicsContext2D();
        System.out.println(squareDimension);
            canvasBox.setHeight(squareDimension);
            canvasBox.setWidth(squareDimension);
            createTrainCanvas(20, 20, Color.RED, canvasBox);
        
        
    }
    
    
    
    
 private void createTrainCanvas(double x, double y, Color color, Canvas canvas) {
    GraphicsContext gc = canvas.getGraphicsContext2D();
    double SizeOfWagon = .5;

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
    canvas.setTranslateX(x);
    canvas.setTranslateY(y);
}

}
