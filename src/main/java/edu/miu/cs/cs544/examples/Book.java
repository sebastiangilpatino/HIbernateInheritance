package edu.miu.cs.cs544.examples;

import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book extends Product {
	private String title;

	public Book(String name, String description, String title) {
		super(name, description);
		this.title = title;
	}

}
