package br.com.lemao.environment.environments;

import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.lemao.environment.model.biker.Biker;
import br.com.lemao.environment.model.gender.Gender;

public class TwoBikersOneMaleAnotherFemaleAndOneBicycleForMaleBiker extends	BikerEnvironment {

	@Override
	@GivenEnvironment(OneMaleBikerAndOneBicycleForThisBiker.class)
	public void run() {
		Biker oliviaBiker = oneBiker()
				.withName("Olivia")
				.withGender(Gender.FEMALE)
				.gimme();
		bikerSupport.persist(oliviaBiker);
	}
	
}