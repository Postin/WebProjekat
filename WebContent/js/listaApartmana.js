$(document).ready(function () {
    loggedUser = null;
    $.get({
        url: 'rest/users/ulogovan',
        success: function (user) {
            loggedUser = user;
            generateToolBar(user);
//            if (user == null || user.uloga != "ADMINISTRATOR") {
//                window.location = "../";
//                return;
 //           }
        }
    });

    
    $.get({
        url: 'rest/apartmans/listApartmans',
        success: function (data) {
            for (let apart of data) {
            	console.log(apart.id);
            	console.log(apart.ime);
                var tableRef = document.getElementById('apartmani').getElementsByTagName('tbody')[0];
                var newRow = tableRef.insertRow();
                
                
                //kolona za naziv
                var newCell2 = newRow.insertCell(0);
                var newText2 = document.createElement('input');
               
                newText2.setAttribute("type", "text");
                newText2.setAttribute("value", apart.ime);
                newText2.setAttribute("id", "value2" + apart.id);
               
                newText2.onkeyup = function () {
                    var txt2 = document.getElementById("value2" + apart.id).value;
                    
                    
                    if (txt2 === "") {
                        document.getElementById('save' + apart.id).disabled = true;
                        return;
                    }
                    if (txt2 !== apart.ime) {
                        document.getElementById('save' + apart.id).disabled = false;
                    }
                    else {
                        document.getElementById('save' + apart.id).disabled = true;
                    }
                };
                newCell2.appendChild(newText2);
                
                
                //kolona za tip
                var newCell3 = newRow.insertCell(1);
                var newText3 = document.createElement('input');
               
                newText3.setAttribute("type", "text");
                newText3.setAttribute("value", apart.tip);
                newText3.setAttribute("id", "value3" + apart.id);
               
                newText3.onkeyup = function () {
                    var txt3 = document.getElementById("value3" + apart.id).value;
                    if (txt3 === "") {
                        document.getElementById('save' + apart.id).disabled = true;
                        return;
                    }
                    if (txt3 !== apart.tip) {
                        document.getElementById('save' + apart.id).disabled = false;
                    }
                    else {
                        document.getElementById('save' + apart.id).disabled = true;
                    }
                };
                newCell3.appendChild(newText3);
                
                
              //kolona za aktivan
                var newCell4 = newRow.insertCell(2);
                var newText4 = document.createElement('input');
               
                newText4.setAttribute("type", "checkbox");
                newText4.setAttribute("value", apart.aktivan);
                newText4.setAttribute("id", "value4" + apart.id);
               
                newText4.onkeyup = function () {
                    var txt4 = document.getElementById("value4" + apart.id).value;
                   
                    if (txt4 !== apart.aktivan) {
                        document.getElementById('save' + apart.id).disabled = false;
                    }
                    else {
                        document.getElementById('save' + apart.id).disabled = true;
                    }
                };
                newCell4.appendChild(newText4);
                
                
                //kolona za cenu po noci
                var newCell5 = newRow.insertCell(3);
                var newText5 = document.createElement('input');
               
                newText5.setAttribute("type", "text");
                newText5.setAttribute("value", apart.cenaPoNoci);
                newText5.setAttribute("id", "value5" + apart.id);
               
                newText5.onkeyup = function () {
                    var txt5 = document.getElementById("value5" + apart.id).value;
                    if (txt5 === "") {
                        document.getElementById('save' + apart.id).disabled = true;
                        return;
                    }
                    if (txt5 !== apart.cenaPoNoci) {
                        document.getElementById('save' + apart.id).disabled = false;
                    }
                    else {
                        document.getElementById('save' + apart.id).disabled = true;
                    }
                };
                newCell5.appendChild(newText5);
                
                
                //kolona za broj soba
                var newCell6 = newRow.insertCell(4);
                var newText6 = document.createElement('input');
               
                newText6.setAttribute("type", "number");
                newText6.setAttribute("value", apart.brojSoba);
                newText6.setAttribute("id", "value6" + apart.id);
               
                newText6.onkeyup = function () {
                    var txt6 = document.getElementById("value6" + apart.id).value;
                    if (txt6 === "") {
                        document.getElementById('save' + apart.id).disabled = true;
                        return;
                    }
                    if (txt6 !== apart.brojSoba) {
                        document.getElementById('save' + apart.id).disabled = false;
                    }
                    else {
                        document.getElementById('save' + apart.id).disabled = true;
                    }
                };
                newCell6.appendChild(newText6);
                
                
                //kolona za broj gostiju
                var newCell7 = newRow.insertCell(5);
                var newText7 = document.createElement('input');
               
                newText7.setAttribute("type", "number");
                newText7.setAttribute("value", apart.brojGostiju);
                newText7.setAttribute("id", "value7" + apart.id);
               
                newText7.onkeyup = function () {
                    var txt7 = document.getElementById("value7" + apart.id).value;
                    if (txt7 === "") {
                        document.getElementById('save' + apart.id).disabled = true;
                        return;
                    }
                    if (txt7 !== apart.brojGostiju) {
                        document.getElementById('save' + apart.id).disabled = false;
                    }
                    else {
                        document.getElementById('save' + apart.id).disabled = true;
                    }
                };
                newCell7.appendChild(newText7);
                
                
                //kolona za mesto
                var newCell8 = newRow.insertCell(6);
                var newText8 = document.createElement('input');
               
                newText8.setAttribute("type", "text");
                newText8.setAttribute("value", apart.lokacija.adresa.mesto);
                newText8.setAttribute("id", "value8" + apart.id);
               
                newText8.onkeyup = function () {
                    var txt8 = document.getElementById("value8" + apart.id).value;
                    if (txt8 === "") {
                        document.getElementById('save' + apart.id).disabled = true;
                        return;
                    }
                    if (txt8 !== apart.lokacija.adresa.mesto) {
                        document.getElementById('save' + apart.id).disabled = false;
                    }
                    else {
                        document.getElementById('save' + apart.id).disabled = true;
                    }
                };
                newCell8.appendChild(newText8);
                
                
                //kolona za ulicu
                var newCell9 = newRow.insertCell(7);
                var newText9 = document.createElement('input');
               
                newText9.setAttribute("type", "text");
                newText9.setAttribute("value", apart.lokacija.adresa.ulicaIbr);
                newText9.setAttribute("id", "value9" + apart.id);
               
                newText9.onkeyup = function () {
                    var txt9 = document.getElementById("value9" + apart.id).value;
                    if (txt9 === "") {
                        document.getElementById('save' + apart.id).disabled = true;
                        return;
                    }
                    if (txt9 !== apart.lokacija.adresa.ulicaIbr) {
                        document.getElementById('save' + apart.id).disabled = false;
                    }
                    else {
                        document.getElementById('save' + apart.id).disabled = true;
                    }
                };
                newCell9.appendChild(newText9);
                
                
                //kolona za postanski broj
                var newCell10 = newRow.insertCell(8);
                var newText10 = document.createElement('input');
               
                newText10.setAttribute("type", "text");
                newText10.setAttribute("value", apart.lokacija.adresa.postanskiBr);
                newText10.setAttribute("id", "value10" + apart.id);
               
                newText10.onkeyup = function () {
                    var txt10 = document.getElementById("value10" + apart.id).value;
                    if (txt10 === "") {
                        document.getElementById('save' + apart.id).disabled = true;
                        return;
                    }
                    if (txt10 !== apart.lokacija.adresa.postanskiBr) {
                        document.getElementById('save' + apart.id).disabled = false;
                    }
                    else {
                        document.getElementById('save' + apart.id).disabled = true;
                    }
                };
                newCell10.appendChild(newText10);
                
                
                //kolona za geografsku sirinu
                var newCell11 = newRow.insertCell(9);
                var newText11 = document.createElement('input');
               
                newText11.setAttribute("type", "text");
                newText11.setAttribute("value", apart.lokacija.geoSirina);
                newText11.setAttribute("id", "value11" + apart.id);
               
                newText11.onkeyup = function () {
                    var txt11 = document.getElementById("value11" + apart.id).value;
                    if (txt11 === "") {
                        document.getElementById('save' + apart.id).disabled = true;
                        return;
                    }
                    if (txt11 !== apart.lokacija.geoSirina) {
                        document.getElementById('save' + apart.id).disabled = false;
                    }
                    else {
                        document.getElementById('save' + apart.id).disabled = true;
                    }
                };
                newCell11.appendChild(newText11);
                
                
              //kolona za geografsku duzinu
                var newCell12 = newRow.insertCell(10);
                var newText12 = document.createElement('input');
               
                newText12.setAttribute("type", "text");
                newText12.setAttribute("value", apart.lokacija.geoDuzina);
                newText12.setAttribute("id", "value12" + apart.id);
               
                newText12.onkeyup = function () {
                    var txt12 = document.getElementById("value12" + apart.id).value;
                    if (txt12 === "") {
                        document.getElementById('save' + apart.id).disabled = true;
                        return;
                    }
                    if (txt12 !== apart.lokacija.geoDuzina) {
                        document.getElementById('save' + apart.id).disabled = false;
                    }
                    else {
                        document.getElementById('save' + apart.id).disabled = true;
                    }
                };
                newCell12.appendChild(newText12);
                
                //parametri objekta Apartman
                var ime = document.getElementById("value2" + apart.id).value;
                var tip = document.getElementById("value3" + apart.id).value;
                var aktivan = document.getElementById("value4" + apart.id).value;
                var cenaPoNoci = document.getElementById("value5" + apart.id).value;
                var brojSoba = document.getElementById("value6" + apart.id).value;
                var brojGostiju = document.getElementById("value7" + apart.id).value;
                var mesto = document.getElementById("value8" + apart.id).value;
                var ulicaIbr = document.getElementById("value9" + apart.id).value;
                var postanskiBr = document.getElementById("value10" + apart.id).value;
                var geoSirina = document.getElementById("value11" + apart.id).value;
                var geoDuzina = document.getElementById("value12" + apart.id).value;
                
                //objekti
                adresa = new Adresa(mesto, ulicaIbr, postanskiBr);
                lokacija = new Lokacija(adresa, geoSirina, geoDuzina);
                apartmanZaDomacinaDto = new ApartmanZaDomacinaDto(tip, ime, brojSoba, brojGostiju, lokacija, cenaPoNoci, aktivan);
                
                
                //kolona za dugme SAVE
                var newCell13 = newRow.insertCell(11);
                var editBtn = document.createElement('button');
                editBtn.innerHTML = "save";
                editBtn.id = "save" + apart.id;
                editBtn.disabled = "true"
                editBtn.onclick = function (e) {
                    e.preventDefault();
                   
                    $.ajax({
                        url:'rest/apartmans/izmeni/'+apart.id, 
                        type: 'PUT',
                        data: JSON.stringify({tip, ime, brojSoba, brojGostiju, lokacija, cenaPoNoci, aktivan}),
                        contentType: 'application/json',
                        success: function () {
                            location.reload();
                        }
                    });
                }
                newCell13.appendChild(editBtn);
                
                
                //kolona za dugme DELETE
                var newCell14 = newRow.insertCell(12);
                var dltBtn = document.createElement('button');
                dltBtn.innerHTML = "delete";
                dltBtn.id = apart.id;
                dltBtn.onclick = function (e) {
                    e.preventDefault();
                    $.ajax({
                        url: 'rest/apartmans/' + event.srcElement.id, //vratice id elementa na kom je izvrsen dogadjaj click()
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

class ApartmanZaDomacinaDto {

    constructor(tip, ime, brojSoba, brojGostiju, lokacija, cenaPoNoci, aktivan) {
        this.tip = tip
        this.ime = ime
        this.brojSoba
        this.brojGostiju
        this.lokacija
        this.cenaPoNoci
        this.aktivan
    }
}

class Adresa {
    constructor(mesto, ulicaIbr, postanskiBr) {
        this.mesto = mesto;
        this.ulicaIbr = ulicaIbr;
        this.postanskiBr = postanskiBr;
    }
}

class Lokacija {
    constructor(adresa, geoSirina, geoDuzina) {
        this.adresa = adresa
        this.geoSirina = geoSirina
        this.geoDuzina = geoDuzina
    }
}