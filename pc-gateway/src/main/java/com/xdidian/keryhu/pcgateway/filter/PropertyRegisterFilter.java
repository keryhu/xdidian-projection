package com.xdidian.keryhu.pcgateway.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;

/**
* @ClassName: PropertyRegisterFilter
* @Description: TODO(物业公司注册pre filter 就是前台web 提交给 此pc gateway时，验证所有必须验证的的对象
* 例如： 提交注册的时候，必须是未登陆用户，还有提交注册的时候，信息是否符合规范，例如email 有没有注册过，phone有没有注册过)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月26日 下午2:09:42
 */
@Component
public class PropertyRegisterFilter extends ZuulFilter {

	@Override
	public Object run() {
		// TODO Auto-generated method stub
		
		return "pre";
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		//order值越大，优先级越小
		return 1;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		//明确定义此filter 是pre ，另外还有post，error,routing三种主要类型
		return "pre";
	}

}
