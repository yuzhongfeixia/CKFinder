/*
Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
For licensing, see license.txt or http://cksource.com/ckfinder/license
*/

CKFinder.customConfig = function( config )
{
	config.filebrowserBrowseUrl = '/ckfinder/ckfinder.html';
    config.filebrowserImageBrowseUrl = '/ckfinder/ckfinder.html?type=Images';
    config.filebrowserFlashBrowseUrl = '/ckfinder/ckfinder.html?type=Flash';
    config.filebrowserjwplayerBrowseUrl = '/ckfinder/ckfinder.html?type=Vedio';
    config.filebrowserVedioBrowseUrl = '/ckfinder/ckfinder.html?type=Vedio';
    config.filebrowserUploadUrl = '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files';
    config.filebrowserImageUploadUrl = '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
    config.filebrowserFlashUploadUrl = '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';
    config.filebrowserjwplayerUploadUrl = '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Vedio';
    config.filebrowserVedioUploadUrl = '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Vedio';
    
    
    config.filebrowserWindowWidth = '1000';
    config.filebrowserWindowHeight = '700';
    config.language = "zh-cn";

};
