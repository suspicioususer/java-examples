package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping(value = "/")
	public ModelAndView mainPage() {
		return new ModelAndView("redirect:/login/");
	}

	/*@RequestMapping(value = "/index")
	public ModelAndView indexPage() {
		return new ModelAndView("login");
	}*/
	
	@RequestMapping(value = "/index")
	public ModelAndView indexPage() {
		return new ModelAndView("redirect:/login/");
	}
}
