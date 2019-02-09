package cn.oauth2.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("blog-oauth2-center")
public interface Oauth2Client {

	@PostMapping(path = "/oauth/token")
	Map<String, Object> postAccessToken(@RequestParam Map<String, String> parameters);

}
