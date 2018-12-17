var rsp = {};
rsp.view;
var addressView;


rsp.createChildren = function () {
}

rsp.createView = function () {
rsp.view = doGet('../html/rsp.html');
}

rsp.prePopulate = function () {
}

rsp.listenEvents = function () {
    eventManager.subscribe('entitySelected', onSelectEntity);
}

rsp.setDefaults = function () {
}

var onSelectEntity = function(entity) {

    if(entity == 'person') {
        personPanel.setView();
        document.getElementById('rspView').innerHTML = personPanel.personView;
        personPanel.init();
    } else {
        addressPanel.setView();
        document.getElementById('rspView').innerHTML = addressPanel.view;
        addressPanel.init();
    }
}
