// Luciano Soares
// File name: Factorial.asm

// Calcula o fatorial do número em R0 e armazena o valor em R1.
// (R0, R1 refer to RAM[0] and RAM[1], respectively.)

// Put your code here.

@1 // Limpa a memória
M=0

// Fatorial de 0
@0
D=M
@1
M=A
@END
D;JEQ


// Fatorial de 1
@0
D=M
@1
M=A
@END
D;JEQ

@0
D=M-1
@3
M=D-1  // Contador loop externo
@2
M=D // Contador Loop interno
@4
M=D+1 // valor à ser somado
@0
D=A
@1
M=D

(LOOP)
// LOOP INTERNO
@4
D=M
@1
M=M+D
@2
M=M-1
D=M
@LOOP
D;JGT

// LOOP EXTERNO
@1
D=M
@4
M=D
@3
M=M-1
D=M
@2
M=D
@LOOP
D;JGT


(END)
@END
0;JMP // Loop
