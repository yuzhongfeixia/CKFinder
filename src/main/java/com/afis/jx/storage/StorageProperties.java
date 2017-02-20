package com.afis.jx.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Configuration
@ConfigurationProperties
public class StorageProperties {

    /**
     * 存储图片根目录
     */
    private String location;
    
    /**
     * 虚拟路径
     */
    private String virtualPath;
    
    /**
     * 文件大小
     */
    private String maxFileSize;

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

	public String getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(String maxFileSize) {
		this.maxFileSize = maxFileSize;
	}
}
