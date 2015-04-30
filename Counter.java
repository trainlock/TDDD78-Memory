package se.liu.ida.linbe810.tddd78.memory;

import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.*;

/**
 * se.liu.ida.linbe810.tddd78.memory.Counter works as a timer that register how long the
 * project have been running.
 */

public class Counter extends JPanel
{
    private Timer timer = null;

    private JTextField textField;
    private static final int TIMER_SIZE = 100;
    private static final int SEC_IN_MIN = 60;
    private int seconds = 0;
    private int minutes = 0;


    public Counter() {
        this.textField = new JTextField("00:00");
        textField.setSize(new Dimension(TIMER_SIZE, TIMER_SIZE));
       	textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setEditable(false);

        runTimer();
        timer.setInitialDelay(0);

        this.add(textField);
    }

    public JTextField getTextField() {
        return textField;
    }

    /**
     * Runs the timer
     */
    public void runTimer(){
	final Action doOneStep = new AbstractAction() {
	    @Override public void actionPerformed(ActionEvent e) {

                if(seconds == SEC_IN_MIN){
                    minutes ++;
                    seconds = 0;
                }
                seconds++;

                String time = String.format("%02d:%02d", minutes, seconds);
                getTextField().setText(time);
	    }
	};

	this.timer = new Timer(1000, doOneStep);
	timer.setCoalesce(true);
	timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }

    public int getTime() {
        return seconds;
    }
}