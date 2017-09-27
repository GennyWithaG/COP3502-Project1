import java.util.*;
import java.util.Scanner;
public class BlackJack
{
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);

        boolean end = false;
        boolean gameEnd = false;
        String message= "1. Get another card\n" + "2. Hold hand\n" + "3. Print statistics\n" + "4. Exit\n";
        String cardMessage = "";
        int playerHand = 0;
        int dealerHand = 0;
        int gameNumber = 1;
        int playerCard = 0;
        int playerWins = 0;
        int dealerWins = 0;
        int tieGames = 0;
        int answer = 0;

        double winPercentage = 0.0;
        while(!end)
        {
            System.out.println("START GAME #" + gameNumber);
            playerCard = newCard();
            cardMessage = printCard(playerCard);
            if(cardMessage.equals("Your card is a King!")||cardMessage.equals("Your card is a Queen!")||cardMessage.equals("Your card is a Jack!"))
            {
                playerCard = 10;
            }
            playerHand = playerCard;
            while(!gameEnd)
            {
                System.out.println(message);
                try
                {
                    answer = in.nextInt();
                }
                catch(InputMismatchException ex)
                {
                System.err.println("Invalid Input! \nPlease use an interger between 1 and 4.");
                in.nextLine();
                }
                if( answer == 1)
                {
                    playerCard = newCard();
                    cardMessage = printCard(playerCard);
                    if(cardMessage.equals("Your card is a King!")||cardMessage.equals("Your card is a Queen!")||cardMessage.equals("Your card is a Jack!"))
                    {
                        playerCard = 10;
                    }
                    playerHand += playerCard;
                    System.out.println("Your hand is: " + playerHand);
                    if(playerHand > 21)
                    {
                        dealerWins++;
                        gameEnd = true;
                        System.out.println("Dealer wins!");
                    }
                    else if(playerHand == 21)
                    {
                        playerWins++;
                        gameEnd = true;
                        System.out.println("You win!");
                    }
                }
                else if( answer == 2)
                {
                    dealerHand = newCard();
                    System.out.println("Dealer's Hand: " + dealerHand);
                    System.out.println("Your Hand: " + playerHand);

                    if(dealerHand > 21)
                    {
                        playerWins++;
                        System.out.println("You win!");
                        gameEnd = true;
                    }
                    else if(dealerHand > playerHand)
                    {
                        dealerWins++;
                        System.out.println("Dealer Wins!");
                        gameEnd = true;
                    }
                    else if(playerHand > dealerHand)
                    {
                        playerWins++;
                        System.out.println("You win!");
                        gameEnd = true;
                    }
                    else if (dealerHand == playerHand)
                    {
                        System.out.println("Its a tie! No one ends!");
                        tieGames++;
                        gameEnd = true;
                    }

                }
                else if( answer == 3)
                {
                    BlackJack var = new BlackJack();
                    var.gameStats(playerWins, dealerWins, tieGames, gameNumber);
                }
                else if ( answer == 4)
                {
                    gameEnd = true;
                    end = true;
                }
                else
                {
                    System.out.println("'" + answer + "' is not a valid option");
                }
            }
            gameEnd = false;
            gameNumber++;
            playerHand = 0;
            playerCard = 0;
            dealerHand = 0;
        }
    }
    public static int newCard()
    {
        Random rand = new Random();
        int randInt = rand.nextInt(14);
        return randInt;
    }
    public static String printCard(int input)
    {
        String output = "";
        if(input == 13)
        {
            output = "Your card is a King!";
        }
        else if(input == 12)
        {
            output = "Your card is a Queen!";
        }
        else if(input == 11)
        {
            output = "Your card is a Jack!" ;
        }
        else if(input == 1)
        {
            output = "Your card is an Ace!";
        }
        else if(input == 8)
        {
            output = "Your card is an 8!";
        }
        else
        {
            output = "Your card is a " + input + "!";
        }
        return output;
    }
    public static void gameStats(int playerWinTotal, int dealerWinTotal, int tieGameTotal, int gameNumberTotal )
    {
        System.out.println("Number of Player Wins: " + playerWinTotal);
        System.out.println("Number of Dealer Wins: " + dealerWinTotal);
        System.out.println("Number of Tied Games: " + tieGameTotal);
        System.out.println("Total # of Games: " + gameNumberTotal);
        double winPercentage = ((double)playerWinTotal/ (double)gameNumberTotal) * 100;
        System.out.println("Percentage of Player Wins " + winPercentage + "\n");
    }
}