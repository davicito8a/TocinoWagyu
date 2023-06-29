package GUI;

import SortVisualizerCore.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
    private AnchorPane root;
    @FXML
    private TextField numberOfBoxes;
    @FXML
    private Button randomButton;
    @FXML
    private TextField numbers;
    @FXML
    private ComboBox<String> sortAlgorithm;
    @FXML
    private ComboBox<String> modes;
    @FXML
    private Button continueButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modes.setItems(FXCollections.observableArrayList("Normal", "Paso a paso"));
        sortAlgorithm.setItems(FXCollections.observableArrayList("Inserción", "Burbuja", "Coctelera", "Seleccion"));

    }

    @FXML
    private void continueToVisualization(ActionEvent event) throws IOException {
        if (modes.getValue() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, seleccione un modo");

            ButtonType okButton = new ButtonType("Aceptar", ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);

            alert.showAndWait();
            return; // Sale del método si no se ha seleccionado un modo
        }

        if (sortAlgorithm.getValue() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, seleccione un tipo de ordenamiento");

            ButtonType okButton = new ButtonType("Aceptar", ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);

            alert.showAndWait();
            return; // Sale del método si no se ha seleccionado un tipo de ordenamiento
        }

        if (numberValidation()) {
            if (modes.getValue().equals("Normal")) {
                Main.modeType = 0;
            } else {
                Main.modeType = 1;
            }

            if (sortAlgorithm.getValue().equals("Inserción")) {
                Main.sortType = 0;
            } else if (sortAlgorithm.getValue().equals("Burbuja")) {
                Main.sortType = 1;
            } else if (sortAlgorithm.getValue().equals("Coctelera")) {
                Main.sortType = 2;
            } else {
                Main.sortType = 3;
            }

            ArrayList<Integer> numbers = new ArrayList<>();
            String[] numberStrings = this.numbers.getText().split(",");
            for (String numberString : numberStrings) {
                try {
                    int number = Integer.parseInt(numberString);
                    numbers.add(number);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("El campo de números contiene valores no válidos");

                    ButtonType okButton = new ButtonType("Aceptar", ButtonData.OK_DONE);
                    alert.getButtonTypes().setAll(okButton);

                    alert.showAndWait();
                    return; // Sale del método si se encuentra un valor no válido
                }
            }
            Main.coordinates.clear();
            ArrayList<StackPane> rectangles = Main.getRectangles(numbers);
            if (Main.sortType == 3) {
                rectangles.addAll(Main.getLocomotoras());
            }

            Main.newAnimationWindow(numbers, rectangles);
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, inténtalo de nuevo");

            ButtonType okButton = new ButtonType("Aceptar", ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);

            alert.showAndWait();
        }
    }

    private boolean numberValidation() {
        Pattern pattern = Pattern.compile("^[0-9]+(,[0-9]+)*$");
        Matcher matcher = pattern.matcher(numbers.getText());

        String[] numberStrings = numbers.getText().split(",");
        boolean validNumberOfElements = numberStrings.length >= 16 && numberStrings.length <= 64;

        boolean validNumbers = Arrays.stream(numberStrings)
                .allMatch(s -> s.matches("^[0-9]+$") && Integer.parseInt(s) >= 1 && Integer.parseInt(s) <= 99);

        return matcher.find() && validNumberOfElements && validNumbers;
    }

    @FXML
    private void generateRandomNumbers(ActionEvent event) {
        if (!numberOfBoxes.getText().matches("^[0-9]+$")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("El campo debe contener solo números naturales");

            ButtonType okButton = new ButtonType("Aceptar", ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);

            alert.showAndWait();
            return; // Sale del método si el campo no contiene solo números
        }

        String numeros = "";
        int numeroRectangulos = Integer.parseInt(this.numberOfBoxes.getText());
        for (int i = 0; i < numeroRectangulos; i++) {
            numeros += ((int) (Math.random() * 99 + 1)) + ",";
        }
        numeros = numeros.substring(0, numeros.length() - 1);
        numbers.setText(numeros);
    }

}
