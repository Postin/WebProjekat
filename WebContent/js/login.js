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
		success: function(id) {
			sessionStorage.setItem("id", id);
			//redirekcija u odnosu na tip korisnika sa jos jednim $.ajax() pozivom
			
			
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