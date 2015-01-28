import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by aaron on 1/27/15.
 */
public class FlashCardCreator {
    String[] terms;
    String[] definitions;
    String[] fileLines;
    String file = "/media/aaron/4600-7BAD/Spring_2015/Classes/Geomorphology/Notes/Book_Notes/Chapter_1.odt";

    private void readFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String fileLine = null;
            int lineCount = 0;
            while((fileLine = reader.readLine()) != null){
                fileLines[lineCount] = fileLine;
                lineCount++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        FlashCardCreator test = new FlashCardCreator();
        test.readFile();
    }
}
