import java.util.ArrayList;
import java.util.List;


public class AVLTree {
	
	private class Node{
		int val;
		Node left;
		Node right;
		int height = 0;
	}

	private Node root;
	private int size = 0;
	
	public AVLTree(){}
	
	public AVLTree(Node root){
		this.root = root;
	}
	
	public void setSize(int size){
		this.size = size;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getRootValue(){
		return root.val;
	}
	
	public int getSuccessorOrPredecessor(int val, boolean successor){
		List<Integer> inorder = new ArrayList<Integer>();
		getInorder(root, inorder);
		if(inorder.isEmpty()){
			return -1;
		}
		int res = -1;
		if(successor){
			for(int i = 0; i < inorder.size(); i++){
				int t = inorder.get(i);
				if(t > val){
					res = t;
					break;
				}
			}
		}else{
			for(int i = inorder.size() - 1; i >= 0; i--){
				int t = inorder.get(i);
				if(t < val){
					res = t;
					break;
				}
			}
		}
		return res;
	}
	
	private void getInorder(Node root, List<Integer> inorder){
		if(root == null){
			return;
		}
		getInorder(root.left, inorder);
		inorder.add(root.val);
		getInorder(root.right, inorder);
	}
	
	public boolean contains(int val){
		return contains(root, val);
	}
	
	private boolean contains(Node root, int val){
		if(root == null){
			return false;
		}
		
		if(root.val == val){
			return true;
		}
		
		if(val > root.val){
			return contains(root.right, val);
		}else{
			return contains(root.left, val);
		}
	}
	
	public void delete(int val){
		root = delete(root, val);
	}
	
	private Node delete(Node root, int val){
	
		if(root == null){
			return null;
		}
		
		if(val > root.val){
			root.right = delete(root.right, val);
			if(getHeight(root.right) - getHeight(root.left) == 2){
				if(val > root.right.val){
					root = rotateRight(root);
				}else{
					root = doubleRotateRight(root);
				}
			}
		}else if(val < root.val){
			root.left = delete(root.left, val);
			if(getHeight(root.left) - getHeight(root.right) == 2){
				if(val < root.left.val){
					root = rotateLeft(root);
				}else{
					root = doubleRotateLeft(root);
				}
			}
		}else{
			if(root.left == null || root.right == null){
				size--;
				if(root.right == null && root.left == null){
					//no children
					root = null;
				}else{
					//one child
					if(root.right == null){
						root = root.left;
					}else{
						root = root.right;
					}
				}
			}else{
				//two children
				//inorder successor
				Node min = getMin(root.right);
				root.val = min.val;
				root.right = delete(root.right, min.val);
			}
		}
		
		if(root != null){
			root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
		}
		
		return root;
	}
	
	private Node getMin(Node root){
		if(root == null){
			return null;
		}
		if(root.left == null){
			return root;
		}
		return getMin(root.left);
	}
	
	public void insert(int val){
		root = insert(root, val);
	}
	
	private Node insert(Node root, int val){
		if(root == null){
			root = new Node();
			root.val = val;
			size++;
			return root;
		}
		
		if(val > root.val){
			//right
			root.right = insert(root.right, val);
			if(getHeight(root.right) - getHeight(root.left) == 2){
				if(val > root.right.val){
					root = rotateRight(root);
				}else{
					root = doubleRotateRight(root);
				}
			}
		}else if(val < root.val){
			//left
			root.left = insert(root.left, val);
			if(getHeight(root.left) - getHeight(root.right) == 2){
				if(val < root.left.val){
					root = rotateLeft(root);
				}else{
					root = doubleRotateLeft(root);
				}
			}
		}
		
		root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
		return root;
	}
	
	private Node rotateLeft(Node node){
		Node left = node.left;
		node.left = left.right;
		left.right = node;
		node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
		left.height = Math.max(getHeight(left.left), getHeight(node)) + 1;
		
		return left;
	}
	
	private Node rotateRight(Node node){
		Node right = node.right;
		node.right = right.left;
		right.left = node;
		node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
		right.height = Math.max(getHeight(right.right), getHeight(node)) + 1;
		
		return right;
	}

	private Node doubleRotateLeft(Node node){
		node.left = rotateRight(node.left);
		return rotateLeft(node);
	}
	
	private Node doubleRotateRight(Node node){
		node.right = rotateLeft(node.right);
		return rotateRight(node);
	}
	
	private static int getHeight(Node node){
		if(node == null){
			return -1;
		}
		return node.height;
	}
	
	public void lvlByLvl(){
		lvlByLvl(root);
	}
	
	private void lvlByLvl(Node root){
		int depth = getDepth(root);
		for(int i = depth; i >= 0; i--){
			List<Integer> vals = new ArrayList<Integer>();
			getLevel(root, vals, i, depth);
			for(int v : vals){
				System.out.print(v + " ");
			}
			System.out.println("");
		}
	}
	
	public void inorder(){
		inorder(root);
		System.out.println();
	}
	
	private void inorder(Node root){
		if(root == null){
			return;
		}
		inorder(root.left);
		System.out.print(root.val + " ");
		inorder(root.right);
	}

    private static void getLevel(Node root, List<Integer> a, int level, int l){
        if(level == l){
            a.add(root.val);
        }
        
        if(root.left != null){
            getLevel(root.left, a, level + 1, l);
        }
        
        if(root.right != null){
            getLevel(root.right, a, level + 1, l);
        }
    }
	
	private static int getDepth(Node root){
		if(root == null){
			return 0;
		}
		
		return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
	}
	
	private static Node fromLinkedList(Node head){
		int length = getLength(head);
		if(length == 0){
			return null;
		}
		if(length == 1){
			return head;
		}
		Node r = head;
		for(int i = 0; i < length/2 - 1; i++){
			r = r.right;
		}
		Node next = r.right;
		r.right = null;
		
		next.left = fromLinkedList(head);
		next.right = fromLinkedList(next.right);
		
		next.height = Math.max(getHeight(next.left), getHeight(next.right)) + 1;
		
		return next;
	}

	private static int getLength(Node head){
		int length = 0;
		while(head != null){
			length++;
			head = head.right;
		}
		return length;
	}
	
	private static Node toLinkedList(Node root){
		List<Node> inorder = new ArrayList<Node>();
		toLinkedListHelper(root, inorder);
		if(inorder.isEmpty()){
			return null;
		}
		for(int i = 0; i < inorder.size(); i++){
			if(i == inorder.size() - 1){
				inorder.get(i).right = null;
			}else{
				inorder.get(i).right = inorder.get(i + 1);
			}
			inorder.get(i).left = null;
			inorder.get(i).height = 0;
		}
		
		return inorder.get(0);
	}
	
	private static void toLinkedListHelper(Node root, List<Node> inorder){
		if(root == null){
			return;
		}
		toLinkedListHelper(root.left, inorder);
		inorder.add(root);
		toLinkedListHelper(root.right, inorder);
	}
	
	private static Node[] splitList(Node head){
		Node[] res = new Node[2];
		res[0] = null;
		res[1] = null;
		int length = getLength(head);
		if(length == 0){
			return res;
		}
		
		if(length == 1){
			res[1] = head;
			return res;
		}
		
		Node n = head;
		for(int i = 0; i < length/2 - 1; i++){
			n = n.right;
		}
		
		Node right = n.right;
		n.right = null;
		
		res[0] = head;;
		res[1] = right;
		
		return res;
	}
	
	private static Node mergeLists(Node head1, Node head2){
		if(head1 == null){
			return head2;
		}
		if(head2 == null){
			return head1;
		}
		
		Node head = null;
			
		if(head1.val < head2.val){
			head = head1;
			head.right = mergeLists(head1.right, head2);
		}else{
			head = head2;
			head.right = mergeLists(head1, head2.right);
		}
		return head;
	}
	
	public static AVLTree[] split(AVLTree tree){
		Node list = toLinkedList(tree.root);
		Node[] lists = splitList(list);
		AVLTree[] trees = new AVLTree[2];
		int size = 0;
		if(tree != null){
			size = tree.getSize();
		}
		int size1 = size/2;
		int size2 = size - size1;
		if(lists[0] != null){
			AVLTree tree1 = new AVLTree(fromLinkedList(lists[0]));
			tree1.setSize(size1);
			trees[0] = tree1;
		}else{
			trees[0] = null;
		}
		
		if(lists[1] != null){
			AVLTree tree2 = new AVLTree(fromLinkedList(lists[1]));
			tree2.setSize(size2);
			trees[1] = tree2;
		}else{
			trees[1] = null;
		}
		
		return trees;
	}
	
	public static AVLTree merge(AVLTree tree1, AVLTree tree2){
		int size1 = tree1.getSize();
		int size2 = tree2.getSize();
		Node head1 = toLinkedList(tree1.root);
		Node head2 = toLinkedList(tree2.root);
		Node mergedList = mergeLists(head1, head2);
		if(mergedList == null){
			return null;
		}
		AVLTree tree = new AVLTree(fromLinkedList(mergedList));
		tree.setSize(size1 + size2);
		return tree;
	}
}
