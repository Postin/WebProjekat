function register() {
	let korisnickoIme = $('#username').val();
	let lozinka = $('#password').val();
	let confirm = $('#confirm').val();
	let ime = $('#name').val();
	let prezime = $('#surname').val();
	let pol;
	
	if(document.getElementById("musko").checked) {
		pol = document.getElementById("musko").value;
	} else if (document.getElementById("zensko").checked) {
		pol = document.getElementById("zensko").value;
	} else if (document.getElementById("drugo").checked) {
		pol = document.getElementById("drugo").value;
	}
	
	if(formValidation(korisnickoIme,lozinka,confirm,ime,prezime,pol) == false) {
		return;
	}
	
	if(lozinka != confirm) {
		alert('Ne poklapaju se lozinka i potvrda lozinke!');
		return;
	}
	
	$.ajax({
		url:'rest/users/register',
		type:'POST',
		data:JSON.stringify({korisnickoIme,lozinka,ime,prezime,pol}),
		contentType:'application/json',
		success: function() {
			window.location.href ='login.html';
		},
		error: function() {
			alert('error!');
		}
	});
}


function formValidation(korisnickoIme,lozinka,confirm,ime,prezime,pol) {
	let flag = true;
	if(korisnickoIme == "") {
		$("#username_val").attr('hidden', false);
		flag = false;
	}
	
	if(lozinka == "") {
		$("#password_val").attr('hidden', false);
		flag = false;
	}
	
	if(confirm == "") {
		$("#confirm_val").attr('hidden', false);
		flag = false;
	}
	
	if(ime == "") {
		$("#name_val").attr('hidden', false);
		flag =  false;
	}
	
	if(prezime == "") {
		$("#surname_val").attr('hidden', false);
		flag =  false;
	}
	
	if(pol == null) {
		$("#gender_val").attr('hidden', false);
		flag =  false;
	}
	return flag;
}
