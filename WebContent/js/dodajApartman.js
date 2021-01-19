vectorSource = null
Glongitude = ""
Glatitude = ""
$(document).ready(function () {
	
	initMap()

    counter = 0;
    var loggedUser = null;

    $.get({
        url: '../rest/users/ulogovan',
        success: function (user) {
            loggedUser = user;
            if (user == null) {
                window.location = "../";
                return;
            }
            if (user.uloga !== "DOMACIN") {
                window.location = "../";
                return;
            }

            initPage();

            $('#addApt').submit(function (event) {
                event.preventDefault();

                nameValidate();
                guestNoValidate();
                roomNoValidate();
                streetValidate();
                placeValidate();
                postalCodeValidate();
                latitudeValidate();
                longitudeValidate();
                priceValidate();
                dateValidate();

                if (!nameValidate()
                    || !guestNoValidate()
                    || !roomNoValidate()
                    || !streetValidate()
                    || !placeValidate()
                    || !postalCodeValidate()
                    || !latitudeValidate()
                    || !longitudeValidate()
                    || !dateValidate()
                    || !priceValidate()) {
                    return;
                }

                var ime = $("#name").val();
                var brojSoba = parseInt($("#roomCount").val());
                var brojGostiju = parseInt($("#guestCount").val());
                var cena = parseInt($("#price").val());
                var ulica = $("#street").val();
                var mesto = $("#place").val();
                var postanskiBroj = $("#postalCode").val();
                var geoSirina = parseFloat($("#geoWid").val());
                var geoDuzina = parseFloat($("#geoLen").val());
                var vremePrijave = $("#checkin").val();
                var vremeOdjave = $("#checkout").val();
                var pocetakDatum = $("#startRent").val();
                var krajDatum = $("#endRent").val();
                var tip = $("#aptType").val();
                var slike = [];

                var imgs = document.getElementById('imgs').getElementsByTagName('img');
                for (var i = 0; i < imgs.length; i++) {
                    slike.push(imgs[i].src)
                }

                var sadrzaji = [];

                var children = document.getElementById('amenities').getElementsByTagName('input'); //input
                for (var i = 0; i < children.length; i++) {
                    if (children[i].checked == true) {
                        sadrzaji.push(children[i].id);
                    }
                }

                adresa = new Adresa(ulica, mesto, postanskiBroj);
                lokacija = new Lokacija(geoSirina, geoDuzina, adresa)
                apartman = new Apartman(ime, brojSoba, brojGostiju, lokacija, cena, vremePrijave, vremeOdjave, pocetakDatum, krajDatum, slike, sadrzaji, tip);

                $.ajax({
                    url: '../rest/apartmans/',
                    contentType: "application/json",
                    data: JSON.stringify(apartman),
                    type: 'POST',
                    success: function () {
                        alert('Apartman je dodat!')
                   //     window.location = "ads.html"
                    },
                    error: function (f) {
                        alert(f.responseText)
                    }
                });
            });
        }
    });

    function nameValidate() {
        if ($("#name").val() === "") {
            document.getElementById("name").style.borderColor = "#FF0000";
            return false;
        } else {
            document.getElementById("name").style.borderColor = "#000000";
        }

        return true;
    }

    function guestNoValidate() {
        if ($("#guestCount").val() === "") {
            document.getElementById("guestCount").style.borderColor = "#FF0000";
            return false;
        } else {
            document.getElementById("guestCount").style.borderColor = "#000000";
        }

        var guestNo = $("#guestCount").val();

        if (guestNo < 1) {
            document.getElementById("guestCount").style.borderColor = "#FF0000";
            return false;
        } else {
            document.getElementById("guestCount").style.borderColor = "#000000";
        }

        return true;
    }

    function roomNoValidate() {
        if ($("#roomCount").val() === "") {
            document.getElementById("roomCount").style.borderColor = "#FF0000";
            return false;
        } else {
            document.getElementById("roomCount").style.borderColor = "#000000";
        }

        var roomNo = $("#roomCount").val();

        if (roomNo < 1) {
            document.getElementById("roomCount").style.borderColor = "#FF0000";
            return false;
        } else {
            document.getElementById("roomCount").style.borderColor = "#000000";
        }

        return true;
    }

    function streetValidate() {
        if ($("#street").val() === "") {
            document.getElementById("street").style.borderColor = "#FF0000";
            return false;
        } else {
            document.getElementById("street").style.borderColor = "#000000";
        }

        return true;
    }

    function placeValidate() {
        if ($("#place").val() === "") {
            document.getElementById("place").style.borderColor = "#FF0000";
            return false;
        } else {
            document.getElementById("place").style.borderColor = "#000000";
        }

        return true;
    }

    function postalCodeValidate() {
        if ($("#postalCode").val() === "") {
            document.getElementById("postalCode").style.borderColor = "#FF0000";
            return false;
        } else {
            document.getElementById("postalCode").style.borderColor = "#000000";
        }

        var postalCode = parseInt($("#postalCode").val());

        if (postalCode < 1) {
            document.getElementById("postalCode").style.borderColor = "#FF0000";
            return false;
        } else {
            document.getElementById("postalCode").style.borderColor = "#000000";
        }

        return true;
    }

    function latitudeValidate() {
        if ($("#geoWid").val() === "") {
            document.getElementById("geoWid").style.borderColor = "#FF0000";
            return false;
        } else {
            document.getElementById("geoWid").style.borderColor = "#000000";
        }

        var latitude = parseFloat($("#geoWid").val());
        if (isNaN(latitude)) {
            document.getElementById("geoWid").style.borderColor = "#FF0000";
            return false;
        } else {
            document.getElementById("geoWid").style.borderColor = "#000000";
        }

        return true;
    }

    function longitudeValidate() {
        if ($("#geoLen").val() === "") {
            document.getElementById("geoLen").style.borderColor = "#FF0000";
            return false;
        } else {
            document.getElementById("geoLen").style.borderColor = "#000000";
        }

        var longitude = parseFloat($("#geoLen").val());
        if (isNaN(longitude)) {
            document.getElementById("geoLen").style.borderColor = "#FF0000";
            return false;
        } else {
            document.getElementById("geoLen").style.borderColor = "#000000";
        }

        return true;
    }

    function priceValidate() {
        if ($("#price").val() === "") {
            document.getElementById("price").style.borderColor = "#FF0000";
            return false;
        } else {
            document.getElementById("price").style.borderColor = "#000000";
        }

        var price = parseFloat($("#price").val());
        if (isNaN(price)) {
            document.getElementById("price").style.borderColor = "#FF0000";
            return false;
        } else {
            document.getElementById("price").style.borderColor = "#000000";
        }

        return true;
    }

    function dateValidate() {
        var yyyy = parseInt($("#startRent").val().substr(0, 4));
        var mm = parseInt($("#startRent").val().substr(5, 7));
        var dd = parseInt($("#startRent").val().substr(8, 10));

        var yyyy2 = parseInt($("#endRent").val().substr(0, 4));
        var mm2 = parseInt($("#endRent").val().substr(5, 7));
        var dd2 = parseInt($("#endRent").val().substr(8, 10));

        if (yyyy2 < yyyy) {
            alert("godina")
            document.getElementById("endRent").style.borderColor = "#FF0000";
            return false;
        } else {
            document.getElementById("endRent").style.borderColor = "#000000";
        }

        if (mm2 < mm) {
            alert("mesec")
            document.getElementById("endRent").style.borderColor = "#FF0000";
            return false;
        } else {
            document.getElementById("endRent").style.borderColor = "#000000";
        }

        if (dd2 < dd) {
            document.getElementById("endRent").style.borderColor = "#FF0000";
            return false;
        } else {
            document.getElementById("endRent").style.borderColor = "#000000";
        }

        return true;
    }

    function initPage() {
        generateToolBar(loggedUser);

        $("#name").keyup(nameValidate);
        $("#guestCount").keyup(guestNoValidate);
        $("#roomCount").keyup(roomNoValidate);
        $("#street").keyup(streetValidate);
        $("#place").keyup(placeValidate);
        $("#postalCode").keyup(postalCodeValidate);
        $("#geoWid").keyup(latitudeValidate);
        $("#geoLen").keyup(longitudeValidate);
        $("#price").keyup(priceValidate);

        var today = new Date();
        var dd = String(today.getDate()).padStart(2, '0'); //getDate() vraca 1 ako je januar, a sa padStart vratice 01
        var mm = String(today.getMonth() + 1).padStart(2, '0');
        var yyyy = today.getFullYear();

        today = yyyy + '-' + mm + '-' + dd;

        document.getElementById('startRent').value = today;
        document.getElementById('startRent').min = today; //vraca minimalnu dozvoljenu vrednost za element
        document.getElementById('endRent').value = today;
        document.getElementById('endRent').min = today;

        $.get({
            url: '../rest/sadrzaj',
            success: function (sadrzaji) {
                for (let sadrzaj of sadrzaji) {
                    $('#amenities').append("<br/><input type=\"checkbox\" name=\"type\" id=\"" + sadrzaj.id + "\" class=\"agree-term\" /> <label for=\"" + sadrzaj.id + "\" class=\"label-agree-term\"><span><span></span></span>" + sadrzaj.naziv + "</label>");
                }
            }
        });
    }

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

 //       $('#toolbar').append("<a href=\"reservations.html\" id=\"reservations\" data-toggle=\"tooltip\" title=\"toggle search bar\">Reservations</a>");

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

        var element = document.createElement('a');
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

function showimg() {
    var reader = new FileReader();
    if (document.getElementById('img').files[0]) reader.readAsDataURL(document.getElementById('img').files[0]);

    reader.onloadend = function () {
        $('#imgs').append("<img src=\"" + reader.result + "\" id=\"img" + counter + "\">");
        $('#imgs').append("<button type=\"button\" onclick=\"removeimg(" + counter + ")\" style=\"background-color: #ff0000\" id=\"imgbutton" + counter + "\">Remove</button>");
        counter += 1;
    }
    document.getElementById('img').value = ""; //..
}

function removeimg(id) {
    document.getElementById("img" + id).remove();
    document.getElementById("imgbutton" + id).remove();
}

function logout() {
    return $.post({
        url: '../rest/users/logout',
        contentType: 'application/json',
        success: function () {
            localStorage.removeItem('jwt');
            window.location = 'login.html'
        }
    });
};

function initMap() {

    var map = new ol.Map({
        target: 'map', // id of the map div
        layers: [new ol.layer.Tile({
            source: new ol.source.OSM(),
        })],
        view: new ol.View({
            center: ol.proj.fromLonLat([19.83, 45.26]),
            zoom: 4
        })

    });

    map.on('singleclick', function(evt) {

        if (vectorSource != null && vectorSource != undefined)
            vectorSource.clear()

        console.log(evt.coordinate);
        // convert coordinate to EPSG-4326
        console.log(ol.proj.transform(evt.coordinate, 'EPSG:3857',
            'EPSG:4326'));


        var features = [];

        var item = ol.proj.transform(evt.coordinate, 'EPSG:3857',
            'EPSG:4326')
        var longitude = item[0];
        var latitude = item[1];
        Glongitude = item[0]
        Glatitude = item[1]
        
        $("#geoWid").val(Glatitude)
        $("#geoLen").val(Glongitude)
        

        var iconFeature = new ol.Feature({
            geometry: new ol.geom.Point(ol.proj.transform([longitude, latitude], 'EPSG:4326', 'EPSG:3857'))
        });

        var iconStyle = new ol.style.Style({
            image: new ol.style.Icon(({
                anchor: [0.5, 1],
                src: "http://cdn.mapmarker.io/api/v1/pin?text=P&size=50&hoffset=1"
            }))
        });

        iconFeature.setStyle(iconStyle);
        features.push(iconFeature);
        vectorSource = new ol.source.Vector({
            features: features
        });

        var vectorLayer = new ol.layer.Vector({
            source: vectorSource
        });
        map.addLayer(vectorLayer);
    });
}

class Apartman {
    constructor(ime, brojSoba, brojGostiju, lokacija, cena, vremePrijave, vremeOdjave, pocetakDatum, krajDatum, slike, sadrzaji, tip) {
        this.ime = ime
        this.brojSoba = brojSoba
        this.brojGostiju = brojGostiju
        this.lokacija = lokacija
        this.cena = cena
        this.vremePrijave = vremePrijave
        this.vremeOdjave = vremeOdjave
        this.pocetakDatum = pocetakDatum
        this.krajDatum = krajDatum
        this.slike = slike
        this.sadrzaji = sadrzaji
        this.tip = tip
    }
}

class Lokacija {
    constructor(geoSirina, geoDuzina, adresa) {
        this.geoSirina = geoSirina
        this.geoDuzina = geoDuzina
        this.adresa = adresa
    }
}

class Adresa {
    constructor(ulica, mesto, postanskiBroj) {
        this.ulica = ulica
        this.mesto = mesto
        this.postanskiBroj = postanskiBroj
    }
}