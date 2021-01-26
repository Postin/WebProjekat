$(document).ready(function() {
	 
		loggedUser = null;
	    
		$.get({
	        url: 'rest/users/ulogovan',
	        success: function (user) {
	            loggedUser = user;
	            generateToolBar(user);
	      
	        }
	    });
	
	    
		 $.get({
		        url: 'rest/apartmans/listaZaPretragu',
		        success: function (listaApt) {
		        	$('#tblPretraga tbody').html('');
					let data = [];
					
					for(i = 0; i < listaApt.length; i++) {
						let d = [listaApt[i].ime, listaApt[i].lokacija.adresa.mesto, listaApt[i].cenaPoNoci, listaApt[i].brojSoba,listaApt[i].brojGostiju];
						data.push(d);
						}
						
						// inicijalizacija datatable-a
						$('#tblPretraga').DataTable( {
						    dom: "Bfrtip",
						    data: data
						  });
					}
		    	});
		    	
		    });	

	
function generateToolBar(user) {
	
    if (user != null) {
        if (user.uloga === "DOMACIN") {
            var novApartman = document.createElement('a');
            novApartman.innerHTML = "Dodaj apartman";
            novApartman.setAttribute('data-toggle', 'tooltip');
            novApartman.setAttribute('href', 'dodajApartman.html');
            novApartman.setAttribute('data-toggle', 'tooltip');
            document.getElementById("toolbar").appendChild(novApartman);
        //    $('#toolbar').append("<a href=\"userList.html\" id=\"userList\" data-toggle=\"tooltip\" title=\"toggle search bar\">User List</a>");
      
            var back = document.createElement('a');
            back.innerHTML = "Back";
            back.setAttribute('data-toggle', 'tooltip');
            back.setAttribute('href', 'domacin.html');
            back.setAttribute('data-toggle', 'tooltip');
            document.getElementById("toolbar").appendChild(back);
        
        } else if (user.uloga === "ADMINISTRATOR") {
            var sadrzaji = document.createElement('a');
            sadrzaji.innerHTML = "Sadrzaji";
            sadrzaji.setAttribute('data-toggle', 'tooltip');
            sadrzaji.setAttribute('href', 'sadrzajApartmana.html');
            sadrzaji.setAttribute('data-toggle', 'tooltip');
            document.getElementById("toolbar").appendChild(sadrzaji);
            
            var back = document.createElement('a');
            back.innerHTML = "Back";
            back.setAttribute('data-toggle', 'tooltip');
            back.setAttribute('href', 'administrator.html');
            back.setAttribute('data-toggle', 'tooltip');
            document.getElementById("toolbar").appendChild(back);
            
      //      $('#toolbar').append("<a href=\"userList.html\" id=\"userList\" data-toggle=\"tooltip\" title=\"toggle search bar\">User List</a>");
      //      $('#toolbar').append("<a href=\"holidays.html\" id=\"holidays\" data-toggle=\"tooltip\" title=\"toggle search bar\">Holidays</a>");
        
        }else if (loggedUser.uloga === "GOST") {
           
            var back = document.createElement('a');
            back.innerHTML = "Back";
            back.setAttribute('data-toggle', 'tooltip');
            back.setAttribute('href', 'gost.html');
            back.setAttribute('data-toggle', 'tooltip');
            document.getElementById("toolbar").appendChild(back);
        
        }

  //      $('#toolbar').append("<a href=\"reservations.html\" id=\"reservations\" data-toggle=\"tooltip\" title=\"toggle search bar\">Reservations</a>");

//        if (user.role != "guest") {
//            var comment = document.createElement('a');
//            comment.innerHTML = "Comments";
//            comment.setAttribute('href', 'comment.html');
//            document.getElementById("toolbar").appendChild(comment);
//        }



        var element = document.createElement('l');
        element.id = "logout";
        element.innerHTML = "Logout";
        element.onclick = logout;
        element.setAttribute('data-toggle', 'tooltip');
        element.setAttribute('href', 'login.html');
        document.getElementById("toolbar").appendChild(element);
    } else {
        $("#toolbar").append("<a href=\"login.html\" data-toggle=\"tooltip\" >Login</a><a href=\"registration.html\" data-toggle=\"tooltip\">Register</a>");
    }
}

function logout() {
    return $.post({
        url: 'rest/users/logout',
        contentType: 'application/json',
        success: function () {
            localStorage.removeItem('jwt');
            window.location = 'login.html'
        }
    });
}		        	
	
		        	
