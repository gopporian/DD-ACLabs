package de.dialogdata.aclabs.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SecurityUtils {
	
	public static String encryptString(String input) {
		if (input != null) {
			MessageDigest messageDigest;
			try {
				messageDigest = MessageDigest.getInstance("SHA-1");
				messageDigest.update(input.getBytes(), 0, input.length());
				String hexEncrypted = new BigInteger(1, messageDigest.digest()).toString(16);

				return hexEncrypted;
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException("Not very likely ... ");
			}
		} else {
			return null;
		}

	}
	
	public static HttpSession getSession() {
        return (HttpSession)
          FacesContext.
          getCurrentInstance().
          getExternalContext().
          getSession(false);
      }
       
      public static HttpServletRequest getRequest() {
       return (HttpServletRequest) FacesContext.
          getCurrentInstance().
          getExternalContext().getRequest();
      }
      
      public static String getUserName()
      {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return  session.getAttribute("user").toString();
      }
}
