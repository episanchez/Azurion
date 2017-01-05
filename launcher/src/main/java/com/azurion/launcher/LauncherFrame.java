
package com.azurion.launcher;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.azurion.launcher.window.BackgroundPanel;


public class LauncherFrame extends JFrame implements ActionListener 
{    
	/**
	 * 
	 */
	private static final long serialVersionUID = 7040543573864484053L;
	private static LauncherFrame instance;    
	private JLabel titre;    
	private JTextField pseudo;    
	private JPasswordField mdp;    
	private JButton jouer;    
	private JProgressBar mainPb;
	private JProgressBar percentPb;
	
	public Image getIconImage(String imageName){
		Image img = null;
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(imageName);
		
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return img;
	}
	
	public LauncherFrame() {
		
		int width = 850;
		int height = 500;
		
		this.setTitle("Azurion Launcher");
		this.setSize(width, height);    
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);        
		this.setLocationRelativeTo(null);        
		this.setLayout(null);
		this.setContentPane(new BackgroundPanel("background.jpg", width, height));
		this.setIconImage(this.getIconImage("logo.png"));
		
		
		titre = new JLabel("Azurion Launcher", SwingConstants.CENTER);      
		titre.setForeground(Color.WHITE);       
		titre.setFont(titre.getFont().deriveFont(60f));       
		titre.setBounds(0, 20, 850, 100);
		titre.setPreferredSize(new Dimension(850, 100));
		this.add(titre);
		
		
		pseudo = new JTextField("Pseudo");      
		pseudo.setBounds(350, 275, 150, 20);
		pseudo.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
				  if(pseudo.getText().equals("Pseudo"))
		            {
		               	pseudo.setText("");
		                repaint();
		                revalidate();
		            }  
			  }
		});
		this.add(pseudo);     
		
		mdp = new JPasswordField("Mot de Passe");       
		mdp.setBounds(350, 305, 150, 20);
		mdp.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
				  mdp.setText("");  
			  }
		});
		this.add(mdp);              
		
		jouer = new JButton("Jouer !");        // Enregistre la fenetre entant qu'ActionListener      
		jouer.addActionListener(this);     
		jouer.setBounds(350, 335, 150, 20);
		this.add(jouer);
		
		mainPb = new JProgressBar();
		mainPb.setStringPainted(true);
		mainPb.setBounds(0, 380, 850, 40);
		this.add(mainPb);
		
		percentPb = new JProgressBar();
		percentPb.setStringPainted(true);
		percentPb.setBounds(0,430, 850, 40);
		this.add(percentPb);
		
		mainPb.setVisible(false);
		percentPb.setVisible(false);
		this.setVisible(true);
		
	}   

	// When the auth succeed 
	private void shadowLoginWindow(){
		this.pseudo.setVisible(false);
		this.mdp.setVisible(false);
		this.jouer.setVisible(false);
		this.mainPb.setVisible(true);
		this.percentPb.setVisible(true);
	}
	
	@Override   
	public void actionPerformed(ActionEvent e) {    
		Thread t = new Thread() {           
			@Override           
			public void run() {
				UnityRunner runner = new UnityRunner();
				String argus = null;
				HttpRequest http = new HttpRequest(pseudo.getText(), mdp.getPassword().toString());

				if ((pseudo.getText() == "beta" && mdp.getPassword().toString() == "beta")|| 
						(argus = http.get("http://localhost:8081/api/0.2/login")) != "Unauthorized" ){
					shadowLoginWindow();
					Updater up = new Updater("https://github.com/episanchez/VerAzurion.git", null, new UpdaterMonitor(mainPb, percentPb));
					up.repositoryRecovery();
					URL exec = null;
					try {
						exec = new URL( Paths.get(".").toUri().toURL() , Paths.get("./Azurion/Azurion_windows.exe").toString());
					} catch (HeadlessException | MalformedURLException e) {
						JOptionPane.showMessageDialog(null, "Cannot run the executable : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
					}
					runner.run(new String[]{exec.getPath().toString(), argus});
				}
			}       
		};      
		t.start(); 
	}
	
	
	public static void main(String[] args) {  
		// Astuce pour avoir le style visuel du systeme hôte 
		try {      
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());   
			} catch (Exception e) { 
				e.printStackTrace(); 
				}        
		instance = new LauncherFrame(); 
		}    // Retourne l'instance de LauncherFrame    
	public static LauncherFrame getInstance() { 
		return instance;  
		}    // Retourne l'instance de notre progress bar  
	public JProgressBar getProgressBar() {     
		return mainPb;  
		}
}


