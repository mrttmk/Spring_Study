package com.jutjoy.domain.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.form.profile.ProfileCreateForm;
import com.jutjoy.domain.repository.ProfileRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProfileCreateService {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	public void create(ProfileCreateForm profileForm) {
		
		Profile profile = createProfile(profileForm);
	}
	
	private Profile createProfile(ProfileCreateForm profileForm) {
		
		Profile entity = new Profile();
		entity.setName(profileForm.getName());
		entity.setGender(profileForm.getGender());
		entity.setHobby(profileForm.getHobby());
		entity.setIntroduction(profileForm.getIntroduction());
		
		return profileRepository.save(entity);
		
	}

}
