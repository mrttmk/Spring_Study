package com.jutjoy.domain.service.news;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import com.jutjoy.domain.repository.NewsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NewsDeleteService {
	
	@Autowired
	private NewsRepository newsRepository;
	
	private final String FILE_PATH = "/Users/moritatomoki/Documents/Spring_Study/news";
	
	public void delete(Integer id) {
		newsRepository.deleteById(id);
		
		String dirPath = FILE_PATH + File.separator + id;
		File uploadDir = new File(dirPath);
		if (uploadDir.exists()) {
			FileSystemUtils.deleteRecursively(uploadDir);
		}
	}

}
