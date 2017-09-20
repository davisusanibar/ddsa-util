/**
 * Created by daviddali
 */

$(document).ready(function () {

    $("#listadoUserSecure").DataTable({
        "ajax": {
            "url": "http://localhost:28081/user/secure/all",
            "type": "GET",
            "dataSrc": ""
        },
        "columns": [
            { "data": "codigo" },
            { "data": "nonbre" },
            { "data": "apellido" },
            { "data": "edad" }
        ]
    })

    $("#listadoUserInsecure").DataTable({
        "ajax": {
            "url": "http://localhost:28081/user/insecure/all",
            "type": "GET",
            "dataSrc": ""
        },
        "columns": [
            { "data": "codigo" },
            { "data": "nonbre" },
            { "data": "apellido" },
            { "data": "edad" }
        ]
    })
});
