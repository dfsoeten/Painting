package painting.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import painting.model.WorldModel;

/**
 * Created by daan on 10-6-17.
 */
public class MainController {

    public void Start(Stage primaryStage) throws Exception{

        //Root Element
        BorderPane root = new BorderPane();

        //Get Controllers
        //Painting
        FXMLLoader painting = new FXMLLoader(getClass().getResource("../view/PaintingView.fxml"));
        root.setCenter(painting.load());
        PaintingController paintingController = painting.getController();

        //Menu
        FXMLLoader menu = new FXMLLoader(getClass().getResource("../view/MenuView.fxml"));
        root.setTop(menu.load());
        MenuController menuController = menu.getController();

        //Create model Objects
        WorldModel worldModel = new WorldModel();

        //Set models
        paintingController.initModel(worldModel);
        menuController.initModel(worldModel);

        //Launch the application
        Scene scene = new Scene(root, 800, 630);
        primaryStage.setTitle("Daan Soeten - Painting");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
