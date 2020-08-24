import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Pong extends JComponentWithEvents
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	// classes
	Racket myRacketL = new Racket();
	Racket myRacketR = new Racket();
	Player myPlayer = new Player();
	static Scanner userInput = new Scanner(System.in);
	// these instance variables persists between method calls!
	public int racketYL = 250; // stand for racket Y axis Left
	public int racketYR = 250; // stand for racket Y axis Right
	private double ovalY = 250;
	private double ovalX = 500;
	//private int leftRacketHitCount;
	//private int rightRacketHitCount;
	private static int leftRacketScoreCount = 0;
	private static int rightRacketScoreCount = 0;
	private int NORTH_OR_SOUTH;
	private int countTimer = 0;
	int beginPause = 0;
	int countLBY = 0;
	//private int pauseC = 0;
	public double angle;
	public double velocity = 3;
	public double oVelocity = 3; // origional velocity
	private static int sDChoose = 0;
	private int bulletLX = 10;
	private int bulletLY;
	private int bulletRX = 10;
	private int bulletRY;
	private double dY = Math.random() * ((Math.sin(Math.atan(0.5))) * velocity);
	private double newDX = 0;
	private double dX = Math.sqrt(Math.pow(velocity, 2) - Math.pow(dY, 2));
	private boolean touchLeft = false;
	private boolean touchRight = false;
	private boolean leftMiss = false;
	private boolean rightMiss = false;
	private boolean alreadyChose = false;
	private boolean pause = false;
	boolean moveYLUp = false;
	boolean moveYLDown = false;
	boolean moveYRUp = false;
	boolean moveYRDown = false;
	boolean reStart = false;
	boolean bulletL = false;
	boolean bulletR = false;
	// private boolean singlePlayer;

	// This method is called each time a key is pressed.
	// The parameter contains the key that was pressed.
	// Note that you can use the constants UP, DOWN, LEFT, and RIGHT
	// to represent the arrow keys.

	public void start()
	// start the game
	{
		velocity = 3;
		int delayInMilliseconds = 1;// 12
		//set the delay for timerFired
		setTimerDelay(5);
	}

	public void computerPlayer() // throws InterruptedException
	// Program the computer player
	{
		if (countTimer % 5 == 0)
			//limit the speed of computer player
		{
			int currentBallLocation = (int) ovalY;
			if ((Math.abs(currentBallLocation - racketYL)) <= 5)
				//if the movement of the racket smaller than 5, move
			{
				if (racketYL < 0)
				{
					racketYL = 0;
				}
				if (racketYL > 430)
				{
					racketYL = 430;
				}
				else
				{
					racketYL = (int) ovalY;
				}
			} else if (Math.abs((currentBallLocation - racketYL)) > 5)
				//move up for 5
			{
				if ((currentBallLocation - racketYL) > 0)
				{
					if (racketYL < 0)
					{
						racketYL = 0;
					}
					if (racketYL > 430)
					{
						racketYL = 430;
					}
					else
						racketYL += 5;
				} else if ((currentBallLocation - racketYL) < 0)
					//move down for five
				{
					if (racketYL < 0)
					{
						racketYL = 0;
					}
					if (racketYL > 430)
					{
						racketYL = 430;
					}
					else
						racketYL -= 5;
				}
			}
		}
	}
	
	public void checkRestart()
	//program for restart, set all methods to 0
	{
		if(reStart)
		{
			ovalY = 250;
			ovalX = 500;
			rightRacketScoreCount = 0;
			leftRacketScoreCount = 0;
			pause = false;
			beginPause = 0;
			alreadyChose = false;
			reStart = !reStart;
		}
	}

	public void mousePressed(int x, int y)
	// mouse pressed, for select section at the beginning of the game
	{
		// may be wrong
		if ((x >= 375 && x <= 395) && (y >= 280 && y <= 300))
		{
			// Calls Coad for single player
			sDChoose = 1;
			alreadyChose = true;
		}
		if ((x >= 375 && x <= 395) && (y >= 330 && y <= 350))
		{
			// Calls Coad for two player
			sDChoose = 2;
			alreadyChose = true;
		}
	}

	public void keyReleased(char key)
	//key released, get together with key pressed, check if we hold the key down or not
	{

		if (key == 'a')
		{
			moveYLUp = false;
		} else if (key == 'q')
		{
			moveYLDown = false;
		}
		if (key == 'l')
		{
			moveYRUp = false;
		} else if (key == 'p')
		{
			moveYRDown = false;
		}

	}

	public void keyPressed(char key)
	//check that if we pressed the key or not
	{
		if(key == 'r')
		{
			reStart = true;
		}
		if (key == SPACE)
			//pause with space bar, stop all programs
		{
			pause = !pause;
		}
		if (pause)
		{
			return;
		}
		
		if(key == 'o')
		{
			bulletR = true;
		}
		
		if(key == 'w')
		{
			bulletL = true;
		}

		if (key == 'a')
		{
			moveYLUp = true;
			// if(key != 'a')
			// break;
		}

		else if (key == 'q')
		{
			moveYLDown = true;
		}
		if (racketYL < 0)
		{
			racketYL = 0;
		}
		if (racketYL > 450)
		{
			racketYL = 450;
		}
		if (key == 'l')
		{
			moveYRUp = true;
		} else if (key == 'p')
		{
			moveYRDown = true;
		}
		if (racketYR < 0)
		{
			racketYR = 0;
		}
		if (racketYR > 450)
		{
			racketYR = 450;
		}

	}

	public void checkRacketMoving()
	//check with the key released and key pressed, create the movement of the racket
	{
		if (sDChoose != 1)
			//if it is single, player can't control computer's racket
		{
			if (moveYLUp)
			{
				racketYL += 5;
			}
			if (moveYLDown)
			{
				racketYL -= 5;
			}
			if (racketYL < 0)
			{
				racketYL = 0;
			}
			if (racketYL > 430)
			{
				racketYL = 430;
			}
		}
		if (moveYRUp)
		{
			racketYR += 3;
		} else if (moveYRDown)
		{
			racketYR -= 3;
		}
		if (racketYR < 0)
		{
			racketYR = 0;
		}
		if (racketYR > 430)
		{
			racketYR = 430;
		}
	}

	public void scoreCounting()
	// score board
	{
		if (alreadyChose == true && pause == false)
		{
			if (ovalX <= 0)
			{
				ovalY = 250;
				ovalX = 500;
				if (startingPitch() == 1)
				{
					dY = (Math.random() * velocity / 1.4);
				} else
				{
					dY = -(Math.random() * velocity / 1.4);
				}
				dX = findNewXVal();
				rightRacketScoreCount++;
			}

			if (ovalX >= 1000 - 10)
			{
				
				ovalY = 250;
				ovalX = 500;
				// velocity = 2;
				if (startingPitch() == 1)
				{
					dY = (Math.random() * 0.7);
				} else
				{
					dY = (Math.random() * 0.7) * -1;
				}
				dX = -(findNewXVal());
				leftRacketScoreCount++;

			}
		}
	}

	public void timerFired()
	// control all anamiation (movement)
	{
		if(beginPause == 0)
		{
			pause = true;
			beginPause++;
		}
		if (pause)
		{
			return;
		}
		if (alreadyChose == true && pause == false)
		{
			checkRacketMoving();
			scoreCounting();
			checkRestart();
			if(bulletL)
			{
				System.out.println("top bulletL"+bulletL);
				if(countLBY == 0)
				{
					bulletLY = racketYL;
					countLBY++;
				}
				if(countTimer % 5 == 0)
				{
					bulletLX += 10;
				}
				if(racketTouchRight())
				{
					bulletL = false;
				}
				if(rightRacketMiss())
				{
					bulletL = false;
				}
				System.out.println("bottom bulletL"+bulletL);
			}

			if (sDChoose == 1)
			{
				computerPlayer();
			}
			if (racketTouchLeft() == true)
			{
				changeVelocityLeft();
				dY = -Math.cos(Math.toRadians(angleForLeft())) * (velocity);
				dX = findNewXVal();
				// dX = dX;
				// System.out.println(Math.sqrt(Math.pow(dY,2)+
				// Math.pow(dX,2)));
			}
			if (racketTouchRight() == true)
			{
				changeVelocityRight();
				dY = -Math.cos(Math.toRadians(angleForRight())) * (velocity);
				dX = findNewXVal();
				dX = -dX;
				// System.out.println(Math.sqrt(Math.pow(dY,2)+
				// Math.pow(dX,2)));
			}
			if (ovalX <= 0)
			{
				velocity = oVelocity;
				ovalY = 250;
				ovalX = 500;
				if (startingPitch() == 1)
				{
					dY = Math.random() * ((Math.sin(Math.atan(0.5))) * velocity);

				} else
				{
					dY = -(Math.random() * ((Math.sin(Math.atan(0.5))) * velocity));

				}
				dX = findNewXVal();
				velocity = oVelocity;
			}

			if (ovalX >= 1000) // - 10)
			{
				velocity = oVelocity;
				ovalY = 250;
				ovalX = 500;
				if (startingPitch() == 1)
				{
					dY = Math.random() * ((Math.sin(Math.atan(0.5))) * velocity);

				} else
				{
					dY = -(Math.random() * ((Math.sin(Math.atan(0.5))) * velocity));

				}
				dX = -(findNewXVal());
				velocity = 3;
			}

			if (ovalY <= 0 || ovalY >= 500 - 10)
			{
				velocity = oVelocity;
				dY = -dY;
				// System.out.println(Math.sqrt(Math.pow(dY,2)+
				// Math.pow(dX,2)));
			}
			ovalX += dX;
			ovalY += dY;
			countTimer++;
		}
		//pauseC++;
		
	}

	public void paint(Graphics2D page)
	// print the shape of racket and ball, include the mid line of the game board, and score, player1/2 display
	{
		if (alreadyChose == false)
		{
			page.setFont(new Font("Times New Roman", Font.BOLD, 50));
			page.drawString("Pong", (int) (1000 / 2) - 50, (int) 100);
			page.setFont(new Font("Arial", Font.BOLD, 16));
			page.drawString("Version 1.1", (int) (1000 / 2) - 40, (int) (30));
			page.setFont(new Font("Arial", Font.BOLD, 16));
			page.drawString("Contron for Player 1: 'q' to go up and 'a' to go down	Contron for Player 2: 'p' to go up and 'l' to go down", (int) 125, (int) 200);
			//page.drawString("Contron for Player 2: 'p' to go up and 'l' to go down", (int) 300, (int) 250);
			page.drawString("Press space bar to start and pause the game	R to restart the game", (int) 225, (int) 250);
			page.drawString("Created By Tom Yang and Stefan Esquivel", (int) 10, (int) (490));
			page.drawString("Please Select Game Mode", (int) (1000 / 2) - 90, (int) (150));
			page.setFont(new Font("Arial", Font.BOLD, 30));
			page.drawString("Single Player", (int) (1000 / 2) - 90, (int) (300));
			page.drawString("Double Player", (int) (1000 / 2) - 80, (int) (350));
			page.fillRect(375, 280, 20, 20);
			page.fillRect(375, 330, 20, 20);
			//page.fillOval((int) ovalX, (int) ovalY, 10, 10);
		}
		if (alreadyChose == true)
		{
			//if player choose single/double player mode already, jump out the choose page and start the game
			if (leftRacketScoreCount < 11 && rightRacketScoreCount < 11)
			{
				if(bulletL)
				{
					page.fillOval((int)bulletLX, (int)bulletLY, 15, 15);
				}
				if(bulletR)
				{
					page.fillOval((int)bulletRX, (int)bulletRY, 15, 15);
				}
				page.fillOval((int) ovalX, (int) ovalY, 10, 10);
				page.fillRect(0, racketYL, myRacketL.getXLengthRacket(), myRacketL.getYLengthRacket());
				// Left Racket
				page.fillRect(990, racketYR, myRacketR.getXLengthRacket(), myRacketR.getYLengthRacket());
				// Right Racket
				page.fillRect(1000 / 2, 0, 1, 500);
				// iDivide line in middle
				page.fillOval((int) ovalX, (int) ovalY, 10, 10);

				page.drawString("player1", (1000 / 2) - 100, 10);
				page.drawString("player2", (1000 / 2) + 100, 10);
				page.drawString("Player1 Score: " + leftRacketScoreCount, (1000 / 2) - 300, 10);
				page.drawString("Player2 Score: " + rightRacketScoreCount, (1000 / 2) + 300, 10);
			}
			if (leftRacketScoreCount >= 11)// && rightRacketScoreCount > 11)
			{
				page.setFont(new Font("Times New Roman", Font.ITALIC, 50));
				page.drawString("Player1 Won!!", 350, 250);
			} else if (rightRacketScoreCount >= 11)// && rightRacketScoreCount >
													// 11)
			{
				page.setFont(new Font("Times New Roman", Font.ITALIC, 50));
				page.drawString("Player2 Won!!", 350, 250);
			}
		}
	}

	public static void main(String[] args)
	{
		launch(1000, 500); // launch the GUI
	}

	public void changeVelocityRight()
	// if the racket moved when hit the ball, ball go faster
	{
		velocity = oVelocity;
		if (moveYRDown || moveYRUp == true)
		{
			velocity = velocity + 2;
		}
	}

	public void changeVelocityLeft()
	{
		// if the racket moved when hit the ball, ball go faster
		velocity = oVelocity;
		if (moveYLDown || moveYLUp == true)
		{
			velocity = velocity + 2;
		}

	}

	public double angleForRight()
	{
		//give different angle when the ball hit different places of the racket
		angle = 1.7142857143 * ((ovalY + 3.5) - (double) racketYR) + 30;
		// System.out.println(angle + " " + ovalY + " "+ racketYR);
		return angle;
	}

	public double angleForLeft()
	{
		//give different angle when the ball hit different places of the racket
		angle = 1.7142857143 * ((ovalY + 3.5) - (double) racketYL) + 30;
		// System.out.println(angle+ " " + ovalY + " "+ racketYL);
		return angle;
	}

	public double findNewXVal()
	{
		//get new x value of the ball, ball plysics
		newDX = Math.sqrt(Math.pow(velocity, 2) - Math.pow(dY, 2));
		return newDX;
	}

	public int startingPitch()
	{
		//at every starting point of the game, determine the ball go up or down
		NORTH_OR_SOUTH = (int) ((Math.random() * 2) + 1);
		return NORTH_OR_SOUTH;
	}

	public boolean racketTouchRight()
	{
		//if the ball touch the right racket
		touchRight = false;
		if ((racketYR <= ovalY) && (racketYR + myRacketR.getYLengthRacket() >= ovalY) && dX >= 0 && ovalX >= 1000 - 20)
		{
			touchRight = true;
		}
		return touchRight;
	}

	public boolean racketTouchLeft()
	{
		//if the ball touch the left racket
		touchLeft = false;
		if ((racketYL - 7 <= ovalY) && (racketYL + myRacketL.getYLengthRacket() >= ovalY) && dX <= 0 && ovalX <= 10)
		{
			touchLeft = true;
		}
		return touchLeft;
	}

	public boolean leftRacketMiss()
	{
		// if the left racket missed the ball
		leftMiss = false;
		if ((racketYL - 7 <= ovalY) && (racketYL + myRacketL.getYLengthRacket() >= ovalY) && dX <= 0
				&& touchLeft == false)
		{
			leftMiss = true;
		}
		return leftMiss;
	}

	public boolean rightRacketMiss()
	{
		// if the right racket missed the ball
		rightMiss = false;
		if ((racketYR - 7 <= ovalY) && (racketYR + myRacketR.getYLengthRacket() >= ovalY) && dX >= 0
				&& touchRight == false)
		{
			rightMiss = true;
		}
		return rightMiss;
	}

}