package died.izaguirre.haulet.tp.gui.utilities;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public final class CSSParser {
	
	public static <T> String parseStyle(String ruta, Class<T> cl)
	{
		String text;
		try {
			text = Files.readString(Paths.get(cl.getResource(ruta).toURI()));
			return text;
		} catch (IOException | URISyntaxException e ) {
			// TODO Auto-generated catch block
			return null;
		}
	}

}
