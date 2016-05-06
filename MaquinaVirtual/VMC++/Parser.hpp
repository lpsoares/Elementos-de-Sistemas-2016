#include <iostream>
#include <vector>
#include <fstream>
#include <unordered_map>
#include <string.h>

using namespace std;

#ifndef PARSER_HPP
#define PARSER_HPP

class Parser {
    public:
        // Constructor, opens the file and gets it ready to be
        // parsed
        Parser();
        string commandType();
        void advance();

    private:
        int lineCounter;
        string line;
        void clearSpacesAndComments();
        void arg1();
        void arg2();
        ifstream infile;

};

#endif
