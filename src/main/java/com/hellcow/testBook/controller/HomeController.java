package com.hellcow.testBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@GetMapping
	@RequestMapping(value = "/")
	public String index() {
		return "redirect:login";
	}

	@PostMapping
	@ResponseBody
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
}
