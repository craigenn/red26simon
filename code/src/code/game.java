package code;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.util.Random;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;


public class game {


	private static Finch redfinch = null;
	private static Finch bluefinch = null;
	private static Finch greenfinch = null;
	private static Finch yellowfinch = null;
	private static Finch onoff = null;

	ArrayList<Integer> scores = new ArrayList<Integer>();
	ArrayList<Integer> usrguess = new ArrayList<Integer>();
	int finchnumber;
	int i =0;
	int userenter;
	char start = 'b';
	Scanner keyboard = new Scanner(System.in);


public game() {
	
}
	public void standard() {

display();
userturn();


	}


	public void first() {
		//for(i=0;i<=10;++i) {
			double j = (Math.random()*((4-1)+1))+1;
			scores.add((int) j);

		//}


	}

	public void addround() {
		scores.add((int) (Math.random()*((4-1)+1))+1);
		standard();
	}


	public void start() {
		first();
		//addround();
		//display();
		
		// addround();
		//System.out.println(scores.toString());




	}
	public void userturn() {
		for(i=0;i<scores.size();i++)
			userenter = keyboard.nextInt();
			/*if(redfinch.isTapped()) {;
			usrguess.add(1);

			}
			else if(bluefinch.isTapped()) {
				usrguess.add(2);
			}
			else if(greenfinch.isTapped()){
				usrguess.add(3);
			}
			else if(yellowfinch.isTapped()) {
				usrguess.add(4);
			}*/
		if(userenter == 1) {
			usrguess.add(1);
			}
		else if(userenter == 2) {
			usrguess.add(2);
		}
		else if(userenter == 3){
			usrguess.add(3);
		}
		else if(userenter == 4) {
			usrguess.add(4);
		}
		
		compare();




	}


	public void display() {
		// this is run through array and flash a finch nose LED in accordance with which number is in that element of the array
		for(i=0;i<scores.size();++i) {
			finchnumber = scores.get(i);

			switch(finchnumber) {

			case 1:
				//redfinch.setLED(Color.red,2500);
				System.out.println("red");
				break;
			case 2:
				//bluefinch.setLED(Color.blue,2500);
				System.out.println("blue");
				break;
			case 3:
				//greenfinch.setLED(Color.green,2500);
				System.out.println("green");
				break;
			case 4:
				System.out.println("yellow");
				break;
				//	yellowfinch.setLED(Color.yellow,2500);

			}
		}
	}
	public void compare() {
		if(usrguess.equals(scores)) {
			System.out.println("correct!");
			addround();
		}
		else {
			System.out.println("Incorrect");
			
		}
	}
}
