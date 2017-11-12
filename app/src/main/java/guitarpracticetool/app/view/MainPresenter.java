package guitarpracticetool.app.view;

import guitarpracticetool.metronome.view.MetronomeView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.inject.Inject;

public class MainPresenter {

  @FXML // fx:id="topBar"
  private AnchorPane topBar;
  @FXML // fx:id="metronomeView"
  private AnchorPane metronomeView;
  @FXML // fx:id="mediaPlayer"
  private StackPane mediaPlayer; // Value injected by FXMLLoader
  @FXML // fx:id="exitButton"
  private Button exitButton; // Value injected by FXMLLoader
  @FXML // fx:id="showMetronomeViewButton"
  private Button showMetronomeViewButton; // Value injected by FXMLLoader
  @FXML // fx:id="showMediaPlayerViewButton"
  private Button showMediaPlayerViewButton; // Value injected by FXMLLoader

  @FXML
    // This method is called by the FXMLLoader when initialization is complete
  void initialize() {
  }

  @FXML
  void showMetronomeView(ActionEvent event) {
  }

  @FXML
  void showMediaPlayerView(ActionEvent event) {
  }

  @FXML
  void exit(ActionEvent event) {
    Stage mainStage = (Stage) topBar.getScene().getWindow();
    mainStage.close();
  }
}
