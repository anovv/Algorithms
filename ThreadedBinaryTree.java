
public class ThreadedBinaryTree {
	
	private Node root;
	
	private class Node{
		int val;//prefix
		Node left;//0
		Node right;//1
		boolean isRightThread;
		boolean isLeftThread;
	}
	
	public static void main(String[] args){
		ThreadedBinaryTree tbt = new ThreadedBinaryTree();
		tbt.insert(1);
		tbt.insert(3);
		tbt.insert(2);
		
		tbt.traverse();
	}
	
	public ThreadedBinaryTree(){
		root = new Node();
		root.val = 0;
	}
	
	private void insert(int val){
		insertHelper(root, val);
	}
	
	private void insertHelper(Node root, int val){
		if(root == null){
			return;
		}
		if(root.val < val){
			//left
			if(root.left == null){
				Node n = new Node();
				n.val = val;
				root.left = n;
			}else{
				insertHelper(root.left, val);
			}
		}else{
			//right
			if(root.right == null){
				Node n = new Node();
				n.val = val;
				root.right = n;
			}else{
				insertHelper(root.right, val);
			}
		}
	}
	
	public void traverse(){
		traverseHelper(root);
	}
	
	private void traverseHelper(Node root){
		if(root == null){
			return;
		}
		System.out.println(root.val);
		traverseHelper(root.left);
		traverseHelper(root.right);
	}
}
