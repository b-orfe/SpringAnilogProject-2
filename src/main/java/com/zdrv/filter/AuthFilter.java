package com.zdrv.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zdrv.domain.User;

/**
 * Servlet Filter implementation class AuthFilter
 */

public class AuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 前処理
		// HttpServletRequestとHttpServletResponseの取得
		var req = (HttpServletRequest) request;
		var res = (HttpServletResponse) response;
		
		// セッションの中身を取り出す
		User user = (User) req.getSession().getAttribute("user");
		
		//ログインしていない場合:
		if(user == null) {
			res.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		//登録ページ
		//ユーザーの権限レベルを確認
		if(req.getRequestURI().contains("register") && 
			user.getLevel() < 2) {
			res.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		chain.doFilter(request, response);
		// 後処理
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

