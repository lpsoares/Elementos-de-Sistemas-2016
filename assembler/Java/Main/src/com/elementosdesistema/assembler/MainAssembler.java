package com.elementosdesistema.assembler;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
//Main: Inicia leitura e escrita dos arquivos e controla aplicativo.




public class MainAssembler {

	public static void main(String[] args) throws IOException {
		Parser parser = new Parser();
	
        Scanner data = null;
        ArrayList<String> count;

        try {
                data = new Scanner(new File("parser.asm"));
        }
        catch ( IOException e) {
            System.out.println("Não é possível abrir o arquivo!");
            e.printStackTrace();
            System.exit(0);
        }
        try {
            data = new Scanner(new File("arquivo.hack"));
    }
    catch ( IOException e) {
        System.out.println("Não é possível abrir o arquivo!");
        e.printStackTrace();
        System.exit(0);
    }

        count = new ArrayList<String>();
        while(data.hasNextLine()) {
            count.add(data.nextLine());
        }

        
        //List<String> lines = Arrays.asList("The first line", "The second line");
        //Path file = Paths.get("parser.asm");
        //Files.write(file, lines, Charset.forName("UTF-8"));
        //Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
        for (String i: count){
        	List<String> lines=Files.readAllLines(Paths.get("parser.asm"), Charset.forName("UTF-8"));
        	for(String line:lines){
               Files.write(data, lines, Charset.forName("UTF-8"));
               //Files.write(file, data, StandardOpenOption.APPEND);
        	  System.out.println(line);
        	}
        	
        	
        }
    }

    
}