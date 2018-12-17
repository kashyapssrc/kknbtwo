var personDetails = function () {
    var httpRequest = new XMLHttpRequest();
    httpRequest.open('GET', 'assets/person.json', true);
    httpRequest.send();

    httpRequest.onreadystatechange = function() {

    if (this.readyState == 4 && this.status == 200) {
            var personJson = JSON.parse(this.responseText);
            var x = null;
            var out = '';
            for (x in personJson) {
               out += personJson[x].firstname + ' '
                    + personJson[x].lastname + '<br>';
            }
        document.getElementById('person-details-info').innerHTML = out;
        }
    };
}
