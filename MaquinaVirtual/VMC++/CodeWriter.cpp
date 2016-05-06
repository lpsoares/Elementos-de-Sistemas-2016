#include <iostream>
#include <vector>
#include <fstream>
#include <unordered_map>
#include <string.h>
#include "CodeWriter.hpp"
#include "Parser.hpp"

using namespace std;


CodeWriter::CodeWriter() {
    // First, we need to create our Parser object
    this -> parser = new Parser;
    // This should recieve the output from the parsing
    ofstream writer("output.asm");
}
