import java.util.HashMap;
import java.util.Map;


public class SymbolTable {

	//Create HashMap (dictionary) to store predefined variables and user created variables
	//and their decimal values
	private Map<String, Integer> symbolTable = new HashMap<>();
	
	public SymbolTable() {
		symbolTable.put("R0",0); //Assign R0 to Register 0
 		symbolTable.put("R1",1); //..
		symbolTable.put("R2",2); //..
		symbolTable.put("R3",3); //..
		symbolTable.put("R4",4); //..
		symbolTable.put("R5",5); //..
		symbolTable.put("R6",6); //..
		symbolTable.put("R7",7); //..
		symbolTable.put("R8",8); //..
		symbolTable.put("R9",9); //..
		symbolTable.put("R10",10); //..
		symbolTable.put("R11",11); //..
		symbolTable.put("R12",12); //..
		symbolTable.put("R13",13); //..
		symbolTable.put("R14",14); //..
		symbolTable.put("R15",15); //Assign R0 to Register 15
		symbolTable.put("SP",0); //Assign STACK POINTER (SP) to Register 0
		symbolTable.put("LCL",1); //Assign LOCALS (to store function local variables) to Register 1
		symbolTable.put("ARG",2); //Assign ARGUMENTS (to store function args) to Register 2
		symbolTable.put("THIS",3); //Assign THIS  to Register 3
		symbolTable.put("THAT",4); //Assign THAT register to Register 3
		symbolTable.put("SCREEN",16384); //Assign SCREEN to Register 16384
		symbolTable.put("KBD",24576); //Assign KBD to Register 24576
	}
	
	//Add an entry (variable) created by the user to the dictionary and assign
	//a register to it. User added variables are assigned a register in order,
	//starting from 16. So created variables var1, var2, var3 will point to
	//register 16, 17, 18 and so on...
	public void addEntry(String symbol) {
		int variableSlot = 16;
		symbolTable.put(symbol, variableSlot);
		variableSlot++;
	}
	
	//Check if entry exists in the dictionary
	public boolean contains(String symbol) {
		return symbolTable.get(symbol) != null;
	}
	
	//Get decimal value (address/register) of selected entry
	public int getAddress(String symbol) {
		return symbolTable.get(symbol);
	}
}