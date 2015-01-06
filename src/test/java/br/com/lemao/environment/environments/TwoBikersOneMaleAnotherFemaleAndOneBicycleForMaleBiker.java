package br.com.lemao.environment.environments;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
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
	
	@Override
	public void beforeRun() {
		assertThat(bikerSupport.findAll(), hasSize(1));
		assertThat(bicycleSupport.findAll(), hasSize(1));
	}
	
	@Override
	public void afterRun() {
		assertThat(bikerSupport.findAll(), hasSize(2));
		assertThat(bicycleSupport.findAll(), hasSize(1));
	}
}