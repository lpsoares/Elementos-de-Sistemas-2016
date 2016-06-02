#include <iostream>
#include <vector>
#include <fstream>
#include <unordered_map>
#include <string.h>
#include <sstream>

using namespace std;

#ifndef PARSER_HPP
#define PARSER_HPP

class Parser {
    public:
        // Constructor, opens the file and gets it ready to be
        // parsed
        Parser();
        ~Parser();    // Also, the destructor
        string commandType(string &line);
        void advance(string &line);
        bool hasMoreCommands();
        string arg1();
        void arg2();

    private:
        int lineCounter;
        string line;
        string command_type;
        void clearSpacesAndComments();
        ifstream infile;
        vector<string> line_split;

};

#endif
