package br.com.lemao.environment.test;

import static br.com.lemao.environment.environments.BikerEnvironment.oneBicycle;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
	
	private BikerSupport bikerSupport = BikerSupport.getInstance();
	private BicycleSupport bicycleSupport = BicycleSupport.getInstance();

	@Test
	public void oneBikerAndOneBicycleForThisBikerCreatedByEnvironment() {
		List<Biker> bikers = bikerSupport.findAll();
		assertThat(bikers.size(), is(1));
		
		Biker biker = bikers.get(0);
		assertThat(biker.getGender(), is(Gender.MALE));
		assertThat(biker.getName(), is("Lemão"));
		
		List<Bicycle> bicycles = bicycleSupport.findAll();
		assertThat(bicycles.size(), is(1));
		
		Bicycle bicycle = bicycles.get(0);
		assertThat(bicycle.getModelName(), is("S-WORKS EPIC 29"));
		assertThat(bicycle.getSerialNumber(), is(165487L));
		assertThat(bicycle.getOwner(), is(biker));
	}
	
	@Test
	@IgnoreEnvironment
	public void nothingCreatedBecauseTheEnvironmentWasIgnored() {
		assertTrue(bikerSupport.findAll().isEmpty());
		assertTrue(bicycleSupport.findAll().isEmpty());
	}
	
	@Test
	@GivenEnvironment(TwoBikersOneMaleAnotherFemaleAndOneBicycleForMaleBiker.class)
	public void twoBikersOneMaleAnotherFemaleAndOneBicycleForMaleBikerCreatedByEnvironment() {
		List<Biker> bikers = bikerSupport.findAll();
		assertThat(bikers.size(), is(2));
		
		Biker lemaoBiker = bikerSupport.findByName("Lemão");
		assertThat(lemaoBiker.getGender(), is(Gender.MALE));
		assertThat(lemaoBiker.getName(), is("Lemão"));
		
		Biker oliviaBiker = bikerSupport.findByName("Olivia");
		assertThat(oliviaBiker.getGender(), is(Gender.FEMALE));
		assertThat(oliviaBiker.getName(), is("Olivia"));
		
		List<Bicycle> bicycles = bicycleSupport.findAll();
		assertThat(bicycles.size(), is(1));
		
		Bicycle bicycle = bicycles.get(0);
		assertThat(bicycle.getModelName(), is("S-WORKS EPIC 29"));
		assertThat(bicycle.getSerialNumber(), is(165487L));
		assertThat(bicycle.getOwner(), is(lemaoBiker));
	}
	
	@Test
	@GivenEnvironment(value=BikerEnvironment.class, environmentName="twoBikersOneMaleAnotherFemale")
	public void twoBikersOneMaleAnotherFemaleCreatedByEnvironment() {
		List<Biker> bikers = bikerSupport.findAll();
		assertThat(bikers.size(), is(2));
		
		Biker zeBiker = bikerSupport.findByName("Zé Grandão");
		assertThat(zeBiker.getGender(), is(Gender.MALE));
		assertThat(zeBiker.getName(), is("Zé Grandão"));
		
		Biker maricotinhaBiker = bikerSupport.findByName("Maria Maricotinha");
		assertThat(maricotinhaBiker.getGender(), is(Gender.FEMALE));
		assertThat(maricotinhaBiker.getName(), is("Maria Maricotinha"));
	}
	
	@Test
	@GivenEnvironment(value=BikerEnvironment.class, environmentName="twoBikersOneMaleAnotherFemaleWithBicycles")
	public void twoBikersOneMaleAnotherFemaleWithBicyclesCreatedByEnvironment() {
		List<Biker> bikers = bikerSupport.findAll();
		assertThat(bikers.size(), is(2));
		
		Biker zeBiker = bikerSupport.findByName("Zé Grandão");
		assertThat(zeBiker.getGender(), is(Gender.MALE));
		assertThat(zeBiker.getName(), is("Zé Grandão"));
		
		Biker maricotinhaBiker = bikerSupport.findByName("Maria Maricotinha");
		assertThat(maricotinhaBiker.getGender(), is(Gender.FEMALE));
		assertThat(maricotinhaBiker.getName(), is("Maria Maricotinha"));
		
		List<Bicycle> bicycles = bicycleSupport.findAll();
		assertThat(bicycles.size(), is(2));
		
		Bicycle epicBicycle = bicycleSupport.findByModelName("S-WORKS EPIC 29");
		assertThat(epicBicycle.getModelName(), is("S-WORKS EPIC 29"));
		assertThat(epicBicycle.getSerialNumber(), is(165487L));
		assertThat(epicBicycle.getOwner(), is(zeBiker));
		
		Bicycle allezBicycle = bicycleSupport.findByModelName("S-WORKS ALLEZ DI2");
		assertThat(allezBicycle.getModelName(), is("S-WORKS ALLEZ DI2"));
		assertThat(allezBicycle.getSerialNumber(), is(98657L));
		assertThat(allezBicycle.getOwner(), is(maricotinhaBiker));
	}
	
	@Test
	@GivenEnvironment(value=BikerEnvironment.class, environmentName="twoBikersOneMaleAnotherFemale")
	public void twoBikersOneMaleAnotherFemaleWithBicyclesCreatedByTest() {
		List<Biker> bikers = bikerSupport.findAll();
		assertThat(bikers.size(), is(2));
		
		Biker zeBiker = bikerSupport.findByName("Zé Grandão");
		assertThat(zeBiker.getGender(), is(Gender.MALE));
		assertThat(zeBiker.getName(), is("Zé Grandão"));
		
		Biker maricotinhaBiker = bikerSupport.findByName("Maria Maricotinha");
		assertThat(maricotinhaBiker.getGender(), is(Gender.FEMALE));
		assertThat(maricotinhaBiker.getName(), is("Maria Maricotinha"));
		
		assertTrue(bicycleSupport.findAll().isEmpty());
		
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
		assertThat(bicycles.size(), is(2));
		
		epicBicycle = bicycleSupport.findByModelName("S-WORKS EPIC 29");
		assertThat(epicBicycle.getModelName(), is("S-WORKS EPIC 29"));
		assertThat(epicBicycle.getSerialNumber(), is(165487L));
		assertThat(epicBicycle.getOwner(), is(zeBiker));
		
		allezBicycle = bicycleSupport.findByModelName("S-WORKS ALLEZ DI2");
		assertThat(allezBicycle.getModelName(), is("S-WORKS ALLEZ DI2"));
		assertThat(allezBicycle.getSerialNumber(), is(98657L));
		assertThat(allezBicycle.getOwner(), is(maricotinhaBiker));
	}

}
