package com.afis.jx.ueditor.hunter;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.afis.jx.ueditor.PathFormat;
import com.afis.jx.ueditor.define.AppInfo;
import com.afis.jx.ueditor.define.BaseState;
import com.afis.jx.ueditor.define.MultiState;
import com.afis.jx.ueditor.define.State;

public class FileManager {

	private String dir = null;
//	private String rootPath = null;
	private String location = null;
	private String virtualPath = null;
	private String[] allowFiles = null;
	private int count = 0;
	
	public FileManager ( Map<String, Object> conf ) {
//		this.rootPath = (String)conf.get( "rootPath" );
		this.location = (String)conf.get("location");
		this.virtualPath = (String) conf.get("virtualPath");
//		this.dir = this.rootPath + (String)conf.get( "dir" );
		this.dir = this.location + (String)conf.get( "dir" );
		
		this.allowFiles = this.getAllowFiles( conf.get("allowFiles") );
		this.count = (Integer)conf.get( "count" );
		
	}
	
	public State listFile ( int index ) {
		
		File dir = new File( this.dir );
		State state = null;

		if ( !dir.exists() ) {
			return new BaseState( false, AppInfo.NOT_EXIST );
		}
		
		if ( !dir.isDirectory() ) {
			return new BaseState( false, AppInfo.NOT_DIRECTORY );
		}
		
		Collection<File> list = FileUtils.listFiles( dir, this.allowFiles, true );
		
		if ( index < 0 || index > list.size() ) {
			state = new MultiState( true );
		} else {
			Object[] fileList = Arrays.copyOfRange( list.toArray(), index, index + this.count );
			state = this.getState( fileList );
		}
		
		state.putInfo( "start", index );
		state.putInfo( "total", list.size() );
		
		return state;
		
	}
	
	private State getState ( Object[] files ) {
		
		MultiState state = new MultiState( true );
		BaseState fileState = null;
		
		File file = null;
		
		for ( Object obj : files ) {
			if ( obj == null ) {
				break;
			}
			file = (File)obj;
			fileState = new BaseState( true );
			fileState.putInfo( "url", virtualPath+PathFormat.format( this.getPath( file ) ) );
			state.addState( fileState );
		}
		
		return state;
		
	}
	
	private String getPath ( File file ) {
		
		String path = file.getAbsolutePath();
		
//		return path.replace( this.rootPath, "/" );
		return path.replace( this.location, "/" );
		
	}
	
	private String[] getAllowFiles ( Object fileExt ) {
		
		String[] exts = null;
		String ext = null;
		
		if ( fileExt == null ) {
			return new String[ 0 ];
		}
		
		exts = (String[])fileExt;
		
		for ( int i = 0, len = exts.length; i < len; i++ ) {
			
			ext = exts[ i ];
			exts[ i ] = ext.replace( ".", "" );
			
		}
		
		return exts;
		
	}
	
}
