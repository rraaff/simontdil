function doOperationSubmit(formName, operation) {
	document.forms[formName].operation.value = operation;
	document.forms[formName].submit();
}