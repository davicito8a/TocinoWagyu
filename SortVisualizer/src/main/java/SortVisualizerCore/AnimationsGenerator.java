package SortVisualizerCore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
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
    
    public AnimationsGenerator(ArrayList<Integer> numbers, ArrayList<StackPane> stackpanes){
        this.numbers = numbers;
        this.boxes = stackpanes;
        

        
        switch (Main.sortType){
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
                pseudocode.selectCocktailSortLines();

                break;
            default:
                break;
        }
    }
   
    private void getInsertionSortAnimations(){
        int j = 1; 
        
        for(int i = 1; i < numbers.size(); i++){ 
            
            if (j > 0){ 
                translateAnimations.add(mover.moveInX(Main.coordinates.get(j - 1), 
                        Main.coordinates.get(i - 1), craneUpperBox1,
                        rope1,magnet1));
            } else if (j == 0){ 
                translateAnimations.add(mover.moveInX(Main.coordinates.get(j + 1), 
                        Main.coordinates.get(i - 1), 
                        craneUpperBox1,rope1,magnet1)); 
            }
            translateAnimations.add(mover.moveInX(Main.coordinates.get(j), 
                    Main.coordinates.get(i), 
                    craneUpperBox2,rope2,magnet2)); 
            
            j = i; 
        
            StackPane stackpane = boxes.get(i); 
            int currentNumber = numbers.get(i); 
        
            translateAnimations.add(parallelAnimations(mover.moveInY(
                    0.65* Main.windowHeight, 0.65* Main.windowHeight - 115, 
                    boxes.get(i)), pseudocode.changeLabelProperties(1, 
                    "for i = " + i))); 
            
            while(j > 0 && currentNumber < numbers.get(j - 1)){ 
                translateAnimations.add(parallelAnimations(mover.moveInX(Main.coordinates.get(j - 1), 
                        Main.coordinates.get(j), 
                        boxes.get(j - 1), craneUpperBox1, rope1, magnet1), pseudocode.changeLabelProperties(2, 
                            "\twhile(" + currentNumber + " < numbers[" + (j - 1) + "])\n\t\tnumbers[" + j + "] = numbers [" + (j - 1) + "]"))); 
                
                boxes.set(j, boxes.get(j - 1)); 
                numbers.set(j, numbers.get(j - 1)); 
            
                if(j - 2 >= 0){
                    translateAnimations.add(mover.moveInX(Main.coordinates.get(j), 
                    Main.coordinates.get(j - 2), 
                    craneUpperBox1,rope1,magnet1));
                }  
               

                j--; 
            }
            if(i != j){
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
    
    private void getBubbleSortAnimations(){
        for(int i = numbers.size() - 1; i > 0; i--){
            translateAnimations.add(pseudocode.changeLabelProperties(1, "for i = " + i, 200));
            for(int j = 0; j < i; j++){
                translateAnimations.add(pseudocode.changeLabelProperties(2, "\tfor j = " + j, 200));
                translateAnimations.add(pseudocode.changeLabelProperties(3, "\t\tif(numbers[" + (j + 1) + "] < numbers [" + j + "])", 200));
                if(numbers.get(j + 1) < numbers.get(j)){                    
                    int currentNumber = numbers.get(j);
                    StackPane stackpane = boxes.get(j);
                    translateAnimations.add(mover.moveInX2(0, Main.coordinates.get(j + 1), craneUpperBox2, rope2, magnet2)); 
                    translateAnimations.add(pseudocode.changeLabelProperties(4, "\t\t\tswap(" + j + ", " + (j + 1) + ")" , 1, true));
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
                    translateAnimations.add(mover.moveInX2(0, 50 , craneUpperBox2, rope2, magnet2));
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
    
    private void getCocktailSortAnimations(){
        boolean swapped = true;
        int start = 0;
        int end = numbers.size();
        
        
        while(swapped){
            translateAnimations.add(pseudocode.changeLabelProperties(1, "while(swapped)", 200));
            swapped = false;
            translateAnimations.add(pseudocode.changeLabelProperties(2, "\tswapped = false", 200));
            
            for(int i = start; i < end - 1; i++){
                translateAnimations.add(pseudocode.changeLabelProperties(3, "\tfor i = " + i, 200));
                translateAnimations.add(pseudocode.changeLabelProperties(4, "\t\tif(numbers[" + (i + 1) + "] < numbers [" + i + "])", 200));
                if(numbers.get(i + 1) < numbers.get(i)){
                    int currentNumber = numbers.get(i);
                    StackPane stackpane = boxes.get(i);
                    
                    translateAnimations.add(mover.moveInX2(0, Main.coordinates.get(i + 1), craneUpperBox2, rope2, magnet2)); 
                    translateAnimations.add(pseudocode.changeLabelProperties(5, "\t\t\tswap(" + i + ", " + (i + 1) + ")" , 1, true));
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
                    translateAnimations.add(mover.moveInX2(0, 50 , craneUpperBox2, rope2, magnet2));
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
            if(swapped == false){
                translateAnimations.add(pseudocode.changeLabelProperties(8, "\t\tbreak", 200));
                break; 
            }
            
            swapped = false;
            translateAnimations.add(pseudocode.changeLabelProperties(9, "\tswapped = false", 200));
            
            end = end - 1;
            
            for(int i = end - 1; i >= start; i--){
                translateAnimations.add(pseudocode.changeLabelProperties(10, "\tfor i = " + i, 200));
                translateAnimations.add(pseudocode.changeLabelProperties(11, "\t\tif(numbers[" + (i + 1) + "] < numbers [" + i + "])", 200));
                if(numbers.get(i + 1) < numbers.get(i)){
                    int currentNumber = numbers.get(i);
                    StackPane stackpane = boxes.get(i);
                    
                    translateAnimations.add(mover.moveInX2(0, Main.coordinates.get(i + 1), craneUpperBox2, rope2, magnet2)); 
                    translateAnimations.add(pseudocode.changeLabelProperties(12, "\t\t\tswap(" + i + ", " + (i + 1) + ")" , 1, true));
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
                            mover.moveInY2(0, 35 +230, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 10 - 35)));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 35, magnet2),
                            mover.moveRope(rope2, 0, 0.65 * Main.windowHeight - 230 - 10 - 35)));
                    translateAnimations.add(mover.moveInX2(0, 1350 , craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(mover.moveInX2(0, Main.coordinates.get(i), boxes.get(i + 1), craneUpperBox2, rope2, magnet2));
                    translateAnimations.add(parallelAnimations(
                            mover.moveInY2(0, 0.65 * Main.windowHeight, boxes.get(i + 1)),
                            mover.moveInY2(0, 35 + 230 , magnet2),
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
    
    private void getSelectionSortAnimations(){
        double angle = -53;
        translateAnimations.add(moverEnLinea(boxes, 4.37, 0));
        translateAnimations.addAll(cambioDireccion(boxes, angle));
        
        System.out.println(numbers.toString());
        for (int i = 0; i < numbers.size() - 1; i++){
            int min_idx = i;
            for (int j = i + 1; j < numbers.size(); j++){
                if (numbers.get(j) < numbers.get(min_idx)){
                    min_idx = j;
                }
            }
            
            ArrayList<StackPane> vagonesAntesDeMinimo = new ArrayList();
            vagonesAntesDeMinimo.addAll(boxes.subList(0, min_idx));
            
            translateAnimations.add(moverEnLinea(vagonesAntesDeMinimo, -1, angle));
            Collections.reverse(vagonesAntesDeMinimo);
            translateAnimations.addAll(cambioDireccion(vagonesAntesDeMinimo, -angle));
            
            TranslateTransition moverCentro = new TranslateTransition();
            moverCentro.setNode(boxes.get(min_idx));
            moverCentro.setToX(1000);
            moverCentro.setToY(270);
            translateAnimations.add(moverCentro);
            break;

        }
        System.out.println(numbers.toString());
    }
    
    private Animation moverEnLinea(List<StackPane> vagones, double desplazamientoPosicion, double angulo){
        ArrayList<Animation> movimientosVagones = new ArrayList();
        
        for(int i = 0; i < vagones.size(); i++){
            TranslateTransition moverVagon = new TranslateTransition();
            moverVagon.setNode(vagones.get(i));
            moverVagon.setByX(desplazamientoPosicion * (Main.separation + Main.squareDimension) * Math.cos(angulo * Math.PI/180));
            moverVagon.setByY(desplazamientoPosicion * (Main.separation + Main.squareDimension) * Math.sin(angulo * Math.PI/180));
            moverVagon.setDuration(Duration.millis(200));
            movimientosVagones.add(moverVagon);
        }
        
        ParallelTransition moverEnConjunto = new ParallelTransition();
        moverEnConjunto.getChildren().addAll(movimientosVagones);
        
        return moverEnConjunto;
    }
    
    private ArrayList<Animation> cambioDireccion(List<StackPane> vagones, double angulo){
        ArrayList<Animation> movimientosVagones = new ArrayList();
        
        for(int i = vagones.size() - 1; i >= 0; i--){
            RotateTransition rotarVagon = new RotateTransition();
            rotarVagon.setNode(vagones.get(i));
            rotarVagon.setByAngle(angulo);
            rotarVagon.setDuration(Duration.millis(25));
            
            movimientosVagones.add(rotarVagon);
            
            ArrayList<Animation> desplazamientosVagones = new ArrayList();
            
            if(angulo < 0){
                desplazamientosVagones.add(moverEnLinea( vagones.subList(i, vagones.size()), 1, angulo));
                desplazamientosVagones.add(moverEnLinea(vagones.subList(0, i), 1, 0));
            } else {
                desplazamientosVagones.add(moverEnLinea( vagones.subList(i, vagones.size()), -1, 0));
                desplazamientosVagones.add(moverEnLinea(vagones.subList(0, i), -1, -angulo));
            }
            
            ParallelTransition moverEnConjunto = new ParallelTransition();
            moverEnConjunto.getChildren().addAll(desplazamientosVagones);
            movimientosVagones.add(moverEnConjunto);
        }
        
        return movimientosVagones;
    }
    
    private void setCrane(){
        int y = 10;
        int y2 = 35;
        
        
        if(Main.sortType==0){
        craneUpperBox1.setTranslateX(Main.coordinates.get(0));
        craneUpperBox1.setTranslateY(y);
        craneUpperBox1.setWidth(40);
        craneUpperBox1.setHeight(20);
        craneUpperBox1.setLayoutX((230/4)/2 - craneUpperBox1.getWidth()/2);
        craneUpperBox1.setFill(Color.YELLOW);
        craneUpperBox1.setStroke(Color.BLACK);
        craneUpperBox1.setStrokeWidth(3);
        }
        
        craneUpperBox2.setTranslateX(Main.coordinates.get(1));
        craneUpperBox2.setTranslateY(y2);
        craneUpperBox2.setWidth(40);
        craneUpperBox2.setHeight(20);
        craneUpperBox2.setLayoutX((230/4)/2 - craneUpperBox2.getWidth()/2);
        craneUpperBox2.setFill(Color.GREEN);
        craneUpperBox2.setStroke(Color.BLACK);
        craneUpperBox2.setStrokeWidth(3);
        
        if(Main.sortType==0){
        magnet1.setTranslateX(Main.coordinates.get(0));
        magnet1.setTranslateY(y);
        magnet1.setWidth(25);
        magnet1.setHeight(10);
        magnet1.setLayoutX((230/4)/2- magnet1.getWidth()/2);
        magnet1.setFill(Color.BLUE);
        magnet1.setStroke(Color.BLACK);
        magnet1.setStrokeWidth(3);
        }
        
        magnet2.setTranslateX(Main.coordinates.get(1));
        if(Main.sortType==0){
            magnet2.setTranslateY(y2);
        }else{
            magnet2.setTranslateY(y2+230);
        }
        magnet2.setWidth(25);
        magnet2.setHeight(10);
        magnet2.setLayoutX((230/4)/2-magnet2.getWidth()/2);
        magnet2.setFill(Color.BLUE);
        magnet2.setStroke(Color.BLACK);
        magnet2.setStrokeWidth(3);
        
        if(Main.sortType == 1 || Main.sortType == 2){
            //magnet1.setLayoutY(0.65*Main.windowHeight -  2*Main.squareDimension - magnet1.getHeight() - y);
            magnet2.setLayoutY(0.65*Main.windowHeight-230- magnet2.getHeight() - y2);
            //rope1 = new Line(Main.squareDimension/2,0,Main.squareDimension/2,0.65*Main.windowHeight - 2 * Main.squareDimension - magnet1.getHeight() - y);
            
        } else {
            magnet1.setLayoutY(0.65*Main.windowHeight - magnet1.getHeight() - y);
            magnet2.setLayoutY(0.65*Main.windowHeight-115 - magnet2.getHeight() - y2);
            rope1 = new Line((230/4)/2,0,(230/4)/2,0.65*Main.windowHeight - y);
            
        }
        if(Main.sortType==0){
            rope1.setTranslateX(Main.coordinates.get(0));
            rope1.setTranslateY(y);
        }
 
        if(Main.sortType==0){
            rope2 = new Line((230/4)/2,0,(230/4)/2,0.65*Main.windowHeight - 115 - magnet2.getHeight() - 35);
        }else{
            rope2 = new Line((230/4)/2,0,(230/4)/2,0.65*Main.windowHeight - magnet2.getHeight() - y2);
        }
        //rope1.setTranslateX(Main.coordinates.get(0));
        //rope1.setTranslateY(y);
        rope2.setTranslateX(Main.coordinates.get(1));
        rope2.setTranslateY(y2);
        
    }
    
    private ParallelTransition parallelAnimations(Animation... animations){
        ParallelTransition parallelAnimations = new ParallelTransition();
        parallelAnimations.getChildren().addAll(animations);
        return parallelAnimations;
    }
    
    private ParallelTransition parallelAnimations(Animation animation1, Animation animation2){
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