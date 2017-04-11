package com.omcbappeda.sumsel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.omcbappeda.sumsel.constant.Constant;

public class MainController extends AbstractController {
	
	protected ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	protected final static String USER_SESSION = "userSession";
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}
	
	protected boolean checkSession(HttpServletRequest request) {
		String session = request.getParameter("trash").equals(Constant.EMPTY_STRING.toString()) || 
				request.getParameter("trash").equals(null) ?  "eyJhY2Nlc3MiIDogImRlbmllZCJ9" : request.getParameter("trash") ;
		String encoded = Constant.EMPTY_STRING.toString();
		
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(new String(DatatypeConverter.parseBase64Binary(session)));
			encoded = DatatypeConverter.printBase64Binary(jsonObject.toJSONString().getBytes());
		} catch (ParseException e) {
//			e.printStackTrace();
			logger.error("CHECK SESSION", e);
			return false;
		}
		
		return encoded.equals(request.getSession().getAttribute(USER_SESSION));
	}
	
	protected boolean checkTrash(String trash, HttpServletRequest request) {
		String session = trash.equals(Constant.EMPTY_STRING.toString()) || 
				trash.equals(null) ?  "eyJhY2Nlc3MiIDogImRlbmllZCJ9" : trash ;
		String encoded = Constant.EMPTY_STRING.toString();
		
		try {
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(new String(DatatypeConverter.parseBase64Binary(session)));
			encoded = DatatypeConverter.printBase64Binary(jsonObject.toJSONString().getBytes());
		} catch (ParseException e) {
//			e.printStackTrace();
			logger.error("CHECK TRASH", e);
			return false;
		}
		
		return encoded.equals(request.getSession().getAttribute(USER_SESSION));
	}
}
