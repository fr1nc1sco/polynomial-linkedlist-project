package classes;

public class LinkedList {
	//attributes
	private Node head;

	//constructors
	public LinkedList() {head = null;}
	public LinkedList(Node newHead) {this.head = newHead;}

	//head getter and setter
	public Node getHead() {return this.head;}
	public void setHead(Node newHead) {this.head = newHead;}

	//methods
	//string override
	@Override
	public String toString() {
		String string = "list contents: ["; //beginning of string
		Node iterNode = this.head; //create an iterator node
		while (iterNode != null) { 									//while iter node is not null
			string = string.concat(iterNode.getData().toString());	//add node value to string
			iterNode = iterNode.getNext(); 							//move onto the next node
			if (iterNode != null) {									//add comma if necessary
				string = string.concat(", ");}
		}
		string = string.concat("]"); //end of string
		return string; //return string
	}

	//print list
	public void printList() {
		System.out.print(this); //print list string
	}

	//list has value X
	public boolean listHasValue(Object obj) {
		Node iterNode = this.head;	//create an iterator node
		while (iterNode != null) {				//while its not null
			if (iterNode.getData() == obj) {	//if value is found
				return true;}					//return true
			iterNode = iterNode.getNext(); 		//move onto next node
		}
		return false;	//not found so return false
	}

	//add value X to the front
	public void addValueFront(Object obj) {
		if (listHasValue(obj)) {	//if list has value
			return;					//end function
		}							//else
		Node newNode = new Node();	//create new node
		newNode.setData(obj); 		//give new node new data
		addNodeFront(newNode);		//call add node front
		return;						//end function
	}
	//add node front
	public void addNodeFront(Node newNode) {
		newNode.setNext(this.head);	//make new node's next the current head
		this.head = newNode; 		//make new node the new head
		return;						//end function
	}

	//add value X to the back
	public void addValueBack(Object obj) {
		Node newNode = new Node(obj);	//create a new node and give it the data
		addNodeBack(newNode);			//call addNodeBack with new node
		return;							//end function
	}
	//add node back
	public void addNodeBack(Node newNode) {
		if (this.head == null) {	//if head is null
			this.head = newNode;	//make newNode the head
			return;					//end function
		}
		Node iterNode = head; //create an iterator node
		while (iterNode != null) { //while its not null
			if (iterNode.getData() == newNode.getData()) { return;}	//end function if value is already in list
			if (iterNode.getNext() == null) {	//if you're at the back of the list
				iterNode.setNext(newNode);		//add new node to the back of the list
				return;							//end function
			}									//else
			iterNode = iterNode.getNext();		//move onto the next node
		}
	}

	//removes value if found
	public void removeValue(Object value) {
		if (this.head == null) {return;}	//end function if empty
		if (this.head.getData() == value) {	//if head is deleted
			this.head = head.getNext();		//make next node new head, could be null
			return;							//end function
		}
		Node prevNode = this.head;				//holds previous node
		Node currNode = this.head.getNext();	//holds node that could be deleted
		while (currNode != null) {						//iterate through the list
			if (currNode.getData() == value) {			//if current has value
				prevNode.setNext(currNode.getNext());	//previous's next = current's next
				return;									//end function
			}											//else
			currNode = currNode.getNext();				//move onto next set of node
			prevNode = prevNode.getNext();
		}
		return;	//end function
	}

	//reverse the order of the node in the linked list
	public void reverseOrder() {
		Node prevNode = null; 		//set previous node to null
		Node currNode = this.head;	//set current node equal to the head
		Node nextNode = null;		//set next node equal to null
		while (currNode != null) {			//while current is not node
			nextNode = currNode.getNext();	//have next node hold next value
			currNode.setNext(prevNode); 	//set currents next to previous, does the "reversing"
			prevNode = currNode; 			//move previous onto current
			currNode = nextNode; 			//move current onto next
		}
		this.setHead(prevNode);		//make last node the new head
		return;						//end function
	}

	//return a new list = (X ^ Y)
	public void intersectionOf(LinkedList L1, LinkedList L2) {
		Node iter1 = L1.getHead();	//iterator node for L1
		while (iter1 != null) {		//iterating through L1
			Node iter2 = L2.getHead();	//iterator node for L2
			while (iter2 != null) { 	//iterating through L2
				if (iter1.getData() == iter2.getData()) {		//if iterator data matches
					Node newNode = new Node(iter2.getData());	//create new node with same value
					this.addNodeBack(newNode);					//add to the back of the list
					break; 										//stop iterating through L2
				}
				iter2 = iter2.getNext();	//move onto next value in L2
			}
			iter1 = iter1.getNext();	//move onto next value in L1
		}
		return;
	}

}
