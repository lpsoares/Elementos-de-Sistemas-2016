#include <iostream>
#include <vector>
#include <fstream>
#include <unordered_map>
#include <string.h>

using namespace std;

class Parser {
    public:
        // Constructor, opens the file and gets it ready to be
        // parsed
        Parser();

    private:
        void clearSpacesAndComments(string &line, int lineCounter);


};

Parser::Parser(void) {
    // The Constructor itself
    vector<string> inputFile;
    string fileName;
    string line;
    int lineCounter = 0;

    cout << "Enter .vm file name here (with it's extension)" << endl;
    getline(cin, fileName);
    ifstream inFile(fileName);

    while(getline(inFile, line)) {
        crearSpacesAndComments(line, lineCounter);
        if(!line.empty()) {
            inputFile.push_back(line);
            cout << line << endl;
            lineCounter++;
        }
    }
}

void Parser::clearSpacesAndComments(string &line, int lineCounter) {
    // Clears the line of any possible space or comment on it (if existent)
    int i = 0;
    while(i <= line.size()) {
        if(isspace(line[i])) {
            line.erase(i, 1);
        } else if(line[i] == '/' && line[i+1] == '/') {
            line.erase(i);
            break;
        } else {
            i++;
        }
    }
}

int main() {
    cout << "Done!" << endl;
    return 0;
}
