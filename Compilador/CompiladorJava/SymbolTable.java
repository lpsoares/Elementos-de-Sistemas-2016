import java.util.*;


public class SymbolTable {
	HashMap<String, String[]> classScope;
	HashMap<String, String[]> methodScope;
	int staticIndex;
	int fieldIndex;
	int varIndex;
	int argIndex;

	public SymbolTable() {
		// Creates a new empty symbol table
		this.classScope = new HashMap<String, String[]>();
		this.methodScope = new HashMap<String, String[]>();
		this.staticIndex = 0;
		this.fieldIndex = 0;
		this.varIndex = 0;
		this.argIndex = 0;

	}

	public void startSubroutine() {
		// Starts a new subroutine scope
		// (i.e., resets the subroutine's symbol table)
		this.methodScope.clear();

	}

	public void define(String name, String type, String kind) {
		// Defines a new identifier of a given name, type
		// and kind and assigns it a running index.
		// STATIC and FIELD identifiers have a class scope,
		// while ARG and VAR hve a subroutine scope
		if(kind == "STATIC") {
			String[] value = {type, kind, String.valueOf(this.staticIndex)};
			this.classScope.put(name, value);
			this.staticIndex++;

		} else if(kind == "FIELD") {
			String[] value = {type, kind, String.valueOf(this.fieldIndex)};
			this.classScope.put(name, value);
			this.fieldIndex++;

		} else if(kind == "ARG") {
			String[] value = {type, kind, String.valueOf(this.argIndex)};
			this.methodScope.put(name, value);
			this.argIndex++;

		} else if(kind == "VAR") {
			String[] value = {type, kind, String.valueOf(this.varIndex)};
			this.methodScope.put(name, value);
			this.varIndex++;

		}

	}

	public int varCount(String kind) {
		// Returns the number of variables of a given kind
		// already defined in the current scope

		return 0;
	}

	public String kindOf(String name) {
		// Returns the kind of the named identifier in the
		// scope. If the identifier is unknown in the current scope,
		// returns NONE
		if(methodScope.get(name) != null) {
			return (methodScope.get(name)[1]);
		} else if(classScope.get(name) != null) {
			return (classScope.get(name)[1]);
		}
		return "NONE";
	}

	public String typeOf(String name) {
		// Returns the type of the named identifier in the
		// current scope
		if(methodScope.get(name) != null) {
			return (methodScope.get(name)[0]);
		} else if(classScope.get(name) != null) {
			return (classScope.get(name)[0]);
		}
		return null;
	}

	public int indexOf(String name) {
		// Returns the index assigned to the named identifier
		if(methodScope.get(name) != null) {
			return Integer.valueOf(methodScope.get(name)[2]);
		} else if(classScope.get(name) != null) {
			return Integer.valueOf(classScope.get(name)[2]);
		}
		return -1;
	}
}
