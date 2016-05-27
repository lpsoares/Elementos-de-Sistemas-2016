import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class JackTokenizer implements AllJackTokenizer{
	
	private ArrayList<String> vm = new ArrayList<>();
    private int currentLineIndex;
	private String currentToken;
	private static Scanner s;
	private final String delim = "(){}><=+-[]";
    private String currentTokenType;
	private String currentKeyWord;
	private StringTokenizer st;
	private String[] keyword = new String[]{"class","constructor","function","method","field",
			"static","var","int","char","boolean","void","true","false","null","this","let","do",
			"if","else","while","return"};
	private String[] symbol = new String[]{"}","}","(",")","[","]",".",",",";","+","-","*","/","&",
			"|","<",">","=","~"};
		
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
		
		currentToken =  currentToken.replaceAll("\\s","");
		
		if(Arrays.asList(keyword).contains(currentToken)){
			currentTokenType = "KEYWORD";
		}
		
		else if(Arrays.asList(symbol).contains(currentToken)){
			currentTokenType = "SYMBOL";
		}
		
		else if(Integer.parseInt(currentToken) >= 0){
			currentTokenType = "INT_CONST";
		}
		
		else if(currentToken.matches("[a-zA-Z]+")){
			currentTokenType = "STRING_CONST";
		}
		
		else {
			currentTokenType = "IDENTIFIER";
		}
		
		return currentTokenType;
	}

	@Override
	public String keyWord() {
		currentToken =  currentToken.replaceAll("\\s","");

		if (currentTokenType == "KEYWORD"){
	         if (currentToken.equals("class")) { 
	        	 currentKeyWord = "CLASS";
	         }
	         else if(currentToken.equals("method")){
	        	 currentKeyWord = "METHOD";
	         }
	         else if(currentToken.equals("function")){
	        	 currentKeyWord = "FUNCTION";
	         }
	         else if(currentToken.equals("constructor")){
	        	 currentKeyWord = "CONSTRUCTOR";
	         }
	         else if(currentToken.equals("int")){
	        	 currentKeyWord = "INT";
	         }
	         else if(currentToken.equals("boolean")){
	        	 currentKeyWord = "BOOLEAN";
	         }
	         else if(currentToken.equals("char")){
	        	 currentKeyWord = "CHAR";
	         }
	         else if(currentToken.equals("void")){
	        	 currentKeyWord = "VOID";
	         }
	         else if(currentToken.equals("var")){
	        	 currentKeyWord = "VAR";
	         }
	         else if(currentToken.equals("static")){
	        	 currentKeyWord = "STATIC";
	         }
	         else if(currentToken.equals("field")){
	        	 currentKeyWord = "FIELD";
	         }
	         else if(currentToken.equals("let")){
	        	 currentKeyWord = "LET";
	         }
	         else if(currentToken.equals("do")){
	        	 currentKeyWord = "DO";
	         }
	         else if(currentToken.equals("if")){
	        	 currentKeyWord = "IF";
	         }
	         else if(currentToken.equals("else")){
	        	 currentKeyWord = "ELSE";
	         }
	         else if(currentToken.equals("while")){
	        	 currentKeyWord = "WHILE";
	         }
	         else if(currentToken.equals("return")){
	        	 currentKeyWord = "RETURN";
	         }
	         else if(currentToken.equals("true")){
	        	 currentKeyWord = "TRUE";
	         }
	         else if(currentToken.equals("false")){
	        	 currentKeyWord = "FALSE";
	         }
	         else if(currentToken.equals("null")){
	        	 currentKeyWord = "NULL";
	         }
	         else if(currentToken.equals("this")){
	        	 currentKeyWord = "THIS";
	         }
		}
		
		else{
			currentKeyWord = "ERROR";
		}
		return currentKeyWord;       
	}

	@Override
	public char symbol() {
		currentToken =  currentToken.replaceAll("\\s","");

		if (currentTokenType == "SYMBOL"){
			return currentToken.charAt(0);
		}
		else{
			return 0;
		}
	}

	@Override
	public String indetifier() {
		if (currentTokenType == "IDENTIFIER"){
			return currentToken;
		}
		else{
			return null;
		}
	}

	@Override
	public int intVal() {
		currentToken =  currentToken.replaceAll("\\s","");

		if (currentTokenType == "INT_CONST"){
			return Integer.parseInt(currentToken);
		}
		else{
			return 0;
		}
	}

	@Override
	public String stringVal() {
		currentToken =  currentToken.replaceAll("\\s","");

		if (currentTokenType == "STRING_CONST"){
			return currentToken;
		}
		else{
			return null;
		}
	}
	
}
