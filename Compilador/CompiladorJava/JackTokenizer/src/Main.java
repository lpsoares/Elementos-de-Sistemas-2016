import java.io.FileNotFoundException;


public class Main {

	private static JackTokenizer jk;

	public static void main(String[] args) {
		try {
			jk = new JackTokenizer("src/teste.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(jk.hasMoreTokens()){
			jk.advance();
			System.out.print(jk.getCurrentToken()+ " ");
			System.out.print(jk.tokenType() + " ");
			System.out.print(jk.keyWord()+ " ");
			System.out.print(jk.symbol()+ " ");
			System.out.print(jk.identifier()+ " ");
			System.out.print(jk.intVal()+ " ");
			System.out.println(jk.stringVal());
		}
	}
}
