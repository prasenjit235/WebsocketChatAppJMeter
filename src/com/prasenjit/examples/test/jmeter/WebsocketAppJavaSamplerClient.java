package com.prasenjit.examples.test.jmeter;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.util.JMeterUtils;

import com.prasenjit.examples.test.clients.WebsocketClient;

public class WebsocketAppJavaSamplerClient extends AbstractJavaSamplerClient{

	WebsocketClient client=null;
	
	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		SampleResult result=new  SampleResult();
		result.setSampleLabel("Test Websocket");
		result.setDataType(SampleResult.TEXT);
		result.sampleStart();
		try{
			String uri=arg0.getParameter("URI");
			String user="login"+arg0.getParameter("user");
			if (client==null) {
				client=new WebsocketClient(); 
				JMeterContextService.getContext().getVariables().putObject(user, client);
				JMeterContextService.getContext().getVariables().put("key", "value is this!!");
				client.start(uri,user);
			}
		result.setSuccessful(true);
		result.setResponseCodeOK();
		result.setResponseMessage("Handshake successful");
		result.setResponseData(("HandShake Request :: ").getBytes());
		}catch (Throwable e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			result.setSuccessful(false);
			result.setResponseMessage("Unexpected exception");
			result.setResponseData(sw.toString().getBytes());
		}
		
		result.sampleEnd();
		return result;
	}

	@Override
	public Arguments getDefaultParameters() {
		Arguments arguments=new Arguments();
		arguments.addArgument("URI", "");
		arguments.addArgument("user","");
		return arguments;
	}
	
	/*@Override
	public void setupTest(JavaSamplerContext context) {
		String uri=context.getParameter("URI");
		if (client==null) {
			client=new WebsocketClient();
			client.start(uri);
		}
	}*/
	
	
}
