# -*- coding: utf-8 -*-
"""
Created on Sun Apr 24 10:39:34 2016

@author: Khalil
"""

import parser

asm = open("parser.asm", "r") #arquivoentrada
hack = open("arquivo.hack","w") #arquivosaida
parser = parser()


#Convertendo de .asm para um arquivo .hack

for i in asm.readlines():
    x = parser.readlines(i)
    hack.write(x)  
    
asm.close()
hack.close()