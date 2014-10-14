package br.com.lemao.environment.model.bicycle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.lemao.environment.model.biker.Biker;

@Entity
public class Bicycle {

	@Id
	@GeneratedValue
	private Long id;
	private Long serialNumber;
	private String modelName;
	@OneToOne
	private Biker owner;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Long serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Biker getOwner() {
		return owner;
	}

	public void setOwner(Biker owner) {
		this.owner = owner;
	}

}
