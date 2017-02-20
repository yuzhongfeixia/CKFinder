package com.afis.jx.storage;

import java.nio.file.Path;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface StorageService {

	public Map<String, String> store(MultipartFile file);
	
	/**
	 * 将源文件copy到目标文件中,主要用在最后用户点提交的时候
	 * @param source
	 * @param path
	 * @param issueId
	 * @return
	 */
	public String moveFile(String source,String path ,String issueId);

	Path load(String filename);

	Resource loadAsResource(String filename);

}
