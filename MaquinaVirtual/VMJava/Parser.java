import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser implements AllParsers {
    private ArrayList<String> vm = new ArrayList<>();
    private int lineIndex;
    private String currentLine;
	private String currentCommand;
	private static Scanner s;
    
	public Parser(String path) throws IOException{
		lineIndex = 0;
        s = new Scanner(new File(path));
        vm.add(s.nextLine());
        currentLine = vm.get(lineIndex);
    }
	
	@Override
	public boolean hasMoreCommands() {
		if(s.hasNextLine()){
			return true;
		}
		s.close();
		return false;
	}

	@Override
	public void advance() {
		//Lê	o	próximo	comando	do	arquivo	de	entrada	e	torna	esse	comando	o	atual.	
		//Deve	ser	chamado	somente	se	hasMoreCommands()	for	verdadeiro.	
		//Inicialmente	não	há	nenhum	comando	atual.	
		vm.add(s.nextLine());
		lineIndex++;
		currentLine = vm.get(lineIndex); 
	}

	@Override
	public String commandType() {
		// Retorna	o	tipo	do	comando	atual	da	linguagem	de	máquina	virtual.	Os	
		//comandos	aritmé?cos	e	lógicos	retornam:	C_ARITHMETIC
		if(currentLine.contains("add") || currentLine.contains("sub") || currentLine.contains("neg")
		|| currentLine.contains("gt") || currentLine.contains("lt") || currentLine.contains("eq") 
		|| currentLine.contains("and") || currentLine.contains("or") || currentLine.contains("not"))
		{
			currentCommand =  "C_ARITHMETIC";	
			return "C_ARITHMETIC";	
		}
		else if(currentLine.contains("push")){
			currentCommand =  "C_PUSH";
			return "C_PUSH";
		}
		else if(currentLine.contains("call")){
			currentCommand =  "C_CALL";
			return "C_CALL";			
		}
		else if(currentLine.contains("pop")){
			currentCommand =  "C_POP";
			return "C_POP";
		}
		else if(currentLine.contains("label")){
			currentCommand =  "C_LABEL";
			return "C_LABEL";
		}
		else if(currentLine.contains("if-goto")){
			currentCommand =  "C_IF";
			return "C_IF";
		}
		else if(currentLine.contains("goto")){
			currentCommand =  "C_GOTO";
			return "C_GOTO";
		}
		else if(currentLine.contains("function")){
			currentCommand =  "C_FUNCTION";
			return "C_FUNCTION";
		}
		else if(currentLine.contains("return")){
			currentCommand =  "C_RETURN";	
			return "C_RETURN";	
		}
		else if(currentLine.contains("//")){
			return null;
		}
		return null;
	}

	@Override
	public String arg1() {
		if (currentCommand == "C_ARITHMETIC"){
			String[] args = currentLine.split(" ");
			return args[0];
		}
		else if( currentCommand == "C_RETURN"){
			return null;
		}
		else {
			String[] args = currentLine.split(" ");
			return args[1];
		}		
	}

	@Override
	public int arg2() {
		if (currentCommand == "C_ARITHMETIC" || currentCommand == "C_RETURN" || currentCommand == "C_GOTO" || 
			currentCommand == "C_IF" || currentCommand == "C_LABEL")
		{
			return 0;
		}
		else {
			String[] args = currentLine.split(" ");
			return Integer.parseInt(args[2]);
		}		
	}

}
