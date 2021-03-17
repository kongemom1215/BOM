package com.spring.bom.service.bear;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SampleInterceptor implements AsyncHandlerInterceptor {
	public SampleInterceptor() {
		}
	
	  // 1번째 수행 
	  public boolean preHandle(HttpServletRequest request, 
		      HttpServletResponse response, Object handler) throws Exception {
		   System.out.println("pre handle..........................");
		    HandlerMethod method = (HandlerMethod) handler;
		    Method methodObj = method.getMethod();
		    System.out.println("Bean: " + method.getBean());
		    System.out.println("Method: " + methodObj);
		  
		   return true; 
	  }
	  // 2번째 수행
	  // Controller  interceptor 호출
	  
	 // 3번째 수행 
	  @Override
	  public void postHandle(HttpServletRequest  request, 
			                 HttpServletResponse response, 
			                 Object              handler,
	                         ModelAndView        modelAndView) throws Exception {

		    System.out.println("post handle........................");
		    String ID =  (String)modelAndView.getModel().get("id");
		    int memCnt = (Integer) modelAndView.getModel().get("memCnt");
		    System.out.println("SampleInterceptor  post handle memCnt: " + memCnt);
		    if(memCnt <  1){
		       System.out.println("memCnt Not exists");
		       request.getSession().setAttribute("ID", ID);
		      // User가 존재하지 않으면   User interCepter Page(회원등록) 이동 
		       response.sendRedirect("doMemberWrite"); 
		     } else {       //    정상   Login   User  
		       System.out.println("memCnt exists");
		       request.getSession().setAttribute("ID", ID);
		       // User가 존재하면   User interCepter Page(회원List) 이동 
		       response.sendRedirect("doMemberList"); 
		     }
	  }
	

}
