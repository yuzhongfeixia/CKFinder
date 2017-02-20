package com.afis.jx.storage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSystemStorageService implements StorageService {
	private final Path rootLocation;
	private final String location;
	private final String virtualPath;
	public String sp = System.getProperty("file.separator");

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.location = properties.getLocation();
		this.rootLocation = Paths.get(this.location);
		this.virtualPath = properties.getVirtualPath();
	}

	@Override
	public Map<String, String> store(MultipartFile file) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if (file.isEmpty()) {
				throw new StorageException("保存空文件失败 " + file.getOriginalFilename());
			}
			String originalFilename = file.getOriginalFilename();
			map.put("originalFilename", file.getOriginalFilename());
			String targetFileName = UUID.randomUUID().toString().replaceAll("-", "")
					+ originalFilename.substring(originalFilename.lastIndexOf("."));
			Files.copy(file.getInputStream(), this.rootLocation.resolve(targetFileName));
			map.put("targetName", virtualPath + targetFileName);
		} catch (IOException e) {
			throw new StorageException("存储文件失败 " + file.getOriginalFilename(), e);
		}
		return map;
	}

	@Override
	public String moveFile(String source, String path, String issueId) {
		if (!source.contains(path)) {// 不包含path，则表示新增图片，需要将图片拷贝到目标path下
			String fileName = source.substring(source.lastIndexOf("/") + 1);
			String targetPath = location + path + System.getProperty("file.separator") + issueId;
			File file = new File(targetPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				Files.move(Paths.get(location + fileName), Paths.get(targetPath).resolve(fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}

			return virtualPath + path + "/" + issueId + "/" + fileName;
		}
		return source;
	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("不能读取文件: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("不能读取文件: " + filename, e);
		}
	}
}
