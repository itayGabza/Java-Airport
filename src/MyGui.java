//import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MyGui extends JFrame {

	//fields
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	public static void main(String[] args) { //Launch the application
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyGui frame = new MyGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyGui() { //all Jlabels and Jbuttons
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPatma = new JLabel("Fatma Ben Gurion Airport");
		lblPatma.setForeground(new Color(0, 51, 153));
		lblPatma.setFont(new Font("David", Font.PLAIN, 20));
		lblPatma.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatma.setBounds(26, 10, 341, 55);
		contentPane.add(lblPatma);

		JLabel jblSecurityWork = new JLabel("Number Of technical crews");
		jblSecurityWork.setBounds(25, 75, 180, 33);
		contentPane.add(jblSecurityWork);

		textField = new JTextField();
		textField.setText("1");
		textField.setBounds(66, 117, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNumberOfTech = new JLabel("Work Time For Security");
		lblNumberOfTech.setBounds(236, 81, 190, 20);
		contentPane.add(lblNumberOfTech);

		textField_1 = new JTextField();
		textField_1.setText("2");
		textField_1.setBounds(265, 117, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnStart = new JButton("Start");
		btnStart.setForeground(new Color(51, 51, 153));
		btnStart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				double timework;
				try{
					timework=Double.parseDouble(textField.getText());
				}
				catch(NumberFormatException e){
					timework=2;
				}
				int numberOfTech;
				try{
					numberOfTech=Integer.parseInt(textField_1.getText());
				}
				catch(NumberFormatException e){
					numberOfTech=1;
				}
				System.out.println("Welcome To Fatma Airport");
				Airport airport = new Airport();
				//elal.startTheDay();
			}
		});
		btnStart.setBounds(66, 200, 89, 23);
		contentPane.add(btnStart);

		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(new Color(51, 51, 153));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(265, 200, 89, 23);
		contentPane.add(btnExit);
	}
}