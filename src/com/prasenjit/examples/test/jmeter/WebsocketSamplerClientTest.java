package com.prasenjit.examples.test.jmeter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.threads.JMeterContextService;

import com.prasenjit.examples.test.clients.WebsocketClient;

public class WebsocketSamplerClientTest extends AbstractJavaSamplerClient{

	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		System.out.println("WebsocketSamplerClientTest.runTest()");
		SampleResult result=new  SampleResult();
		result.setSampleLabel("Test Websocket Next Sequence");
		result.setDataType(SampleResult.TEXT);
		result.sampleStart();
		try{
		String user="login"+arg0.getParameter("user");
		System.out.println("WebsocketSamplerClientTest::user::"+user);
		WebsocketClient client=(WebsocketClient)JMeterContextService.getContext().getVariables().getObject(user);//getObject("websocketclient");
		/*String value=JMeterContextService.getContext().getVariables().get("key");
		System.out.println("@@@@Value####"+value);
		value=JMeterContextService.getContext().getVariables().get("key1");
		System.out.println("@@@@Value for key1####"+value);*/
		Random random=new Random();
		for (int i = 1; i <= 30; i++) {
			int generatedNo=random.nextInt(50);
			String frnd="loginlogin"+generatedNo;
			if (!frnd.equalsIgnoreCase(user)) {
				client.sendFrndToFrndMsg(user, frnd);
			}
		}
//		client.sendHandShakeReq();
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
		arguments.addArgument("user", "");
		return arguments;
	}
	
	@Override
	public void setupTest(JavaSamplerContext context) {
		// TODO Auto-generated method stub
		super.setupTest(context);
	}
}
