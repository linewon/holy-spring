package line.modules.validated.web;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

import line.modules.validated.entity.request.StringRequest;

@RestController
@RequestMapping("/validated")
public class ValidatedController {

	@Autowired
	private RestTemplate restTemplate;

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

	@GetMapping("")
	public String httpClient() {
		JSONObject json = new JSONObject();
		json.put("name", "line");
		String url = "http://localhost:18080/holy-spring/validated";
		ResponseEntity<String> responseEntity = null;
		String resp = null;
		try {
			responseEntity = restTemplate.postForEntity(url, json, String.class);
			resp = responseEntity.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}
}
