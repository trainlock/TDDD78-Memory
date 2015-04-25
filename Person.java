package se.liu.ida.linbe810.tddd78.memory;

public class Person
{
    private String name;
    private int timeScore;

    public Person(String name, int timeScore) {
	this.name = name;
	this.timeScore = timeScore;
    }

    public int getTimeScore() {
	return timeScore;
    }
}
