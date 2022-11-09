package classes;

public class Node {
	// attributes
	private Object data;
	private Node next;

	// constructors
	public Node() {
		data = null;
		next = null;
	}
	public Node(Object data) {
		this.data = data;
		this.next = null;
	}
	public Node(Object data, Node next) {
		this.data = data;
		this.next = next;
	}

	// getter and setter for data
	public Object getData() {return this.data;}
	public void setData(Object data) {this.data = data;}

	// getter and setter for next node
	public Node getNext() {return this.next;}
	public void setNext(Node next) {this.next = next;}
}