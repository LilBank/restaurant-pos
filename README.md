# Restaurant Point Of Sale (POS)

**Restaurant POS** replace the use of paper for ordering system. With security, every employee must sign up and login in work so we could classify which dish is ordered by who in case of responsibility needed. (all user’s data are stored in database)
Daily sales are recorded for monthly progress. (also recorded in database)
Manager mode will be added in this program. (adding or removing a menu or set a promotion.
Menu buttons are created from menu stored in database.

## Prerequisites

What things you need to install in the application.

* [MySQL-Connector](https://dev.mysql.com/downloads/connector/j/) - Used for connecting to database.
* [JBCrypt](http://www.mindrot.org/projects/jBCrypt/) - Used for encrypting the password in database.

## Installing

1. Download the whole folder containing *runnable* RestaurantPOS.jar file.
2. Add these JAR files by following the steps below.

	Open the project -> Build Path -> Configure Build Path.. -> Add External JARS..

## Running 


## Deployment

Our application can be useful to restaurants that currently lack use of technology, to improve efficiency and speed of usability.

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

## Developers 

1. Piyawat Setthitikun ([bankkeez](https://github.com/bankkeez))
2. Vichaphol Thamsuthikul ([kimvcp](https://github.com/kimvcp)) 

