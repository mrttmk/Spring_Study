package com.jutjoy.domain.entity.news;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name = "news")
@Data
@EntityListeners(AuditingEntityListener.class)
public class News {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "image_name")
	private String imageName;
	
	@CreatedDate
	@Column(name = "registered_date")
	private Timestamp registeredDate;
	
	@LastModifiedDate
	@Column(name = "updated_date")
	private Timestamp updatedDate;

}
