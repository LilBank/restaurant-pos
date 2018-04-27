package controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

import application.CSTable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CSMenuController {
	@FXML
	Button back;
	@FXML
	Button newImage;
	@FXML
	ListView<Label> listItems;
	@FXML
	ListProperty<Label> listProperty = new SimpleListProperty<>();

	List<Image> folderImage = new ArrayList<>();

	@FXML
	public void initialize() {
		listProperty.set(FXCollections.observableArrayList(folderImage));
		listItems.itemsProperty().bind(listProperty);
	}

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 * @param event
	 */
	public void backButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new CSTable());
	}

	public void insertImageHandler(ActionEvent event) throws MalformedURLException {
		FileChooser chooser = new FileChooser();
		File selectedFile = chooser.showOpenDialog(new Stage());
		System.out.print(selectedFile.getAbsolutePath());
		Image image = new Image(selectedFile.toURI().toURL().toExternalForm());
		folderImage.add(image);
		listProperty.set(FXCollections.observableArrayList(folderImage));
		reloadListItems();
	}

	public void reloadListItems() {
		for (Label image : folderImage) {
			Label food = new Label();
			food.setGraphic(new ImageView(image));
			listItems.getItems().addAll(food);
		}
		listItems.refresh();
	}
}
