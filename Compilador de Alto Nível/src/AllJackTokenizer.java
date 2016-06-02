
public interface AllJackTokenizer {
	boolean hasMoreTokens();
	void advance();
	String tokenType();
	String keyWord();
	String symbol();
	String identifier();
	int intVal();
	String stringVal();
}
