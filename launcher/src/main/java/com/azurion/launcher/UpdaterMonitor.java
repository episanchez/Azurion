/**
 * 
 */
package com.azurion.launcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JProgressBar;

import org.eclipse.jgit.lib.ProgressMonitor;
/**
 * @author episanchez
 * @version 0.1
 */

public class UpdaterMonitor implements ProgressMonitor {
	
	Logger logger = LoggerFactory.getLogger(Updater.class);
	JProgressBar mainBar = null;
	JProgressBar percentBar = null;
	
	public UpdaterMonitor(JProgressBar mbar, JProgressBar pbar){
		this.mainBar = mbar;
		this.percentBar = pbar;
	}
	public void beginTask(String title, int totalWork){
		mainBar.setString(title);
		percentBar.setMinimum(0);
		percentBar.setMaximum(totalWork);
		percentBar.setValue(0);
	}
	    
	public void endTask(){
		mainBar.setValue(mainBar.getValue() + 1);
	}

	  public boolean isCancelled(){
		return false;
		}

	  public void start(int totalTasks){
		  mainBar.setMinimum(0);
		  mainBar.setMaximum(totalTasks);
		  mainBar.setValue(0);
	  }

	  public void update(int completed){
		  percentBar.setValue(percentBar.getValue() + completed);
	  }
}
