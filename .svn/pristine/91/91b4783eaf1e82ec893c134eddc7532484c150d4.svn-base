package com.shuaibi.zaizaigoods.util;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.shuaibi.zaizaicommons.entity.util.FileEntity;
import com.shuaibi.zaizaicommons.util.FileUtil;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 文件服务器上传类
 */
@Component
public class UploadUtil {

	@Autowired
	private FastFileStorageClient storageClient;

	@Autowired
	private ThumbImageConfig thumbImageConfig;

	/**
	 * Spring MVC文件上传,返回的是经过处理的path+fileName
	 *
	 * @param request request
	 * @param folder 上传文件夹
	 * @param userid 用户名
	 * @return
	 */
	public String upload(HttpServletRequest request, String folder, String userid) {
		FileUtil fileUtil = new FileUtil();
		String file_url = "";
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				String prefix = fileUtil.getFilePrefix(file);
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为"",说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						System.out.println(myFileName);
						// 重命名上传后的文件名
						String fileName = userid + "." + prefix;
						// 定义上传路径,格式为 upload/Amayadream/Amayadream.jpg
						String path = request.getServletContext().getRealPath("/") + folder + "/" + userid;
						File localFile = new File(path, fileName);
						if (!localFile.exists()) {
							localFile.mkdirs();
						}
						try {
							file.transferTo(localFile);
							file_url = folder + "/" + userid + "/" + fileName;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return file_url;
	}

	/**
	 * 上传文件到文件服务器
	 *
	 * @param multipartFile MultipartFile
	 * @return fileEntity 文件路径
	 * @throws FileNotFoundException Exceptiion
	 */
	public FileEntity upload(MultipartFile multipartFile) throws FileNotFoundException {
		File file = multipartFileToFile(multipartFile);
		StorePath storePath = storageClient.uploadImageAndCrtThumbImage(new FileInputStream(file),
				file.length(), "png", null);

		FileEntity fileEntity = new FileEntity();
		fileEntity.setPath("http://39.108.213.45:8888/group1/" + storePath.getPath());
		fileEntity.setFullPath("http://39.108.213.45:8888/" + storePath.getFullPath());
		fileEntity.setThumbPath("http://39.108.213.45:8888/group1/"
				+ thumbImageConfig.getThumbImagePath(storePath.getPath()));

		return fileEntity;
	}

	/**
	 * 上传文件到文件服务器
	 *
	 * @param multipartFiles MultipartFile[]
	 * @return fileEntity 文件路径
	 * @throws FileNotFoundException Exceptiion
	 */
	public List<FileEntity> batchUpload(MultipartFile[] multipartFiles) throws IOException {
		List<FileEntity> fileEntities = new ArrayList<>();
		for (MultipartFile multipartFile : multipartFiles) {
			FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
			StorePath storePath = storageClient.uploadImageAndCrtThumbImage(fileInputStream,
					multipartFile.getSize(), "png", null);
			FileEntity fileEntity = new FileEntity();
			fileEntity.setPath("http://39.108.213.45:8888/group1/" + storePath.getPath());
			fileEntity.setFullPath("http://39.108.213.45:8888/" + storePath.getFullPath());
			fileEntity.setThumbPath("http://39.108.213.45:8888/group1/"
					+ thumbImageConfig.getThumbImagePath(storePath.getPath()));

			fileEntities.add(fileEntity);
		}
		return fileEntities;
	}

	/**
	 * MultipartFile 转换为 File
	 * 
	 * @param multipartFile MultipartFile
	 * @return file File
	 */
	public File multipartFileToFile(MultipartFile multipartFile) {
		CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		File file = fi.getStoreLocation();
		return file;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileEntityList List<FileEntity>
	 */
	public void deleteFile(List<FileEntity> fileEntityList) {
		for (FileEntity fileEntity : fileEntityList) {
			storageClient.deleteFile(fileEntity.getPath());
		}
	}
}
