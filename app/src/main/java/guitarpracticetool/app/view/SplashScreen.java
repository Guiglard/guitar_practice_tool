package guitarpracticetool.app.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SplashScreen extends javafx.application.Preloader {

  private Stage preloaderStage;
  private Scene preloaderScene;

  @Override
  public void init() throws Exception {
    VBox loading = new VBox(20);
    loading.setMaxWidth(Region.USE_PREF_SIZE);
    loading.setMaxHeight(Region.USE_PREF_SIZE);
    loading.getChildren().add(new ProgressBar());
    loading.getChildren().add(new Label("Please wait..."));
    BorderPane root = new BorderPane(loading);
    preloaderScene = new Scene(root);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.preloaderStage = primaryStage;
    preloaderStage.setWidth(800);
    preloaderStage.setHeight(600);
    preloaderStage.setScene(preloaderScene);
    preloaderStage.show();
  }

  @Override
  public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
    if (stateChangeNotification.getType() == StateChangeNotification.Type.BEFORE_START) {
      preloaderStage.hide();
    }
  }
}
