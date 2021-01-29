$(document).ready(function () {
    $.get({
        url: 'rest/users/ulogovan',
        success: function (user) {
            generateToolBar(user);
            $.get({
                url: 'rest/komentar',
                success: function (komentari) {
                    if (user.uloga === "ADMINISTRATOR") {
                        $(".tableHeaders").append(
                            "<th>Gost</th>" + "<th>Apartman</th>" + "<th>Komentar</th>" + "<th>Ocena</th>" + "<th>Vidljivost</th>")
                        for (let k of komentari) {

                            kom = "<tr><td>" + k.gostKorisnickoIme + "</td><td>" + k.apartmanId + "</td><td>" + k.tekst + "</td><td>" + k.ocena + "</td><td>" + k.vidljivost + "</td></tr>"

                            $(".tableBody").append(kom);
                        }
                    }
                    else if (user.uloga === "DOMACIN") {

                        $(".tableHeaders").append(
                            "<th>Id</th>" + "<th>Gost</th>" + "<th>Apartman</th>" + "<th>Komentar</th>" + "<th>Ocena</th>" + "<th>Vidljivost</th>" + "<th>Sakriven</th>")
                        for (let k of komentari) {
                            if (k.vidljivost)
                                kom = "<tr><td id=\"id\">" + k.id + "</td><td>" + k.gostKorisnickoIme + "</td><td>" + k.apartmanId + "</td><td>" + k.tekst + "</td><td>" + k.ocena + "</td><td>" + k.vidljivost + "</td><td><button class=\"ban\">Sakrij</button></td></tr>"
                            else
                                kom = "<tr><td id=\"id\">" + k.id + "</td><td>" + k.gostKorisnickoIme + "</td><td>" + k.apartmanId + "</td><td>" + k.tekst + "</td><td>" + k.ocena + "</td><td>" + k.vidljivost + "</td><td></td></tr>"
                            

                            $(".tableBody").append(kom)

                        }
                      } 
                    if (user.uloga === "GOST") {
                        $(".tableHeaders").append(
                            "<th>Gost</th>" + "<th>Apartman</th>" + "<th>Komentar</th>" + "<th>Ocena</th>")
                        for (let k of komentari) {

                            kom = "<tr><td>" + k.gostKorisnickoIme + "</td><td>" + k.apartmanId + "</td><td>" + k.tekst + "</td><td>" + k.ocena + "</td><td>" + "</td></tr>"

                            $(".tableBody").append(kom);
                        }
                    }
                        
                        $(".ban").click(function () {
                            console.log($(this).parents("td").siblings("#id").text())
                            $.ajax({
                                url: 'rest/komentar/hide/' + $(this).parents("td").siblings("#id").text(),
                                method: 'PUT',
                                success: function () {
                                    alert("Success")
                                    location.reload();
                                },
                                error: function (e) {
                                    alert(e.responseText)
                                }
                            })
                        })

                    }

                
            });
        }
    });

})



function generateToolBar(user) {
	
    if (user != null) {
        if (user.uloga === "DOMACIN") {
            var novApartman = document.createElement('a');
            novApartman.innerHTML = "Dodaj apartman";
            novApartman.setAttribute('data-toggle', 'tooltip');
            novApartman.setAttribute('href', 'dodajApartman.html');
            novApartman.setAttribute('data-toggle', 'tooltip');
            document.getElementById("toolbar").appendChild(novApartman);
        
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
            
      
          }else if (user.uloga === "GOST") {
           
            var back = document.createElement('a');
            back.innerHTML = "Back";
            back.setAttribute('data-toggle', 'tooltip');
            back.setAttribute('href', 'gost.html');
            back.setAttribute('data-toggle', 'tooltip');
            document.getElementById("toolbar").appendChild(back);
        
        }

        var element = document.createElement('l');
        element.id = "logout";
        element.innerHTML = "Logout";
        element.onclick = logout;
        element.setAttribute('data-toggle', 'tooltip');
        element.setAttribute('href', 'login.html');
        document.getElementById("toolbar").appendChild(element);
    } else {
       alert("Neregistrovani i neulogovani korisnici ne mogu da pregledaju komentare!")
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
