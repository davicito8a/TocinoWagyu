package SortVisualizer;

import java.util.ArrayList;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class InsertionSorter {
    
    private ArrayList<Rectangle> rectangles = new ArrayList();
    private ArrayList<StackPane> stackpanes = new ArrayList();
    
    public InsertionSorter(ArrayList<Rectangle> rectangles, ArrayList<StackPane> stackpanes){
        this.rectangles = rectangles;
        this.stackpanes = stackpanes;
    }
    
    public ArrayList<Transition> getSortingTransitions(){
        ArrayList<Transition> transitions = new ArrayList();
        
        for(int i = 1; i < rectangles.size(); i++){
            int j = i;
            
            Rectangle r = rectangles.get(i);
            StackPane stackpane = stackpanes.get(i);
            double currentHeight = r.getHeight();
          
            int contador = 0;
            
            TranslateTransition moverAbajo = new TranslateTransition();
            //moverAbajo.setNode(rectangles.get(i));
            moverAbajo.setNode(stackpanes.get(i));
            moverAbajo.setByY(1.5 * Main.rectangleMaxHeight);
            //moverAbajo.setDuration(Duration.seconds(0.5));
            transitions.add(moverAbajo);
            
            while(j > 0 && currentHeight < rectangles.get(j - 1).getHeight()){
                contador++;
                
                TranslateTransition moverDerecha = new TranslateTransition();
                //moverDerecha.setNode(rectangles.get(j - 1));
                moverDerecha.setNode(stackpanes.get(j - 1));
                moverDerecha.setByX(Main.separation + Main.rectangleWidth);
                //moverDerecha.setDuration(Duration.seconds(0.5));
                transitions.add(moverDerecha);
                
                stackpanes.set(j, stackpanes.get(j-1));
                rectangles.set(j, rectangles.get(j - 1));
                
                j--;    
            }
            
            TranslateTransition relocate = new TranslateTransition();
            relocate.setNode(stackpane);
            //reubicar.setNode(r);
            relocate.setByX(-1 * (Main.separation + Main.rectangleWidth) * contador);
            relocate.setByY(-1.5 * Main.rectangleMaxHeight);
            relocate.setDuration(Duration.seconds(0.5));
            transitions.add(relocate);
            
            stackpanes.set(j, stackpane);
            rectangles.set(j, r); 
        }
        
        return transitions;
    }
    
}
