import java.util.List;
import java.util.Scanner;
import java.util.Random;

/**
 * Created by aaron on 1/27/15.
 */
public class FlashCardCreator {
    String file = "/home/aaron/Documents/Spring_2015/Geomorphology/Chapter1.odt";
    int[] answerOrder;
    int correctCounter;
    int incorrectCounter;
    List definitionBank;
    List termBank;

    public void startTest(){
        Test test = new Test(file);
        test.createTest();
        answerOrder = test.getQuestionOrder();
        definitionBank = test.getDefinitions();
        termBank = test.getTerms();
        correctCounter = 0;
        incorrectCounter = 0;
        for(int i = 0; i < answerOrder.length; i++){
            int answer = answerOrder[i];
            String[] answerBank = new String[4];
            int[] answerNumbers = new int[4];
            String definition = test.getDefinition(answerOrder[i]);

            for(int j = 0; j < 4; j++){
                if(j == 0)
                    answerNumbers[j] = answer;
                else{
                    Random rand = new Random();
                    int randNum = -1;
                    boolean isNumInAry = true;
                    while(isNumInAry){
                        randNum = rand.nextInt(answerOrder.length);
                        isNumInAry = isInList(answerNumbers, randNum);
                    }
                    answerNumbers[j] = randNum;
                }
            }
            answerBank = convertNumsToAnswers(answerNumbers);

            System.out.print("Question " + i + ":\n");
            System.out.print(definition + "\n\n\n");

            int first, second, third;
            first = second = third = -1;
            int correctAnswer = -1;
            for(int j = 0; j < answerBank.length; j++){
                Random rand = new Random();
                int randNum = rand.nextInt(answerBank.length);
                boolean answerChosen = false;
                while(!answerChosen){
                    if(j == 0) {
                        first = randNum;
                        answerChosen = true;
                    }

                    if(j == 1){
                        if(randNum != first){
                            second = randNum;
                            answerChosen = true;
                        }
                        else
                            randNum = (randNum + 1) % answerBank.length;
                    }

                    if(j == 2){
                        if(randNum != first && randNum != second){
                            third = randNum;
                            answerChosen = true;
                        }
                        else
                            randNum = (randNum + 1) % answerBank.length;
                    }

                    if(j == 3){
                        if(randNum != first && randNum != second && randNum != third){
                            third = randNum;
                            answerChosen = true;
                        }
                        else
                            randNum = (randNum + 1) % answerBank.length;
                    }
                }



                if(randNum == 0)
                    correctAnswer = j;

                System.out.print((j+1) + ":" + answerBank[randNum] + "\n");
            }

            System.out.print("\n\nPlease choose an answer 1-4: ");
            Scanner scan = new Scanner(System.in);
            int userGuess = scan.nextInt();

            if(userGuess == (correctAnswer + 1)){
                System.out.print("Correct!\n\n\n");
                correctCounter++;
            }
            else{
                System.out.print("Incorrect.\n\n\n");
                incorrectCounter++;
            }

        }

        System.out.print("This concludes the quiz. Please wait while your score is calculated...");

        System.out.print("The results are:\n" + correctCounter + " correct answers.\n" + incorrectCounter + " incorrect answers.=\n");
        float percentCorrect = (float)correctCounter / definitionBank.size();
        System.out.print("\nYou got " + percentCorrect + "% correct!\n\n");
    }

    private boolean isInList(int[] ary, int item){
        boolean isContained = false;
        for(int i = 0; i < ary.length; i++){
            if(ary[i] == item)
                isContained = true;
        }

        return isContained;
    }

    private String[] convertNumsToAnswers(int[] answers){
        String[] terms = new String[answers.length];
        for(int i = 0; i < answers.length; i++){
            terms[i] = String.valueOf(termBank.get(answers[i]));
        }

        return terms;
    }

    public static void main(String[] args){
        FlashCardCreator test = new FlashCardCreator();
        test.startTest();
    }
}
