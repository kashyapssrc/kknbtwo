var personDetails = function () {
    var httpRequest = new XMLHttpRequest();
    httpRequest.open('GET', 'assets/person.json', true);
    httpRequest.send();
    httpRequest.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var data = JSON.parse(this.responseText);  //parse the JSON
        data.push({        //add the employee
            firstname:"Mike",
            lastname:"Rut",
            email:"kar",
            birthdate:"12-12-1997",
            createddate:"12:12:12",
            admin:1
});
txt = JSON.stringify(data);

    }

    document.getElementById('person-details-info').innerHTML = txt;
};
}