import java.io.IOException;
import java.lang.StringBuffer;
import java.lang.Object;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class Main {

    public static void main(String[] args) throws IOException {

        String user = new String(System.getProperty("user.name"));
        StringBuffer dataBuffer = new StringBuffer();
        List<String> newList = new ArrayList<>();
        newList = FileUtils.readLines(new File("C:\\Users\\" + user + "\\Documents\\DDCN-Wi-Fi-Testing\\wifi-test-08-06-16-13.32.59.txt"), "UTF-16");
        //List lines = FileUtils.readLines(new File("C:\\Users\\" + user + "\\Documents\\DDCN-Wi-Fi-Testing\\wifi-test-08-06-16-13.32.59.txt"), "UTF-8");
        for (String line : newList) {
            String lineString = line.toString();
            //System.out.println(lineString);
            String newString = lineString.trim().replaceAll(" ","").replaceAll("\\t", "");
            System.out.println(newString);
            if (lineString.contains("Signal")) {
                //System.out.println("YES");
                //dataBuffer.append(lineString);
                //System.out.println("HELLO" + dataBuffer.toString());
            } else {
                //System.out.println("No String");
            }
        }
    }
}




