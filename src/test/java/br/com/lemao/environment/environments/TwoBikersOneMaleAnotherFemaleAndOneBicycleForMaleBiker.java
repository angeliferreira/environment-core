package br.com.lemao.environment.environments;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.lemao.environment.model.biker.Biker;
import br.com.lemao.environment.model.biker.BikerSupport;
import br.com.lemao.environment.model.gender.Gender;

public class TwoBikersOneMaleAnotherFemaleAndOneBicycleForMaleBiker extends	BikerEnvironment {
	
	@Override
	public void afterRun() {
		assertThat(new BikerSupport().findAll(), hasSize(2));
	}
	
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
		assertThat(new BikerSupport().findAll(), hasSize(1));
	}
}