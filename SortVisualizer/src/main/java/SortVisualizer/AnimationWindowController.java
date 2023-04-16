package SortVisualizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.Animation;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle; 
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import javafx.scene.layout.VBox;

public class AnimationWindowController {
    private final int type;
    
    private ArrayList<Animation> AnimacionesDeGrua;
    
    private SequentialTransition sequentialTranslateTransitionsCaja = new SequentialTransition();
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
    private ArrayList<Animation> translateTransitions;
    private ArrayList<Animation> pseudocodeAnimations;
    private SequentialTransition sequentialTranslateTransitions;
    private SequentialTransition sequentialPseudocodeAnimations = new SequentialTransition();
    private int currentTransitionIndex = 0;
    Animator animator;
    
    // La escena de la ventana de animación
    private Scene scene;
    // El panel de anclaje raíz de la ventana de animación
    private AnchorPane root;

    // Los botones de control de la animación
    private VBox pseudocodeBox;
    
    private final Button play = new Button("Play");
    private final Button pause = new Button("Pause");
    private final Button increaseSpeed = new Button("Increase");
    private final Button decreaseSpeed = new Button("Decrease");
    private final Button stepForward = new Button("Forward");
    private final Button stepBackward = new Button("Backward");
    
    private final int prefWidth = Main.windowWidth/15;
    
    public AnimationWindowController(ArrayList<Integer> numbers, ArrayList<StackPane> stackpanes, int type) throws IOException{
        this.numbers = numbers;
        this.stackpanes = stackpanes;
        animator = new Animator(numbers, stackpanes);
        // Se asigna el tipo de animación de ordenamiento
        this.type = type;
        // Se inicia la generación de la ventana de animación
        start();
        this.AnimacionesDeGrua = null;
    }
    

    private void start () throws IOException{
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
        setGrua();
        root.getChildren().add(pseudocodeBox);
        
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
            play(sequentialTranslateTransitions);
        });
        
        pause.setOnAction(event -> {
            pause(sequentialTranslateTransitions);
        });
        
        increaseSpeed.setOnAction(event -> {
            increaseSpeed(sequentialTranslateTransitions);
        });
        
        decreaseSpeed.setOnAction(event -> {
            decreaseSpeed(sequentialTranslateTransitions);
        });
        
        stepForward.setOnAction(event -> {
            stepForward(translateTransitions);
        });
        
        stepBackward.setOnAction(event -> {
            stepBackward(translateTransitions);
        });   
        
        root.getChildren().addAll(play, pause, increaseSpeed, decreaseSpeed, stepForward, stepBackward);
        
        if(type == 0){
            stepForward.setVisible(false);
            stepBackward.setVisible(false);
        } else if(type == 1) {
            play.setVisible(false);
            pause.setVisible(false);
            increaseSpeed.setVisible(false);
            decreaseSpeed.setVisible(false);
        }
        
    }
    
    private void getTransitions(){
        translateTransitions = animator.getTranslateTransitions();
        pseudocodeBox = animator.getPseudocodeBox();
        sequentialPseudocodeAnimations.getChildren().addAll(animator.getPseudocodeAnimations());
        AnimacionesDeGrua = animator.getAnimacionesDeGrua();
        sequentialTranslateTransitionsCaja.getChildren().addAll(animator.getAnimacionesDeGrua());
               
    }
    
    private void createTimeline(){
        sequentialTranslateTransitions = new SequentialTransition();
        sequentialTranslateTransitions.getChildren().addAll(translateTransitions);
    }
    
    private void increaseSpeed(SequentialTransition timeline){
        if(timeline.getStatus().equals(RUNNING)){
            timeline.setRate(timeline.getRate() * 1.25);
            sequentialPseudocodeAnimations.setRate(sequentialPseudocodeAnimations.getRate() * 1.25);
        }
    }
    
    private void decreaseSpeed(SequentialTransition timeline){
        /*
        Este método disminuye la velocidad de la animación. Se verifica si la 
        animación esta en ejecución antes de cambiar la velocidad.
        */
        if(timeline.getStatus().equals(RUNNING)){
            timeline.setRate(timeline.getRate() * 0.8);
            sequentialPseudocodeAnimations.setRate(sequentialPseudocodeAnimations.getRate() * 0.8);
        }
    }
    
    private void pause(SequentialTransition timeline){
        /*Este método pausan la animación respectivamente.*/
        timeline.pause();
        sequentialPseudocodeAnimations.pause();
    }
    
    private void play(SequentialTransition timeline){
        /*Este método reanuda la animación respectivamente.*/
        sequentialPseudocodeAnimations.play();
        //sequentialTranslateTransitionsCaja.play();
        timeline.play();   
    }
    
    private void stepForward(ArrayList<Animation> transitions){
        if(currentTransitionIndex <= transitions.size() - 1){
            Animation transition = transitions.get(currentTransitionIndex);
            transition.setOnFinished(e -> currentTransitionIndex++);
            transition.setRate(1);
            transition.play();
        }
    }
    
    private void stepBackward(ArrayList<Animation> transitions){
        if(currentTransitionIndex >= 1){
            Animation transition = transitions.get(currentTransitionIndex - 1);
            transition.setOnFinished(e -> currentTransitionIndex--);
            transition.setRate(-1);
            transition.play();
        }  
    }
     
    public Scene getScene(){
        /*Este método devuelve la escena de la GUI que contiene todos 
        los elementos que se utilizan en la animación.*/
        return scene;
    }
    
    private void setGrua(){
        Color ColorInterno = Color.LIGHTGREEN;
        Color ColorExterno = Color.LIGHTGREEN;
        float EscalaGrua = 0.9f;
        int CoorBigaSuperior = 1300;
        int RazondeBigaDeArriba = (CoorBigaSuperior-50)/13;
        //Grua
        //###################################################################################
        
        //SoporteBigas Izquierda
        Line l = new Line(40/EscalaGrua,400/EscalaGrua,40/EscalaGrua,40/EscalaGrua);
        l.setStroke(Color.YELLOW);
        l.setStrokeWidth(5);
        Line l2 = new Line(10/EscalaGrua,400/EscalaGrua,10/EscalaGrua,15/EscalaGrua);
        l2.setStroke(Color.YELLOW);
        l2.setStrokeWidth(5);

        //=============================================================================

        //Bigas de Adorno Lado Izquierdo(De abajo a arriba)
        
        Line l7 = new Line(10/EscalaGrua,400/EscalaGrua,40/EscalaGrua,350/EscalaGrua);
        l7.setStroke(ColorInterno);
        l7.setStrokeWidth(3);
        Line l8 = new Line(10/EscalaGrua,350/EscalaGrua,40/EscalaGrua,300/EscalaGrua);
        l8.setStroke(ColorInterno);
        l8.setStrokeWidth(3);
        Line l9 = new Line(10/EscalaGrua,300/EscalaGrua,40/EscalaGrua,250/EscalaGrua);
        l9.setStroke(ColorInterno);
        l9.setStrokeWidth(3);
        Line l10 = new Line(10/EscalaGrua,250/EscalaGrua,40/EscalaGrua,200/EscalaGrua);
        l10.setStroke(ColorInterno);
        l10.setStrokeWidth(3);
        Line l11 = new Line(10/EscalaGrua,200/EscalaGrua,40/EscalaGrua,150/EscalaGrua);
        l11.setStroke(ColorInterno);
        l11.setStrokeWidth(3);
        Line l12 = new Line(10/EscalaGrua,150/EscalaGrua,40/EscalaGrua,100/EscalaGrua);
        l12.setStroke(ColorInterno);
        l12.setStrokeWidth(3);
        Line l13 = new Line(10/EscalaGrua,100/EscalaGrua,40/EscalaGrua,50/EscalaGrua);
        l13.setStroke(ColorInterno);
        l13.setStrokeWidth(3);
        /*
        Line l14 = new Line(10/EscalaGrua,150/EscalaGrua,40/EscalaGrua,100/EscalaGrua);
        l14.setStroke(ColorInterno);
        l14.setStrokeWidth(3);
        Line l15 = new Line(10/EscalaGrua,100/EscalaGrua,40/EscalaGrua,50/EscalaGrua);
        l15.setStroke(ColorInterno);
        l15.setStrokeWidth(3);*/

        //=============================================================================

        //Bigas de Adorno Lado Derecho(De abajo a arriba)
        
        //Line l16 = new Line((CoorBigaSuperior)/EscalaGrua,500/EscalaGrua,(CoorBigaSuperior+30)/EscalaGrua,450/EscalaGrua);
        //l16.setStroke(ColorInterno);
        //l16.setStrokeWidth(3);
        //Line l17 = new Line((CoorBigaSuperior)/EscalaGrua,450/EscalaGrua,(CoorBigaSuperior+30)/EscalaGrua,400/EscalaGrua);
        //l17.setStroke(ColorInterno);
        //l17.setStrokeWidth(3);
        Line l18 = new Line((CoorBigaSuperior)/EscalaGrua,400/EscalaGrua,(CoorBigaSuperior+30)/EscalaGrua,350/EscalaGrua);
        l18.setStroke(ColorInterno);
        l18.setStrokeWidth(3);
        Line l19 = new Line((CoorBigaSuperior)/EscalaGrua,350/EscalaGrua,(CoorBigaSuperior+30)/EscalaGrua,300/EscalaGrua);
        l19.setStroke(ColorInterno);
        l19.setStrokeWidth(3);
        Line l20 = new Line((CoorBigaSuperior)/EscalaGrua,300/EscalaGrua,(CoorBigaSuperior+30)/EscalaGrua,250/EscalaGrua);
        l20.setStroke(ColorInterno);
        l20.setStrokeWidth(3);
        Line l21 = new Line((CoorBigaSuperior)/EscalaGrua,250/EscalaGrua,(CoorBigaSuperior+30)/EscalaGrua,200/EscalaGrua);
        l21.setStroke(ColorInterno);
        l21.setStrokeWidth(3);
        Line l22 = new Line((CoorBigaSuperior)/EscalaGrua,200/EscalaGrua,(CoorBigaSuperior+30)/EscalaGrua,150/EscalaGrua);
        l22.setStroke(ColorInterno);
        l22.setStrokeWidth(3);
        Line l23 = new Line((CoorBigaSuperior)/EscalaGrua,150/EscalaGrua,(CoorBigaSuperior+30)/EscalaGrua,100/EscalaGrua);
        l23.setStroke(ColorInterno);
        l23.setStrokeWidth(3);
        Line l24 = new Line((CoorBigaSuperior)/EscalaGrua,100/EscalaGrua,(CoorBigaSuperior+30)/EscalaGrua,50/EscalaGrua);
        l24.setStroke(ColorInterno);
        l24.setStrokeWidth(3);
        //Line l25 = new Line(20/EscalaGrua,100/EscalaGrua,50/EscalaGrua,50/EscalaGrua);
        //l25.setStroke(ColorInterno);
        //l25.setStrokeWidth(3);
       
        //=============================================================================
        
        //Bigas de Adorno Lado Izquierdo(De arriba a abajo)
        
        //Line l26 = new Line(40/EscalaGrua,500/EscalaGrua,10/EscalaGrua,450/EscalaGrua);
        //l26.setStroke(ColorExterno);
        //l26.setStrokeWidth(3);
        //Line l27 = new Line(40/EscalaGrua,450/EscalaGrua,10/EscalaGrua,400/EscalaGrua);
        //l27.setStroke(ColorExterno);
        //l27.setStrokeWidth(3);
        
        Line l28 = new Line(40/EscalaGrua,400/EscalaGrua,10/EscalaGrua,350/EscalaGrua);
        l28.setStroke(ColorExterno);
        l28.setStrokeWidth(3);
        Line l29 = new Line(40/EscalaGrua,350/EscalaGrua,10/EscalaGrua,300/EscalaGrua);
        l29.setStroke(ColorExterno);
        l29.setStrokeWidth(3);
        Line l30 = new Line(40/EscalaGrua,300/EscalaGrua,10/EscalaGrua,250/EscalaGrua);
        l30.setStroke(ColorExterno);
        l30.setStrokeWidth(3);
        Line l31 = new Line(40/EscalaGrua,250/EscalaGrua,10/EscalaGrua,200/EscalaGrua);
        l31.setStroke(ColorExterno);
        l31.setStrokeWidth(3);
        Line l32 = new Line(40/EscalaGrua,200/EscalaGrua,10/EscalaGrua,150/EscalaGrua);
        l32.setStroke(ColorExterno);
        l32.setStrokeWidth(3);
        Line l33 = new Line(40/EscalaGrua,150/EscalaGrua,10/EscalaGrua,100/EscalaGrua);
        l33.setStroke(ColorExterno);
        l33.setStrokeWidth(3);
        Line l34 = new Line(40/EscalaGrua,100/EscalaGrua,10/EscalaGrua,50/EscalaGrua);
        l34.setStroke(ColorExterno);
        l34.setStrokeWidth(3);
        
        //=============================================================================
        
        //Bigas de Adorno Lado Derecho(De arriba a abajo)
        
        //Line l35 = new Line((CoorBigaSuperior+30)/EscalaGrua,500/EscalaGrua,(CoorBigaSuperior)/EscalaGrua,450/EscalaGrua);
        //l35.setStroke(ColorExterno);
        //l35.setStrokeWidth(3);
        //Line l36 = new Line((CoorBigaSuperior+30)/EscalaGrua,450/EscalaGrua,(CoorBigaSuperior)/EscalaGrua,400/EscalaGrua);
        //l36.setStroke(ColorExterno);
        //l36.setStrokeWidth(3);
        Line l37 = new Line((CoorBigaSuperior+30)/EscalaGrua,400/EscalaGrua,(CoorBigaSuperior)/EscalaGrua,350/EscalaGrua);
        l37.setStroke(ColorExterno);
        l37.setStrokeWidth(3);
        Line l38 = new Line((CoorBigaSuperior+30)/EscalaGrua,350/EscalaGrua,(CoorBigaSuperior)/EscalaGrua,300/EscalaGrua);
        l38.setStroke(ColorExterno);
        l38.setStrokeWidth(3);
        Line l39 = new Line((CoorBigaSuperior+30)/EscalaGrua,300/EscalaGrua,(CoorBigaSuperior)/EscalaGrua,250/EscalaGrua);
        l39.setStroke(ColorExterno);
        l39.setStrokeWidth(3);
        Line l40 = new Line((CoorBigaSuperior+30)/EscalaGrua,250/EscalaGrua,(CoorBigaSuperior)/EscalaGrua,200/EscalaGrua);
        l40.setStroke(ColorExterno);
        l40.setStrokeWidth(3);
        Line l41 = new Line((CoorBigaSuperior+30)/EscalaGrua,200/EscalaGrua,(CoorBigaSuperior)/EscalaGrua,150/EscalaGrua);
        l41.setStroke(ColorExterno);
        l41.setStrokeWidth(3);
        Line l42 = new Line((CoorBigaSuperior+30)/EscalaGrua,150/EscalaGrua,(CoorBigaSuperior)/EscalaGrua,100/EscalaGrua);
        l42.setStroke(ColorExterno);
        l42.setStrokeWidth(3);
        Line l43 = new Line((CoorBigaSuperior+30)/EscalaGrua,100/EscalaGrua,(CoorBigaSuperior)/EscalaGrua,50/EscalaGrua);
        l43.setStroke(ColorExterno);
        l43.setStrokeWidth(3);
        
        //=============================================================================
        
        //Bigas Superiores
        Line l3 = new Line(40/EscalaGrua,40/EscalaGrua,(CoorBigaSuperior)/EscalaGrua,40/EscalaGrua);//l1
        l3.setStroke(Color.YELLOW);
        l3.setStrokeWidth(5);
        Line l4 = new Line(10/EscalaGrua,15/EscalaGrua,(CoorBigaSuperior+30)/EscalaGrua,15/EscalaGrua);//l2
        l4.setStroke(Color.YELLOW);
        l4.setStrokeWidth(5);
       
        //=============================================================================
        
        //Bigas de Adorno Superios(De abajo hacia arriba)
        Line l44 = new Line(40/EscalaGrua,15/EscalaGrua,40+(RazondeBigaDeArriba)/EscalaGrua,40/EscalaGrua);
        l44.setStroke(ColorExterno);
        l44.setStrokeWidth(3);
        int aux1=50+(RazondeBigaDeArriba);
        Line l45 = new Line((aux1)/EscalaGrua,15/EscalaGrua,(aux1+(RazondeBigaDeArriba))/EscalaGrua,40/EscalaGrua);
        l45.setStroke(ColorExterno);
        l45.setStrokeWidth(3);
        int aux2=aux1+(RazondeBigaDeArriba);
        Line l46 = new Line((aux2)/EscalaGrua,15/EscalaGrua,(aux2+RazondeBigaDeArriba)/EscalaGrua,40/EscalaGrua);
        l46.setStroke(ColorExterno);
        l46.setStrokeWidth(3);
        int aux3=aux2+RazondeBigaDeArriba;
        Line l47 = new Line((aux3)/EscalaGrua,15/EscalaGrua,(aux3+RazondeBigaDeArriba)/EscalaGrua,40/EscalaGrua);
        l47.setStroke(ColorExterno);
        l47.setStrokeWidth(3);
        int aux4= aux3+RazondeBigaDeArriba;
        Line l48 = new Line((aux4)/EscalaGrua,15/EscalaGrua,(aux4+RazondeBigaDeArriba)/EscalaGrua,40/EscalaGrua);
        l48.setStroke(ColorExterno);
        l48.setStrokeWidth(3);
        int aux5 = aux4+RazondeBigaDeArriba;
        Line l49 = new Line((aux5)/EscalaGrua,15/EscalaGrua,(aux5+RazondeBigaDeArriba)/EscalaGrua,40/EscalaGrua);
        l49.setStroke(ColorExterno);
        l49.setStrokeWidth(3);
        int aux6 = aux5+RazondeBigaDeArriba;
        Line l50 = new Line((aux6)/EscalaGrua,15/EscalaGrua,(aux6+RazondeBigaDeArriba)/EscalaGrua,40/EscalaGrua);
        l50.setStroke(ColorExterno);
        l50.setStrokeWidth(3);
        int aux7 = aux6+RazondeBigaDeArriba;
        Line l51 = new Line((aux7)/EscalaGrua,15/EscalaGrua,(aux7+RazondeBigaDeArriba)/EscalaGrua,40/EscalaGrua);
        l51.setStroke(ColorExterno);
        l51.setStrokeWidth(3);
        int aux8 = aux7+RazondeBigaDeArriba;
        Line l52 = new Line((aux8)/EscalaGrua,15/EscalaGrua,(aux8+RazondeBigaDeArriba)/EscalaGrua,40/EscalaGrua);
        l52.setStroke(ColorExterno);
        l52.setStrokeWidth(3);
        int aux9 = aux8+RazondeBigaDeArriba;
        Line l53 = new Line((aux9)/EscalaGrua,15/EscalaGrua,(aux9+RazondeBigaDeArriba)/EscalaGrua,40/EscalaGrua);
        l53.setStroke(ColorExterno);
        l53.setStrokeWidth(3);
        int aux10 = aux9+RazondeBigaDeArriba;
        Line l54 = new Line((aux10)/EscalaGrua,15/EscalaGrua,(aux10+RazondeBigaDeArriba)/EscalaGrua,40/EscalaGrua);
        l54.setStroke(ColorExterno);
        l54.setStrokeWidth(3);
        int aux11 = aux10+RazondeBigaDeArriba;
        Line l55 = new Line((aux11)/EscalaGrua,15/EscalaGrua,(aux11+RazondeBigaDeArriba)/EscalaGrua,40/EscalaGrua);
        l55.setStroke(ColorExterno);
        l55.setStrokeWidth(3);
        int aux12=aux11+RazondeBigaDeArriba;
        Line l56 = new Line((aux12)/EscalaGrua,15/EscalaGrua,(aux12+RazondeBigaDeArriba)/EscalaGrua,40/EscalaGrua);
        l56.setStroke(ColorExterno);
        l56.setStrokeWidth(3);
        
        //=============================================================================
        
        //Bigas de Adorno Superior(De arriba hacia abajo)
        Line l57 = new Line((aux12+RazondeBigaDeArriba)/EscalaGrua,15/EscalaGrua,aux12/EscalaGrua,40/EscalaGrua);
        l57.setStroke(ColorInterno);
        l57.setStrokeWidth(3);
        Line l58 = new Line(aux12/EscalaGrua,15/EscalaGrua,aux11/EscalaGrua,40/EscalaGrua);
        l58.setStroke(ColorInterno);
        l58.setStrokeWidth(3);
        Line l59 = new Line(aux11/EscalaGrua,15/EscalaGrua,aux10/EscalaGrua,40/EscalaGrua);
        l59.setStroke(ColorInterno);
        l59.setStrokeWidth(3);
        Line l60 = new Line(aux10/EscalaGrua,15/EscalaGrua,aux9/EscalaGrua,40/EscalaGrua);
        l60.setStroke(ColorInterno);
        l60.setStrokeWidth(3);
        Line l61 = new Line(aux9/EscalaGrua,15/EscalaGrua,aux8/EscalaGrua,40/EscalaGrua);
        l61.setStroke(ColorInterno);
        l61.setStrokeWidth(3);
        Line l62 = new Line(aux8/EscalaGrua,15/EscalaGrua,aux7/EscalaGrua,40/EscalaGrua);
        l62.setStroke(ColorInterno);
        l62.setStrokeWidth(3);
        Line l63 = new Line(aux7/EscalaGrua,15/EscalaGrua,aux6/EscalaGrua,40/EscalaGrua);
        l63.setStroke(ColorInterno);
        l63.setStrokeWidth(3);
        Line l64 = new Line(aux6/EscalaGrua,15/EscalaGrua,aux5/EscalaGrua,40/EscalaGrua);
        l64.setStroke(ColorInterno);
        l64.setStrokeWidth(3);
        Line l65 = new Line(aux5/EscalaGrua,15/EscalaGrua,aux4/EscalaGrua,40/EscalaGrua);
        l65.setStroke(ColorInterno);
        l65.setStrokeWidth(3);
        Line l66 = new Line(aux4/EscalaGrua,15/EscalaGrua,aux3/EscalaGrua,40/EscalaGrua);
        l66.setStroke(ColorInterno);
        l66.setStrokeWidth(3);
        Line l67 = new Line(aux3/EscalaGrua,15/EscalaGrua,aux2/EscalaGrua,40/EscalaGrua);
        l67.setStroke(ColorInterno);
        l67.setStrokeWidth(3);
        Line l68 = new Line(aux2/EscalaGrua,15/EscalaGrua,aux1/EscalaGrua,40/EscalaGrua);
        l68.setStroke(ColorInterno);
        l68.setStrokeWidth(3);
        Line l69 = new Line(aux1/EscalaGrua,15/EscalaGrua,40/EscalaGrua,40/EscalaGrua);
        l69.setStroke(ColorInterno);
        l69.setStrokeWidth(3);
        
        //=============================================================================
        
        //Decoracion de Rectangulos
        Rectangle rec = new Rectangle();
        rec.setX(10/EscalaGrua); rec.setY(15/EscalaGrua);
        rec.setWidth(33/EscalaGrua); rec.setHeight(33/EscalaGrua);
        rec.setFill(ColorExterno);
        
        
        Rectangle rec2 = new Rectangle();
        rec2.setX((CoorBigaSuperior)/EscalaGrua); rec2.setY(15/EscalaGrua);
        rec2.setWidth(33/EscalaGrua); rec2.setHeight(33/EscalaGrua);
        rec2.setFill(ColorExterno);
        
        //=============================================================================
        
        //Soporte Bigas Derecha
        Line l5 = new Line((CoorBigaSuperior)/EscalaGrua,40/EscalaGrua,(CoorBigaSuperior)/EscalaGrua,400/EscalaGrua);//l3
        l5.setStroke(Color.YELLOW);
        l5.setStrokeWidth(5);
        Line l6 = new Line((CoorBigaSuperior+30)/EscalaGrua,15/EscalaGrua,(CoorBigaSuperior+30)/EscalaGrua,400/EscalaGrua);//l4
        l6.setStroke(Color.YELLOW);
        l6.setStrokeWidth(5);
        
        //############################################################################################################
        //Guardar Line l16 = new Line(800,500,830,500);
        
        //Garra N°1:
        
        Rectangle rec3 = animator.getRectangleAnimation1();
        
        Rectangle rec4 = animator.getRectangleAnimation2();
        
        //================================================
        //Traslaciones de la Grua
        
        TranslateTransition translation = new TranslateTransition();
        translation.setByX(-230f);
        translation.setCycleCount(20);
        translation.setDuration(Duration.millis(1000));
        translation.setAutoReverse(true);
        
        TranslateTransition translation2 = new TranslateTransition();
        translation2.setByX(-230f);
        translation2.setByY(50);
        translation2.setCycleCount(20);
        translation2.setDuration(Duration.millis(1000));
        translation2.setAutoReverse(true);
        
        TranslateTransition translation3 = new TranslateTransition();
        translation3.setByX(-230f);
        translation3.setCycleCount(20);
        translation3.setDuration(Duration.millis(1000));
        translation3.setAutoReverse(true);
        
        TranslateTransition translation4 = new TranslateTransition();
        translation4.setByX(-230f);
        translation4.setByY(50);
        translation4.setCycleCount(20);
        translation4.setDuration(Duration.millis(1000));
        translation4.setAutoReverse(true);
        
        TranslateTransition translation5 = new TranslateTransition();
        translation5.setByX(-230f);
        translation5.setByY(50);
        translation5.setCycleCount(20);
        translation5.setDuration(Duration.millis(1000));
        translation5.setAutoReverse(true);
        
        TranslateTransition translation6 = new TranslateTransition();
        translation6.setByX(-230f);
        translation6.setByY(50);
        translation6.setCycleCount(20);
        translation6.setDuration(Duration.millis(1000));
        translation6.setAutoReverse(true);
        
        //Linea De las Garras:
        Line l70 = new Line();//De Rec3
        Line l71 = new Line(540/EscalaGrua,10/EscalaGrua,540/EscalaGrua,300/EscalaGrua);//De Rec4
        
        //La garra provisoria:
       /* Circle c1 = new Circle();
        c1.setCenterX(290/EscalaGrua); c1.setCenterY(300/EscalaGrua);
        c1.setRadius(6/EscalaGrua);
        c1.setFill(ColorInterno);*/
        
        Circle c2 = new Circle();
        c2.setCenterX(540/EscalaGrua); c2.setCenterY(300/EscalaGrua);
        c2.setRadius(6/EscalaGrua);
        c2.setFill(ColorExterno);
        
        
        for(int i=0;i<2;i++){
            TranslateTransition trans = new TranslateTransition();
            //trans.setByX();
            trans.setByY(20);
            trans.setCycleCount(i);
            trans.setDuration(Duration.millis(1000));
            trans.setAutoReverse(true);
            trans.setNode(c2);
            trans.play();
        }
        
        
        //translation.setNode(rec3);
        //translation2.setNode(l70);
        translation3.setNode(rec4);
        translation4.setNode(l71);
        //translation5.setNode(c1);
        //translation6.setNode(c2);
        
        
        //translation.play(); 
        //translation2.play();
        //translation3.play(); 
        //translation4.play();
        //translation5.play(); 
        //translation6.play(); 
        
        
        // Establece la escena en el escenario y lo muestra
        root.getChildren().addAll(l,l2,l3,l4,l5,l6);
        root.getChildren().addAll(l7,l8,l9,l10);
        root.getChildren().addAll(l11,l12,l13);
        root.getChildren().addAll(l18,l19,l20);
        root.getChildren().addAll(l21,l22,l23,l24);
        root.getChildren().addAll(l28,l29,l30);
        root.getChildren().addAll(l31,l32,l33,l34);
        root.getChildren().addAll(l37,l38,l39,l40);
        root.getChildren().addAll(l41,l42,l43,l44,l45);
        root.getChildren().addAll(l46,l47,l48,l49,l50);
        root.getChildren().addAll(l51,l52,l53,l54,l55);
        root.getChildren().addAll(l56,l57,l58,l59,l60);
        root.getChildren().addAll(l61,l62,l63,l64,l65);
        root.getChildren().addAll(l66,l67,l68,l69);
        root.getChildren().addAll(l70,l71);
        root.getChildren().addAll(rec,rec2,rec3,rec4);
        //root.getChildren().addAll(c1,c2);

    }
}