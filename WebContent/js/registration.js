function register() {
	let korisnickoIme = $('#username').val();
	let lozinka = $('#password').val();
	let confirm = $('#confirm').val();
	let ime = $('#name').val();
	let prezime = $('#prezime').val();
	let pol;
	
	if(document.getElementById("musko").checked) {
		pol = document.getElementById("musko").value;
	} else if (document.getElementById("zensko").checked) {
		pol = document.getElementById("zensko").value;
	} else if (document.getElementById("drugo").checked) {
		pol = document.getElementById("drugo").value;
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