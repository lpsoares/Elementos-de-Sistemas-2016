#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <unordered_map>

using namespace std;
// This was made with g++ compiler and c++11 in mind

string aTypeInstruction(string line, unordered_map<string, int> map) {
  int startOfInstruction = line.find("@", 0);
  int n;
  line.erase(0, startOfInstruction + 1);

  try {
    n = stoi(line);
  } catch(exception & invalid_argument) {
    int index = line.find(" ", 0);
    if(index == -1) {
      n = map[line];
    }
  }
  return line;
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
    inputFile.push_back(line);
  }
  return inputFile;
}

void code(vector<string> file, unordered_map<string, int> map) {
  ofstream writer("code.hack");
  for(string & line : file) {
    int i = 0;
    do {
      switch(line[i]) {
        case ' ':
          break;
        case '@':
          aTypeInstruction(line, map);
      }
      i++;
    } while(i < line.size());
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

  cout << "Done!" << endl;
  return 0;
}
