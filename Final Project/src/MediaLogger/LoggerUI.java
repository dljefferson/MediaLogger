package MediaLogger;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;



public class LoggerUI {

	private JFrame frmLoggerUI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoggerUI window = new LoggerUI();
					window.frmLoggerUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoggerUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoggerUI = new JFrame();
		frmLoggerUI.setTitle("Media Logger");
		frmLoggerUI.setBounds(100, 100, 450, 300);
		frmLoggerUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoggerUI.getContentPane().setLayout(null);
		
		JButton btnLogMovie = new JButton("Log Movie");
		btnLogMovie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		    
				
			String title = getMedia("Enter Movie Title",  "Title Dialog");
		    String director = getMedia("Enter Director",  "Director Dialog");	
		    String category = getMedia("Enter Category",  "Category Dialog");
			try {
				MediaInsert("Movie", title, director, category);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			}
		});
		btnLogMovie.setBounds(118, 31, 208, 23);
		frmLoggerUI.getContentPane().add(btnLogMovie);
		
		JButton btnLogBook = new JButton("Log Book");
		btnLogBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnLogBook.setBounds(118, 65, 208, 23);
		frmLoggerUI.getContentPane().add(btnLogBook);
		
		JButton btnLogMusic = new JButton("Log Music");
		btnLogMusic.setBounds(118, 99, 208, 23);
		frmLoggerUI.getContentPane().add(btnLogMusic);
		
		JButton btnMovieSeen = new JButton("Movies Seen");
		btnMovieSeen.setBounds(118, 133, 208, 23);
		frmLoggerUI.getContentPane().add(btnMovieSeen);
		
		JButton btnBookRead = new JButton("Books Read");
		btnBookRead.setBounds(118, 167, 208, 23);
		frmLoggerUI.getContentPane().add(btnBookRead);
		
		JButton btnMusicLtn = new JButton("Music Listened To");
		btnMusicLtn.setBounds(118, 198, 208, 23);
		frmLoggerUI.getContentPane().add(btnMusicLtn);
	}
	public String getMedia(String prompt, String title) {
		
		String s = (String)JOptionPane.showInputDialog(
				frmLoggerUI,
				prompt,
				title, JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				"");
	     return s;

   }

   
	//Insert media into mysql database
	public void MediaInsert(String type, String title, String director, String category) throws Exception
   {
	Class.forName("com.mysql.jdbc.Driver");
		
		//Create a variable for connection called "con"
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/media_db?autoReconnect=true&useSSL=false", "root", "dljz8x");
		
		//Here we create a query
	     PreparedStatement statement = con.prepareStatement("Insert into media(type, title, director, category) VALUES('"+type+"', '"+title+"', '"+director+"', '"+category+"')");
	    statement.executeUpdate();
   }

}