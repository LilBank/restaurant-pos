package controller;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * ScreenController class to keep track of the different scene.
 * 
 * @author Piyawat & Vichapol
 *
 */
public class ScreenController {
	public static void switchWindow(Stage window, Application app) {
		try {
			app.start(window);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
