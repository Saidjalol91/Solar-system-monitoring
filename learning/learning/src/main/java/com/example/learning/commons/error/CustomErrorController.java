//package com.example.learning.commons.error;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.view.JstlView;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
//import java.net.URL;
//import java.util.Date;
//
//@Controller
//public class CustomErrorController implements ErrorController {
//	@Autowired
//	private InternalResourceViewResolver viewResolver;
//
//	@Autowired
//	private ServletContext servletContext;
//
//	private static final String PATH = "/error";
//
//	@RequestMapping(value = PATH)
//	public String error (HttpServletRequest request, Model model){
//		Object status  =request.getAttribute (RequestDispatcher.ERROR_STATUS_CODE);
//		HttpStatus httpStatus = HttpStatus.valueOf (Integer.valueOf (status.toString ()));
//
//		model.addAttribute ("status code", status);
//		model.addAttribute ("message", httpStatus.getReasonPhrase ());
//		model.addAttribute ("timestamp", new Date ());
//
//		if(!exitsView("error/" + status)){
//			return "error/error";
//		}
//
//		return "error/" + status;
//	}
//
//
//	public String getErrorPath(){
//		return PATH;
//	}
//
//	private boolean exitsView(String path){
//		try{
//			JstlView view = (JstlView) viewResolver.resolveViewName (path, null);
//			URL resource = servletContext.getResource (view.getUrl ());
//			return resource != null;
//		}
//		catch (Exception ex)
//		{
//			throw new RuntimeException ();
//		}
//	}
//}
