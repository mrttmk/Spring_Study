package com.jutjoy.domain.form.news;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class NewsEditForm {
	
	@NotEmpty(message = "*タイトルは必ず入力してください。")
	@Size(max = 20, message = "タイトルは20文字以内で設定してください。")
	private String title;
	
	@NotEmpty(message = "*本文は必ず入力してください。")
	private String content;
	
	private MultipartFile image;
	
	private boolean imageRemove;

}
