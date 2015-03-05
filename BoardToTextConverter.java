package se.liu.ida.linbe810.tddd78.memory;

public class BoardToTextConverter
{
    public static String convertToText(Board gameBoard) {
	int height = gameBoard.getHeight();
	int width = gameBoard.getWidth();

	StringBuilder sb = new StringBuilder();

	for (int h = 0; h < height; h++) {
	    for (int w = 0; w < width; w++) {
		tileConverter(gameBoard.getTile(h, w), sb);
		sb.append(" ");
	    }
	    sb.append('\n');
	}
	String text = sb.toString();
	return text;
    }

    public static StringBuilder tileConverter(Tiles tileType, StringBuilder sb) {
	switch (tileType) {
	    case A:
		sb.append("A");
		break;
	    case B:
		sb.append("B");
		break;
	    case C:
		sb.append("C");
		break;
	    case D:
		sb.append("D");
		break;
	    case E:
		sb.append("E");
		break;
	    case F:
		sb.append("F");
		break;
	    case G:
		sb.append("G");
		break;
	    case H:
		sb.append("H");
		break;
	    case BACKSIDE:
		sb.append(" ");
		break;
	    case OUTSIDE:
		sb.append("| ");
		break;
	    default:
		sb.append("?");
		break;
	}
	return sb;
    }
}
