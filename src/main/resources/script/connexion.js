$(document).ready(function() {
	this.add = function() {
		
		$("#email").val("");
		$("#inscriptionMdp").val("");
		$("#limite").val("");
		$("#salaire").val("");
		
  var element = document.getElementById("inscriptionForm");
  if (element.style.display === "none") {
    element.style.display = "block";
    console.log("if");
  } else {
    element.style.display = "none";
    console.log("else");
  }
	};

	});