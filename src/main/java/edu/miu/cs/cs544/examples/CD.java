package edu.miu.cs.cs544.examples;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CD extends Product {
	private String artist;

	public CD(String name, String description, String artist) {
		super(name, description);
		this.artist = artist;
	}
}
