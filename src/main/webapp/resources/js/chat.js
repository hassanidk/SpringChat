$(document).ready(function() {
	loadmessage();
	loadsalon();
	
});
setInterval(function(){loadmessage();},5000);
setInterval(function(){loadsalon();},60000);

$('body').on('keypress','#chattext', function(e){
    if (e.keyCode == 13){
    	sendMessage();
    }
});


function loadmessage(){
	var salon = getParam()["salon"];
	var idmessage = $('#idmessage').text();
		$.ajax({
			type:"GET",
			url:"chat/salon/"+salon,
			data:{idmessage:idmessage},
			success:function(respond){
				var res = JSON.parse(respond);
				jQuery.each(res.messages,function(i,val){
					
					
					$('#pseudoMessage').append(val.auteur);
					$('#separationMessage').append("-");
					$('#contenuMessage').append(val.message);
				})
				
				$('#idmessage').text(res['nbmessage']);
				
			}
		});
}
function sendMessage(){
	var salon = getParam()["salon"];
	var pseudo = getParam()["pseudo"];
	var message = document.getElementById("chattext").value;
	var data = {'auteur':pseudo,'salon':salon,'message':message};
	$.ajax({
		type:"POST",
		url:"chat/salon/"+salon,
		data: {json :JSON.stringify(data)},
		success:function(respond){
			$('#idmessage').text(respond['nbmessage']);
			$('#chattext').val('');
		},
		
			
	});	
}

function loadsalon(){
	var pseudo = getParam()["pseudo"];
	
	$.ajax({
		type:"GET",
		url:"chat/salon",
		success:function(respond){
			var res = JSON.parse(respond);
			$('#listeSalon').empty();
			jQuery.each(res.salons,function(i,val){	
				$('#listeSalon').append("<a href=\"chat.html?pseudo="+pseudo+"&salon="+val.salon+"\">"+val.salon+"</a> <br />");
						
			})
		}
		
	})
}

function newsalon(){
	var pseudo = getParam()["pseudo"];
	var salon = document.getElementById("newsalon").value;
	$.ajax({
		type:"POST",
		url:"chat/salon",
		data:{nomsalon:salon},
		success:function(respond){
			alert(respond);
			loadsalon();
		}
	});
	
}

function quitter(){
	location.href = "Logout";
	
	
}

function getParam(){
	var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,    
    function(m,key,value) {
      vars[key] = value;
    });
    return vars;
}



