package ru.usachev.LogiWebProject.controller;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController{

	private static Logger logger = Logger.getLogger(MainController.class);


	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Грузоперевозки");
		model.addObject("message", "Стартовая страница");
		model.setViewName("pages/hello");
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public String adminPage() {
		return "admin/admin-main";
	}

	@RequestMapping(value = "/driver**", method = RequestMethod.GET)
	public String driverPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		model.addAttribute("username", userDetail.getUsername());
		return "driver/driver-main";
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "Вы успешно вышли!");
		}
		model.setViewName("pages/login");

		return model;

	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Неправильный логин или пароль!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Неправильный логин или пароль!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accessDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}


		model.setViewName("pages/403");
		return model;
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(Exception.class)
	public ModelAndView redirectToErrorPage() {
		ModelAndView mav = new ModelAndView("pages/404");
		mav.addObject("message", "error on server");
		return mav;
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ModelAndView redirectToError500Page() {
		ModelAndView mav = new ModelAndView("pages/500");
		mav.addObject("message", "error on server");
		return mav;
	}
}