#include <iostream>
#include <vector>
#include <fstream>
#include <unordered_map>
#include <string.h>

using namespace std;

#ifndef CODEWRITER_HPP
#define CODEWRITER_HPP

class CodeWriter {
    public:
        CodeWriter();  // Constructor
        void writeArithmetic(string);
        void writePushPop(string, string, int); // (Comando, segmento, índice)
        void writeLabel(string);    // (símbolo)
        void writeGoto(string);     // (símbolo)
        void writeIf(string);       // (símbolo)
        void writeCall(string);     // (símbolo)
        void writeReturn(string);   // (símbolo)
        void writeFunction(string); // (símbolo)
        void close(); // Closes output file

    private:
        ofstream writer;

};

#endif
