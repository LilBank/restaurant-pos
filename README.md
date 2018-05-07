# Restaurant Point Of Sale (POS)

**Restaurant POS** is the application replacing the use of paper for ordering system. It can be very useful to restaurants that currently lack uses of technology, to improve efficiency and speed of transaction.

## Prerequisites

What things you need to install in the application.

* [MySQL-Connector](https://dev.mysql.com/downloads/connector/j/) - Used for connecting to database.
* [JBCrypt](http://www.mindrot.org/projects/jBCrypt/) - Used for encrypting the password in database.

## Installing

1. Download the whole folder containing *runnable* RestaurantPOS.jar file.
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

## Packages

* application - Contains all the application classes including Main class.
* view - Contains all the FXML linked with Scene Builder.
* controller - Contains all the controllers of each application class
* database - Contains a class having methods to manage the database.
* model - Contains all the object classes of the application.
* util - Contains all the utility classes and interface.

## Patterns 

There are some design and technology used.

* MVC (Model View Controller) - Pattern will be used in this program. 
* Singleton pattern - Used in order and menu.
* Observer pattern - Used in display ordered items and more.

## Built With

- [Eclipse IDE](https://www.eclipse.org/ide/) - Constructor of codes.
- [Scene Builder JavaFx](http://gluonhq.com/products/scene-builder/) - Constructor of javafx FXML project.
- [Chrome MySQL Admin](https://chrome.google.com/webstore/detail/chrome-mysql-admin/ndgnpnpakfcdjmpgmcaknimfgcldechn) - Database for storing the data of this application. 

## Developers 

1. Piyawat Setthitikun ([bankkeez](https://github.com/bankkeez))
2. Vichaphol Thamsuthikul ([kimvcp](https://github.com/kimvcp)) 

