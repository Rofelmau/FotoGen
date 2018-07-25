package Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;


public class Main_Controller implements Initializable, ButtonDesign {

    @FXML private StackPane dndZone;
    @FXML private StackPane gridBackGround00;
    @FXML private StackPane gridBackGround01;
    @FXML private StackPane gridBackGround02;
    @FXML private StackPane gridBackGround10;
    @FXML private StackPane gridBackGround11;
    @FXML private StackPane gridBackGround12;
    @FXML private StackPane gridBackGround20;
    @FXML private StackPane gridBackGround21;
    @FXML private StackPane gridBackGround22;
    @FXML private StackPane gridBackGround30;
    @FXML private StackPane gridBackGround31;
    @FXML private StackPane gridBackGround32;
    @FXML private StackPane gridBackGround40;
    @FXML private StackPane gridBackGround41;
    @FXML private StackPane gridBackGround42;
    @FXML private GridPane gridpane;
    @FXML private ImageView imageView00;
    @FXML private ImageView imageView01;
    @FXML private ImageView imageView02;
    @FXML private ImageView imageView10;
    @FXML private ImageView imageView11;
    @FXML private ImageView imageView12;
    @FXML private ImageView imageView20;
    @FXML private ImageView imageView21;
    @FXML private ImageView imageView22;
    @FXML private ImageView imageView30;
    @FXML private ImageView imageView31;
    @FXML private ImageView imageView32;
    @FXML private ImageView imageView40;
    @FXML private ImageView imageView41;
    @FXML private ImageView imageView42;
    @FXML private TextField mainPath;
    @FXML private TextField pathDE;
    @FXML private TextField pathEN;
    @FXML private CheckBox generateDE;
    @FXML private CheckBox generateEN;
    @FXML private Button buttonCreate;
    @FXML private Button buttonDelete;

    private HashMap<String,ImageView> ImageViews;
    private HashMap<ImageView,StackPane> StackPanes;

    private LinkedList<File> fileList = null;
    private HashMap<ImageView, Integer> position;

    private ImageView imagePreView;
    private Image deleteIcon;
    private Image deleteIconSmall;
    private int startPosition;

    private Stage dialogStage;
    private ImageView imageToDelete = null;


    private static Main_Controller controller = null;

    static Main_Controller getController() {
        return controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = this;

        this.dndZone.setStyle("-fx-border-color: grey;"
                + "-fx-border-style: solid;");

        this.dndZone.setOnDragOver(this::mouseDragOver);
        this.dndZone.setOnDragDropped(this::mouseDragDropped);
        this.dndZone.setOnDragExited(event -> this.dndZone.setStyle("-fx-border-color: #C6C6C6;"));

        this.gridpane.setGridLinesVisible(true);
        this.gridpane.setStyle("-fx-border-color: grey;"
                + "-fx-border-style: solid;"
                + "-fx-border-width: 1");

        ImageViews = new HashMap<>();
        StackPanes = new HashMap<>();
        position = new HashMap<>();
        Platform.runLater(() -> {
            ImageViews.put("imageView00", imageView00);
            StackPanes.put(imageView00, gridBackGround00);
            position.put(imageView00, 0);
            ImageViews.put("imageView01", imageView01);
            StackPanes.put(imageView01, gridBackGround01);
            position.put(imageView01, 1);
            ImageViews.put("imageView02", imageView02);
            StackPanes.put(imageView02, gridBackGround02);
            position.put(imageView02, 2);
            ImageViews.put("imageView10", imageView10);
            StackPanes.put(imageView10, gridBackGround10);
            position.put(imageView10, 3);
            ImageViews.put("imageView11", imageView11);
            StackPanes.put(imageView11, gridBackGround11);
            position.put(imageView11, 4);
            ImageViews.put("imageView12", imageView12);
            StackPanes.put(imageView12, gridBackGround12);
            position.put(imageView12, 5);
            ImageViews.put("imageView20", imageView20);
            StackPanes.put(imageView20, gridBackGround20);
            position.put(imageView20, 6);
            ImageViews.put("imageView21", imageView21);
            StackPanes.put(imageView21, gridBackGround21);
            position.put(imageView21, 7);
            ImageViews.put("imageView22", imageView22);
            StackPanes.put(imageView22, gridBackGround22);
            position.put(imageView22, 8);
            ImageViews.put("imageView30", imageView30);
            StackPanes.put(imageView30, gridBackGround30);
            position.put(imageView30, 9);
            ImageViews.put("imageView31", imageView31);
            StackPanes.put(imageView31, gridBackGround31);
            position.put(imageView31, 10);
            ImageViews.put("imageView32", imageView32);
            StackPanes.put(imageView32, gridBackGround32);
            position.put(imageView32, 11);
            ImageViews.put("imageView40", imageView40);
            StackPanes.put(imageView40, gridBackGround40);
            position.put(imageView40, 12);
            ImageViews.put("imageView41", imageView41);
            StackPanes.put(imageView41, gridBackGround41);
            position.put(imageView41, 13);
            ImageViews.put("imageView42", imageView42);
            StackPanes.put(imageView42, gridBackGround42);
            position.put(imageView42, 14);

            setButtonDesign(buttonCreate);
            setButtonDesign(buttonDelete);
        });

        imagePreView = new ImageView();
        imagePreView.setFitWidth(375);
        imagePreView.setFitHeight(265);
        imagePreView.setPreserveRatio(true);
        AnchorPane n = (AnchorPane) gridpane.getParent();

        n.getChildren().add(imagePreView);
        imagePreView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0.5, 0, 0)");

        try {
            InputStream ras = getClass().getResourceAsStream("/View/delete.png");
            deleteIcon = new Image(ras, 17, 17, true, false);
            ras.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream ras = getClass().getResourceAsStream("/View/delete.png");
            deleteIconSmall = new Image(ras, 16, 16, true, false);
            ras.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private  void mouseDragOver(final DragEvent e) {
        Dragboard db = e.getDragboard();
        if (db.hasFiles()) {
            if (this.isAcceptedMedium(db)) {
                this.dndZone.setStyle("-fx-border-color: lightgreen;"
                        + "-fx-border-width: 3;"
                        + "-fx-background-color: #C6C6C6;"
                        + "-fx-border-style: solid;");
                e.acceptTransferModes(TransferMode.COPY);
            }
        } else {
            e.consume();
        }
    }

    private void clearGridPane(){
        //clear gridpane
        BiConsumer<? super String, ? super ImageView> biConsumer = (k, v) -> {
            v.setImage(null);

            LinkedList<Button> buttonsToRemove = new LinkedList<>();
            StackPane sp = StackPanes.get(v);
            Consumer<Node> cs = (node) -> {
                if(node instanceof Button)
                    buttonsToRemove.add((Button) node);
            };
            sp.getChildren().forEach(cs);
            sp.getChildren().removeAll(buttonsToRemove);
        };
        ImageViews.forEach(biConsumer);

        fileList = new LinkedList<>();
    }

    private void mouseDragDropped(final DragEvent e){
        try {
            loadWaitForResultScene();
        } catch (IOException r) {
            r.printStackTrace();
        }


        //clear gridpane
        clearGridPane();


        Dragboard db = e.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            fileList.addAll(db.getFiles());
            success = true;
        }
        e.setDropCompleted(success);
        e.consume();

        if(fileList.size() > 0) {
            Platform.runLater(() -> {
                showImagesInGridPane();
                ImageViews.forEach((k, v) -> {
                    if (v.getImage() != null) {
                        ImageView deleteFunc = new ImageView(deleteIcon);
                        Button deleteButton = new Button();
                        deleteButton.setGraphic(deleteFunc);
                        StackPane sp = StackPanes.get(v);
                        sp.getChildren().add(deleteButton);
                        deleteButton.toFront();
                        deleteButton.setStyle("-fx-background-color: white; " +
                                "-fx-border-color: grey;" +
                                "-fx-border-width: 1;");
                        deleteButton.setOnMouseEntered(event -> Fotomanager.getInstance().getScene().getRoot().setCursor(Cursor.HAND));
                        deleteButton.setOnMousePressed(event -> deleteFunc.setImage(deleteIconSmall));
                        deleteButton.setOnMouseReleased(event -> {
                            deleteFunc.setImage(deleteIcon);
                            reorderImagesInGird(v);
                        });
                        v.setOnMouseEntered(event -> mouseOver(event, v));
                        v.setOnMousePressed(event -> event.setDragDetect(true));
                        v.setOnMouseExited(event -> mouseExit());
                        v.setOnDragDetected(event -> {
                            startPosition = position.get(v);
                            Dragboard dragboard = v.startDragAndDrop(TransferMode.MOVE);

                            ClipboardContent content = new ClipboardContent();
                            content.putImage(v.getImage());
                            dragboard.setContent(content);

                            dragboard.setDragView(v.snapshot(null, null));
                            event.consume();
                        });
                        v.setOnMouseDragged(event -> event.setDragDetect(false));
                        v.setOnMouseDragReleased(event -> mouseExit());
                        v.setOnMouseReleased(event -> mouseExit());

                        StackPanes.get(v).setOnDragDropped(event -> dragDropped(event, v));
                        v.setOnDragDropped(event -> dragDropped(event, v));


                        StackPanes.get(v).setOnDragExited(event -> StackPanes.get(v).setStyle("-fx-background-color: white;"));
                        StackPanes.get(v).setOnDragOver(event -> dragOver(event, v));
                        v.setOnDragExited(event -> StackPanes.get(v).setStyle("-fx-background-color: white;"));
                        v.setOnDragOver(event -> dragOver(event, v));

                    } else {
                        removeMouseEffects(v);
                    }
                });
                Platform.setImplicitExit(true);
                dialogStage.close();
            });
        }else{
            Platform.runLater(() -> {
                Platform.setImplicitExit(true);
                dialogStage.close();
            });
        }
    }

    private void reorderImagesInGird(ImageView v){

        imageToDelete = v;

        final URL fxmlUrl = getClass().getResource("/View/Dialog.fxml");

        final FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        Scene dialogScene = null;
        try {
            dialogScene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(Fotomanager.getInstance().getStage());
        dialogStage.setScene(dialogScene);
        dialogStage.setResizable(false);
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.show();
    }

    void removeImage(){

        int currentPosition = position.get(imageToDelete);
        fileList.remove(currentPosition);
        Image tmp;
            do {
                int next = currentPosition + 1;
                int nextR = gridGetR(next), nextC = gridGetC(next);
                if(next < 15)
                    tmp = ImageViews.get("imageView" + nextR + "" + nextC).getImage();
                else
                    tmp = null;
                int currentR = gridGetR(currentPosition), currentC = gridGetC(currentPosition);
                ImageViews.get("imageView" + currentR + "" + currentC).setImage(tmp);
                currentPosition++;
            } while (tmp != null);

        BiConsumer<? super String, ? super ImageView> biConsumer = (k, view) -> {

            LinkedList<Button> buttonsToRemove = new LinkedList<>();
            StackPane sp = StackPanes.get(view);
            Consumer<Node> cs = (node) -> {
                if(view.getImage() == null && node instanceof Button)
                    buttonsToRemove.add((Button) node);
            };
            sp.getChildren().forEach(cs);
            sp.getChildren().removeAll(buttonsToRemove);
            if(view.getImage() == null)
                removeMouseEffects(view);
        };

        ImageViews.forEach(biConsumer);

    }

    private void removeMouseEffects(ImageView view){
        view.setOnMouseEntered(event -> Fotomanager.getInstance().getScene().getRoot().setCursor(Cursor.DEFAULT));
        view.setOnMouseExited(event -> {
        });
        view.setOnMousePressed(event -> {
        });
        view.setOnMouseDragged(event -> {
        });
        view.setOnDragOver(event -> {
        });
    }


    Stage getDialogStage() {
        return dialogStage;
    }

    private void swapImagesInGrid(int start, int end){
        int r = gridGetR(start), c = gridGetC(start);
        Image tmp = ImageViews.get("imageView" + r + "" + c).getImage();

        int type = 1;
        if(start > end)
            type = -1;

        start *= type;
        end *= type;

        for(; start < end; start++){
            int rNew = gridGetR(start*type+type);
            int cNew = gridGetC(start*type+type);

            ImageView currentView = ImageViews.get("imageView" + r + "" + c);
            currentView.setImage(ImageViews.get("imageView" + rNew + "" + cNew).getImage());
            c = cNew;
            r = rNew;
        }

        ImageView currentView = ImageViews.get("imageView" + r + "" + c);
        currentView.setImage(tmp);
    }


    private int gridGetR(int position){
        return (position / 3);
    }
    private int gridGetC(int position){
        return position % 3;
    }

    private void showImagesInGridPane(){
        int c = 0, r = 0;
        for (File aFileList : fileList) {
            if (r < 5) {

                    try {
                        FileInputStream fis = new FileInputStream(aFileList.getAbsolutePath());
                        //Image myImage = new Image(fis, 1024, 768, true, false);
                        //Image myImage = new Image(fis, 720, 480, true, false);

                        Image myImage = new Image(fis, 640, 480, true, false);

                        ImageView currentView = ImageViews.get("imageView" + r + "" + c);
                        currentView.setImage(myImage);
                        fis.close();
                        c++;
                        if (c % 3 == 0) {
                            c = 0;
                            r++;
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

            }
        }
    }


    private void dragDropped(DragEvent event, ImageView view){
        Dragboard d = event.getDragboard();
        if (d.hasImage())
        {
            if(startPosition != position.get(view)){
                if(startPosition < position.get(view)){
                    for(int i = startPosition; i < position.get(view); i++){
                        File tmp = fileList.set(i+1,fileList.get(i));
                        fileList.set(i,tmp);
                    }
                }else{
                    for(int i = startPosition; i > position.get(view); i--){
                        File tmp = fileList.set(i-1,fileList.get(i));
                        fileList.set(i,tmp);
                    }
                }
                swapImagesInGrid(startPosition,position.get(view));
                //System.gc();
            }
        }
        event.setDropCompleted(true);
    }

    private void mouseOver(MouseEvent event, ImageView view){
        Fotomanager.getInstance().getScene().getRoot().setCursor(Cursor.OPEN_HAND);
        imagePreView.setImage(view.getImage());
        imagePreView.setX(18);
        if(event.getSceneY() > 373) {
            imagePreView.setY(event.getSceneY()-340);
        }else{
            imagePreView.setY(event.getSceneY()+72);
        }
    }

    private void mouseExit(){
        imagePreView.setImage(null);
        Fotomanager.getInstance().getScene().getRoot().setCursor(Cursor.DEFAULT);
    }

    private void dragOver(DragEvent event, ImageView v){
        Dragboard d = event.getDragboard();
        if (d.hasImage() && !(startPosition ==  position.get(v)))
        {
            event.acceptTransferModes(TransferMode.MOVE);
            StackPanes.get(v).setStyle("-fx-background-color: lightgreen;");
        }
        event.consume();
    }


    private boolean isAcceptedMedium(final Dragboard db){
        for(File f: db.getFiles()){
            if (! f.getName().toLowerCase().contains(".jpg")
                    && !f.getName().toLowerCase().contains(".jpeg")
                    && !f.getName().toLowerCase().contains(".png")
                    )
                return false;
        }
        return true;
    }

    private void showDialogStage(Scene dialogScene){
        dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(Fotomanager.getInstance().getStage());
        dialogStage.setScene(dialogScene);
        dialogStage.setResizable(false);
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.show();
    }

    private void loadWaitForResultScene() throws IOException{
        final URL fxmlUrl = getClass().getResource("/View/WaitForResult.fxml");
        final FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);

        showDialogStage(new Scene(fxmlLoader.load()));
    }

    @FXML
    public void generateData(){
        Fotomanager.getInstance().getScene().getRoot().setCursor(Cursor.WAIT);


        if(fileList!= null
            &&!fileList.isEmpty()
                && !mainPath.getText().isEmpty()
                && (( generateDE.isSelected() && !pathDE.getText().isEmpty()) || (generateEN.isSelected() && !pathEN.getText().isEmpty())))
        {
            try {
                loadWaitForResultScene();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Runnable task = this::createImagesBackgroundTask;

            // Run the task in a background thread
            Thread thread = new Thread(task);
            // Terminate the running thread if the application exits
            thread.setDaemon(true);
            // Start the thread0
            thread.start();


        }else{
            buttonCreate.setStyle("-fx-background-color: #FFFFFF;");
            Fotomanager.getInstance().getScene().getRoot().setCursor(Cursor.DEFAULT);
        }


    }


    private void createImagesBackgroundTask() {
        if(!mainPath.getText().isEmpty() ) {
            if( generateDE.isSelected() && !pathDE.getText().isEmpty()) {
                for (int i = 0; i < fileList.size(); i++) {
                    importFoto(fileList.get(i),mainPath.getText(),i+1,pathDE.getText());
                }
            }
            if( generateEN.isSelected() && !pathEN.getText().isEmpty()){
                String fs = System.getProperty("file.separator");
                try{
                    FileChannel testChannel = new FileInputStream(mainPath.getText() + fs + "ENG" ).getChannel();
                    testChannel.close();
                }catch(IOException f) {
                    new File(mainPath.getText() + fs + "ENG").mkdir();
                }
                for (int i = 0; i < fileList.size(); i++) {
                    importFoto(fileList.get(i),mainPath.getText() + fs + "ENG",i+1,pathEN.getText());
                }
            }
        }
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        defaultButtonDesign(buttonCreate);
        Fotomanager.getInstance().getScene().getRoot().setCursor(Cursor.DEFAULT);


        Platform.runLater(() -> {
            Platform.setImplicitExit(true);
            dialogStage.close();
        });
    }

    private void importFoto(File file, String mainPath, int index, String name){
        String fs = System.getProperty("file.separator");
        String path = file.getPath();
        FileChannel sourceChannel;
        name = name.replace(" ", "-");
        try {
            sourceChannel = new FileInputStream(path).getChannel();
            FileChannel destChannel = new FileOutputStream(mainPath + fs + index + "-" + name + "." + getFormat(file)).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

            destChannel.close();
            sourceChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFormat(final File file){
        String format = file.getName();
        String[] split = format.split("\\.");
        return split[1];
    }

    @FXML
    public void deleteData(){
     /*
        try {
            loadWaitForResultScene();
        } catch (IOException r) {
            r.printStackTrace();
        }

    */
        generateDE.setSelected(false);
        generateEN.setSelected(false);
        mainPath.setText(null);
        pathDE.setText(null);
        pathEN.setText(null);
        clearGridPane();

            Platform.runLater(() -> {

                ImageViews.forEach((k, v) -> removeMouseEffects(v));

                //Platform.setImplicitExit(true);
               // dialogStage.close();
            });

    }
}
