package chekalkin.labs.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import chekalkin.labs.User;
import chekalkin.labs.util.Messages;

public class UserTableModel extends AbstractTableModel {
	
	private static final String[] COLUMN_NAMES = {Messages.getString("UserTableModel.id"), Messages.getString("UserTableModel.first_name"), Messages.getString("UserTableModel.last_name")}; 
	private static final Class[] COLUMN_CLASSES = {Long.class, String.class, String.class};
	private List<User> users = null; 
	
	public UserTableModel(Collection<User> users) {
		this.users = new ArrayList<User>(users);
	}

	@Override
	public int getRowCount() {
		
		return users.size();
	}

	@Override
	public int getColumnCount() {
		
		return COLUMN_NAMES.length;
	}

	
	@Override
	public String getColumnName(int column) {
		
		return COLUMN_NAMES[column];
	}

	
	@Override
	public Class getColumnClass(int columnIndex) {
		
		return COLUMN_CLASSES[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		User user = (User) users.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return user.getId();
		case 1:
			return user.getFirstName();
		case 2:
			return user.getLastName();
		}
		return null;
	}
	
	public User getUser(int index) {
		return (User) users.get(index);
	}

	public void addUsers(Collection<User> users) {
		this.users.addAll(users);

	}

	public void clearUsers() {
		this.users = new ArrayList<User>();
	}

}
