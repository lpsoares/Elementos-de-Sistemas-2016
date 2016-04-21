#include <iostream>
#include <vector>
#include <string>
#include <fstream>

using namespace std;
// This was made with g++ compiler and c++11 in mind

vector<string> parser() {
  vector<string> inputFile;
  string fileName;
  string line;

  cout << "Digite o nome do arquivo de entrada" << endl;
  getline(cin, fileName);
  ifstream inFile(fileName);

  while (getline(inFile, line)) {
    inputFile.push_back(line);
  }

  return inputFile;
}

int main() {
  vector<string> fout = parser();
  for(string & line : fout) {
    cout << line << endl;
  }
  return 0;
}
