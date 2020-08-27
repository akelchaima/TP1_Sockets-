import java.awt.Color;
import java.awt.EventQueue;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;

public class Host2 {

	private JFrame frmServer;
	private static JTextArea textArea;
	
	static ServerSocket ss;
	//ServerSocket est une classe permettant de créer une connexion côté serveur
	//à laquelle les clients distants doivent se connecter. 
	//En cas de réussite de la connexion client,
	//renvoie un socket ordinaire pouvant être utilisé par la suite pour 
	//communiquer avec le client.
	//Pour que la connexion soit créée, elle nécessite le portNo.
	static Socket s;
	
	static DataInputStream input; //permet de recevoir les données                          
	static DataOutputStream output; //permet d'envoyer les données
	private JLabel lblLareMatrice;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JTextArea textArea_2;
	private JButton button;
	private static JTextField textField;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Host2 window = new Host2();
					window.frmServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		String msg1 = ""; //pour stocker les 2 msg= 2 mat réçus du client
		String msg2 = "";

		try {
			System.out.println("Waiting For Clients....");
			
			ss = new ServerSocket(1201); //port number to identify the app
			s= ss.accept(); //as soon as the server gets a connection from the client,
			                //it will return a socket object stocked in var S
			System.out.println("Connection Established/Client is Connected");
			System.out.println("");
			
			input = new DataInputStream(s.getInputStream());// variable qui contient le flux venant du client
			output = new DataOutputStream(s.getOutputStream());//variable qui contient le flux allant au client
			
			while((!msg1.equals("exit"))&&(!msg2.equals("exit")))
			{
				msg1 = input.readUTF();//permet de lire le msg reçu encodée UTF-8
				msg2= input.readUTF();
				
				textArea.setText(textArea.getText().trim()+""+msg1); //display the msg from the client
				textField.setText(textField.getText().trim()+""+msg2); //display the msg from the client
			
             
			}
		
			
		}catch(Exception e) {
		e.printStackTrace();
	}
}

	/**
	 * Create the application.
	 */
	public Host2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServer = new JFrame();
		frmServer.setTitle("Host2");
		frmServer.setBounds(100, 100, 450, 450);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.setResizable(false);
		frmServer.getContentPane().setLayout(null);
		
	    textArea = new JTextArea();
		textArea.setBounds(10, 28, 174, 135);
		textArea.setBorder((BorderFactory.createLineBorder(Color.BLACK))); 
		frmServer.getContentPane().add(textArea);
		
		JScrollPane pn = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pn.setBounds(10, 28, 174, 135);
		frmServer.getContentPane().add(pn);
		
		JButton btnSendResult = new JButton("Send Result");
		btnSendResult.setForeground(new Color(0, 153, 255));
		btnSendResult.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnSendResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String result = "";
					result = textArea_2.getText().trim();
					output.writeUTF(result);//sending the result from server to client (encodée)
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSendResult.setBounds(300, 368, 124, 42);
		frmServer.getContentPane().add(btnSendResult);
		
		lblLareMatrice = new JLabel("\u2192X :");
		lblLareMatrice.setFont(new Font("Arial", Font.BOLD, 16));
		lblLareMatrice.setBounds(10, 11, 114, 14);
		frmServer.getContentPane().add(lblLareMatrice);
		
		lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(212, 70, 28, 64);
		frmServer.getContentPane().add(lblNewLabel_1);
		
		btnNewButton = new JButton("Result");
		btnNewButton.setFont(new Font("Segoe UI Symbol", Font.BOLD, 13));
		btnNewButton.setForeground(new Color(0, 204, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//*****create the 1st matrix from the first msg sent from the client
				String[] lines = textArea.getText().split("\n");
				 int l = (int) lines.length;
				   float lamda = Float.valueOf(textField.getText());

                 float[] mat1 = new float[l];
 				int val[] = new int[lines.length];
 				int j=0;
				   for(int i=0;i<lines.length;i++)//create a table and fill it with the values
	                {
	               	 String num[]=lines[i].split("=");
	               	val[j] = Integer.parseInt(num[1]);
	               	j++;   
	                 }
	             j=0;
				   for(int i=0;i<lines.length;i++) //fill mat1 using the table
	                {
					   
						   mat1[i]=val[j];
						   j++;
						   System.out.println("  →X[" + (i + 1) +"]=" +mat1[i]+ "\n"); 
		                }
		
				   //*****Multiplication(mat1,lamda);

			        	//Creating Result
			            float[] prod = new float[mat1.length];

			                    for (int i = 0; i < mat1.length; i++) {
			                    	prod[i] = 0;

			                        prod[i] = mat1[i] * lamda;
			                       
			                    }
			            
			 
			            // Displaying Result
			            for (int row = 0; row < prod.length; row++) {
			                    System.out.print(" →X.λ[" + (row + 1) + "]=" +prod[row] + " ");
			                    
			                    textArea_2.setText(textArea_2.getText()+"  →X.λ[" + (row + 1) + "]=" +prod[row] + "\n");
			                
			                
			            }
			        		   
		}
	});
		btnNewButton.setBounds(126, 187, 174, 23);
		frmServer.getContentPane().add(btnNewButton);
		
		textArea_2 = new JTextArea();
		textArea_2.setBounds(126, 222, 174, 135);
		textArea_2.setBorder((BorderFactory.createLineBorder(Color.BLACK))); 
		frmServer.getContentPane().add(textArea_2);
		
		JScrollPane p = new JScrollPane(textArea_2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		p.setBounds(126, 222, 174, 135);
		frmServer.getContentPane().add(p);
		
		button = new JButton("CLEAR");
		button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 13));
		button.setForeground(new Color(255, 51, 0));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
				textField.setText(null);
				textArea_2.setText(null);
			}
		});
		button.setBounds(10, 368, 124, 42);
		frmServer.getContentPane().add(button);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBorder((BorderFactory.createLineBorder(Color.BLACK)));
		textField.setBounds(282, 89, 142, 26);
		frmServer.getContentPane().add(textField);
		
		JLabel label = new JLabel("\u03BB :");
		label.setFont(new Font("Arial", Font.BOLD, 16));
		label.setBounds(282, 70, 28, 14);
		frmServer.getContentPane().add(label);
	}
}
