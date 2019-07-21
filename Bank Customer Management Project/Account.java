import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Account implements Serializable {

	private static final long serialVersionUID = -9180809925470496634L;
	private int id;
	private String name, surname, address, phone, createdTime, lastUpdateTime;
	private double balance;
	transient private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	transient private LocalDate localDate = LocalDate.now();
	
	public Account(int id, String name, String surname, String address, String phone, double balance) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phone = phone;
		this.balance = balance;
		this.createdTime = dtf.format(localDate);
		this.lastUpdateTime = dtf.format(localDate);
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void transaction(Account acc, double balance) {
		if(balance > this.balance) {
			System.out.println("\tInsufficient balance.");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.out.println("Interruption.");
			}
		} else {
			this.balance -= balance;
			acc.setBalance(acc.getBalance() + balance);
			System.out.println("\tMoney successfully has sent.");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.out.println("Interruption.");
			}
		}
	}

	@Override
	public String toString() {
		
		return "CUSTOMER ID: " + id + "\nCUSTOMER NAME: " + name + "\nCUSTOMER SURNAME: " + surname + "\nCUSTOMER ADDRESS: " + 
		address + "\nCUSTOMER PHONE: " + phone + "\nCUSTOMER BALANCE: " + balance + "\nCUSTOMER CREATED DAY: " + createdTime +
		"\nCUSTOMER LAST OPERATION DAY: " + lastUpdateTime;
	
	}
	
}
