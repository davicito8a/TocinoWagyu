package SortVisualizerCore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class AnimationsGenerator {

    private final Mover mover = new Mover();

    private final ArrayList<Integer> numbers;
    private final ArrayList<StackPane> boxes;

    private final ArrayList<Animation> translateAnimations = new ArrayList();
    private final ArrayList<Animation> pseudocodeAnimations = new ArrayList();

    private VBox pseudocodeBox = new VBox();
    private Pseudocode pseudocode = new Pseudocode(pseudocodeBox);

    Rectangle craneUpperBox1 = new Rectangle();
    Rectangle craneUpperBox2 = new Rectangle();
    Line rope1;
    Line rope2;
    Rectangle magnet1 = new Rectangle();
    Rectangle magnet2 = new Rectangle();

    public AnimationsGenerator(ArrayList<Integer> numbers, ArrayList<StackPane> stackpanes) {
        this.numbers = numbers;
        this.boxes = stackpanes;

        switch (Main.sortType) {
            case 0:
                setCrane();
                getInsertionSortAnimations();
                pseudocode.selectInsertionSortLines();
                break;
            case 1:
                setCrane();
                getBubbleSortAnimations();
                pseudocode.selectBubbleSortLines();
                break;
            case 2:
                setCrane();
                getCocktailSortAnimations();
                pseudocode.selectCocktailSortLines();
                break;
            case 3:
                getSelectionSortAnimations();
                pseudocode.selectSelecionSortLines();
                break;
            default:
                break;
        }
    }

    private void getInsertionSortAnimations() {
        int j = 1;

        for (int i = 1; i < numbers.size(); i++) {

            if (j > 0) {
                translateAnimations.add(mover.moveInX(Main.coordinates.get(j - 1),
                        Main.coordinates.get(i - 1), craneUpperBox1,
                        rope1, magnet1));
            } else if (j == 0) {
                translateAnimations.add(mover.moveInX(Main.coordinates.get(j + 1),
                        Main.coordinates.get(i - 1),
                        craneUpperBox1, rope1, magnet1));
            }
            translateAnimations.add(mover.moveInX(Main.coordinates.get(j),
                    Main.coordinates.get(i),
                    craneUpperBox2, rope2, magnet2));

            j = i;

            StackPane stackpane = boxes.get(i);
            int currentNumber = numbers.get(i);

            translateAnimations.add(parallelAnimations(mover.moveInY(
                    0.65 * Main.windowHeight, 0.65 * Main.windowHeight - 115,
                    boxes.get(i)), pseudocode.changeLabelProperties(1,
                    "for i = " + i)));

            while (j > 0 && currentNumber < numbers.get(j - 1)) {
                translateAnimations.add(parallelAnimations(mover.moveInX(Main.coordinates.get(j - 1),
                        Main.coordinates.get(j),
                        boxes.get(j - 1), craneUpperBox1, rope1, magnet1), pseudocode.changeLabelProperties(2,
                        "\twhile(" + currentNumber + " < numbers[" + (j - 1) + "])\n\t\tnumbers[" + j + "] = numbers [" + (j - 1) + "]")));

                boxes.set(j, boxes.get(j - 1));
                numbers.set(j, numbers.get(j - 1));

                if (j - 2 >= 0) {
                    translateAnimations.add(mover.moveInX(Main.coordinates.get(j),
                            Main.coordinates.get(j - 2),
                            craneUpperBox1, rope1, magnet1));
                }

                j--;
            }
            if (i != j) {
                translateAnimations.add(mover.moveInX(Main.coordinates.get(i),
                        Main.coordinates.get(j),
                        stackpane, craneUpperBox2, rope2, magnet2));
            }

            translateAnimations.add(parallelAnimations(mover.moveInY(0.65 * Main.windowHeight - 115,
                    0.65 * Main.windowHeight,
                    stackpane), pseudocode.changeLabelProperties(3,
                            "\tnumbers[" + j + "]" + " = " + currentNumber)));

            boxes.set(j, stackpane);
            numbers.set(j, currentNumber);
        }
    }

    private void getBubbleSortAnimations() {
        for (int i = numbers.size() - 1; i > 0; i--) {
            translateAnimations.add(pseudocode.changeLabelProperties(1, "for i = " + i, 200));
            for (int j = 0; j < i; j++) {
                translateAnimations.add(pseudocode.changeLabelProperties(2, "\tfor j = " + j, 200));
                translateAnimations.add(pseudocode.changeLabelProperties(3, "\t\tif(numbers[" + (j + 1) + "] < numbers [" + j + "])", 200));
                if (numbers.get(j + 1) < numbers.get(j)) {
                    int currentNumber = numbers.get(j);
                    StackPane stackpane = boxes.get(j);
                    translateAnimations.add(mover.moveInX2(0, Main.coordinates.get(j + 1), craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(pseudocode.changeLabelProperties(4, "\t\t\tswap(" + j + ", " + (j + 1) + ")", 1, true));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 0.65 * Main.windowHeight - 230, boxes.get(j + 1)),
                            mover.moveInY2(0, 35, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 230 - 10 - 35)));
                    translateAnimations.add(mover.moveInX2(0, 50, boxes.get(j + 1), craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(mover.moveInX2(0, Main.coordinates.get(j), craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 35 + 230, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 10 - 35)));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 0.65 * Main.windowHeight - 230, boxes.get(j)),
                            mover.moveInY2(0, 35, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 230 - 10 - 35)));
                    translateAnimations.add(mover.moveInX2(0, Main.coordinates.get(j + 1), boxes.get(j), craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 0.65 * Main.windowHeight, boxes.get(j)),
                            mover.moveInY2(0, 35 + 230, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 10 - 35)));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 35, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 230 - 10 - 35)));
                    translateAnimations.add(mover.moveInX2(0, 50, craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(mover.moveInX2(0, Main.coordinates.get(j), boxes.get(j + 1), craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 0.65 * Main.windowHeight, boxes.get(j + 1)),
                            mover.moveInY2(0, 35 + 230, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 10 - 35)));
                    translateAnimations.add(pseudocode.unselectLine(4));

                    numbers.set(j, numbers.get(j + 1));
                    boxes.set(j, boxes.get(j + 1));

                    numbers.set(j + 1, currentNumber);
                    boxes.set(j + 1, stackpane);
                }
            }
        }
    }

    private void getCocktailSortAnimations() {
        boolean swapped = true;
        int start = 0;
        int end = numbers.size();

        while (swapped) {
            translateAnimations.add(pseudocode.changeLabelProperties(1, "while(swapped)", 200));
            swapped = false;
            translateAnimations.add(pseudocode.changeLabelProperties(2, "\tswapped = false", 200));

            for (int i = start; i < end - 1; i++) {
                translateAnimations.add(pseudocode.changeLabelProperties(3, "\tfor i = " + i, 200));
                translateAnimations.add(pseudocode.changeLabelProperties(4, "\t\tif(numbers[" + (i + 1) + "] < numbers [" + i + "])", 200));
                if (numbers.get(i + 1) < numbers.get(i)) {
                    int currentNumber = numbers.get(i);
                    StackPane stackpane = boxes.get(i);

                    translateAnimations.add(mover.moveInX2(0, Main.coordinates.get(i + 1), craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(pseudocode.changeLabelProperties(5, "\t\t\tswap(" + i + ", " + (i + 1) + ")", 1, true));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 0.65 * Main.windowHeight - 230, boxes.get(i + 1)),
                            mover.moveInY2(0, 35, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 230 - 10 - 35)));
                    translateAnimations.add(mover.moveInX2(0, 50, boxes.get(i + 1), craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(mover.moveInX2(0, Main.coordinates.get(i), craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 35 + 230, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 10 - 35)));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 0.65 * Main.windowHeight - 230, boxes.get(i)),
                            mover.moveInY2(0, 35, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 230 - 10 - 35)));
                    translateAnimations.add(mover.moveInX2(0, Main.coordinates.get(i + 1), boxes.get(i), craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 0.65 * Main.windowHeight, boxes.get(i)),
                            mover.moveInY2(0, 35 + 230, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 10 - 35)));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 35, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 230 - 10 - 35)));
                    translateAnimations.add(mover.moveInX2(0, 50, craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(mover.moveInX2(0, Main.coordinates.get(i), boxes.get(i + 1), craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 0.65 * Main.windowHeight, boxes.get(i + 1)),
                            mover.moveInY2(0, 35 + 230, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 10 - 35)));
                    translateAnimations.add(pseudocode.unselectLine(5));

                    numbers.set(i, numbers.get(i + 1));
                    boxes.set(i, boxes.get(i + 1));

                    numbers.set(i + 1, currentNumber);
                    boxes.set(i + 1, stackpane);

                    swapped = true;
                    translateAnimations.add(pseudocode.changeLabelProperties(6, "\t\t\tswapped = true", 200));
                }
            }

            translateAnimations.add(pseudocode.changeLabelProperties(7, "\tif(swapped = false)", 200));
            if (swapped == false) {
                translateAnimations.add(pseudocode.changeLabelProperties(8, "\t\tbreak", 200));
                break;
            }

            swapped = false;
            translateAnimations.add(pseudocode.changeLabelProperties(9, "\tswapped = false", 200));

            end = end - 1;

            for (int i = end - 1; i >= start; i--) {
                translateAnimations.add(pseudocode.changeLabelProperties(10, "\tfor i = " + i, 200));
                translateAnimations.add(pseudocode.changeLabelProperties(11, "\t\tif(numbers[" + (i + 1) + "] < numbers [" + i + "])", 200));
                if (numbers.get(i + 1) < numbers.get(i)) {
                    int currentNumber = numbers.get(i);
                    StackPane stackpane = boxes.get(i);

                    translateAnimations.add(mover.moveInX2(0, Main.coordinates.get(i + 1), craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(pseudocode.changeLabelProperties(12, "\t\t\tswap(" + i + ", " + (i + 1) + ")", 1, true));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 0.65 * Main.windowHeight - 230, boxes.get(i + 1)),
                            mover.moveInY2(0, 35, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 230 - 10 - 35)));
                    translateAnimations.add(mover.moveInX2(0, 1350, boxes.get(i + 1), craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(mover.moveInX2(0, Main.coordinates.get(i), craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 35 + 230, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 10 - 35)));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 0.65 * Main.windowHeight - 230, boxes.get(i)),
                            mover.moveInY2(0, 35, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 230 - 10 - 35)));
                    translateAnimations.add(mover.moveInX2(0, Main.coordinates.get(i + 1), boxes.get(i), craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 0.65 * Main.windowHeight, boxes.get(i)),
                            mover.moveInY2(0, 35 + 230, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 10 - 35)));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 35, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 230 - 10 - 35)));
                    translateAnimations.add(mover.moveInX2(0, 1350, craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(mover.moveInX2(0, Main.coordinates.get(i), boxes.get(i + 1), craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 0.65 * Main.windowHeight, boxes.get(i + 1)),
                            mover.moveInY2(0, 35 + 230, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 10 - 35)));
                    translateAnimations.add(pseudocode.unselectLine(12));

                    numbers.set(i, numbers.get(i + 1));
                    boxes.set(i, boxes.get(i + 1));

                    numbers.set(i + 1, currentNumber);
                    boxes.set(i + 1, stackpane);

                    swapped = true;
                    translateAnimations.add(pseudocode.changeLabelProperties(13, "\t\t\tswapped = true", 200));
                }
            }

            start = start + 1;
        }

    }

    private void getSelectionSortAnimations() {
        double angle = -25.22;

        // Mover vagones a vía superior
        /*if(numbers.size()>=16 && numbers.size()<=19){
            translateAnimations.add(moverEnLinea(boxes, 0.5, 0));
        }
        else if(numbers.size()>=20 && numbers.size()<=29){
            translateAnimations.add(moverEnLinea(boxes, 0, 0));
        }
        else if(numbers.size()>=30 && numbers.size()<=39){
            translateAnimations.add(moverEnLinea(boxes, 0, 0));
        }
        else if(numbers.size()>=40 && numbers.size()<=47){
            translateAnimations.add(moverEnLinea(boxes, 0, 0));
        }
        else if(numbers.size()>=48 && numbers.size()<=64){
            translateAnimations.add(moverEnLinea(boxes, 0, 0));
        }*/
        ArrayList<StackPane> Salvadore = new ArrayList();
        Salvadore.addAll(boxes.subList(0, boxes.size() - 2));
        Salvadore.add(0, boxes.get(boxes.size() - 2));

        translateAnimations.add(moverEnLinea(boxes.subList(boxes.size() - 1, boxes.size()), 3.37, 0));
        translateAnimations.addAll(cambioDireccion(boxes.subList(boxes.size() - 1, boxes.size()), angle, 0, angle, 1));
        translateAnimations.add(moverEnLinea(boxes.subList(boxes.size() - 1, boxes.size()), boxes.size() - 1, angle));

        translateAnimations.add(moverEnLinea(Salvadore, 4.37, 0));
        translateAnimations.addAll(cambioDireccion(Salvadore, angle, 0, angle, 1));

        System.out.println(numbers.toString());
        for (int i = 0; i < numbers.size(); i++) {
            //translateAnimations.add(pseudocode.changeLabelProperties(1, "for i = " + i, 200));
            int min_idx = i;
            //translateAnimations.add(pseudocode.changeLabelProperties(2, "\tmax_idx = " + min_idx, 200));
            for (int j = i + 1; j < numbers.size(); j++) {
                //translateAnimations.add(pseudocode.changeLabelProperties(3, "\tfor j = " + j, 200));
                //translateAnimations.add(pseudocode.changeLabelProperties(4, "\t\tif(numbers[" + j + "] > numbers[" + min_idx + "]", 200));
                if (numbers.get(j) > numbers.get(min_idx)) {
                    min_idx = j;
                    //translateAnimations.add(pseudocode.changeLabelProperties(5, "\t\t\tmax_idx = " + min_idx, 100));
                }
            }

            //translateAnimations.add(pseudocode.changeLabelProperties(6, "\tremoveInCurrentArray(" + min_idx + ")" , 1, true));
            //translateAnimations.add(pseudocode.changeLabelProperties(7, "\taddInNewArray(" + (numbers.size() - 1 - i) + ", " + numbers.get(min_idx) + ")" , 1, true));
            ArrayList<StackPane> vagonesNoOrdenados = new ArrayList();
            vagonesNoOrdenados.addAll(boxes.subList(0, boxes.size() - 2));

            // Sacar todos los vagones ubicados antes del mínimo de la vía superior y meterlos en la vía central
            ArrayList<StackPane> vagonesConMinimo = new ArrayList();
            vagonesConMinimo.addAll(vagonesNoOrdenados.subList(i, min_idx + 1));
            vagonesConMinimo.add(0, boxes.get(boxes.size() - 2));

            // Desplazar una posición hacia atrás en la vía superior
            translateAnimations.add(moverEnLinea(vagonesConMinimo, -1, angle));
            Collections.reverse(vagonesConMinimo);
            // meter en vía central
            translateAnimations.addAll(cambioDireccion(vagonesConMinimo, -angle, angle, 0, -1));
            translateAnimations.add(moverEnLinea(vagonesConMinimo, 1, 0));
            Collections.reverse(vagonesConMinimo);
            translateAnimations.addAll(cambioDireccion(vagonesConMinimo, -angle, 0, -angle, 1));
            translateAnimations.add(moverEnLinea(vagonesConMinimo, boxes.size() - 2 - vagonesConMinimo.size() - i + 1, -angle));

            ArrayList<StackPane> vagonesSinMinimo = new ArrayList();
            vagonesSinMinimo.addAll(vagonesConMinimo.subList(0, vagonesConMinimo.size() - 1));
            translateAnimations.add(moverEnLinea(vagonesSinMinimo, -(boxes.size() - 2 - vagonesConMinimo.size() - i + 2), -angle));//Esta bien
            Collections.reverse(vagonesSinMinimo);
            translateAnimations.addAll(cambioDireccion(vagonesSinMinimo, angle, -angle, 0, -1));
            translateAnimations.add(moverEnLinea(vagonesSinMinimo, -(boxes.size() - 2 - vagonesSinMinimo.size() + 1), 0));

            ArrayList<StackPane> vagonesRestantes = new ArrayList();
            vagonesRestantes.addAll(vagonesNoOrdenados.subList(min_idx + 1, vagonesNoOrdenados.size()));
            vagonesRestantes.add(boxes.get(boxes.size() - 1));
            Collections.reverse(vagonesRestantes);
            translateAnimations.add(moverEnLinea(vagonesRestantes, -(vagonesConMinimo.size() + 1), angle));
            translateAnimations.addAll(cambioDireccion(vagonesRestantes, -angle, angle, 0, -1));
            translateAnimations.add(moverEnLinea(vagonesRestantes, -i, 0));

            //vagonesRestantes.remove(0);
            /*translateAnimations.add(moverEnLinea(boxes.subList(boxes.size()-1,boxes.size()), 0, 0));
            translateAnimations.addAll(cambioDireccion(boxes.subList(boxes.size()-1,boxes.size()), angle, 0, angle, 1));
            translateAnimations.add(moverEnLinea(boxes.subList(boxes.size()-1,boxes.size()),boxes.size()-i-2,angle));
             */
            ArrayList<StackPane> vagonesEnProceso = new ArrayList();
            Collections.reverse(vagonesSinMinimo);
            Collections.reverse(vagonesRestantes);

            vagonesEnProceso.addAll(vagonesSinMinimo);
            vagonesEnProceso.addAll(vagonesRestantes);

            translateAnimations.add(moverEnLinea(vagonesEnProceso, i + 1, 0));
            translateAnimations.addAll(cambioDireccion(vagonesEnProceso, angle, 0, angle, 1));

            /*
            // Sacar el vagon mínimo junto con todos los que le siguen de la vía superior y meterlos en la vía inferior.
            ArrayList<StackPane> vagonesDespuesDeMinimo = new ArrayList();
            vagonesDespuesDeMinimo.addAll(vagonesNoOrdenados.subList(min_idx, vagonesNoOrdenados.size()));
            // Mover los vagones la cantidad de posiciones equivalente al número de vagones retirados con anterioridad.
            translateAnimations.add(moverEnLinea(vagonesDespuesDeMinimo, -(vagonesConMinimo.size() + 1), angle));
            Collections.reverse(vagonesDespuesDeMinimo);
            // Cambio de dirección de los vagones sobrantes hacia la vía inferior.
            translateAnimations.addAll(cambioDireccion(vagonesDespuesDeMinimo, 2*-1*angle-360, angle, -1*angle-180, -1));
            // Mover los vagones sobrantes la cantidad de posiciones equivalente al número de vagones retirados con anterioridad.
            translateAnimations.add(moverEnLinea(vagonesDespuesDeMinimo, vagonesConMinimo.size() + 1, -angle));
            Collections.reverse(vagonesConMinimo);
            
            translateAnimations.add(parallelAnimations(pseudocode.unselectLine(6), pseudocode.unselectLine(7)));
           
            //ArrayList<StackPane> vagonesSinMinimo = new ArrayList();
            vagonesSinMinimo.addAll(vagonesDespuesDeMinimo.subList(0, vagonesDespuesDeMinimo.size() - 1));
            // Retroceso de los vagones la misma cantidad de posiciones que los vagones retirados con anterioridad.
            translateAnimations.add(moverEnLinea(vagonesSinMinimo, -(vagonesConMinimo.size() + 2), -angle));
            Collections.reverse(vagonesSinMinimo);
            // Cambio de dirección hacia vía superior
            translateAnimations.addAll(cambioDireccion(vagonesSinMinimo, 360-2*-1*angle, angle*-1-180, angle, 1));
            // Movimiento de vagones en vía superior la misma cantidad de posiciones que el número de vagones retirados con anterioridad.
            translateAnimations.add(moverEnLinea(vagonesSinMinimo, vagonesConMinimo.size(), angle));
            
            // Reinserción de los vagones retirados.
            translateAnimations.add(moverEnLinea(vagonesConMinimo, 1, 0));
            translateAnimations.addAll(cambioDireccion(vagonesConMinimo, angle, 0, angle, 1));*/
            System.out.println(numbers.toString());
            int temp = numbers.get(min_idx);
            StackPane stack = boxes.get(min_idx);
            numbers.remove(min_idx);
            boxes.remove(min_idx);
            numbers.add(i, temp);
            boxes.add(i, stack);
            System.out.println(numbers.toString());
        }
        System.out.println(numbers.toString());
    }

    private Animation moverEnLinea(List<StackPane> vagones, double desplazamientoPosicion, double angulo) {
        ArrayList<Animation> movimientosVagones = new ArrayList();

        for (int i = 0; i < vagones.size(); i++) {
            TranslateTransition moverVagon = new TranslateTransition();
            moverVagon.setNode(vagones.get(i));
            moverVagon.setByX(desplazamientoPosicion * (Main.separation + Main.squareDimension) * Math.cos(angulo * Math.PI / 180));
            moverVagon.setByY(desplazamientoPosicion * (Main.separation + Main.squareDimension) * Math.sin(angulo * Math.PI / 180));
            moverVagon.setDuration(Duration.millis(200));
            movimientosVagones.add(moverVagon);
        }

        ParallelTransition moverEnConjunto = new ParallelTransition();
        moverEnConjunto.getChildren().addAll(movimientosVagones);

        return moverEnConjunto;
    }

    private ArrayList<Animation> cambioDireccion(List<StackPane> vagones, double anguloGiro, double anguloMovimiento1, double anguloMovimiento2, double desplazamientoPosicion) {
        ArrayList<Animation> movimientosVagones = new ArrayList();

        for (int i = vagones.size() - 1; i >= 0; i--) {
            RotateTransition rotarVagon = new RotateTransition();
            rotarVagon.setNode(vagones.get(i));
            rotarVagon.setByAngle(anguloGiro);
            rotarVagon.setDuration(Duration.millis(25));

            movimientosVagones.add(rotarVagon);

            ArrayList<Animation> desplazamientosVagones = new ArrayList();

            desplazamientosVagones.add(moverEnLinea(vagones.subList(i, vagones.size()), desplazamientoPosicion, anguloMovimiento2));
            desplazamientosVagones.add(moverEnLinea(vagones.subList(0, i), desplazamientoPosicion, anguloMovimiento1));

            ParallelTransition moverEnConjunto = new ParallelTransition();
            moverEnConjunto.getChildren().addAll(desplazamientosVagones);
            movimientosVagones.add(moverEnConjunto);
        }

        return movimientosVagones;
    }

    private void setCrane() {
        int y = 10;
        int y2 = 35;

        if (Main.sortType == 0) {
            craneUpperBox1.setTranslateX(Main.coordinates.get(0));
            craneUpperBox1.setTranslateY(y);
            craneUpperBox1.setWidth(40);
            craneUpperBox1.setHeight(20);
            craneUpperBox1.setLayoutX((230 / 4) / 2 - craneUpperBox1.getWidth() / 2);
            craneUpperBox1.setFill(Color.YELLOW);
            craneUpperBox1.setStroke(Color.BLACK);
            craneUpperBox1.setStrokeWidth(3);
        }

        craneUpperBox2.setTranslateX(Main.coordinates.get(1));
        craneUpperBox2.setTranslateY(y2);
        craneUpperBox2.setWidth(40);
        craneUpperBox2.setHeight(20);
        craneUpperBox2.setLayoutX((230 / 4) / 2 - craneUpperBox2.getWidth() / 2);
        craneUpperBox2.setFill(Color.GREEN);
        craneUpperBox2.setStroke(Color.BLACK);
        craneUpperBox2.setStrokeWidth(3);

        if (Main.sortType == 0) {
            magnet1.setTranslateX(Main.coordinates.get(0));
            magnet1.setTranslateY(y);
            magnet1.setWidth(25);
            magnet1.setHeight(10);
            magnet1.setLayoutX((230 / 4) / 2 - magnet1.getWidth() / 2);
            magnet1.setFill(Color.BLUE);
            magnet1.setStroke(Color.BLACK);
            magnet1.setStrokeWidth(3);
        }

        magnet2.setTranslateX(Main.coordinates.get(1));
        if (Main.sortType == 0) {
            magnet2.setTranslateY(y2);
        } else {
            magnet2.setTranslateY(y2 + 230);
        }
        magnet2.setWidth(25);
        magnet2.setHeight(10);
        magnet2.setLayoutX((230 / 4) / 2 - magnet2.getWidth() / 2);
        magnet2.setFill(Color.BLUE);
        magnet2.setStroke(Color.BLACK);
        magnet2.setStrokeWidth(3);

        if (Main.sortType == 1 || Main.sortType == 2) {
            //magnet1.setLayoutY(0.65*Main.windowHeight -  2*Main.squareDimension - magnet1.getHeight() - y);
            magnet2.setLayoutY(0.65 * Main.windowHeight - 230 - magnet2.getHeight() - y2);
            //rope1 = new Line(Main.squareDimension/2,0,Main.squareDimension/2,0.65*Main.windowHeight - 2 * Main.squareDimension - magnet1.getHeight() - y);

        } else {
            magnet1.setLayoutY(0.65 * Main.windowHeight - magnet1.getHeight() - y);
            magnet2.setLayoutY(0.65 * Main.windowHeight - 115 - magnet2.getHeight() - y2);
            rope1 = new Line((230 / 4) / 2, 0, (230 / 4) / 2, 0.65 * Main.windowHeight - y);

        }
        if (Main.sortType == 0) {
            rope1.setTranslateX(Main.coordinates.get(0));
            rope1.setTranslateY(y);
        }

        if (Main.sortType == 0) {
            rope2 = new Line((230 / 4) / 2, 0, (230 / 4) / 2, 0.65 * Main.windowHeight - 115 - magnet2.getHeight() - 35);
        } else {
            rope2 = new Line((230 / 4) / 2, 0, (230 / 4) / 2, 0.65 * Main.windowHeight - magnet2.getHeight() - y2);
        }
        //rope1.setTranslateX(Main.coordinates.get(0));
        //rope1.setTranslateY(y);
        rope2.setTranslateX(Main.coordinates.get(1));
        rope2.setTranslateY(y2);

    }

    private ParallelTransition parallelAnimations(Animation... animations) {
        ParallelTransition parallelAnimations = new ParallelTransition();
        parallelAnimations.getChildren().addAll(animations);
        return parallelAnimations;
    }

    private ParallelTransition parallelAnimations(Animation animation1, Animation animation2) {
        ParallelTransition parallelAnimations = new ParallelTransition();
        parallelAnimations.getChildren().addAll(animation1, animation2);
        return parallelAnimations;
    }

    public ArrayList<Animation> getTranslateAnimations() {
        return translateAnimations;
    }

    public ArrayList<Animation> getPseudocodeAnimations() {
        return pseudocodeAnimations;
    }

    public VBox getPseudocodeBox() {
        return pseudocodeBox;
    }

    public Rectangle getCraneUpperBox1() {
        return craneUpperBox1;
    }

    public Rectangle getCraneUpperBox2() {
        return craneUpperBox2;
    }

    public Line getRope1() {
        return rope1;
    }

    public Line getRope2() {
        return rope2;
    }

    public Rectangle getMagnet1() {
        return magnet1;
    }

    public Rectangle getMagnet2() {
        return magnet2;
    }

}
