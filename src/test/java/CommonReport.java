import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommonReport {
    public static void CreateReport(String filename, String writeTxt) throws IOException {

        File file = new File(filename);

        //Create output file
        try {
            if (file.createNewFile()) {

            } else {

            }
            Scanner scanner = new Scanner(new File(filename));
            List<String> testAll = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                testAll.add(line);
            }
            scanner.close();
            testAll.add(writeTxt);

            PrintWriter myreport = null;
            myreport = new PrintWriter(new FileWriter(filename), true);
            for(int i=0;i<testAll.size();i++){
                myreport.println(testAll.get(i));
            }
            myreport.close();
        } catch (IOException e) {

        }
    }
}
