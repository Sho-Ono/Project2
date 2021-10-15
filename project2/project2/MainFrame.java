package project2;

import javax.swing.JFrame;
//import javax.swing.JMenuBar;

public class MainFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Sentiment Analysis");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MainPanel panel = new MainPanel();
		
//		frame.setJMenuBar(panel.createMenuBar());
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
	}
}