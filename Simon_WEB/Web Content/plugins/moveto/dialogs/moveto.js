/*
Marcos Godoy
*/
function showMoveOKMessage() {
		notimooNormalManager.show({
			title: 'Message',
			message: 'Text has been copied succesfully.',
			 sticky: true
		});
	}
	function showMoveErrorMessage(errText) {
		notimooErrorManager.show({
			title: 'Error',
			message: 'Text has not been copied. Error: ' + errText,
			customClass:'alert_error',
			 sticky: true
		});
	}

CKEDITOR.dialog.add( 'moveto', function( editor )
{
	return {
		title : 'Copy selected content to paragraph',
		minWidth : 400,
		minHeight : 200,
		contents : [
			{
				id : 'tab1',
				label : 'First Tab',
				title : 'First Tab',
				elements :
				[
					{
						id : 'input1',
						type : 'text',
						label : 'as paragraph'
					},
					{
						id : 'detail',
						type : 'text',
						label : 'Detail'
					},
						{
						id : 'input2',
						type : 'checkbox',
						label : 'Add to actual paragraph'
					}
				]
			}
		],
		buttons : [{
			type:'button',
			id:'moveButtonID', /* note: this is not the CSS ID attribute! */
			label: 'Copiar',
			onClick: function(event){
				var oEditor = CKEDITOR.instances.paragraphText;
				var temp = new CKEDITOR.dom.element( 'span' );
				var ranges = oEditor.getSelection().getRanges();
				temp.append(ranges[ 0 ].cloneContents());
				var newParagraphText = temp.getHtml();
				
				if (newParagraphText.length == 0) {
					notimooErrorManager.show({
						title: 'Error',
						message: 'There is no text selected to copy',
						customClass:'alert_error',
						sticky: true
					});
					this.getDialog().hide();
					return;
				}
				var pText = this.getDialog().getContentElement('tab1','input1').getValue();
				if (pText.length == 0) {
					notimooErrorManager.show({
						title: 'Error',
						message: 'Must indicate destination paragraph for the text',
						customClass:'alert_error',
						sticky: true
					});
					return;
				}
			
				//alert(moveToParagraphUrl);
			   var append = this.getDialog().getContentElement('tab1','input2').getValue();
			   var detail = this.getDialog().getContentElement('tab1','detail').getValue();
			   // aca hago todo el proceso si anda mal, muestro algo, si anda bien, muestro otra cosa
			   doMoveRequest(this.getDialog(), pText, detail, append, newParagraphText, oEditor, ranges);
			}
		},CKEDITOR.dialog.cancelButton],
	};
} );

function clearSelect(objSelect) {
	for (var i = objSelect.options.length; i >= 0; i--) {
		objSelect.options[i] = null;
	}
}

function setOptionsWithValue(objSelect, arr) {
	clearSelect(objSelect);
	for (var i =0; i < arr.length; i+=2) {
		var opt1 = document.createElement('OPTION');
		opt1.value = arr[i];
		opt1.text = arr[i+1];
		objSelect.options.add(opt1);
	}
}

function doMoveRequest(objDialog, pText, detail, append, newParagraphText, oEditor, ranges) {
 var jsonRequest = new Request.JSON({url: moveToParagraphUrl, 
 	onSuccess: 
 		function(json, responseText){
			var errorResult = json.error;
			if ('notLogged' == errorResult) {
				//window.location='<html:rewrite page="/login.jsp"/>';
				return;
			}
	    	var result = json.result;
		   if ('OK' == result) {
		   	oEditor.getSelection().selectRanges(ranges);
		   	showMoveOKMessage();
		    objDialog.hide();
			//oEditor.insertHtml('');
			document.getElementById('parDisplay').innerHTML = json.actualParagraph;
			var changeLast = json.changeLast;
			if ('true' == changeLast) {
				var nextSpanObj = document.getElementById('nextButtonLayer');
				var submits = nextSpanObj.getElementsByTagName("input");
				submits[0].disabled = false;
			}
			if (!append) {
				setOptionsWithValue(document.forms['CreateDocumentForm'].goToParagraph, json.paragraphs);
			}
		   } else {
		   	var error = json.error;
		   	oEditor.getSelection().selectRanges(ranges);
		   	showMoveErrorMessage(error);
		   }
		}
	}).post({'destination': pText, 'detail' : detail, 'append': append, 'newParagraphText': newParagraphText});
}