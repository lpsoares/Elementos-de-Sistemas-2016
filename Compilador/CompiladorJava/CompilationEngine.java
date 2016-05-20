import java.io.*;

public class CompilationEngine{

	//Rotina de teste
	public static void main(){

		FileInputStream in=null; 
		FileOutputStream out=null;

		try {
			in = new FileInputStream("input.Jack");
			out = new FileOutputStream("output.vm");
			
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

	}

	void CompileClass(){

	}
	void CompileSubroutine(){

	}

}
//Faça arquivo com as assinaturas