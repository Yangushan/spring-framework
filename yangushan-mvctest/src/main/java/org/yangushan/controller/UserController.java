package org.yangushan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yangushan.po.User;
import org.yangushan.service.UserService;

import java.util.Date;

/**
 * created by yangushan
 * 2024/3/23 14:09
 */
@RestController()
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/test")
	public String test() {
		return userService.test();
	}

	/**
	 * 就算什么注解都不使用，如果我们的参数中有Name也能正确赋值，因为我们的user里面有一个有参数构造方法
	 * Spring在底层会拿到这个构造方法的参数去传入的参数中拿，如果能拿得到一样可以映射
	 *	可以参考，走的参数分析是ModelAttributeMethodProcessor对象，兜底对象
	 * @see	org.springframework.web.method.annotation.ModelAttributeMethodProcessor#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.method.support.ModelAndViewContainer, org.springframework.web.context.request.NativeWebRequest, org.springframework.web.bind.support.WebDataBinderFactory)
	 *
	 * @param user
	 * @return
	 */
	@GetMapping("/testUser")
	public String testUser(User user) {
		System.out.println(user.getDate());
		return user.getName();
	}

	/**
	 * 这个方法和上面一个方法是有区别的，这个方法我们指定了@ReuqestParam，所以就会用这个参数的解析器
	 * 也就是RequestParamMethodArgumentResolver
	 *
	 * @param user
	 * @return
	 */
	@GetMapping("/testUser1")
	public String testUser1(@RequestParam("user") User user) {
		System.out.println(user.getDate());
		return user.getName();
	}

	/**
	 *
	 * @param file
	 * @param name 这个拿的是Url的参数
	 * @param name2 在复合请求下可以拿到表单里面的参数
	 * @return
	 */
	@PostMapping("/testFile")
	public String testFile(MultipartFile file,
						   @RequestParam(value = "name", required = false) String name,
						   @RequestPart(value = "name2", required = false) String name2) {
		System.out.println(name);
		return name2;
	}

	@GetMapping("/charsetTest")
	public String charsetTest() {
		return "你好";
	}

	/**
	 * 如果不配置MessageConverter，则就会报错，无法解析
	 * 所以可以自己加入自己的
	 * @return
	 */
	@GetMapping("/testReturnUser")
	public User user() {
		User user = new User();
		user.setName("1122");
		user.setDate(new Date());
		return user;
	}

	@GetMapping("/testInteceptor")
	public void testInteceptor(String param) {
		System.out.println("testInteceptor");
		if ("error".equals(param)) {
			throw new NullPointerException();
		}
	}

}
