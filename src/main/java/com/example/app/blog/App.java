package com.example.app.blog;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.models.Author;
import com.example.app.models.Blog;
import com.example.app.repository.AuthorRepository;
import com.example.app.repository.BlogRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import static com.example.app.x.print;

@Controller
@RequestMapping("/blog")
public class App {
	
	@Autowired
	private BlogRepository blogs;

	@Autowired
	private AuthorRepository authors;

	

	final private String INDEX = "blog/index.jsp";
	final private String CREATE = "blog/create.jsp";
	final private String POST = "blog/post.jsp";
	final private String R_LOGIN = "redirect:../author/login";


	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("blogs", blogs.findAll());
		return INDEX;
	}
	
	
	@GetMapping("/create")
	public String create(Model model, HttpServletRequest request) {
		var author = request.getSession().getAttribute("author");
		if (author == null){
			return R_LOGIN;
		}
		return CREATE;
	}
	
	@PostMapping("/create")
	public String addblog(@ModelAttribute("blog") @Valid Blog blog, Model model, 
						HttpServletRequest request,
						RedirectAttributes ra // eat all current scope params for redirect
						)
	
	throws Exception 
	{
		var author = (Author)request.getSession().getAttribute("author");
		if (author == null){
			print("user is null");
			return R_LOGIN;
		}
		var author_ = authors.findById(author.getUsername());

		if (author_.isEmpty()){
			print("2: user is null");
			return R_LOGIN;
		}
		
		if (!author.equals(author_.get())){
			print("3: user is mismatch");
			
			return R_LOGIN;
		}

		blog.setAuthor(author_.get());
		blogs.save(blog);
		model.addAttribute("success", true);
		return POST;
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") UUID id, Model model, HttpServletRequest request) throws Exception {
		var blog = blogs.findById(id);
		if (blog.isEmpty()){
			return INDEX;
		}
		
		if(!blog.get().getAuthor().equals(request.getSession().getAttribute("author"))){
			return R_LOGIN;
		}
		try {
			blogs.deleteById(id);
		}catch(Exception e){
			model.addAttribute("success", false);
		}
		model.addAttribute("success", true);
		model.addAttribute("blogs", blogs.findAll());
		return INDEX;
	}
	
	@GetMapping("/post")
	public String viewpost(@RequestParam("id") UUID id, Model model) {
		var blog = blogs.findById(id);
		if (blog == null || blog.isEmpty()) {
			return INDEX;
		}
		model.addAttribute("blog", blog.get());
		return POST;
	}


	@GetMapping("/edit")
	public String editPost(@RequestParam("id") UUID id, Model model, HttpServletRequest request){
		var blog = blogs.findById(id);
		if (blog.isEmpty()){
			return CREATE;
		}
		if(!blog.get().getAuthor().equals(request.getSession().getAttribute("author"))){
			return R_LOGIN;
		}
		model.addAttribute("blog", blog.get());
		return CREATE;
	}


	public void setBlogs(BlogRepository blogs) {
		this.blogs = blogs;
	}


	public void setAuthors(AuthorRepository authors) {
		this.authors = authors;
	}


	public App(BlogRepository blogs, AuthorRepository authors) {
		this.blogs = blogs;
		this.authors = authors;
	}


	public App() {}
	
	
}

