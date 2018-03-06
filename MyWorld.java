import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import javax.swing.JOptionPane;

/**
         * Name: (huize cui)
         * Course: CS40S
         * Teacher: Mr. Hardman
         * Lab #1, Two-Dimensional Arrays
         * Date Last Modified: (Today’s Date)
         *
         */
public class MyWorld extends World
{
    private boolean playerOneTurn = true;
    private boolean messageShown = false;
    
    private String playerOneName;
    private String playerTwoName;
    
    private String [][] board = new String[3][3];
    

    /**
     * MyWorld is the constructor for objects of class MyWorld
     * 
     * @param There are no parameters
     * @return an object of type MyWorld
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 600, 1); 
         
        drawLines();
        
        for( int r = 0; r < board.length; r++ )
        {
            for( int c = 0; c < board[r].length; c++)
            {
                board[r][c] = ""; 
                
            }
        }
        
        
    } 
    
    private void drawLines()
    {
        getBackground().setColor( Color.BLACK );
        
        for( int i = 200; i < getWidth(); i += 200 )
        {
            getBackground().drawLine(i, 0, i, getHeight() );
            getBackground().drawLine(0, i, getWidth(), i);
        }
        
        Greenfoot.start();
    }
    
    /**
     * started asks and stores Player One's and Player Two's names
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void started()
    {
        playerOneName = JOptionPane.showInputDialog( null, "Please enter your name:", "PlayerOne Name", JOptionPane.QUESTION_MESSAGE );
        playerTwoName = JOptionPane.showInputDialog( null, "Please enter your name:", "PlayerTwo Name", JOptionPane.QUESTION_MESSAGE );
    }
    
    /**
     * act is the code that is run on every iteration of the act cycle
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void act()
    {
        displayBoard();
        Greenfoot.delay(10);
        
        if( checkPlayerOneWin() == true )
        {
            JOptionPane.showMessageDialog( null, "Congratulations Player One!", "playerOne Win", JOptionPane.PLAIN_MESSAGE );
            
            Greenfoot.stop();
        }
        
        if( checkPlayerTwoWin() == true )
        {
            JOptionPane.showMessageDialog( null, "Congratulations Player Two!", "plauerTwo Win",JOptionPane.PLAIN_MESSAGE );
            
            Greenfoot.stop();
        }
            
        if( checkBoardFilled() == true )
        {
            JOptionPane.showMessageDialog( null,  "It is a draw!", "Wrong step", JOptionPane.PLAIN_MESSAGE );
            
            Greenfoot.stop();
        }
        
        if( messageShown == false )
        {
            showTurn();
            
            messageShown = true;
        }
        
        checkMouseClick();
    }
    
    /**
     * showTurn shows messages for Player One's and Player Two's turns
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void showTurn()
    {
        if( playerOneTurn == true )
        {
            if( messageShown == false )
            {
                JOptionPane.showMessageDialog( null, playerOneName + "Player One, it is your turn.", "for playerOne", JOptionPane.PLAIN_MESSAGE );
               
            }
        }
        else
        {
            if( messageShown == false )
            {
                JOptionPane.showMessageDialog( null, playerTwoName + "Player Two, it is your turn.", "for playerTwo", JOptionPane.PLAIN_MESSAGE );
                
            }
        }
    }
    
    /**
     * checkmouseClick checks where Player One and Two want to place their X's and O's
     * and tells them to select a different spot if the spot is already filled
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void checkMouseClick()
    {
        MouseInfo userClick = Greenfoot.getMouseInfo();
        
      
        int columnNum;
        int rowNum;
        
        if( Greenfoot.mouseClicked(this) )
        {
            columnNum = userClick.getX() / ( getWidth() / 3 );
            rowNum = userClick.getY() / ( getHeight() / 3 );
            if( board[rowNum][columnNum] == "" )
            {
                if( playerOneTurn == true )
                {
                    board[rowNum][columnNum] = "X";
                    
                    playerOneTurn = false;
                    messageShown = false;
                }
                else
                {
                    board[rowNum][columnNum] = "O";
                    
                    playerOneTurn = true;
                    messageShown = false;
                }
            }
            else
            {
                JOptionPane.showMessageDialog( null," that the spot is not empty and the use should select a different spot", "tic tac toe", JOptionPane.PLAIN_MESSAGE );
            }
        }
        
    }
    
    private void displayBoard()
    {
        GreenfootImage toDisplay;
        
        for( int r = 0; r < board.length; r ++ )
        {
            for( int c = 0; c < board[r].length; c ++ )
            {
                toDisplay = new GreenfootImage( board[r][c], 100, Color.BLACK, new Color(0,0,0,0) );  
                getBackground().drawImage( toDisplay, c * getWidth()/3 + (getWidth()/3 - toDisplay.getWidth() )/2 , r * getHeight()/3 + (getHeight()/3 - toDisplay.getHeight() )/2 );
            }
        }
        
    }
    
    private boolean checkPlayerOneWin()
    {
        boolean playerOneWin = false;
        
        if(board[0][0] == "X" && board[0][1] == "X" && board[0][2] == "X" )
        {
            playerOneWin = true;
        }
        else if(board[1][0] == "X" && board[1][1] == "X" && board[1][2] == "X"  )
        {
            playerOneWin = true;
        }
        else if(board[2][0] == "X" && board[2][1] == "X" && board[2][2] == "X"  )
        {
            playerOneWin = true;
        }
        else if(board[0][0] == "X" && board[1][0] == "X" && board[2][0] == "X"  )
        {
            playerOneWin = true;
        }
        else if(board[0][1] == "X" && board[0][1] == "X" && board[2][1] == "X"  )
        {
            playerOneWin = true;
        }
        else if(board[0][0] == "X" && board[1][1] == "X" && board[2][2] == "X"  )
        {
            playerOneWin = true;   
        }
        else if(board[0][0] == "X" && board[1][1] == "X" && board[2][2] == "X"  )
        {
            playerOneWin = true;
        }
        else if(board[0][2] == "X" && board[1][1] == "X" && board[2][1] == "X"  )
        {
            playerOneWin = true;
        }
        
        return playerOneWin;
    }
    
    private boolean checkPlayerTwoWin()
    {
        boolean playerTwoWin = false;
        
        if(board[0][0] == "O" && board[0][1] == "O" && board[0][2] == "O" )
        {
            playerTwoWin = true;
        }
        else if(board[1][0] == "O" && board[1][1] == "O" && board[1][2] == "O"  )
        {
            playerTwoWin = true;
        }
        else if(board[2][0] == "O" && board[2][1] == "O" && board[2][2] == "O"  )
        {
            playerTwoWin = true;
        }
        else if(board[0][0] == "O" && board[1][0] == "O" && board[2][0] == "O"  )
        {
            playerTwoWin = true;
        }
        else if(board[0][1] == "O" && board[0][1] == "O" && board[2][1] == "O"  )
        {
            playerTwoWin = true;
        }
        else if(board[0][0] == "O" && board[1][1] == "O" && board[2][2] == "O"  )
        {
            playerTwoWin = true;   
        }
        else if(board[0][0] == "O" && board[1][1] == "O" && board[2][2] == "O"  )
        {
            playerTwoWin = true;
        }
        else if(board[0][2] == "O" && board[1][1] == "O" && board[2][1] == "O"  )
        {
            playerTwoWin = true;
        }
        return playerTwoWin;
    }
    
    private boolean checkBoardFilled()
    {
        boolean boardisfilled = true;
        for( int r = 0; boardisfilled == true && r < board.length; r ++ )
        {
            for( int c = 0; boardisfilled == true && c < board.length; c ++ )
            {
                if(board[r][c] == "" )
                {
                    boardisfilled = false;
                }
            }
        }
        return boardisfilled;
    }

   

}
