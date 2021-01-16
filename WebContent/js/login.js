
	
$(document).ready(function() {
	//ako je korisnik vec ulogovan, redirekcija na index.html
	if(sessionStorage.getItem("loggedIn") != null) {
		//window.location.href = "index.html"; TO DO - otkomentarisati posle, za sada samo smeta
	}
});

function login() {
	let korisnickoIme = $('#username').val();
	let lozinka = $('#password').val();
	
	if(formValidation == false) {
		return;
	}
	
	
	$.ajax({
		url:'rest/users/login',
		type:'POST',
		data:JSON.stringify({korisnickoIme,lozinka}),
		contentType:'application/json',
		success: function() {
			sessionStorage.setItem("loggedIn",korisnickoIme);
			
			//redirekcija u odnosu na tip korisnika sa jos jednim $.ajax() pozivom
			$.ajax({
				url:'rest/users/uloga/'+korisnickoIme,
				type:'GET',
				success: function(korisnik) {
					if(korisnik.uloga == 'GOST') {
						window.location.href = "gost.html";
						
					} else if(korisnik.uloga == 'DOMACIN') {
						window.location.href = "domacin.html";
						
					} else if(korisnik.uloga == 'ADMINISTRATOR') {
						window.location.href = "administrator.html";
					}
				},
				error: function() {
					alert('Nema ulogovanog korisnika.');
				}
			});
			
		},
		error: function() {
			alert('error');
		}
	});
}

function formValidation(korisnickoIme,lozinka) {
	let flag = true;
	if(korisnickoIme == "") {
		$("#username_val").attr('hidden', false);
		flag = false;
	}
	
	if(lozinka == "") {
		$("#password_val").attr('hidden', false);
		flag = false;
	}
	return flag;
}