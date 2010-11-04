/*
Marcos Godoy
*/
function showMoveOKMessage() {
		notimooNormalManager.show({
			title: 'Mensaje',
			message: 'El texto ha sido movido exitosamente.',
			 sticky: true
		});
	}
	function showMoveErrorMessage() {
		notimooErrorManager.show({
			title: 'Error',
			message: 'El texto no ha podido ser movido.',
			customClass:'alert_error',
			 sticky: true
		});
	}

CKEDITOR.dialog.add( 'moveto', function( editor )
{
	return {
		title : 'Mover contenido seleccionado a párrafo',
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
						label : 'Número de párrafo'
					},
						{
						id : 'input2',
						type : 'checkbox',
						label : 'incorporar'
					}
				]
			}
		],
		buttons : [{
			type:'button',
			id:'moveButtonID', /* note: this is not the CSS ID attribute! */
			label: 'Mover',
			onClick: function(event){
				var oEditor = CKEDITOR.instances.paragraphText;
				var temp = new CKEDITOR.dom.element( 'span' );
				var ranges = oEditor.getSelection().getRanges();
				temp.append(ranges[ 0 ].cloneContents());
				var newParagraphText = temp.getHtml();
				
				if (newParagraphText.length == 0) {
					notimooErrorManager.show({
						title: 'Error',
						message: 'No ha seleccionado nada para mover',
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
						message: 'Debe ingresar el párrafo destino',
						customClass:'alert_error',
						sticky: true
					});
					return;
				}
			
				//alert(moveToParagraphUrl);
			   var append = this.getDialog().getContentElement('tab1','input2').getValue();
			   // aca hago todo el proceso si anda mal, muestro algo, si anda bien, muestro otra cosa
			   doMoveRequest(this.getDialog(), pText, append, newParagraphText, oEditor, ranges);
			}
		},CKEDITOR.dialog.cancelButton],
	};
} );

function doMoveRequest(objDialog, pText, append, newParagraphText, oEditor, ranges) {
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
			oEditor.insertHtml('');
		   } else {
		   	var error = json.error;
		   	showMoveErrorMessage();
		   }
		}
	}).post({'destination': pText, 'append': append, 'newParagraphText': newParagraphText});
}