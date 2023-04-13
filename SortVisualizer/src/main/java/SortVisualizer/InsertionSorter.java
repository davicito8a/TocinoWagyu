package SortVisualizer;

import java.util.ArrayList;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;

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
            moverAbajo.setNode(stackpanes.get(i));
            moverAbajo.setByY(- 1 * 2 * Main.rectangleWidth);
            transitions.add(moverAbajo);
            System.out.println(moverAbajo.getDuration());
            
            
            
            while(j > 0 && currentNumber < numbers.get(j - 1)){
                contador++;
                
                TranslateTransition moverDerecha = new TranslateTransition();
                moverDerecha.setNode(stackpanes.get(j - 1));
                moverDerecha.setByX(Main.separation + Main.rectangleWidth);
                transitions.add(moverDerecha);
                
                stackpanes.set(j, stackpanes.get(j - 1));
                numbers.set(j, numbers.get(j - 1));
                
                j--;    
            }
            
            TranslateTransition reubicarX = new TranslateTransition();
            reubicarX.setNode(stackpane);
            reubicarX.setByX(-1 * (Main.separation + Main.rectangleWidth) * contador);
            transitions.add(reubicarX);
            
            TranslateTransition reubicarY = new TranslateTransition();
            reubicarY.setNode(stackpane);
            reubicarY.setByY(2 * Main.rectangleWidth);
            transitions.add(reubicarY);
            
            stackpanes.set(j, stackpane); 
            numbers.set(j, currentNumber);
        }
        
        return transitions;
    }
    
}
