package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import application.Tableview;
import application.Main;
import database.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import model.Menu;
import util.ImageFactory;
import util.ScreenController;

/**
 * MGTableController(manager) class contains method for handling event from the
 * UserInterface. Contains method for adding or removing menu with picture.
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
	
	public static List<Button> folderImage = new ArrayList<>();
	private static DBManager dbm = DBManager.getInstance();
	private ImageFactory instance = ImageFactory.getInstance();
	private static List<Menu> foodname = dbm.getFoodname("Foods");
	private static List<Menu> drinkname = dbm.getFoodname("Drinks");
	private Alert alert;

	/**
	 * Initialize the buttons to panes
	 */
	@FXML
	public void initialize() {
		instance.getFoodButton().forEach(x -> foodpane.getChildren().add(x));
		instance.getDrinkButton().forEach(x -> drinkpane.getChildren().add(x));
	}
	
	/**
	 * Get the list of buttons with the set images.
	 * 
	 * @return list of Buttons
	 */
	public static List<Button> getImage() {
		return folderImage;
	}

	/**
	 * Method for handling new Food button. Insert food images into the flow pane.
	 * 
	 * @param event
	 */
	public void insertFoodHandler(ActionEvent event) {
		createMenu("Foods", foodname, foodpane);
	}

	/**
	 * Method for handling new Drink button. Insert drink images into the flow pane.
	 * 
	 * @param event
	 */
	public void insertDrinkHandler(ActionEvent event) {
		createMenu("Drinks", drinkname, drinkpane);

	}

	/**
	 * Method for handling newImage button. Insert an image into the flow pane and
	 * database.
	 * 
	 * @param name
	 *            of table in database.
	 */
	public void createMenu(String table, List<Menu> name, FlowPane pane) {
		JTextField nameField = new JTextField(5);
		JTextField priceField = new JTextField(5);
		JTextField urlField = new JTextField(5);
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
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter Your Menu",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			if (nameField.getText().equals("") || priceField.getText().equals("") || urlField.getText().equals("")) {
				alert = new Alert(AlertType.ERROR, "Please input all the box", ButtonType.OK);
				alert.setHeaderText("Inputfield Error");
				alert.show();
			}
			if (!urlField.getText().contains(".jpg")) {
				alert = new Alert(AlertType.ERROR, "Url is incorrect", ButtonType.OK);
				alert.setHeaderText("Inputfield Error");
				alert.show();
			} else {
				myPanel = new JPanel();
				JLabel label = new JLabel();
				URL url;
				BufferedImage bufferImage;
				try {
					url = new URL(urlField.getText());
					bufferImage = ImageIO.read(url);
					label.setIcon(new ImageIcon(bufferImage));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				label.setSize(200, 200);
				myPanel.add(label);
				int preview = JOptionPane.showConfirmDialog(null, myPanel, "Image Preview",
						JOptionPane.OK_CANCEL_OPTION);
				if (preview == JOptionPane.OK_OPTION) {
					dbm.insertTo(table, nameField.getText(), Integer.parseInt(priceField.getText()),
							urlField.getText());
					Button button = new Button(name.get(name.size() - 1).getName());
					button.setPrefSize(220, 220);
					image = new Image(urlField.getText());
					ImageView view = new ImageView(image);
					view.setFitWidth(130);
					view.setFitHeight(160);
					button.setWrapText(true);
					button.setGraphic(view);
					pane.getChildren().add(button);
					alert = new Alert(AlertType.ERROR, "Please restart the application", ButtonType.OK);
					alert.setHeaderText("Action Required");
					alert.show();
				}
			}
		}
	}

	/**
	 * Method for handling deleteImage button. Delete the selected button from the
	 * flow pane.
	 * 
	 * @param event
	 */
	public void deleteImageHandler(ActionEvent event) {
		Button button = instance.getSelectedButton();
		Menu menu = null;
		if (button == null) {
			alert = new Alert(AlertType.ERROR, "Must select atleast one dish!", ButtonType.OK);
			alert.show();
		}
		if (foodpane.getChildren().contains(button)) {
			menu = (Menu) instance.getSelectedButton().getUserData();
			foodpane.getChildren().remove(button);
			dbm.removeImage("Foods", menu);
		}
		if (drinkpane.getChildren().contains(button)) {
			menu = (Menu) instance.getSelectedButton().getUserData();
			drinkpane.getChildren().remove(button);
			dbm.removeImage("Drinks", menu);
		}
	}

	/**
	 * Handler for back button. When event receive the table view scene is shown.
	 * 
	 * @param event
	 */
	public void backButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new Tableview());
	}

	/**
	 * Handler for logout button. When event receive the Start up scene is shown.
	 * 
	 * @param event
	 */
	public void logoutHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) logout.getScene().getWindow(), new Main());
	}
}
