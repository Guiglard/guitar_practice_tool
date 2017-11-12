package guitarpracticetool.metronome.view;

import guitarpracticetool.metronome.domain.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import javax.inject.Inject;

public class MetronomePresenter implements TickObserver {

  private static final System.Logger LOGGER = System.getLogger(MetronomePresenter.class.getName());

  @FXML
  private AnchorPane mainPane;
  @FXML
  private StackPane plusBPMButton;
  @FXML
  private StackPane stopButton;
  @FXML
  private TextField bpmLabel;
  @FXML
  private StackPane playButton;
  @FXML
  private StackPane minusBPMButton;
  @Inject
  private Metronome metronome;

  @FXML
  void play() {
    metronome.start();
  }

  @FXML
  void stop() {
    metronome.stop();
  }

  @FXML
  void bpmChanged() {
  }

  @FXML
  void plusBPM() {
  }

  @FXML
  void minusBPM() {
  }

  @FXML
  void initialize() {
    mainPane.setBackground(Background.EMPTY);
    mainPane.setStyle("-fx-background-color: transparent;");
    metronome.registerTickObserver(this);
    bpmLabel.setText("" + metronome.getBPM());
  }

  @Override
  public void tick() {
  }
}
