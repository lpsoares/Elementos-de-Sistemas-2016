import java.io.FileNotFoundException;
import java.util.StringTokenizer;

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
			System.out.println(jk.getCurrentToken());
		}
	}
}
