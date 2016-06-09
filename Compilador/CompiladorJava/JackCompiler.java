import java.util.Scanner;
import java.io.File;
import java.io.IOException;


public class JackCompiler {

	public static void main(String[] args) {
		VMWriter vmWriter;
		CompilationEngine compEngine;
		JackTokenizer jackTok;
		
		Scanner arquivo;
		File arquivoOut;
		
		
		try {
			arquivo = new Scanner(new File(args[0]));
		}
		catch ( IOException e) {
			System.out.println("Nao foi possivel abrir o arquivo selecionado.");
			e.printStackTrace();
			System.exit(0);
		}
		
		arquivoOut = new File(arquivo.toString().split("\\.")[0].concat(".vm"));
		
		jackTok = new JackTokenizer(arquivo);
		vmWriter = new VMWriter(arquivoOut);
		compEngine = new CompilationEngine(jackTok, vmWriter, arquivoOut);			
			
		}
		



}
