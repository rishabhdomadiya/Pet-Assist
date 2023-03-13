package com.team13.petassist.entity;

import com.team13.petassist.interfaces.IPetForm;

public class PetForm implements IPetForm{
	
	private String animal;
	private String breed;
	private String id;
	private String groomServices;
	private String nutServices;
	private String prodServices;
	private static PetForm INSTANCE;
	
	public PetForm() {
		super();
	}
	
	public static PetForm getInstance() {
		INSTANCE = new PetForm();
		return INSTANCE;
	}
	
	public String getGroomServices() {
		return groomServices;
	}

	public void setGroomServices(String groomServices) {
		this.groomServices = groomServices;
	}

	public String getNutServices() {
		return nutServices;
	}

	public void setNutServices(String nutServices) {
		this.nutServices = nutServices;
	}

	public String getProdServices() {
		return prodServices;
	}

	public void setProdServices(String prodServices) {
		this.prodServices = prodServices;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
