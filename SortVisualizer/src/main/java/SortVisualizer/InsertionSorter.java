package SortVisualizer;

import java.util.ArrayList;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class InsertionSorter {
    
    private ArrayList<Rectangle> rectangles = new ArrayList();
    
    public InsertionSorter(ArrayList<Rectangle> rectangles){
        this.rectangles = rectangles;
    }
    
    public ArrayList<Transition> getSortingTransitions(){
        ArrayList<Transition> transitions = new ArrayList();
        
        for(int i = 1; i < rectangles.size(); i++){
            int j = i;
            
            Rectangle r = rectangles.get(i);
            double currentHeight = r.getHeight();
          
            int contador = 0;
            
            TranslateTransition moverAbajo = new TranslateTransition();
            moverAbajo.setNode(rectangles.get(i));
            moverAbajo.setByY(1.5 * Main.rectangleMaxHeight);
            moverAbajo.setDuration(Duration.seconds(0.5));
            transitions.add(moverAbajo);
            
            while(j > 0 && currentHeight < rectangles.get(j - 1).getHeight()){
                contador++;
                
                TranslateTransition moverDerecha = new TranslateTransition();
                moverDerecha.setNode(rectangles.get(j - 1));
                moverDerecha.setByX(Main.separation + Main.rectangleWidth);
                moverDerecha.setDuration(Duration.seconds(0.5));
                transitions.add(moverDerecha);
                
                rectangles.set(j, rectangles.get(j - 1));
                
                j--;    
            }
            
            TranslateTransition reubicar = new TranslateTransition();
            reubicar.setNode(r);
            reubicar.setToX(-1 * (Main.separation + Main.rectangleWidth) * contador);
            reubicar.setByY(-1.5 * Main.rectangleMaxHeight);
            transitions.add(reubicar);
            
            rectangles.set(j, r); 
        }
        
        return transitions;
    }
    
}
