package SortVisualizer;
// En esta clase se controla el modo y se controla la cantidad elementos del arreglo
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
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
private void continueToVisualization(ActionEvent event) throws IOException { 
    // Verifica si se ha seleccionado un modo
    if(modes.getValue() == null) { // Si el valor del ChoiceBox es nulo, significa que no se ha seleccionado un modo
        Alert alert = new Alert(AlertType.ERROR); // Crea una ventana de diálogo de error
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, seleccione un modo");

        ButtonType okButton = new ButtonType("Aceptar", ButtonData.OK_DONE); 
        alert.getButtonTypes().setAll(okButton); 

        Optional<ButtonType> result = alert.showAndWait(); 
        if (result.get() == okButton) {
        }
        return; 
    }

    // Verifica si los números ingresados en "n" son válidos
    if(numberValidation()){ // Si los números son válidos, continúa
        // Si el modo seleccionado es "Step by step mode", establece "type" en 1, de lo contrario, en 0
        if(modes.getValue().equals("Step by step mode"))
            Main.type = 1;
        else
            Main.type = 0;
        
        // Crea una lista de enteros a partir de las cadenas de números separadas por comas en "n"
        ArrayList<Integer> numbers = new ArrayList(); // Crea una nueva lista vacía
        String[] numberStrings = this.n.getText().split(","); // Obtiene los números como una cadena separada por comas
        for (String numberString : numberStrings) { // Itera sobre cada número
            int number = Integer.parseInt(numberString); // Convierte la cadena a un número entero
            numbers.add(number); // Agrega el número a la lista
        }
        
        // Crea una lista de objetos StackPane a partir de la lista de enteros "numbers"
        ArrayList<StackPane> rectangles = Main.getRectangles(numbers);
        
        // Crea una nueva ventana de visualización y pasa los números y rectángulos a la misma
        Main.newAnimationWindow(numbers, rectangles);
    } else { // Si los números no son válidos, muestra un mensaje de error
        System.out.println("error papasito"); // Imprime un mensaje de error en la consola
        Alert alert = new Alert(AlertType.ERROR); // Crea una ventana de diálogo de error
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, inténtalo de nuevo");

        ButtonType okButton = new ButtonType("Aceptar", ButtonData.OK_DONE); // Crea un botón "Aceptar"
        alert.getButtonTypes().setAll(okButton); // Agrega el botón "Aceptar" al cuadro de diálogo

        Optional<ButtonType> result = alert.showAndWait(); // Muestra el cuadro de diálogo y espera a que el usuario haga clic en un botón
        if (result.get() == okButton) {
            // Código para cerrar el cuadro de diálogo (en este caso, no se hace nada

        }
    }
}

private boolean numberValidation(){
    // Uso de expresiones regulares para validar que el contenido de "n" sean números separados por comas
    Pattern pattern = Pattern.compile("^[0-9]+(,[0-9]+)*$");
    Matcher matcher = pattern.matcher(n.getText());

    // Validar que el número de elementos esté entre 16 y 64
    String[] numberStrings = n.getText().split(",");
    boolean validNumberOfElements = numberStrings.length >= 16 && numberStrings.length <= 64;

    // Validar que todos los números estén entre 1 y 99
    boolean validNumbers = Arrays.stream(numberStrings)
                                  .allMatch(s -> s.matches("^[0-9]+$") && Integer.parseInt(s) >= 1 && Integer.parseInt(s) <= 99);

    return matcher.find() && validNumberOfElements && validNumbers;
}

@FXML
private void generateRandom(ActionEvent event) {
    // Verificar si el campo de texto solo contiene números
    if (!numeroRectangulos.getText().matches("^[0-9]+$")) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("El campo debe contener solo números");

        ButtonType okButton = new ButtonType("Aceptar", ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == okButton) {
            // Código para cerrar el cuadro de diálogo
        }
        return;
    }
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

