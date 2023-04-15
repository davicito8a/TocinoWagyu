/**
 * Clase que implementa el algoritmo de ordenamiento por inserción(unidad 1 bbs) y 
 * devuelve una lista de transiciones de animación para visualizar el proceso de ordenamiento en una interfaz gráfica de usuario.
 */
package SortVisualizer;

import java.util.ArrayList;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class InsertionSorter {
    
    // ArrayLists para almacenar los números y StackPanes que se ordenarán
    private ArrayList<Integer> numbers = new ArrayList();
    private ArrayList<StackPane> stackpanes = new ArrayList();
    
    Rectangle rec3 = new Rectangle();
    Color color = Color.BLUE;
    
    // Constructor que toma dos ArrayLists como argumentos
    public InsertionSorter(ArrayList<Integer> numbers, ArrayList<StackPane> stackpanes){
        this.numbers = numbers;
        this.stackpanes = stackpanes;
        rec3.setX(250); rec3.setY(10);
        rec3.setWidth(80); rec3.setHeight(55);
        rec3.setFill(color);
    }

    public Rectangle getRec3() {
        return rec3;
    }

    /**
     * Devuelve una lista de transiciones de animación para visualizar el proceso de ordenamiento por inserción.
     * @return Una lista de objetos Transition que contienen información sobre cómo se deben animar los StackPanes para visualizar el proceso de ordenamiento por inserción.
     */    
    public ArrayList<Transition> getSortingTransitions(){
        ArrayList<Transition> transitions = new ArrayList();
        
        // Bucle que recorre la lista de números
        for(int i = 1; i < numbers.size(); i++){
            int j = i;
            
            // Obtiene el StackPane y el número actual
            StackPane stackpane = stackpanes.get(i);
            int currentNumber = numbers.get(i);
            
            int contador = 0;
            
            // Crea una nueva transición para mover el StackPane hacia abajo
            TranslateTransition moverAbajo = new TranslateTransition();
            moverAbajo.setNode(stackpanes.get(i));
            moverAbajo.setByY(- 1 * 2 * Main.rectangleWidth);
            transitions.add(moverAbajo);
            
            // Bucle que recorre los números antes del número actual y los compara con el número actual
            while(j > 0 && currentNumber < numbers.get(j - 1)){
                contador++;
                
                // Crea una nueva transición para mover el StackPane hacia la derecha
                TranslateTransition moverDerecha = new TranslateTransition();
                moverDerecha.setNode(stackpanes.get(j - 1));
                moverDerecha.setByX(Main.separation + Main.rectangleWidth);
                transitions.add(moverDerecha);
                
                // Intercambia los valores de los números y StackPanes
                stackpanes.set(j, stackpanes.get(j - 1));
                numbers.set(j, numbers.get(j - 1));
                
                j--;    
            }
            
            // Crea una nueva transición para reubicar el StackPane en su posición final
            TranslateTransition reubicar = new TranslateTransition();
            reubicar.setNode(stackpane);
            reubicar.setByX(-1 * (Main.separation + Main.rectangleWidth) * contador);
            reubicar.setByY(2 * Main.rectangleWidth);
            reubicar.setDuration(Duration.seconds(0.5));
            transitions.add(reubicar);
            
            // Actualiza los valores de los números y StackPanes
            stackpanes.set(j, stackpane); 
            numbers.set(j, currentNumber);
        }
        
        // Devuelve la lista de transiciones de animación
        return transitions;
    }
    
}
