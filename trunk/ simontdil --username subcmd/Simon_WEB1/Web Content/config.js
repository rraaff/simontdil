/*
Copyright (c) 2003-2010, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	//config.enterMode = CKEDITOR.ENTER_DIV;
	config.enterMode = CKEDITOR.ENTER_BR;
	config.autoGrow_minHeight = 100;
	config.resize_minHeight = 100;
	config.forcePasteAsPlainText = true;
	config.extraPlugins = 'simonflags';
	config.simonflags_path = 'plugins/simonflags/images/'
};
