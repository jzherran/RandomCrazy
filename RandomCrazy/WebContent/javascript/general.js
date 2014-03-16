/**
 * Function hover for display laber hidden.
 */

function insertDataRow(objSource, objDestiny, text) {
	if (text == null) {
		var input = document.getElementById(objSource);
		if (objSource != '' && objDestiny != '' && input.value != '') {
			var list = document.getElementById(objDestiny);
			var lengthList = list.getElementsByTagName('div').length;
			if (lengthList >= 0 && input) {
				var newDiv = document.createElement('div');
				var newImg = document.createElement("a");
				newImg.setAttribute("class", "delete_row");
				newImg.setAttribute("onclick", "deleteRowData(this);");
				newDiv.id = lengthList + "_name";
				newDiv.setAttribute('class', 'div_list_name');
				newDiv.textContent = (lengthList + 1) + ". " + input.value;
				newDiv.appendChild(newImg);
				list.appendChild(newDiv);
			}
			input.value = '';
		}
	} else {
		var list = document.getElementById(objDestiny);
		var lengthList = list.getElementsByTagName('div').length;
		if (lengthList >= 0 && text) {
			var newDiv = document.createElement('div');
			var newImg = document.createElement("a");
			newImg.setAttribute("class", "delete_row");
			newImg.setAttribute("onclick", "deleteRowData(this);");
			newDiv.id = lengthList + "_name";
			newDiv.setAttribute('class', 'div_list_name');
			newDiv.textContent = (lengthList + 1) + ". " + text;
			newDiv.appendChild(newImg);
			list.appendChild(newDiv);
		}
	}
}

function loadRandom(objSource) {
	document.getElementById("box_names").textContent = "";
	if (document.getElementById("randomType").value == '0') {
		loadRandomSimple(objSource.id);
	}
}

function loadListRandoms(objType) {

}

function showHover(objSource, objDestiny) {
	if (objSource != null) {
		if (objDestiny != null && (document.getElementById(objDestiny).style.display == "" || document.getElementById(objDestiny).style.display == "none")) {
			document.getElementById(objDestiny).style.display = "block";
		} else if (objDestiny != null && document.getElementById(objDestiny).style.display == "block") {
			document.getElementById(objDestiny).style.display = "none";
		}
	}
}

/**
 * Redirect to index application.
 */
function returnIndex() {
	location.href = '../index.html';
}

/**
 * Ajax function for sent to server
 */
function ajaxFunctionServer(servlet, params) {
	var ajaxRequest; // The variable that makes Ajax possible!
	try {
		// Opera 8.0+, Firefox, Safari
		ajaxRequest = new XMLHttpRequest();
	} catch (e) {
		// Internet Explorer Browsers
		try {
			ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				// Something went wrong
				console.log("Your browser broke!");
				return false;
			}
		}
	}
	// Create a function that will receive data sent from the server
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4) {
			return ajaxRequest.responseText;
		}
	};
	ajaxRequest.open("POST", ".." + servlet + "?params=" + params, false);
	ajaxRequest.setRequestHeader("Content-Type", "text/xml; charset=UTF-8");
	ajaxRequest.send();
	return ajaxRequest.responseText;
}

function run(e) {
	if (e.keyCode == 13) {
		insertDataRow('name_insert', 'box_names');
		return false;
	}
}

/**
 * Ajax function for sent to server
 */
function ajaxFunction() {
	var ajaxRequest; // The variable that makes Ajax possible!

	try {
		// Opera 8.0+, Firefox, Safari
		ajaxRequest = new XMLHttpRequest();
	} catch (e) {
		// Internet Explorer Browsers
		try {
			ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				// Something went wrong
				alert("Your browser broke!");
				return false;
			}
		}
	}
	// Create a function that will receive data sent from the server
	ajaxRequest.onreadystatechange = function() {
		if (ajaxRequest.readyState == 4) {
			var ajaxDisplay = document.getElementById('ajaxDiv');
			ajaxDisplay.innerHTML = ajaxRequest.responseText;
		}
	};
	ajaxRequest.open("POST", "../RandomGenerator" + queryString, true);
	ajaxRequest.send(null);
}

/**
 * 
 * @param objSource
 */
function deleteRowData(objSource) {
	console.log(objSource.parentNode);
	objSource.parentNode.remove();
	var list = document.getElementById("box_names").getElementsByTagName("div");
	for ( var i = 0; i < list.length; i++) {
		list[i].id = i+"_name";
		list[i].textContent = (i+1)+"."+list[i].textContent.split('.')[1];
		var newImg = document.createElement("a");
		newImg.setAttribute("class", "delete_row");
		newImg.setAttribute("onclick", "deleteRowData(this);");
		list[i].appendChild(newImg);
	}
}

/**
 * 
 * @param string
 * @returns
 */
function capitaliseFirstLetter(string) {
	return string.charAt(0).toUpperCase() + string.slice(1);
}