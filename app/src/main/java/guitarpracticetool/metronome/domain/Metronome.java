package guitarpracticetool.metronome.domain;

import javax.annotation.*;
import javax.sound.midi.*;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.lang.System.Logger;

/**
 * The metronome class.
 */
public class Metronome implements MetaEventListener {

  private static Logger LOGGER = System.getLogger(Metronome.class.getName());

  private int bpm = 60;
  private Sequencer sequencer;
  private Sequence sequence;
  private Track track;

  private ArrayList<TickObserver> tickObservers = new ArrayList<>();

  @PostConstruct
  public void initialize() {
    LOGGER.log(Level.INFO, "Initializing metronome");
    setUpMidi();
    buildTrackAndStart();
  }

  @PreDestroy
  public void close() {
    LOGGER.log(Level.INFO,"Closing the sequencer...");
    sequencer.stop();
    sequencer.close();
  }

  public void start() {
    LOGGER.log(Level.INFO,"Starting the sequencer...");
    sequencer.start();
  }

  public void stop() {
    LOGGER.log(Level.INFO,"Stopping the sequencer...");
    sequencer.stop();
  }

  public void setBPM(int bpm) {
    this.bpm = bpm;
    sequencer.setTempoInBPM(getBPM());
  }

  public int getBPM() {
    return bpm;
  }

  public void meta(MetaMessage message) {
    LOGGER.log(Level.DEBUG,"MetaMessage : " + message.getType());
    notifyTickObservers();
    if (message.getType() == 47) {
      sequencer.stop();
      //setBPM(getBPM());
    }
  }

  public void setUpMidi() {
    try {
      sequencer = MidiSystem.getSequencer();
      sequencer.open();
      sequencer.addMetaEventListener(this);
      sequence = new Sequence(Sequence.PPQ, 4);
      track = sequence.createTrack();
      sequencer.setTempoInBPM(getBPM());
      sequencer.setTickPosition(0);
      sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void buildTrackAndStart() {
    int[] trackList = {35, 0, 46, 0};

    sequence.deleteTrack(null);
    track = sequence.createTrack();

    makeTracks(trackList);
    track.add(newSoundEvent(ShortMessage.PROGRAM_CHANGE, 9, 1, 0, 4));
    try {
      sequencer.setSequence(sequence);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void makeTracks(int[] list) {
    for (int i = 0; i < list.length; i++) {
      int key = list[i];
      if (key != 0) {
        track.add(newSoundEvent(ShortMessage.NOTE_ON, 9, key, 100, i));
        track.add(newSoundEvent(ShortMessage.NOTE_OFF, 9, key, 100, i + 1));
      }
      track.add(newMetaEvent(i));

    }
  }

  public MidiEvent newSoundEvent(int comd, int chan, int one, int two, int tick) {
    MidiEvent event = null;
    try {
      ShortMessage soundMessage = new ShortMessage();
      soundMessage.setMessage(comd, chan, one, two);
      event = new MidiEvent(soundMessage, tick);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return event;
  }

  public MidiEvent newMetaEvent(int tick) {
    MidiEvent event = null;
    try {
      MetaMessage metaMessage = new MetaMessage();
      metaMessage.setMessage(0x01, "smb".getBytes(), "smb".getBytes().length);
      event = new MidiEvent(metaMessage, tick);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return event;
  }

  public void registerTickObserver(TickObserver o) {
    tickObservers.add(o);
  }

  public void notifyTickObservers() {
    for (int i = 0; i < tickObservers.size(); i++) {
      TickObserver observer = tickObservers.get(i);
      observer.tick();
    }
  }

  public long getTickLength() {
    return sequencer.getTickLength();
  }

  public long getTickPosition() {
    return sequencer.getTickPosition();
  }

  public double getTickProgression() {
    return (getTickPosition() + 1.) / getTickLength();
  }
}
