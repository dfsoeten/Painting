package painting;

import javafx.application.Application;
import javafx.stage.Stage;
import painting.controller.MainController;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Create new mainController
        MainController mainController = new MainController();

        //Start Application
        mainController.Start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
