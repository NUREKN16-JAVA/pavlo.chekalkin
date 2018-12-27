package chekalkin.labs.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import chekalkin.labs.User;
import chekalkin.labs.db.DatabaseException;
import chekalkin.labs.util.Messages;

public class AddPanel extends JPanel implements ActionListener {
	
	protected MainFrame parent;
	private JPanel fieldPanel;
	protected JPanel buttonPanel;
	private JButton okButton;
	private JButton cancelButton;
	private JTextField lastNameField;
	private JTextField dateOfBirthField;
	private JTextField firstNameField;
	private Color bgColor;
	
	public AddPanel(MainFrame parent) {
		this.parent = parent;
		initialize();
	}

	private void initialize() {
		this.setName("addPanel"); 
		this.setLayout(new BorderLayout());
		this.add(getFieldPanel(), BorderLayout.NORTH);
		this.add(getButtonPanel(), BorderLayout.SOUTH);
		
	}

	private JPanel getFieldPanel() {
		if(fieldPanel == null) {
			fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(3,2));
			addLabelField(fieldPanel, Messages.getString("AddPanel.first_name"), getFirstNameField()); 
			addLabelField(fieldPanel, Messages.getString("AddPanel.last_name"), getLastNameField()); 
			addLabelField(fieldPanel, Messages.getString("AddPanel.date_of_birth"), getDateOfBirthField()); 
			
			
		}
		return fieldPanel;
	}

	protected JTextField getDateOfBirthField() {
		if(dateOfBirthField == null) {
			dateOfBirthField = new JTextField();
			dateOfBirthField.setName("dateOfBirthField"); 
		}
		return dateOfBirthField;
	}

	protected JTextField getLastNameField() {
		if(lastNameField == null) {
			lastNameField = new JTextField();
			lastNameField.setName("lastNameField"); 
		}
		return lastNameField;
	}

	private void addLabelField(JPanel panel, String labelText, JTextField textField) {
		JLabel label = new JLabel(labelText);
		label.setLabelFor(textField);
		panel.add(label);
		panel.add(textField);
		
	}

	protected JTextField getFirstNameField() {
		if(firstNameField == null) {
			firstNameField = new JTextField();
			firstNameField.setName("firstNameField"); 
		}
		return firstNameField;
	}

	private JPanel getButtonPanel() {
		if(buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getOkButton(), null);
			buttonPanel.add(getCancelButton(), null);
		}
		return buttonPanel;
	}

	private JButton getCancelButton() {
		if(cancelButton == null) {
			cancelButton = new JButton();
			//cancelButton.setText(Messages.getString("AddPanel.cancel")); 
			cancelButton.setText(Messages.getString("AddPanel.cancel")); 
			cancelButton.setName("cancelButton"); //$NON-NLS-1$
			cancelButton.setActionCommand("cancel"); //$NON-NLS-1$
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}

	private JButton getOkButton() {
		if(okButton == null) {
			okButton = new JButton();
			//okButton.setText(Messages.getString("AddPanel.ok")); 
			okButton.setText(Messages.getString("AddPanel.Ok")); 
			okButton.setName("okButton"); 
			okButton.setActionCommand("ok"); 
			okButton.addActionListener(this);
		}
		return okButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("ok".equalsIgnoreCase(e.getActionCommand())) {
			User user = new User();
			
			user.setFirstName(getFirstNameField().getText());
			user.setLastName(getLastNameField().getText());
			DateFormat format = DateFormat.getDateInstance();
			try {
				user.setBirthday(format.parse(getDateOfBirthField().getText()));
			} catch (ParseException e1) {
				getDateOfBirthField().setBackground(Color.RED);
				return;
			}
			
			try {
				parent.getDao().create(user);
			} catch (DatabaseException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		clearFields();
		this.setVisible(false);
		parent.showBrowsePanel();
		
	}

	private void clearFields() {
		getFirstNameField().setText("");
		getFirstNameField().setBackground(bgColor);
		
		getLastNameField().setText("");
		getLastNameField().setBackground(bgColor);

		getDateOfBirthField().setText("");
		getDateOfBirthField().setBackground(bgColor);
		
	}

}
