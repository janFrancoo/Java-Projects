import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	private static ArrayList<Account> accountList = new ArrayList<>();
	
	public static int menu() {
		
		while(true) {
		
			System.out.println("\t\t\tCUSTOMER ACCOUNT BANKING MANAGEMENT SYSTEM\n");
			System.out.println("\t\t\t\tWELCOME TO THE MAIN MENU\n\n");
			System.out.println("1. Create New Account");
			System.out.println("2. Update an Account");
			System.out.println("3. Transactions");
			System.out.println("4. Check Details of an Account");
			System.out.println("5. Remove an Account");
			System.out.println("6. Customer's List");
			System.out.println("7. Exit\n\n");
			System.out.print("Enter your choice: ");
			
			int choice;
			Scanner scanner = new Scanner(System.in);
			choice = scanner.nextInt();
			if(choice <= 7 && choice >= 1) {
				return choice;
			} else {
				System.out.println("\n\tEnter a valid number.");
			}
			
		}

	}
	
	public static Account create_account() {
		int id;
		String name, surname, address, phone;
		double balance;
		Scanner scanner = new Scanner(System.in);
		System.out.print("\tEnter Customer ID: ");
		id = scanner.nextInt();
		System.out.print("\tEnter Customer Name - Surname: ");
		scanner.nextLine();
		String[] nameSurname = scanner.nextLine().split(" ", 2);
		name = nameSurname[0];
		surname = nameSurname[1];
		System.out.print("\tEnter Customer Address: ");
		address = scanner.nextLine();
		System.out.print("\tEnter Customer Phone: ");
		phone = scanner.nextLine();
		System.out.print("\tEnter Customer Balance: ");
		balance = scanner.nextDouble();
		
		Account account = new Account(id, name, surname, address, phone, balance);
		
		return account;
	}
	
	public static void show_accounts() {
		
		for(Account account : accountList) {
			System.out.println(account);
			System.out.println("\n");
		}
		
	}
	
	public static void check_details() {
		
		int searchID;
		String searchName;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Search by ID: ");
		searchID = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Search by Name: ");
		searchName = scanner.nextLine();
		
		for(Account account : accountList) {
			if(searchID == account.getId() || searchName == account.getName()) {
				System.out.println(account);
			}
		}
		
	}
	
	public static void update_account() {
		
		int searchID;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Search by ID: ");
		searchID = scanner.nextInt();
		
		for(Account account : accountList) {
			if(searchID == account.getId()) {
				String name, surname, address, phone;
				System.out.print("\tUpdate Customer Name - Surname: ");
				scanner.nextLine();
				String[] nameSurname = scanner.nextLine().split(" ", 2);
				name = nameSurname[0];
				surname = nameSurname[1];
				System.out.print("\tEnter Customer Address: ");
				address = scanner.nextLine();
				System.out.print("\tEnter Customer Phone: ");
				phone = scanner.nextLine();
				account.setName(name);
				account.setSurname(surname);
				account.setAddress(address);
				account.setPhone(phone);
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				LocalDate localDate = LocalDate.now();
				account.setLastUpdateTime(dtf.format(localDate));
			}
		}
		
	}
	
	public static void delete_account() {
		
		int searchID;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Search by ID: ");
		searchID = scanner.nextInt();
		
		for(Account account : accountList) {
			if(searchID == account.getId()) {
				accountList.remove(account);
			}
		}
		
	}
	
	public static void transaction() {
		
		int searchID;
		double money;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Search by ID: ");
		searchID = scanner.nextInt();
		
		for(Account account : accountList) {
			if(searchID == account.getId()) {
				System.out.print("Money to account that has ID: ");
				searchID = scanner.nextInt();
				for(Account account2: accountList) {
					if(searchID == account2.getId()) {
						System.out.print("How much money?: ");
						money = scanner.nextDouble();
						account.transaction(account2, money);
					}
				}
			}
		}
		
	}
	
	public static void save_list() {
		
		try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("acc.bin"))){
			
			output.writeObject(accountList);
			
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
		} catch (IOException e) {
			System.out.println("IOException");
		}
		
	}
	
	public static void load_list() {
		
		try(ObjectInputStream input = new ObjectInputStream(new FileInputStream("acc.bin"))){
			
			accountList = (ArrayList<Account>)input.readObject();
			
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
		} catch (IOException e) {
			System.out.println("IOException");
		} catch (ClassNotFoundException e) {
			System.out.println("ClasNotFoundException");
		}
		
	}

	public static void main(String args[]) {
		
		int choice;
		load_list();
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			for(int i=0; i<10; i++) {
				System.out.println("\n");
			}
			choice = menu();
			
			if(choice == 1) {
				Account newAccount = create_account();
				accountList.add(newAccount);
				System.out.println("\tAccount successfully created.");
				save_list();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					System.out.println("Interruption.");
				}
			} else if (choice == 2) {
				update_account();
				System.out.println("\tAccount successfully updated.");
				save_list();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					System.out.println("Interruption.");
				}
			} else if (choice == 3) {
				transaction();
				save_list();
			} else if (choice == 4) {
				check_details();
				System.out.println("Press enter for to return the main menu.");
				scanner.nextLine();
			} else if (choice == 5) {
				delete_account();
				System.out.println("\tAccount successfully deleted.");
				save_list();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					System.out.println("Interruption.");
				}
			} else if (choice == 6) {
				show_accounts();
				System.out.println("Press enter for to return the main menu.");
				scanner.nextLine();
			} else if (choice == 7) {
				save_list();
				System.out.println("Have a nice day!");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			
		}
		
	}
	
}
