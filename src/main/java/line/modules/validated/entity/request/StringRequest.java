package line.modules.validated.entity.request;


import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

public class StringRequest {

	@Getter
	@Setter
	@NotBlank(message="请输入用户名")
	private String name;
	@Getter
	@Setter
	@NotBlank(message="请输入年龄")
	private String age;
	@Getter
	@Setter
	@NotBlank(message="请输入地址")
	private String addr;
}
