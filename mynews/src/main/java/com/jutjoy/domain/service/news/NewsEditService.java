package com.jutjoy.domain.service.news;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jutjoy.domain.entity.news.News;
import com.jutjoy.domain.entity.news.NewsHistories;
import com.jutjoy.domain.form.news.NewsEditForm;
import com.jutjoy.domain.repository.NewsHistoriesRepository;
import com.jutjoy.domain.repository.NewsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
@Service
public class NewsEditService {
	
	@Autowired
	private NewsRepository newsRepository;
	
	@Autowired
	private NewsHistoriesRepository newsHistoriesRepository;
	
	private final String FILE_PATH = "/Users/moritatomoki/Documents/Spring_Study/news";
	
	public void edit (int id, NewsEditForm form) {
		MultipartFile image = form.getImage();
		
		News entity = newsRepository.findById(id).get();
		String beforeImageName = entity.getImageName();
		
		News news = editNews(entity, form);
		
		registerHistory(entity.getId());
		
		try {
			String dirPath = FILE_PATH + File.separator + news.getId();
			File uploadDir = new File(dirPath);
			
			if (form.isImageRemove()) {
				deleteFile(beforeImageName, uploadDir);
			}
			
			if (!image.getOriginalFilename().isEmpty()) {
				deleteFile(beforeImageName, uploadDir);
				
				if (!uploadDir.exists()) {
					uploadDir.mkdirs();
				}
				
				String fullPath = uploadDir.getPath() + File.separator + image.getOriginalFilename();
				File afterImageFullPath = new File(fullPath);
				try (FileOutputStream fileOutputStream = new FileOutputStream(afterImageFullPath);
						BufferedOutputStream uploadFileStream = new BufferedOutputStream(fileOutputStream)){
					byte[] bytes = image.getBytes();
					uploadFileStream.write(bytes);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public News findNews(int id) {
		News news = newsRepository.findById(id).get();
		return news;
	}
	
	private void deleteFile(String imageName, File uploadDir) {
		if (!Objects.isNull(imageName) && uploadDir.exists()) {
			File imageFullPath = new File(uploadDir.getPath() + File.separator + imageName);
			imageFullPath.delete();
		}
	}
	
	private News editNews(News entity, NewsEditForm form) {
		entity.setTitle(form.getTitle());
		entity.setContent(form.getContent());
		if (!Objects.isNull(form.getImage())) {
			entity.setImageName(form.getImage().getOriginalFilename());
		} else if (form.isImageRemove()) {
			entity.setImageName(null);
		}
		return newsRepository.save(entity);
	}
	
	/*@Autowired
	private NewsHistoriesRepository newsHistoriesRepository;*/
	
	private void registerHistory(Integer id) {
		NewsHistories entity = new NewsHistories();
		entity.setNewsId(id);
		newsHistoriesRepository.save(entity);
	}

}
