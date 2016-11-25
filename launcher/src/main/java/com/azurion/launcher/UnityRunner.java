package com.azurion.launcher;

import java.io.IOException;
import javax.swing.JOptionPane;


public class UnityRunner {
	private Runtime runtime = Runtime.getRuntime();
	public void run(String[] array){
		try {
			runtime.traceMethodCalls(true);
			runtime.exec(array[0]);
			runtime.exit(0);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Cannot run the executable", "Error", JOptionPane.INFORMATION_MESSAGE);
			runtime.exit(0);
		}
	}
}
