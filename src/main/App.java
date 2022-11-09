package main;

import classes.*;
import java.util.Scanner;

public class App {

	// global variables
	static int listSize = 256; // list size for quick adjustment
	static String indexOf = "index (0 - " + (listSize - 1) + ") of"; // prints allowed index
	static PolyNodeLL[] polyNodeListArray = new PolyNodeLL[listSize]; // polynomial node linked list array
	static Scanner stdin = new Scanner(System.in); // for user input

	public static void main(String[] args) {

		// local variables
		String userInput = ""; // for catching user input
		char userChoice = '0'; // for user choice

		while (userChoice != 'q') { // runs the program as long as user input isn't q

			// displays menu
			System.out.println("Please enter what you want:");
			System.out.println("i: input");
			System.out.println("p: print");
			System.out.println("a: add");
			System.out.println("s: subtract");
			System.out.println("m: multiply");
			System.out.println("e: evaluate");
			System.out.println("q: quit");

			// gets first lowercase letter of user input
			userInput = stdin.nextLine();
			userInput.toLowerCase();
			userChoice = userInput.charAt(0);

			// switch table running respective functions
			switch (userChoice) {
			case 'i':
				inputPolynomial();
				break;
			case 'a':
				addPolynomials();
				break;
			case 's':
				subPolynomials();
				break;
			case 'm':
				multiplyPolynomials();
				break;
			case 'e':
				evaluatePolynomial();
				break;
			case 'p':
				printPolynomial();
				break;
			case 'q':
				System.out.print("Program ending...");
				userChoice = 'q';
				break;
			default:
				System.out.println("ERROR: Invalid choice");
			}
			// waits 2 seconds
			try {
				Thread.sleep(2000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			System.out.println("");
		}
		stdin.close();	//closes scanner
	}

	private static Boolean inRange(int index) {
		if (index >= 0 && index < listSize) {return true;}	//return true if in range
		return false;										//return false
	}
	private static Boolean nullIndex (int index) {
		if (polyNodeListArray[index] == null) {return true;}	//return true if index is null
		return false;											//return false
	}

	//print polynomial
	private static void printPolynomial() {
		//get index from user
		System.out.print("Select " + indexOf + " polynomial to print: ");
		int printIndex = stdin.nextInt();
		stdin.nextLine();
		//error checking
		if (!inRange(printIndex)) { // displays error if out of range
			System.out.println("ERROR: Given index is out of range");
			return;
		}
		if (nullIndex(printIndex)) { // displays error if index is null
			System.out.print("ERROR: Given index is null");
			return;
		}
		//prints poly list
		polyNodeListArray[printIndex].printPolyNodeLL();
		System.out.println();
		return;
	}

	private static void inputPolynomial() {
		//gets user indexes and number of terms
		System.out.println("Input Polynomial:\n" + "Enter " + indexOf + " polynomial function & number of terms: ");
		int insertIndex = stdin.nextInt();
		int terms = stdin.nextInt();
		//error checking
		if (!inRange(insertIndex)) {
			System.out.println("ERROR: Given index is out of range");
			return;
		}
		//creates a new list for user polynomial
		PolyNodeLL newPolyList = new PolyNodeLL();
		//for loop for inserting terms
		for (int i = 1; i <= terms; i++) {
			//gets user input
			System.out.println("Enter coefficient & exponent for term " + i + ":");
			double userCoef = stdin.nextDouble();
			int userExpo = stdin.nextInt();
			stdin.nextLine();
			//creates polyterm with input and adds it to new list
			PolyTerm newPolyTerm = new PolyTerm(userCoef, userExpo);
			newPolyList.addValueInOrder(newPolyTerm);
		}
		//stores poly list in given index
		polyNodeListArray[insertIndex] = newPolyList;
		//prints final result
		System.out.print("f(X) = ");
		polyNodeListArray[insertIndex].printPolyNodeLL();
		System.out.println();
		return;
	}

	private static void addPolynomials() {
		//gets user input and error checks
		System.out.print("Add Polynomials:\n" + "Enter " + indexOf + " addend f(x): ");
		int addIndexX = stdin.nextInt();
		if (!inRange(addIndexX)) {
			System.out.println("ERROR: Given index is out of range");
			return;
		}
		if (nullIndex(addIndexX)) {
			System.out.println("ERROR: Given index is null");
			return;
		}
		System.out.print("Enter " + indexOf + " addend g(x): ");
		int addIndexY = stdin.nextInt();
		if (!inRange(addIndexY)) {
			System.out.println("ERROR: Given index is out of range");
			return;
		}
		if (nullIndex(addIndexY)) {
			System.out.println("ERROR: Given index is null");
			return;
		}
		System.out.print("Enter " + indexOf + " sum h(x): ");
		int sumIndexZ = stdin.nextInt();
		if (!inRange(sumIndexZ)) {
			System.out.println("ERROR: Given index is out of range");
			return;
		}
		if (sumIndexZ == addIndexX || sumIndexZ == addIndexY) {
			System.out.println("ERROR Given indexes clash");
			return;
		}
		stdin.nextLine();
		//create new list with given indexes
		polyNodeListArray[sumIndexZ] = new PolyNodeLL();
		polyNodeListArray[sumIndexZ].addPolyLists(polyNodeListArray[addIndexX], polyNodeListArray[addIndexY]);
		//prints final result
		System.out.print("h(x) = f(x) + g(x) = ");
		polyNodeListArray[sumIndexZ].printPolyNodeLL();
		System.out.println();
		return;
	}

	private static void subPolynomials() {
		//gets user input and error checks
		System.out.print("Subtract Polynomials:\n" + "Enter " + indexOf + " minuend f(x): ");
		int minIndexX = stdin.nextInt();
		if (!inRange(minIndexX)) {
			System.out.print("ERROR: Given index is out of range");
			return;
		}
		if (nullIndex(minIndexX)) {
			System.out.println("ERROR: Given index is null");
			return;
		}
		System.out.print("Enter " + indexOf + " subtrahend g(x): ");
		int subIndexY = stdin.nextInt();
		if (!inRange(subIndexY)) {
			System.out.println("ERROR Given index is out of range");
			return;
		}
		if (nullIndex(subIndexY)) {
			System.out.println("ERROR: Given index is null");
			return;
		}
		System.out.print("Enter " + indexOf + " the difference h(x): ");
		int difIndexZ = stdin.nextInt();
		if (!inRange(difIndexZ)) {
			System.out.println("ERROR: Given index is out of range");
			return;
		}
		if (difIndexZ == minIndexX || difIndexZ == subIndexY) {
			System.out.println("ERROR: Given indexes clash");
			return;
		}
		stdin.nextLine();
		//creates new list with given indexes
		polyNodeListArray[difIndexZ] = new PolyNodeLL();
		polyNodeListArray[difIndexZ].subtractPolyLists(polyNodeListArray[minIndexX], polyNodeListArray[subIndexY]);
		//prints final results
		System.out.println("h(x) = f(x) - g(x) = ");
		polyNodeListArray[difIndexZ].printPolyNodeLL();
		System.out.print("");
		return;
	}

	private static void multiplyPolynomials() {
		//same thing as add and sub
		System.out.print("Multiply Polynomials:\n" + "Enter index of multiplier f(x): ");
		int mulIndexX = stdin.nextInt();
		if (!inRange(mulIndexX)) {
			System.out.println("Given index is out of range");
			return;
		}
		if (nullIndex(mulIndexX)) {
			System.out.println("ERROR: Given index is null");
			return;
		}
		System.out.print("Enter index of multiplicand g(x): ");
		int mulIndexY = stdin.nextInt();
		if (!inRange(mulIndexY)) {
			System.out.println("ERROR: Given index is out of range");
			return;
		}
		if (nullIndex(mulIndexY)) {
			System.out.println("ERROR: Given index is null");
			return;
		}
		System.out.print("Enter index of the product h(x): ");
		int proIndexZ = stdin.nextInt();
		if (!inRange(proIndexZ)) {
			System.out.println("Given index is out of range");
			return;
		}
		if (proIndexZ == mulIndexX || proIndexZ == mulIndexY) {
			System.out.println("ERROR: given indexes clash");
			return;
		}
		stdin.nextLine();
		polyNodeListArray[proIndexZ] = new PolyNodeLL();
		polyNodeListArray[proIndexZ].multiplyPolyLists(polyNodeListArray[mulIndexX], polyNodeListArray[mulIndexY]);
		System.out.print("h(x) = f(x) * g(x) = ");
		polyNodeListArray[proIndexZ].printPolyNodeLL();
		System.out.println();
		return;
	}

	private static void evaluatePolynomial() {
		//gets user index and error checks
		System.out.print("Evaluate Polynomial:\n" + "Enter " + indexOf + " polynomial f(x): ");
		int evalIndex = stdin.nextInt();
		if (!inRange(evalIndex)) {
			System.out.println("Given index is out of range");
			return;
		}
		if (nullIndex(evalIndex)) {
			System.out.println("ERROR: Given index is null");
			return;
		}
		System.out.print("Enter value of x: ");
		double valueOfX = stdin.nextDouble();
		stdin.nextLine();
		//gets result
		double evaluatedNum = 0;
		evaluatedNum = polyNodeListArray[evalIndex].evaluatePolyList(valueOfX);
		//prints final result
		System.out.println("f(" + evalIndex + ") = " + evaluatedNum);
	}
}