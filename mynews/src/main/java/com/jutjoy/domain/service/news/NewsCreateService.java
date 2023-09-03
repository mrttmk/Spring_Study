package com.jutjoy.domain.service.news;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jutjoy.common.CommonConstant;
import com.jutjoy.domain.entity.news.News;
import com.jutjoy.domain.form.news.NewsCreateForm;
import com.jutjoy.domain.repository.NewsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NewsCreateService {
	
	@Autowired
	private NewsRepository newsRepository;
	
	public void create(NewsCreateForm form) {
		
		MultipartFile image = form.getImage();
		
		News news = createNews(form);
		
		if(!image.getOriginalFilename().isEmpty()) {
			String dirPath = CommonConstant.FILE_PATH + File.separator + news.getId();
			File uploadDir = new File(dirPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			
			try {
				String fullPath = uploadDir.getPath() + File.separator + image.getOriginalFilename();
				File imageFullPath = new File(fullPath);
				try(FileOutputStream fileOutputStream = new FileOutputStream(imageFullPath);
						BufferedOutputStream uploadFileStream = new BufferedOutputStream(fileOutputStream)) {
							
							byte[] bytes = image.getBytes();
							uploadFileStream.write(bytes);
							
						}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private News createNews(NewsCreateForm form) {
		
		News entity = new News();
		entity.setTitle(form.getTitle());
		entity.setContent(form.getContent());
		entity.setImageName(Objects.isNull(form.getImage()) ? null : form.getImage().getOriginalFilename());
		
		return newsRepository.save(entity);
	}

}
