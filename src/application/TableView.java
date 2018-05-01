package application;

import javafx.stage.Stage;

public class TableView extends AbstractWindow {
	
	@Override
	public void start(Stage stage) {
		try {
			super.setFilename("view/normal-tableview.fxml");
			super.start(stage);
			stage.setTitle("Sign Up");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
