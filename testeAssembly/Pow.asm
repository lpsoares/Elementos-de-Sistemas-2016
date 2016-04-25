@0
D=M
@16
M=D-1
@1
D=M
@4
M=D
@0
D=M
@2
M=D
@1
D=M
@17
M=D-1
(LOOP)
    @0
    @0
    D=M
    @2
    M=M+D
    @16
    M=M-1
    @LOOP
    M;JGT
    
    @0
    D=M
    @16
    M=D

    @17
    M=M-1
    @17
    M;JGT

