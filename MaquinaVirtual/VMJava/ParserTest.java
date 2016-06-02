import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class ParserTest {
	private int args2;
	String currentCommand;
	String args1;
	@Test
	public void testPushAdd() throws IOException {
		Parser parser = new Parser("teste1.vm");
		
		currentCommand = parser.commandType();
		if(!(currentCommand == null)){
			args1 = parser.arg1();
			args2 = parser.arg2();
		}
		Assert.assertEquals("C_PUSH", currentCommand);
		Assert.assertEquals("constant", args1);
		Assert.assertEquals(4, args2);
		if(parser.hasMoreCommands()){
			parser.advance();
		}
		
		currentCommand = parser.commandType();
		if(!(currentCommand == null)){
			args1 = parser.arg1();
			args2 = parser.arg2();
		}
		Assert.assertEquals("C_PUSH", currentCommand);
		Assert.assertEquals("constant", args1);
		Assert.assertEquals(3, args2);
		
		if(parser.hasMoreCommands()){
			parser.advance();
		}
		
		currentCommand = parser.commandType();
		if(!(currentCommand == null)){
			args1 = parser.arg1();
			args2 = parser.arg2();
		}
		
		Assert.assertEquals("C_ARITHMETIC", currentCommand);
		Assert.assertEquals("add", args1);
		Assert.assertEquals(0, args2);
	}

	@Test
	public void testPopComment() throws IOException {
		Parser parser = new Parser("teste2.vm");
		
		currentCommand = parser.commandType();
		if(!(currentCommand == null)){
			args1 = parser.arg1();
			args2 = parser.arg2();
		}
		
		Assert.assertEquals("C_POP", currentCommand);
		Assert.assertEquals("temp", args1);
		Assert.assertEquals(1, args2);
		if(parser.hasMoreCommands()){
			parser.advance();
		}
		
		currentCommand = parser.commandType();
		if(!(currentCommand == null)){
			args1 = parser.arg1();
			args2 = parser.arg2();
		}
		Assert.assertEquals(null, currentCommand);
		
		if(parser.hasMoreCommands()){
			parser.advance();
		}
		
		currentCommand = parser.commandType();
		if(!(currentCommand == null)){
			args1 = parser.arg1();
			args2 = parser.arg2();
		}
		
		Assert.assertEquals("C_CALL", currentCommand);
		Assert.assertEquals("func", args1);
		Assert.assertEquals(3, args2);
	}

	@Test
	public void testFunctionReturnLabelReturn() throws IOException {
		Parser parser = new Parser("teste3.vm");
		
		currentCommand = parser.commandType();
		if(!(currentCommand == null)){
			args1 = parser.arg1();
			args2 = parser.arg2();
		}
		
		Assert.assertEquals("C_FUNCTION", currentCommand);
		Assert.assertEquals("func", args1);
		Assert.assertEquals(1, args2);
		if(parser.hasMoreCommands()){
			parser.advance();
		}
		
		currentCommand = parser.commandType();
		if(!(currentCommand == null)){
			args1 = parser.arg1();
			args2 = parser.arg2();
		}
		
		Assert.assertEquals("C_LABEL", currentCommand);
		Assert.assertEquals("loop", args1);
		Assert.assertEquals(0, args2);
		
		if(parser.hasMoreCommands()){
			parser.advance();
		}
		
		currentCommand = parser.commandType();
		if(!(currentCommand == null)){
			args1 = parser.arg1();
			args2 = parser.arg2();
		}
		
		Assert.assertEquals("C_RETURN", currentCommand);
		Assert.assertEquals(null, args1);
		Assert.assertEquals(0, args2);
	}
	
	@Test
	public void testGotoIfGoto() throws IOException {
		Parser parser = new Parser("teste4.vm");
		
		currentCommand = parser.commandType();
		if(!(currentCommand == null)){
			args1 = parser.arg1();
			args2 = parser.arg2();
		}
		
		Assert.assertEquals("C_GOTO", currentCommand);
		Assert.assertEquals("loop", args1);
		Assert.assertEquals(0, args2);
		
		if(parser.hasMoreCommands()){
			parser.advance();
		}
		
		currentCommand = parser.commandType();
		if(!(currentCommand == null)){
			args1 = parser.arg1();
			args2 = parser.arg2();
		}
		
		Assert.assertEquals("C_IF", currentCommand);
		Assert.assertEquals("loop", args1);
		Assert.assertEquals(0, args2);
	}
}
