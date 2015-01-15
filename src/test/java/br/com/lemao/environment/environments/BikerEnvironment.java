package br.com.lemao.environment.environments;

import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.lemao.environment.model.bicycle.Bicycle;
import br.com.lemao.environment.model.biker.Biker;

public class BikerEnvironment extends AbstractBikerBicyclesEnvironment {
	
	public void twoBikersOneMaleAnotherFemale() {
		Biker zeBiker = oneBiker().withName("Zé Grandão").male().gimme();
		bikerSupport.persist(zeBiker);
		
		Biker maricotinhaBiker = oneBiker().withName("Maria Maricotinha").female().gimme();
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
