
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.awt.Color;
import java.util.ArrayList;



public class game{ 
	//creating Finch objects
	Finch redfinch = new Finch();
	Finch bluefinch = new Finch();
	Finch greenfinch = new Finch();
	Finch yellowfinch = new Finch();
	Finch onoff = new Finch();
	//creating ArrayList objects.
	ArrayList<Integer> AL_scores = new ArrayList<Integer>(); 
	ArrayList<Integer> AL_usrguess = new ArrayList<Integer>();
	int finchnumber;	
	int i =0;
	int userenter;
	int lives;

	public void standard() {
		// This method runs through the switch test to ensure that the game can continue
		//it then proceeds to display the pattern stored in the Arraylist.
		switchtest();
		display();
		userturn();
	}

	public void gamesetup() {
		// This method initialises the game by making the onoff finch flash its nose so the user knows
		// which finch is the switch, it then checks if the onoff switch is fine, adds 5 lives 
		//and stores the first generated number in the Arraylist
		onoff.setLED(Color.pink,2500);
		switchtest(); 
		lives = 5;
		AL_scores.add((int) (Math.random()*((4-1)+1))+1); 
	}

	public void addround() {
		//checks the onoffswitch and adds another number to the scores Arraylist then calls standard method
		switchtest(); 
		AL_scores.add((int) (Math.random()*((4-1)+1))+1); 
		standard();
	}

	public void userturn() {
		//allows user to enter their guessed by blocking the obstacle sensors, depending on which is tapped will
		//add 1-4 into the Arraylist AL_usrguess
		for(i=0;i<AL_scores.size();i++) {
			switchtest();
			while(true) {
				if(redfinch.isObstacle()==true) {
					AL_usrguess.add(1);
					redfinch.buzz(3000, 500);
					System.out.println("Red tapped");
					break;
				}
				else if(bluefinch.isObstacle()==true) {
					AL_usrguess.add(2);
					bluefinch.buzz(4000, 500);
					System.out.println("Blue tapped");
					break;
				}
				else if(greenfinch.isObstacle()==true){
					AL_usrguess.add(3);
					greenfinch.buzz(5000, 500);
					System.out.println("Green tapped");
					break;
				}
				else if(yellowfinch.isObstacle()==true) {
					AL_usrguess.add(4);
					yellowfinch.buzz(6000, 500);
					System.out.println("Yellow tapped");
					break;
				}
			}

			try {

				Thread.sleep(800);
			} catch (Exception e) {

			}

		}

		compare();

	}


	public void display() {
		//displays the pattern by taking a number that was generated and flashing a finch that is 
		//associated with that number
		for(i=0;i<AL_scores.size();++i) {
			finchnumber = AL_scores.get(i);
			switchtest();
			try {
				Thread.sleep(300);
			} catch (Exception e) {

			}
			switch(finchnumber) {

			case 1:
				System.out.println("red");
				redfinch.setLED(Color.red,2500); 
				break;
			case 2:
				System.out.println("blue");
				bluefinch.setLED(Color.blue,2500);
				break;
			case 3:
				System.out.println("green");
				greenfinch.setLED(Color.green,2500);
				break;
			case 4:
				System.out.println("yellow");
				yellowfinch.setLED(Color.yellow,2500);
				break;

			}
		}
	}


	public void livecheck() {
		//livecheck removes 1 from the int lives and if lives have hit 0 will end the game. 
		//If the player still has lives it will repeat the display for the player to retry.
		switchtest();
		lives = lives-1;
		if (lives == 0) {
			System.out.println("Incorrect! Game Over");
			redfinch.buzz(2000, 1100);
			bluefinch.buzz(2000, 1100);
			greenfinch.buzz(2000, 1100);
			System.out.println("Your score was " + (AL_usrguess.size()-1));
			System.exit(0);

		}
		else {
			switchtest();
			System.out.println("\n Incorrect you have " + lives + " attempts left!");
			AL_usrguess.removeAll(AL_usrguess);
			standard();
		}
	}

	public void compare() {
		//compares if the two arraylists are the same, if they are will add another colour to the game pattern 
		//and repeat.
		switchtest();
		if(AL_usrguess.equals(AL_scores)) { 
			System.out.println("Correct!    Next round:");
			AL_usrguess.removeAll(AL_usrguess);
			addround();
		}
		else {
			livecheck();

		}
	}

	public void switchtest() {
		//gets called repeatedly over the course of the game to poll if the game is still 
		//continuing or if the on off switch has been turned to off.
		//if finch is off it will flash pink, buzz and display the players score as it ends the game.
		if(onoff.isBeakUp()==true) {
			onoff.setLED(Color.pink,2500);
			System.out.println("Game Over!");
			onoff.buzz(2000, 1000);
			System.out.println("Your score was " + (AL_usrguess.size()-1));
			System.exit(0);
		}
	}
}









