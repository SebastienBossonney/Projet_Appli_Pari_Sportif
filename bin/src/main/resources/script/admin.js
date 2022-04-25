$(document).ready(function() {

	this.sport = [football,basketball,rugby];
	this.match = [{
		"sport" : "football",
		"equipe 1" : "PSG",
		"equipe 2" : "Lyon"
},
{
		"sport" : "football",
		"equipe 1" : "Manchester",
		"equipe 2" : "Toulon"
		},
		{
		"sport" : "basketball",
		"equipe 1" : "Montauban",
		"equipe 2" : "Paul-Sabatier"
		},
		{
		"sport" : "rugby",
		"equipe 1" : "Toulouse",
		"equipe 2" : "Cahors"
	}]

	this.add = function() {
		$("#id").val("");
		$("#intitule").val("");
		$("#promotion").val("");
		$("#dtDebut").val("");
		$("#duree").val("");
		$("#dispositif").val("");
		
		$("#filiereForm").show();
	};

});