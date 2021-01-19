$(document).ready(function () {
    loggedUser = null;
    $.get({
        url: '../rest/user/currUser',
        success: function (user) {
            loggedUser = user;
            generateToolBar(user);
            if (user == null || user.role != "admin") {
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
            url: '../rest/amenity?name=' + name,
            contentType: 'application/json',
            success: function (data) {
                alert("Amenity added")
                window.location = ''
            },
            error: function (ret) {
                alert(ret.responseText)
            }
        });
    });

    $.get({
        url: '../rest/amenity',
        success: function (data) {
            for (let amenity of data) {
                var tableRef = document.getElementById('amenities').getElementsByTagName('tbody')[0];
                var newRow = tableRef.insertRow();
                var newCell2 = newRow.insertCell(0);
                var newText2 = document.createElement('input');
                newText2.setAttribute("type", "text");
                newText2.setAttribute("value", amenity.name);
                newText2.setAttribute("id", "value" + amenity.id);
                newText2.onkeyup = function () {
                    var txt = document.getElementById("value" + amenity.id);
                    if (txt.value === "") {
                        document.getElementById('save' + amenity.id).disabled = true;
                        return;
                    }
                    if (txt.value !== amenity.name) {
                        document.getElementById('save' + amenity.id).disabled = false;
                    }
                    else {
                        document.getElementById('save' + amenity.id).disabled = true;
                    }
                };
                newCell2.appendChild(newText2);
                var newCell3 = newRow.insertCell(1);
                var editBtn = document.createElement('button');
                editBtn.innerHTML = "save";
                editBtn.id = "save" + amenity.id;
                editBtn.disabled = "true"
                editBtn.onclick = function (e) {
                    e.preventDefault();
                    var txt = document.getElementById("value" + amenity.id).value;
                    $.ajax({
                        url: '../rest/amenity?id=' + amenity.id + "&name=" + txt,
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
                dltBtn.id = amenity.id;
                dltBtn.onclick = function (e) {
                    e.preventDefault();
                    $.ajax({
                        url: '../rest/amenity?id=' + event.srcElement.id,
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
        if (user.role === "host") {
            var newApartment = document.createElement('a');
            newApartment.innerHTML = "Add apartment";
            newApartment.setAttribute('data-toggle', 'tooltip');
            newApartment.setAttribute('href', 'newApartment.html');
            newApartment.setAttribute('data-toggle', 'tooltip');
            document.getElementById("toolbar").appendChild(newApartment);
            $('#toolbar').append("<a href=\"userList.html\" id=\"userList\" data-toggle=\"tooltip\" title=\"toggle search bar\">User List</a>");
        } else if (user.role === "admin") {
            var amenities = document.createElement('a');
            amenities.innerHTML = "Amenities";
            amenities.setAttribute('data-toggle', 'tooltip');
            amenities.setAttribute('href', 'amenities.html');
            amenities.setAttribute('data-toggle', 'tooltip');
            document.getElementById("toolbar").appendChild(amenities);
            $('#toolbar').append("<a href=\"userList.html\" id=\"userList\" data-toggle=\"tooltip\" title=\"toggle search bar\">User List</a>");
            $('#toolbar').append("<a href=\"holidays.html\" id=\"holidays\" data-toggle=\"tooltip\" title=\"toggle search bar\">Holidays</a>");
        }

        $('#toolbar').append("<a href=\"reservations.html\" id=\"reservations\" data-toggle=\"tooltip\" title=\"toggle search bar\">Reservations</a>");

        if (user.role != "guest") {
            var comment = document.createElement('a');
            comment.innerHTML = "Comments";
            comment.setAttribute('href', 'comment.html');
            document.getElementById("toolbar").appendChild(comment);
        }

        var profile = document.createElement('a');
        profile.innerHTML = "Profile";
        profile.setAttribute('data-toggle', 'tooltip');
        profile.setAttribute('href', 'userProfile.html');
        profile.setAttribute('data-toggle', 'tooltip');
        document.getElementById("toolbar").appendChild(profile);

        var element = document.createElement('a');
        element.id = "logout";
        element.innerHTML = "Logout";
        element.onclick = logout;
        element.setAttribute('data-toggle', 'tooltip');
        element.setAttribute('href', 'login.html');
        document.getElementById("toolbar").appendChild(element);
    } else {
        $("#toolbar").append("<a href=\"login.html\" data-toggle=\"tooltip\" >Login</a><a href=\"register.html\" data-toggle=\"tooltip\">Register</a>");
    }
}

function logout() {
    return $.post({
        url: '../rest/user/logout',
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