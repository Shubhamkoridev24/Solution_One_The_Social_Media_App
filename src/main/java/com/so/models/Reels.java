package com.so.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reels {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer id;
	
	private String title;
	
	private String video;
	
	@ManyToOne
	private User user;
	
	
}
