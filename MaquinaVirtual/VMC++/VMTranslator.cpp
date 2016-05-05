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
        string commandType(string);

    private:
        int lineCounter;
        string line;
        string command;
        void clearSpacesAndComments(string, int);
        void arg1(string line);


};

Parser::Parser() {
    // The Constructor itself
    vector<string> inputFile;
    string fileName;
    this -> lineCounter = 0;

    cout << "Enter .vm file name here (with it's extension) or a directory's name" << endl;
    getline(cin, fileName);
    ifstream inFile(fileName);

    while(getline(inFile, this -> line)) {
        // First, we gotta clear the line from any space or comment
        this -> clearSpacesAndComments(line, lineCounter);
        // Then, if there is still something left in it, we shall append
        // it to the end of our list
        if(!line.empty()) {
            inputFile.push_back(this -> line);
            cout << inputFile.back() << endl;
            this -> lineCounter++;
        }
    }
}

void Parser::clearSpacesAndComments(string line, int lineCounter) {
    // Clears the line of any possible space or comment on it (if existent)
    this -> line = line;
    this -> lineCounter = lineCounter;
    int i = 0;
    while(i <= this -> line.size()) {
        if(isspace(this -> line[i])) {
            this -> line.erase(i, 1);
        } else if((this -> line[i] == '/') && (this -> line[i+1] == '/')) {
            this -> line.erase(i);
            break;
        } else {
            i++;
        }
    }
}

void Parser::arg1(string line) {
    if(this -> command == "C_ARITHMETIC") {
        this -> command = line;
    } else if(this -> command == "C_PUSH") {
        this -> command = "push";
    }

    this -> line = line;
}

int main() {
    Parser* parser= new Parser();
    cout << "Done!" << endl;
    return 0;
}
