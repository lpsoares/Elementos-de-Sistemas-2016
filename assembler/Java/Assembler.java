// Curso Elementos de Sistemas
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;


public class Assembler {

    public static void main(String[] args) {
        System.out.println("Assembler");
     	System.out.println(args[0]);

	try (Writer writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream(args[1]), "utf-8"))) {
    	writer.write("0101010100101001010");
	} catch (IOException ex) {
        	System.out.println("problema com escrita do arquivo");
	}

    }

}
