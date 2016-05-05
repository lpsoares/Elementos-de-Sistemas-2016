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
        string commandType();

    private:
        int lineCounter;
        string line;
        string command;
        string finalCommand;
        void clearSpacesAndComments();
        void arg1();
        void arg2();

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
        this -> clearSpacesAndComments();
        // Then, if there is still something left in it, we shall append
        // it to the end of our list
        if(!line.empty()) {
            cout << this -> line << endl;
            inputFile.push_back(this -> line);
            this -> lineCounter++;
        }
    }
}

void Parser::clearSpacesAndComments() {
    // Clears the line of any possible space or comment on it (if existent)
    int i = 0;
    bool begin = true;
    while(i <= this -> line.size()) {
        if(isspace(this -> line[i]) || begin) {
            this -> line.erase(i, 1);
        } else if((this -> line[i] == '/') && (this -> line[i+1] == '/')) {
            this -> line.erase(i);
            break;
        } else {
            i++;
        }
    }
}

void Parser::arg1() {
    if(this -> command == "C_ARITHMETIC") {
        this -> finalCommand = this -> line;
    } else if(this -> command == "C_PUSH") {
        this -> line = line.erase(0, 4);
        this -> arg2();
    } else if(this -> command == "C_POP") {
        this -> line = line.erase(0, 3);
        this -> arg2();
    } else if(this -> command == "C_LABEL") {
    }

    this -> line = line;
}

void Parser::arg2() {

}

string commandType() {
}

int main() {
    Parser* parser= new Parser();
    cout << "Done!" << endl;
    return 0;
}
