import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Code {
	
	private String binResult;
	ArrayList<String> entrada;
	private Map<String, String> dictionaryComp = new HashMap<String, String>();
	private Map<String, String> dictionaryDest = new HashMap<String, String>();
	private Map<String, String> dictionaryJump = new HashMap<String, String>();


	
	public Code(ArrayList<String> entrada){
		this.entrada = new ArrayList<String>();
		this.entrada = entrada;
		
		//Declarando o dicionario com informacao de todas comps.
		dictionaryComp.put("0", "0101010");
		dictionaryComp.put("1", "0111111");
		dictionaryComp.put("-1", "0111010");
		dictionaryComp.put("D", "0001100");
		dictionaryComp.put("A", "0110000");
		dictionaryComp.put("M", "1110000");
		dictionaryComp.put("!D", "0001101");
		dictionaryComp.put("!A", "0110001");
		dictionaryComp.put("!M", "1110001");
		dictionaryComp.put("-D", "0001111");
		dictionaryComp.put("-A", "0110011");
		dictionaryComp.put("-M", "1110011");
		dictionaryComp.put("D+1", "0011111");
		dictionaryComp.put("A+1", "0110111");
		dictionaryComp.put("M+1", "1110111");
		dictionaryComp.put("D-1", "0001110");
		dictionaryComp.put("A-1", "0110010");
		dictionaryComp.put("M-1", "1110010");
		dictionaryComp.put("D+A", "0000010");
		dictionaryComp.put("D+M", "1000010");
		dictionaryComp.put("D-A", "0010011");
		dictionaryComp.put("D-M", "1010011");
		dictionaryComp.put("A-D", "0000111");
		dictionaryComp.put("M-D", "1000111");
		dictionaryComp.put("D&A", "0000000");
		dictionaryComp.put("D&M", "1000000");
		dictionaryComp.put("D|A", "0010101");
		dictionaryComp.put("D|M", "1010101");
		
		//Declarando o dicionario com informacao de todas dests.
		dictionaryDest.put(null, "000");
		dictionaryDest.put("M", "001");
		dictionaryDest.put("D", "010");
		dictionaryDest.put("MD", "011");
		dictionaryDest.put("A", "100");
		dictionaryDest.put("AM", "101");
		dictionaryDest.put("AD", "110");
		dictionaryDest.put("AMD", "111");
		
		//Declarando o dicionario com informacao de todos jumps.
		dictionaryJump.put(null, "000");
		dictionaryJump.put("JGT", "001");
		dictionaryJump.put("JEQ", "010");
		dictionaryJump.put("JGE", "011");
		dictionaryJump.put("JLT", "100");
		dictionaryJump.put("JNE", "101");
		dictionaryJump.put("JLE", "110");
		dictionaryJump.put("JMP", "111");

	}
	
	public String checkFirstElement(){
		if (this.entrada.get(0) == "@") {
			return "000";
		}
		
		else {
			return "111";
		}	
	}
	
	public String transformToValue(){
		int result = Integer.parseInt(this.entrada.get(1));
		return Integer.toBinaryString(result);
	}
	
	public String transformComp(){
		String result;
		result = dictionaryComp.get(this.entrada.get(1));
		return result;
	}
	
	public String transformDest(){
		String result;
		result = dictionaryDest.get(this.entrada.get(2));
		return result;
	}
	
	public String transformJump(){
		String result;
		result = dictionaryJump.get(this.entrada.get(3));
		return result;
	}
	
	public void finalResult(){
		binResult += checkFirstElement();
		if (checkFirstElement() == "000"){
			
			binResult += transformToValue();
		}
		else {
			
			binResult += transformComp();
			binResult += transformDest();
			binResult += transformJump();
		}
	}
	
	public String getBinResult(){
		return this.binResult;
	}
	
}
