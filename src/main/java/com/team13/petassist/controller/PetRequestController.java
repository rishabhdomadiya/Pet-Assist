package com.team13.petassist.controller;

import com.team13.petassist.entity.Order;
import com.team13.petassist.entity.PetAdoptionForm;
import com.team13.petassist.interfaces.IPetAdoption;
import com.team13.petassist.repo.OrderRepo;
import com.team13.petassist.repo.PetAdoptionRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;
import java.util.List;

@Controller
public class PetRequestController {
	@Value("${url}")
	String url;
	@Value("${user1}")
	String user;
	@Value("${passwordDb}")
	String passwordDb;
	IPetAdoption petAdoptionRepo;

	@RequestMapping(value = "/petRequests/{userId}", method = RequestMethod.GET)
	public String getPetRequests(@PathVariable String userId, Model model) throws SQLException {
		petAdoptionRepo = PetAdoptionRepo.getInstance1(url, user, passwordDb);
		List<PetAdoptionForm> petAdoptionRequests = petAdoptionRepo.getAllAdoptionRequests();
		model.addAttribute("petAdoptionRequests", petAdoptionRequests);
		return "pet-adoption-request";
	}

	@RequestMapping(value = "/petRequests/{userId}/editPetOrderDetails/{id}", method = RequestMethod.GET)
	public String getPetRequests1(@PathVariable String userId, @PathVariable String id, Model model)
			throws SQLException {
		petAdoptionRepo = PetAdoptionRepo.getInstance1(url, user, passwordDb);

		PetAdoptionForm petForm = petAdoptionRepo.getPetReqById(id);

		model.addAttribute("petAdoptionForm", petForm);
		return "EditPetAdoptionDetails";
	}

	@RequestMapping(value = "/editPerOrder/{userId}")
	public String updateOrderDetails(@PathVariable("userId") String userId, @ModelAttribute PetAdoptionForm petForm,
			Model model) throws SQLException {
		petAdoptionRepo = PetAdoptionRepo.getInstance1(url, user, passwordDb);
		petAdoptionRepo.updatePetRequest(petForm);

		return "redirect:/petRequests/" + userId;
	}

}
