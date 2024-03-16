import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class UtilPropFalkinburg {
	static final String PROP_FILENAME_REMOTE = "/var/lib/tomcat9/webapps/webproject-ex-0213-falkinburg/src/main/webapp/config.properties";
	
	public static Properties getProperties() {
		Properties props = new Properties();
		try {
			InputStream inputStream = UtilPropFalkinburg.class.getResourceAsStream(PROP_FILENAME_REMOTE);
			props.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
}
