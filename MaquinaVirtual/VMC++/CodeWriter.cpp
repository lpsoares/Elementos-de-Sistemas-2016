#include <iostream>


#include <vector>
#include <fstream>
#include <unordered_map>
#include <string.h>
#include "CodeWriter.hpp"

using namespace std;


CodeWriter::CodeWriter() {
    // First, we need to create our Parser object
    // This should recieve the output from the parsing
    ofstream writer("output.asm");
}
