package ca.sheridancollege.nguye482.a4.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.nguye482.a4.beans.Event;
import ca.sheridancollege.nguye482.a4.beans.Transaction;
import ca.sheridancollege.nguye482.a4.beans.User;
import ca.sheridancollege.nguye482.a4.beans.UserLog;
import ca.sheridancollege.nguye482.a4.database.DatabaseAccess;
import ca.sheridancollege.nguye482.a4.security.LogAccessDeniedHandler;


@Controller
public class MainController {
	@Autowired @Lazy
	private DatabaseAccess da;
	
	@Autowired
	LogAccessDeniedHandler log;
	
	@GetMapping("/")
	public String root(HttpSession session,Model model,@ModelAttribute Event event) {
		List<Event> list = da.getEvent();
		session.setAttribute("eventList", list);
		model.addAttribute("accountName", getUserName());
		model.addAttribute("year", LocalDate.now().getYear());
		return "index";
	}
	
	@GetMapping("/txns/add")
	public String securePages(Model model, @ModelAttribute User user) {
		model.addAttribute("trans", new Transaction());
		model.addAttribute("events", da.getEvent());
		model.addAttribute("accountName", getUserName());
		model.addAttribute("year", LocalDate.now().getYear());
		return "/txns/add";
	}
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("accountName", getUserName());
		model.addAttribute("year", LocalDate.now().getYear());
		return "login";
	}
	
	@GetMapping("/denied")
	public String permissionDenied(Model model) {
		model.addAttribute("accountName", getUserName());
		model.addAttribute("year", LocalDate.now().getYear());
		return "/error/permissionDenied";
	}
	

	@PostMapping("/addNew")
	public String addTransaction (Model model,@ModelAttribute Transaction trans) {
		model.addAttribute("countTrans", da.addTransaction(trans));
		model.addAttribute("events",da.getEvent());
		model.addAttribute("trans", da.getTrans());
		model.addAttribute("accountName", getUserName());
		model.addAttribute("year", LocalDate.now().getYear());
		return "/txns/index";
	}
	
	@GetMapping("/txns/index")
	public String list(Model model,Transaction trans) {
		model.addAttribute("events",da.getEvent());
		model.addAttribute("trans", da.getTrans());
		model.addAttribute("accountName", getUserName());
		model.addAttribute("year", LocalDate.now().getYear());
		return "/txns/index";
	}
	
	private String getUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.isAuthenticated() ) {
			return auth.getName();
		}
		else
			return null;
	}
}
