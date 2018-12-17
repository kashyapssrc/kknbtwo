var personPanel = {};
personPanel.personView;

personPanel.setView = function () {
    personPanel.personView = doGet('../html/person/panel.html');
}
personPanel.init = function () {
    personPanel.createChildren();
    personPanel.createView();
    personPanel.prePopulate();
    personPanel.listenEvents();
    personPanel.setDefaults();
}

personPanel.createChildren = function () {
    personListPanel.createChildren();
    personInformationPanel.createChildren();
}

personPanel.createView = function () {
    
    personListPanel.createView();
    document.getElementById('personPanel').innerHTML = personListPanel.view;
    personInformationPanel.createView();
    document.getElementById('personPanel').innerHTML += personInformationPanel.view;
}

personPanel.prePopulate = function () {
    personListPanel.prePopulate();
    personInformationPanel.prePopulate();
}

personPanel.listenEvents = function () {
    personListPanel.listenEvents();
    personInformationPanel.listenEvents();
}

personPanel.setDefaults = function () {
    personListPanel.setDefaults();
    personInformationPanel.setDefaults();
}
