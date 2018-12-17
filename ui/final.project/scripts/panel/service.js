var doGet = function (url) {
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
           response = this.responseText;
        }
    };
    httpRequest.open('GET', url, false);
    httpRequest.send();
    return response;
}