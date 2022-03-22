package semantic;

import java.util.*;
import ast.Definition;

public class SymbolTable {
	
	private int scope=-1; // whenever we set, we increment -> global will be 0
	private List<Map<String,Definition>> table;

	public SymbolTable()  {
		table = new ArrayList<>();
		set();
	}

	/**
	 * create a new scope
	 * increment scope attribute (unless we are setting the global scope)
	 */
	public void set() {
		scope++;
		table.add(new HashMap<>(){});
	}

	/**
	 * remove the last scope
	 */
	public void reset() {
		table.remove(scope);
		scope--;
	}

	/**
	 * return true if the insertion was successful
	 * return false if there exists a variable definition with the same name in the same scope
	 * @param definition to add
	 * @return
	 */
	public boolean insert(Definition definition) {
		if (findInCurrentScope(definition.getName()) != null)
			return false;
		table.get(scope).put(definition.getName(), definition);
		definition.setScope(scope);
		return true;
	}

	/**
	 * search for a definition in the symboltable and return the definition found
	 * returns null if not found
	 * @param id name of variable
	 * @return
	 */
	public Definition find(String id) {
		Definition result;
		for (int i=scope; i>-1; i--){
			result = table.get(i).get(id);
			if (result != null)
				return result;
		}
		return null;
	}

	/**
	 * search for a definition in this scope and return the definition found
	 * returns null if not found
	 * @param id name of variable
	 * @return
	 */
	public Definition findInCurrentScope(String id) {
		return table.get(scope).get(id);
	}
}
