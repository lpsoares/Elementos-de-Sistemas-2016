import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class JackTokenizer implements AllJackTokenizer{
	
    private ArrayList<String> vm = new ArrayList<>();
    private int currentLineIndex;
	private String currentToken;
	private static Scanner s;
	private final String delim = "(){}><=+-[]	 ";
	private StringTokenizer st;
	
	public String getCurrentToken(){
		return currentToken;
	}

	public JackTokenizer(String path) throws FileNotFoundException{
		currentLineIndex = 0;
        s = new Scanner(new File(path));
        while(s.hasNextLine())
        	vm.add(s.nextLine());
        st = new StringTokenizer(vm.get(currentLineIndex),delim,true);
	   }
	

	@Override
	public boolean hasMoreTokens() {
		if(st.hasMoreTokens()){
			return true;
		}
		if(vm.size()>currentLineIndex+1){
			return true;
		}
		return false;
	}

	@Override
	public void advance() {
		if(st.hasMoreTokens()){
			currentToken = 	st.nextToken();
		}
		else{
			if(currentLineIndex+1 < vm.size()){
			currentLineIndex++;
			st = new StringTokenizer(vm.get(currentLineIndex),delim,true);
			advance();
			}
			else{
				currentLineIndex++;
			}
		}
	}

	@Override
	public String tokenType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String keyWord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public char symbol() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String indetifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int intVal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String stringVal() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
