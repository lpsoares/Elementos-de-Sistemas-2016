#include <iostream>
#include <vector>
#include <fstream>
#include <unordered_map>
#include <string.h>
#include "Parser.hpp"
#include "CodeWriter.hpp"


using namespace std;

Parser* parser;

int main() {
    // Setting these default values
    int SP = 256;
    int LCL = 0;
    int ARG = 0;
    int THIS = 0;
    string command_type;
    string line;

    parser = new Parser();
    // We got to read the file line by line and work with it
    // inside of this loop
    while(parser->hasMoreCommands()) {
        parser->advance(line);
        command_type = parser->commandType(line);
        cout << command_type << endl;
    }
    cout << "Done!" << endl;
    return 0;
}
