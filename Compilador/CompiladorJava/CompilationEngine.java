import java.io.*;

public class CompilationEngine{

		FileInputStream in=null; 
		FileOutputStream out=null;
		PrintStream printStream;

	//Rotina de teste
	public static void main(String[] args){

		FileInputStream in=null; 
		FileOutputStream out=null;

		try {
			//in = new FileInputStream("input.Jack");
			out = new FileOutputStream("output.xml");
			
			CompilationEngine a = new CompilationEngine(in, out);

		}catch(Exception e){
         // if any error occurs
			e.printStackTrace();
		}finally {
			try{
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			}catch(Exception e){
         // if any error occurs
				e.printStackTrace();
			}
		}

		

	}

	CompilationEngine(FileInputStream in, FileOutputStream out){
		//this.in = in;

		printStream = new PrintStream(this.out = out);

		CompileClass();

		printStream.close();
		
	}

	void CompileClass(){

		printStream.print("<class>");

	}

	void CompileSubroutine(){

	}

}
//Faça arquivo com as assinaturas