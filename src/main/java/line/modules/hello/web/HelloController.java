package line.modules.hello.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public ModelAndView hello() {
		ModelAndView mv = new ModelAndView();
		
		String text = "this is text from controller!";
		mv.addObject("hello", text);
		mv.setViewName("hello");
		
		return mv;
	}
}
