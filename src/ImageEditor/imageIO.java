package ImageEditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class imageIO {

	private JFrame frame;
	private boolean loaded = false;
	
	private imageManipulation mm;
	
	private final JFileChooser openFileChooser;
	private final JFileChooser saveFileChooser;
	public BufferedImage originalBI = null , newBI = null;
	
	public ImageIcon RsizeImage(BufferedImage BI) {
		Image image = BI;
		Image newImage = image.getScaledInstance(700 , 400, Image.SCALE_SMOOTH);
		ImageIcon mg = new ImageIcon(newImage);
		return mg;
	}

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			imageIO window = new imageIO();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * Create the application.
	 */
	public imageIO() {
		initialize();
		
		openFileChooser = new JFileChooser();
		openFileChooser.setCurrentDirectory(new File("c:\\images"));
		openFileChooser.setFileFilter(new FileNameExtensionFilter("PNG images" , "png"));
		
		saveFileChooser = new JFileChooser();
		saveFileChooser.setCurrentDirectory(new File("c:\\images"));
		saveFileChooser.setFileFilter(new FileNameExtensionFilter("PNG images" , "png"));
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 943, 575);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton openFileButton = new JButton("Open file");
		openFileButton.setBackground(new Color(192, 192, 192));
		openFileButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		openFileButton.setHorizontalAlignment(SwingConstants.LEFT);
		openFileButton.setBounds(10, 490, 85, 21);
		frame.getContentPane().add(openFileButton);
		
		JLabel messageLabel = new JLabel("");
		messageLabel.setBounds(105, 490, 308, 16);
		frame.getContentPane().add(messageLabel);
		
		JLabel imageBox = new JLabel();
		imageBox.setBounds(100 , 100 , 700 , 400);
		frame.getContentPane().add(imageBox);
		
		JButton saveFileButten = new JButton("Save file");
		saveFileButten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				saveImage();
				
			}

			private void saveImage() {
				
				if(loaded) {
					int val = saveFileChooser.showSaveDialog(null);
					
					if(val == JFileChooser.APPROVE_OPTION) {
						try {
							ImageIO.write(newBI , "png" , saveFileChooser.getSelectedFile());
							messageLabel.setText("Image file successfully saved!");
							
						}catch(IOException io) {
							messageLabel.setText("Failed to save the image!");
						}
					}else {
						messageLabel.setText("No file choosen!");
					}
					
				}else {
					messageLabel.setText("There is no loaded image!");
				}

			}
		});
		saveFileButten.setHorizontalAlignment(SwingConstants.LEFT);
		saveFileButten.setFont(new Font("Tahoma", Font.BOLD, 10));
		saveFileButten.setBounds(834, 490, 85, 21);
		frame.getContentPane().add(saveFileButten);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnManipulations = new JMenu("Manipulations");
		menuBar.add(mnManipulations);
		
		JMenuItem mntmCrop = new JMenuItem("Crop");
		mntmCrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(loaded) {
					
					if(e.getActionCommand().equals("Crop")) {
						
						JTextField xField = new JTextField(5);
					    JTextField yField = new JTextField(5);
					    JTextField hField = new JTextField(5);
					    JTextField wField = new JTextField(5);

					    JPanel p = new JPanel();
					    p.add(new JLabel("X cordintae:"));
					    p.add(xField);
					    p.add(Box.createHorizontalStrut(15)); // a spacer
					    p.add(new JLabel("Y cordinate:"));
					    p.add(yField);
					    p.add(Box.createHorizontalStrut(15)); // a spacer
					    p.add(new JLabel("Hieght:"));
					    p.add(hField);
					    p.add(Box.createHorizontalStrut(15)); // a spacer
					    p.add(new JLabel("Wiedth:"));
					    p.add(wField);

					    int result = JOptionPane.showConfirmDialog(null, p, 
					             "Getting the desired paramter for croping!", JOptionPane.OK_CANCEL_OPTION);
					    if (result == JOptionPane.OK_OPTION) {
					    	int x = Integer.parseInt(xField.getText());
					    	int y = Integer.parseInt(yField.getText());
					    	int h = Integer.parseInt(hField.getText());
					    	int w = Integer.parseInt(wField.getText());
					    	  
					    	mm = new imageCropper(originalBI);
								
					    	try {
								newBI = mm.crop(originalBI, w, h, x, y);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					    	
					    	messageLabel.setText("Image Cropping has done successfully!!");
							
							imageBox.setIcon(RsizeImage(newBI));
					    	  
					     }else if(result == JOptionPane.CANCEL_OPTION){
					    	 messageLabel.setText("Image Cropping has canceled!!");
					     }
					}else {
						messageLabel.setText("No file choosen!");
					}
					
				}else {
					messageLabel.setText("There is no loaded image!");
				}
				
			}
			
		});
		mnManipulations.add(mntmCrop);
		
		JSeparator separator_1 = new JSeparator();
		mnManipulations.add(separator_1);
		
		JMenuItem mntmRsize = new JMenuItem("Rsize");
		mntmRsize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(loaded) {
				
					if(e.getActionCommand().equals("Rsize")) {
						JTextField hField = new JTextField(5);
						JTextField wField = new JTextField(5);

						JPanel p = new JPanel();
						p.add(new JLabel("Hieght:"));
						p.add(hField);
						p.add(Box.createHorizontalStrut(15)); // a spacer
						p.add(new JLabel("Wiedth:"));
						p.add(wField);
				    

						int result = JOptionPane.showConfirmDialog(null, p, 
				             "Getting the desired paramter for resizing!", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							int h = Integer.parseInt(hField.getText());
							int w = Integer.parseInt(wField.getText());
				    	  
							mm = new imageResizer(originalBI);
							newBI = mm.resize(originalBI, w, h);
							imageBox.setBounds(100 , 100 , w , h);
							Image image = newBI;
							ImageIcon mg = new ImageIcon(image);
							imageBox.setIcon(mg);
							
							messageLabel.setText("Image Resizing has done successfully!!");
							
						}else {
							messageLabel.setText("Image Resizing has canceled!!");
						}
					
					}else {
						messageLabel.setText("No file choosen!");
					}
				
				}else {
					messageLabel.setText("There is no loaded image!");
				}
				
			}
			
		});
		mnManipulations.add(mntmRsize);
		
		JSeparator separator_2 = new JSeparator();
		mnManipulations.add(separator_2);
		
		JMenuItem mntmBlackwight = new JMenuItem("Black&Wight");
		mntmBlackwight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(loaded) {
				
					if(e.getActionCommand().equals("Black&Wight")) {
						
						mm = new colorConversion(originalBI);
					
						newBI = mm.convertToBW();
					
						messageLabel.setText("Color conversion has done successfully!!");
					
						imageBox.setIcon(RsizeImage(newBI));
					}else {
						messageLabel.setText("No file choosen!");
					}
				}else {
					messageLabel.setText("There is no loaded image!");
				}
				
			}
			
		});
		mnManipulations.add(mntmBlackwight);
		
		JSeparator separator = new JSeparator();
		mnManipulations.add(separator);
		
		JMenu mnFilters = new JMenu("Filters");
		mnManipulations.add(mnFilters);
		
		JMenuItem mntmSharpen = new JMenuItem("Sharpen");
		mntmSharpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(loaded) {
				
					if(e.getActionCommand().equals("Sharpen")) {
					
						mm = new imageManipulation(originalBI , 1);
					
						newBI = mm.filter();
						messageLabel.setText("Sharpen filter has done successfully!!");
					
						imageBox.setIcon(RsizeImage(newBI));
					}else {
						messageLabel.setText("No file choosen!");
					}
				}else {
					messageLabel.setText("There is no loaded image!");
				}
			}
			
		});
		mnFilters.add(mntmSharpen);
		
		JMenuItem mntmSmooth = new JMenuItem("Smooth");
		mntmSmooth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(loaded) {
				
					if(e.getActionCommand().equals("Smooth")) {
					
						mm = new imageManipulation(originalBI , 2);
					
						newBI = mm.filter();
						messageLabel.setText("Smoothing filter has done successfully!!");
					
						imageBox.setIcon(RsizeImage(newBI));
					}else {
						messageLabel.setText("No file choosen!");
					}
				}else {
					messageLabel.setText("There is no loaded image!");
				}
				
			}
			
		});
		mnFilters.add(mntmSmooth);
		
		JMenuItem mntmEdgeDetection = new JMenuItem("Edge Detection");
		mntmEdgeDetection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(loaded) {
				
					if(e.getActionCommand().equals("Edge Detection")) {
					
						mm = new imageManipulation(originalBI , 3);
					
						newBI = mm.filter();
						messageLabel.setText("Edge detecting filter has done successfully!!");
					
						imageBox.setIcon(RsizeImage(newBI));
					}else {
						messageLabel.setText("No file choosen!");
					}
				}else {
					messageLabel.setText("There is no loaded image!");
				}
			}
			
		});
		mnFilters.add(mntmEdgeDetection);
		
		openFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int val = openFileChooser.showOpenDialog(null);
				if(val == JFileChooser.APPROVE_OPTION) {
					try {
						originalBI = ImageIO.read(openFileChooser.getSelectedFile());
						messageLabel.setText("Image file successfully loaded!");
						
						loaded = true;
						
						imageBox.setIcon(RsizeImage(originalBI));
						
					}catch(IOException io) {
						messageLabel.setText("Failed to load the image!");
					}
				}else {
					messageLabel.setText("No file choosen!");
				}
				
			}

		});
	}
}
