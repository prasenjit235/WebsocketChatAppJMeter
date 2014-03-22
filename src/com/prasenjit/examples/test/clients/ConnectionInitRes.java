package com.prasenjit.examples.test.clients;

import java.util.ArrayList;
import java.util.List;



public class ConnectionInitRes implements WebsocketResponse{

	private int status;
	private String connectorId;
	private String responseId;
	private int replyMode;
	private ArrayList<String> connectorIds;
	
	public ConnectionInitRes() {
	}
	
	public ConnectionInitRes(int status) {
		this.status=status;
	}

	@Override
	public int getReplyMode() {
		return replyMode;
	}

	
	public void setConnectorId(String connectorId) {
		this.connectorId = connectorId;
	}

	@Override
	public String toString() {
		return "ConnectionInitRes [status=" + status + ", connectorId="
				+ connectorId + "]";
	}

	@Override
	public String getConnectorId() {
		return connectorId;
	}

	public int getStatus() {
		return status;
	}

	public String getResponseId() {
		return responseId;
	}

	@Override
	public List<String> getConnectorIds() {
		// TODO Auto-generated method stub
		return connectorIds;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	public void setReplyMode(int replyMode) {
		this.replyMode = replyMode;
	}

	public void setConnectorIds(ArrayList<String> connectorIds) {
		this.connectorIds = connectorIds;
	}

	
	
	
}
