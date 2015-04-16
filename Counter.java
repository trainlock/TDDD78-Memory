package se.liu.ida.linbe810.tddd78.memory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.*;

public class Counter
{
    private final ClockListener clockListener = new ClockListener();
    private final Timer timer = new Timer(1000, clockListener);
    private static final int ONE_MIN_IN_SEC = 60;
    private static final int RESET = 00;

private class ClockListener implements ActionListener {

    private int minutes;
    private int seconds;
    private String minute = null;
    private String second = null;

    @Override
    public void actionPerformed(final ActionEvent e) {
        NumberFormat format = new DecimalFormat("00");
        if (seconds == ONE_MIN_IN_SEC) {
            seconds = RESET;
            minutes++;
        }
        minute = format.format(minutes);
        second = format.format(seconds);
        System.out.println(minute + ":" + second);
        seconds++;
    }
}

}
