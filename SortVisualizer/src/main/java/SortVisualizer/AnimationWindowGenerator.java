package SortVisualizer;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import static javafx.animation.Animation.Status.RUNNING;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;

public class AnimationWindowGenerator {
    private final int type;
    // La lista de números que se van a ordenar
    private final ArrayList<Integer> numbers;
    // La lista de paneles de apilamiento asociados a los números
    private final ArrayList<StackPane> stackpanes;
    // La lista de transiciones de animación de ordenamiento
    private ArrayList<Transition> transitions;
    // La línea de tiempo de animación
    private SequentialTransition timeline;
    // El índice de la transición actual
    private int index = 0;
    
    // La escena de la ventana de animación
    private Scene scene;
    // El panel de anclaje raíz de la ventana de animación
    private AnchorPane root;

    // Los botones de control de la animación
    private final Button play = new Button("Play");
    private final Button pause = new Button("Pause");
    private final Button increaseSpeed = new Button("Increase");
    private final Button decreaseSpeed = new Button("Decrease");
    private final Button stepForward = new Button("Forward");
    private final Button stepBackward = new Button("Backward");

    // La anchura predefinida de los botones
    private final int prefWidth = 75;
    
    
    // Constructor de la clase
    public AnimationWindowGenerator(ArrayList<Integer> numbers, ArrayList<StackPane> stackpanes, int type) throws MalformedURLException{
        // Se asignan las listas de números y paneles de apilamiento
        this.numbers = numbers;
        this.stackpanes = stackpanes;
        // Se asigna el tipo de animación de ordenamiento
        this.type = type;
        // Se inicia la generación de la ventana de animación
        start();
    }
    
    private void start () throws MalformedURLException{
        // Se obtienen las transiciones de animación de ordenamiento
        getTransitions();
        
        // Si el tipo de animación es 0 (es decir, es una animación de ordenamiento automática)
        if(type == 0){
            // Se crea la línea de tiempo de animación
            createTimeline();
        } 
        
        // Se crea el panel de anclaje raíz
        root = new AnchorPane();
        // Se establece el fondo de la ventana de animación
        BackgroundImage background = new BackgroundImage(new Image(new File("src/main/java/SortVisualizer/background.png").toURI().toURL().toExternalForm()),
        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
        // Se añaden los paneles de apilamiento al panel de anclaje raíz
        root.getChildren().addAll(stackpanes);
        // Se establece el fondo del panel de anclaje raíz
        root.setBackground(new Background(background));
        setButtonsLayout();
        scene = new Scene(root);
        scene.getStylesheets().add(new File("src/main/java/SortVisualizer/Styles.css").toURI().toURL().toExternalForm());
    }
    
    private void setButtonsLayout(){
        /*
        Este método establece la ubicación y el ancho de los botones de control en la interfaz gráfica de usuario (GUI). 
        Cada botón se ajusta a una ubicación específica en función del ancho y la altura de la ventana de la GUI.
        */
        play.setLayoutX(0.55 * Main.windowWidth);
        play.setLayoutY(0.85 * Main.windowHeight);
        play.setPrefWidth(prefWidth);
        
        pause.setLayoutX(0.65 * Main.windowWidth);
        pause.setLayoutY(0.85 * Main.windowHeight);
        pause.setPrefWidth(prefWidth);
        
        increaseSpeed.setLayoutX(0.75 * Main.windowWidth);
        increaseSpeed.setLayoutY(0.85 * Main.windowHeight);
        increaseSpeed.setPrefWidth(prefWidth);
        
        decreaseSpeed.setLayoutX(0.85 * Main.windowWidth);
        decreaseSpeed.setLayoutY(0.85 * Main.windowHeight);
        decreaseSpeed.setPrefWidth(prefWidth);
        
        stepForward.setLayoutX(0.75 * Main.windowWidth);
        stepForward.setLayoutY(0.85 * Main.windowHeight);
        stepForward.setPrefWidth(prefWidth);
        
        stepBackward.setLayoutX(0.85 * Main.windowWidth);
        stepBackward.setLayoutY(0.85 * Main.windowHeight);
        stepBackward.setPrefWidth(prefWidth);
        
        play.setOnAction(event -> {
            play(timeline);
        });
        
        pause.setOnAction(event -> {
            pause(timeline);
        });
        
        increaseSpeed.setOnAction(event -> {
            increaseSpeed(timeline);
        });
        
        decreaseSpeed.setOnAction(event -> {
            decreaseSpeed(timeline);
        });
        
        stepForward.setOnAction(event -> {
            stepForward(transitions);
        });
        
        stepBackward.setOnAction(event -> {
            stepBackward(transitions);
        });   
        
        root.getChildren().addAll(play, pause, increaseSpeed, decreaseSpeed, stepForward, stepBackward);
        
        if(type == 0){
            stepForward.setVisible(false);
            stepBackward.setVisible(false);
        } else {
            play.setVisible(false);
            pause.setVisible(false);
            increaseSpeed.setVisible(false);
            decreaseSpeed.setVisible(false);
        }
    }
    
    private void getTransitions(){
        /*
        Este método crea una instancia de la clase InsertionSorter que toma una lista de números y un arreglo de paneles, 
        y devuelve una lista de objetos de transición. Estos objetos se usan en la animación para cambiar la posición de los paneles 
        para demostrar la clasificación.*/
        
        InsertionSorter sorter = new InsertionSorter(numbers, stackpanes);
        transitions = sorter.getSortingTransitions();
    }
    
    private void createTimeline(){
        /*
        : Este método crea una instancia de SequentialTransition que agrega los objetos de transición que se obtienen del método getTransitions(). Esto crea una animación secuencial que cambia la posición de los paneles en una secuencia específica.
        */
        timeline = new SequentialTransition();
        timeline.getChildren().addAll(transitions);
    }
    
    private void increaseSpeed(SequentialTransition timeline){
        /*
        Este método aumenta la velocidad de la animación, se verifica si la 
        animación esta en ejecución antes de cambiar la velocidad.
        */
        if(timeline.getStatus().equals(RUNNING))
            timeline.setRate(timeline.getRate() * 1.25);
    }
    
    private void decreaseSpeed(SequentialTransition timeline){
        /*
        Este método disminuye la velocidad de la animación, se verifica si la 
        animación esta en ejecución antes de cambiar la velocidad.
        */
        if(timeline.getStatus().equals(RUNNING))
            timeline.setRate(timeline.getRate() * 0.8);
    }
    
    private void pause(SequentialTransition timeline){
        /*Este método pausan la animación respectivamente.*/
        timeline.pause();
    }
    
    private void play(SequentialTransition timeline){
        /*Este método reanuda la animación respectivamente.*/
        timeline.play();   
    }
    
    private void stepForward(ArrayList<Transition> transitions){
        /*Este método se usa para avanzar un paso en la animación. 
        Cada uno toma una lista de transiciones y cambia la posición de los paneles en la animación en consecuencia.*/
        if(index <= transitions.size() - 1){
            TranslateTransition transition = (TranslateTransition) transitions.get(index);
            transition.setOnFinished(e -> index++);
            transition.play();
        }
    }
    
    private void stepBackward(ArrayList<Transition> transitions){
        /*Este método se usa para retroceder un paso en la animación. 
        Cada uno toma una lista de transiciones y cambia la posición de los paneles en la animación en consecuencia.*/
        if(index >= 1){
            TranslateTransition transition = (TranslateTransition) transitions.get(index - 1);
            transition.setOnFinished(e -> {
                transition.setByX(-1 * transition.getByX());
                transition.setByY(-1 * transition.getByY());
                index--;
            });
            transition.setByX(-1 * transition.getByX());
            transition.setByY(-1 * transition.getByY());
            transition.play();
        }  
    }
    
    public Scene getScene(){
        /*Este método devuelve la escena de la GUI que contiene todos 
        los elementos que se utilizan en la animación.*/
        return scene;
    }
}