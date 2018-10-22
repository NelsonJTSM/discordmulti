import java.util.*;


public class randomG
{
    public static void main (String args[])
    {
        Scanner scan = new Scanner(System.in);
        int random, answer;
        int counter =1;
        random = (int)(Math.random() * 100+1);

        System.out.println("\n Enter a number between 1 and 100");

        answer = scan.nextInt();

        while(random != answer)
        {
            if(counter ==1) {

                if(answer <1 || answer>100)
                {
                    System.out.println("Try again");
                }
                else if(random <answer)
                {
                    System.out.println("Go Lower");
                }
                else if(random> answer)
                {
                    System.out.println("Go Higher");
                }
                else
                {
                    System.out.println("\nCongrats you got it right in "+ counter + " round");
                }
            }
            else
            {


                answer = scan.nextInt();
                if(answer <1 || answer>100)
                {
                    System.out.println("Try again");
                }
                else if(random <answer)
                {
                    System.out.println("Go Lower");
                }
                else if(random> answer)
                {
                    System.out.println("Go Higher");
                }
                else
                {
                    System.out.println("\n Congrats you got it right in "+ counter + " rounds");
                }
            }

            counter++;

        }
    }
}
