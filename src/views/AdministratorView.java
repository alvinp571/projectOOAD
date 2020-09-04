package views;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import components.LabelTitle;
import views.base.BaseView;

public class AdministratorView extends BaseView{
	private static final long serialVersionUID = 1L;

	  private LabelTitle title;
	  private JLabel lblUsername, lblPassword;
	  private JTextField txtUsername;
	  private JPasswordField txtPassword;
	  private JButton btnLogin, btnRegister;

	  public AdministratorView() {
	    super("Administrator Form", 350, 225);
	  }

	  @Override
	  public void initializeComponent() {
	    title = new LabelTitle("Manager Form");
	    lblUsername = new JLabel("Username");
	    lblPassword = new JLabel("Password");
	    txtUsername = new JTextField();
	    txtPassword = new JPasswordField();
	    btnLogin = new JButton("Login");
	    btnRegister = new JButton("Register");
	  }

	  @Override
	  public void addComponent() {
	    JPanel pnlFormLabel = new JPanel(new GridLayout(2, 1, 8, 8));
	    pnlFormLabel.add(lblUsername);
	    pnlFormLabel.add(lblPassword);

	    JPanel pnlFormInput = new JPanel(new GridLayout(2, 1, 8, 8));
	    pnlFormInput.add(txtUsername);
	    pnlFormInput.add(txtPassword);

	    JPanel pnlForm = new JPanel(new BorderLayout(8, 8));
	    pnlForm.add(pnlFormLabel, BorderLayout.WEST);
	    pnlForm.add(pnlFormInput, BorderLayout.CENTER);

	    JPanel pnlButton = new JPanel(new GridLayout(2, 1, 8, 8));
	    pnlButton.add(btnLogin);
	    pnlButton.add(btnRegister);

	    JPanel panel = new JPanel(new BorderLayout(8, 8));
	    panel.add(title.getLabel(), BorderLayout.NORTH);
	    panel.add(pnlForm, BorderLayout.CENTER);
	    panel.add(pnlButton, BorderLayout.SOUTH);
	    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

	    add(panel);
	  }

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		
	}
	

}
