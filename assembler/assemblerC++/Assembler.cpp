#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <unordered_map>

using namespace std;


// This was made with g++ compiler and c++11 in mind

string toBinary(int n) {
  // converts decimal integers into its binary equivalent as a string
  string result;
  while(n != 0) {
    result = (n % 2 == 0 ? "0" : "1") + result;
    n /= 2;
  }
  return result;
}


string aTypeInstruction(string line, unordered_map<string, int> map) {
  // decodes a type instructions
  int startOfInstruction = line.find("@", 0);
  int n;
  line.erase(0, startOfInstruction + 1);

  try {
    n = stoi(line);
  } catch(exception & invalid_argument) {
    n = map[line];
  }

  string result = toBinary(n);
  while(result.size() < 16) {
    result.insert(0, "0");
  }
  return result;
}

string clearSpacesAndComments(string line) {
  int i = 0;
  while(i < line.size()) {
    if(line[i] == ' ') {
      line.erase(i, 1);
    } else if(line[i] == '/' && line[i + 1] == '/') {
      line.erase(i);
      break;
    } else {
      i++;
    }
  }
  return line;
}

string shift(string expression) {
  if(expression == "<<") {
    // bitshift left
    return "11";
  } else if(expression == ">>") {
    // bitshit right
    return "10";
  } else {
    // no shift
    return "00";
  }
}

string dest(string expression) {
  if(expression == "M") {
    // Memory[A]
    return "001";
  } else if(expression == "D") {
    // D register
    return "010";
  } else if(expression == "A") {
    // A register
    return "100";
  } else if(expression == "MD" || expression == "DM") {
    // Memory[A] and D register
    return "011";
  } else if(expression == "AD" || expression == "DA") {
    // A register and D register
    return "110";
  } else if(expression == "AM" || expression == "MA") {
    // A register and Memory[A]
    return "101";
  } else if(expression == "") {
    // Value is not stored
    return "000";
  } else {
    // Value is stored everywhere!
    return "111";
  }
}

string comp(string expression) {
  // What to compute. This is pretty self explanatory
  // soo there won't be many comments
  if(expression == "0") {
    return "0101010";
  } else if(expression == "1") {
    return "0111111";
  } else if(expression == "-1") {
    return "0111010";
  } else if(expression == "D") {
    return "0001100";
  } else if(expression == "A") {
    return "0110000";
  } else if(expression == "!D") {
    return "0001101";
  } else if(expression == "!A") {
    return "0110001";
  } else if(expression == "-D") {
    return "0001111";
  } else if(expression == "-A") {
    return "0110011";
  } else if(expression == "D+1") {
    return "0011111";
  } else if(expression == "A+1") {
    return "0110111";
  } else if(expression == "D-1") {
    return "0001110";
  } else if(expression == "A-1") {
    return "0110010";
  } else if(expression == "D+A") {
    return "0000010";
  } else if(expression == "D-A") {
    return "0010011";
  } else if(expression == "A-D") {
    return "0000111";
  } else if(expression == "D&A") {
    return "0000000";
  } else if(expression == "D|A") {
    return "0010101";
  } else if(expression == "M") {
    return "1110000";
  } else if(expression == "!M") {
    return "1110001";
  } else if(expression == "-M") {
    return "1110011";
  } else if(expression == "M+1") {
    return "1110010";
  } else if(expression == "D+M") {
    return "1000010";
  } else if(expression == "D-M") {
    return "1010011";
  } else if(expression == "M-D") {
    return "1000111";
  } else if(expression == "D&M") {
    return "1000000";
  } else if(expression == "D|M") {
    return "1010101";
  } else {
    // The best thing would be to throw an exception here
    cout << "Comp instruction not found" << endl;
  }
  // after doing this I really feel like a switch statement
  // would have been a cleaner option...

}

string jump(string expression) {

}

string cTypeInstruction(string line) {
  string instruction = "1";
  string expression = "";
  int i = 0;
  string bs;
  string c;
  string d;
  string j;

  // checks for the destination of the data
  while(i < line.size()) {
    if(line[i] != '=' || line[i] != ';') {
      expression += line[i];
      i++;
    } else {
      if(expression.empty()) {
        // The best thing would be to throw an exception here
        cout << "Error, the line starts with = without a reference" << endl;
      }
      break;
    }
  }

  i++; // This will jump the '=' or ';' sign
  d = dest(expression);

  // No jump expressions
  if(line[i] == '=') {

    // Checks if bitshifter is going to be activated or not
    // This next line concatenates two chars in one string
    string bitShift = {line[i], line[i + 1]};
    bs = shift(bitShift);

    // if a shift was done, this jumps to after it
    if(bitShift == "<<" || bitShift == ">>") {
      i += 2;
    }

    // Now, we are going to get the comp
    line.erase(i);
    c = comp(line);

    // In this case, there is never going to be a jump operator
    j = "000";

  } else if(line[i] == ';') {

  }

  // generating the final instruction
  instruction.append(bs);
  instruction.append(c);
  instruction.append(d);
  instruction.append(j);

}

// reads the input file and returns a vector of the lines (as strings)
vector<string> parser() {
  vector<string> inputFile;
  string fileName;
  string line;

  cout << "Digite o nome do arquivo de entrada" << endl;
  getline(cin, fileName);
  ifstream inFile(fileName);

  while(getline(inFile, line)) {
    line = clearSpacesAndComments(line);
    if(!line.empty()) {
      inputFile.push_back(line);
    }
  }
  return inputFile;
}

void code(vector<string> file, unordered_map<string, int> map) {
  ofstream writer("code.hack");
  string result;

  for(string & line : file) {
    if(line[0] == '@') {
      result = aTypeInstruction(line, map);
    } else {
      result = cTypeInstruction(line);
    }
    writer << result << endl;
  }
}


// Creates the basic simbols table
unordered_map<string, int> basicSimbolTable() {
  unordered_map<string, int> map;

  map["R0"] = 0;
  map["R1"] = 1;
  map["R2"] = 2;
  map["R3"] = 3;
  map["R4"] = 4;
  map["R5"] = 5;
  map["R6"] = 6;
  map["R7"] = 7;
  map["R8"] = 8;
  map["R9"] = 9;
  map["R10"] = 10;
  map["R11"] = 11;
  map["R12"] = 12;
  map["R13"] = 13;
  map["R14"] = 14;
  map["R15"] = 15;
  map["SCREEN"] = 16384;
  map["KBD"] = 24576;
  map["SP"] = 0;
  map["LCL"] = 1;
  map["ARG"] = 2;
  map["THIS"] = 3;
  map["THAT"] = 4;

  return map;
}



int main() {
  vector<string> file = parser();

  for(string & line : file) {
    cout << line << endl;
  }

  unordered_map<string, int> map = basicSimbolTable();
  cout << map["R5"] << endl;

  code(file, map);

  cout << "Done!" << endl;
  return 0;
}
