# -*- coding: utf-8 -*-
"""
Created on Fri May  6 21:44:45 2016

@author: lucasastur
"""
op_binarios = ('add','sub','and','or')
op_unarios = ('neg','not')    
compare = ('eq','gt','lt')
Op = dict(zip(op_binarios,('+','-','&','|')))
Op.update(zip(op_unarios,('-','!')))
Comp = dict(zip(compare,('JEQ','JGT','JLT')))
Seg = dict(zip(('local','argument','this','that','temp','pointer'),('LCL','ARG','THIS','THAT',5,3)))

class CodeWriter:
    
    def __init__(self, arquivo_saida):  #construtor
        self.endoflabel = 1
        self.arquivo = open(arquivo_saida + '.asm', 'w')
        
        self.writeInit()
        
        
    def writeInit(self):      #codigo do bootstrap
        self.arquivo.write('@256\n')
        self.arquivo.write('D=A\n')
        self.arquivo.write('@SP\n')
        self.arquivo.write('M=D\n')
        
        self.writeCall('Sys$init', 0)
        
        
    def writeArithmetic(self, comando):
        if comando in op_binarios:             
            self.twoFromStack()
            
            self.outfile.write('D=A%sD\n'%Op[comando])
            
            self.pushD()

        elif comando in op_unarios:
            self.popToD()
            
            self.arquivo.write('D=%sD\n'%Op[comando])
            
            self.pushD()

        elif comando in compare:
            true = 'TRUE%s' %(self.endoflabel)
            pula = 'PULA%s' %(self.endoflabel)
            self.endoflabel += 1
            self.twoFromStack()
            self.arquivo.write('D=A-D\n')
            self.arquivo.write('@%s\n' %true)
            self.arquivo.write('D;%s\n' %Comp[comando])
            self.arquivo.write('@SP\n')
            self.arquivo.write('A=M\n')
            self.arquivo.write('M=0\n')
            self.incrementSP()  
            self.arquivo.write('@%s\n' %pula)
            self.arquivo.write('0;JMP\n')    
            self.arquivo.write('(%s)\n' %true)
            self.arquivo.write('@SP\n')
            self.arquivo.write('A=M\n')
            self.arquivo.write('M=-1\n')
            self.incrementSP()
            self.arquivo.write('(%s)\n' %pula)
        
        
    def writePushPop(self, comando, segmento, indice):          
        if comando == 'push':
            if segmento == 'constant':
                self.arquivo.write('@%s\n' %indice)
                self.arquivo.write('D=A\n')
            elif segmento in ('temp','pointer'):
                self.arquivo.write('@%d\n' %(Seg[segmento] + int(indice)))
                self.arquivo.write('D=M\n')
            elif segmento == 'static':
                varName = self.fileName + indice
                self.arquivo.write('@%s\n' %varName)
                self.arquivo.write('D=M\n')            
            else:
                symbol = Seg[segmento]
                self.arquivo.write('@%s\n' %symbol)
                self.arquivo.write('D=M\n')
                self.arquivo.write('@%s\n' %indice)
                self.arquivo.write('A=D+A\n')
                self.arquivo.write('D=M\n')
                
            self.pushD()
            
        elif comando == 'pop':
            if segmento in ('temp','pointer'):
                self.arquivo.write('@%d\n' %(Seg[segmento] + int(indice)))
                self.arquivo.write('D=A\n')
                self.arquivo.write('@R13\n')
                self.arquivo.write('M=D\n')
            elif segmento == 'static':
                varName = self.FileName + indice
                self.arquivo.write('@%s\n' %varName)
                self.arquivo.write('D=A\n')
                self.arquivo.write('@R13\n')
                self.arquivo.write('M=D\n')
            else:
                symbol = Seg[segmento]
                self.arquivo.write('@%s\n' %symbol)
                self.arquivo.write('D=M\n')
                self.arquivo.write('@%s\n' %indice)
                self.arquivo.write('D=D+A\n')
                self.arquivo.write('@R13\n')
                self.arquivo.write('M=D\n')
                
            self.popToD()
            
            self.arquivo.write('@R13\n')
            self.arquivo.write('A=M\n')
            self.arquivo.write('M=D\n')
                   
        
    def writeLabel(self, simbolo):
        self.arquivo.write('(%s)\n' %simbolo)
    
    
    def writeGoto(self,simbolo):
        self.arquivo.write('@%s\n' %simbolo)
        self.arquivo.write('0;JMP\n')  
        
        
    def writeIf(self,simbolo):
        self.popToD()
        
        self.outfile.write('@%s\n' %simbolo)
        self.outfile.write('D;JNE\n')
    
       
    def writeCall(self,nome_funcao, num_args):       
        argOffset = int(num_args) + 5
        returnLabel = 'return%s%d' %(nome_funcao, self.endoflabel) 
        self.endoflabel += 1
        
        self.arquivo.write('@%s\n' %returnLabel)
        self.arquivowrite('D=A\n')
        
        self.pushD()
        
        for segment in ('LCL','ARG','THIS','THAT'):   
            self.arquivo.write('@%s\n' %segment)
            self.arquivo.write('D=M\n')
            self.pushD()
            
        self.arquivo.write('@SP\n')        
        self.arquivo.write('D=M\n')
        self.arquivo.write('@%d\n' %argOffset)
        self.arquivo.write('D=D-A\n')
        self.arquivo.write('@ARG\n')
        self.arquivo.write('M=D\n')        
        self.arquivo.write('@SP\n')              
        self.arquivo.write('D=M\n')
        self.arquivo.write('@LCL\n') 
        self.arquivo.write('M=D\n')
        self.writeGoto(nome_funcao)
        self.writeLabel(returnLabel)
    
    
    def writeReturn(self):
        FRAME = 'R5'
        RET = 'R6' 
        self.arquivo.write('@LCL\n')       
        self.arquivo.write('D=M\n')
        self.arquivo.write('@%s\n' %FRAME)
        self.arquivo.write('M=D\n')    
        self.arquivo.write('D=M\n')      
        self.arquivo.write('@5\n')
        self.arquivo.write('D=D-A\n')
        self.arquivo.write('@%s\n' %RET)
        self.arquivo.write('M=D\n')      
        self.popToD()
        self.arquivo.write('@ARG\n')       
        self.arquivo.write('A=M\n')
        self.arquivo.write('M=D\n')           
        self.arquivo.write('@ARG\n')    
        self.arquivo.write('D=M+1\n')
        self.arquivo.write('@SP\n')
        self.arquivo.write('M=D\n')
        self.arquivo.write('@%s\n' %FRAME) 
        self.arquivo.write('A=M-1\n')
        self.arquivo.write('D=M\n')
        self.arquivo.write('@THAT\n')
        self.arquivo.write('M=D\n')        
        self.arquivo.write('@%s\n' %FRAME)
        self.arquivo.write('D=M\n')
        self.arquivo.write('@2\n')
        self.arquivo.write('A=D-A\n')
        self.arquivo.write('D=M\n')
        self.arquivo.write('@THIS\n')
        self.arquivo.write('M=D\n')
        self.arquivo.write('@%s\n' %FRAME)
        self.arquivo.write('D=M\n')
        self.arquivo.write('@3\n')
        self.arquivo.write('A=D-A\n')
        self.arquivo.write('D=M\n')
        self.arquivo.write('@ARG\n')
        self.arquivo.write('M=D\n')
        self.arquivo.write('@%s\n' %FRAME)
        self.arquivo.write('D=M\n')
        self.arquivo.write('@4\n')
        self.arquivo.write('A=D-A\n')
        self.arquivo.write('D=M\n')
        self.arquivo.write('@LCL\n')
        self.arquivo.write('M=D\n')
        self.arquivo.write('@%s\n' %RET) 
        self.arquivo.write('A=M\n')
        self.arquivo.write('A=M\n')
        self.arquivo.write('0;JMP\n')
        
        
    #funcoes nao pedidas no desenvolvimento do CodeWriter
        
    def pushD(self):
        self.arquivo.write('@SP\n')
        self.arquivo.write('A=M\n')
        self.arquivo.write('M=D\n')
        
        self.incrementSP()        

    def twoFromStack(self):
        self.popToD()
        
        self.popToA()

    def decrementSP(self):
        self.arquivo.write('@SP\n')
        self.arquivo.write('M=M-1\n')

    def incrementSP(self):
        self.arquivo.write('@SP\n')
        self.arquivo.write('M=M+1\n')        

    def popToA(self):
        self.decrementSP()
        self.arquivo.write('@SP\n')
        self.arquivo.write('A=M\n')
        self.arquivo.write('A=M\n')

    def popToD(self):
        self.decrementSP()
        self.arquivo.write('@SP\n')
        self.arquivo.write('A=M\n')
        self.arquivo.write('D=M\n')
        
    #######################################################
        
    def close(self):
        self.arquivo.close() #fecha e conclui arquivo
        
    