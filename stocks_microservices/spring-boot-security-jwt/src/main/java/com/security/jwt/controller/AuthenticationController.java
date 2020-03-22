package com.security.jwt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.security.jwt.config.TokenProvider;
import com.security.jwt.dao.DropDownList;
import com.security.jwt.dao.UserDao;
import com.security.jwt.model.AuthToken;
import com.security.jwt.model.LoginUser;
import com.security.jwt.model.Stocks;
import com.security.jwt.model.User;
import com.security.jwt.model.UserDto;
import com.security.jwt.service.RemoteServiceCall;
import com.security.jwt.service.UserService;
import com.security.jwt.service.impl.UserServiceImpl;

import static com.security.jwt.model.Constants.TOKEN_PREFIX;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;
    
    @Autowired
    private RemoteServiceCall remoteServiceCall;
    

	@Autowired
	private UserDao userDao;

    @ResponseBody @RequestMapping(value = "/generate-token", method = RequestMethod.POST,consumes = "application/json")
    @HystrixCommand(fallbackMethod  = "tokenfallback")
    public String register(@RequestBody LoginUser loginUser,Model model,HttpSession session) throws AuthenticationException {
    	ModelAndView model1 = new ModelAndView();
    	 session.setAttribute("user", loginUser.getUsername());
        System.out.println("dkjfhkdsb"+loginUser);
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        session.setAttribute("token", token);
        List<DropDownList> dlist=new ArrayList<>();
        List<String> finList=new ArrayList<String>();
        finList=remoteServiceCall.callPortfolioremote((userDao.findByUsername(loginUser.getUsername())).getCustid());
        for(String s:finList)
        	
        {DropDownList d=new DropDownList(s,s);
        dlist.add(d);
        	
        }
        session.setAttribute("dlist", dlist);
        model.addAttribute("dlist", dlist);
        System.out.println("final  list"+dlist.size());
        model.addAttribute("token", token);
        System.out.print("token---"+token);
        System.out.print("custid---"+userDao.findByUsername(loginUser.getUsername()));
      
       


    	
return "welcome";
    }
	
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user,HttpSession session){
    	System.out.println("user data"+user);
    	Random r=new Random();
    	int i=r.nextInt(200);
    	System.out.println("randon value"+i);
    	session.setAttribute("custid", i);
        return userService.save(user,i);
    }
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView  login(){
    	ModelAndView model = new ModelAndView();
    	model.setViewName("login");
        return model;
    }
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView  welcome(){
    	ModelAndView model = new ModelAndView();
    	model.setViewName("welcome");
        return model;
    }
	@RequestMapping(value = "/disstocks", method = RequestMethod.GET)
    public ModelAndView  displaystocks(){
    	ModelAndView model = new ModelAndView();
    	model.setViewName("stocks");
        return model;
    }
	@RequestMapping(value = "/disports", method = RequestMethod.GET)
    public ModelAndView  displayports(){
    	ModelAndView model = new ModelAndView();
    	model.setViewName("portfolio");
        return model;
    }
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView  logout(){
    	ModelAndView model = new ModelAndView();
    	model.setViewName("logout");
        return model;
    }
	@RequestMapping(value = "/registerdone", method = RequestMethod.GET)
    public ModelAndView  register(){
    	ModelAndView model = new ModelAndView();
    	model.setViewName("registerdone");
        return model;
    }
	 public String tokenfallback(@RequestBody LoginUser loginUser,Model model,HttpSession session) throws AuthenticationException {
	        System.out.println("inside token fallback");
	        List<DropDownList> dlist=new ArrayList<>();
	        DropDownList d=new DropDownList("fallback","fall back from portfolio service");
	        dlist.add(d);
	        	
	        session.setAttribute("dlist", dlist);

		 return "";
	 }
	 @RequestMapping(value = "/dismasterstocks", method = RequestMethod.GET)
	    public ModelAndView  dismasterstocks(){
	    	ModelAndView model = new ModelAndView();
	    	model.setViewName("dismasterstocks");
	        return model;
	    }
	 @RequestMapping(value = "/storestocks", method = RequestMethod.GET)
	    public ModelAndView  storestocks(){
	    	ModelAndView model = new ModelAndView();
	    	model.setViewName("storestocks");
	        return model;
	    }
}
