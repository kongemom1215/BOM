package com.spring.bom.service.coffee;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.spring.bom.model.bro.User_info;

public class CoffeeInterceptor implements AsyncHandlerInterceptor {
	public CoffeeInterceptor() {
		}
	  // 1번째 수행 
		public boolean preHandle(HttpServletRequest request, 
				HttpServletResponse response, Object handler) throws Exception {
			System.out.println("pre handle..........................");
			HandlerMethod method = (HandlerMethod) handler;
		    Method methodObj = method.getMethod();
		    System.out.println("Bean: " + method.getBean());
		    System.out.println("Method: " + methodObj);
//		    String search = request.getParameter("search");
//		    System.out.println("CoffeeInterceptor preHandle search->"+search);
		    		    
		    return true; 
	  }
	  // 2번째 수행
	  // Controller interceptor 호출
	  
	  
	  
	  // 3번째 수행 
	  @Override
	  public void postHandle(HttpServletRequest  request, 
			                 HttpServletResponse response, 
			                 Object              handler,
	                         ModelAndView        modelAndView) throws Exception {
		  HttpSession session = request.getSession();
		  System.out.println("post handle........................");
		  // String ID =  (String)modelAndView.getModel().get("id");
		  int memCnt = -1;
		  String url = "/bro/index";
		  try{
			  memCnt = (Integer) modelAndView.getModel().get("memCnt");
			  url = (String)modelAndView.getModel().get("url");
		  }catch (Exception e) {
			  System.out.println("CoffeeInterceptor postHandle =>"+e.getMessage());
		  }
		  System.out.println("CoffeeInterceptor  posthandle memCnt: " + memCnt);
		  if(memCnt <  1){
			  System.out.println("memCnt Not exists");
			  // request.getSession().setAttribute("ID", ID);
			  // 관리자가 아니면   User login Page(회원로그인) 이동
			  // session.invalidate();
			  session.setAttribute("manager", "9"); // 관리자 아니면 
			  System.out.println("manager ->9");
			  // response.sendRedirect("/coffee/kkk");
			  url = "/bro/index";
		  } else {       //    정상   관리자 면 Login   User  
			  System.out.println("memCnt exists");
			  // request.getSession().setAttribute("ID", ID);
			  // 관리자면  coffee/censorMemberManagerPage2(회원검열페이지)
			  session.setAttribute("manager", "0"); // 관리자 면 
			  
		  }
		  response.sendRedirect(url);
	  }
}
