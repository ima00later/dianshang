package com.shopping.content.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.online.shopping.entity.Result;
import io.minio.MinioClient;
import io.minio.policy.PolicyType;


@RestController
public class UploadController {

	@Value("${minio.endpoint}")
	private String endpoint;     //http://192.168.1.5:9000 #MinIO服务所在地址
	@Value("${minio.bucketName}")
	private String bucketName;  // mall #存储桶名称
	@Value("${minio.accessKey}")
	private String accessKey;   //minioadmin #访问的key
	@Value("${minio.secretKey}")
	private String secretKey;   //minioadmin #访问的秘钥

	@RequestMapping("/uploadFile")
	public Map<String,Object> uploadFile(MultipartFile file){
		
		try {
			MinioClient minioClient = new MinioClient(endpoint, accessKey, secretKey);
			boolean isExist = minioClient.bucketExists(bucketName);
			
			if (!isExist) {
				minioClient.makeBucket(bucketName);
				minioClient.setBucketPolicy(bucketName, "*.*", PolicyType.READ_ONLY);
			}
			
			String filename = file.getOriginalFilename();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			//设置存储对象名称
			String objectName = sdf.format(new Date())+"/"+filename;
			//把存储对象上传到存储桶中
			minioClient.putObject(bucketName, objectName, file.getInputStream(), file.getContentType());
			System.out.println("文件上传成功");
			
			String objectUrl = minioClient.getObjectUrl(bucketName, objectName);
			System.out.println(objectUrl);
/*
			InputStream object = minioClient.getObject(bucketName, objectName);
			byte[] buf = new byte[32];
			String pic="";
			int len=-1;
			if((len=object.read(buf))!=-1){
				pic = new String(buf, 0, len);
				//System.out.println("pic===="+pic);
			}
*/

			HashMap<String, Object> result = new HashMap<>();

			result.put("message",objectUrl);//放图片文件
			//result.put("picname",objectUrl);//放图片地址
			result.put("success",true);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			HashMap<String, Object> result = new HashMap<>();
			result.put("success",false);
			result.put("message","存储出现异常");
			return result;
		}
	}
	
//	@RequestMapping("/uploadFile")
//	public Result uploadFile(MultipartFile file){
//		try {
//			//设置虚拟的映射路径 ---> D:/file
//			String path="D:/file";
//			String url = "";
//			if (file!=null && file.getSize()>0) {
//				file.transferTo(new File(path, file.getOriginalFilename()));
//				url = "http://localhost:8887/upload/"+file.getOriginalFilename();
//			}	
//			return new Result(true, url);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return new Result(false, "上传失败");
//		}		
//	}
	
//	@Value("${FILE_SERVER_URL}")
//	private String file_server_url;
//	
//	@RequestMapping("/uploadFile")
//	public Result uploadFile(MultipartFile file){
//		
//		try {
//			// 获得文件名:
//			String fileName = file.getOriginalFilename();
//			// 获得文件的扩展名:
//			String extName = fileName.substring( fileName.lastIndexOf(".")+1 );
//			// 创建工具类
//			FastDFSClient client = new FastDFSClient("classpath:fastDFS/fdfs_client.conf");
//			
//			String path = client.uploadFile(file.getBytes(), extName); // group1/M00/
//			
//			String url = file_server_url + path;
//			
//			return new Result(true, url);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new Result(false, "上传失败！");
//		}
//		
//	}
	
}
