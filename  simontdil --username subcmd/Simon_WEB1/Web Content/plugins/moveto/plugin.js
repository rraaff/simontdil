/*
Marcos Godoy
*/

CKEDITOR.plugins.add( 'moveto',
{
	requires : [ 'dialog' ],

	init : function( editor )
	{
		editor.config.simonflags_path = editor.config.simonflags_path || ( this.path + 'images/' );
		editor.addCommand( 'moveto', new CKEDITOR.dialogCommand( 'moveto' ) );
		editor.ui.addButton( 'moveto',
			{
				label : 'Copiar a párrafo',
				command : 'moveto',
				icon: this.path + 'images/moveto.gif'

			});
		CKEDITOR.dialog.add( 'moveto', this.path + 'dialogs/moveto.js' );
	}
} );