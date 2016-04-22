#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <unordered_map>
#include <tuple>
#include <string.h>

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


tuple<string, unordered_map<string, int>> aTypeInstruction(string line, unordered_map<string, int> map, int i) {
  // decodes A type instructions
  int n;
  // Erases the '@'
  line.erase(0, 1);

  // If the instruction was already a number it converts it
  // to an integer, otherwise, it searches for it in the instructions
  // hash map
  try {
    n = stoi(line);
  } catch(exception & invalid_argument) {
    // First, we need to check it this is already a key at our map
    if (map.find(line) == map.end()) {
      // If soo, we need to give this key the value of the line it
      // is at
      map[line] = i;
      n = i;
    } else {
      // If it's already in the map, we only need to give back
      // its value
      n = map[line];
    }
  }

  string result = toBinary(n);
  while(result.size() < 16) {
    result.insert(0, "0");
  }
  return make_tuple(result, map);
}

string clearSpacesAndComments(string line) {
  // This function helps us clear out the given code
  // into information that can be processed by our assembler
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
    cout << "ERROR: Comp instruction not found" << endl;
  }
  // after doing this I really feel like a switch statement
  // would have been a cleaner option...

}

string jump(string expression) {
  if(expression == "JGT") {
    return "001";
  } else if(expression == "JEQ") {
    return "010";
  } else if(expression == "JGE") {
    return "011";
  } else if(expression == "JLT") {
    return "100";
  } else if(expression == "JNE") {
    return "101";
  } else if(expression == "JLE") {
    return "110";
  } else if(expression == "JMP") {
    return "111";
  } else {
    // The best thing would be to throw an exception here
    cout << "ERROR: Jump instruction not found" << endl;
  }
}

string cTypeInstruction(string line) {
  string instruction = "1";
  string expression;
  string bs;
  string c;
  string d;
  string j;
  int i = 0;

  // checks for the destination of the data
  while(i < line.size()) {
    if(line[i] != '=' && line[i] != ';') {
      expression += line[i];
      i++;
    } else {
      if(expression.empty()) {
        // The best thing would be to throw an exception here
        cout << "ERROR: Line starts with = or ; without a reference" << endl;
      }
      break;
    }
  }
  i++; // This will jump the '=' or ';' sign
  d = dest(expression);
  // No jump expressions
  if(line[i - 1] == '=') {
    // Checks if bitshifter is going to be activated or not
    // This next line concatenates two chars in one string
    string bitShift = {line[i], line[i + 1]};
    bs = shift(bitShift);

    // if a shift was done, this jumps to after it
    if(bitShift == "<<" || bitShift == ">>") {
      i += 2;
    }

    // Now, we are going to get the comp
    line.erase(0, i);
    c = comp(line);

    // In this case, there is never going to be a jump operator
    j = "000";

  // Dealing with jump instructions
} else if(line[i - 1] == ';') {
    c = comp(expression);

    // erases everything in the line from the ';' onwards
    line.erase(0, i);
    j = jump(line);

    // In this case, there is never going to be a dest operator
    d = "000";

    // Same for the bitshift instruction
    bs = "00";
  }

  // generating the final instruction
  instruction.append(bs);
  instruction.append(c);
  instruction.append(d);
  instruction.append(j);

  return instruction;

}

// reads the input file and returns a vector of the lines (as strings)
tuple<vector<string>, string> parser() {
  vector<string> inputFile;
  string fileName;
  string line;

  cout << "Enter .asm file name here (with it's extension)" << endl;
  getline(cin, fileName);
  ifstream inFile(fileName);

  while(getline(inFile, line)) {
    line = clearSpacesAndComments(line);
    if(!line.empty()) {
      inputFile.push_back(line);
    }
  }
  return make_tuple(inputFile, fileName);
}

void code(vector<string> file, unordered_map<string, int> map, string fileName) {
  fileName.erase(fileName.find('.'), fileName.size());
  fileName.append(".hack");
  ofstream writer(fileName);
  string result;
  // string & line : file
  for(int i = 0; i < file.size(); i++) {
    if(file[i][0] == '@') {
      tie(result, map) = aTypeInstruction(file[i], map, i);
    } else {
      result = cTypeInstruction(file[i]);
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
  vector<string> file;
  string fileName;
  tie(file, fileName) = parser();

  unordered_map<string, int> map = basicSimbolTable();

  code(file, map, fileName);

  cout << "Done!" << endl;

  return 0;
}
