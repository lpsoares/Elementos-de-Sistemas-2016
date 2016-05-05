#include <iostream>
#include <vector>
#include <fstream>
#include <unordered_map>
#include <string.h>

using namespace std;

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

};

CodeWriter::CodeWriter() {
    // This should recieve the output from the parsing
    ofstream writer("output.asm");
}

int main() {
    return 0;
}
