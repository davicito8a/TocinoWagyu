package SortVisualizer;

import java.io.IOException;
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
    private TextField numbers;
    @FXML
    private Button continueButton;
    @FXML
    private TextField numberOfBoxes;
    @FXML
    private Button randomButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modes.setItems(FXCollections.observableArrayList("Normal mode", "Step by step mode"));
    }    
    
    @FXML
    private void continueToVisualization(ActionEvent event) throws IOException { 
        if(numbersPatternValidation()){
            if(modes.getValue().equals( "Normal mode")){
                Main.type = 0;
            } else if (modes.getValue().equals("Step by step mode")){ 
                Main.type = 1;
            }
            
            ArrayList<Integer> numbers = new ArrayList();
            String[] numberStrings = this.numbers.getText().split(",");

            for(String numberString : numberStrings) {
                int number = Integer.parseInt(numberString);
                numbers.add(number);
            }
            
            Main.coordinates.clear();
            ArrayList<StackPane> rectangles = Main.getRectangles(numbers);
            Main.newAnimationWindow(numbers, rectangles);
        }
    }
    
    private boolean numbersPatternValidation(){
        Pattern pattern = Pattern.compile("^[0-9]+(,[0-9]+)*$");
        Matcher matcher = pattern.matcher(numbers.getText());
        
        return matcher.find();
    }
    
    @FXML
    private void generateRandomNumbers(ActionEvent event) {
        String numeros = "";
        int numeroRectangulos = Integer.parseInt(this.numberOfBoxes.getText());
        
        for(int i = 0; i < numeroRectangulos; i++){
            numeros += ((int)(Math.random()*99 + 1)) + ",";
        }
        
        numeros = numeros.substring(0, numeros.length() - 1);
        numbers.setText(numeros);
    }
 
}