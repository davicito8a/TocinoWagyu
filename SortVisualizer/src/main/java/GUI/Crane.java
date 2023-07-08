package GUI;

import SortVisualizerCore.Main;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Crane {

    public static Color ColorInterno = Color.GOLD;
    public static Color ColorExterno = Color.BLACK;
    Canvas canvas = new Canvas(1500, 600);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    int CoorBigaSuperior = 1300;
    int RazondeBigaDeArriba = (CoorBigaSuperior - 50) / 13;

    public Crane() {
        CreateCrane();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void CreateCrane() {
        gc.setLineWidth(2);
        //===============================================================================
        //Lineas Laterales de Izquierda
        gc.setStroke(ColorExterno);
        gc.strokeLine(40, 400, 40, 40);
        gc.setStroke(ColorExterno);
        gc.strokeLine(10, 400, 10, 15);

        //Bigas Izquierdas (abajo arriba)
        int coorx1 = 10, coory1 = 400, coorx2 = 40, coory2 = 350;
        for (int i = 1; i < 8; i++) {
            gc.setStroke(ColorInterno);
            gc.strokeLine(coorx1, coory1, coorx2, coory2);
            coory1 = coory1 - 50;
            coory2 = coory2 - 50;
        }
        //Bigas Izquierdas (arriba abajo)
        int coorx12 = 40, coory12 = 400, coorx22 = 10, coory22 = 350;
        for (int i = 1; i < 8; i++) {
            gc.setStroke(ColorInterno);
            gc.strokeLine(coorx12, coory12, coorx22, coory22);
            coory12 = coory12 - 50;
            coory22 = coory22 - 50;
        }
        //===============================================================================
        //Superiores
        //CoordsBiga superior 1300
        gc.setStroke(ColorExterno);
        gc.strokeLine(40, 40, 1450, 40);
        gc.setStroke(ColorExterno);
        gc.strokeLine(10, 15, 1480, 15);

        //Biga superior adorno (Abajo arriba)
        int aux = RazondeBigaDeArriba;
        int coorys = 15, coory1s = 40;
        for (int i = 1; i < 19; i++) {
            if (i == 1) {
                gc.setStroke(ColorInterno);
                gc.strokeLine(40, coorys, (40 + RazondeBigaDeArriba), coory1s);
            } else if (i > 1) {
                gc.setStroke(ColorInterno);
                gc.strokeLine(aux, coorys, (aux + RazondeBigaDeArriba), coory1s);
                aux = (aux + 80);
            }
        }

        //Biga superior adorno (arriba abajo)
        int aux2 = 1400 + RazondeBigaDeArriba;
        int coorys2 = 15, coory1s2 = 40;
        for (int i = 1; i < 20; i++) {
            if (i == 1) {
            } else if (i > 1) {
                gc.setStroke(ColorInterno);
                gc.strokeLine(aux2, coorys2, (aux2 - RazondeBigaDeArriba), coory1s2);
                aux2 = (aux2 - 80);
            }
        }
        //Rectangulos de adorno
        gc.setFill(Color.GOLD);
        gc.fillRect(10, 15, 30, 30);
        gc.setFill(Color.GOLD);
        gc.fillRect(1450, 15, 30, 30);

        //===============================================================================
        //Lineas Laterales de Derecha
        gc.setStroke(ColorExterno);
        gc.strokeLine(1450, 40, 1450, 400);
        gc.setStroke(ColorExterno);
        gc.strokeLine(1480, 15, 1480, 400);
        //Bigas Derecha (abajo arriba)
        int coorx13 = 1450, coory13 = 400, coorx23 = 1480, coory23 = 350;
        for (int i = 1; i < 8; i++) {
            gc.setStroke(ColorInterno);
            gc.strokeLine(coorx13, coory13, coorx23, coory23);
            coory13 = coory13 - 50;
            coory23 = coory23 - 50;
        }

        //Bigas Derecha (arriba abajo)
        int coorx14 = 1480, coory14 = 400, coorx24 = 1450, coory24 = 350;
        for (int i = 1; i < 8; i++) {
            gc.setStroke(ColorInterno);
            gc.strokeLine(coorx14, coory14, coorx24, coory24);
            coory14 = coory14 - 50;
            coory24 = coory24 - 50;
        }

        //Posiciones Auxiliares para Bubble y Cocktail 
        //Lado Izquierda 
        if (Main.sortType == 1) {
            double[] apoyoIzquiera1 = {40, 150, 130, 40};
            double[] apoyoIzquiera2 = {200, 200, 230, 230};

            gc.setStroke(ColorInterno);
            gc.fillPolygon(apoyoIzquiera1, apoyoIzquiera2, 4);

            gc.setLineWidth(5.5);
            gc.setStroke(ColorExterno);
            gc.strokeLine(40, 200, 150, 200);
            gc.strokeLine(150, 200, 130, 230);
            gc.strokeLine(130, 230, 40, 230);

        } else if (Main.sortType == 2) {
            double[] apoyoIzquiera12 = {40, 150, 130, 40};
            double[] apoyoIzquiera22 = {200, 200, 230, 230};

            gc.setStroke(ColorInterno);
            gc.fillPolygon(apoyoIzquiera12, apoyoIzquiera22, 4);

            gc.setLineWidth(5.5);
            gc.setStroke(ColorExterno);
            gc.strokeLine(40, 200, 150, 200);
            gc.strokeLine(150, 200, 130, 230);
            gc.strokeLine(130, 230, 40, 230);

            //Lado Derecha
            double[] apoyoDerecha13 = {1450, 1340, 1360, 1450};
            double[] apoyoDerecha23 = {200, 200, 230, 230};

            gc.setStroke(ColorInterno);
            gc.fillPolygon(apoyoDerecha13, apoyoDerecha23, 4);

            gc.setLineWidth(5.5);
            gc.setStroke(ColorExterno);
            gc.strokeLine(1450, 200, 1340, 200);
            gc.strokeLine(1340, 200, 1360, 230);
            gc.strokeLine(1360, 230, 1450, 230);
        }

    }
}
