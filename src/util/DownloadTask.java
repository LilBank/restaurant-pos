package util;

import java.util.ArrayList;
import java.util.List;

import database.DBManager;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextAlignment;
import model.Menu;
import model.Order;

public class DownloadTask extends Task<List<Button>> {
	// single instantiation
	private static DBManager dbm = DBManager.getInstance();
	private static Order o = Order.getInstance();
	private static List<Menu> foodname = dbm.getFoodname("Foods");
	private static List<Menu> drinkname = dbm.getFoodname("Drinks");
	private static List<String> foodUrl = dbm.getFoodUrl("Foods");
	private static List<String> drinkUrl = dbm.getFoodUrl("Drinks");
	private static List<Button> buttonList = new ArrayList<Button>();

	@Override
	protected List<Button> call() throws Exception {
		int i = 0;
		for (Menu item : foodname) {
			Button button = new Button(item.getName());
			Image image = new Image(foodUrl.get(i));
			i++;
			ImageView view = new ImageView(image);
			view.setFitHeight(100);
			view.setFitWidth(100);
			button.setPrefSize(150, 150);
			button.setWrapText(true);
			button.setTextAlignment(TextAlignment.CENTER);
			button.setGraphic(view);
			button.setUserData(item);

			// set handler for the button
			button.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					o.addOrder((Menu) button.getUserData());
					System.out.println(((Menu) button.getUserData()).getName());

				}
			});
			buttonList.add(button);
		}
		return buttonList;

	}
	public static void main(String[] args) {
		Task<List<Button>> download = new DownloadTask();
		new Thread(download).start();
	}
}
