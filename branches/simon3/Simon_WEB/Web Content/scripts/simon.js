function doOperationSubmit(formName, operation) {
	document.forms[formName].operation.value = operation;
	document.forms[formName].submit();
}

function doIndexedOperationSubmit(formName, operation, indexClicked) {
	document.forms[formName].indexOperation.value = operation;
	document.forms[formName].indexClicked.value = indexClicked;
	document.forms[formName].submit();
}