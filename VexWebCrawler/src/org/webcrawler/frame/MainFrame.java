package org.webcrawler.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import org.webcrawler.model.WebCrawler;
import org.webcrawler.threads.CrawlingThread;
import org.webcrawler.threads.SkipThread;
import org.webcrawler.threads.ThreadManager;

import com.firebase.client.Firebase;

public class MainFrame extends JFrame implements ActionListener {
	private static MainFrame instance;
	private int skips = 0;
	private JTextField maxThreadsFields = new JTextField("100", 10);
	private JTextField timeoutFields = new JTextField("60", 10);
	private JLabel skipsLabel = new JLabel("Skips: 00000");
	private JLabel percentageLabel = new JLabel("Percentage: 00000%");
	private JLabel detailJLabel = new JLabel("Details");
	private JButton startButton = new JButton("Start!");
	private JButton removeButton = new JButton("Clear Everything");
	private JButton compButton = new JButton("Clear Only Competitions");

	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}

	private MainFrame() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 500);
		setVisible(true);
		setLayout(new BorderLayout());

		detailJLabel.setMaximumSize(new Dimension(1000, 1000));
		startButton.addActionListener(this);
		removeButton.addActionListener(this);
		compButton.addActionListener(this);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(new JLabel("Max Threads:"));
		panel.add(maxThreadsFields);

		panel.add(new JLabel("Timeout: "));
		panel.add(timeoutFields);

		panel.add(skipsLabel);
		panel.add(percentageLabel);

		this.add(panel, BorderLayout.NORTH);
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		southPanel.add(startButton);
		southPanel.add(removeButton);
		southPanel.add(compButton);
		
		this.add(southPanel, BorderLayout.SOUTH);
		this.add(detailJLabel, BorderLayout.CENTER);

	}

	public void setSkips(int skips) {
		skipsLabel.setText("Skips: " + skips);
	}
	
	public void addSkip() {
		this.skips++;
		skipsLabel.setText("Skips: " + this.skips);
		skipsLabel.paintImmediately(skipsLabel.getVisibleRect());
	}
	
	public void setPercentage(double d) {
		DecimalFormat df = new DecimalFormat("#.##");
        //System.out.print(df.format(d));

		percentageLabel.setText("Percentage: " + String.valueOf(d) + "%");
		percentageLabel.paintImmediately(percentageLabel.getVisibleRect());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		detailJLabel.setText("Starting");
		if (e.getActionCommand().equals(startButton.getActionCommand())) {
			detailJLabel.setText("Getting all the links from robotevents (this can take anywhere from 20 sec to 2 min) :(");
			detailJLabel.paintImmediately(detailJLabel.getVisibleRect());
			
			ThreadManager.setLinks(WebCrawler.getCompetitions());
			
			detailJLabel.setText("Done!");
			detailJLabel.paintImmediately(detailJLabel.getVisibleRect());
			try{
				Thread.sleep(500);
			}catch(Exception e2) {
				
			}
			detailJLabel.setText("Creating " + maxThreadsFields.getText() + " threads!");
			//make so many threads
			for (int i = 0; i < Integer.parseInt(maxThreadsFields.getText()); i++) {
				CrawlingThread t = new CrawlingThread();
				t.setTIMEOUT(1000*Integer.parseInt(timeoutFields.getText()));
				t.start();
			}
			for (int i = 0; i < 5; i++) {
				SkipThread t = new SkipThread();
				t.start();
			}
			detailJLabel.setText("Threads Created! Now data is being borrowed (stolen)");
			detailJLabel.paintImmediately(detailJLabel.getVisibleRect());
		}else if(e.getActionCommand().equals(removeButton.getActionCommand())) {
			removeAllData();
		}else if(e.getActionCommand().equals(compButton.getActionCommand())) {
			removeAllCompData();
		}
	}

	public void subtractSkips() {
		this.skips--;
		skipsLabel.setText("Skips: " + this.skips);
		skipsLabel.paintImmediately(skipsLabel.getVisibleRect());
		
	}
	private static void removeAllData() {
		Firebase ref = new Firebase("https://vexscout.firebaseio.com/");
		System.out.println("Removing!");
		ref.removeValue();
		System.out.println("Removing!");
	}
	private static void removeAllCompData() {
		Firebase ref = new Firebase("https://vexscoutcompetitions.firebaseio.com/");
		System.out.println("Removing!");
		ref.removeValue();
		System.out.println("Removing!");
	}
}
