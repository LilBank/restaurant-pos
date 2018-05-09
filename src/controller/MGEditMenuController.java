package controller;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.Tableview;
import application.Main;
import database.DBManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Menu;
import model.Order;
import sun.nio.ch.SelectionKeyImpl;
import util.DownloadTask;
import util.ImageFactory;
import util.ScreenController;

/**
 * MGTableController(manager) class contains method for handling event from the
 * UserInterface. Contains method for adding menu with picture or removing a
 * menu.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class MGEditMenuController {
	@FXML
	Button back;
	@FXML
	Button newFood;
	@FXML
	Button newDrink;
	@FXML
	Button deleteImage;
	@FXML
	Button logout;
	@FXML
	private FlowPane foodpane;
	@FXML
	private FlowPane drinkpane;
	/** List of all images */
	public static List<Button> folderImage = new ArrayList<>();
	// single instantiation
	private static DBManager dbm = DBManager.getInstance();
	private static List<Menu> foodname = dbm.getFoodname("Foods");
	private static List<Menu> drinkname = dbm.getFoodname("Drinks");
	private static List<String> foodUrl = dbm.getFoodUrl("Foods");
	private static List<String> drinkUrl = dbm.getFoodUrl("Drinks");
	ImageFactory instance = ImageFactory.getInstance();
	Order o = Order.getInstance();
	;

	/**
	 * Bind listView with ListProperty at the beginning.
	 */
	@FXML
	public void initialize() {
		instance.getFoodButton().forEach(x -> foodpane.getChildren().add(x));
		instance.getDrinkButton().forEach(x -> drinkpane.getChildren().add(x));
	}

	public static List<Button> getImage() {
		return folderImage;
	}

	/**
	 * Method for handling newImage button. Insert image to the list view.
	 * 
	 * @throws MalformedURLException
	 */
	public void insertFoodHandler(ActionEvent event) {
		createMenu("Foods");

	}

	/**
	 * Method for handling newImage button. Insert image to the list view.
	 * 
	 * @throws MalformedURLException
	 */
	public void insertDrinkHandler(ActionEvent event) {
		 createMenu("Drinks");

	}

	/**
	 * Method for handling newImage button. Insert image to the list view.
	 * 
	 * @throws MalformedURLException
	 */
	public void createMenu(String table) {
		// Ask user to input dialog.
		JTextField nameField = new JTextField(5);
		JTextField priceField = new JTextField(5);
		JTextField urlField = new JTextField(5);
		// Get the response value.
		Image image = null;
		Alert alert = null;
		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Name: "));
		myPanel.add(nameField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Price: "));
		myPanel.add(priceField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Url: "));
		myPanel.add(urlField);

		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter Name, Price and URL ",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			System.out.println(urlField.getText());
			if (nameField.getText().equals("")) {
				alert = new Alert(AlertType.ERROR, "Name is empty.", ButtonType.OK);
				alert.setHeaderText("Inputfield Error");
				alert.show();

			}
			if (priceField.getText().equals("")) {
				alert = new Alert(AlertType.ERROR, "Price is empty.", ButtonType.OK);
				alert.setHeaderText("Inputfield Error");
				alert.show();

			}
			if (urlField.getText().equals("")) {
				alert = new Alert(AlertType.ERROR, "URL is empty.", ButtonType.OK);
				alert.setHeaderText("Inputfield Error");
				alert.show();
			}

			if (!urlField.getText().contains(".jpg")) {
				alert = new Alert(AlertType.ERROR, "Url is incorrect", ButtonType.OK);
				alert.setHeaderText("Inputfield Error");
				alert.show();
			} else {
				// dbm.InsertTo(table, name, price, result.get());
				dbm.InsertTo(table, nameField.getText(), Integer.parseInt(priceField.getText()), urlField.getText());
				Button button = new Button(foodname.get(foodname.size()-1).getName());
				button.setPrefSize(150, 150);
				image = new Image(urlField.getText());
				ImageView view = new ImageView(image);
				view.setFitWidth(100);
				view.setFitHeight(100);
				button.setAlignment(Pos.TOP_CENTER);
				button.setGraphic(view);
				foodpane.getChildren().add(button);
			}
		}
	}

	/**
	 * during the test.
	 */
	public void chooseFile() {
		FileChooser chooser = new FileChooser();
		/** Open file dialog and save it to file */
		File selectedFile = chooser.showOpenDialog(new Stage());
	}

	/**
	 * Method for handling deleteImage button. Delete image in the list view.
	 * 
	 */
	public void deleteImageHandler(ActionEvent event) {
		folderImage.remove(folderImage.size() - 1);
	}

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 */
	public void backButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new Tableview());
	}

	/**
	 * Handler for logout button. When event recieve the Start up scene is shown.
	 * 
	 */
	public void logoutHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) logout.getScene().getWindow(), new Main());
	}
}
