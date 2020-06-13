WEBSOCKET_URL = "ws://localhost:8080/AppStore_war_exploded/appStatus"
var userName = document.getElementById("username_holder").innerText.split(" ")[2]
var body = document.getElementById("app_status_body")
var webSocket = new WebSocket(WEBSOCKET_URL);
webSocket.onopen = function (message) {
    wsOpen(message);
};

function addApp(appData) {
    body.innerHTML += "<p>" + appData + "</p><br>";
}

function wsGetMessage(message) {
    body.innerHTML = ""
    var parts = message.data.split("\t")
    parts.forEach(addApp)
}

webSocket.onmessage = function (message) {
    wsGetMessage(message);
};

function wsOpen(message) {
    body.innerHTML = "<p> Websocket Connected</p>"
    webSocket.send(userName)
}