
public class LinkedListNode<T extends Comparable<T>> {
	private T data;
	private LinkedListNode<T> next;
	
	public LinkedListNode(T data){
		this(data,null);
	}
	
	public LinkedListNode(T data, LinkedListNode<T> next){
		this.data = data;
		this.next = next;
	}
	
	public T getData(){
		return data;
	}
	
	public LinkedListNode<T> getNext(){
		return next;
	}
	
	public void setNext(LinkedListNode<T> next){
		this.next = next;
	}
}
