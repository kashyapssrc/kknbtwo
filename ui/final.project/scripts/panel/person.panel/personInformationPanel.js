var personInformationPanel = {};
var keyList = ['id', 'firstname', 'lastname', 'email', 'birthdate', 'admin'];
personInformationPanel.view;

personInformationPanel.init = function () {
    personInformationPanel.createChildren();
    personInformationPanel.createView();
    personInformationPanel.prePopulate();
    personInformationPanel.listenEvents();
    personInformationPanel.setDefaults();
}

personInformationPanel.createChildren = function () { }

personInformationPanel.createView = function () {
    personInformationPanel.view = doGet('../html/person/InformationPanel.html');
}

personInformationPanel.prePopulate = function () { }

personInformationPanel.listenEvents = function () {
    eventManager.subscribe('itemSelected', populateForm);
    eventManager.subscribe('addSelected', clearFileds);
    document.getElementById('formSubmit').addEventListener('click', onSelectSubmit);
}

personInformationPanel.setDefaults = function () {

}

var populateForm = function(tableRow) {
    for(i = 0; i< keyList.length; i++) {
        document.getElementById(keyList[i]).value = tableRow.cells[i].innerHTML;
    }
};


var clearFileds = function () {
    for(i = 0; i< keyList.length; i++) {
        document.getElementById(keyList[i]).value = '';
    }
};

var onSelectSubmit = function () {
    var temp = {};
    var person = [];
    for(i = 0; i< keyList.length; i++) {
        temp[keyList[i]] = document.getElementById(keyList[i]).value;
    }
    person.push(temp);
    eventManager.broadcast('submitSelected', person);
};