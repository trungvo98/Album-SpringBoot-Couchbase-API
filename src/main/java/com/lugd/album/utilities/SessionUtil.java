package com.lugd.album.utilities;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class SessionUtil {

	public SessionUtil() {
	}

	public static HttpSession getSession() {
		RequestAttributes atr = RequestContextHolder.currentRequestAttributes();
		HttpSession session = (HttpSession) atr.getSessionMutex();
		return session;
	}

	public static CustomerSession getCurrentUser() {
		CustomerSession UserAccount = (CustomerSession) getSession().getAttribute("CurrentUser");
		return UserAccount;
	}

	public static CustomerSession getCurrentUser(HttpSession session) {
		CustomerSession UserAccount = (CustomerSession) session.getAttribute("CurrentUser");
		return UserAccount;
	}

	public static void setCurrentUser(CustomerSession UserAccount) {
		getSession().setAttribute("CurrentUser", UserAccount);
	}

	public static void setCurrentUser(HttpSession session, CustomerSession UserAccount) {
		session.setAttribute("CurrentUser", UserAccount);
	}

	public static void removeCurrentUser(HttpSession session) {
		session.removeAttribute("CurrentUser");
	}

	public static void removeCurrentUser() {
		getSession().removeAttribute("CurrentUser");
	}

	public static void removeCaptcha() {
		getSession().removeAttribute("captchaEnc");
	}

	public static class CustomerSession {

		private int cusId;
		private String cusEmail;
		private long tokenExpireIn;
		private String cusName;
		private String token;

		public CustomerSession() {

		}

		public int getCusId() {
			return cusId;
		}

		public void setCusId(int cusId) {
			this.cusId = cusId;
		}

		public String getCusEmail() {
			return cusEmail;
		}

		public void setCusEmail(String cusEmail) {
			this.cusEmail = cusEmail;
		}

		public String getCusName() {
			return cusName;
		}

		public void setCusName(String cusName) {
			this.cusName = cusName;
		}

		public long getTokenExpireIn() {
			return tokenExpireIn;
		}

		public void setTokenExpireIn(long tokenExpireIn) {
			this.tokenExpireIn = tokenExpireIn;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

	}

}