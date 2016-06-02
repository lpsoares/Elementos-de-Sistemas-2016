
public interface AllJackTokenizer {
	boolean hasMoreTokens();
	void advance();
	String tokenType();
	String keyWord();
	char symbol();
	String identifier();
	int intVal();
	String stringVal();
}
