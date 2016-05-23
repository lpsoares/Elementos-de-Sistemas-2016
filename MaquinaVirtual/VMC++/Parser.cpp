#include <iostream>
#include <vector>
#include <fstream>
#include <unordered_map>
#include <string.h>
#include <sstream>
#include <boost/algorithm/string.hpp>
#include "Parser.hpp"

using namespace std;


Parser::Parser() {
    // The Constructor itself
    vector<string> inputFile;
    string fileName, dirName;
    string entry;
    this->line = "   ";

    cout << "Are you entering a file or a directory name? [F/d]" << endl;
    getline(cin, entry);

    if(entry != "D" || entry != "d") {
        this->lineCounter = 0;
        cout << "Enter .vm file name here (with it's extension):" << endl;
        getline(cin, fileName);
        this->infile.open(fileName);
    } else {
        cout << "Enter the directory name here:" << endl;
        getline(cin, dirName);
    }
}

Parser::~Parser() {
    // Destructor
    infile.close();
}

void Parser::advance(string &line) {
    // We got to check if we got a real code line
    // or just an empty line / comment but also
    // if we've reached the end of our file

    do {
        getline(this->infile, this->line);
        clearSpacesAndComments();
    } while(this->line.empty() && this->hasMoreCommands());

    cout << "next line" << endl;

    if(!this->line.empty()) {
        line = this->line;
    }
}

bool Parser::hasMoreCommands() {
    return (!this->infile.eof());
}

void Parser::clearSpacesAndComments() {
    // Clears the line of any possible space or comment on it (if existent)
    string buf;
    bool broken = false;

    this->line_split.clear();
    stringstream ss(this->line);

    // First, we got to check if the line is not empty (has content) because
    // if it is we cannot "clear" it
    if(!this->line.empty()) {

        // Equivalent to python's split
        while(ss >> buf) {
            if(buf.find("//") == string::npos && !broken) {
                this->line_split.push_back(buf);
            } else {
                broken = true;
            }
        }

        for(string s : this->line_split) {
            cout << s << endl;
        }
    }
}

string Parser::arg1() {
    if(this->command_type == "C_ARITHMETIC") {
        return this->line;
    } else if(this->command_type == "C_POP") {

    } else if(this->command_type == "C_PUSH") {

    } else if(this->command_type == "C_LABEL") {

    } else if(this->command_type == "C_FUNCTION") {

    } else if(this->command_type == "C_IF") {

    } else if(this->command_type == "C_GOTO") {

    } else if(this->command_type == "C_CALL") {

    } else if(this->command_type == "C_RETURN") {

    }

}

void Parser::arg2() {

}

string Parser::commandType(string &line) {
    // Evaluates the line string (already cleaned) to check for which kind of
    // of operator we have. Also, deletes the substring that has the command
    // eg: "add", "sub", "pop" etc
    this->line = line;

    if(this->line.find("add", 0) != string::npos || this->line.find("sub", 0) != string::npos || this->line.find("neg", 0) != string::npos || this->line.find("and", 0) != string::npos || this->line.find("not", 0) != string::npos) {
        line = this->line;
        this->command_type = "C_ARITHMETIC";
    } else if(this->line.find("eq", 0) != string::npos || this->line.find("gt", 0) != string::npos || this->line.find("lt", 0) != string::npos || this->line.find("or", 0) != string::npos) {
        line = this->line;
        this->command_type = "C_ARITHMETIC";
    } else if(this->line.find("pop", 0) != string::npos) {
        this->line.erase(0, 3);
        line = this->line;
        this->command_type = "C_POP";
    } else if(this->line.find("push", 0) != string::npos) {
        this->line.erase(0, 4);
        line = this->line;
        this->command_type = "C_PUSH";
    } else if(this->line.find("label", 0) != string::npos) {
        this->line.erase(0, 5);
        line = this->line;
        this->command_type = "C_LABEL";

    } else if(this->line.find("funcion", 0) != string::npos) {
        this->line.erase(0, 7);
        line = this->line;
        this->command_type = "C_FUNCTION";

    } else if(this->line.find("if-goto", 0) != string::npos) {
        this->line.erase(0, 7);
        line = this->line;
        this->command_type = "C_IF";

    } else if(this->line.find("goto", 0) != string::npos) {
        this->line.erase(0, 4);
        line = this->line;
        this->command_type = "C_GOTO";

    } else if(this->line.find("call", 0) != string::npos) {
        this->line.erase(0, 4);
        line = this->line;
        this->command_type = "C_CALL";

    } else if(line.find("return", 0) != string::npos) {
        this->line.erase(0, 6);
        line = this->line;
        this->command_type = "C_RETURN";
    } else if(this->hasMoreCommands()) {
        // The only reason this is not a simple "else" is due
        // to how it would behave on the file's last line
        this->command_type = "C_ERROR";
    }

    if(this->command_type != "C_ERROR") {
        return this->command_type;
    } else {
        cout << "ERROR: Command type not found" << endl;
    }
}
