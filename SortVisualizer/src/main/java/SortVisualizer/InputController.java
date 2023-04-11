package SortVisualizer;
// En esta clase se controla el modo y se controla la cantidad elementos del arreglo
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class InputController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private ComboBox<String> modes;
    @FXML
    private TextField n;
    @FXML
    private Button button;
    @FXML
    private TextField numeroRectangulos;
    @FXML
    private Button random;

 @Override
public void initialize(URL url, ResourceBundle rb) {
    // Establece el contenido de "modes" a una lista observable de dos cadenas
    modes.setItems(FXCollections.observableArrayList("Normal mode", "Step by step mode"));
}    

@FXML
private void continueToVisualization(ActionEvent event) throws MalformedURLException { 
    // Verifica si los números ingresados en "n" son válidos
    if(numberValidation()){
        // Si el modo seleccionado es "Step by step mode", establece "type" en 1, de lo contrario, en 0
        if(modes.getValue().equals("Step by step mode"))
            Main.type = 1;
        else
            Main.type = 0;
        
        // Crea una lista de enteros a partir de las cadenas de números separadas por comas en "n"
        ArrayList<Integer> numbers = new ArrayList();
        String[] numberStrings = this.n.getText().split(",");
        for (String numberString : numberStrings) {
            int number = Integer.parseInt(numberString);
            numbers.add(number);
        }
        
        // Crea una lista de objetos StackPane a partir de la lista de enteros "numbers"
        ArrayList<StackPane> rectangles = Main.getRectangles(numbers);
        
        // Crea una nueva ventana de visualización y pasa los números y rectángulos a la misma
        Main.newAnimationWindow(numbers, rectangles);
    } else {
        // Si los números ingresados no son válidos, muestra un mensaje de error en la consola
        System.out.println("Ingresa de nuevo");
    }
}

private boolean numberValidation(){
    // Uso de expresiones regulares para validar que el contenido de "n" sean números separados por comas
    Pattern pattern = Pattern.compile("^[0-9]+(,[0-9]+)*$");
    Matcher matcher = pattern.matcher(n.getText());
    
    return matcher.find();
}

@FXML
private void generateRandom(ActionEvent event) {
    // Genera una cadena separada por comas de números aleatorios y la establece como contenido de "n"
    String numeros = "";
    int numeroRectangulos = Integer.parseInt(this.numeroRectangulos.getText());
    for(int i = 0; i < numeroRectangulos; i++){
        numeros += ((int)(Math.random()*99 + 1)) + ",";
    }
    numeros = numeros.substring(0, numeros.length() - 1);
    n.setText(numeros);
}
 
}

