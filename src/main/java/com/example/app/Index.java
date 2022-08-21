package com.example.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class Index {
	@GetMapping("/")
	public RedirectView index() {
		var redirectView = new RedirectView();
		redirectView.setUrl("blog/");
		return redirectView;
	}
}
