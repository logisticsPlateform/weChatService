package com.nyh.app.core.service.publish.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nyh.app.common.exception.SystemException;
import com.nyh.app.common.util.VoToPo;
import com.nyh.app.common.vo.publish.PublishVo;
import com.nyh.app.core.context.WebContext;
import com.nyh.app.core.orm.index.mapper.PubinfoMapper;
import com.nyh.app.core.orm.index.po.PubinfoPo;
import com.nyh.app.core.service.publish.UserPublishService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserPublishServiceImpl implements UserPublishService {
	
	@Autowired
	private PubinfoMapper  pubinfo;


	@Override
	public PublishVo saveimage(MultipartFile image){
		
		String userId = WebContext.getUserId();
		File file = new File("C://Users/Administrator/Desktop/tomcat8/webapps/ROOT/images/"+userId);
		boolean exists = file.exists();
		System.out.println(exists);
		if(!exists){
			boolean mkdirs = file.mkdirs();
			System.out.println(mkdirs);
		}
		//查询用户发布的信息个数 并创建新的文件夹存放图片
		int findCount = pubinfo.findCount();
		System.out.println(findCount);
		int dircount = findCount+1;
		System.out.println("dircount==============>"+dircount);
		File pub_dir = new File("C://Users/Administrator/Desktop/tomcat8/webapps/ROOT/images/"+userId+"/"+dircount);
		boolean mkdirs = pub_dir.exists();
		if (!mkdirs){
			mkdirs = pub_dir.mkdirs();
		}
		String absolutePath = pub_dir.getAbsolutePath();
		if(mkdirs){
			 try {
				 long currentTimeMillis = System.currentTimeMillis();
				 File imageFile = new File(absolutePath+"/"+currentTimeMillis+".jpg");
				 image.transferTo(imageFile);
				 PublishVo publishvo = new PublishVo();
				 String upload_image = "http://localhost:8888/images/"+userId+"/"
						 + dircount+"/"+currentTimeMillis+".jpg";
				 publishvo.setImageUrl(upload_image);
				 publishvo.setImageType("image/jpeg");
				 publishvo.setImageName(currentTimeMillis+".jpg");
				 int imageSize = (int)imageFile.length();
				 publishvo.setImageSize(imageSize);
				
				return publishvo;
			} catch (Exception e) {
				log.error("上传图片异常",e);
				throw new SystemException("系统繁忙");
				
			} 
		}
		return null;
		// file.transferTo(new File("C://Users/Administrator/Desktop/tomcat8/webapps/ROOT/qq.jpg")); 
		 
	}

	@Override
	public PublishVo savecontent(PublishVo pub) {
		// TODO Auto-generated method stub
		String userId = WebContext.getUserId();
		System.out.println("pub=======>"+pub);
		PubinfoPo pubinfoPo = new PubinfoPo();
		VoToPo.copyProperties(pubinfoPo, pub);
		pubinfoPo.setUserId(userId);
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String create_date = simpleDateFormat.format(date);
		pubinfoPo.setCreateDate(create_date);
		System.out.println("pubinfoPo=====>"+pubinfoPo);
		String imageUrl = pubinfoPo.getImageUrl();
		String sub_imageUrl = imageUrl.substring(1);
		pubinfoPo.setImageUrl(sub_imageUrl);
		
		pubinfo.insert_po(pubinfoPo);
		
		
		return null;
	}






}
