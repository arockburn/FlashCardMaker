import java.util.Arrays;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 2/21/15.
 */
public class Test {
    int numTerms;
    List<String> definitions = new ArrayList<String>();
    List<String> terms = new ArrayList<String>();
    List<String> fileLines = new ArrayList<String>();
    int[] questionOrder;
    String file;

    Test(){
        numTerms = 0;
        file = null;
    }

    Test(String f){
        file = f;
        readFile();
        splitTerms();
        numTerms = terms.size();
        questionOrder = new int[numTerms];
        Arrays.fill(questionOrder, -1);
    }

    private void readFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String fileLine = null;
            int lineCount = 0;
            while((fileLine = reader.readLine()) != null){
                fileLines.add(fileLine);
                lineCount++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void splitTerms(){
       for(int i = 0; i < fileLines.size(); i++){
           if(fileLines.get(i).contains(":")) {
               String[] splitLine = (fileLines.get(i)).split(":");
               terms.add(splitLine[0].trim());
               definitions.add(splitLine[1].trim());
           }
       }
    }

    public List getDefinitions(){
        return definitions;
    }

    public List getTerms(){
        return terms;
    }

    private boolean searchList(int num){
        boolean isInList = false;
        for(int i = 0; i < numTerms; i++){
            if(questionOrder[i] == num)
                isInList = true;
        }

        return isInList;
    }

    private void createQuestionOrder(){
        for(int i = 0; i < numTerms; i++){
            Random rand = new Random();
            int randNum = rand.nextInt(numTerms);
            boolean isNumListed = false;
            int loopCnt = 0;
            while(!isNumListed){
                isNumListed = searchList(randNum);

                if(isNumListed){
                    randNum = (randNum + 1) % numTerms;
                    isNumListed = false;
                }
                else{
                    questionOrder[i] = randNum;
                    isNumListed = true;
                }
            }
        }
    }

    public void createTest(){
        createQuestionOrder();
    }

    public int[] getQuestionOrder(){
        return questionOrder;
    }

    public String getDefinition(int num){
        return definitions.get(num);
    }
}
