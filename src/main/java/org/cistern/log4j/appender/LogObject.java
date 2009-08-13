package org.cistern.log4j.appender;

public class LogObject {
	
	String data; //Static Log Data//
	String payload; //Dynamic Log Data//
	String authkey; //Agent Authkey//
	String logtype_id; //logtype_id for cistern server//
	String loglevel_id; //loglevel to send//
	String etime; //Event time//
	String agent_id;
	
	/*Constuctors to set defaults*/
	public LogObject(String data, String payload, String authkey,
			String logtype_id, String loglevel_id, String etime, String agent_id) {
		super();
		this.data = data;
		this.payload = payload;
		this.authkey = authkey;
		this.logtype_id = logtype_id;
		this.loglevel_id = loglevel_id;
		this.etime = etime;
		this.agent_id = agent_id;
	}
	
	public LogObject(String data, String payload, String authkey,
			String logtype_id, String agent_id) {
		super();
		this.data = data;
		this.payload = payload;
		this.authkey = authkey;
		this.logtype_id = logtype_id;
		this.loglevel_id = "6";
		this.agent_id = agent_id;
		this.etime = Long.toString(System.currentTimeMillis()/1000);
	}
	
	

	public LogObject(String data, String payload, String logtype_id, String agent_id) {
		super();
		this.data = data;
		this.payload = payload;
		this.logtype_id = logtype_id;
		this.loglevel_id = "6";
		this.agent_id = agent_id;
		this.etime = Long.toString(System.currentTimeMillis()/1000);
		this.authkey = "";
	}

	/*Methods*/
	
	public String getData() {
		return data;
	}
	public String getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}

	public void setData(String data) {
		this.data = data;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public String getAuthkey() {
		return authkey;
	}
	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}
	public String getLogtype_id() {
		return logtype_id;
	}
	public void setLogtype_id(String logtype_id) {
		this.logtype_id = logtype_id;
	}
	public String getLoglevel_id() {
		return loglevel_id;
	}
	public void setLoglevel_id(String loglevel_id) {
		this.loglevel_id = loglevel_id;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	
	
	

}
