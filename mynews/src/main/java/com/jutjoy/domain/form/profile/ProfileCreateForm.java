package com.jutjoy.domain.form.profile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProfileCreateForm {
	
	@NotEmpty(message = "*名前は必ず入力してください。")
	private String name;
	
	@NotNull(message = "*生別は必ず選択してください。")
	private String gender;
	
	@NotEmpty(message = "*趣味は必ず入力してください。")
	private String hobby;
	
	@NotEmpty(message = "*自己紹介は必ず入力してください。")
	private String introduction;

}
