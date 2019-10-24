package com.lugd.album.security;

import org.springframework.stereotype.Component;

import java.util.Calendar;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

@Component
public class TokenHelper {
	
    public boolean checkToken(String token, long tmp, String secret) {
    	SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
    	try {
    		if(getCurrentTimeInSecond() - tmp < 30) {
    			Mac mac = Mac.getInstance("HmacSHA256");
                mac.init(signingKey);
                String data = secret+":"+tmp;
                byte[] rawHmac = mac.doFinal(data.getBytes());
                String signature = DatatypeConverter.printHexBinary(rawHmac).toLowerCase();
                if(signature.equals(token)) {
                	return true;
                }
    		}
    		
    	} catch (Exception e) {
			// TODO: handle exception
		}
        
        return false;
    }

    private long getCurrentTimeInSecond() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }

}
