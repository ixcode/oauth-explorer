
function showElement(id) {
    var el = document.getElementById(id);
    el.setAttribute("class", "");
}
function hideElement(id) {
    var el = document.getElementById(id);
    el.setAttribute("class", "hidden");
}


function toggleControlsBasedOnFlow(flow) {
    console.log("Clicked! " + flow);

    if ("4-3" === flow) {
        showElement("resource-owner-creds");
    } else {
        hideElement("resource-owner-creds");
    }
}

function listenToRadios() {
    var radios = document.forms["authorization"].elements["grant-type"];

    for(var i = 0, max = radios.length; i < max; i++) {
        radios[i].onclick = function() {
            toggleControlsBasedOnFlow(this.value);
        }
    }
}

function initialise() {
    console.log("Initialising app...");
    listenToRadios();
}

window.onload = initialise;