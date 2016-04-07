// File name: Fibonacci.asm

// Le a quantidade de valores da RAM[0] e grava a sequencia de números 
// de Fibonacci nas posições seguintes RAM[1], RAM[2], etc....
// Por exemplo: se RAM[0]=6
// RAM[1]=0, RAM[2]=1, RAM[3]=1, RAM[4]=2, RAM[5]=3, RAM[6]=5
// Maiores informações em: https://oeis.org/A000045

// Codigo limitado, 24 iteracoes sao o limite dos calculos possiveis para o Doug
@0
D = M - 1
@43
D;JLT
@0
D=D-1
@43
D;JLT
@0
D=D-1
@2
M=1
@43
D;JLT

@0
D=D-1
@3
M=1
@43
D;JLT

@3
D=A
@1
M=D

@1
D=M
A=D
D=M
A=A+1
M=D+M
A=A-1
A=A-1
D=M
A=A+1
A=A+1
M=D+M

@0
D=M
@1
M=M+1
D=M-D
@24
D;JLT

@1
M = 0
@43
0;JMP