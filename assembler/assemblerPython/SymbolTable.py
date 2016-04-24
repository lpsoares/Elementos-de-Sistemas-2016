symbol_table = {
	"R0":0,
	"R0":1,
	"R0":2,
	"R0":3,
	"R0":4,
	"R0":5,
	"R0":6,
	"R0":7,
	"R0":8,
	"R0":9,
	"R0":10,
	"R0":11,
	"R0":12,
	"R0":13,
	"R0":14,
	"R0":15,
	"SP":0,
	"LCL":1,
	"ARG":2,
	"THIS":3,
	"THAT":4,
	"SCREEN":16384,
	"KBD":24576
}

def addEntry(symbol, address):
	symbol_table[symbol] = address

def contains(symbol):
	return symbol_table != None

def getAddress(symbol):
	return symbol_table[symbol]