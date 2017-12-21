package restTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class HttpClientTest {

	@Autowired
	private RestTemplate restTemplate;
	
	public void test1() {
		restTemplate.postForEntity();
	}
	
	public static void main(String[] args) {
		
	}
}
