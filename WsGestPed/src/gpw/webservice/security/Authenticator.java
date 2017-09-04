package gpw.webservice.security;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;

public class Authenticator {
	
	private static Logger logger = Logger.getLogger(Authenticator.class);
	protected static Properties props;
	protected static String usr = null;
	protected static String pwd = null;

	public static Boolean authenticateWsCAll(MessageContext msgCtx) {
		try {
			if(msgCtx != null) {
				if (null == props) {
					String fileName = System.getProperty("jboss.server.config.dir") + File.separator + "config.properties";
					try(FileInputStream fis = new FileInputStream(fileName)) {
						props = new Properties();
						props.load(fis);
						usr = props.getProperty("ws_username");
						pwd = getMD5(props.getProperty("ws_password"));
					}
				}
				
				Map<?,?> requestHeaders = (Map<?,?>) msgCtx.get(MessageContext.HTTP_REQUEST_HEADERS);
		        List<?> userList = (List<?>) requestHeaders.get("ws_username");
		        List<?> passList = (List<?>) requestHeaders.get("ws_password");
		        if( (userList != null && !userList.isEmpty()) && 
		        		(passList != null && !passList.isEmpty()) ) {
		        	String nomUsr = (String) userList.get(0);
		        	String passWd = (String) passList.get(0);
		        	if(nomUsr.equals(usr) && passWd.equals(pwd)) {
		        		return true;
		        	}
		        }
			}
		} catch (Exception e) {
			logger.fatal("Excepcion en Authenticator > authenticateWsCAll al validar las credenciales: " + e.getMessage(), e);
			return false;
		}
		return false;
	}
	
	/**
	 * metodo que transforma una password sin cifrar a una cifrada con el metodo MD5. 
	 * @param input
	 * @return string con hash md5 de password
	 */
	protected static String getMD5(String input) {
		 try {
			 MessageDigest md = MessageDigest.getInstance("MD5");
			 byte[] messageDigest = md.digest(input.getBytes());
			 BigInteger number = new BigInteger(1, messageDigest);
			 String hashtext = number.toString(16);
		 
			 while (hashtext.length() < 32) {
			 	hashtext = "0" + hashtext;
		 	}
		 	return hashtext;
	 	}
		catch (NoSuchAlgorithmException e) {
			logger.fatal("Excepcion en Authenticator > authenticateWsCAll al generar hash MD5: " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
