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
			System.out.print(jk.tokenType() + " ");
			if (jk.tokenType() == "IDENTIFIER"){
				System.out.println(jk.identifier());
			}
			if(jk.tokenType() == "SYMBOL"){
				System.out.println(jk.symbol());
			}
			if (jk.tokenType() == "KEYWORD"){
				System.out.println(jk.keyWord());
			}
			if(jk.tokenType()=="INT_CONST"){
				System.out.println(jk.intVal());
			}
			if(jk.tokenType()== "STRING_CONST"){
				System.out.println(jk.stringVal());
			}
		}
	}
}
