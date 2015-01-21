package br.com.lemao.environment.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.lemao.environment.environments.BikersAndBikesEnvironmentSet;
import br.com.lemao.environment.junit.EnvironmentTransactionalRunner;
import br.com.lemao.environment.model.bicycle.Bicycle;
import br.com.lemao.environment.model.bicycle.support.BicycleSupport;
import br.com.lemao.environment.model.biker.support.BikerSupport;

@RunWith(EnvironmentTransactionalRunner.class)
public class EnvironmentTransactionalRunnerTest {

	private BikerSupport bikerSupport = BikerSupport.getInstance();
	private BicycleSupport bicycleSupport = BicycleSupport.getInstance();
	
	@Test
	@GivenEnvironment(BikersAndBikesEnvironmentSet.TwoBikersWithBicycles.class)
	public void thereAreTwoNamedBikersWithTwoBikes() {
		List<Bicycle> bicycles = bicycleSupport.findAll();
		for (Bicycle bicycle : bicycles)
			assertNotNull(bicycle.getOwner());
	}

	@Test
	@GivenEnvironment(BikersAndBikesEnvironmentSet.TwoBikers.class)
	public void thereAreTwoNamedBikers() {
		assertThat(bikerSupport.findAll().size(), is(2));
	}

	@Test
	@GivenEnvironment(BikersAndBikesEnvironmentSet.TwoBikersWithOneBicycle.class)
	public void thereAreTwoNamedBikersWithOnlyOneBike() {
		assertThat(bikerSupport.findAll().size(), is(2));
		assertThat(bicycleSupport.findAll().size(), is(1));
	}
}
