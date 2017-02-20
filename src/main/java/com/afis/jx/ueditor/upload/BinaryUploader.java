package com.afis.jx.ueditor.upload;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.afis.jx.ueditor.PathFormat;
import com.afis.jx.ueditor.define.AppInfo;
import com.afis.jx.ueditor.define.BaseState;
import com.afis.jx.ueditor.define.FileType;
import com.afis.jx.ueditor.define.State;

public class BinaryUploader {

	public static final State save(HttpServletRequest request, Map<String, Object> conf) {
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartRequest.getFile(conf.get("fieldName").toString());

			String savePath = (String) conf.get("savePath");
			String originFileName = multipartFile.getOriginalFilename();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}
			savePath = PathFormat.parse(savePath, originFileName);
			System.out.println("savePath~~~~"+savePath);

			String[] savePathBySplit_temp = savePath.split("/");
			String temp = "";
			String fileName = savePathBySplit_temp[savePathBySplit_temp.length - 1];
			for (int i = 1; i < savePathBySplit_temp.length - 1; i++) {
				if (i != savePathBySplit_temp.length - 2) {
					temp += savePathBySplit_temp[i] + "/";
				} else {
					temp += savePathBySplit_temp[i];
				}
			}
			System.out.println("temp======"+temp);
//			String pathTemp = request.getSession().getServletContext().getRealPath(temp);
			String pathTemp = (String)conf.get("location")+temp;
			System.out.println("pathTemp++++++"+pathTemp);

			File targetFile = new File(pathTemp);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			State storageState = StorageManager.saveFileByInputStream(multipartFile.getInputStream(),
					pathTemp + "/" + fileName, maxSize);

			if (storageState.isSuccess()) {
				storageState.putInfo("url", (String)conf.get("virtualPath")+PathFormat.format(savePath));
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}

			return storageState;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}
}
