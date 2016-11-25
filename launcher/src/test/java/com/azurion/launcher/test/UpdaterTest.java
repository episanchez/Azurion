package com.azurion.launcher.test;

import org.junit.Test;
import com.azurion.launcher.UnityRunner;

public class UpdaterTest {
	@Test
	public void runUnityTest(){
		UnityRunner runner = new UnityRunner();
		
		runner.run(new String[]{"cmd.exe", "echo", "lol"});
	}
	
	/*
	@Test
	public void httpTest(){
		HttpRequest http = new HttpRequest("toto", "test");
		
		http.get("http://localhost:8081/api/login");
	}
	*/
}
