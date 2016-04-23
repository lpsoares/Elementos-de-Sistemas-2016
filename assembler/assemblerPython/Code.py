#Symbol table already defined here, for some values it's easier to use decimal values
symbol_table = {'SCREEN':16384,'KBD':24576,'SP':0,'LCL':1,'ARG':2,'THIS':3,'THAT':4}

#the line we're reading (P in Hack's Software)
l_number = 20

#Just to check whether it's a letter or not
let = 'abcdefghijklmnopqrstuvwxyz'

#starting point for new variabels
com_count = 15

def convert_assembly_to_binary(command_type,l_number,symbol,bs='11',dest='',comp='',jump=''):
	""" This function get as arguments the commands and traslate them into
		binary by using the tables (bs_table, dest_table, comp_table, jump_table)

		arguments:
			command_type: 	A_COMMAND,
							C_COMMAND,
							L_COMMAND
								Gets the type of the current
								command:
								m A_COMMAND for @Xxx where
								Xxx is either a symbol or a
								decimal number
								m C_COMMAND for
								dest=comp;jump
								m L_COMMAND (actually, pseudocommand)
								for (Xxx) where Xxx
								is a symbol.

			l_number:		int
								Gets the adress
								the value of the registers PC

			symbol: 		string
								Gets the symbol or decimal
								Xxx of the current command
								@Xxx or (Xxx). Should be called
								only when commandType() is
								A_COMMAND or L_COMMAND.

			bs:				string
								Gets the dest mnemonic in
								the current C-command (4 possibilities).
								Should be called only
								when commandType() is C_COMMAND.

			dest:			string
								Gets the dest mnemonic in
								the current C-command (8 possibilities).
								Should be called only
								when commandType() is C_COMMAND.

			comp: 			string
								Gets the comp mnemonic in
								the current C-command (28 possibilities).
								Should be called only
								when commandType() is
								C_COMMAND.

			jump:			string
								Gets the jump mnemonic in
								the current C-command (8 possibilities).
								Should be called only
								when commandType() is
								C_COMMAND.

		All this arguments come from the parser, once the lines from command
		are read they are compiled as this arguments and this func is called
		translanting the mnemonic into binary code.

		return:
			binary_command:	string
								Returns a binary code
								that contain all the 16 bits
								indicating the next command to be executed.
		"""

	#A_COMMAND
	######################################################

	if command_type == 'A_COMMAND':
		v = symbol[1::]
		#v = symbol
		#To assemble the ones that belong to the Symbol Table
		if v in symbol_table:
			valor = bin(symbol_table[v])

		#Set new parameters starting from 15
		elif v not in symbol_table and symbol[1] in let:
			add_to_symbol_table(symbol,com_count)
			com_count += 1
			valor = bin(symbol_table[v])
		#Virtual registers
		elif symbol[1] == 'R':
			valor = bin(int(symbol[2::]))

		#Actual Numbers	
		else:
			valor = bin(int(symbol[v]))

		#fill out the last numbers not shown
		valor = valor[2::]
		for i in range(15-len(valor)):
			binary_command += '0'
		binary_command += valor

	#C_COMMAND
	######################################################
	#bs = '11' #uncomment this if there's no bs control
	elif command_type == 'C_COMMAND':
		binary_command = '1'
		binary_command += bs_table[bs] #change to just bs
		binary_command += dest_table[dest]
		binary_command += comp_table[comp]
		binary_command += jump_table[jump]

	#L_COMMAND - (pseudo command)
	######################################################

	elif command_type == 'L_COMMAND':

		add_to_symbol_table(symbol,l_number)

		elif symbol[0] in let.upper():
			comp[symbol[0]]

		if len(binary_command) == 16 :
			print(binary_command)
			print(len(binary_command))

	######################################################

	return binary_command



#Should be defined in the symbol_table module
def add_to_symbol_table(symbol,symbol_number):
	""" This is a helper function used to add a new item
		to the symbol table.

		arguments:
			symbol: 		string
								The tag that has been placed
								inside parentesis.

			symbol_number:	string
								The value of PC in the moment
								the command is been created
								so we know what value to place
								in the tag.

		Obs.:
			Don't forget not to count an extra line once the program is 
			passing throw this line, in case you're using a counter variabel.
	"""
	if symbol[0] == '(':
		symbol_table[str(symbol[1:len(symbol)-1:])] = symbol_number
	elif symbol[0] == '@':
		symbol_table[str(symbol[1::])] = symbol_number
