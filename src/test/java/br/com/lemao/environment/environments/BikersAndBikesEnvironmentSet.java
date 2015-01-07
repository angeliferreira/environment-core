package br.com.lemao.environment.environments;

import org.hibernate.Transaction;

import br.com.lemao.environment.Environment;
import br.com.lemao.environment.annotation.GivenEnvironment;
import br.com.lemao.environment.hibernate.HibernateUtil;
import br.com.lemao.environment.model.bicycle.Bicycle;
import br.com.lemao.environment.model.bicycle.BicycleBuilder;
import br.com.lemao.environment.model.bicycle.BicycleSupport;
import br.com.lemao.environment.model.biker.Biker;
import br.com.lemao.environment.model.biker.BikerBuilder;
import br.com.lemao.environment.model.biker.BikerSupport;
import br.com.lemao.environment.model.gender.Gender;

public class BikersAndBikesEnvironmentSet{
	
	public static class TwoBikers extends Environment {
		
		private BikerSupport bikerSupport = new BikerSupport();
		
		@Override
		public void beforeRun() {
			Transaction transaction = HibernateUtil.getCurrentSession().getTransaction();
			if(transaction.isActive()){
				transaction.rollback();
				HibernateUtil.getCurrentSession().getTransaction().begin();
			}else{
				transaction.begin();
			}
		}

		@Override
		public void run() {
			Biker bortolozzo = new BikerBuilder().
					withName("Bortolozzo").
					withGender(Gender.MALE).
					gimme();

			bikerSupport.persist(bortolozzo);
			
			Biker bruna = new BikerBuilder().
					withName("Bruna").
					withGender(Gender.FEMALE).
					gimme();
			
			bikerSupport.persist(bruna);
		}
	}

	public static final class TwoBikersWithBicycles extends Environment {
		
		private BikerSupport bikerSupport = new BikerSupport();
		private BicycleSupport bicycleSupport = new BicycleSupport();
		
		@Override
		@GivenEnvironment(TwoBikers.class)
		public void run() {
			
			Biker bortolozzo = bikerSupport.findByName("Bortolozzo");
			Biker bruna = bikerSupport.findByName("Bruna");
			
			Bicycle bortolozzoBike = new BicycleBuilder().
					forBiker(bortolozzo).
					withModelName("KHS").
					withSerialNumber(100l).
					gimme();
				
			bicycleSupport.persist(bortolozzoBike);
			
			Bicycle brunaBike = new BicycleBuilder().
					forBiker(bruna).
					withModelName("Moulton").
					withSerialNumber(100l).
					gimme();
			
			bicycleSupport.persist(brunaBike);
		}
	}
	
	public static final class TwoBikersWithOneBicycle extends Environment {
		
		private BikerSupport bikerSupport = new BikerSupport();
		private BicycleSupport bicycleSupport = new BicycleSupport();
		
		@Override
		@GivenEnvironment(TwoBikers.class)
		public void run() {
			
			Biker bortolozzo = bikerSupport.findByName("Bortolozzo");
			
			Bicycle bortolozzoBike = new BicycleBuilder().
					forBiker(bortolozzo).
					withModelName("KHS").
					withSerialNumber(100l).
					gimme();
				
			bicycleSupport.persist(bortolozzoBike);
		}
	}
}