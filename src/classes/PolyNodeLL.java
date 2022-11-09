package classes;

import java.lang.Math;

public class PolyNodeLL extends LinkedList {

	//methods
	//print poly node list
	public void printPolyNodeLL() {
		Node nodeListIter = this.getHead();	//creates an iterator node
		while (nodeListIter != null) { 		//iterates through the list
			if (((PolyTerm) nodeListIter.getData()).getCoef() < 0) {	//prints parentheses around polyterm if negative
				System.out.print("(");
				((PolyTerm) nodeListIter.getData()).printNode();
				System.out.print(")");
			}
			else {((PolyTerm) nodeListIter.getData()).printNode();}		//else prints polyterm
			if (nodeListIter.getNext() != null) {		//adds + if necessary
				System.out.print(" + ");
				if (((PolyTerm) nodeListIter.getData()).getCoef() < 0) {
					System.out.print("");
				}

			}
			nodeListIter = nodeListIter.getNext(); 	//move onto next node
		}
	}

	//input poly in order additive
	public void addValueInOrder(PolyTerm newPolyTerm) {
		if (newPolyTerm.getCoef() == 0) {return;}	//don't add if coef is 0
		Node newPolyNode = new Node(newPolyTerm);	//creates new node with given polyterm
		addPolyNodeInOrder(newPolyNode); 			//calls add node in order function
	}
	//input new node in order
	public void addPolyNodeInOrder(Node newPolyNode) {
		int newExpo = ((PolyTerm) newPolyNode.getData()).getExpo(); //var for polyterm's expo
		if (this.getHead() == null || 					//if head is null or new expo is greater than head expo
				newExpo > ((PolyTerm) this.getHead().getData()).getExpo()) {
			newPolyNode.setNext(this.getHead());		//set new nodes next node = head node
			setHead(newPolyNode); 						//make new node the new head node
			return;										//end function
		}
		Node currNode = this.getHead();			//start current node at head
		Node nextNode = currNode.getNext();		//next = second node (could be null)
		while (currNode != null) {				//iterate through the nodes
			int currExpo = ((PolyTerm) currNode.getData()).getExpo();	//var for current expo
			if (newExpo == currExpo) {											//if the exponents match
				double newCoef = ((PolyTerm) currNode.getData()).getCoef()		//creates new coef = x coef
						+ ((PolyTerm) newPolyNode.getData()).getCoef();			//new coef += y coef
				PolyTerm newPolyTerm = new PolyTerm(newCoef, currExpo);			//new polyterm with new coef
				currNode.setData(newPolyTerm);									//update polyterm in iter node
				return;															//end function
			}
			if (currNode.getNext() == null) {		//if you reach the end
				currNode.setNext(newPolyNode);		//add new node to the end
				return;
			}
			int nextExpo = ((PolyTerm) nextNode.getData()).getExpo();	//var for next expo
			if (newExpo > nextExpo) {					//if node goes between current and next
				newPolyNode.setNext(nextNode);			//set newNodes next to nextNode
				currNode.setNext(newPolyNode);			//set current node's next to newNode
				return;									//end function
			}
			currNode = currNode.getNext();	//move onto next set of nodes
			nextNode = nextNode.getNext();
		}
	}

	//adds two polynomial functions
	public void addPolyLists(PolyNodeLL polyLLX, PolyNodeLL polyLLY) {
		Node iterNodeX = polyLLX.getHead();		//create iterator nodes for list X
		Node iterNodeY = polyLLY.getHead();		//create iterator node for list Y
		//Node iterNodeZ = this.getHead();		//future iterator for parallel 
		PolyTerm polyX = new PolyTerm();		//vars for keeping track of values of iterator nodes
		PolyTerm polyY = new PolyTerm();
		while (iterNodeX != null && iterNodeY != null) {	//while iterNodes are not null, top-down cruncher
			polyX = (PolyTerm) iterNodeX.getData();		//var for X's poly
			polyY = (PolyTerm) iterNodeY.getData();		//var for Y's poly
			if (polyX.getExpo() == polyY.getExpo()) {							//if exponents match
				double newCoef = polyX.getCoef();								//add up coefficient X
				newCoef += polyY.getCoef();										//and coefficient Y
				PolyTerm newPoly = new PolyTerm(newCoef, polyY.getExpo());		//create new polyterm
				this.addValueInOrder(newPoly);									//add new value to list Z
				iterNodeX = iterNodeX.getNext();								//move onto next X node
				iterNodeY = iterNodeY.getNext();								//move onto next Y node
				continue;														//skip the rest of the loop
			}
			if (polyX.getExpo() > polyY.getExpo()) {			//if expo X is greater 
				this.addValueInOrder(polyX);					//add X's value to list Z
				iterNodeX = iterNodeX.getNext();				//move onto next X node
				continue;										//skip the rest of the loop
			}
			if (polyX.getExpo() < polyY.getExpo()) {			//if expo Y is greater
				this.addValueInOrder(polyY);					//add Y's value to list Z
				iterNodeY = iterNodeY.getNext();				//move onto the next Y node						
			}
		}
		if (iterNodeX == null && iterNodeY != null) {					//if only iterX is null
			this.addNodeBack(iterNodeY);	//add the rest of Linked List Y
			return; 					//return list Z
		} 											//else
		if (iterNodeX != null) {
			this.addNodeBack(iterNodeX); 		//add the rest of Linked List X
			return; 						//return list Z
		}
	}

	//subtracts polynomial function Y from polynomial function X
	public void subtractPolyLists(PolyNodeLL polyLLX, PolyNodeLL polyLLY) {
		Node iterNodeX = polyLLX.getHead();		//iterator nodes for X and Y
		Node iterNodeY = polyLLY.getHead();
		//Node iterNodeZ = this.getHead();		future parallel Z iterator
		PolyTerm polyX = new PolyTerm();		//vars for keeping track of vars of iterators
		PolyTerm polyY = new PolyTerm();
		while (iterNodeX != null && iterNodeY != null) {	//while both iterators are not null
			polyX = (PolyTerm) iterNodeX.getData();	//var for polyterm X
			polyY = (PolyTerm) iterNodeY.getData();	//var for polyterm Y
			double coefZ = 0;						//var for coef
			if (polyX.getExpo() == polyY.getExpo()) {						//if exponents are the same
				coefZ += polyX.getCoef();									//add coef X
				coefZ -= polyY.getCoef();									//subtract coef Y
				PolyTerm newPoly = new PolyTerm(coefZ, polyY.getExpo());	//create polyterm with coef Z
				this.addValueInOrder(newPoly);								//add polyterm to list
				iterNodeX = iterNodeX.getNext();							//move onto next X node
				iterNodeY = iterNodeY.getNext();							//move onto next Y node
				continue;													//skip the rest of the loop
			}
			if (polyX.getExpo() > polyY.getExpo()) {						//if expo X is greater than expo Y
				coefZ += polyX.getCoef();									//add coef X to coef Z
				PolyTerm newPoly = new PolyTerm(coefZ, polyX.getExpo());	//create new polyterm with coef X
				this.addValueInOrder(newPoly);								//add new value to list Z
				iterNodeX = iterNodeX.getNext();							//move onto next X node
				continue;													//skip the rest of the loop
			}
			if (polyX.getExpo() < polyY.getExpo()) {						//if expo Y is greater than expo X
				coefZ -= polyY.getCoef();									//subtract coef Y
				PolyTerm newPoly = new PolyTerm(coefZ, polyY.getExpo());	//create new polyterm with coef Y
				this.addValueInOrder(newPoly);								//add value to list Z
				iterNodeY = iterNodeY.getNext();							//move onto next Y node
			}
		}
		if(iterNodeY == null && iterNodeX != null) {	//if only poly list X is left
			Node newNode = iterNodeX;					//create e copy of iterator node
			this.addNodeBack(newNode);					//add the rest of list
		}
		while (iterNodeY != null) {								//while there's still list Y left
				polyY = (PolyTerm) iterNodeY.getData();			//get iterators polyterm
				polyY.negateCoef();								//negate the coefficient
				Node newPolyNode = new Node(polyY, null);	//create a new node with negate coefficient
				this.addNodeBack(newPolyNode);					//add new node the back of the list
				iterNodeY = iterNodeY.getNext();				//move onto next node
		}
		return;
	}

	//multiplies polynomial functions
	public void multiplyPolyLists(PolyNodeLL polyLLX, PolyNodeLL polyLLY) {
		Node iterNodeX = polyLLX.getHead();		//iterator for list X
		while (iterNodeX != null) {				//iterate through list X
			Node iterNodeY = polyLLY.getHead();	//iterator for list Y
			while (iterNodeY != null) {			//iterate through list Y
				PolyTerm polyX = (PolyTerm) iterNodeX.getData();		//var for polyterm X
				PolyTerm polyY = (PolyTerm) iterNodeY.getData();		//var for polyterm Y
				int newExpo = (polyX.getExpo() + polyY.getExpo());		//new expo += expo Y
				double newCoef = (polyY.getCoef() * polyX.getCoef());	//new coef = coef X
				PolyTerm newPoly = new PolyTerm(newCoef, newExpo);		//create new polyterm with new values
				this.addValueInOrder(newPoly);							//add new value to the list
				iterNodeY = iterNodeY.getNext();						//move onto next Y node
			}
			iterNodeX = iterNodeX.getNext();	//move onto next X node
		}
		return;
	}

	//evaluates polynomial function given x
	public double evaluatePolyList(double valueOfX) {
		double finalValue = 0;		//final value to return
		Node iterNode = this.getHead();	//iterator node for list X
		while(iterNode != null) {	//iterates through the list
			double coef = ((PolyTerm) iterNode.getData()).getCoef();	//var for current coefficient
			int expo = ((PolyTerm) iterNode.getData()).getExpo();		//var for current exponent
			double tempValue = Math.pow(valueOfX, expo);				//new temporary value = x^exponent
			tempValue *= coef;											//temporary value *= coefficient
			finalValue += tempValue;									//add to final value
			iterNode = iterNode.getNext();								//move onto next node
		}
		return finalValue;	//return the final value
	}

}