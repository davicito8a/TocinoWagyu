package SortVisualizer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class InputController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private ComboBox<?> modes;
    @FXML
    private TextField n;
    @FXML
    private Button button;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void continueToVisualization(ActionEvent event) {
        Main.n = Integer.parseInt(n.getText());
        
    }
 
}
