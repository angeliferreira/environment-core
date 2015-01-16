package br.com.lemao.environment.environments;

import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.lemao.environment.model.bicycle.Bicycle;
import br.com.lemao.environment.model.biker.Biker;

public class BikersAndBikesEnvironmentSet {

	public static final class TwoBikers extends AbstractBikerBicycleEnvironment {
		@Override
		public void run() {
			Biker bortolozzo = oneBiker().withName("Bortolozzo").male().gimme();
			bikerSupport.persist(bortolozzo);

			Biker bruna = oneBiker().withName("Bruna").female().gimme();
			bikerSupport.persist(bruna);
		}
	}
	
	public static final class TwoBikersInMemory extends AbstractBikerBicycleEnvironment {
		@Override
		public void run() {
			Biker bortolozzo = oneBiker().withName("Bortolozzo").male().gimme();
			bikerInMemorySupport.persist(bortolozzo);

			Biker bruna = oneBiker().withName("Bruna").female().gimme();
			bikerInMemorySupport.persist(bruna);
		}
	}

	public static final class TwoBikersWithBicycles extends AbstractBikerBicycleEnvironment {
		@Override
		@GivenEnvironment(TwoBikers.class)
		public void run() {
			Bicycle bortolozzoBike = oneBicycle()
					.forBiker(bikerSupport.findByName("Bortolozzo"))
					.withModelName("KHS")
					.withSerialNumber(100l)
					.gimme();
			bicycleSupport.persist(bortolozzoBike);

			Bicycle brunaBike = oneBicycle()
					.forBiker(bikerSupport.findByName("Bruna"))
					.withModelName("Moulton")
					.withSerialNumber(100l)
					.gimme();
			bicycleSupport.persist(brunaBike);
		}
	}
	
	public static final class TwoBikersWithBicyclesInMemory extends AbstractBikerBicycleEnvironment {
		@Override
		@GivenEnvironment(TwoBikersInMemory.class)
		public void run() {
			Bicycle bortolozzoBike = oneBicycle()
					.forBiker(bikerInMemorySupport.findByName("Bortolozzo"))
					.withModelName("KHS")
					.withSerialNumber(100l)
					.gimme();
			bicycleInMemorySupport.persist(bortolozzoBike);

			Bicycle brunaBike = oneBicycle()
					.forBiker(bikerInMemorySupport.findByName("Bruna"))
					.withModelName("Moulton")
					.withSerialNumber(100l)
					.gimme();
			bicycleInMemorySupport.persist(brunaBike);
		}
	}

	public static final class TwoBikersWithOneBicycle extends AbstractBikerBicycleEnvironment {
		@Override
		@GivenEnvironment(TwoBikers.class)
		public void run() {
			Bicycle bortolozzoBike = oneBicycle()
					.forBiker(bikerSupport.findByName("Bortolozzo"))
					.withModelName("KHS")
					.withSerialNumber(100l)
					.gimme();
			bicycleSupport.persist(bortolozzoBike);
		}
	}
	
	public static final class TwoBikersWithOneBicycleInMemory extends AbstractBikerBicycleEnvironment {
		@Override
		@GivenEnvironment(TwoBikersInMemory.class)
		public void run() {
			Bicycle bortolozzoBike = oneBicycle()
					.forBiker(bikerInMemorySupport.findByName("Bortolozzo"))
					.withModelName("KHS")
					.withSerialNumber(100l)
					.gimme();
			bicycleInMemorySupport.persist(bortolozzoBike);
		}
	}
}