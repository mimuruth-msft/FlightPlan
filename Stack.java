
public class Stack<T extends Comparable<T>> implements Comparable<Stack<T>> {

	private LinkedList<String,T> stack;
	
	public Stack(){
		stack = new LinkedList<String,T>();
	}
	
	public void push(T data){
		stack.addData(data);
	}
	
	public T peek(){
		if(stack.getRoot() == null)
			return null;
		return stack.getRoot().getData();
	}
	
	public T pop(){
		T result = stack.removeLast();
		return result;
	}
	
	public boolean contains(T data){
		return stack.getData(data)!=null;
	}

	@Override
	public int compareTo(Stack<T> o) {
		return stack.compareTo(o.stack);
	}

	@Override
	public String toString() {
		return "Stack [stack=" + stack + "]";
	}
	
}
