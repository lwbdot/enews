package com.lwb.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class CodingFilter extends HttpServlet implements Filter {
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		arg0.setCharacterEncoding("utf-8");
		arg2.doFilter(arg0, arg1);//将过滤连传递给下个过滤器
	}

	public void init(FilterConfig arg0) throws ServletException {

	}
}
