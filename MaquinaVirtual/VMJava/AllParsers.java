
public interface AllParsers {
	public boolean hasMoreCommands();
	public void advance();
	public String commandType();
	public String arg1();
	public int arg2();
}
