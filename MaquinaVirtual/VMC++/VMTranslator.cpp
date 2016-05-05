#include <iostream>
#include <vector>
#include <fstream>
#include <unordered_map>
#include <string.h>

using namespace std;

class Parser {
    // Constructor, opens the file and gets it ready to be
    // parsed
    Parser();

}

Parser::Parser(unordered_map<string, int> map) {
    // The Constructor itself
    vector<string> inputFile;
    string fileName;
    string line;
    int lineCounter = 0;

    cout << "Enter .vm file name here (with it's extension)" << endl;
    getline(cin, fileName);
    ifstream inFile(fileName);

    while(getline(inFile, line)) {
        line = crearSpacesAndComments(line, map, lineCounter);
    }
}

int main() {
    cout << "Done!" << endl;
    return 0;
}
