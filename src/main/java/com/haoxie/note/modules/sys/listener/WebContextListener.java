package com.haoxie.note.modules.sys.listener;

import javax.servlet.ServletContext;

import com.haoxie.note.modules.sys.service.SystemService;
import org.springframework.web.context.WebApplicationContext;

import com.haoxie.note.modules.sys.service.SystemService;

public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {
	
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		if (!SystemService.printKeyLoadMessage()){
			return null;
		}
		return super.initWebApplicationContext(servletContext);
	}
}
