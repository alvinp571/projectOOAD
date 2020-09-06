//package views;
//
//import java.awt.BorderLayout;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//
//import javax.swing.AbstractAction;
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JPasswordField;
//import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//
//import components.LabelTitle;
//import controllers.EmployeeHandler;
//import views.base.BaseView;
//
//public class HumanCapitalView extends BaseView{
//	private static final long serialVersionUID = 1L;
//
//	  private LabelTitle title;
//	  private JLabel lblUsername, lblPassword;
//	  private JTextField txtUsername;
//	  private JPasswordField txtPassword;
//	  private JButton btnLogin, btnRegister;
//
//	  public HumanCapitalView() {
//	    super("Human Capital Form", 350, 225);
//	  }
//
//	  @Override
//	  public void initializeComponent() {
//	    title = new LabelTitle("Manager Form");
//	    lblUsername = new JLabel("Username");
//	    lblPassword = new JLabel("Password");
//	    txtUsername = new JTextField();
//	    txtPassword = new JPasswordField();
//	    btnLogin = new JButton("Login");
//	    btnRegister = new JButton("View Employee");
//	  }
//
//	  @Override
//	  public void addComponent() {
//	    JPanel pnlFormLabel = new JPanel(new GridLayout(2, 1, 8, 8));
//	    pnlFormLabel.add(lblUsername);
//	    pnlFormLabel.add(lblPassword);
//
//	    JPanel pnlFormInput = new JPanel(new GridLayout(2, 1, 8, 8));
//	    pnlFormInput.add(txtUsername);
//	    pnlFormInput.add(txtPassword);
//
//	    JPanel pnlForm = new JPanel(new BorderLayout(8, 8));
//	    pnlForm.add(pnlFormLabel, BorderLayout.WEST);
//	    pnlForm.add(pnlFormInput, BorderLayout.CENTER);
//
//	    JPanel pnlButton = new JPanel(new GridLayout(2, 1, 8, 8));
////	    pnlButton.add(btnLogin);
//	    pnlButton.add(btnRegister);
//
//	    JPanel panel = new JPanel(new BorderLayout(8, 8));
//	    panel.add(title.getLabel(), BorderLayout.NORTH);
//	    panel.add(pnlForm, BorderLayout.CENTER);
//	    panel.add(pnlButton, BorderLayout.SOUTH);
//	    panel.setBorder(new EmptyBorder(10, 10, 10, 10));
//
//	    add(panel);
//	  }
//
//	@Override
//	public void addListener() {
//		btnLogin.addActionListener(
//				new AbstractAction() {
//					private static final long serialVersionUID = 1L;
//					@Override
//			        public void actionPerformed(ActionEvent e) {
//						
//					}
//			    }
//			);
//
//			 btnRegister.addActionListener(
//				new AbstractAction() {
//					private static final long serialVersionUID = 1L;
//					@Override
//			        public void actionPerformed(ActionEvent e) {
//				       	new EmployeeHandler().showViewEmployeeForm().showForm();
//					}
//			    }
//			);
//	}
//
//}

package views;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import views.base.BaseView;

/**
 * Main Form
 *
 * @author kevinsudut <kevinsuryaw@gmail.com>
 */
public final class HumanCapitalView extends BaseView {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private JMenuBar menuBar;
  private JMenu mFile, mManage;
  private JMenuItem miLogout, miViewEmployees;
  private JDesktopPane desktopPane;
  
  private ViewEmployeeHumanCapital viewEmployeeHumanCapital;

  public HumanCapitalView() {
    super("Human and Resource Staff", Boolean.TRUE);
  }

  @Override
  public void initializeComponent() {
    menuBar = new JMenuBar();

    mFile = new JMenu("File");
    mManage = new JMenu("Manage");

    miLogout = new JMenuItem("Logout");
    miViewEmployees = new JMenuItem("View Employees");

    desktopPane =
      new JDesktopPane() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        protected void paintComponent(Graphics g) {
          super.paintComponent(g);
          try {
            final File PATH = new File("assets/library.jpg");
            Image image = ImageIO.read(PATH);

            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      };
  }

  @Override
  public void addComponent() {
    mFile.add(miLogout);
    mManage.add(miViewEmployees);

    menuBar.add(mFile);
    menuBar.add(mManage);

    setJMenuBar(menuBar);

    setContentPane(desktopPane);
  }

  @Override
  public void addListener() {
    miLogout.addActionListener(
      new AbstractAction() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
          // TODO Auto-generated method stub

        }
      }
    );

    miViewEmployees.addActionListener(
      new AbstractAction() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	if(viewEmployeeHumanCapital == null || viewEmployeeHumanCapital.isClosed()) {
        		viewEmployeeHumanCapital = new ViewEmployeeHumanCapital();
        		desktopPane.add(viewEmployeeHumanCapital);
        		viewEmployeeHumanCapital.showForm();
        	}
        
        }
      }
    );
  }
}

