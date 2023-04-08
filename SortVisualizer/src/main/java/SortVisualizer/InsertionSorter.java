package SortVisualizer;

import java.util.ArrayList;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class InsertionSorter {
    
    private ArrayList<Integer> numbers = new ArrayList();
    private ArrayList<StackPane> stackpanes = new ArrayList();
    
    public InsertionSorter(ArrayList<Integer> numbers, ArrayList<StackPane> stackpanes){
        this.numbers = numbers;
        this.stackpanes = stackpanes;
    }
    
    public ArrayList<Transition> getSortingTransitions(){
        ArrayList<Transition> transitions = new ArrayList();
        
        for(int i = 1; i < numbers.size(); i++){
            int j = i;
            
            StackPane stackpane = stackpanes.get(i);
            int currentNumber = numbers.get(i);
            
            int contador = 0;
            
            TranslateTransition moverAbajo = new TranslateTransition();
            //moverAbajo.setNode(rectangles.get(i));
            moverAbajo.setNode(stackpanes.get(i));
            moverAbajo.setByY(- 1 * 2 * Main.rectangleWidth);
            //moverAbajo.setDuration(Duration.seconds(0.5));
            transitions.add(moverAbajo);
            
            while(j > 0 && currentNumber < numbers.get(j - 1)){
                contador++;
                
                TranslateTransition moverDerecha = new TranslateTransition();
                //moverDerecha.setNode(rectangles.get(j - 1));
                moverDerecha.setNode(stackpanes.get(j - 1));
                moverDerecha.setByX(Main.separation + Main.rectangleWidth);
                //moverDerecha.setDuration(Duration.seconds(0.5));
                transitions.add(moverDerecha);
                
                stackpanes.set(j, stackpanes.get(j - 1));
                numbers.set(j, numbers.get(j - 1));
                
                j--;    
            }
            
            TranslateTransition reubicar = new TranslateTransition();
            reubicar.setNode(stackpane);
            //reubicar.setNode(r);
            reubicar.setByX(-1 * (Main.separation + Main.rectangleWidth) * contador);
            reubicar.setByY(2 * Main.rectangleWidth);
            reubicar.setDuration(Duration.seconds(0.5));
            transitions.add(reubicar);
            
            stackpanes.set(j, stackpane); 
            numbers.set(j, currentNumber);
        }
        
        return transitions;
    }
    
}
