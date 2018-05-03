package factory;

import java.net.URL;

import com.sun.org.apache.bcel.internal.util.ClassLoader;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sun.security.jca.GetInstance;

public class ImageFactory {
	/** singleton instance of ImageFactory. */
	protected static ImageFactory factory;

	/**
	 * Get an instance of ImageFactory.
	 * 
	 * @return object of a subclass
	 */
	ImageFactory getInstance() {
		if (factory == null)
			factory = new ImageFactory();
		return factory;
	}

	public ImageView getImage(String filename) {
		URL url = new ClassLoader().getClass().getResource(filename);
		Image image = new Image(url.getPath());
		return new ImageView(image);
	}

}