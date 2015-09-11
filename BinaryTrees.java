import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


public class BinaryTrees {
	
	public static void flatten2(TreeNode root){
		
		flattenHelper(root, new TreeNode[]{null});
	}
	
	public static void flattenHelper(TreeNode root, TreeNode[] prev){

		if(root.right != null){
			flattenHelper(root.right, prev);
		}
		
		if(root.left != null){
			flattenHelper(root.left, prev);
		}		
		

		root.right = prev[0];
		root.left = null;
		prev[0] = root;
		
	}
	
	public static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int val){
			this.val = val;
		}
	}
	
	public static class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;
		TreeLinkNode(int x) { val = x; }
	}
			
	public static void printTree(TreeNode root){
		System.out.println(root.val);
		if(root.left != null){
			printTree(root.left);
		}
		if(root.right != null){
			printTree(root.right);
		}
	}
	
	public static void printByLevel(TreeNode root){
		int depth = getDepth(root);
		for(int i = 0; i < depth; i++){
			printLevel(root, 0, i);
			System.out.println("");
		}
	}
		
	public static void printLevel(TreeNode root, int level, int l){
		if(l == level){
			System.out.print(root.val + " ");
		}
		if(root.left != null){
			printLevel(root.left, level + 1, l);
		}
		if(root.right != null){
			printLevel(root.right, level + 1, l);
		}
	}
	

    public static void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        ArrayList<TreeNode> a = new ArrayList<TreeNode>();
        
        toArray(root, a);
        root = a.get(0);
        
        for(int i = 1; i < a.size(); i++){
        	root.right = a.get(i);
        	root.left = null;
        	root = root.right;
        }
    }
    
    public static void toArray(TreeNode root, ArrayList<TreeNode> arr){
        
        arr.add(root);//preorder?
        if(root.left != null){
            toArray(root.left, arr);
        }
        if(root.right != null){
            toArray(root.right, arr);
        }
    }
    
    public static ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<ArrayList<Integer>>();
        }
        ArrayList<ArrayList<Integer>> levels = getLevels(root);
        for(int i = 0 ; i < levels.size(); i++){
            if(i%2 != 0){
                ArrayList<Integer> level = levels.get(i);
                int size = level.size();
                for(int j = 0; j < size/2; j++){
                    int a = level.get(j);
                    int b = level.get(size - 1 - j);
                    level.set(j, b);
                    level.set(size - 1 - j, a);
                }
                levels.set(i, level);
            }
        }
        
        return levels;
    }
    
    public static ArrayList<ArrayList<Integer>> getLevels(TreeNode root){
        int depth = getDepth(root);
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < depth + 1; i++){
            ArrayList<Integer> level = new ArrayList<Integer>();
            getLevel(root, level, 0, i);
            res.add(level);
        }
        return res;
    }
    
    public static void getLevel(TreeNode root, ArrayList<Integer> a, int level, int l){
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
    
    public static int getDepth(TreeNode root){

		if(root == null){
			return 0;
		}		
		
		int r = getDepth(root.right);
		int l = getDepth(root.left);
		
		return (l > r) ? l + 1 : r + 1;
	}	
    
    public static ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> vals = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode last = null;
        while(root != null || !stack.isEmpty()){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                TreeNode peeknode = stack.peek();
                if(peeknode.right != null && last != peeknode.right){
                    root = peeknode.right;
                }else{
                    stack.pop();
                    vals.add(peeknode.val);
                    last = peeknode;
                }
            }
        }
        return vals;
    }
	
    public static int sumNumbers(TreeNode root) {
        if(root == null){
            return 0;
        }
        
        return sumNumbersHelper(root, root.val);
    }
    
    public static int sumNumbersHelper(TreeNode root, int sum){
        int res = 0;
        
        if(root.left != null){
            int val = root.left.val;
            String s = sum + val + "";
            sum = Integer.parseInt(s);
            sumNumbersHelper(root.left, sum);
        }else if(root.right != null){
            int val = root.right.val;
            String s = sum + val + "";
            sum = Integer.parseInt(s);
            sumNumbersHelper(root.right, sum);
        }else if(root.right == null && root.left == null){
            res += sum;
        }
        
        return res;
    }
    
    public static ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        Stack<Integer> path = new Stack<Integer>();
        if(root != null){
            path.push(root.val);
            pathSumHelper(root, sum, res, path);
        }
        return res;
    }
    
    public static void pathSumHelper(TreeNode root, int sum, ArrayList<ArrayList<Integer>> res, Stack<Integer> path){
        
        /*int s = 0;
        for(int i = 0; i < path.size(); i++){
            s += path.get(i);
        }
        if(s == sum && path.size() != 0){
            res.add(path);
            return;
        }*/
        
        if(root.left != null){
            path.push(root.left.val);
            pathSumHelper(root.left, sum, res, path);
            path.pop();
        }
        
        if(root.right != null){
            path.push(root.right.val);
            pathSumHelper(root.right, sum, res, path);
            path.pop();
        }
        
        if(root.right == null && root.left == null){
            int s = 0;
            for(int i = 0; i < path.size(); i++){
                s += path.get(i);
            }
            if(s == sum && path.size() != 0){
            	ArrayList<Integer> a = new ArrayList<Integer>();
            	for(int i = 0; i < path.size(); i++){
            		a.add(path.get(i));
            	}
            	res.add(a);
                return;
            }
        }
    }
    
    public static int getDepth(TreeLinkNode root){
        if(root == null){
            return 0;
        }
        int l = getDepth(root.left);
        int r = getDepth(root.right);
        
        return (l > r) ? l + 1 : r + 1; 
    }
    
    public static void connect(TreeLinkNode root) {
        if(root == null){
            return;
        }
        int depth = getDepth(root);
        for(int i = 0; i < depth; i++){
            TreeLinkNode[] prev = {null};
            connectLevel(root, i, 0, prev);
        }
    }
    
    public static void connectLevel(TreeLinkNode root, int level, int cur, TreeLinkNode[] prev){
        if(level == cur){
            root.next = prev[0];
            prev[0] = root;
        }
        if(root.right != null){
            connectLevel(root.right, level, cur + 1, prev);
            //System.out.println(prev[0].val);
        }
        if(root.left != null){
            connectLevel(root.left, level, cur + 1, prev);
            //System.out.println(prev[0].val);
        }
    }
    public static void preOrder(TreeNode root){

    	System.out.print(root.val + " ");
    	if(root.left != null){
    		preOrder(root.left);
    	}
    	if(root.right != null){
    		preOrder(root.right);
    	}
    }
    
    public static void inOrder(TreeNode root){
    	if(root.left != null){
    		inOrder(root.left);
    	}
    	System.out.print(root.val + " ");
    	if(root.right != null){
    		inOrder(root.right);
    	}
    }
    
    public static void postOrder(TreeNode root){
    	if(root.left != null){
    		postOrder(root.left);
    	}
    	if(root.right != null){
    		postOrder(root.right);
    	}
    	System.out.print(root.val + " ");
    }    
    
    public static void insertInBST(TreeNode root, int val){
    	if(root == null){
    		return;
    	}
    	if(val > root.val){
    		if(root.right == null){
    			root.right = new TreeNode(val);
    		}else{
    			insertInBST(root.right, val);
    		}
    	}else{
    		if(root.left == null){
    			root.left = new TreeNode(val);
    		}else{
    			insertInBST(root.left, val);
    		}
    	}
    }
    
    /*public static void BFS(TreeNode root, Queue<TreeNode> q){
    	if(root == null){
    		return;
    	}
    	System.out.print(root.val + " ");    	
    	q.add(root.left);
    	q.add(root.right);
    	while(!q.isEmpty()){
    		BFS(q.poll(), q);
    	}
    }*/
    
    public static void BFS(TreeNode root){
    	Queue<TreeNode> q = new LinkedList<TreeNode>();
    	q.add(root);
    	while(!q.isEmpty()){
    		TreeNode node = q.poll();
    		System.out.println(node.val + " ");
    		if(node.left != null){
    			q.add(node.left);
    		}
    		if(node.right != null){
    			q.add(node.right);
    		}
    	}
    }
    
    /*public static void DFS(TreeNode root, Stack<TreeNode> s){
    	if(root == null){
    		return;
    	}
    	
    	System.out.print(root.val);
    	s.push(root.right);
    	s.push(root.left);
    	while(!s.isEmpty()){
    		DFS(s.pop(), s);
    	}
    }*/
    
    public static void DFS(TreeNode root){
    	Stack<TreeNode> s = new Stack<TreeNode>();
    	s.push(root);
    	while(!s.isEmpty()){
    		TreeNode node = s.pop();
    		if(node != null){
    			System.out.print(node.val + " ");
				if(root.right != null){
					s.push(node.right);
				}
				if(root.left != null){
					s.push(node.left);
				}
    		}
    	}
    }
    
    public static void solve(char[][] board) {
    	boolean [][] mark = new boolean[board.length][board[0].length];
    	
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
            	
            	for(int k = 0; k < mark.length; k++){
            		for(int l = 0; l < mark[0].length; l++){
            			mark[k][l] = true;
            		}
            	}
            	
                if(board[i][j] == 'O' && !hasWayOut(board, i, j, mark)){
                    board[i][j] = 'X';
                }
            }
            
        }
    }
    
    
    
    public static boolean hasWayOut(char[][] board, int x, int y, boolean[][] mark){
    	
    	if(board[x][y] == 'O' && (x == 0 || x == board.length - 1 || y == 0 || y == board[0].length - 1)){
    		mark[x][y] = false;
            return true;
        }
        
        boolean check = board[x][y] == 'O';
		mark[x][y] = false;
        
        boolean check1 = mark[x - 1][y] && board[x - 1][y] == 'O'&& hasWayOut(board, x - 1, y, mark);
        boolean check2 = mark[x + 1][y] && board[x + 1][y] == 'O'&& hasWayOut(board, x + 1, y, mark);
        boolean check3 = mark[x][y - 1] && board[x][y - 1] == 'O'&& hasWayOut(board, x, y - 1, mark);
        boolean check4 = mark[x][y + 1] && board[x][y + 1] == 'O'&& hasWayOut(board, x, y + 1, mark);
        
        return check&&(check1||check2||check3||check4);
    }
           
    public static TreeNode construct(int[] vals){
    	int index = 0;
    	TreeNode root = new TreeNode(vals[index++]);
    	Queue<TreeNode> q = new LinkedList<TreeNode>();
    	q.add(root);
    	while(!q.isEmpty()){
    		
    		if(index == vals.length){
    			break;
    		}
    		
    		TreeNode t = q.poll();
    		if(t.left == null){
    			t.left = new TreeNode(vals[index++]);
    			q.add(t.left);
    		}
    		
    		if(index == vals.length){
    			break;
    		}
    		
    		if(t.right == null){
    			t.right = new TreeNode(vals[index++]);
    			q.add(t.right);
    		}
    		
    	}
    	
    	return root;
    }
        
    public static int fib(int n, HashMap<Integer, Integer> map){
    	if(n == 0 || n == 1){
    		return 1;
    	}
    	if(map.containsKey(n)){
    		return map.get(n);
    	}
    	int f = fib(n - 1, map) + fib(n - 2, map);
    	map.put(n, f);
    	return f;
    }
    
    public static void preOrderIter(TreeNode root){
    	Stack<TreeNode> s = new Stack<TreeNode>();
    	while(root != null || !s.isEmpty()){
    		if(root != null){
    			System.out.print(root.val + " ");
    			s.push(root.right);
    			root = root.left;
    		}else{
    			root = s.pop();
    		}
    	}
    }
    
    public static void inOrderIter(TreeNode root){
    	Stack<TreeNode> s = new Stack<TreeNode>();
    	while(root != null || !s.isEmpty()){
    		if(root != null){
    			s.push(root);
    			root = root.left;
    		}else{
    			root = s.pop();
    			System.out.print(root.val + " ");
    			root = root.right;
    		}
    	}
    }
    
    public static void postOrderIter(TreeNode root){
    	Stack<TreeNode> s = new Stack<TreeNode>();
    	TreeNode last = null;
    	while(root != null || !s.isEmpty()){
    		if(root != null){
    			s.push(root);
    			root = root.left;
    		}else{
    			TreeNode t = s.peek();
    			if(t.right != null && t.right != last){
    				root = t.right;
    			}else{
    				s.pop();
    				System.out.print(t.val + " ");
    				last = t;
    			}
    		}
    	}
    } 
    
    public static int maxPathSum(TreeNode root) {
        if(root == null){
            return 0;
        }
        int[] max = {0};
        helper(root, max);
        
        return max[0];
    }
    
    public static void getMax(TreeNode root, int val, int[] max){
        if(root == null){
            max[0] = Math.max(val, max[0]);
            return;
        }
        getMax(root.left, val + root.val, max);
        getMax(root.right, val + root.val, max);
    }
    
    public static int getMaxPathForNode(TreeNode root){
        if(root == null){
            return 0;
        }
        int[] l = {0};
        int[] r = {0};
        getMax(root.left, 0, l);
        getMax(root.right, 0, r);
        
        return l[0] + r[0] + root.val;
    }
    
    public static void helper(TreeNode root, int[] max){
        
        int cur = getMaxPathForNode(root);
        max[0] = Math.max(cur, max[0]);
        
        if(root.left != null){
            helper(root.left, max);
        }
        
        if(root.right != null){
            helper(root.right, max);
        }
    }
    
    public TreeNode LCAinBST(TreeNode root, int val1, int val2){
    	if(root == null){
    		return null;
    	}
    	
    	if(val1 > root.val && val2 > root.val){
    		return LCAinBST(root.right, val1, val2);
    	}
    	
    	if(val1 < root.val && val2 < root.val){
    		return LCAinBST(root.left, val1, val2);
    	}
    	
    	return root;
    }
    
    public static int getDepth(TreeNode root, int target){
    	int[] depth = {0};
    	getDepthOfNode(root, target, 0, depth);
    	
    	return depth[0];
    }
    
    public static void getDepthOfNode(TreeNode root, int target, int depth, int[] res){
    	if(root == null){
    		return;
    	}
    	
    	if(root.val == target){
    		res[0] = depth;
    		return;
    	}
    	
    	if(root.left != null){
    		getDepthOfNode(root.left, target, depth + 1, res);
    	}
    	
    	if(root.right != null){
    		getDepthOfNode(root.right, target, depth + 1, res);
    	}
    }
    
    public static TreeNode getParent(TreeNode node){
    	return null;
    }
    
    public static TreeNode findNode(TreeNode root, int target){
    	return null;//same shit
    }
    
    public static TreeNode LCA(TreeNode root, TreeNode node1, TreeNode node2){
    	int h1 = getDepth(root, node1.val);
    	int h2 = getDepth(root, node2.val);
    	
    	node1 = findNode(root, node1.val);
    	node2 = findNode(root, node1.val);
    	
    	while(h1 != h2){
    		if(h1 > h2){
    			node1 = getParent(node1);
    			h1--;
    		}else{
    			node2 = getParent(node2);
    			h2--;
    		}    		
    	}
    	
    	while(node1 != node2){
    		node1 = getParent(node1);
    		node2 = getParent(node2);
    	}
    	
    	return node1;
    }
    
    public int maxRectangle(int[][] m){
    	int max = 0;
    	int temp = 0;
    	
    	for(int i = 1; i <= m.length; i++){
    		for(int j = 1; j <= m[0].length; j++){
    			temp = i*j;
    			loop: 
    			for(int k = 0; k < m.length; k++){
    				for(int l = 0; l < m[0].length; j++){
    					if(isAllOnes(m, k, l, i, j)){
    						max = Math.max(temp, max);
    					}else{
    						break loop;
    					}
    				}
    			}
    		}
    	}
    	return max;
    }
    
    public boolean isAllOnes(int[][] m, int i, int j, int len_i, int len_j){
    	if(i + len_i > m.length || j + len_j > m[0].length){
    		return false;
    	}
    	for(int k = i; k < i + len_i; k++){
    		for(int l = j; k < j + len_j; l++){
    			if(m[k][l] != 1){
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public int partition(int[] a, int beg, int end){
    	int j = beg - 1;
    	int val = a[end];
    	for(int i = beg; i < end; i++){
    		if(a[i] < val){
    			j++;
    			swap(a, i, j);
    		}
    	}
    	swap(a, j + 1, end);
    	return j + 1;//j for qSort
    }
    
    public void swap(int[] a, int i, int j){
    	int temp = a[i];
    	a[i] = a[j];
    	a[j] = temp;
    }
    
    public int knapsack01(int[] vals, int[] weights, int capacity){
    	return 0;//m[i][j] = Math.max(m[i - 1][j], vals[i - 1] + m[i - 1][j - weights[i - 1]]);
    }
    
	public static void main(String[] args){
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.right = new TreeNode(7);
		
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		
		//printByLevel(root);
		//flatten2(root);
		//printByLevel(root);
		//root.right.right = new TreeNode(7);
		//root.left.left = new TreeNode(3);
		//root.left.right = new TreeNode(4);
		//root.left.right = new TreeNode(6);
		//root.left.left = new TreeNode(15);
		//root.right = new TreeNode(3);
		//root.right.right = new TreeNode(5);
		
		//root.right.left = new TreeNode(4);
		//root.left.left = new TreeNode(3);
		//root.right.right.right.right = new TreeNode(6);
		//root.right.right.right.right.left = new TreeNode(9);
		//System.out.println(getDepth(root, 1));
		//flatten(root);
		//printTree(root);
		//flatten(root);
		//printByLevel(root);
		//ArrayList<TreeNode> a = new  ArrayList<TreeNode>();
		//toArray(root, a);
		//ArrayList<ArrayList<Integer>> res = getLevels(root); 
		//System.out.println(postorderTraversal(root));		
		//System.out.println(pathSum(root, 1));	
		//BFS(root, new LinkedList<TreeNode>());	
		/*char[][] c = new char[5][5];
		c[0] = "OXXOX".toCharArray();
		c[1] = "XOOXO".toCharArray();
		c[2] = "XOXOX".toCharArray();
		c[3] = "OXOOO".toCharArray();
		c[4] = "XXOXO".toCharArray();
		solve(c);
		for(int i = 0; i < c.length; i++){
			System.out.println(Arrays.toString(c[i]));	
		}*/
		
		//int[] depth = {0};
		//getDepthOfNode(root, 1, 0, depth);
		
		//System.out.println(depth[0]);
		//preOrder(root);
	}
}
