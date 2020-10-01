package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import etl.utility.DialogBox;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon/app.png")));
        primaryStage.setTitle("ETL APPLICATION ");
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();


        /* In questa sezione posso gestire l'evento di chiusura
         * e comportarmi di conseguenza. Dovrò pertanto pensare
         * ad una qualche operazione da eseguire prima che
         * l'applicazione venga chiusa.
         */
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            if(DialogBox.confirmBox("Closing Application",
                    "Are you sure to close application?")){
                primaryStage.close();
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
