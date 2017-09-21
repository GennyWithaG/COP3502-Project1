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
        int playerHand = 0;
        int dealerHand = 0;
        int gameNumber = 1;
        int playerCard = 0;
        int playerWins = 0;
        int dealerWins = 0;
        int tieGames = 0;
        int answer = 0;
        Random rand = new Random();
        int randInt = rand.nextInt(14);


        double winPercentage = 0.0;
        while(!end)
        {
            System.out.println("START GAME #" + gameNumber);
            playerCard = rand.nextInt(13) + 1;
            if( playerCard ==13 || playerCard == 12 || playerCard == 11)
            {
                playerCard = 10;
            }

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
                    playerCard = rand.nextInt(13) + 1;
                    if(playerCard == 13)
                    {
                        System.out.println("Your card is a King!");
                        playerCard = 10;
                    }
                    else if(playerCard == 12)
                    {
                        System.out.println("Your card is a Queen!");
                        playerCard = 10;
                    }
                    else if(playerCard == 11)
                    {
                        System.out.println("Your card is a Jack!");
                        playerCard = 10;
                    }
                    else if(playerCard == 1)
                    {
                        System.out.println("Your card is an Ace!");
                    }
                    else
                    {
                        System.out.println("Your card is a " + playerCard + "!");
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
                    dealerHand = rand.nextInt(11) + 15;
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
                    System.out.println("Number of Player Wins: " + playerWins);
                    System.out.println("Number of Dealer Wins: " + dealerWins);
                    System.out.println("Number of Tied Games: " + tieGames);
                    System.out.println("Total # of Games: " + gameNumber);
                    winPercentage = ((double)playerWins/ (double)gameNumber) * 100;
                    System.out.println("Percentage of Player Wins " + winPercentage);
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
}