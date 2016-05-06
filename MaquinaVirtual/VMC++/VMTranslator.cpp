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

    parser = new Parser();

    cout << "Done!" << endl;
    return 0;
}
