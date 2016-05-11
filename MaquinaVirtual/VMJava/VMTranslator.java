import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VMTranslator {

	private static CodeWriter cw = new CodeWriter(new File("vm_compiled.asm"));
	
	private static File currentDir;
	private static List<String> inputFiles;
	private static int fileCount;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		inputFiles = new ArrayList<>();
		
		if(args.length == 0) {
			System.out.println("No VM files passed as argument. Checking for VM files in current folder...");
			Thread.sleep(2000);

			currentDir = new File(System.getProperty("user.dir"));
			System.out.println(System.getProperty("user.dir"));
			
			for(File vmFile: currentDir.listFiles()) {
				if(vmFile.getName().endsWith(".vm")) {
					inputFiles.add(vmFile.getName());
					System.out.println(vmFile.getName());
					fileCount++;
				}
			}
			System.out.println(fileCount + " files found");
			Thread.sleep(2000);
			compile();
		} else if(!(args.length > 2)) {
			inputFiles.add(args[0]);
			compile();
		} else {
			System.out.println("Too many arguments");
		}
	}
	
	public static void compile() throws IOException {
		for(String file: inputFiles) {
			Parser pr = new Parser(file);
			while(pr.hasMoreCommands()) {
				pr.advance();
				write(pr, cw);
			}
		}
	}
	
	public static void write(Parser pr, CodeWriter cw) {
		String currentCmd = pr.commandType();
		
		switch (currentCmd) {
			case "C_ARITHMETIC":
				cw.writeArithmetic(pr.arg1());
				break;
			case "C_PUSH":
			case "C_POP":
				cw.writePushPop(currentCmd, pr.arg1(), pr.arg2());
				break;
			case "C_LABEL":
				cw.writeLabel(pr.arg1());
				break;
			case "C_GOTO":
				cw.writeGoto(pr.arg1());
				break;
			case "C_IF":
				cw.writeIf(pr.arg1());
				break;
			case "C_FUNCTION":
				cw.writeFunction(pr.arg1(), pr.arg2());
				break;
			case "C_RETURN":
				cw.writeReturn();
				break;
			case "C_CALL":
				cw.writeCall(pr.arg1(), pr.arg2());
				break;
		}
	}
}
