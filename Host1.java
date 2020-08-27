import java.awt.EventQueue;
import java.awt.TextField;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.Color;

public class Host1 { //Client 

	private JFrame frmClient;
	private static JTextField textField; 
	private static JTextArea textArea;
	private static JTextArea textArea_1;
	private static JTextField textField_2;
	
	static Socket s;
	static DataInputStream input;//permet de recevoir les données 
	static DataOutputStream output;//permet d'envoyer les données
	private JButton btnStart;
	private JButton btnClear;
	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Host1 window = new Host1();
					window.frmClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {
			System.out.println("Client Is Connected");
			
			s = new Socket("127.0.0.1",1201);// localhost IP@ to identify the machine, port number
			input = new DataInputStream(s.getInputStream());//une variable contenant le flux d'enrée venant du serveur 
			output = new DataOutputStream(s.getOutputStream());//une variable contenant le flux de sortie allant au serveur 
              
			String msg ="";//pour stocker le msg =resultat réçus du client
			while(!msg.equals("exit"))
			{
				msg = input.readUTF();//permet de lire le msg reçu encodée en UTF-8
				textArea.setText(textArea.getText().trim()+"\n Server:\n"+msg);//display the msg from the server
				                                   //elimine les espaces
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public Host1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClient = new JFrame();
		frmClient.setTitle("Host1");
		frmClient.setBounds(700, 100, 450, 570);
		frmClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClient.setResizable(false);
		frmClient.getContentPane().setLayout(null);
		
	    textArea = new JTextArea(); 
	    textArea.setForeground(new Color(0, 0, 0));
		textArea.setBounds(127, 11, 192, 149);
		textArea.setBorder((BorderFactory.createLineBorder(Color.BLACK))); 
		frmClient.getContentPane().add(textArea);
		
		JScrollPane pn = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pn.setBounds(127, 11, 192, 149);
		frmClient.getContentPane().add(pn);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
			}
		});
		textField.setBounds(148, 185, 69, 26);
		textField.setBorder((BorderFactory.createLineBorder(Color.BLACK))); 
		frmClient.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Send Request");
		btnNewButton.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnNewButton.setForeground(new Color(0, 153, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String request1 = textArea_1.getText().trim();
					output.writeUTF(request1); //the sent request1= table X
					                            //ma chaine codée en UTF8
					String request2 = textField_2.getText().trim();
					output.writeUTF(request2);//the sent request2= lamda
					                            //ma chaine codée en UTF8
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(300, 478, 124, 42);
		frmClient.getContentPane().add(btnNewButton);
		
		JLabel lblSaisieDeLa = new JLabel("Valeur de  \u03BB  :");
		lblSaisieDeLa.setFont(new Font("Arial", Font.BOLD, 12));
		lblSaisieDeLa.setBounds(264, 185, 81, 26);
		frmClient.getContentPane().add(lblSaisieDeLa);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(343, 185, 69, 26);
		textField_2.setBorder((BorderFactory.createLineBorder(Color.BLACK))); 

		frmClient.getContentPane().add(textField_2);
		
	    textArea_1 = new JTextArea();
		textArea_1.setBounds(127, 286, 192, 149);
		textArea_1.setBorder((BorderFactory.createLineBorder(Color.BLACK))); 
		frmClient.getContentPane().add(textArea_1);
		
		JScrollPane pane = new JScrollPane(textArea_1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBounds(127, 286, 192, 149);
		frmClient.getContentPane().add(pane);
		
		btnStart = new JButton("START");
		btnStart.setForeground(new Color(0, 204, 0));
		btnStart.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int l;
				//int c;
				float lamda;
				//int c1;
			    l = Integer.parseInt(textField.getText());
		        //c= Integer.parseInt(textField_1.getText()); 
		        
		        lamda = Float.valueOf(textField_2.getText());
		        //c1= Integer.parseInt(textField_3.getText());
		        
		        float[] mat1 = new float[l];
		        for (int row = 0; row < l; row++) {
		            	textArea_1.setText(textArea_1.getText()+"  →X[" + (row + 1)+"]=\n"); 
		            
		        }
		 
				
				
			}
		});
		btnStart.setBounds(173, 233, 89, 42);
		frmClient.getContentPane().add(btnStart);
		
		btnClear = new JButton("CLEAR");
		btnClear.setForeground(new Color(255, 51, 0));
		btnClear.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
				textArea_1.setText(null);
				textField.setText(null);
				textField_2.setText(null);

			}
		});
		btnClear.setBounds(10, 478, 124, 42);
		frmClient.getContentPane().add(btnClear);
		
		JLabel lblNbrDeLignes = new JLabel("Nbr de lignes de \u2192X :");
		lblNbrDeLignes.setFont(new Font("Arial", Font.BOLD, 12));
		lblNbrDeLignes.setBounds(22, 185, 152, 26);
		frmClient.getContentPane().add(lblNbrDeLignes);
	
	}
}
