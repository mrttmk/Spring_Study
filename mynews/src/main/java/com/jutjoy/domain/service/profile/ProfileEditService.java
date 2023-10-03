package com.jutjoy.domain.service.profile;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.entity.profile.ProfileHistories;
import com.jutjoy.domain.form.profile.ProfileEditForm;
import com.jutjoy.domain.repository.ProfileRepository;
import com.jutjoy.domain.repository.ProfilesHistoriesRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Transactional
@Service
public class ProfileEditService {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	public void edit(int id, ProfileEditForm form) {
		
		Profile entity = profileRepository.findById(id).get();
		Profile profile = editProfile(entity, form);
		registerHistory(entity.getId());
		
    }
	
	public Profile findProfile(int id) {
		Profile profile = profileRepository.findById(id).get();
		return profile;
	}
	
	private Profile editProfile(Profile entity, ProfileEditForm form) {
		entity.setName(form.getName());
		entity.setGender(form.getGender());
		entity.setHobby(form.getHobby());
		entity.setIntroduction(form.getIntroduction());

		return profileRepository.save(entity);
	}
	
	@Autowired
	private ProfilesHistoriesRepository profilesHistoriesRepository;
	
	private void registerHistory(Integer id) {
		ProfileHistories entity = new ProfileHistories();
		entity.setProfilesId(id);
		profilesHistoriesRepository.save(entity);
	}
}


