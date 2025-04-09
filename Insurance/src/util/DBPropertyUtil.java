package util;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
	public static String getPropertyString(String fileName) {
        Properties props = new Properties();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String host = props.getProperty("hostname");
        String port = props.getProperty("port");
        String db = props.getProperty("dbname");
        String user = props.getProperty("username");
        String pass = props.getProperty("password");

        return "jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + user + "&password=" + pass;
    }

}
