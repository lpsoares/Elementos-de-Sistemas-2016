
public interface AllJackTokenizer {
	boolean hasMoreTokens();
	void advance();
	String tokenType();
	String keyWord();
	char symbol();
	String indetifier();
	int intVal();
	String stringVal();
}
