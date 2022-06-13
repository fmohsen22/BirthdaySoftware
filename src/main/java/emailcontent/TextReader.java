package emailcontent;

import com.mastertheboss.Constants.Gender;
import com.mastertheboss.framework.Configuration;
import org.junit.Test;

import java.io.*;

public class TextReader {



    public static String textReader(Gender gender,String name, String sender){
        String g = "";
        String path = Configuration.of().getPath("text");
        switch (gender){
            case Male:
                g="Mr.";
                break;
            case Female:
                g="Mrs.";
        }
        StringBuilder sb = new StringBuilder();
        String strLine = "";
        StringBuilder outPut
                = new StringBuilder("");
        try {
            BufferedReader br = new BufferedReader(new FileReader(path+"/email Sample.txt"));
            while (strLine != null)
            {
                sb.append(strLine);
                sb.append(System.lineSeparator());
                strLine = br.readLine();
                outPut.append(strLine+"\n");
            }
            br.close();

            strLine = outPut.toString().replace("<gender>",g).replace("<name>",name).replace("<sender name>",sender).replace("null","");
            System.out.println(strLine);
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Unable to read the file.");
        }
        return strLine;
    }

    @Test
    public void test(){
        textReader(Gender.Male,"Ebi","Mohsen");
    }

}
