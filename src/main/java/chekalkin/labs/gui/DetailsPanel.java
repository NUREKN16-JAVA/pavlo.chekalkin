package chekalkin.labs.gui;

import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JPanel;

import chekalkin.labs.User;



public class DetailsPanel extends AddPanel {
	
	private User user;
    private JButton backButton;

    public DetailsPanel(MainFrame mainFrame) {
        super(mainFrame);
        setName("detailsPanel"); 
    }

    protected void doAction(ActionEvent e) throws ParseException {
        if ("ok".equalsIgnoreCase(e.getActionCommand())) { 
            user.setFirstName(getFirstNameField().getText());
            user.setLastName(getLastNameField().getText());
            DateFormat format = DateFormat.getDateInstance();
        }
    }

    public void setUser(User user) {
        DateFormat format = DateFormat.getDateInstance();
        this.user = user;
        getFirstNameField().setText(user.getFirstName());
        getFirstNameField().setEditable(false);
        getLastNameField().setText(user.getLastName());
        getLastNameField().setEditable(false);
        getDateOfBirthField().setText(format.format(user.getBirthday()));
        getDateOfBirthField().setEditable(false);
    }

    protected JPanel getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.add(getBackButton());
        }
        return buttonPanel;
    }

    private JButton getBackButton() {
        if (backButton == null) {
            backButton = new JButton();
            backButton.setText("Назад");
            //backButton.setText(Messages.getString(Messages.getString("DetailsPanel.back"))); 
            backButton.setName("backButton"); 
            backButton.setActionCommand("back"); 
            backButton.addActionListener(this);
        }
        return backButton;
    }
}
