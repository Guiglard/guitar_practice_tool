package guitarpracticetool.app.view;

import com.airhacks.afterburner.injection.Injector;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;

/**
 * Main class of Guitar Practice Tool
 */
public class Main extends Application {

  private static System.Logger LOGGER = System.getLogger(Main.class.getName());
  private double xOffset;
  private double yOffset;

  @Override
  public void init() throws Exception {
    // preloads the MetronomePresenter and its @Inject fields for performance reasons
    //Injector.instantiatePresenter(MetronomePresenter.class);
  }

  @Override
  public void start(Stage stage) throws Exception {
    LOGGER.log(System.Logger.Level.INFO, "Starting Guitar Practice Tool");
    Parent root = new MainView().getView();
    makeStageMoveable(stage, root);
    stage.setTitle("Guitar Practice Tool");
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());
    stage.setScene(scene);
    stage.show();
  }

  private void makeStageMoveable(Stage stage, Parent root) {
    stage.initStyle(StageStyle.UNDECORATED);
    root.setOnMousePressed(event -> {
      xOffset = event.getSceneX();
      yOffset = event.getSceneY();
    });
    root.setOnMouseDragged(event -> {
      stage.setX(event.getScreenX() - xOffset);
      stage.setY(event.getScreenY() - yOffset);
    });
  }

  @Override
  public void stop() throws Exception {
    LOGGER.log(System.Logger.Level.INFO, "Stopping Guitar Practice Tool");
    Injector.forgetAll();
  }
}
