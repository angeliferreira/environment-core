package br.com.lemao.environment;

import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.lemao.environment.annotation.IgnoreEnvironment;
import br.com.lemao.environment.junit.TransactionalRule;
import br.com.lemao.environment.model.bicycle.Bicycle;
import br.com.lemao.environment.model.bicycle.BicycleSupport;
import br.com.lemao.environment.model.biker.Biker;
import br.com.lemao.environment.model.biker.BikerSupport;
import br.com.lemao.environment.model.gender.Gender;

@GivenEnvironment(OneMaleBikerAndOneBicycleForThisBiker.class)
public class BikerTest {

	@Rule
	public TransactionalRule myRule = new TransactionalRule();
	
	private BikerSupport bikerSupport = new BikerSupport();
	private BicycleSupport bicycleSupport = new BicycleSupport();

	@Test
	public void oneBikerAndOneBicycleForThisBikerCreatedByEnvironment() {
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
	@IgnoreEnvironment
	public void nothingCreatedBecauseThereIsNoEnvironment() {
		Assert.assertTrue(bikerSupport.findAll().isEmpty());
		Assert.assertTrue(bicycleSupport.findAll().isEmpty());
	}
	
	@Test
	@GivenEnvironment(TwoBikersOneMaleAnotherFemaleAndOneBicycleForMaleBiker.class)
	public void twoBikersOneMaleAnotherFemaleAndOneBicycleForMaleBikerCreatedByEnvironment() {
		List<Biker> bikers = bikerSupport.findAll();
		Assert.assertEquals(2, bikers.size());
		
		Biker lemaoBiker = bikerSupport.findByName("Lemão");
		Assert.assertEquals(Gender.MALE, lemaoBiker.getGender());
		Assert.assertEquals("Lemão", lemaoBiker.getName());
		
		Biker oliviaBiker = bikerSupport.findByName("Olivia");
		Assert.assertEquals(Gender.FEMALE, oliviaBiker.getGender());
		Assert.assertEquals("Olivia", oliviaBiker.getName());
		
		List<Bicycle> bicycles = bicycleSupport.findAll();
		Assert.assertEquals(1, bicycles.size());
		
		Bicycle bicycle = bicycles.get(0);
		Assert.assertEquals("S-WORKS EPIC 29", bicycle.getModelName());
		Assert.assertEquals(165487L, bicycle.getSerialNumber().longValue());
		Assert.assertEquals(lemaoBiker, bicycle.getOwner());
	}

}
