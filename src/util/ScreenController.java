package util;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * An Interface class containing method for controlling windows.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public interface ScreenController {
	/**
	 * Method for switching scene by receiving current stage and another window
	 * that extends Application.(switchWindow(from,to))
	 * 
	 * @param Stage
	 * @param Application
	 */
	public static void switchWindow(Stage window, Application app) {
		try {
			app.start(window);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
