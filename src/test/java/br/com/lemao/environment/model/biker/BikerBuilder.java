package br.com.lemao.environment.model.biker;

import br.com.lemao.environment.model.biker.Biker;
import br.com.lemao.environment.model.gender.Gender;

public class BikerBuilder {
	
	private String name;
	private Gender gender;
	
	public BikerBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public BikerBuilder withGender(Gender gender) {
		this.gender = gender;
		return this;
	}
	
	public Biker gimme() {
		Biker biker = new Biker();
		biker.setName(name);
		biker.setGender(gender);
		
		return biker;
	}

}
