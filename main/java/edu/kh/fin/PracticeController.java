package edu.kh.fin;


import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.kh.fin.member.model.vo.Ingan;

@Controller
public class PracticeController {
	
	@RequestMapping(value="/hello",method =RequestMethod.GET)
	public String hello() {
		return "hello";
	}
	@RequestMapping(value="/helloTest",method = RequestMethod.POST)
	public String helloTest(Ingan ingan) {
		System.out.println(ingan); 
		return null;
	}
	
}
