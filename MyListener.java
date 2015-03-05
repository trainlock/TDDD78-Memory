package se.liu.ida.linbe810.tddd78.memory;

import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class MyListener implements MouseListener
{
    private ArrayList<MouseListener> mouseListeners;

    @Override public void mousePressed(final MouseEvent e) {}

    public void addMouseListener(MouseListener ml){
	mouseListeners.add(ml);
    }
}
