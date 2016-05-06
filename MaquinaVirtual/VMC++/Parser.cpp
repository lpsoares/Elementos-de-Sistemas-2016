#include <iostream>
#include <vector>
#include <fstream>
#include <unordered_map>
#include <string.h>
#include "Parser.hpp"

using namespace std;


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
        this -> infile.open(fileName);
        do {
            getline(this -> infile, this -> line);
            clearSpacesAndComments();
        } while(this -> line.empty());
        cout << this -> line << endl;

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
