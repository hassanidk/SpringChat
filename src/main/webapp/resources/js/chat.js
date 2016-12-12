$(document).ready(function() {
	loadnamesalon();
	loadmessage();
	loadsalon();
	
});
setInterval(function(){loadmessage();},5000);
setInterval(function(){loadsalon();},60000);

$('body').on('keypress','#chattext', function(e){
    if (e.keyCode == 13){
    	sendMessage();
    	loadmessage();
    }
});

function loadnamesalon(){
	var salon = getParam()["salon"];
	$('#idsalon').text('Salon '+salon);
}
function loadmessage(){
	var salon = getParam()["salon"];
	var pseudo = getParam()["pseudo"];
	var idmessage = $('#idmessage').text();
		$.ajax({
			type:"GET",
			url:"chat/salon/"+salon,
			data:{idmessage:idmessage},
			success:function(respond){
				var res = JSON.parse(respond);
				jQuery.each(res.messages,function(i,val){
					
					if (pseudo == val.auteur){
						$('#chat').append(addClass('message_user',val.auteur,val.message));
				
					}else{
						$('#chat').append(addClass('message_others',val.auteur,val.message));
					}

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
			$('#listeSalon').append('<h1>Liste des salons</h1>');
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
			$('#newsalon').val('');
		}
	});
	
}

function addClass(type,auteur,message){
	var newclass='<div id="'+type+'"><div id="contenuMessage">'+message+'</div><span id="pseudoMessage">'
	+auteur+'</span></div>'	
	
	return newclass;
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



