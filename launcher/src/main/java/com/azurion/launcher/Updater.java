package com.azurion.launcher;

import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.lib.ProgressMonitor;
import org.eclipse.jgit.lib.RepositoryCache;
import org.eclipse.jgit.lib.TextProgressMonitor;
import org.eclipse.jgit.util.FS;

public class Updater {
	private Git repo;
	private String branch;
	private String REMOTE_URL;
	private ProgressMonitor monitor;
	private final static Logger logger = LoggerFactory.getLogger(Updater.class);
	
	public Updater(String remote_url, String SpecificBranch, UpdaterMonitor monitor){
		this.REMOTE_URL = remote_url;
		if (monitor != null)
			this.monitor = monitor;
		else
			this.monitor = new TextProgressMonitor(new PrintWriter(System.out));
		if (SpecificBranch == null)
			this.branch = this.selectBranch();
		else
			this.branch = SpecificBranch;
	}
	
	public void finalize(){
		this.repo.getRepository().close();
	}
	
	private String selectBranch(){
		Machine machine = new Machine();
		if (machine.isWindowsOS())
			return "Windows";
		else if (machine.isMacOS())
			return "Mac";
		else if (machine.isLinuxOS())
			return "Linux";
		return "Test";
	}
	
	
	public boolean repositoryRecovery(File dir, boolean local){
		try{
			if (RepositoryCache.FileKey.isGitRepository(new File(dir.getAbsolutePath(), ".git"), FS.DETECTED)){
				this.repo = Git.open(new File(dir.getAbsolutePath(), ".git"));
				this.repo.checkout().setName("origin/" + this.branch).call();
				this.repo.pull().setProgressMonitor(this.monitor).call();
			}
			else {
				if(!dir.delete() && !local){
					return false;
				}
				this.repo =  Git.cloneRepository().setURI(REMOTE_URL).setDirectory(dir).setBranch(this.branch).setProgressMonitor(this.monitor).call();
					
				this.repo.checkout().setName("origin/" + this.branch).call();
				this.finalize();
			}
			return true;
		} catch (GitAPIException e) {
			JOptionPane.showMessageDialog(null, "Problème d'accès GIT", "Information", JOptionPane.ERROR_MESSAGE);
			logger.error("GitAPIException : " + e.getMessage());
			return false;
		} catch (IOException e){
			JOptionPane.showMessageDialog(null, "Problème d'accès fichier", "Information", JOptionPane.ERROR_MESSAGE);
			logger.error("IOException : " + e.getMessage());
			return false;
		} catch (JGitInternalException e){
			JOptionPane.showMessageDialog(null, "Problème de récupération du dépot", "Information", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public boolean repositoryRecovery(){ // current repository
		return repositoryRecovery(new File("Azurion", ""), true);
	}
	
	public Git getRepository(){
		return repo;
	}

}
