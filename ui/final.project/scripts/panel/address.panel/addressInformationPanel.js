var addressInformationPanel = {};
addressInformationPanel.view;

addressInformationPanel.init = function () {
    addressInformationPanel.createChildren();
    addressInformationPanel.createView();
    addressInformationPanel.prePopulate();
    addressInformationPanel.listenEvents();
    addressInformationPanel.setDeafults();
}

addressInformationPanel.createChildren = function () {

}

addressInformationPanel.createView = function () {
    addressInformationPanel.view = doGet('../html/address/information/panel.html');
}

addressInformationPanel.prePopulate = function () {

}

addressInformationPanel.listenEvents = function () {

}

addressInformationPanel.setDeafults = function () {

}