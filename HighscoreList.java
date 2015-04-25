package se.liu.ida.linbe810.tddd78.memory;

import java.util.ArrayList;
import java.util.Collections;

public class HighscoreList
{
    private static final HighscoreList hsL = new HighscoreList();
    private static ArrayList<Person> hs = new ArrayList<Person>();
    private static final HighscorePanel hsP = new HighscorePanel();

    private HighscoreList() {
        System.out.println("hej");
        /** Listan skapas efterråt och är därför null!
        if (listSize() != 0) {
            printResult();
        }
         */
    }

    public static HighscoreList gethsL() {
	return hsL;
    }

    public void addScore(String name, int time) {
        hs.add(new Person(name, time));
        Collections.sort(hs, new HighscoreComparator());
    }

    public int listSize() {
        return hs.size();
    }
    public void printResult(){
            for (int i = 0; i < listSize(); i++) {
            hsP.textArea.setText(i+1+": "+ hs.get(i));
        }
    }
}
