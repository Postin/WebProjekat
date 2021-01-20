$(document).ready(function () {
    loggedUser = null;
    $.get({
        url: 'rest/users/ulogovan',
        success: function (user) {
            loggedUser = user;
            generateToolBar(user);
            if (user == null || user.uloga != "ADMINISTRATOR") {
                window.location = "../";
                return;
            }
        }
    });

    $("#addAmenity").click(function (e) {
        
    	e.preventDefault();
        name = $("#name").val()
        if (name === "") {
            return;
        }
        $.post({
            url: 'rest/sadrzaj?name=' + name,
            contentType: 'application/json',
            success: function (data) {
                alert("Sadrzaj je dodat")
                window.location = ''
            },
            error: function (ret) {
                alert(ret.responseText)
            }
        });
    });

    $.get({
        url: 'rest/sadrzaj',
        success: function (data) {
            for (let sadrzaj of data) {
                var tableRef = document.getElementById('amenities').getElementsByTagName('tbody')[0];
                var newRow = tableRef.insertRow();
                var newCell2 = newRow.insertCell(0);
                var newText2 = document.createElement('input');
               
                newText2.setAttribute("type", "text");
                newText2.setAttribute("value", sadrzaj.naziv);
                newText2.setAttribute("id", "value" + sadrzaj.id);
               
                newText2.onkeyup = function () {
                    var txt = document.getElementById("value" + sadrzaj.id);
                    if (txt.value === "") {
                        document.getElementById('save' + sadrzaj.id).disabled = true;
                        return;
                    }
                    if (txt.value !== sadrzaj.naziv) {
                        document.getElementById('save' + sadrzaj.id).disabled = false;
                    }
                    else {
                        document.getElementById('save' + sadrzaj.id).disabled = true;
                    }
                };
                newCell2.appendChild(newText2);
                var newCell3 = newRow.insertCell(1);
                var editBtn = document.createElement('button');
                editBtn.innerHTML = "save";
                editBtn.id = "save" + sadrzaj.id;
                editBtn.disabled = "true"
                editBtn.onclick = function (e) {
                    e.preventDefault();
                    var txt = document.getElementById("value" + sadrzaj.id).value;
                    $.ajax({
                        url: 'rest/sadrzaj?id=' + sadrzaj.id + "&name=" + txt,
                        type: 'PUT',
                        contentType: 'application/json',
                        success: function () {
                            location.reload();
                        }
                    });
                }
                newCell3.appendChild(editBtn);
                var newCell4 = newRow.insertCell(2);
                var dltBtn = document.createElement('button');
                dltBtn.innerHTML = "delete";
                dltBtn.id = sadrzaj.id;
                dltBtn.onclick = function (e) {
                    e.preventDefault();
                    $.ajax({
                        url: 'rest/sadrzaj?id=' + event.srcElement.id, //vratice id elementa na kom je izvrsen dogadjaj click()
                        type: 'DELETE',
                        contentType: 'application/json',
                        success: function () {
                            location.reload();
                        }
                    });
                }
                newCell4.appendChild(dltBtn);

            }
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
      
        
        } else if (user.uloga === "ADMINISTRATOR") {
            var sadrzaji = document.createElement('s');
            sadrzaji.innerHTML = "Sadrzaji";
            sadrzaji.setAttribute('data-toggle', 'tooltip');
            sadrzaji.setAttribute('href', 'sadrzajApartmana.html');
            sadrzaji.setAttribute('data-toggle', 'tooltip');
            document.getElementById("toolbar").appendChild(sadrzaji);
      //      $('#toolbar').append("<a href=\"userList.html\" id=\"userList\" data-toggle=\"tooltip\" title=\"toggle search bar\">User List</a>");
      //      $('#toolbar').append("<a href=\"holidays.html\" id=\"holidays\" data-toggle=\"tooltip\" title=\"toggle search bar\">Holidays</a>");
        }

  //      $('#toolbar').append("<a href=\"reservations.html\" id=\"reservations\" data-toggle=\"tooltip\" title=\"toggle search bar\">Reservations</a>");

//        if (user.role != "guest") {
//            var comment = document.createElement('a');
//            comment.innerHTML = "Comments";
//            comment.setAttribute('href', 'comment.html');
//            document.getElementById("toolbar").appendChild(comment);
//        }
//
//        var profile = document.createElement('a');
//        profile.innerHTML = "Profile";
//        profile.setAttribute('data-toggle', 'tooltip');
//        profile.setAttribute('href', 'userProfile.html');
//        profile.setAttribute('data-toggle', 'tooltip');
//        document.getElementById("toolbar").appendChild(profile);

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

function validateName() {
    if ($("#name").val() === "") {
        document.getElementById("name").style.borderColor = "#FF0000";
    }
    else {
        document.getElementById("name").style.borderColor = "#000000";
    }
}