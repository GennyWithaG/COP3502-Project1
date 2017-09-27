/*
Class file for Project1 for COP3502. Program to run a text-based version of BlackJack using user prompts.
By Geneva Anderson on 9-25-2017. Edited on 9-27-2017.
*/
import java.util.*;
import java.util.Scanner;
public class BlackJack
{
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);      //Creates Scanner

        boolean end = false;      //Program variables
        boolean gameEnd = false;
        String message= "1. Get another card\n" + "2. Hold hand\n" + "3. Print statistics\n" + "4. Exit\n\n" + "Choose an option: ";
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

        while(!end)       //Loop that runs the overall game
        {
            System.out.println("START GAME #" + gameNumber + "\n");       //Prints opening statements to screen
            playerCard = newCard();
            cardMessage = printCard(playerCard);
            System.out.println(cardMessage);

            if(cardMessage.equals("Your card is a King!")||cardMessage.equals("Your card is a Queen!")||cardMessage.equals("Your card is a Jack!"))      //Resets card value to 10 if face card
            {
                playerCard = 10;
            }
            playerHand = playerCard;
            System.out.println("Your hand is: " + playerHand + "\n");

            while(!gameEnd)      //Loop that runs the current hand
            {
                System.out.print(message);      //Prints option menue
                try      //Checks for and catches the possible InputMismatchException
                {
                    answer = in.nextInt();
                    System.out.println();
                }
                catch(InputMismatchException ex)
                {
                System.err.println("Invalid Input! \nPlease use an interger between 1 and 4.");
                in.nextLine();
                System.out.println();
                }
                //Checks player answer
                if( answer == 1)      //If player wanted new card
                {
                    playerCard = newCard();
                    cardMessage = printCard(playerCard);
                    System.out.println(cardMessage);
                    if(cardMessage.equals("Your card is a King!")||cardMessage.equals("Your card is a Queen!")||cardMessage.equals("Your card is a Jack!"))      //Resets card value to 10 if face card
                    {
                        playerCard = 10;
                    }
                    playerHand += playerCard;
                    System.out.println("Your hand is: " + playerHand + "\n");
                    if(playerHand > 21)      //Prints who won if over or on 21
                    {
                        dealerWins++;
                        gameEnd = true;
                        System.out.println("Dealer wins!\n");
                    }
                    else if(playerHand == 21)
                    {
                        playerWins++;
                        gameEnd = true;
                        System.out.println("You win!\n");
                    }
                }
                else if( answer == 2)      //If player held hand
                {
                    Random rand = new Random();      //Gives dealer random hand
                    dealerHand = rand.nextInt(11) + 15;
                    System.out.println("Dealer's Hand: " + dealerHand);
                    System.out.println("Your Hand: " + playerHand + "\n");

                    if(dealerHand > 21)      //Checks and prints who won
                    {
                        playerWins++;
                        System.out.println("You win!\n");
                        gameEnd = true;
                    }
                    else if(dealerHand > playerHand)
                    {
                        dealerWins++;
                        System.out.println("Dealer Wins\n!");
                        gameEnd = true;
                    }
                    else if(playerHand > dealerHand)
                    {
                        playerWins++;
                        System.out.println("You win!\n");
                        gameEnd = true;
                    }
                    else if (dealerHand == playerHand)
                    {
                        System.out.println("Its a tie! No one wins!\n");
                        tieGames++;
                        gameEnd = true;
                    }

                }
                else if( answer == 3)      //If player wanted game stats
                {
                    BlackJack var = new BlackJack();
                    var.gameStats(playerWins, dealerWins, tieGames, gameNumber);
                }
                else if ( answer == 4)      //If player ends game
                {
                    gameEnd = true;
                    end = true;
                }
                else      //If player inputed int outside of param borders
                {
                    System.out.println("'" + answer + "' is not a valid option\n");
                }
            }
            gameEnd = false;      //Resets variables for next hand
            gameNumber++;
            playerHand = 0;
            playerCard = 0;
            dealerHand = 0;
        }
    }
    public static int newCard()      //Returns a random card
    {
        Random rand = new Random();
        int randInt = rand.nextInt(13)+1;
        return randInt;
    }
    public static String printCard(int input)      //Prints card info based on param entered
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
    public static void gameStats(int playerWinTotal, int dealerWinTotal, int tieGameTotal, int gameNumberTotal )      //Prints  game stats with param entered
    {
        System.out.println("Number of Player Wins: " + playerWinTotal);
        System.out.println("Number of Dealer Wins: " + dealerWinTotal);
        System.out.println("Number of Tied Games: " + tieGameTotal);
        System.out.println("Total # of Games: " + (gameNumberTotal-1));
        double winPercentage = ((double)playerWinTotal/ (double)(gameNumberTotal-1)) * 100;
        winPercentage = Math.round(winPercentage * 10);
        winPercentage = winPercentage / 10;
        System.out.println("Percentage of Player Wins " + winPercentage + "%\n");
    }
}