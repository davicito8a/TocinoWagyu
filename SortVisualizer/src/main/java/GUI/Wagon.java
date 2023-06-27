package GUI;

import SortVisualizerCore.Main;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Affine;

public class Wagon {

    GraphicsContext gw;
    private final Canvas canvasBox;

    public Wagon(Canvas canvasBox) {
        this.canvasBox = canvasBox;
    }

    public void DrawWagon(Canvas canvasBox, double squareDimension, int size) {
        gw = canvasBox.getGraphicsContext2D();
        //System.out.println(squareDimension);
        canvasBox.setHeight(squareDimension * 2.5);
        canvasBox.setWidth(squareDimension * 3);
        createTrainCanvas(0, -5, Color.RED, canvasBox, size);
    }

    public void DrawTrain(int scale) {
        GraphicsContext gw = canvasBox.getGraphicsContext2D();

        // Determinar el factor de escala relativo
        double scaleFactor = 1.0;
        switch (scale) {
            case 4:
                scaleFactor = 0.5;
                break;
            case 5:
                scaleFactor = 0.4;
                break;
            case 7:
                scaleFactor = 0.25;
                break;
            case 10:
                scaleFactor = 0.2;
                break;
            case 12:
                scaleFactor = 0.15;
                break;
            default:
                System.out.println("Caso inválido");
                return;
        }

        // Guardar la transformación actual
        Affine originalTransform = gw.getTransform();

        // Crear una transformación escalada
        Affine scaleTransform = new Affine();
        scaleTransform.appendScale(scaleFactor, scaleFactor);

        // Combinar la transformación escalada con la transformación original
        Affine combinedTransform = originalTransform.clone();
        combinedTransform.append(scaleTransform);

        // Establecer la transformación combinada en el contexto de gráficos
        gw.setTransform(combinedTransform);

        double sizeOfWagon = 1.0; // Factor de tamaño original

        gw.setFill(Color.BLACK);
        // Ruedas
        gw.fillOval(12 * sizeOfWagon, 1 * sizeOfWagon, 20 * sizeOfWagon, 20 * sizeOfWagon);
        gw.fillOval(37 * sizeOfWagon, 1 * sizeOfWagon, 20 * sizeOfWagon, 20 * sizeOfWagon);
        gw.fillOval(60 * sizeOfWagon, 1 * sizeOfWagon, 40 * sizeOfWagon, 40 * sizeOfWagon);

        gw.fillOval(12 * sizeOfWagon, 60 * sizeOfWagon, 20 * sizeOfWagon, 20 * sizeOfWagon);
        gw.fillOval(37 * sizeOfWagon, 60 * sizeOfWagon, 20 * sizeOfWagon, 20 * sizeOfWagon);
        gw.fillOval(60 * sizeOfWagon, 40 * sizeOfWagon, 40 * sizeOfWagon, 40 * sizeOfWagon);

        gw.setFill(Color.RED);
        gw.fillRect(10 * sizeOfWagon, 10 * sizeOfWagon, 110 * sizeOfWagon, 60 * sizeOfWagon);

        // Frontal
        gw.setFill(Color.BLACK);
        double[] dobleX = {10 * sizeOfWagon, 10 * sizeOfWagon, 1 * sizeOfWagon};
        double[] dobleY = {10 * sizeOfWagon, 70 * sizeOfWagon, 35 * sizeOfWagon};
        gw.fillPolygon(dobleX, dobleY, 3);

        // Cañon y cabina
        gw.fillRect(50 * sizeOfWagon, 15 * sizeOfWagon, 45 * sizeOfWagon, 50 * sizeOfWagon);

        gw.fillOval(15 * sizeOfWagon, 25 * sizeOfWagon, 25 * sizeOfWagon, 25 * sizeOfWagon);
        gw.setFill(Color.GRAY);
        gw.fillOval(17.5 * sizeOfWagon, 27 * sizeOfWagon, 20 * sizeOfWagon, 20 * sizeOfWagon);

        // Restaurar la transformación original
        gw.setTransform(originalTransform);
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
                System.out.println("Caso inválido");
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
        int tamanoint = (int) Main.size;
        double Centrar = 0;
        switch (tamanoint) {
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

        canvas.setTranslateX(x + Centrar);
        canvas.setTranslateY(y);
    }
}
