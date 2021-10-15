package project2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.Hashtable;
import java.util.Map;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;

import sentimentanalysis.Tweet;
import sentimentanalysis.TweetCollection;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;


public class MainPanel extends JPanel {

	private TweetCollection tc;
	private JTextField userName;
	private JComboBox comboBox; 
	private JLabel tweetData;
	private JSlider slider;
	private final JTextArea textArea = new JTextArea();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField predText;
	private JTextField predResult;
	
	public MainPanel() {
		setBackground(new Color(255, 228, 181));
		tc = new TweetCollection(
				"./sentimentanalysis/testProcessed.txt", 160);//1600000

		setPreferredSize(new Dimension(800,500));
		setLayout(null);

		textArea.setText(tc.toString());

		JLabel Tweets = new JLabel("Tweets:");
		Tweets.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		Tweets.setBounds(28, 38, 134, 16);
		add(Tweets);

		userName = new JTextField();
		userName.setBounds(511, 74, 199, 26);
		add(userName);
		userName.setColumns(10);

		JLabel lblNewLabel = new JLabel("Enter User Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(399, 79, 114, 16);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Select ID");
		lblNewLabel_1.setBounds(397, 159, 54, 14);
		add(lblNewLabel_1);

		JButton btnGetTweet = new JButton("Get Tweet IDs");
		btnGetTweet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Set<Long> ids = tc.getTweetIdsByUser(userName.getText());
				String[] idsStr = new String[ids.size()];
				int index = 0;
				for (Long long1 : ids) {
					idsStr[index] = long1.toString();
					index++;
				}
				comboBox.setModel(new DefaultComboBoxModel(idsStr));
			}
		});
		btnGetTweet.setBounds(511, 107, 172, 29);
		add(btnGetTweet);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = (String)comboBox.getSelectedItem();
				Tweet t = tc.getTweetById(Long.parseLong(id));
				tweetData.setText("Tweet: " + t.toString());
			}
		});
		comboBox.setBounds(461, 153, 234, 27);
		add(comboBox);

		tweetData = new JLabel("Tweet:");
		tweetData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tweetData.setBounds(399, 192, 328, 16);
		add(tweetData);
		
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMaximum(tc.size());
		slider.setMinimum(1);
		slider.setMajorTickSpacing(100);
		slider.setBounds(407, 221, 274, 45);
		add(slider);
		
		JLabel lblTweet = new JLabel("Tweet: ");
		lblTweet.setBounds(399, 301, 46, 14);
		add(lblTweet);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		textArea_1.setBounds(399, 313, 328, 60);
		add(textArea_1);
		
		JButton sldBtn = new JButton("Get Tweet");
		sldBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tweet t = new Tweet(-1, 12, "", "");
				tc.setNthTweet(slider.getValue());
				t.setText(tc.getNthTweet());
				textArea_1.setText(t.getText());
			}
		});
		sldBtn.setBounds(461, 277, 172, 23);
		add(sldBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 65, 359, 281);
		add(scrollPane);
		scrollPane.setViewportView(textArea);
		
		JRadioButton radioButton = new JRadioButton("0");
		radioButton.setSelected(true);
		buttonGroup.add(radioButton);
		radioButton.setBounds(87, 373, 54, 23);
		add(radioButton);
		radioButton.setVisible(false);
		
		JRadioButton radioButton_1 = new JRadioButton("2");
		radioButton_1.setSelected(true);
		buttonGroup.add(radioButton_1);
		radioButton_1.setBounds(143, 373, 54, 23);
		add(radioButton_1);
		radioButton_1.setVisible(false);
		
		JRadioButton radioButton_2 = new JRadioButton("4");
		buttonGroup.add(radioButton_2);
		radioButton_2.setBounds(199, 373, 54, 23);
		add(radioButton_2);
		radioButton_2.setVisible(false);
		
		predText = new JTextField();
		predText.setBounds(76, 421, 353, 20);
		add(predText);
		predText.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Enter New Tweet:");
		lblNewLabel_2.setBounds(77, 403, 176, 14);
		add(lblNewLabel_2);
				
		JLabel guessResult = new JLabel("");
		guessResult.setBounds(245, 480, 268, 14);
		add(guessResult);
		guessResult.setVisible(false);
		
		JButton btnNewButton = new JButton("Predict");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tweet t = new Tweet(-1, 12, "", predText.getText());
				predResult.setText("" + tc.predict(t));
				if ((tc.predict(t) == 0 && radioButton.isSelected()) || 
						(tc.predict(t) == 2 && radioButton_1.isSelected()) || 
						(tc.predict(t) == 4 && radioButton_2.isSelected())) {
					guessResult.setText("Your prediction was correct!");
				}
				else
					guessResult.setText("Your prediction was wrong.");
			}
		});
		btnNewButton.setBounds(73, 455, 143, 23);
		add(btnNewButton);
		
		predResult = new JTextField();
		predResult.setEditable(false);
		predResult.setBounds(245, 456, 54, 20);
		add(predResult);
		predResult.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Predict Predictions:");
		lblNewLabel_3.setBounds(87, 357, 129, 14);
		add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);
		
		JLabel lblNewLabel_4 = new JLabel("Prediction:");
		lblNewLabel_4.setBounds(245, 441, 70, 14);
		add(lblNewLabel_4);
		
		JCheckBox chckbxPredict = new JCheckBox("Predict");
		chckbxPredict.setBounds(280, 353, 70, 23);
		add(chckbxPredict);
		
		JButton btnNewButton_1 = new JButton("Apply");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxPredict.isSelected()) {
					radioButton.setVisible(true);
					radioButton_1.setVisible(true);
					radioButton_2.setVisible(true);
					lblNewLabel_3.setVisible(true);
					guessResult.setVisible(true);
				}
				else {
					radioButton.setVisible(false);
					radioButton_1.setVisible(false);
					radioButton_2.setVisible(false);
					lblNewLabel_3.setVisible(false);
					guessResult.setVisible(false);
				}
			}
		});
		btnNewButton_1.setBounds(280, 383, 89, 23);
		add(btnNewButton_1);
	}
}