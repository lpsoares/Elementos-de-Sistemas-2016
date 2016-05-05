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
        void clearSpacesAndComments();
        void arg1();
        void arg2();

};

Parser::Parser() {
    // The Constructor itself
    vector<string> inputFile;
    string fileName, dirName;
    string entry;

    cout << "Are you entering a file or a directory name? [F/d]" << endl;
    getline(cin, entry);

    if(entry != "D" || entry != "d") {
        this -> lineCounter = 0;
        cout << "Enter .vm file name here (with it's extension):" << endl;
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
                this -> commandType();
                // TODO: Do something with the information we just gathered
                this -> lineCounter++;
            }
        }
    } else {
        cout << "Enter the directory name here:" << endl;
        getline(cin, dirName);
    }
}

void Parser::clearSpacesAndComments() {
    // Clears the line of any possible space or comment on it (if existent)
    int i = 0;
    while(i <= this -> line.size()) {
        // Checks for empty spaces
        if(isspace(this -> line[i])) {
            this -> line.erase(i, 1);
        // Checks for comments
        } else if((this -> line[i] == '/') && (this -> line[i+1] == '/')) {
            this -> line.erase(i);
            break;
        } else {
            i++;
        }
    }
}

void Parser::arg1() {

}

void Parser::arg2() {

}

string Parser::commandType() {
    // Evaluates the line string (already cleaned) to check for which kind of
    // of operator we have
    string line = this -> line;
    if(line == "add" || line == "sub" || line == "neg" || line == "eq" || line == "gt" || line == "lt" || line == "and" || line == "or" || line == "not") {
        return "C_ARITHMETIC";
    } else if(line.find("pop") != string::npos) {
        return "C_POP";
    } else if(line.find("push") != string::npos) {
        return "C_PUSH";
    } else if(line.find("label") != string::npos) {
        return "C_LABEL";
    } else if(line.find("funcion") != string::npos) {
        return "C_FUNCTION";
    } else if(line.find("if-goto") != string::npos) {
        return "C_IF";
    } else if(line.find("goto") != string::npos) {
        return "C_GOTO";
    } else if(line.find("call") != string::npos) {
        return "C_CALL";
    } else if(line.find("return") != string::npos) {
        return "C_RETURN";
    } else {
        cout << "ERROR: Command type not found" << endl;
    }
}

int main() {
    Parser* parser= new Parser();
    cout << "Done!" << endl;
    return 0;
}
