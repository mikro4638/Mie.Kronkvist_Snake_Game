package no.uib.inf101.snake.midi;

import java.io.InputStream;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;

/**
 * Spiller av slangemusikk.
 * Eksempelbruk:
 * <code>
 * SnakeSong music = new SnakeSong();
 * music.run(); 
 * </code>
 */
public class SnakeSong implements Runnable {
    private static final String SNAKEMUSIC = "snakeSang.midi";
    private Sequencer sequencer;

    /** 
     * Runnable-grensesnittets run-metode. 
     * Henter sangfilen fra resources og spiller av. 
     */
    @Override
    public void run() {
        InputStream song = SnakeSong.class.getClassLoader().getResourceAsStream(SNAKEMUSIC);
        this.doPlayMidi(song, true);
    }

    /** 
     * Spiller av MIDI-filen. 
     * @param is InputStream for MIDI-filen.
     * @param loop Angir om avspillingen skal loope.
     */
    private void doPlayMidi(final InputStream is, final boolean loop) {
        try {
            this.doStopMidiSounds();
            (this.sequencer = MidiSystem.getSequencer()).setSequence(MidiSystem.getSequence(is));
            if (loop) {
                this.sequencer.setLoopCount(-1);
            }
            this.sequencer.open();
            this.sequencer.start(); // Starter avspillingen
        } catch (Exception e) {
            this.midiError("" + e);
        }
    }

    /** 
     * Stopper MIDI-lydene. 
     */
    public void doStopMidiSounds() {
        try {
            if (this.sequencer == null || !this.sequencer.isRunning()) {
                return;
            }
            this.sequencer.stop();
            this.sequencer.close();
        } catch (Exception e) {
            this.midiError("" + e);
        }
        this.sequencer = null;
    }

    /** 
     * Pauser MIDI-lydene. 
     */
    public void doPauseMidiSounds() {
        try {
            if (this.sequencer == null || !this.sequencer.isRunning()) {
                return;
            }
            this.sequencer.stop();
        } catch (Exception e) {
            this.midiError("" + e);
        }
    }

    /** 
     * Fortsetter avspilling av MIDI-lydene. 
     */
    public void doUnpauseMidiSounds() {
        try {
            if (this.sequencer == null) {
                return;
            }
            this.sequencer.start();
        } catch (Exception e) {
            this.midiError("" + e);
        }
    }

    /** 
     * Håndterer MIDI-feil. 
     * @param msg Feilmeldingen som skal vises. 
     */
    private void midiError(final String msg) {
        System.err.println("Midi-feil: " + msg);
        this.sequencer = null;
    }
}
