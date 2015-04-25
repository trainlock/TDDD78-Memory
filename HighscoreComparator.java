package se.liu.ida.linbe810.tddd78.memory;

import java.util.Comparator;

public class HighscoreComparator implements Comparator<Person>
{
    public int compare(Person p1, Person p2){
	return Integer.valueOf(p1.getTimeScore()).compareTo(Integer.valueOf(p2.getTimeScore()));
    }
 }
