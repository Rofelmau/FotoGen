package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class Dialog_Controller implements Initializable, ButtonDesign {

    @FXML private Button buttonCancel;
    @FXML private Button buttonDelete;

    public void initialize(URL location, ResourceBundle resources) {
        setButtonDesign(buttonCancel);
        setButtonDesign(buttonDelete);
    }




    @FXML
    void closeDialog(){
        Main_Controller controller = Main_Controller.getController();
        Stage thisDialog = controller.getDialogStage();
        thisDialog.close();
    }

    @FXML
    void deleteImage(){
        Main_Controller.getController().removeImage();
        closeDialog();
    }
}
