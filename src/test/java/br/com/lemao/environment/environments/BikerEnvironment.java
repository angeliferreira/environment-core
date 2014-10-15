package br.com.lemao.environment.environments;

import br.com.lemao.environment.Environment;
import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.lemao.environment.model.bicycle.Bicycle;
import br.com.lemao.environment.model.bicycle.BicycleBuilder;
import br.com.lemao.environment.model.bicycle.BicycleSupport;
import br.com.lemao.environment.model.biker.Biker;
import br.com.lemao.environment.model.biker.BikerBuilder;
import br.com.lemao.environment.model.biker.BikerSupport;
import br.com.lemao.environment.model.gender.Gender;

public class BikerEnvironment extends Environment {
	
	protected BikerSupport bikerSupport = new BikerSupport();
	protected BicycleSupport bicycleSupport = new BicycleSupport();
	
	public static BicycleBuilder oneBicycle() {
		return new BicycleBuilder();
	}

	public static BikerBuilder oneBiker() {
		return new BikerBuilder();
	}
	
	public void twoBikersOneMaleAnotherFemale() {
		Biker zeBiker = oneBiker()
				.withName("Zé Grandão")
				.withGender(Gender.MALE)
				.gimme();
		bikerSupport.persist(zeBiker);
		
		Biker maricotinhaBiker = oneBiker()
				.withName("Maria Maricotinha")
				.withGender(Gender.FEMALE)
				.gimme();
		bikerSupport.persist(maricotinhaBiker);
	}
	
	@GivenEnvironment(value=BikerEnvironment.class, environmentName="twoBikersOneMaleAnotherFemale")
	public void twoBikersOneMaleAnotherFemaleWithBicycles() {
		Bicycle epicBicycle = oneBicycle()
				.forBiker(bikerSupport.findByName("Zé Grandão"))
				.withModelName("S-WORKS EPIC 29")
				.withSerialNumber(165487L)
				.gimme();
		bicycleSupport.persist(epicBicycle);
		
		Bicycle allezBicycle = oneBicycle()
				.forBiker(bikerSupport.findByName("Maria Maricotinha"))
				.withModelName("S-WORKS ALLEZ DI2")
				.withSerialNumber(98657L)
				.gimme();
		bicycleSupport.persist(allezBicycle);
	}

}
