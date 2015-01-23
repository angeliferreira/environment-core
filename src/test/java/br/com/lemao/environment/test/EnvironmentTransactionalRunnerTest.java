package br.com.lemao.environment.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.lemao.environment.environments.BikersAndBikesEnvironmentSet;
import br.com.lemao.environment.junit.InMemoryRunner;
import br.com.lemao.environment.model.bicycle.Bicycle;
import br.com.lemao.environment.model.bicycle.support.BicycleInMemorySupport;
import br.com.lemao.environment.model.biker.support.BikerInMemorySupport;

@RunWith(InMemoryRunner.class)
public class EnvironmentTransactionalRunnerTest {

	@Test
	@GivenEnvironment(BikersAndBikesEnvironmentSet.TwoBikersWithBicycles.class)
	public void thereAreTwoNamedBikersWithTwoBikes() {
		List<Bicycle> bicycles = BicycleInMemorySupport.findAll();
		for (Bicycle bicycle : bicycles)
			assertNotNull(bicycle.getOwner());
	}

	@Test
	@GivenEnvironment(BikersAndBikesEnvironmentSet.TwoBikers.class)
	public void thereAreTwoNamedBikers() {
		assertThat(BikerInMemorySupport.findAll().size(), is(2));
	}

	@Test
	@GivenEnvironment(BikersAndBikesEnvironmentSet.TwoBikersWithOneBicycle.class)
	public void thereAreTwoNamedBikersWithOnlyOneBike() {
		assertThat(BikerInMemorySupport.findAll().size(), is(2));
		assertThat(BicycleInMemorySupport.findAll().size(), is(1));
	}
}
