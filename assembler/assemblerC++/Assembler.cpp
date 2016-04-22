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

string comp(string expression) {
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

string cTypeInstruction(string line) {
  string instruction = "1";
  string expression = "";
  int i = 0;

  while(i < line.size()) {
    if(line[i] != '=') {
      expression += line[i];
      i++;
    } else {
      if(expression.empty()) {
        cout << "Error, the line starts with = without a refference" << endl;
      }
      break;
    }
  }

  string d = comp(expression);
  expression = "";

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
