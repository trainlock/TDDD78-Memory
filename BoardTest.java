package se.liu.ida.linbe810.tddd78.memory;

public final class BoardTest
{
    private BoardTest() {}

    public static void main(String[] args) {
	Board board = new Board(4, 4);
	new MemoryFrame(board);
    }
}