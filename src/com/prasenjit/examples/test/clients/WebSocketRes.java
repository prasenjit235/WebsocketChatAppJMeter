package com.prasenjit.examples.test.clients;

import java.util.ArrayList;
import java.util.List;

public class WebSocketRes implements WebsocketResponse{

	private int status;
	private String connectorId;
	private String responseId;
	private int replyMode;
	private ArrayList<String> connectorIds;
	
	@Override
	public int getReplyMode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getConnectorId() {
		// TODO Auto-generated method stub
		return this.connectorId;
	}

	@Override
	public List<String> getConnectorIds() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getResponseId() {
		return responseId;
	}

	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	public void setConnectorId(String connectorId) {
		this.connectorId = connectorId;
	}

	public void setReplyMode(int replyMode) {
		this.replyMode = replyMode;
	}

	public void setConnectorIds(ArrayList<String> connectorIds) {
		this.connectorIds = connectorIds;
	}

}
