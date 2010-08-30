package com.tdil.simon.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.utils.LoggerProvider;
/**
 * @deprecated
 * @author mgodoy
 *
 */
public class TransactionFilter implements Filter {

	private static final Logger Log = LoggerProvider.getLogger(TransactionFilter.class);
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		boolean commited = false;
		try {
			IBatisManager.beginTransaction();
            chain.doFilter(request, response);
            IBatisManager.commitTransaction();
            commited = true;
		} catch (SQLException e) {
			Log.error(e.getMessage(), e);
			throw new ServletException(e);
		} finally {
			if (!commited) {
				try {
					IBatisManager.rollbackTransaction();
				} catch (SQLException e) {
					Log.error(e.getMessage(), e);
				}
			}
		}

	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}
}

