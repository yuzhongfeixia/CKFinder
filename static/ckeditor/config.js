/**
 * @license Copyright (c) 2003-2016, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here.
	// For complete reference see:
	// http://docs.ckeditor.com/#!/api/CKEDITOR.config

	// The toolbar groups arrangement, optimized for two toolbar rows.
	config.toolbarGroups = [
		{ name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
		{ name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
		{ name: 'links' },
		{ name: 'insert' },
		{ name: 'forms' },
		{ name: 'tools' },
		{ name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'others' },
		'/',
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
		{ name: 'styles' },
		{ name: 'colors' },
		{ name: 'about' }
	];

	// Remove some buttons provided by the standard plugins, which are
	// not needed in the Standard(s) toolbar.
	config.removeButtons = 'Underline,Subscript,Superscript';

	// Set the most common block elements.
	config.format_tags = 'p;h1;h2;h3;pre';

	// Simplify the dialog windows.
	config.removeDialogTabs = 'image:advanced;link:advanced';
};


//CKEDITOR.editorConfig = function( config ) {
//     config.toolbarGroups = [
//         { name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
//         { name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
//         { name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
//         { name: 'forms', groups: [ 'forms' ] },
//         '/',
//         { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
//         { name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
//         { name: 'links', groups: [ 'links' ] },
//         { name: 'insert', groups: [ 'insert' ] },
//         '/',
//         { name: 'styles', groups: [ 'styles' ] },
//         { name: 'colors', groups: [ 'colors' ] },
//         { name: 'tools', groups: [ 'tools' ] },
//         { name: 'others', groups: [ 'others' ] },
//         { name: 'about', groups: [ 'about' ] }
//     ];
// 
////     config.removeButtons = 'Source,Save,NewPage,Scayt';
////     config.image_previewText=' ';     //预览区域显示内容
//     config.filebrowserBrowseUrl ='/ckfinder/ckfinder.html';   
//     // 上传图片时浏览服务文件夹                                                                                                        
//     config.filebrowserImageBrowseUrl ='/ckfinder/ckfinder.html?Type=Images';                                          
//     // 上传Flash时浏览服务文件夹                                                                                                     
//     config.filebrowserFlashBrowseUrl ='/ckfinder/ckfinder.html?Type=Flash';                                           
//     // 上传文件按钮(标签)                                                                                                         
//     config.filebrowserUploadUrl ='/ckfinder/core/connector/aspx/connector.aspx?command=QuickUpload&type=Files';       
//     // 上传图片按钮(标签)                                                                                                         
//     config.filebrowserImageUploadUrl ='/ckfinder/core/connector/aspx/connector.aspx?command=QuickUpload&type=Images';  
//     // 上传Flash按钮(标签)                                                                                                      
//     config.filebrowserFlashUploadUrl ='/ckfinder/core/connector/aspx/connector.aspx?command=QuickUpload&type=Flash';  
//};