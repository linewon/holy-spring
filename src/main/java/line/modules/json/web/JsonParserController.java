package line.modules.json.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import line.modules.json.service.JsonParserService;

@Controller
@RequestMapping(value="/jsonParser")
public class JsonParserController {
	
	@Autowired
	private JsonParserService jsonParseservice;

	/**
	 * 打开jsonParser页面
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView jsonParser() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/jsonParser/jsonParser");
		return mv;
	}
	
	@RequestMapping(value="/jsonString", method=RequestMethod.GET)
	@ResponseBody
	public String parseJson(HttpServletRequest request) {
		String jsonStr = request.getParameter("inputJson");
		
		String resultStr;
		try {
			resultStr = jsonParseservice.formatJson(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
			resultStr = e.getMessage();
		}
		return resultStr;
	}
	
	@RequestMapping(value="/xmlString", method=RequestMethod.GET)
	@ResponseBody
	public String getXml(HttpServletRequest request) {
		
		String resultStr = "<context-param><param-name>contextConfigLocation</param-name><param-value>classpath:applicationContext.xml</param-value></context-param>";
		return resultStr;
	}
}
