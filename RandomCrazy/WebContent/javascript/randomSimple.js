/**
 * 
 */
function onLoadRandomSimple() {
	var type = new Array("SIMPLE",'LIST');
	var textResponse = ajaxFunctionServer("/RandomManager", type);
	var arrayResponse = textResponse.split("~");
	var divFather = document.getElementById("list_randoms");
	for ( var i = 0; i < arrayResponse.length; i++) {
		var divChild = document.createElement("div");
		divChild.setAttribute("id", arrayResponse[i]);
		divChild.setAttribute("onclick", "loadRandom(this);");
		divChild.setAttribute("class", "div_list_random");
		divChild.textContent = capitaliseFirstLetter(arrayResponse[i]);
		divFather.appendChild(divChild);
	}
}

/**
 * 
 * @param idSource
 */
function loadRandomSimple(idSource){
	var type = new Array("SIMPLE", "LOAD", idSource);
	var textResponse = ajaxFunctionServer("/RandomManager", type);
	var arrayResponse = textResponse.split("~");
	for ( var i = 0; i < arrayResponse.length; i++) {
		insertDataRow('name_insert','box_names', capitaliseFirstLetter(arrayResponse[i].toLowerCase()));
	}
}

/**
 * 
 */
function randomListSingle() {
	var box_list = document.getElementById("box_names").getElementsByTagName("div");
	var params = new Array();
	for ( var i = 0; i < box_list.length; i++)
		params[i] = box_list[i].textContent.replace((i + 1) + ". ", "");
	var response = ajaxFunctionServer("/RandomGenerator", params);
	document.getElementById("box_names").textContent = "";
	var tmp = document.getElementById("img_result");
	if (tmp != null)
		tmp.remove();
	var newDiv = document.createElement("div");
	var newPre = document.createElement("pre");
	newDiv.id = "img_result";
	newDiv.appendChild(newPre);
	newDiv.setAttribute("style", "width: 210px; height: 390px; margin: auto;");
	newPre.textContent = response;
	document.getElementById("box_result").appendChild(newDiv);
}

window.onload = onLoadRandomSimple;