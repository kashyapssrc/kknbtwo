var eventManager = {}
var listen = [];

eventManager.subscribe = function (eventName, action) {
    listen[eventName] = action;
    console.log(listen);
};

eventManager.broadcast = function (eventName, data) {
    var handler = listen[eventName];
    handler(data);
}

