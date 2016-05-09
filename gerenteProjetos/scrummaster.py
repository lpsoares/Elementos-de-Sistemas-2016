#Import
from firebase import firebase
import random

#User feedback
print("Picking Scrum Master...")

#Get firebase app
firebase = firebase.FirebaseApplication("https://elemsist.firebaseio.com")

#Get scrum master
def get_scrum_master():

	#Get student at random index in firebase
	idx = random.randint(1,16)
	aluno = firebase.get("/alunos","/alunos0{0}".format(idx)) 

	#If student wasn't Scrum Master before, return student and update his/her status
	if aluno["wasSM"] == "False":
		scrum_master = aluno["name"]
		firebase.put("/alunos/alunos0{0}".format(idx), name="wasSM", data="True")
		return scrum_master

	#Else, try again
	else:
		get_scrum_master()

print("And the Scrum Master is:", get_scrum_master())

