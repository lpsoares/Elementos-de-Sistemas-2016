def parser(file):

	openfile = open(file)
	line = openfile.readlines()

	def command_type(line):
		chars = line.split()
		for i in chars:
			while chars[i]:
				if chars[i] and chars[i+1] == "/":
					break
				elif chars[0].isnumeric():
					break
				elif chars[0] == "@":
					if not line[1].isnumeric():
						break
					else:
						continue
				elif chars[i] == " ":
					pass
				else:
					if chars[0] == "@":
						return "A_COMMAND"
					elif chars[0] == "d" and chars[1] == "e" and chars[2] == "s" and chars[3] == "t":
						return "C_COMMAND"
					elif chars[0] == "j" and chars[1] == "u" and chars[3] == "m" and chars[4] == "p":
						return "C_COMMAND"
					elif chars[0] == "(":
						return "L_COMMAND"
					else:
						return False


	def l_number(line):
		lookup = symbol
		for num, lines in enumerate(openfile, 1):
			if lookup in lines:
				return int(num)

	def symbol(line):
			chars = line.split()
			for i in chars:
				while chars[i]:
					if chars[i] and chars[i+1] == "/":
						break
					elif chars[0].isnumeric():
						break
					elif chars[0] == "@":
						if not line[1].isnumeric():
							break
						else:
							continue
					elif chars[i] == " ":
						pass
					else:
						if chars[0] == "(":
							symbollist = []
							for i in chars:
								while chars[i] != ")":
									symbollist.append(chars[i])
								symbollist.remove("(")
								Symbol = "".join(str(i) for i in symbollist)
								return Symbol
						else:
							return False

	def bs(line):
		pass

	def dest(line):
		chars = line.split()
		for i in chars:
			while chars[i]:
				if chars[i] and chars[i+1] == "/":
					break
				elif chars[0].isnumeric():
					break
				elif chars[0] == "@":
					if not line[1].isnumeric():
						break
					else:
						continue
				elif chars[i] == " ":
					pass
				else:	
					if command_type == "C_COMMAND":
						destlist = []
						for i in chars(1,len(chars)):
							while chars[i].isalpha():
								destlist.append(chars[i])
							Dest = "".join(str(i) for i in destlist)
							return Dest
					else:
						return False



	def comp(line):
		chars = line.split()
		for i in chars:
			while chars[i]:
				if chars[i] and chars[i+1] == "/":
					break
				elif chars[0].isnumeric():
					break
				elif chars[0] == "@":
					if not line[1].isnumeric():
						break
					else:
						continue
				elif chars[i] == " ":
					pass
				else:	
					if command_type == "C_COMMAND":
						complist = []
						for i in chars(1,len(chars)):
							while chars[i].isalpha():
								complist.append(chars[i])
							Cest = "".join(str(i) for i in complist)
							return Cest
					else:
						return False

	def jump(line):
		chars = line.split()
		for i in chars:
			while chars[i]:
				if chars[i] and chars[i+1] == "/":
					break
				elif chars[0].isnumeric():
					break
				elif chars[0] == "@":
					if not line[1].isnumeric():
						break
					else:
						continue
				elif chars[i] == " ":
					pass
				else:	
					if command_type == "C_COMMAND":
						jumplist = []
						for i in chars(1,len(chars)):
							while chars[i].isalpha():
								jumplist.append(chars[i])
							Jump = "".join(str(i) for i in jumplist)
							return Jump
					else:
						return False