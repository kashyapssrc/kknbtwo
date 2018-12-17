var personListPanel = {};
personListPanel.view;
personListPanel.personList;
var personList;
personListPanel.personListItem;

personListPanel.init = function () {
    personListPanel.createChildren();
    personListPanel.createView();
    personListPanel.prePopulate();
    personListPanel.listenEvents();
    personListPanel.setDefaults();
}

personListPanel.createChildren = function () { }

personListPanel.createView = function () {
    personListPanel.view = doGet('../html/person/listPanel.html');
}

personListPanel.prePopulate = function () {

    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
            personList = JSON.parse(this.responseText);
            constructList(personList);
        }
    };
    httpRequest.open('GET', 'assets/person.json', false);
    httpRequest.send();
}

var constructList = function (data) {
    getPersonTemplate();
    var personValues = Object.keys(data[0]);
    var listItem = '';
    for (var i = 0; i < data.length; i++) {
        listItem += personListPanel.personListItem
                                   .replace(/%id%/, data[i][personValues[0]])
                                   .replace(/%firstname%/, data[i][personValues[1]])
                                   .replace(/%lastname%/, data[i][personValues[2]])
                                   .replace(/%email%/, data[i][personValues[3]])
                                   .replace(/%birthdate%/, data[i][personValues[4]])
                                   .replace(/%admin%/, data[i][personValues[5]]);
        }
    document.getElementById('personList').innerHTML += listItem;
}

var getPersonTemplate = function () {
personListPanel.personListItem = doGet('html/person/listItemPanel.html');
}

personListPanel.listenEvents = function () {
    document.getElementById('personList').addEventListener('click', onPopulate);
    document.getElementById('selectAdd').addEventListener('click', onSelectedAdd);
    eventManager.subscribe('submitSelected', addListItem);
}

var addListItem = function (item) {
    constructList(item);
}

personListPanel.setDefaults = function () {
    var table = document.getElementById('personList');
    eventManager.broadcast('itemSelected', table.rows[1]);
}

var onPopulate = function () {
    eventManager.broadcast('itemSelected', this);
}

var onSelectedAdd = function () {
    eventManager.broadcast('addSelected', '');
}