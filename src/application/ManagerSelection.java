package application;

import javafx.stage.Stage;

public class ManagerSelection extends AbstractWindow {
	@Override
	public void start(Stage stage) {
		try{
			super.setFilename("view/manager-selection.fxml");
			super.start(stage);
			stage.setTitle("Manager Mode");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
