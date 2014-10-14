package br.com.lemao.environment;

import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.lemao.environment.junit.TransactionalRule;
import br.com.lemao.environment.model.bicycle.Bicycle;
import br.com.lemao.environment.model.bicycle.BicycleSupport;
import br.com.lemao.environment.model.biker.Biker;
import br.com.lemao.environment.model.biker.BikerSupport;
import br.com.lemao.environment.model.gender.Gender;

public class BikerTest {

	@Rule
	public TransactionalRule myRule = new TransactionalRule();
	
	private BikerSupport bikerSupport = new BikerSupport();
	private BicycleSupport bicycleSupport = new BicycleSupport();

	@Test
	@GivenEnvironment(BikerEnvironmentWithOneBikerAndOneBicycle.class)
	public void oneBikerAndOneBicycleCreatedByEnvironment() {
		List<Biker> bikers = bikerSupport.findAll();
		Assert.assertEquals(1, bikers.size());
		
		Biker biker = bikers.get(0);
		Assert.assertEquals(Gender.MALE, biker.getGender());
		Assert.assertEquals("Lemão", biker.getName());
		
		List<Bicycle> bicycles = bicycleSupport.findAll();
		Assert.assertEquals(1, bicycles.size());
		
		Bicycle bicycle = bicycles.get(0);
		Assert.assertEquals("S-WORKS EPIC 29", bicycle.getModelName());
		Assert.assertEquals(165487L, bicycle.getSerialNumber().longValue());
		Assert.assertEquals(biker, bicycle.getOwner());
	}
	
	@Test
	public void nothingCreatedBecauseThereIsNoEnvironment() {
		Assert.assertTrue(bikerSupport.findAll().isEmpty());
		Assert.assertTrue(bicycleSupport.findAll().isEmpty());
	}

}
