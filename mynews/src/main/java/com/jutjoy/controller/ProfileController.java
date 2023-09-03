package com.jutjoy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jutjoy.domain.form.profile.ProfileCreateForm;
import com.jutjoy.domain.service.profile.ProfileCreateService;


@Controller
public class ProfileController {
	
	@GetMapping("/profile/create")
	public String create(@ModelAttribute("form") ProfileCreateForm profileCreateForm) {
		return "profile/create";
	}
	
	@Autowired
	private ProfileCreateService profileCreateService;
	
	@PostMapping("/profile/create")
	public String create(@Validated @ModelAttribute("form") ProfileCreateForm profileCreateForm,
			BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "profile/create";
		}
		
		profileCreateService.create(profileCreateForm);
		return "redirect:/profile/create/complete";
	}
	
	@GetMapping("/profile/create/complete")
	public String complete() {
		return "profile/complete";
	}
	

}
