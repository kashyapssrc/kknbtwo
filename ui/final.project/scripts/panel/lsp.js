var lsp = {};
lsp.view;

lsp.createChildren = function () { }

lsp.createView = function () {

    lsp.view = doGet('../html/lsp.html');
}

lsp.prePopulate = function () {
}

lsp.listenEvents = function () {
    // document.getElementById(id).addEventListener(onClick, onEntitySelect);
    document.getElementById('person').addEventListener('click', onPersonSelect);
    document.getElementById('address').addEventListener('click', onAddressSelect);

}

lsp.setDefaults = function () {
    eventManager.broadcast('entitySelected', 'person');
}

var onPersonSelect = function () {
    eventManager.broadcast('entitySelected', 'person');
};

var onAddressSelect = function () {
    eventManager.broadcast('entitySelected', 'address');
};

