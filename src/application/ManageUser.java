package application;

import javafx.stage.Stage;
import util.AbstractWindow;

public class ManageUser extends AbstractWindow{
	@Override
	public void start(Stage stage) {
		try {
			super.setFilename("view/manager-usermanager.fxml");
			super.start(stage);
			stage.setTitle("User Manager");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
