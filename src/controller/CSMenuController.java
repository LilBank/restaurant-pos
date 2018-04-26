package controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.BinaryOperator;

import application.CSTable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CSMenuController {
	@FXML
	Button back;
	@FXML
	Button newImage;

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 * @param event
	 */
	public void backButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new CSTable());
	}

	public void insertImageHandler(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		File selectedFile = chooser.showSaveDialog(new Stage());
		Image image = new Image(getClass().getResourceAsStream(selectedFile + ""));
		newImage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Button button = (Button) e.getSource();
				button.setGraphic(new ImageView(image));
			}
		});
	}

	public static String fileToString(String file) {
		String result = null;
		DataInputStream in = null;

		try {
			File f = new File(file);
			byte[] buffer = new byte[(int) f.length()];
			in = new DataInputStream(new FileInputStream(f));
			in.readFully(buffer);
			result = new String(buffer);
		} catch (IOException e) {
			throw new RuntimeException("IO problem in fileToString", e);
		} finally {
			try {
				in.close();
			} catch (IOException e) { /* ignore it */
			}
		}
		return result;
	}
}
