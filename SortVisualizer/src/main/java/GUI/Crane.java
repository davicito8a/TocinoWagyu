package GUI;

import SortVisualizerCore.Main;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Crane {

    public static Color ColorInterno = Color.GOLD;
    public static Color ColorExterno = Color.BLACK;
    Group root = new Group();
    Group root2 = new Group();
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
            int tamanoint = (int) Main.size;
            double sizePlata = 0;

            switch (tamanoint) {
                case 4:
                    sizePlata = 0.97;

                    break;
                case 5:
                    sizePlata = 0.93;

                    break;
                case 7:
                    sizePlata = 0.87;

                    break;
                case 10:
                    sizePlata = 0.85;

                    break;
                case 12:
                    sizePlata = 0.83;

                    break;
                default:
                    System.out.println("Algo salio terriblemente mal");
            }

            double[] apoyoIzquiera1 = {40 * sizePlata, 150 * sizePlata, 130 * sizePlata, 40 * sizePlata};
            double[] apoyoIzquiera2 = {200 * sizePlata, 200 * sizePlata, 230 * sizePlata, 230 * sizePlata};

            gc.setStroke(ColorInterno);
            gc.fillPolygon(apoyoIzquiera1, apoyoIzquiera2, 4);

            gc.setLineWidth(5.5);
            gc.setStroke(ColorExterno);
            gc.strokeLine(40 * sizePlata, 200 * sizePlata, 150 * sizePlata, 200 * sizePlata);
            gc.strokeLine(150 * sizePlata, 200 * sizePlata, 130 * sizePlata, 230 * sizePlata);
            gc.strokeLine(130 * sizePlata, 230 * sizePlata, 40 * sizePlata, 230 * sizePlata);
        } else if (Main.sortType == 2) {
            int tamanoint = (int) Main.size;
            double sizePlata = 0;

            switch (tamanoint) {
                case 4:
                    sizePlata = 0.97;

                    break;
                case 5:
                    sizePlata = 0.93;

                    break;
                case 7:
                    sizePlata = 0.87;

                    break;
                case 10:
                    sizePlata = 0.85;

                    break;
                case 12:
                    sizePlata = 0.83;

                    break;
                default:
                    System.out.println("Algo salio terriblemente mal");
            }

            double[] apoyoIzquiera1 = {40 * sizePlata, 150 * sizePlata, 130 * sizePlata, 40 * sizePlata};
            double[] apoyoIzquiera2 = {200 * sizePlata, 200 * sizePlata, 230 * sizePlata, 230 * sizePlata};

            gc.setStroke(ColorInterno);
            gc.fillPolygon(apoyoIzquiera1, apoyoIzquiera2, 4);

            gc.setLineWidth(5.5);
            gc.setStroke(ColorExterno);
            gc.strokeLine(40 * sizePlata, 200 * sizePlata, 150 * sizePlata, 200 * sizePlata);
            gc.strokeLine(150 * sizePlata, 200 * sizePlata, 130 * sizePlata, 230 * sizePlata);
            gc.strokeLine(130 * sizePlata, 230 * sizePlata, 40 * sizePlata, 230 * sizePlata);

            //Lado Derecha
            double acomodarderecha = 0;
            switch (tamanoint) {
                case 4:
                    acomodarderecha = 42;

                    break;
                case 5:
                    acomodarderecha = 100;

                    break;
                case 7:
                    acomodarderecha = 187;

                    break;
                case 10:
                    acomodarderecha = 217;

                    break;
                case 12:
                    acomodarderecha = 247;

                    break;
                default:
                    System.out.println("Algo salio terriblemente mal");
            }

            double[] apoyoDerecha1 = {1450 * sizePlata + acomodarderecha, 1340 * sizePlata + acomodarderecha, 1360 * sizePlata + acomodarderecha, 1450 * sizePlata + acomodarderecha};
            double[] apoyoDerecha2 = {200 * sizePlata, 200 * sizePlata, 230 * sizePlata, 230 * sizePlata};

            gc.setStroke(ColorInterno);
            gc.fillPolygon(apoyoDerecha1, apoyoDerecha2, 4);

            gc.setLineWidth(5.5);
            gc.setStroke(ColorExterno);
            gc.strokeLine(1450 * sizePlata + acomodarderecha, 200 * sizePlata, 1340 * sizePlata + acomodarderecha, 200 * sizePlata);
            gc.strokeLine(1340 * sizePlata + acomodarderecha, 200 * sizePlata, 1360 * sizePlata + acomodarderecha, 230 * sizePlata);
            gc.strokeLine(1360 * sizePlata + acomodarderecha, 230 * sizePlata, 1450 * sizePlata + acomodarderecha, 230 * sizePlata);

        }
    }
}
