package org.cistern.appender.log4j;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
// import org.apache.log4j.spi.ErrorCode; - Used for log4j exceptions
import org.cistern.appender.log4j.LogObject;
import org.cistern.appender.log4j.Util;

import java.security.NoSuchAlgorithmException;

import java.io.*;
import java.net.*;




public class TCPAppender extends AppenderSkeleton {
	
	//Setup variables
	String serverhost;
	int serverport;
	String agent_id;
	String logtype_id;
	String authkey;
	int timeout;
	
	static String eq = "/000/";
	static String newval = "/111/";
	Socket sock;

	//Setup loglevels
	String fatal = "1";
	String error = "2";
	String warn = "3";
	String info = "4";
	String debug = "5";
	String level;

	protected void append(LoggingEvent arg0) {
		// Log4j calls this with a log event
		String s;
		LoggingEvent entry = arg0;
		if (entry.getThrowableStrRep() == null){
			s = "";
		} else {
			s = entry.getThrowableStrRep()[0]; //This needs fixing...
		}
		LogObject event = new LogObject("<<<MESSAGE>>> " + s, "MESSAGE" + eq + entry.getMessage(), this.authkey, this.logtype_id, this.agent_id);

		if (entry.getLevel() == Level.DEBUG) {
			level = debug;
		}else if (entry.getLevel() == Level.INFO){
			level = info;
		}else if (entry.getLevel() == Level.WARN){
			level = warn;
		}else if (entry.getLevel() == Level.ERROR){
			level = error;
		}else if (entry.getLevel() == Level.FATAL){
			level = fatal;
		}else{
			level = "6";
		}
		System.out.println(entry.getLevel());
		event.setLoglevel_id(level);
		//event.setEtime(Long.toString(entry.getTimeStamp()/1000));
		
		String data = null;
		try {
			data = Util.getSendableString(event);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println(data);
		try {
			sendData(data);
		} catch (UnknownHostException e) {
			 e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void sendData(String data) throws IOException {
		try {
			PrintWriter out = null;
			out = new PrintWriter(sock.getOutputStream(), true);		
			out.println(data);
			data = null;
		} catch (SocketTimeoutException e) {
			System.out.println("Socket Connection Timedout");
		}
	}

	public void close() {
		try {
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	public boolean requiresLayout() {
		return false;
	}
	
	public void activateOptions(){ 
		Socket socket = new Socket();
		int retry = 3;
		int n = 0;
		try {
			socket.connect(new InetSocketAddress(serverhost, serverport), timeout);
			this.setSock(socket);
		} catch (SocketTimeoutException e) {
			System.out.println("Socket Connection Timedout");
		} catch (IOException e) {
			try {
				while (retry >= n){
				Thread.currentThread();
				Thread.sleep(100);
				activateOptions();
				n = n + 1;
				}
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
	

	public String getServerhost() {
		return serverhost;
	}

	public void setServerhost(String serverhost) {
		this.serverhost = serverhost;
	}

	public int getServerport() {
		return serverport;
	}

	public void setServerport(int serverport) {
		this.serverport = serverport;
	}

	public String getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}

	public String getLogtype_id() {
		return logtype_id;
	}

	public void setLogtype_id(String logtype_id) {
		this.logtype_id = logtype_id;
	}

	public String getAuthkey() {
		return authkey;
	}

	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	public Socket getSock() {
		return sock;
	}

	public void setSock(Socket sock) {
		this.sock = sock;
	}
	
}
