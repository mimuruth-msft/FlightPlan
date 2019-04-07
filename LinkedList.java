
public class LinkedList<K extends Comparable<K>,T extends Comparable<T>> implements Comparable<LinkedList<K,T>>{

	private LinkedListNode<T> root;
	private K listInfo;
	
	public LinkedList(){
		this(null);
	}
	
	public LinkedList(K listInfo){
		this.listInfo = listInfo;
		this.root = null;
	}
	
	public void addData(T data){
		root = new LinkedListNode<T>(data,root);
	}
	
	public T getData(T data){
		LinkedListNode<T> current = root;
		T result = null;
		while(current!=null){
			if(data.compareTo(current.getData()) == 0){
				result = current.getData();
				break;
			}
			current = current.getNext();
		}
		return result;
	}
	
	public T removeLast(){
		if(root == null)
			return null;
		T result = root.getData();
		root = root.getNext();
		return result;
	}

	public T remove(T data){
		LinkedListNode<T> current = root;
		LinkedListNode<T> previous = null;
		T result = null;
		while(current!=null){
			if(data.compareTo(current.getData()) == 0){
				result = current.getData();
				if(previous == null){
					root = root.getNext();
				}else{
					previous.setNext(current.getNext());
				}
				break;
			}
			previous = current;
			current = current.getNext();
		}
		return result;
	}
	
	public K getInfo(){
		return listInfo;
	}

	@Override
	public int compareTo(LinkedList<K, T> o) {
		if(listInfo == null)
			return -1;
		return listInfo.compareTo(o.getInfo());
	}

	public LinkedListNode<T> getRoot(){
		return root;
	}
	
	public void setInfo(K listInfo){
		this.listInfo = listInfo;
	}

	@Override
	public String toString() {
		return "LinkedList [root=" + getStringList() + ", listInfo=" + listInfo + "]";
	}
	
	private String getStringList(){
		String result = "";
		LinkedListNode<T> current = root;
		while(current!=null){
			result += current.getData().toString() + ", ";
			current = current.getNext();
		}
		return result;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
}
