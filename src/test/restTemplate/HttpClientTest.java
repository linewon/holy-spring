package restTemplate;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

public class HttpClientTest {

	private RestTemplate restTemplate;
	
	public HttpClientTest() {
//		ClientHttpRequestFactory request = new SimpleClientHttpRequestFactory();
//		restTemplate = new RestTemplate(request);
		restTemplate = new RestTemplate();
		
		List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
		try {
			StringHttpMessageConverter stringConverter = (StringHttpMessageConverter) converters.get(1);
			MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converters.get(6);
			if (stringConverter.getDefaultCharset() == jsonConverter.getDefaultCharset())
				System.out.println("Y Y Y Y YES!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Iterator<HttpMessageConverter<?>> it = restTemplate.getMessageConverters().iterator();
		while (it.hasNext()) {
			HttpMessageConverter<?> messageConverter = it.next();
			if (messageConverter instanceof StringHttpMessageConverter) {
				Charset cs = ((StringHttpMessageConverter) messageConverter).getDefaultCharset();
				System.out.println(cs.name());
				((StringHttpMessageConverter) messageConverter).setDefaultCharset(Charset.forName("utf-8"));;
				cs = ((StringHttpMessageConverter) messageConverter).getDefaultCharset();
				System.out.println(cs);
			}
		}
	}
	
	public void test1() {
		String url = "http://localhost:18080/holy-spring/validated";
		JSONObject postData = new JSONObject();
		Class<String> clz = String.class;
		
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, postData, clz);
		String resp = responseEntity.getBody();
		System.out.println(resp);
		
		
	}
	
	public static void main(String[] args) {
		HttpClientTest demo = new HttpClientTest();
		demo.test1();
	}
}
