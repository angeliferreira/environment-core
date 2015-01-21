package br.com.lemao.environment.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;

import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.lemao.environment.environments.BikersAndBikesEnvironmentSet;
import br.com.lemao.environment.junit.InMemoryRule;
import br.com.lemao.environment.model.bicycle.Bicycle;
import br.com.lemao.environment.model.bicycle.support.BicycleInMemorySupport;
import br.com.lemao.environment.model.biker.support.BikerInMemorySupport;

public class EnvironmentInMemoryTest {
	
	@Rule
	public InMemoryRule inMemoryRule = new InMemoryRule();
	
	@Test
	@GivenEnvironment(BikersAndBikesEnvironmentSet.TwoBikersWithBicyclesInMemory.class)
	public void thereAreTwoNamedBikersWithTwoBikes() {
		List<Bicycle> bicycles = BicycleInMemorySupport.findAll();
		assertThat(bicycles.size(), is(2));
		for (Bicycle bicycle : bicycles)
			assertNotNull(bicycle.getOwner());
	}

	@Test
	@GivenEnvironment(BikersAndBikesEnvironmentSet.TwoBikersInMemory.class)
	public void thereAreTwoNamedBikers() {
		assertThat(BikerInMemorySupport.findAll().size(), is(2));
	}

	@Test
	@GivenEnvironment(BikersAndBikesEnvironmentSet.TwoBikersWithOneBicycleInMemory.class)
	public void thereAreTwoNamedBikersWithOnlyOneBike() {
		assertThat(BikerInMemorySupport.findAll().size(), is(2));
		assertThat(BicycleInMemorySupport.findAll().size(), is(1));
	}

}
