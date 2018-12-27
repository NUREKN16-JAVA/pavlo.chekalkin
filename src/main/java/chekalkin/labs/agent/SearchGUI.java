package chekalkin.labs.agent;

import javax.swing.*;

import chekalkin.labs.User;
import chekalkin.labs.agent.Exceptions.SearchException;
import chekalkin.labs.gui.UserTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;

public class SearchGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private SearchAgent agent;
	private JPanel contentPanel;
	private JPanel tablePanel;
	private JTable table;

	public SearchGUI() {
		// TODO Auto-generated constructor stub
	}

	public SearchGUI(SearchAgent agent) {
		this.agent = agent;
		initialize();
	}

	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 800);
		this.setTitle("Searcher");
		this.setContentPane(getContentPanel());
	}

	private JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getSearchPanel(), BorderLayout.NORTH);
			contentPanel.add(new JScrollPane(getTablePanel()), BorderLayout.CENTER);
		}
		return contentPanel;
	}

	private JPanel getTablePanel() {
		if (tablePanel == null) {
			tablePanel = new JPanel(new BorderLayout());
			tablePanel.add(getTable(), BorderLayout.CENTER);
		}
		return tablePanel;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable(new UserTableModel(new LinkedList<User>()));
		}
		return table;
	}

	private JPanel getSearchPanel() {
		return new SearchPanel(agent);
	}

	public static void main(String[] args) {
		SearchGUI gui = new SearchGUI(null);
		gui.setVisible(true);
	}

	class SearchPanel extends JPanel implements ActionListener {

		private static final long serialVersionUID = 1L;
		SearchAgent agent;
		private JPanel fieldPanel;
		private JButton cancelButton;
		private JButton searchButton;
		private JTextField firstNameField;
		private JTextField lastNameField;

		public SearchPanel(SearchAgent agent) {
			this.agent = agent;
			initialize();
		}

		private void initialize() {
			this.setName("addPanel");
			this.setLayout(new BorderLayout());
			this.add(getFieldPanel(), BorderLayout.NORTH);

		}

		private JButton getCancelButton() {
			if (cancelButton == null) {
				cancelButton = new JButton();
				cancelButton.setText("Cancel");
				cancelButton.setName("cancelButton");
				cancelButton.setActionCommand("cancel");
				cancelButton.addActionListener(this);
			}
			return cancelButton;
		}

		private JButton getSearchButton() {
			if (searchButton == null) {
				searchButton = new JButton();
				searchButton.setText("Search");
				searchButton.setName("okButton");
				searchButton.setActionCommand("ok");
				searchButton.addActionListener(this);
			}
			return searchButton;
		}

		private JPanel getFieldPanel() {
			if (fieldPanel == null) {
				fieldPanel = new JPanel();
				fieldPanel.setLayout(new GridLayout(1, 2));
				addLabeledField(fieldPanel, "FirstName", getFirstNameField());
				fieldPanel.add(getSearchButton());
				addLabeledField(fieldPanel, "LastName", getLastNameField());
				fieldPanel.add(getCancelButton());
			}
			return fieldPanel;
		}

		protected JTextField getLastNameField() {
			if (lastNameField == null) {
				lastNameField = new JTextField();
				lastNameField.setName("lastNameField");
			}
			return lastNameField;
		}

		private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
			JLabel label = new JLabel(labelText);
			label.setLabelFor(textField);
			panel.add(label);
			panel.add(textField);
		}

		protected JTextField getFirstNameField() {
			if (firstNameField == null) {
				firstNameField = new JTextField();
				firstNameField.setName("firstNameField");
			}
			return firstNameField;
		}

		protected void doAction(ActionEvent e) throws ParseException {
			if ("ok".equalsIgnoreCase(e.getActionCommand())) {
				String firstName = getFirstNameField().getText();
				String lastName = getLastNameField().getText();
				try {
					clearUsers();
					agent.search(firstName, lastName);
				} catch (SearchException e1) {
					throw new RuntimeException(e1);
				}
			}
			clearFields();
		}

		public void actionPerformed(ActionEvent e) {
			try {
				doAction(e);
			} catch (ParseException e1) {
				return;
			}
		}

		private void clearFields() {
			getFirstNameField().setText("");
			getLastNameField().setText("");
		}
	}

	public void addUsers(Collection<User> users) {
		UserTableModel model = (UserTableModel) getTable().getModel();
		model.addUsers(users);
		this.repaint();
	}

	private void clearUsers() {
		UserTableModel model = (UserTableModel) getTable().getModel();
		model.clearUsers();
		this.repaint();
	}

}
