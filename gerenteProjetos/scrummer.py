#!/usr/bin/env python

from random import choice

def getScrummer():
	with open("alunos.txt","r+") as file:
		alunos = file.read().split("\n")
		scrummer = choice(alunos)
		del alunos[alunos.index(scrummer)]

		print("Scrummer:", scrummer)
	
		file.seek(0)
		file.truncate()
		for aluno in alunos:
			file.write(aluno + "\n")

def setScrummer():
	file = open("alunos.txt","x")
	alunos = ["André Toyama","Antonio Sigrist","Carlos Rosa","Eric Otofuji",
	          "Felipe Buniac","Gabriel Rios","Gabriela Almeida","João Pedro",
	          "Khalil Yassine","Lucas Astur","Marcelo Andrade","Matheus Dias",
	          "Pedro Cunial","Rachel Moraes","Rafael Molines"]
	for aluno in alunos:
		file.write(aluno + "\n")
	file.close()

try:
	getScrummer()

except FileNotFoundError:
	setScrummer()
	getScrummer()
