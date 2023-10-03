package com.jutjoy.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jutjoy.domain.entity.profile.ProfileHistories;

@Repository
public interface ProfilesHistoriesRepository extends JpaRepository<ProfileHistories, Integer> {

}
