import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class XFastTrie {
	
	private int depth;
	
	private List<Set<String>> levels;
	
	private Node root;
	
	private class Node{
		String val;//
		Node left;//0, predecessor if leaf
		Node right;//1, successor if leaf
		
		State state;
		
		public String toString(){
			String s = " Val: " + val + " State: " + state;
			if(state == State.LEAF){
				String rightStr = (right == null) ? "null" : right.val;
				String leftStr = (left == null) ? "null" : left.val;
				s += " Left: " + leftStr + " Right: " + rightStr;
			}else if(state == State.NORIGHT){
				String rightStr = (right == null) ? "null" : right.val;
				s += " Right: " + rightStr;
			}else if(state == State.NOLEFT){
				String leftStr = (left == null) ? "null" : left.val;
				s += " Left: " + leftStr;
			}
			return s;
		}
	}
	
	private enum State{
		NOLEFT,
		NORIGHT,
		BOTH,
		LEAF
	}
	
	public static void main(String[] args){
		XFastTrie xft = new XFastTrie(3);

		xft.insert(1);
		xft.insert(2);
		xft.insert(3);
		xft.insert(4);
		
		System.out.print(xft.getSuccessorOrPredecessor(4, false));
		
		/*for(Set<String> level : xft.levels){
			for(String s : level){
				System.out.println(xft.getByPrefix(xft.root, s, 0));
			}
		}*/
	}
	
	public XFastTrie(int size){
		depth = size + 1;
		root = new Node();
		levels = new ArrayList<Set<String>>();
		for(int i = 0; i < depth; i++){
			levels.add(new HashSet<String>());
		}	
		levels.get(0).add("");
	}
	
	public boolean contains(int val){
		String binary = toBinary(val);
		return levels.get(depth - 1).contains(binary);
	}
	
	public void delete(int v){
		String val = toBinary(v);
		if(!levels.get(depth - 1).contains(val)){
			return;
		}
		Node predecessor = getSuccessorOrPredecessorHelper(v, false);
		Node successor = getSuccessorOrPredecessorHelper(v, true);
		
		//delete from a linked list
		if(predecessor != null){
			predecessor.right = successor;
		}
		if(successor != null){
			successor.left = predecessor;
		}
		
		deleteHelper(root, predecessor, successor, val, 0, new boolean[]{true});
	}
	
	private void deleteHelper(Node root, Node pred, Node suc, String val, int index, boolean[] check){
		if(index >= val.length()){
			return;
		}
		char c = val.charAt(index);
		if(c == '0'){
			deleteHelper(root.left, pred, suc, val, index + 1, check);
		}else{
			deleteHelper(root.right, pred, suc, val, index + 1, check);
		}
		
		if(check[0]){
			if(root.state == State.BOTH){
				check[0] = false;
				if(c == '0'){
					root.left = suc;
					root.state = State.NOLEFT;
				}else{
					root.right = pred;
					root.state = State.NORIGHT;
				}
			}else{
				root.left = null;
				root.right = null;
				root.state = null;
			}
			String prefix = val.substring(0, index + 1);
			levels.get(index + 1).remove(prefix);
		}else{
			if(root.state == State.NOLEFT){
				root.left = suc;
			}else if(root.state == State.NORIGHT){
				root.right = pred;
			}
		}
	}
	
	public void insert(int v){
		String val = toBinary(v);
		if(levels.get(depth - 1).contains(val)){
			return;
		}
		
		Node n = new Node();
		n.val = val;
		n.state = State.LEAF;
		Node predecessor = getSuccessorOrPredecessorHelper(v, false);
		Node successor = getSuccessorOrPredecessorHelper(v, true);
		
		//insert in a linked list
		if(predecessor != null){
			predecessor.right = n;
			n.left = predecessor;
		}
		if(successor != null){
			successor.left = n;
			n.right = successor;
		}
		
		//insert in a trie and a level list
		insertHelper(root, n, 0);
		//update links
		updateSuccThreads(root, n, successor, 0);
		updatePredThreads(root, n, predecessor, 0);
//		updateSuccThreads(root, n, successor, 0, new Node[]{null});
//		updatePredThreads(root, n, predecessor, 0, new Node[]{null});
	}

	private void insertHelper(Node root, Node node, int index){
		String val = node.val;
		if(index >= val.length()){
			return;
		}
		char c = val.charAt(index);
		if(c == '0'){
			//left
			if(root.state == State.NOLEFT){
				root.left = null;
				root.state = null;
			}
			if(root.left == null){
				if(index == val.length() - 1){
					//leaf
					root.left = node;
				}else{
					Node n = new Node();
					root.left = n;
				}
				String prefix = val.substring(0, index + 1);
				levels.get(index + 1).add(prefix);
			}
			insertHelper(root.left, node, index + 1);
		}else{
			//right
			if(root.state == State.NORIGHT){
				root.right = null;
				root.state = null;
			}
			if(root.right == null){
				if(index == val.length() - 1){
					//leaf
					root.right = node;
				}else{
					Node n = new Node();
					root.right = n;
				}
				String prefix = val.substring(0, index + 1);
				levels.get(index + 1).add(prefix);
			}
			insertHelper(root.right, node, index + 1);
		}
		
		//update threads
		if(root.left == null || root.state == State.NOLEFT){
			root.left = node;
			root.state = State.NOLEFT;
		}else if(root.right == null || root.state == State.NORIGHT){
			root.right = node;
			root.state = State.NORIGHT;
		}else{
			if(root.state == null){
				root.state = State.BOTH;
			}
		}
	}
	
	/*private void updateSuccThreads(Node root, Node node, Node sucessor, int index, Node[] min){
		if(sucessor == null || node == null || root == null){
			return;
		}
		String val = sucessor.val;
		if(index >= val.length()){
			return;
		}
		
		char c = val.charAt(index);
		if(c == '0'){
			updateSuccThreads(root.left, node, sucessor, index + 1, min);
		}else{
			updateSuccThreads(root.right, node, sucessor, index + 1, min);
		}
		
		String prefix1 = node.val.substring(0, index + 1);
		String prefix2 = val.substring(0, index + 1);
		
		boolean start = prefix1.equals(prefix2);
		if(start && min[0] == null){
			min[0] = getMin(root.right);
//			System.out.println(root.state);
		}
		
		if((root.left == null || root.state == State.NOLEFT) && start){
			root.left = min[0];
			root.state = State.NOLEFT;
		}
	}
	
	private void updatePredThreads(Node root, Node node, Node predecessor, int index, Node[] max){
		if(predecessor == null || node == null || root == null){
			return;
		}
		String val = predecessor.val;
		if(index >= val.length()){
			return;
		}
		
		char c = val.charAt(index);
		if(c == '0'){
			updatePredThreads(root.left, node, predecessor, index + 1, max);
		}else{
			updatePredThreads(root.right, node, predecessor, index + 1, max);
		}
		
		String prefix1 = node.val.substring(0, index + 1);
		String prefix2 = val.substring(0, index + 1);
		
		boolean start = prefix1.equals(prefix2);
		
		if(start && max[0] == null){
			max[0] = getMax(root.left);
			System.out.println(root.state);
		}
		
		if((root.right == null || root.state == State.NORIGHT) && start){
			root.right = max[0];
			root.state = State.NORIGHT;
		}
	}*/
	
	private Node getMax(Node root){
		if(root.state == State.LEAF){
			return root;
		}
		
		if(root.state == State.NORIGHT){
			return getMax(root.left);
		}else{
			return getMax(root.right);
		}
	}
	
	private Node getMin(Node root){
		if(root.state == State.LEAF){
			return root;
		}
		
		if(root.state == State.NOLEFT){
			return getMin(root.right);
		}else{
			return getMin(root.left);
		}
	}
	
	private void updateSuccThreads(Node root, Node node, Node sucessor, int index){
		if(sucessor == null || node == null || root == null){
			return;
		}
		String val = sucessor.val;
		if(index >= val.length()){
			return;
		}
		
		char c = val.charAt(index);
		if(c == '0'){
			updateSuccThreads(root.left, node, sucessor, index + 1);
		}else{
			updateSuccThreads(root.right, node, sucessor, index + 1);
		}
		
		String prefix1 = node.val.substring(0, index + 1);
		String prefix2 = val.substring(0, index + 1);
		
		boolean start = prefix1.equals(prefix2);
		
		if((root.left == null || root.state == State.NOLEFT) && start){
			root.left = node;
			root.state = State.NOLEFT;
		}
	}
	
	private void updatePredThreads(Node root, Node node, Node predecessor, int index){
		if(predecessor == null || node == null || root == null){
			return;
		}
		String val = predecessor.val;
		if(index >= val.length()){
			return;
		}
		
		char c = val.charAt(index);
		if(c == '0'){
			updatePredThreads(root.left, node, predecessor, index + 1);
		}else{
			updatePredThreads(root.right, node, predecessor, index + 1);
		}
		
		String prefix1 = node.val.substring(0, index + 1);
		String prefix2 = val.substring(0, index + 1);
		
		boolean start = prefix1.equals(prefix2);
		
		if((root.right == null || root.state == State.NORIGHT) && start){
			root.right = node;
			root.state = State.NORIGHT;
		}
	}
	
	public int getSuccessorOrPredecessor(int v, boolean sucessor){
		Node n = getSuccessorOrPredecessorHelper(v, sucessor);
		if(n == null){
			return -1;
		}
		return fromBinary(n.val);
	}
	
	private Node getSuccessorOrPredecessorHelper(int v, boolean successor){
		String val = toBinary(v);
		if(levels.get(depth - 1).contains(val)){
			if(successor){
				return getByPrefix(root, val, 0).right;
			}else{
				return getByPrefix(root, val, 0).left;
			}
		}
		
		int beg = 0;
		int end = depth - 1;
		
		//binary search for longest common prefix
		int mid = (end + beg)/2;
		String prefix = val.substring(0, mid);
		while(end >= beg){
			Set<String> level = levels.get(mid);
			if(level.contains(prefix)){
				beg = mid + 1;
			}else{
				end = mid - 1;
			}
			mid = (end + beg)/2;
			prefix = val.substring(0, mid);
		}
		
		Node lcp = getByPrefix(root, prefix, 0);
		
		if(lcp.state == State.LEAF){
			//leaf
			return lcp;
		}else{
			//internal
			if(successor){
				if(lcp.state == State.NORIGHT){
					return lcp.right.right;
				}else{
					return lcp.left;
				}
			}else{
				if(lcp.state == State.NOLEFT){
					return lcp.left.left;
				}else{
					return lcp.right;
				}
			}
		}
	}
	
	private Node getByPrefix(Node root, String prefix, int index){
		if(index == prefix.length()){
			return root;
		}
		char c = prefix.charAt(index);
		if(c == '0'){
			return getByPrefix(root.left, prefix, index + 1);
		}else{
			return getByPrefix(root.right, prefix, index + 1);
		}
	}
	
	private String toBinary(int n){
		String res = "";
		while(n > 0){
			res = n%2 + res;
			n /= 2;
		}
		
		//pad with zeroes
		while(res.length() < depth - 1){
			res = "0" + res;
		}
		return res;
	}
	
	private int fromBinary(String val){
		int res = 0;
		int exp = 1;
		
		for(int i = val.length() - 1; i >= 0; i--){
			int temp = Character.getNumericValue(val.charAt(i));
			res += temp*exp;
			exp *= 2;
		}
		
		return res;
	}
}
