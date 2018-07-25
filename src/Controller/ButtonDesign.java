package Controller;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

public interface ButtonDesign {

    default void onMouseOver(Button btn){
        double currentSize = btn.getFont().getSize();
        Font newFont = new Font(currentSize+0.5);
        btn.setFont(newFont);
        btn.setStyle("-fx-background-color:#FFFFFF;" +
                "-fx-border-color:green");
    }

    default void onMouseExit(Button btn){
        double currentSize = btn.getFont().getSize();
        Font newFont = new Font(currentSize-0.5);
        btn.setFont(newFont);
        btn.setStyle("-fx-background-color:#FFFFFF;" +
                "-fx-border-color:grey");
    }
    default void onMouseReleased(Button btn){
        double currentSize = btn.getFont().getSize();
        Font newFont = new Font(currentSize+1);
        btn.setFont(newFont);
        defaultButtonDesign(btn);
    }

    default void onMousePressed(Button btn){
        double currentSize = btn.getFont().getSize();
        Font newFont = new Font(currentSize-1);
        btn.setFont(newFont);
        defaultButtonDesign(btn);
    }

    default void defaultButtonDesign(Button btn){
        btn.setStyle("-fx-background-color:#FFFFFF;" +
                "-fx-border-color:grey;");
    }
    default void setButtonDesign(Button btn){
        defaultButtonDesign(btn);
        btn.setOnMouseEntered(event -> onMouseOver(btn));
        btn.setOnMouseExited(event -> onMouseExit(btn));
        btn.setOnMousePressed(event -> onMousePressed(btn));
        btn.setOnMouseReleased(event -> onMouseReleased(btn));

    }
}
