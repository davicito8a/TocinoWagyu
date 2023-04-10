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
        modes.setItems(FXCollections.observableArrayList("Normal mode", "Step by step mode"));
    }    
    
    @FXML
    private void continueToVisualization(ActionEvent event) throws MalformedURLException { 
        if(numberValidation()){
            if(modes.getValue().equals("Step by step mode"))
                Main.type = 1;
            else
                Main.type = 0;
            
            ArrayList<Integer> numbers = new ArrayList();

            String[] numberStrings = this.n.getText().split(",");

            for (String numberString : numberStrings) {
                int number = Integer.parseInt(numberString);
                numbers.add(number);
            }
            System.out.println(numbers);
            ArrayList<StackPane> rectangles = Main.getRectangles(numbers);
            Main.newAnimationWindow(numbers, rectangles);
        } else {
            System.out.println("Ingresa de nuevo");
        }
    }
    
    private boolean numberValidation(){
        // Uso de expresiones regulares para validaciones 
        Pattern pattern = Pattern.compile("^[0-9]+(,[0-9]+)*$");
        Matcher matcher = pattern.matcher(n.getText());
        
        return matcher.find();
    }

    @FXML
    private void generateRandom(ActionEvent event) {
        String numeros = "";
        int numeroRectangulos = Integer.parseInt(this.numeroRectangulos.getText());
        
        for(int i = 0; i < numeroRectangulos; i++){
            numeros += ((int)(Math.random()*99 + 1)) + ",";
        }
        numeros = numeros.substring(0, numeros.length() - 1);
        System.out.println(numeros);
        n.setText(numeros);
    }
 
}

