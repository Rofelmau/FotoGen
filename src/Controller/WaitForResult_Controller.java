package Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class WaitForResult_Controller implements Initializable{
    @FXML Text waitText;
    private int rounds;
    private Thread thread;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // System.out.println("Debug 0");
            rounds = 1;
            Runnable task = this::updateGUIBackgroundTask;
            // Run the task in a background thread
            thread = new Thread(task);
            // Terminate the running thread if the application exits
            thread.setDaemon(true);
            // Start the thread0
            //thread.start();
            thread.start();

        Platform.runLater(() -> {
            Main_Controller controller = Main_Controller.getController();
            Stage thisDialog = controller.getDialogStage();
            thisDialog.setOnHiding(event -> {
                if (thread.isAlive())
                        thread.interrupt();
            });

        });

    }

    private void changeText(){
        StringBuilder dots = new StringBuilder();
        for (int i = 0; i < rounds; i++)
            dots.append(" .");
       // System.out.println("rounds " + dots);
        waitText.setText("Der Vorgang wird bearbeitet" + dots);
        rounds++;
        if (rounds > 4)
            rounds = 1;
    }

    private void updateGUIBackgroundTask() {
        do {
          //  System.out.println("Debug 3");
            changeText();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
             //   System.out.println("Debug 2");
                Thread.currentThread().interrupt();
            }
        } while (!Thread.currentThread().isInterrupted());
    }
}
