var addressPanel = {};
addressPanel.view;

addressPanel.setView = function () {
    addressPanel.view = doGet('../html/address/panel.html');
}

addressPanel.init = function () {
    addressPanel.createChildren();
    addressPanel.createView();
    addressPanel.prePopulate();
    addressPanel.listenEvents();
    addressPanel.setDeafults();
}

addressPanel.createChildren = function () {
    addressListPanel.createChildren();
    addressInformationPanel.createChildren();
}

addressPanel.createView = function () {
    addressListPanel.createView();
    document.getElementById('addressPanel').innerHTML = addressListPanel.view;
    addressInformationPanel.createView();
    document.getElementById('addressPanel').innerHTML += addressInformationPanel.view;
    
}

addressPanel.prePopulate = function () {
    addressListPanel.prePopulate();
    addressInformationPanel.prePopulate();
}

addressPanel.listenEvents = function () {
    addressListPanel.listenEvents();
    addressInformationPanel.listenEvents();
}

addressPanel.setDeafults = function () {
    addressListPanel.setDeafults();
    addressInformationPanel.setDeafults();
}
