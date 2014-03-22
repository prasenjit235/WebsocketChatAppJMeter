package com.prasenjit.examples.test.clients;

import java.util.List;

public interface WebsocketResponse {

	public int getReplyMode();
	public String getConnectorId() ;
	public List<String> getConnectorIds();
}
