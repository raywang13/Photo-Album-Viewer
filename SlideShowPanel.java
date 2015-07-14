package project3;
//Import the basic graphics classes.
import java.awt.*;

import javax.imageio.ImageIO;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SlideShowPanel extends JPanel {

	Timer timer;

	private JButton prevButton, nextButton, exitButton, loadPhotosButton, saveButton, openButton;
	BufferedImage image = null;
	int i = 0;
	String result = "Please load the photo library first.";

	
	
	ArrayList<String> photoList = new ArrayList<String>();
	
	// Constructor for the panel 
	SlideShowPanel() {
	
		try {
			image = ImageIO.read(new File("/Users/raywang/Documents/USF/CS112/workspace/project3/security.jpg"));
		} catch (IOException e) {
		}

		this.setLayout(new BorderLayout());

		// buttonPanelTop contains the top row of buttons:
		// load photos files, save and open
		JPanel buttonPanelTop = new JPanel();
		buttonPanelTop.setLayout(new GridLayout(1, 3));
		loadPhotosButton = new JButton("Load photos");
		saveButton = new JButton("Save Library");
		openButton = new JButton("Load Library");

		loadPhotosButton.addActionListener(new MyButtonListener());
		saveButton.addActionListener(new MyButtonListener());
		openButton.addActionListener(new MyButtonListener());

		buttonPanelTop.add(loadPhotosButton);
		buttonPanelTop.add(saveButton);
		buttonPanelTop.add(openButton);
		this.add(buttonPanelTop, BorderLayout.NORTH);

		// Bottom panel with panels: Previous, Next, Exit buttons
		JPanel buttonPanelBottom = new JPanel();
		buttonPanelBottom.setLayout(new GridLayout(1, 3));
		prevButton = new JButton("Previous");
		nextButton = new JButton("Next");
		exitButton = new JButton("Exit");

		prevButton.addActionListener(new MyButtonListener());
		nextButton.addActionListener(new MyButtonListener());
		exitButton.addActionListener(new MyButtonListener());

		buttonPanelBottom.add(prevButton);
		buttonPanelBottom.add(nextButton);      
		buttonPanelBottom.add(exitButton);
		this.add(buttonPanelBottom, BorderLayout.SOUTH);

	}

	public void paintComponent(Graphics g) {

		// Draw our Image object.
		g.drawImage(image, 0, 0, 800, 600, this); // at location 50,10
		// 200 wide and high
	}

	class MyButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == loadPhotosButton) {
				System.out.println("Load photos button");	
				// 1) read all the photos files from photos directory using recursion
				// 2) Display the first image in the arraylist  
				Traverse c = new Traverse();
				try {
					c.traverseArray(new File("/Users/raywang/Pictures/pics"), photoList);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				try {
					image = ImageIO.read(new File(photoList.get(i)));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				updateUI();
				
				timer = new Timer(5000, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						nextButton();
					}
				});
				//timer.setInitialDelay(pause);
				timer.start(); 

			} else if (e.getSource() == saveButton) {
				// Save all the names in the arraylist to a file called output.txt
				PhotoSaver b = new PhotoSaver();
				b.photoSave(photoList);
				
			} else if (e.getSource() == openButton) {
				// open the file output.txt and load photo file paths 
				// into the arraylist of strings
				PhotoLoader a = new PhotoLoader();
				try {
					a.photoLoad(photoList);
					image = ImageIO.read(new File(photoList.get(i)));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				updateUI();	
				
				timer = new Timer(5000, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						nextButton();
					}
				});
				//timer.setInitialDelay(pause);
				timer.start(); 
				
			} else if (e.getSource() == prevButton) {
				if(photoList.size() == 0) {
					JOptionPane.showMessageDialog(null, result);
				}
				if(i == 0) {
					i = photoList.size() - 1;
				}
				i--;
				try {
					image = ImageIO.read(new File((String) photoList.get(i)));
				} catch (IOException e1) {
				}
				updateUI();

			} else if (e.getSource() == nextButton) {
//				if(photoList.size() == 0) {
//					JOptionPane.showMessageDialog(null, result);
//				}
//				if(i == photoList.size() - 1) {
//					i = 0;
//				}
//				i++;
//					try {
//						image = ImageIO.read(new File((String) photoList.get(i)));
//					} catch (IOException e1) {
//					}
//				updateUI();
				nextButton();
				
			} else if (e.getSource() == exitButton) {
				// Exit the program
				System.exit(0);
			}
		}
	} //end of buttonListener

	public static void main(String[] args) {
		JFrame frame = new JFrame("Slide show program");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SlideShowPanel panel = new SlideShowPanel();
		panel.setPreferredSize(new Dimension(800, 600));
		frame.getContentPane().add(panel);

		frame.pack();
		frame.setVisible(true);
		
		
	} //end of main
	
	public void nextButton() {
		//created a nextButton method so I can call it in my timer
		//instead of having a block of code
		if(photoList.size() == 0) {
			JOptionPane.showMessageDialog(null, result);
		}
		if(i == photoList.size() - 1) {
			i = 0;
		}
		i++;
			try {
				image = ImageIO.read(new File((String) photoList.get(i)));
			} catch (IOException e1) {
			}
		updateUI();
	}
		
} //end of class