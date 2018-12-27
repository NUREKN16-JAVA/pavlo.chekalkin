package chekalkin.labs;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;

import org.junit.Test;

import chekalkin.labs.User;

public class UserTest {
	private User user;
    private Calendar calendar;
    private Date birthday;
    
    @Before
    public void setUp() throws ParseException {
    	Date date = new SimpleDateFormat("dd-MM-yyyy").parse("07-07-199");
        user = new User(1L, "Ivan", "Ivanov", date);
        calendar = Calendar.getInstance();
    }
    
    @Test
    public void testGetFullName() {
    	assertEquals("Ivanov, Ivan", user.getFullName());
    }
    
    //today
    @Test
    public void test_getAge_1() {
        calendar.set(1999, calendar.get(Calendar.NOVEMBER), 30);
        birthday = calendar.getTime();
        user.setBirthday(birthday);
        int result = 19;	
        int actual = user.getAge();
        assertEquals(result, actual);
    }
    
    //tomorrow
    @Test
    public void test_getAge_2() {
    	calendar.set(1999, calendar.get(Calendar.DECEMBER), 1);
		birthday = calendar.getTime();
	    user.setBirthday(birthday);
		int result = 18;	
	    int actual = user.getAge();
	    assertEquals(result, actual);
    }
  //yesterday
    @Test
    public void test_getAge_3() {
    	calendar.set(1999, calendar.get(Calendar.NOVEMBER), 29);
        birthday = calendar.getTime();
        user.setBirthday(birthday);
        int result = 19;	
        int actual = user.getAge();
        assertEquals(result, actual);
    }
    //before current month
    @Test
    public void test_getAge_4() {
    	calendar.set(1999, calendar.get(Calendar.AUGUST), 30);
        birthday = calendar.getTime();
        user.setBirthday(birthday);
        int result = 19;	
        int actual = user.getAge();
        assertEquals(result, actual);
    }
    //after current month
    @Test
    public void test_getAge_5() {
    	calendar.set(1999, calendar.get(Calendar.DECEMBER), 30);
        birthday = calendar.getTime();
        user.setBirthday(birthday);
        int result = 18;	
        int actual = user.getAge();
        assertEquals(result, actual);
    }
    //1 year<
    @Test
    public void test_getAge_6() {
    	calendar.set(1998, calendar.get(Calendar.DECEMBER), 30);
        birthday = calendar.getTime();
        user.setBirthday(birthday);
        int result = 19;	
        int actual = user.getAge();
        assertEquals(result, actual);
    }
    //1year>
    @Test
    public void test_getAge_7() {
    	calendar.set(2001, calendar.get(Calendar.DECEMBER), 30);
        birthday = calendar.getTime();
        user.setBirthday(birthday);
        int result = 16;	
        int actual = user.getAge();
        assertEquals(result, actual);
    }
}
