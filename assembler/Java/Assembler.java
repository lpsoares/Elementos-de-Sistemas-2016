// Curso Elementos de Sistemas

import java.io.Writer;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;


public class Assembler {
	//Main: Inicia leitura e escrita dos arquivos e controla aplicativo.
    public static void main(String[] args) {
        System.out.println("Assembler");

    }

}

     	//System.out.println(args[0]);

	try (Writer writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream(args[1]), "utf-8"))) {
  

//abrir arquivo escrita
    Parser parser = new Parser(args[0]);
   
    while(parser.hasMoreCommands()){
    	parser.advance();
    
    	
    	Code code = new Code(Code.symbol);
    	
    	ArrayList<String> parametros;
    	parametros = new ArrayList<String>();
 
    	
    
		if(parser.commandType() == "A_COMMAND"){
			parser.symbol();
			writer.write(Code.transformToValue());
			parametros.add("lixo", symbol)
		}
		else if((parser.commandType() == "L_COMMAND")){
			parser.symbol();
			writer.write(Code.transformToValue());
			parametros.add("lixo", symbol,transformComp)
		}	
		else if((parser.commandType() == "C_COMMAND")){
			parser.symbol();
			System.out.println("C_COMMAND");
			writer.write(Code.transformToValue());
			parametros.add("lixo", symbol,transformComp,TransformDest)
	}	
    }
	} catch (IOException ex) {
    	System.out.println("problema com escrita do arquivo");
	}
    }
}


//}
//    
//    
//	Writer writer = null;
//
//	try {
//	    writer = new BufferedWriter(new OutputStreamWriter(
//	          new FileOutputStream("filename.asm"), "utf-8"));
//	    writer.write(commandType);
//	} catch (IOException ex) {
//	  System.out.println("Error")
//	} finally {
//	   try {writer.close();} catch (Exception ex) {/*ignore*/}
//	}
//	
//	
//	
//    Scanner data = null;
//    ArrayList<String> count;
//
//    try {
//            data = new Scanner(new File("parser.asm"));
//    }
//    catch ( IOException e) {
//        System.out.println("Não é possível abrir o arquivo!");
//        e.printStackTrace();
//        System.exit(0);
//    }
//    try {
//        data = new Scanner(new File("arquivo.hack"));
//}
//catch ( IOException e) {
//    System.out.println("Não é possível abrir o arquivo!");
//    e.printStackTrace();
//    System.exit(0);
//}
//
//    count = new ArrayList<String>();
//    while(data.hasNextLine()) {
//        count.add(data.nextLine());
//    }
//
//
//}
//
//    
//    //List<String> lines = Arrays.asList("The first line", "The second line");
//    //Path file = Paths.get("parser.asm");
//    //Files.write(file, lines, Charset.forName("UTF-8"));
//    //Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
//    for (String i: count){
//    	List<String> lines=Files.readAllLines(Paths.get("parser.asm"), Charset.forName("UTF-8"));
//    	for(String line:lines){
//           Files.write(data, lines, Charset.forName("UTF-8"));
//           //Files.write(file, data, StandardOpenOption.APPEND);
//    	  System.out.println(line);
//    	}
//    	
//    	
//    }
//}


