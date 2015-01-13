package br.com.lemao.environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.lemao.environment.environments.BikersAndBikesEnvironmentSet;
import br.com.lemao.environment.hibernate.HibernateUtil;
import br.com.lemao.environment.junit.EnvironmentRunner;
import br.com.lemao.environment.model.bicycle.Bicycle;
import br.com.lemao.environment.model.bicycle.BicycleSupport;
import br.com.lemao.environment.model.biker.BikerSupport;

@RunWith(EnvironmentRunner.class)
public class RunnerTest {
	
	@Test 
	@GivenEnvironment(BikersAndBikesEnvironmentSet.TwoBikersWithBicycles.class) 
	public void thereAreTwoNamedBikersWithTwoBikes() {
		List<Bicycle> bicycles = new BicycleSupport().findAll();
		for (Bicycle bicycle : bicycles) 
			assertNotNull(bicycle.getOwner());
	}
	
	@Test 
	@GivenEnvironment(BikersAndBikesEnvironmentSet.TwoBikers.class) 
	public void thereAreTwoNamedBikers() {
		BikerSupport support = new BikerSupport();
		assertEquals(2, support.findAll().size());
	}
	
	@Test 
	@GivenEnvironment(BikersAndBikesEnvironmentSet.TwoBikersWithOneBicycle.class)
	public void thereAreTwoNamedBikersWithOnlyOneBike() {
		BicycleSupport bicycleSupport = new BicycleSupport();
		BikerSupport bikerSupport = new BikerSupport();
		
		assertEquals(2, bikerSupport.findAll().size());
		assertEquals(1, bicycleSupport.findAll().size());
	}
	
	@AfterClass
	public static void tearDownHibernateEnvironment(){
		HibernateUtil.getCurrentSession().getTransaction().rollback();
	}
}
