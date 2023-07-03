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

    public void DrawTrain(double width,boolean rotate) {
        GraphicsContext gc = canvasBox.getGraphicsContext2D();
        double height = width / 1.4;
        
        gc.setFill(Color.RED);
        gc.fillRect(0, 0, width, height);
        
        if(rotate==true){
            // Simetría axial
            gc.setFill(Color.BLACK);
            gc.fillRect((1 - 0.6) * width, 0.1 * height, 0.4 * width, 0.8 * height);

            gc.setFill(Color.GRAY);
            gc.fillOval((1 - 0.9) * width, 0.4 * height, 0.2 * width, 0.2 * height);
        }else{
            gc.setFill(Color.BLACK);
            gc.fillRect(0.2 * width, 0.1 * height, 0.4 * width, 0.8 * height);

            gc.setFill(Color.GRAY);
            gc.fillOval(0.7 * width, 0.4 * height, 0.2 * width, 0.2 * height); // falta hacer calculos
        }
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
