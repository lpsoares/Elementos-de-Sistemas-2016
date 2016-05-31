public class SymbolTable {
	
	public SymbolTable() {
		// Creates a new empty symbol table 
	
	}
	
	public void startSubroutine() {
		// Starts a new subroutine scope 
		// (i.e., resets the subroutine's symbol table)
	
	}
	
	public void define(String name, String type, String kind) {
		// Defines a new identifier of a given name, type
		// and kind and assigns it a running index.
		// STATIC and FIELD identifiers have a class scope,
		// while ARG and VAR hve a subroutine scope
		
	}
	
	public int varCount(String kind) {
		// Returns the number of variables of a given kind
		// already defined in the current scope
		
	}
	
	public String kindOf(String name) {
		// Returns the kind of the named identifier in the
		// scope. If the identifier is unknown in the current scope,
		// returns NONE
		
	}
	
	public String typeOf(String name) {
		// Returns the type of the named identifier in the 
		// current scope
		
	}
	
	public int indexOf(String name) {
		// Returns the index assigned to the named identifier
		
	}
	
}