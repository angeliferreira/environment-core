package br.com.lemao.environment;

import static br.com.lemao.environment.environments.BikerEnvironment.oneBicycle;

import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.lemao.environment.annotation.IgnoreEnvironment;
import br.com.lemao.environment.environments.BikerEnvironment;
import br.com.lemao.environment.environments.OneMaleBikerAndOneBicycleForThisBiker;
import br.com.lemao.environment.environments.TwoBikersOneMaleAnotherFemaleAndOneBicycleForMaleBiker;
import br.com.lemao.environment.junit.TransactionalRule;
import br.com.lemao.environment.model.bicycle.Bicycle;
import br.com.lemao.environment.model.bicycle.BicycleSupport;
import br.com.lemao.environment.model.biker.Biker;
import br.com.lemao.environment.model.biker.BikerSupport;
import br.com.lemao.environment.model.gender.Gender;

@GivenEnvironment(OneMaleBikerAndOneBicycleForThisBiker.class)
public class BikerEnvironmentDataTest {

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
	public void nothingCreatedBecauseTheEnvironmentWasIgnored() {
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
	
	@Test
	@GivenEnvironment(value=BikerEnvironment.class, environmentName="twoBikersOneMaleAnotherFemale")
	public void twoBikersOneMaleAnotherFemaleCreatedByEnvironment() {
		List<Biker> bikers = bikerSupport.findAll();
		Assert.assertEquals(2, bikers.size());
		
		Biker zeBiker = bikerSupport.findByName("Zé Grandão");
		Assert.assertEquals(Gender.MALE, zeBiker.getGender());
		Assert.assertEquals("Zé Grandão", zeBiker.getName());
		
		Biker maricotinhaBiker = bikerSupport.findByName("Maria Maricotinha");
		Assert.assertEquals(Gender.FEMALE, maricotinhaBiker.getGender());
		Assert.assertEquals("Maria Maricotinha", maricotinhaBiker.getName());
	}
	
	@Test
	@GivenEnvironment(value=BikerEnvironment.class, environmentName="twoBikersOneMaleAnotherFemaleWithBicycles")
	public void twoBikersOneMaleAnotherFemaleWithBicyclesCreatedByEnvironment() {
		List<Biker> bikers = bikerSupport.findAll();
		Assert.assertEquals(2, bikers.size());
		
		Biker zeBiker = bikerSupport.findByName("Zé Grandão");
		Assert.assertEquals(Gender.MALE, zeBiker.getGender());
		Assert.assertEquals("Zé Grandão", zeBiker.getName());
		
		Biker maricotinhaBiker = bikerSupport.findByName("Maria Maricotinha");
		Assert.assertEquals(Gender.FEMALE, maricotinhaBiker.getGender());
		Assert.assertEquals("Maria Maricotinha", maricotinhaBiker.getName());
		
		List<Bicycle> bicycles = bicycleSupport.findAll();
		Assert.assertEquals(2, bicycles.size());
		
		Bicycle epicBicycle = bicycleSupport.findByModelName("S-WORKS EPIC 29");
		Assert.assertEquals("S-WORKS EPIC 29", epicBicycle.getModelName());
		Assert.assertEquals(165487L, epicBicycle.getSerialNumber().longValue());
		Assert.assertEquals(zeBiker, epicBicycle.getOwner());
		
		Bicycle allezBicycle = bicycleSupport.findByModelName("S-WORKS ALLEZ DI2");
		Assert.assertEquals("S-WORKS ALLEZ DI2", allezBicycle.getModelName());
		Assert.assertEquals(98657L, allezBicycle.getSerialNumber().longValue());
		Assert.assertEquals(maricotinhaBiker, allezBicycle.getOwner());
	}
	
	@Test
	@GivenEnvironment(value=BikerEnvironment.class, environmentName="twoBikersOneMaleAnotherFemale")
	public void twoBikersOneMaleAnotherFemaleWithBicyclesCreatedByTest() {
		List<Biker> bikers = bikerSupport.findAll();
		Assert.assertEquals(2, bikers.size());
		
		Biker zeBiker = bikerSupport.findByName("Zé Grandão");
		Assert.assertEquals(Gender.MALE, zeBiker.getGender());
		Assert.assertEquals("Zé Grandão", zeBiker.getName());
		
		Biker maricotinhaBiker = bikerSupport.findByName("Maria Maricotinha");
		Assert.assertEquals(Gender.FEMALE, maricotinhaBiker.getGender());
		Assert.assertEquals("Maria Maricotinha", maricotinhaBiker.getName());
		
		Assert.assertTrue(bicycleSupport.findAll().isEmpty());
		
		Bicycle epicBicycle = oneBicycle()
				.forBiker(zeBiker)
				.withModelName("S-WORKS EPIC 29")
				.withSerialNumber(165487L)
				.gimme();
		bicycleSupport.persist(epicBicycle);
		
		Bicycle allezBicycle = oneBicycle()
				.forBiker(maricotinhaBiker)
				.withModelName("S-WORKS ALLEZ DI2")
				.withSerialNumber(98657L)
				.gimme();
		bicycleSupport.persist(allezBicycle);
		
		List<Bicycle> bicycles = bicycleSupport.findAll();
		Assert.assertEquals(2, bicycles.size());
		
		epicBicycle = bicycleSupport.findByModelName("S-WORKS EPIC 29");
		Assert.assertEquals("S-WORKS EPIC 29", epicBicycle.getModelName());
		Assert.assertEquals(165487L, epicBicycle.getSerialNumber().longValue());
		Assert.assertEquals(zeBiker, epicBicycle.getOwner());
		
		allezBicycle = bicycleSupport.findByModelName("S-WORKS ALLEZ DI2");
		Assert.assertEquals("S-WORKS ALLEZ DI2", allezBicycle.getModelName());
		Assert.assertEquals(98657L, allezBicycle.getSerialNumber().longValue());
		Assert.assertEquals(maricotinhaBiker, allezBicycle.getOwner());
	}

}
