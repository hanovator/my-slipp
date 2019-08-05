package net.slipp.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.slipp.domain.Question;
import net.slipp.domain.QuestionRepository;
import net.slipp.domain.Result;
import net.slipp.domain.UserVo;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/form")
	public String form(HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)){
			return "redirect:/users/loginForm";
		}
		return "/qna/form";
	}
	
	@PostMapping("")
	public String create(String title, String contents, HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "redirect:/users/loginForm";
		}
		
		UserVo sessionUser = HttpSessionUtils.getUserFromSession(session);
		Question newQuestion = new Question(sessionUser, title, contents);
		questionRepository.save(newQuestion);
		
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model){
		model.addAttribute("question",questionRepository.findOne(id));
		return "/qna/show";
	}
	
	@GetMapping("/{id}/form")
	public String updateFrom(@PathVariable Long id, Model model, HttpSession session){
		
		Question question = questionRepository.findOne(id);
		Result result = valid(session, question);
		if(!result.isValid()){
			model.addAttribute("erroMessage",result.getErroMessage());
			return "/user/login";
		}

		model.addAttribute("question", question);
		return "/qna/updateForm";
		
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, String title, String contents, HttpSession session, Model model){
		
		Question question = questionRepository.findOne(id);
		Result result = valid(session, question);
		if(!result.isValid()){
			model.addAttribute("erroMessage",result.getErroMessage());
			return "/user/login";
		}
		
		question.update(title, contents);
		questionRepository.save(question);
		return String.format("redirect:/questions/%d", id);

	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, HttpSession session, Model model){
		
		Question question = questionRepository.findOne(id);
		Result result = valid(session, question);
		if(!result.isValid()){
			model.addAttribute("erroMessage",result.getErroMessage());
			return "/user/login";
		}

		questionRepository.delete(id);
		return "redirect:/";
		
	}
	
	// exception을 이용한 valid
	private boolean hasPermission(HttpSession session, Question question){
		if(!HttpSessionUtils.isLoginUser(session)){
			throw new IllegalStateException("로그인이 필요합니다");
		}
		
		UserVo loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!question.isSameWriter(loginUser)){
			throw new IllegalStateException("자신이 쓴 글만 수정, 삭제가 가능합니다");
		}
		
		return false;
	}
	
	private Result valid(HttpSession session, Question question){
		if(!HttpSessionUtils.isLoginUser(session)){
			return Result.fail("로그인이 필요합니다");
		}
		
		UserVo loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!question.isSameWriter(loginUser)){
			return Result.fail("자신이 쓴 글만 수정, 삭제가 가능합니다");
		}
		
		return Result.ok();
	}
	
}
