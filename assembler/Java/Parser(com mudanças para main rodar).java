import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Rafael on 22/04/2016.
 */
public class Parser {

    private ArrayList<String> asm = new ArrayList<>();
    private int linha;
    private String input;

    public Parser (String path) throws IOException {
        String linha;
        BufferedReader asmReader;
        try (FileReader fr = new FileReader(path)) {
            asmReader = new BufferedReader(fr);
            while ((linha = asmReader.readLine()) != null) {
                asm.add(linha);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean hasMoreCommands () {
        if (linha < asm.size()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void advance () {
        //linha++;
        input = asm.get(linha++);
	System.out.println(input);
    }

    public String commandType () {
        if (input.charAt(0) == '@') {
            return "A_COMMAND";
        }
        else if (input.charAt(0) == '(') {
            return "L_COMMAND";
        }
        else {
            return "C_COMMAND";
        }

    }

    public String symbol () {
        String symbol;
        if (this.commandType() == "A_COMMAND") {
            symbol =  input.substring(1,input.length()-1);
            if (symbol.matches(".*\\d.*")) {
                return symbol;
            }
            else {
                //if (SymbolTable.contains(symbol)) {
                //    return SymbolTable.GetAddress(symbol);
                //}
                //else {
                    return "Command not A_COMMAND";
                //}
            }
        }
        else {
            symbol = input.substring(1,input.length()-2);
            //SymbolTable.addEntry(symbol, address);
            //address++;
        }
	return("NULL");
    }

    public String dest () {
        return input;
    }

    public String comp () {
        return input;
    }

    public String jump () {
        return input;
    }
}
