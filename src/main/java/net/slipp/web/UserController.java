package net.slipp.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	private List<UserVo> users = new ArrayList<UserVo>();
	
	@RequestMapping("/create")
	public String create(UserVo uvo) {
		System.out.println(uvo.getUserId());
		users.add(uvo);
		return "redirect:/list";
	}
	
	@RequestMapping("/list")
	public String list(Model model){
		model.addAttribute("users",users);
		return "list";
	}
}
