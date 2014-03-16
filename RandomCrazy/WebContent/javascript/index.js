/**
 * Function change selected object.
 */

function changeSelect(obj) {
	console.log(obj.options[obj.selectedIndex].value);
	var select = obj.options[obj.selectedIndex].value;
	if (select == 0)
		location.href = 'type/randomSimple.html';
	else if (select == 1)
		location.href = 'type/randomMultiple.html';
}

/**
 * Init document.
 */
window.onload = function(){document.getElementById("random_type").value="-1";};