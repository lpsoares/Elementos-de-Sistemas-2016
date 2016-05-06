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
    string final_command
    string command_type;
    string line;

    parser = new Parser();
    // We got to read the file line by line and work with it
    // inside of this loop
    while(parser->hasMoreCommands()) {
        parser->advance(line);
        command_type = parser->commandType(line);
        cout << command_type << endl;

        if(command_type != "C_RETURN") {
            final_command = parser->arg1();
            cout << final_command << endl;
        }
    }
    cout << "Done!" << endl;
    return 0;
}
