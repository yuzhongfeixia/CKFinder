/*
 * CKFinder
 * ========
 * http://cksource.com/ckfinder
 * Copyright (C) 2007-2015, CKSource - Frederico Knabben. All rights reserved.
 *
 * The software, this file and its contents are subject to the CKFinder
 * License. Please read the license.txt file before using, installing, copying,
 * modifying or distribute this file or part of its contents. The contents of
 * this file is part of the Source Code of CKFinder.
 */
package com.afis.jx.ckfinder.connector.plugins;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.afis.jx.ckfinder.connector.configuration.IConfiguration;
import com.afis.jx.ckfinder.connector.data.EventArgs;
import com.afis.jx.ckfinder.connector.data.IEventHandler;
import com.afis.jx.ckfinder.connector.data.InitCommandEventArgs;
import com.afis.jx.ckfinder.connector.data.PluginInfo;
import com.afis.jx.ckfinder.connector.data.PluginParam;
import com.afis.jx.ckfinder.connector.errors.ConnectorException;

public class ImageResizeInitCommandEventHandler implements IEventHandler {

	private PluginInfo pluginInfo;

	public ImageResizeInitCommandEventHandler(PluginInfo info) {
		this.pluginInfo = info;
	}

	@Override
	public boolean runEventHandler(EventArgs eventArgs, IConfiguration arg1)
		throws ConnectorException {
		InitCommandEventArgs args = (InitCommandEventArgs) eventArgs;
		NodeList list = args.getRootElement().getElementsByTagName("PluginsInfo");
		if (list.getLength() > 0) {
			Node node = list.item(0);
			Element pluginElem = args.getXml().getDocument().createElement(pluginInfo.getName());
			for (PluginParam param : pluginInfo.getParams()) {
				pluginElem.setAttribute(param.getName(), param.getValue());
			}
			node.appendChild(pluginElem);
		}
		return false;
	}
}
