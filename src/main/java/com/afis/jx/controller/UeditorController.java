package com.afis.jx.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afis.jx.ueditor.MyActionEnter;

/**
 * 控制层 2016.11.23
 */
@RestController
@RequestMapping(value = "/ueditor")
@ConfigurationProperties
public class UeditorController {

	private String location;

	private String virtualPath;

	@RequestMapping(value = "/config")
	public void config(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		response.setContentType("application/json");
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("ueditor-config.json");
		String exec = new MyActionEnter(request, is, location, virtualPath).exec();
		PrintWriter writer = response.getWriter();
		writer.write(exec);
		writer.flush();
		writer.close();
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getVirtualPath() {
		return virtualPath;
	}

	public void setVirtualPath(String virtualPath) {
		this.virtualPath = virtualPath;
	}
}