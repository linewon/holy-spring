package line.modules.validated.web;

import java.util.Iterator;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import line.modules.validated.entity.request.StringRequest;

@RestController
@RequestMapping("/validated")
public class ValidatedController {

	@PostMapping("")
	public String validation(@RequestBody @Validated StringRequest request, BindingResult result) {
		
		if (result.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			Iterator<ObjectError> itr = result.getAllErrors().iterator();
			while (itr.hasNext()) {
				
				sb.append(itr.next().getDefaultMessage()).append(';');
			}
			return sb.toString();
		}
		
		return "Successfully!";
	}
}
