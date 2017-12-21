package line.modules.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import line.modules.user.entity.User;
import line.modules.user.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public ModelAndView userInfo() {
		ModelAndView mv = new ModelAndView();
		String userName = "line";
		User usr = userService.getByUserName(userName);
		String userInfo = JSON.toJSONString(usr);
		System.out.println(userInfo);
		mv.addObject("user", userInfo);
		mv.setViewName("/user/userInfo");
		return mv;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView test(String userName, String password) {
		ModelAndView mv = new ModelAndView();
		User user = new User();
		user.setUserName(userName);
		user.setPassWord(password);
		String userInfo = JSON.toJSONString(user);
		System.out.println(userInfo);
		mv.addObject("user", userInfo);
		mv.setViewName("/user/userInfo");
		return mv;
	}
}
