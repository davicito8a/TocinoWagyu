package GUI;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Wagon {

    GraphicsContext gc;
    private final Canvas canvasBox;

    public Wagon(Canvas canvasBox) {
        this.canvasBox = canvasBox;
    }

    public void DrawWagon(Canvas canvasBox, double squareDimension) {
        gc = canvasBox.getGraphicsContext2D();

        createTrainCanvas(0, -5, Color.RED, canvasBox, squareDimension);
    }

    public void DrawTrain(double width) {
        GraphicsContext gc = canvasBox.getGraphicsContext2D();
        
        double height = width/ 1.4;
        // Color locomotora.
        gc.setFill(Color.RED);
        
        // Cuerpo.
        gc.fillRect(0, 0, width, height);
        
        gc.setFill(Color.BLACK);
        gc.fillRect(0.2 * width, 0.1 * height, 0.4 * width, 0.8 * height);
        
        gc.setFill(Color.GRAY);
        gc.fillOval(0.7 * width, 0.4 * height, 0.2 * width, 0.2 * height); // falta hacer calculos
        //gc.fillOval(17.5 * SizeOfWagon, 27 * SizeOfWagon, 20 * SizeOfWagon, 20 * SizeOfWagon);
        
        /*
        

    
        

        //Frontal
        gw.setFill(Color.BLACK);
        double dobleX[] = {10 * SizeOfWagon, 10 * SizeOfWagon, 1 * SizeOfWagon};
        double dobleY[] = {10 * SizeOfWagon, 70 * SizeOfWagon, 35 * SizeOfWagon};
        gw.fillPolygon(dobleX, dobleY, 3);

        //Cañon y cabina
        gw.fillRect(50 * SizeOfWagon, 15 * SizeOfWagon, 45 * SizeOfWagon, 50 * SizeOfWagon);

        gw.fillOval(15 * SizeOfWagon, 25 * SizeOfWagon, 25 * SizeOfWagon, 25 * SizeOfWagon);
        gw.setFill(Color.GRAY);
        gw.fillOval(17.5 * SizeOfWagon, 27 * SizeOfWagon, 20 * SizeOfWagon, 20 * SizeOfWagon);
        */

    }

    private void createTrainCanvas(double x, double y, Color color, Canvas canvas, double scale) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.web("#008080")); // Color principal del vagón (turquesa oscuro)
        gc.fillRect(0, 0, scale, scale / 1.4);

        // Detalles decorativos del vagón
        // Ventanas del vagón
        gc.setFill(Color.web("#F5F5F5")); // Color de las ventanas (blanco humo)

        double windowWidth = scale / 5;
        double windowHeight = scale / 2.5;
        double windowSpacing = scale / 16;
        double windowX = (scale - (4 * windowWidth) - (3 * windowSpacing)) / 2;
        double windowY = scale / 6;

        for (int i = 0; i < 4; i++) {
            gc.fillRect(windowX, windowY, windowWidth, windowHeight);
            windowX += windowWidth + windowSpacing;
        }

        // Ruedas del vagón
        gc.setFill(Color.web("#444444")); // Color de las ruedas (gris oscuro)

        double wheelRadius = scale / 12;
        double wheelY = (scale / 1.4) - (wheelRadius / 2);

        double wheelX1 = scale / 5;
        double wheelX2 = (scale / 1.5) - (wheelRadius * 2);

        gc.fillOval(wheelX1, wheelY, wheelRadius, wheelRadius);
        gc.fillOval(wheelX2, wheelY, wheelRadius, wheelRadius);

        gc.setFill(Color.web("#808080")); // Color del eje de las ruedas (gris medio)
        gc.fillRect(wheelX1 + (wheelRadius / 2), wheelY, wheelRadius, wheelRadius);
        gc.fillRect(wheelX2 + (wheelRadius / 2), wheelY, wheelRadius, wheelRadius);

        // Detalles adicionales
        gc.setFill(Color.web("#FFD700")); // Color para detalles adicionales (oro)

        double detailWidth = scale / 2.5;
        double detailHeight = scale / 12;
        double detailX = (scale - detailWidth) / 2;
        double detailY = scale / 2.5;

        gc.fillRect(detailX, detailY, detailWidth, detailHeight);

        gc.setFill(Color.web("#A0522D")); // Color para detalles adicionales (marrón)

        double roofWidth = scale / 1.1;
        double roofHeight = scale / 8;
        double roofX = (scale - roofWidth) / 2;
        double roofY = detailY - (roofHeight / 1.5);

        gc.fillRoundRect(roofX, roofY, roofWidth, roofHeight, roofHeight, roofHeight);

    }

}
