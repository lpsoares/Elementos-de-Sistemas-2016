// File name: Div.asm

// Divide R0 por R1 e armazena o resultado em R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
// divisao para numeros inteiros positivos
@1
D=M
@22
D;JEQ
@0
D=M
@1
D=D-M
@22
D;JLT
@0
D=M
@1
D=M
@0
M=M-D
@22
D;JLT
@10
M=M+1
@0
M;JGT
@22
D=A
0;JMP