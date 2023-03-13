package com.team13.petassist.interfaces;

import java.util.List;

import com.team13.petassist.entity.PetForm;

public interface IPet {
	public List<PetForm> getAllPets();
	public List<PetForm> getFilteredPets(String animalName);
	public List<PetForm> getDistinctAnimals();
	public List<PetForm> getPetById(String id);
	public List<PetForm> getPetByUserId(String userId);
}
