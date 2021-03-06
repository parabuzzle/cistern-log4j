package org.cistern.appender.log4j;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
	 
	//Setup delimiters
	static String vb = "__1_VV";
	static String bb = "__1_BB";
	static String check = "__1_CC";
	static String end = "__1_EE";
	static String eq = "/000/";
	static String newval = "/111/";
	 
	protected static String getSendableString(LogObject obj) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		//Returns a string for sending to Cistern. Formated properly with the required MD5 Checksum.
		String data = null;
		
		//TODO: generate data string cleaner. Loop over LogObject and append to string instead.
		data = "authkey" + vb + obj.getAuthkey() + bb + "agent_id" + vb + obj.getAgent_id() + bb + "logtype_id" + vb + obj.getLogtype_id() + bb + "loglevel_id" + vb + obj.getLoglevel_id() + bb + "etime" + vb + obj.getEtime() + bb + "data" + vb + obj.getData() + bb + "payload" + vb + obj.payload + bb;
		String withchecksum = data + check + convertToHex(MD5(data)) + end;
		return withchecksum;
	}
	
	private static byte[] MD5(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		//Makes a byte array of the checksum of a String
		MessageDigest md = null;
		byte[] md5hash = null;
		md = MessageDigest.getInstance("MD5");
		md5hash = new byte[32];
		md.update(data.getBytes("UTF-8"), 0, data.length());
		md5hash = md.digest();
		return md5hash;
	}
	 
	private static String convertToHex(byte[] data) {
		//Turns a byte array in to a hex string
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
	    int halfbyte = (data[i] >>> 4) & 0x0F;
	    int two_halfs = 0;
	    do {
	    	if ((0 <= halfbyte) && (halfbyte <= 9))
	    		buf.append((char) ('0' + halfbyte));
	    	else
	    		buf.append((char) ('a' + (halfbyte - 10)));
	    	halfbyte = data[i] & 0x0F;
	       	} while(two_halfs++ < 1);
		}
		String hash = buf.toString();
		buf = null;
	    return hash;
	}
}
