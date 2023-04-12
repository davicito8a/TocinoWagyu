package SortVisualizer;
/*
import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class ShowCode {

    private TextArea pseudoCodeTextArea; // Área de texto que contiene el pseudo código
    private Label currentLineLabel; // Etiqueta que muestra la línea actual del pseudo código
    private int currentLineIndex = 0; // Índice de la línea actual
    private Color highlightedColor = Color.RED; // Color de resaltado de línea
    private Color defaultColor = Color.BLACK; // Color predeterminado de línea

    public ShowCode(TextArea pseudoCodeTextArea, Label currentLineLabel) {
        this.pseudoCodeTextArea = pseudoCodeTextArea;
        this.currentLineLabel = currentLineLabel;
        updateCurrentLineLabel(); // Actualiza el número de línea actual en la etiqueta
    }

    // Actualiza la etiqueta con el número de línea actual del pseudo código
    private void updateCurrentLineLabel() {
        String[] lines = pseudoCodeTextArea.getText().split("\\n");
        currentLineLabel.setText(lines[currentLineIndex]);
    }

    // Resalta la línea actual en el área de texto del pseudo código
    private void highlightCurrentLine() {
        String[] lines = pseudoCodeTextArea.getText().split("\\n");
        String currentLine = lines[currentLineIndex];
        String highlightedLine = currentLine.replaceFirst("^\\s+", "");
        pseudoCodeTextArea.setStyle("-fx-highlight-fill: " + highlightedColor.toString() + "; -fx-highlight-text-fill: black;");
        pseudoCodeTextArea.selectRange(pseudoCodeTextArea.getText().indexOf(highlightedLine), pseudoCodeTextArea.getText().indexOf(highlightedLine) + highlightedLine.length());
        pseudoCodeTextArea.deselect();
    }

    // Quita el resaltado de la línea actual en el área de texto del pseudo código
    private void unhighlightCurrentLine() {
        pseudoCodeTextArea.setStyle("-fx-highlight-fill: null; -fx-highlight-text-fill: null;");
    }

    // Ejecuta el pseudo código y muestra las transiciones en la pantalla
    public void execute() {
        InsertionSorter sorter = new InsertionSorter(numbers, stackpanes); // Instancia un ordenador de inserción con los números a ordenar y las pilas visuales correspondientes
        ArrayList<Transition> transitions = sorter.getSortingTransitions(); // Obtiene las transiciones de ordenamiento

        for (Transition transition : transitions) { // Itera a través de las transiciones
            transition.play(); // Reproduce la transición actual
            currentLineIndex++; // Incrementa el número de línea actual
            updateCurrentLineLabel(); // Actualiza la etiqueta con el número de línea actual
            highlightCurrentLine(); // Resalta la línea actual en el área de texto del pseudo código
            transition.setOnFinished(event -> { // Establece un evento que se activa cuando la transición actual termina
                unhighlightCurrentLine(); // Quita el resaltado de la línea actual
            });
        }
    }
}*/
