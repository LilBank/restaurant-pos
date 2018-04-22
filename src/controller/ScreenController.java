package controller;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * ScreenController class to keep track of the different scene.
 * 
 * @author Piyawat & Vichapol
 *
 */
public class ScreenController {
	private Map<String, Pane> sceneMap = new HashMap<>();
	private Scene scene;

	public ScreenController(Scene scene) {
		this.scene = scene;
	}

	/**
	 * Adds the scene into the Map<K,V> for switching.
	 * 
	 * @param fxmlname
	 * @param pane
	 */
	public void addScene(String fxmlname, Pane pane) {
		sceneMap.put(fxmlname, pane);
	}

	/**
	 * Removes the scene from the Map<K,V>.
	 * 
	 * @param fxmlname
	 */
	public void removeScreen(String fxmlname) {
		sceneMap.remove(fxmlname);
	}

	/**
	 * Set the scene from fxmlname.
	 * 
	 * @param fxmlname
	 */
	public void activate(String fxmlname) {
		//setRoot for defining root node of scene graph
		scene.setRoot(sceneMap.get(fxmlname));
	}

}
