/**
 * 
 */
package HowCanIHelp;

/**
 * @author dweintraub
 *
 */
import java.util.*;					//Needed for the Scanner class
import javax.swing.JOptionPane;		//Needed for dialog boxes

public class AppDriver {

	/**
	 * The main method mimics the How Can I Help? Android application by prompting the user to
	 * input information to create an app user profile, allows them to generate donation
	 * offers and/or requests for donations, then search for matches.
	 * <p>
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Declare variables
		String input;
		
		//Create ArrayList to hold list of app users
		ArrayList<AppUser> userList = new ArrayList<AppUser>();
		ArrayList<RecipientItem> requestsList = new ArrayList<RecipientItem>();
		ArrayList<DonorItem> donationsList = new ArrayList<DonorItem>();
		
		//Ask user if they are a first-time user looking to register
		input = JOptionPane.showInputDialog("Are you a first-time user of How Can I Help? Enter Y/y for yes or N/n for no");
		if (input.equalsIgnoreCase("y")) {
			registration();
		}
		else if (input.equalsIgnoreCase("n")) {
			String answer = JOptionPane.showInputDialog("Are you already registered? Enter Y/y for yes or N/n for no");
			if (answer.equalsIgnoreCase("y")) {
				userLogin();
			}
			else if (answer.equalsIgnoreCase("n")) {
				JOptionPane.showMessageDialog(null,  "Please register");
				registration();
			}
			else {
				JOptionPane.showMessageDialog(null,  "Invalid input");
				System.exit(0);
			}
		}
		else {
			JOptionPane.showMessageDialog(null,  "Invalid input");
			System.exit(0);
		}

	}
	
	/**
	 * The registration method collects personal information from first-time users to generate their app user
	 * profile.
	 * <p>
	 * @return an ArrayList of AppUser objects
	 */
	
	public static ArrayList<AppUser> registration() {
		
		//Declare variables
		String firstName;
		String lastName;
		String email;
		String organization;
		String password;
		String inputHouseNumber;
		long houseNumber;
		String streetName;
		String city;
		String state;
		String inputZipCode;
		long zipCode;
		String phoneNumber;
		
		//Collect registration information from new users
		firstName = JOptionPane.showInputDialog("What is your first name?");
		lastName = JOptionPane.showInputDialog("What is your last name?");
		organization = JOptionPane.showInputDialog("If you are registering as part of an organization, please enter its name. " +
				"Otherwise please enter null.");
		email = JOptionPane.showInputDialog("Please enter your email");
		password = JOptionPane.showInputDialog("Please enter a password consisting of at least 8 characters: ");
		streetName = JOptionPane.showInputDialog("Please enter your street name: ");
		inputHouseNumber = JOptionPane.showInputDialog("Please enter the house number?");
		houseNumber = Long.parseLong(inputHouseNumber);
		city = JOptionPane.showInputDialog("Please enter the city: ");
		state = JOptionPane.showInputDialog("Please enter the state: ");
		inputZipCode = JOptionPane.showInputDialog("Please enter the zip code: ");
		zipCode = Long.parseLong(inputZipCode);
		phoneNumber = JOptionPane.showInputDialog("Please enter your phone number: ");
			
		//Instantiate new AppUser object with user's info and add to list of users
		userList.add(new AppUser(email, password, organization, firstName, lastName, houseNumber, streetName, city, state, zipCode, phoneNumber));
		
		//Return list of user app objects with new user added to it
		return userList;
		
		//Ask user if he/she wants to log into the app to request or offer goods or services
		String input = JOptionPane.showInputDialog("Would you like to login to request or offer a donation of goods or services?" +
		"Enter Y/y for yes or N/n for no");
		 
		if (input.equalsIgnoreCase("y")) {
			userLogin();
		}
		else if (input.equalsIgnoreCase("n")) {
			JOptionPane.showMessageDialog(null, "Logging you out...");
			System.exit(0);
		}
		else {
			JOptionPane.showMessageDialog(null,  "Invalid input");
			System.exit(0);
		}
	}
	
	/**
	 * The userLogin method allows a registered user to log into the app.
	 */
	
	public static void userLogin() {
		
		//Declare variables
		String loginEmail;
		String loginPassword;
		
		loginEmail = JOptionPane.showInputDialog("Enter your email: ");
		loginPassword = JOptionPane.showInputDialog("Enter your password: ");
		
		int index = userList.getEmail().indexOf(loginEmail);
		if (index == -1) {
			JOptionPane.showMessageDialog(null, "Sorry, that email is not found in our system.  Please try logging in again.");
			System.exit(0);
		}
		else {
			if (userList.getPassword(index).equals(loginPassword)) {
				requestOrDonate(userList.getIDNumber(index));
			}
			else {
				JOptionPane.showMessageDialog(null, "Sorry, that password is not registered with that email.  Please try logging in again.");
				System.exit(0);
			}
		}
	}
		
	/**
	 * The requestOrDonate method prompts the user to choose to either offer a donation or request goods or services.
	 * <p>
	 * @param IDNumber the user's IDNumber
	 */

	public static void requestOrDonate(long IDNumber) {
	
	String input = JOptionPane.showInputDialog("Would you like to request an item or service? Enter Y/y for yes or N/n for no");
		
		if (input.equalsIgnoreCase("y")) {
			requestDonation(IDNumber);
		}
		else if (input.equalsIgnoreCase("n")) {
			String answer = JOptionPane.showInputDialog("Would you like to donate an item or service?  Enter Y/y for yes or N/n for no");
			if (answer.equalsIgnoreCase("y") {
				offerDonation(IDNumber);
			}
			else if (answer.equalsIgnoreCase("n") {
				JOptionPane.showMessageDialog(null, "Logging you out...");
				System.exit(0);
			}
		else {
			JOptionPane.showMessageDialog(null,  "Invalid input");
			System.exit(0);
		}
	}
		
	}
	
	/**
	 * The requestDonation method prompts the user to input a request for goods or services and the amount
	 * needed, creating a recipientItem object and storing it in a requestedItems list.
	 * <p>
	 * @param IDNumber the user's ID number
	 * @return an ArrayList of recipient item objects
	 */
	
		public static ArrayList<RecipientItem> requestDonation(long IDNumber) {
			//Declare variables
			String itemRequested;
			String inputNumNeeded;
			long numNeeded;
			
			//Prompt user to input item and number needed
			itemRequested = JOptionPane.showInputDialog("Please enter the item or service you are requesting: ");
			inputNumNeeded = JOptionPane.showInputDialog("Please enter the number of items you are requesting: ");
			numNeeded = Long.parseLong(inputNumNeeded);
			
			//Instantiate new RecipientItem object with request's info and add to list of requests
			requestsList.add(new RecipientItem(IDNumber, itemRequested, numNeeded));
			
			//Return list of recipient item objects with new request added to it
			return requestsList;
			
			//Search donorList for matches 
		}
		
		/**
		 * The offerDonation method prompts the user to input a donation offer of goods or services and the amount
		 * being offered, creating a donorItem object and storing it in a donations list.
		 * <p>
		 * @param IDNumber the user's ID number
		 * @return an ArrayList of donor item objects
		 */
		
			public static ArrayList<DonorItem> requestDonation(long IDNumber) {
				//Declare variables
				String itemOffered;
				String inputNumOffered;
				long numOffered;
				
				//Prompt user to input item and number needed
				itemOffered = JOptionPane.showInputDialog("Please enter the item or service you would like to donate: ");
				inputNumOffered = JOptionPane.showInputDialog("Please enter the number of items you can donate: ");
				numOffered = Long.parseLong(inputOffered);
				
				//Instantiate new RecipientItem object with request's info and add to list of requests
				donationsList.add(new DonorItem(IDNumber, itemOffered, numOffered));
				
				//Return list of recipient item objects with new request added to it
				return donationsList;
				
				//Search donorList for matches 
}
