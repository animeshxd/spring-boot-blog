package com.example.app.author;



// import static com.example.x.print;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.models.Author;
import com.example.app.repository.AuthorRepository;
import com.example.app.repository.service.AuthorManager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/author")
public class AuthorController {
	
	@Autowired
	private AuthorRepository authors;

	@Autowired
	private AuthorManager manager;

	public AuthorController() {}

	public AuthorController(AuthorRepository authors, AuthorManager manager) {
		this.authors = authors;
		this.manager = manager;
	}


	@RequestMapping("/")
	public String index(){
		return "redirect:../blog/";
	}
	
	@GetMapping("/register")
	public String register() {	

		return "author/register.jsp";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("author") @Valid  Author author,
							BindingResult result, Model model ){

		// print(author.email, author.name, author.password, author.username);
		
		if (!author.getXfile().getContentType().contains("image")){
			
			result.rejectValue("xfile", "xfile.invalid", "invalid file");
			return "author/register.jsp";
		}

		if(result.hasErrors()) {
            return "author/register.jsp";
        }
		

		if(authors.findById(author.getUsername()).isPresent()){
			model.addAttribute("error", "username already exists"); 
			return "author/register.jsp";
		}
		
		
		try {
			manager.save(author);
		} catch (DataIntegrityViolationException e) {
			result.rejectValue("email", "", "this is already exists");
			return "author/register.jsp";
		}catch(Exception e){
			e.printStackTrace();
			result.rejectValue("email", "", e.toString());
			return "author/register.jsp";
		}
		
		return "author/login.jsp";
	}

	@GetMapping("/login")
	public String login(){
		return "author/login.jsp";
	}

	@PostMapping("/login")
	public String login(@RequestParam("username") String username,
						@RequestParam("password") String password,
						Model model,
						HttpServletRequest req,
						RedirectAttributes ra // eat all redirect attributes 
					   )	
	{
		var author = authors.findById(username);
		if(author.isEmpty()){
			model.addAttribute("username", username);
			model.addAttribute("error", "username does not exists"); 
			return "author/login.jsp";
		}		
		if (!author.get().getPassword().equals(password)){
			model.addAttribute("username", username);
			model.addAttribute("error", "password mismatch"); 
			return "author/login.jsp";
		}
		req.getSession().setAttribute("author", author.get());
		return "redirect:../blog/";
	}

	@GetMapping("/update")
	public String update() {

		return "author/";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:../blog/";
	}
	
}
