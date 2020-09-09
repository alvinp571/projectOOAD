package views;

import components.ButtonInternalClose;

import components.LabelTitle;
import components.Message;
import components.PanelForm;
import components.Table;
import controllers.BookHandler;
import controllers.GenreHandler;
import models.Book;
import models.Genre;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;
import views.base.BaseInternalView;

/**
 * Manage Course Form
 *
 * @author kevinsudut <kevinsuryaw@gmail.com>
 */
public final class ManageBook extends BaseInternalView {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private LabelTitle title;
  private Table table;
  private JTabbedPane tabbedPane;
  private PanelForm panelRestock, panelDelete;
  private JLabel lblRestockGenre,lblRestockTitle,lblRestockISBN,lblRestockQuantity;
  private JLabel lblDeleteBook;
  private JLabel lblSelectDeleteGenre;
  private JTextField txtInsertTitle, txtInsertISBN,txtInsertQuantity;
  private JComboBox<String> cbInsertGenre;
  private JButton btnInsert,btnDelete;
  private ButtonInternalClose close;

  public ManageBook() {
    super("Restock Book", 1000, 350);
  }

  private BookHandler bookHandler = new BookHandler();
  private GenreHandler genreHandler = new GenreHandler();
  private List<Genre> theGenres = genreHandler.getAll();
  
  @Override
  public void initializeComponent() {
	  Vector<Object> tHeader = new Vector<>();
	    tHeader.add("Id");
	    tHeader.add("Genre");
	    tHeader.add("Title");
	    tHeader.add("ISBN");
	    tHeader.add("Quantity");
	    Vector<Vector<Object>> tRows = new Vector<>();
	    
	    List<Book> theBooks = bookHandler.getAll();
	    for (Book b : theBooks) {
	    	Vector<Object> forEachRow = addRow(b);
			tRows.add(forEachRow);
		}
    
    table = new Table(tHeader, tRows);

    title = new LabelTitle("Restock Book");

    tabbedPane = new JTabbedPane();

    /**
     * Initialize Component for Insert Form
     */
    
    lblRestockGenre = new JLabel("Book Genre");
    lblRestockTitle = new JLabel("Book Title");
    lblRestockISBN = new JLabel("Book ISBN");
    lblRestockQuantity = new JLabel("Book Quantity");
    txtInsertTitle = new JTextField();
    txtInsertISBN = new JTextField();
    txtInsertQuantity = new JTextField();
    
    List<String> forComboBox = new ArrayList<String>();
    forComboBox.add("Please choose Genre");
    
    for (Genre g : theGenres) {
		forComboBox.add(g.getType());
	}
    
    String[] genreStrings = forComboBox.toArray(new String[forComboBox.size()]);
    
    
    cbInsertGenre = new JComboBox<String>(genreStrings);
    btnInsert = new JButton("Insert");

    Component[][] insert = {
      new Component[] { lblRestockTitle,lblRestockISBN,lblRestockQuantity,lblRestockGenre},
      new Component[] { txtInsertTitle, txtInsertISBN,txtInsertQuantity,cbInsertGenre },
    };

    panelRestock = new PanelForm(insert, btnInsert, new Dimension(350, 350));

    /**
     * Initialize Component for Delete Form
     */

    lblDeleteBook = new JLabel("Book List");
    lblSelectDeleteGenre = new JLabel("Please Choose the Books");
    btnDelete = new JButton("Delete");
    btnDelete.setEnabled(Boolean.FALSE);

    Component[][] delete = {
      new Component[] {lblDeleteBook},
      new Component[] {lblSelectDeleteGenre},
    };

    panelDelete = new PanelForm(delete, btnDelete, new Dimension(350, 350));
    
    close = new ButtonInternalClose();
  }

public Vector<Object> addRow(Book b) {
	Vector<Object> forEachRow = new Vector<>();
	forEachRow.add(b.getId());
	String genreType = "";
	for (Genre genre : theGenres) {
		if(b.getGenre_id().equals(genre.getId())) {
			genreType = genre.getType();
			break;
		}
	}
	forEachRow.add(genreType);
	forEachRow.add(b.getTitle());
	forEachRow.add(b.getIsbn());
	forEachRow.add(b.getQuantity());
	return forEachRow;
}

  @Override
  public void addComponent() {
    tabbedPane.add("Restock Book", panelRestock.getPanel());
    tabbedPane.add("Delete Book", panelDelete.getPanel());

    JPanel pnlCenter = new JPanel(new BorderLayout(8, 8));
    pnlCenter.add(table.getScrollPane(), BorderLayout.CENTER);
    pnlCenter.add(tabbedPane, BorderLayout.EAST);
    
    JPanel pnlSouth = new JPanel(new BorderLayout(4, 4));
    pnlSouth.add(close.getButton(), BorderLayout.SOUTH);

    JPanel panel = new JPanel(new BorderLayout(8, 8));
    panel.add(title.getLabel(), BorderLayout.NORTH);
    panel.add(pnlCenter, BorderLayout.CENTER);
    panel.add(pnlSouth, BorderLayout.SOUTH);
    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

    add(panel);
  }

  @Override
  public void addListener() {
    table.addMouseListener(
      new MouseInputAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
          super.mouseClicked(e);
          
          int row = table.getSelectedRow();
          lblSelectDeleteGenre.setText(table.getValueAt(row,0));
          btnDelete.setEnabled(true);
        }
      }
    );

    btnInsert.addActionListener(
      new AbstractAction() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	HashMap<String, String> inputs = new HashMap<String, String>();
        	inputs.put("title",txtInsertTitle.getText());
        	inputs.put("isbn",txtInsertISBN.getText());
        	inputs.put("quantity", txtInsertQuantity.getText());
        	inputs.put("genre",cbInsertGenre.getSelectedItem().toString());
        	
        	
        	Book b = bookHandler.decide(inputs);
        	if(b!=null) {
        		table.addNewRow(addRow(b));
        		txtInsertTitle.setText("");
        		txtInsertISBN.setText("");
        		txtInsertQuantity.setText("");
        		cbInsertGenre.setSelectedIndex(0);
        	}
        }
      }
    );

    btnDelete.addActionListener(
      new AbstractAction() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void actionPerformed(ActionEvent e) {
        	int result = Message.confirm("Are you sure want to delete this course","Delete course");
        	if(result==JOptionPane.YES_OPTION) {
        		bookHandler.delete(lblSelectDeleteGenre.getText());
        		
        		//remove selected row
        		table.removeRow(table.getSelectedRow());
        		
        		refreshForm();
        		
            	Message.success("Success delete course");
        	}
        }
      }
    );

    close.addListener(this);
  }
  
  private void refreshForm() {
	  txtInsertTitle.setText("");
	  txtInsertISBN.setText("");
	  txtInsertQuantity.setText("");
	  cbInsertGenre.setSelectedIndex(0);
	  
	  lblSelectDeleteGenre.setText("Please Choose Genre");
	  btnDelete.setEnabled(false);
	  
  }
}