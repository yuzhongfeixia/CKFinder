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

import com.afis.jx.ckfinder.connector.configuration.Events;
import com.afis.jx.ckfinder.connector.configuration.Events.EventTypes;
import com.afis.jx.ckfinder.connector.configuration.Plugin;

public class ImageResize extends Plugin {

	@Override
	public void registerEventHandlers(Events events) {
		events.addEventHandler(EventTypes.BeforeExecuteCommand, ImageResizeCommad.class, pluginInfo);
		events.addEventHandler(EventTypes.BeforeExecuteCommand, ImageResizeInfoCommand.class);
		events.addEventHandler(EventTypes.InitCommand, ImageResizeInitCommandEventHandler.class, pluginInfo);

	}
}
