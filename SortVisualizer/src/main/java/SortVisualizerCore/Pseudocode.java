package SortVisualizerCore;

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Pseudocode {

    private VBox pseudocodeBox;
    private ArrayList<Label> labels = new ArrayList();
    private final String initialStyle = "-fx-text-fill: white";
    private final String newStyle = "-fx-background-color: yellow";

    public Pseudocode(VBox pseudocodeBox) {
        this.pseudocodeBox = pseudocodeBox;
    }

    public void selectInsertionSortLines() {
        addLines("for i = n",
                "\twhile(currentNumber < numbers[j - 1])\n\t\tnumbers[j] = numbers [j - 1]",
                "\tnumbers[j] = currentNumber");
    }

    public void selectBubbleSortLines() {
        addLines("for i = n",
                "\tfor j = n2",
                "\t\tif(numbers[j + 1] < numbers [j])",
                "\t\t\tswap(j, j + 1)");
    }

    public void selectCocktailSortLines() {
        addLines("while(swapped)",
                "\tswapped = false",
                "\tfor i = n",
                "\t\tif(numbers[i + 1] < numbers [i])",
                "\t\t\tswap(i, i + 1)",
                "\t\t\tswapped = true",
                "\tif(swapped = false)",
                "\t\tbreak",
                "\tswapped = false",
                "\tfor i = n2",
                "\t\tif(numbers[i + 1] < numbers [i])",
                "\t\t\tswap(i, i + 1)",
                "\t\t\tswapped = true");
    }

    public void selectSelecionSortLines() {
        addLines("for i = n",
                "\tmax_idx = c",
                "\tfor j = n2",
                "\t\tif(numbers[j] > numbers[max_idx]",
                "\t\t\tmax_idx = j",
                "\tremoveInCurrentArray(max_idx)",
                "\taddInNewArray(n3, max)");
    }

    private void addLines(String... lines) {
        for (String line : lines) {
            Label label = new Label(line);
            label.setStyle(initialStyle);
            label.setFont(new Font(14));
            labels.add(label);
        }
        pseudocodeBox.getChildren().addAll(labels);
        pseudocodeBox.setTranslateX(0.0 * Main.windowWidth);
        if(Main.sortType == 3){
            pseudocodeBox.setTranslateY(0.7 * Main.windowHeight);
        }
        else{
            pseudocodeBox.setTranslateY(0.8 * Main.windowHeight);
        }
        pseudocodeBox.setStyle("-fx-background-color: black");

        if (Main.sortType == 2) {
            double toY = 0.5;
            pseudocodeBox.setOnMouseEntered(e -> {
                TranslateTransition moveUpward = new TranslateTransition();
                moveUpward.setNode(pseudocodeBox);
                moveUpward.setToY(toY * Main.windowHeight);
                moveUpward.play();
            });

            pseudocodeBox.setOnMouseExited(e -> {
                TranslateTransition moveUpward = new TranslateTransition();
                moveUpward.setNode(pseudocodeBox);
                moveUpward.setToY(0.8 * Main.windowHeight);
                moveUpward.play();
            });
        }
    }

    public Animation changeLabelProperties(int lineNumber, String newText) {
        Timeline changeLabelPropertiesAnimation = new Timeline();

        if (!newText.isEmpty()) {
            KeyFrame newTextFrame = new KeyFrame(Duration.millis(1), event -> labels.get(lineNumber - 1).setText(newText));
            changeLabelPropertiesAnimation.getKeyFrames().addAll(newTextFrame);
        }

        KeyFrame newStyleFrame = new KeyFrame(Duration.millis(1), event -> labels.get(lineNumber - 1).setStyle(newStyle));
        KeyFrame initialStyleFrame = new KeyFrame(Duration.millis(400), event -> labels.get(lineNumber - 1).setStyle(initialStyle));

        changeLabelPropertiesAnimation.getKeyFrames().addAll(newStyleFrame, initialStyleFrame);

        return changeLabelPropertiesAnimation;
    }

    public Animation changeLabelProperties(int lineNumber, String newText, int duration) {
        Timeline changeLabelPropertiesAnimation = new Timeline();

        if (!newText.isEmpty()) {
            KeyFrame newTextFrame = new KeyFrame(Duration.millis(1), event -> labels.get(lineNumber - 1).setText(newText));
            changeLabelPropertiesAnimation.getKeyFrames().addAll(newTextFrame);
        }

        KeyFrame newStyleFrame = new KeyFrame(Duration.millis(1), event -> labels.get(lineNumber - 1).setStyle(newStyle));
        KeyFrame initialStyleFrame = new KeyFrame(Duration.millis(duration), event -> labels.get(lineNumber - 1).setStyle(initialStyle));

        changeLabelPropertiesAnimation.getKeyFrames().addAll(newStyleFrame, initialStyleFrame);

        return changeLabelPropertiesAnimation;
    }

    public Animation selectLine(int lineNumber, String newText, int duration, boolean wannaSelect) {
        Timeline changeLabelPropertiesAnimation = new Timeline();

        if (!newText.isEmpty()) {
            KeyFrame newTextFrame = new KeyFrame(Duration.millis(1), event -> labels.get(lineNumber - 1).setText(newText));
            changeLabelPropertiesAnimation.getKeyFrames().addAll(newTextFrame);
        }

        KeyFrame newStyleFrame = new KeyFrame(Duration.millis(1), event -> labels.get(lineNumber - 1).setStyle(newStyle));
        changeLabelPropertiesAnimation.getKeyFrames().addAll(newStyleFrame);

        return changeLabelPropertiesAnimation;
    }

    public Animation unselectLine(int lineNumber) {
        Timeline changeLabelPropertiesAnimation = new Timeline();

        KeyFrame initialStyleFrame = new KeyFrame(Duration.millis(1), event -> labels.get(lineNumber - 1).setStyle(initialStyle));
        changeLabelPropertiesAnimation.getKeyFrames().add(initialStyleFrame);

        return changeLabelPropertiesAnimation;
    }

}
