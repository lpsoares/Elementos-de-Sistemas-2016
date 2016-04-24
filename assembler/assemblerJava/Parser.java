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
    private int[] destInput = {000,001,010,011,100,101,110,111};
    private String[] destOutput = {null,"M","D","MD","A","AM","AD","AMD"};
    private int[] compInput = {101010,111111,111010,001100,110000,001101,110001,001111,
                               110011,011111,110111,001110,110010,00010,010011,000111,000000,010101};
    private String[] compOutput = {"0","1","-1","D","A","!D","!A","-D","-A","D+1","A+1","D-1","A-1",
                                   "D+A","D-A","A-D","D%A","D|A"};

    private int[] jumpInput = {000,001,010,011,100,101,110,111};
    private String[] jumpOutput = {null,"JGT","JEQ","JGE","JLT","JNE","JLE","JMP"};

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
        linha++;
        input = asm.get(linha);
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
                if (SymbolTable.contains(symbol)) {
                    return SymbolTable.GetAddress(symbol);
                }
                else {
                    return "Command not A_COMMAND";
                }
            }
        }
        else {
            symbol = input.substring(1,input.length()-2);
            SymbolTable.addEntry(symbol, address);
            address++;
        }
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
