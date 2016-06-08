import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class VMWriter {
	
	private PrintWriter printWriter;//OBJETO USADO PARA MANDAR COMANDOS PARA VM

	public VMWriter(File file) throws FileNotFoundException{
		printWriter = new PrintWriter(file);//INICIAR OBJETO PRINTWRITER A PARTIR DO ARGUMENTO USADO
	}
	
	//METODO PARA DAR "PUSH" EM UM SEGMENTO EM UM ESPECIFICO INDEX
	public void writePush(String segment, int index){
		String segmentFinal = new String();
		if (segment == "CONST"){
			segmentFinal = "constant";
		}
		if (segment == "LOCAL"){
			segmentFinal = "local";
		}
		if (segment == "STATIC"){
			segmentFinal = "static";
		}
		if (segment == "THIS"){
			segmentFinal = "this";
		}
		if (segment == "THAT"){
			segmentFinal = "that";
		}
		if (segment == "POINTER"){
			segmentFinal = "pointer";
		}
		if (segment == "TEMP"){
			segmentFinal = "temp";
		}
		
		if(!segmentFinal.isEmpty()){
			printWriter.write("push " + segmentFinal + " " + index + "\n");
		}
	}
	
	//METODO PARA DAR "POP" EM UM SEGMENTO EM UM ESPECIFICO INDEX
	public void writePop(String segment, int index){
		String segmentFinal = new String();
		if (segment == "CONST"){
			segmentFinal = "constant";
		}
		if (segment == "LOCAL"){
			segmentFinal = "local";
		}
		if (segment == "STATIC"){
			segmentFinal = "static";
		}
		if (segment == "THIS"){
			segmentFinal = "this";
		}
		if (segment == "THAT"){
			segmentFinal = "that";
		}
		if (segment == "POINTER"){
			segmentFinal = "pointer";
		}
		if (segment == "TEMP"){
			segmentFinal = "temp";
		}
		
		if(!segmentFinal.isEmpty()){
			printWriter.write("pop " + segmentFinal + " " + index + "\n");
		}
	}
	
	//METODO PARA PUBLICAR UM COMANDO ARITMETICO
	public void writeArithmetic(String command){
		if(command.equals("ADD") || command.equals("SUB") || command.equals("NEG") || command.equals("EQ") ||
				command.equals("GT")|| command.equals("LT") || command.equals("AND") || command.equals("OR") || 
				command.equals("NOT")){//CHECAR SE O COMANDO ESTA VALIDO
			command = command.toLowerCase();
			printWriter.write(command + "\n");
		}
	}
	
	//CONJUNTO DE METODOS COM O MESMO PADRAO -- LABELS DIFERENTES
	public void writeLabel(String label){
		printWriter.write("label "+label+"\n");
	}
	
	public void writeGoto(String label){
		printWriter.write("goto "+label+"\n");
	}
	
	public void writeIf(String label){
		printWriter.write("if-goto "+label+"\n");
	}
	
	//FIM DOS METODOS COM LABEL
	
	public void writeCall(String name, int nArgs){
		printWriter.write("call "+name+ " " + nArgs + "\n");
	}
	
	public void writeFunction(String name, int nLocals){
		printWriter.write("call "+name+ " " + nLocals + "\n");
	}
	
	//METODO DE RETORNO
	public void writeReturn(){
		printWriter.write("return \n");
	}
	
	public void close(){
		printWriter.close();
	}
}
