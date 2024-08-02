package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.modals.customer;
import com.example.demo.repository.customerrepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class mvcmodalcontroller {
	@Autowired
	customerrepository repo;
	@RequestMapping("/welcome")
	public String home() {
		return "welcome.html";
	}
	@GetMapping("/loadform")
	public String loadform() {
		return "add.html";
	}
	 @PostMapping("/insert")
	public String insertion(@ModelAttribute customer c,HttpSession session) {
		 repo.save(c);
		 session.setAttribute("message", "successfully inserted");
		 return "redirect:/loadform";
		
	}
	 @DeleteMapping("/delete/{id}")
	public String deletebyid(@PathVariable(value = "id") int id,HttpSession session) {
		repo.deleteById(id);
		session.setAttribute("message", "successfully deleted");
		return "redirect:/";
	}
	 @GetMapping("/getbyid/{id}")
	public String getbyid(@PathVariable(value = "id") int id,HttpSession session,Model m) {
		Optional<customer> c= repo.findById(id);
		customer c1=c.get();
		m.addAttribute("product", c1);
		return "edit";
	}
	 @GetMapping("/getall")
	public String getall(Model m) {
		Iterable<customer> cus=repo.findAll();
		m.addAttribute("allproducts",cus);
		return "home";
	
	}
	 @PutMapping("/update")
	 public String update(@ModelAttribute customer c,HttpSession session) {
		 repo.save(c);
		 session.setAttribute("message", "succesfully updated");
		 return "redirect:/";
	 }
	

}
