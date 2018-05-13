# Restaurant Point Of Sale (POS)

**Restaurant POS** is the application replacing the use of paper for ordering system. It can be very useful to restaurants that currently lack uses of technology, to improve efficiency and speed of transaction.

## Prerequisites

What things you need to install in the application.

* [MySQL-Connector](https://dev.mysql.com/downloads/connector/j/) - Used for connecting to database.
* [JBCrypt](http://www.mindrot.org/projects/jBCrypt/) - Used for encrypting the password in database.

## Installing

1. Download the whole folder containing *runnable* jar file.
2. Add these JAR files by following the steps below.

	Open the project -> Build Path -> Configure Build Path.. -> Add External JARS..

## Deployment

**Note:** This application required an *Internet Connection*

There are 3 modes for user.
1. Manager mode - Customize the menu and table and also remove the employee. 
2. Employee mode - Receive the order from customer and Check bill of each table.
3. Customer mode - Order the dishes and display to the Employee view.

With security, every employee must sign up and login in work so we could classify which dish is ordered by who in case of responsibility needed. (all user, menu and table data are stored in database)

## Running 

### Login ###

Manager mode or Employee mode is shown depending on user's access level.

![capture2](https://user-images.githubusercontent.com/32285706/39966793-c68a3bb6-56db-11e8-97f7-b00e6bbba7f1.JPG)

### Manager Mode ###

Manager has more functions than Employee

![capture10](https://user-images.githubusercontent.com/32285706/39966931-bf2a65d8-56dd-11e8-82cc-1a850606df27.JPG)

Edit Menu Function 

![capture5](https://user-images.githubusercontent.com/32285706/39966943-e488732e-56dd-11e8-9967-cd560474b8dd.JPG)

Remove User Function

![capture6](https://user-images.githubusercontent.com/32285706/39966944-fbb5626e-56dd-11e8-8f55-c649f61c2e2b.JPG)

Manage Table Function

![capture7](https://user-images.githubusercontent.com/32285706/39966949-12c47b70-56de-11e8-8744-4e573586758f.JPG)

Order Foods and Drinks Function

![capture12](https://user-images.githubusercontent.com/32285706/39966988-9a5bb882-56de-11e8-9030-0ed44f94f802.JPG)

Summary Function

![capture9](https://user-images.githubusercontent.com/32285706/39966954-24e2e670-56de-11e8-8aeb-20c8b2349605.JPG)



### Employee Mode ###

Sign up for new employee.

![capture3](https://user-images.githubusercontent.com/32285706/39966796-c8e6b934-56db-11e8-9ac6-a340bc0ded63.JPG)

Employee is able to access to the Table View. (Same as manager but less function) 

![capture8](https://user-images.githubusercontent.com/32285706/39966873-e5d7eb52-56dc-11e8-86e9-18ce9b4b2fc7.JPG)



### Customer Mode ###

Start Interface

![capture1](https://user-images.githubusercontent.com/32285706/39966645-922bc1f2-56d9-11e8-8eb0-6c3d21f27775.JPG)

User Interface

![capture](https://user-images.githubusercontent.com/32285706/39966594-ceb0a2a6-56d8-11e8-8369-76cb5076bd48.JPG)

## Packages

* application - Contains all the application classes including Main class.
* view - Contains all the FXML linked with Scene Builder.
* controller - Contains all the controllers of each application class
* database - Contains a class having methods to manage the database.
* model - Contains all the object classes of the application.
* util - Contains all the utility classes and interface.

## Patterns 

There are some design and technology used.

* MVC Pattern (Model-View-Controller) - Used for separating application's concerns. 
* Singleton Pattern - Used for encapsulating customer's orders and can be used in many classes.
* Observer Pattern - Used for displaying ordered items and observing changes in database.

## Technology

### Map Interface ###

*Interface Map<K,V>* ( K - the type of keys maintained, V - the type of mapped values )

**Concept:** If you call a key, you get a value. But the value does not return the key.

### Database ###

*Database* is a storage of data, where user can access the data any time and any where using  

**Usability:** Both Customer's and Manager's program can access the same data .

### jBCrypt ###

*jBCrypt* is a java used for hashing password. With high quality of algorithm, it prevents the hacker from hacking the password in the database. Users can use the method **checkpw( your password, password in database)** to check if the password matches or not.

**Additional:** Try using PreparedStatement with wildcard (?) for preventing SQL Injection 

	sqlCommand = "SELECT * FROM User WHERE name = ?";
		PreparedStatement stmt = connection.prepareStatement(sqlCommand);
			stmt.setString(1, user);
			if (BCrypt.checkpw(yourPass, databasePass)) {
				dowork();
			}
			stmt.executeQuery();
		
		


## Built With

- [Eclipse IDE](https://www.eclipse.org/ide/) - Constructor of codes.
- [Scene Builder JavaFx](http://gluonhq.com/products/scene-builder/) - Constructor of javafx FXML project.
- [Chrome MySQL Admin](https://chrome.google.com/webstore/detail/chrome-mysql-admin/ndgnpnpakfcdjmpgmcaknimfgcldechn) - Database for managing the data of this application. 

## Developers 

1. Piyawat Setthitikun ([bankkeez](https://github.com/bankkeez))
2. Vichaphol Thamsuthikul ([kimvcp](https://github.com/kimvcp)) 

