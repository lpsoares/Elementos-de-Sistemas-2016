// File name: RestoDaDivisao.asm
// Para entender o funcionamneto é preciso saber que : Divide o número posicionado na RAM[1] pelo número posicionado no RAM[2] e armazena o resultado na RAM[0].Para descobrir o resto da divisão checamos o valor restante em RAM[1] ao final da divisão.
@2
D=M
//valor no RAM[2] que será dividido
@22
//caso D seja igual a zero pula para o loop infinito
D;JEQ
@1
D=M
//valor no RAM[1] que será dividido
@2
D=D-M
//D = M[1]-M[2]
@22
//caso D seja igual a zero pula para o loop infinito
D;JLT
@1
//valor no RAM[1] que será dividido
D=M
@2
//valor no RAM[2] que será dividido
D=M
@1
M=M-D
// M= M[2]-M[1]
@22
//caso D seja igual a zero pula para o loop infinito
D;JLT
@0	
//Resultado salvo em RAM[0]
M=M+1
//toda vez que subtraimos M[2] adicionamos 1 no resultado pois funciona como se fosse uma divisão
@0
M;JGT
//loop infinito para parar o problema
@22
D=A
0;JMP