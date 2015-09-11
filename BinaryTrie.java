import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BinaryTrie {
	
	private Node root;
	
	public static void main(String[] args){
		List<Integer> l = new ArrayList<Integer>();
		l.add(5);
		l.add(6);
		l.add(2);
		l.add(1);
		l.add(4);
		BinaryTrie bt = new BinaryTrie(l);
		List<String> bins = bt.getLeafs();
		for(String s : bins){
			System.out.println(s);
		}
	}
	
	public void insert(String binary){
		insertHelper(root, binary, 0);
	}
	
	public void traverse(){
		traverseHelper(root);
	}
	
	private void insertHelper(Node root, String binary, int index){
		if(index >= binary.length()){
			return;
		}
		char c = binary.charAt(index);
		if(c == '0'){
			//left;
			if(root.left == null){
				root.left = new Node();
				String prefix = binary.substring(0, index + 1);
				root.left.value = prefix;
			}
			insertHelper(root.left, binary, index + 1);
		}else{
			//right
			if(root.right == null){
				root.right = new Node();
				String prefix = binary.substring(0, index + 1);
				root.right.value = prefix;
			}
			insertHelper(root.right, binary, index + 1);
		}
	}
	
	private void traverseHelper(Node root){
		if(root == null){
			return;
		}
		traverseHelper(root.left);
		traverseHelper(root.right);
		System.out.println(root.value);
	}
	
	public List<String> getLeafs(){
		List<String> res = new ArrayList<String>();
		getLeafsHelper(res, root);
		return res;
	}
	
	private void getLeafsHelper(List<String> res, Node root){
		if(root.left != null){
			getLeafsHelper(res, root.left);
		}
		if(root.right != null){
			getLeafsHelper(res, root.right);
		}
		if(root.left == null && root.right == null){
			res.add(root.value);
		}
	}
	
	public BinaryTrie(List<Integer> values){
		Collections.sort(values);
		int length = toBinary(values.get(values.size() - 1)).length();
		List<String> binaries = new ArrayList<String>();
		for(int i : values){
			binaries.add(pad(toBinary(i), length));
		}
		root = new Node();
		root.value = "";
		for(String s : binaries){
			insert(s);
		}
	}
	
	//inorder
	private void updateThreads(){
		List<Node> inOrder = new ArrayList<Node>();
		inOrder(inOrder, root);
		for(int i = 0; i < inOrder.size(); i++){
			Node n = inOrder.get(i);
			if(n.left == null && n.right == null){
				//leaf
				continue;
			}
			if(n.left == null && i > 0){
				n.left = inOrder.get(i - 1);
			}
			if(n.right == null && i < inOrder.size() - 1){
				n.right = inOrder.get(i + 1);
			}
		}
	}
	
	private void inOrder(List<Node> list, Node root){
		if(root == null){
			return;
		}
		inOrder(list, root.left);
		list.add(root);
		inOrder(list, root.right);
	}
	
	
	
	/*public void printBinaries(){
		for(String s : binaries){
			System.out.println(s);
		}
	}*/
	
	public String toBinary(int n){
		int exp = 1;
		int res = 0;
		while(n > 0){
			int r = n%2;
			res += exp*r;
			n /= 2;
			exp *= 10;
		}
		
		return res + "";
	}
	
	private String pad(String binary, int length){
		int len = binary.length();
		for(int i = 0; i < length - len; i++){
			binary = "0" + binary;
		}
		return binary;
	}
	
	
	private class Node{
		String value;
		Node left;
		Node right;
	}
}
