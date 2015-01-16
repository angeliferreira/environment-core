package br.com.lemao.environment.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.lemao.environment.environments.BikersAndBikesEnvironmentSet;
import br.com.lemao.environment.junit.EnvironmentInMemoryRunner;
import br.com.lemao.environment.model.bicycle.Bicycle;
import br.com.lemao.environment.model.bicycle.BicycleInMemorySupport;
import br.com.lemao.environment.model.biker.BikerInMemorySupport;

@RunWith(EnvironmentInMemoryRunner.class)
public class EnvironmentInMemoryTest {
	
	private BikerInMemorySupport bikerInMemorySupport = BikerInMemorySupport.getInstance();
	private BicycleInMemorySupport bicycleInMemorySupport = BicycleInMemorySupport.getInstance();
	
	@Test
	@GivenEnvironment(BikersAndBikesEnvironmentSet.TwoBikersWithBicyclesInMemory.class)
	public void thereAreTwoNamedBikersWithTwoBikes() {
		List<Bicycle> bicycles = bicycleInMemorySupport.findAll();
		assertThat(bicycles.size(), is(2));
		for (Bicycle bicycle : bicycles)
			assertNotNull(bicycle.getOwner());
	}

	@Test
	@GivenEnvironment(BikersAndBikesEnvironmentSet.TwoBikersInMemory.class)
	public void thereAreTwoNamedBikers() {
		assertThat(bikerInMemorySupport.findAll().size(), is(2));
	}

	@Test
	@GivenEnvironment(BikersAndBikesEnvironmentSet.TwoBikersWithOneBicycleInMemory.class)
	public void thereAreTwoNamedBikersWithOnlyOneBike() {
		assertThat(bikerInMemorySupport.findAll().size(), is(2));
		assertThat(bicycleInMemorySupport.findAll().size(), is(1));
	}

}
