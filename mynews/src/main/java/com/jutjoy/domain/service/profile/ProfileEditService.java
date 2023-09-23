package com.jutjoy.domain.service.profile;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.form.profile.ProfileEditForm;
import com.jutjoy.domain.repository.ProfileRepository;

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
		
		/*String dirPath = File.separator + profile.getId();
		File uploadDir = new File(dirPath);
		
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		String fullPath = uploadDir.getPath() + File.separator;
		File afterImageFullPath = new File(fullPath);*/
    }
	
	public Profile findProfile(int id) {
		Profile profile = profileRepository.findById(id).get();
		return profile;
	}
	
	/*private void deleteFile(String imageName, File uploadDir) {
		if (!Objects.isNull(imageName) && uploadDir.exists()) {
			File imageFullPath = new File(uploadDir.getPath() + File.separator + imageName);
			imageFullPath.delete();
		}
	}*/
	
	private Profile editProfile(Profile entity, ProfileEditForm form) {
		entity.setName(form.getName());
		entity.setGender(form.getGender());
		entity.setHobby(form.getHobby());
		entity.setIntroduction(form.getIntroduction());

		return profileRepository.save(entity);
	}
}


