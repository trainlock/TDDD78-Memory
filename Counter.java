package se.liu.ida.linbe810.tddd78.memory;

import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.*;

public class Counter extends JPanel
{
    private Timer timer;
    public JTextField textField;
    private static final int TIMER_SIZE = 100;
    private int seconds = 0;
    private int minutes = 0;


    public Counter() {
        this.textField = new JTextField("00:00");
        textField.setSize(new Dimension(TIMER_SIZE, TIMER_SIZE));
       	textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        runTimer();
        timer.setInitialDelay(0);

        this.add(textField);
    }

    public void runTimer(){
	final Action doOneStep = new AbstractAction() {
	    @Override public void actionPerformed(ActionEvent e) {

                if(seconds == 60){
                    minutes ++;
                    seconds = 0;
                }
                seconds++;
                String time = String.format("%02d:%02d",minutes,seconds);
                textField.setText(time);
	    }
	};

	this.timer = new Timer(1000, doOneStep);
	timer.setCoalesce(true);
	timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }
}