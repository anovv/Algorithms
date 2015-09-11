import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;


public class ULTULTTEST {
	// longest ariphm progression - Map<int difference, list<pairs>>

	public static void main(String[] args){
		System.out.println(division(3, 2));	
	}
	
	public static int division(int dividend, int divisor){
		//2 << 1 = 4
		//4 >> 1 = 2
		//1. Keep  multiply 2 (<<1) to the divisor, 
		//	until it is greater than the dividend. Store the times of shift operation.
		
		//2. if dividend > divisor, then dividend = dividend - divisor/2(>>1). 
		//  Until dividend< original divisor. Store the   result.
		
		//3. Output the result.
		long a = Math.abs(dividend);
		long b = Math.abs(divisor);
		long counter = 1;
		while(a > b){
			b = b << 1;
			counter = counter << 1;
		}
		
		long res = 0;
		while(a > Math.abs(divisor)){
			while(a >= b){
				a -= b;
				res = res + counter;
			}
			b = b >> 1;
			counter = counter >> 1;
		}
		
		int sign = (dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0) ? 1 : - 1; 
		
		return (int)(sign * res);
	}
	
	public static void shuffle(int[] a){
		for(int i = 0; i < a.length; i++){
			int j = i + new Random().nextInt(a.length - i);
			swap(a, i, j);
		}
	}
	
	public static String[] toStrAr(String s){
		ArrayList<String> a = new ArrayList<String>();
		int i = 0;
		int j = 0;
		while(j < s.length()){
			while(j < s.length() && s.charAt(j) != '+' && s.charAt(j) != '-' && s.charAt(j) != '*' && s.charAt(j) != '/'){				
				j++;
			}
			a.add(s.substring(i, j));
			if(j < s.length()){
				a.add(s.substring(j, j + 1));
			}
			j++;
			i = j;
		}
		
		String[] res = new String[a.size()];
		for(int k = 0; k < res.length; k++){
			res[k] = a.get(k);
		}
		
		return res;
	}
	
	public static int evalExpr(String exp){
		String[] str = toStrAr(exp);
		Stack<String> s = new Stack<String>();
		for(int i = 0; i < str.length; i++){
			if(str[i].equals("*")){
				String res = (Integer.parseInt(s.pop()) * Integer.parseInt(str[i + 1])) + "";				
				s.push(res);
				i++;
			}else if(str[i].equals("/")){
				String res = (Integer.parseInt(s.pop()) / Integer.parseInt(str[i + 1])) + "";				
				s.push(res);
				i++;
			}else{
				s.push(str[i]);
			}
		}
		
		
		ArrayList<String> ar = new ArrayList<String>(s);

		Stack<String> st = new Stack<String>();
		
		for(int i = 0; i < ar.size(); i++){
			if(ar.get(i).equals("+")){
				String res = (Integer.parseInt(st.pop()) + Integer.parseInt(ar.get(i + 1))) + "";				
				st.push(res);
				i++;
			}else if(ar.get(i).equals("-")){
				String res = (Integer.parseInt(st.pop()) - Integer.parseInt(ar.get(i + 1))) + "";				
				st.push(res);
				i++;
			}else{
				st.push(ar.get(i));
			}
		}
		
		return Integer.parseInt(st.pop());
	}
	
	
	public boolean findWord(char[][] c, int x, int y, String s, boolean[][] nv){
		boolean check = (s.length() > 0) && (c[x][y] == s.charAt(0));
		nv[x][y] = false;
		if(!check){
			nv[x][y] = true;
			return false;
		}
		
		if(check && s.length() == 1){
			nv[x][y] = true;
			return true;
		}
		
		boolean c1 = isValidCoord(c, x, y + 1) && nv[x][y + 1] && findWord(c, x, y + 1, s.substring(1), nv);
		nv[x][y] = true;

		boolean c2 = isValidCoord(c, x, y - 1) && nv[x][y - 1] && findWord(c, x, y - 1, s.substring(1), nv);
		nv[x][y] = true;

		boolean c3 = isValidCoord(c, x + 1, y) && nv[x + 1][y] && findWord(c, x + 1, y, s.substring(1), nv);
		nv[x][y] = true;

		boolean c4 = isValidCoord(c, x - 1, y) && nv[x - 1][y] && findWord(c, x - 1, y, s.substring(1), nv);
		nv[x][y] = true;
		
		return (check)&&(c1||c2||c3||c4);
	}
	
	public boolean isValidCoord(char[][] c, int x, int y){
		return (x < c.length && y < c[0].length && x >= 0 && y >= 0);
	}
	
	public boolean hasWayOut(char[][] c, int x, int y, boolean[][] nv){
		if((x == 0 || x == c.length - 1 || y == 0 || y == c[0].length - 1) && c[x][y] == 'O'){
			nv[x][y] = true;
			return true;
		}
		
		boolean check = c[x][y] == 'O';
		nv[x][y] = false;
		if(!check){
			nv[x][y] = true;
			return false;
		}
		
		boolean c1 = nv[x + 1][y] && c[x + 1][y] == 'O' && hasWayOut(c, x + 1, y, nv);
		nv[x][y] = true;

		boolean c2 = nv[x - 1][y] && c[x - 1][y] == 'O' && hasWayOut(c, x - 1, y, nv);
		nv[x][y] = true;

		boolean c3 = nv[x][y + 1] && c[x][y + 1] == 'O' && hasWayOut(c, x, y + 1, nv);
		nv[x][y] = true;

		boolean c4 = nv[x][y - 1] && c[x][y - 1] == 'O' && hasWayOut(c, x, y - 1, nv);
		nv[x][y] = true;
		
		return (check)&&(c1||c2||c3||c4);
	}
	
	static class GraphNode{
		int val;
		ArrayList<GraphNode> neighbors;
		GraphNode(int val){
			neighbors = new ArrayList<GraphNode>();
			this.val = val;
		}
	}
	
	
	
	public static GraphNode clone(GraphNode head){
		GraphNode res = new GraphNode(head.val);
		Queue<GraphNode> q1 = new LinkedList<GraphNode>();
		Queue<GraphNode> q2 = new LinkedList<GraphNode>();
		q1.add(head);
		q2.add(res);
		Set<GraphNode> visited = new HashSet<GraphNode>();
		visited.add(head);
		
		while(!q1.isEmpty()){
			GraphNode node = q1.poll();
			GraphNode cur = q2.poll();
			visited.add(node);
			ArrayList<GraphNode> neighbors = node.neighbors;
			for(GraphNode neighbor : neighbors){
				if(!visited.contains(neighbor)){
					GraphNode n = new GraphNode(neighbor.val);
					cur.neighbors.add(n);
					q1.add(neighbor);
					q2.add(n);
					visited.add(neighbor);
				}
			}
		}
		
		return res;
	}
	
	public static ArrayList<Integer> BFS(GraphNode head){
		ArrayList<Integer> res = new ArrayList<Integer>();
		Queue<GraphNode> q = new LinkedList<GraphNode>();
		q.add(head);
		Set<GraphNode> visited = new HashSet<GraphNode>();
		while(!q.isEmpty()){
			GraphNode node = q.poll();
			res.add(node.val);
			visited.add(node);
			ArrayList<GraphNode> neighbors = node.neighbors;
			
			for(GraphNode neighbor : neighbors){
				if(!visited.contains(neighbor)){
					q.add(neighbor);
					visited.add(neighbor);
				}
			}
		}
		
		return res;
	}
	
	public static String nextPermutation(String s){
		char[] c = s.toCharArray();
		
		int k = -1;
		int l = 0;
		for(int i = 0; i < c.length - 1; i++){
			if(c[i] < c[i + 1]){
				k = i;
			}
		}
		
		if(k == -1){
			return s;//reverse s
		}
		
		for(int i = 0; i < c.length; i++){
			if(c[i] > c[k]){
				l = i;
			}
		}
		
		char temp = c[l];
		c[l] = c[k];
		c[k] = temp;
		
		int a = k + 1;
		int b = s.length() - 1;
		
		while(a < b){
			char t = c[a];
			c[a] = c[b];
			c[b] = t;
			a++;
			b--;
		}
		
		return new String(c);
	}
	
	static class MyQueue{
		Stack<Integer> in;
		Stack<Integer> out;
		
		MyQueue(){
			in = new Stack<Integer>();
			out = new Stack<Integer>();
		}
		
		void add(int elem){
			in.push(elem);
		}
		
		int poll(){
			if(out.isEmpty()){
				while(!in.isEmpty()){
					out.push(in.pop());
				}
			}
			return out.pop();
		}
	}
	
	static class MyStack{
		Queue<Integer> in;
		Queue<Integer> out;
		
		MyStack(){
			in = new LinkedList<Integer>();
			out = new LinkedList<Integer>();			
		}
		
		void push(int elem){
			in.add(elem);
		}
		
		int pop(){
			while(in.size() != 1){
				out.add(in.poll());
			}
			int elem = in.poll();
			Queue<Integer> temp = in;
			in = out;
			out = temp;
			return elem;
		}
	}
	
	public static ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> s){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if(s.size() == 0){
			res.add(new ArrayList<Integer>());
			return res;
		}
		
		int head = s.get(0);
		ArrayList<Integer> rest = new ArrayList<Integer>(s.subList(1, s.size()));
		
		for(ArrayList<Integer> t : subsets(rest)){
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(head);
			temp.addAll(t);
			res.add(temp);
			res.add(t);
		}
		
		return res;
	}
	
	public static String reverseStr(String s){
		if(s.length() < 2){
			return s;
		}
		
		return reverseStr(s.substring(1)) + s.charAt(0);
	}
	
	public static int knapsack(int[] vals, int[] weights, int capacity){
		int[][] m = new int[weights.length + 1][capacity + 1];
		m[0][0] = 0;
		
		for(int i = 1; i < m.length; i++){
			m[i][0] = 0;
		}
		
		for(int i = 1; i < m[0].length; i++){
			m[0][i] = 0;
		}
		
		for(int i = 1; i < m.length; i++){
			for(int j = 1; j < m[0].length; j++){
				if(j >= weights[i - 1]){
					m[i][j] = Math.max(m[i - 1][j], vals[i - 1] + m[i - 1][j - weights[i - 1]]);
				}else{
					m[i][j] = m[i - 1][j];
				}
			}
		}
		
		return m[m.length - 1][m[0].length - 1];
	}
	
	public static boolean partitionProblem(int[] a, int target){
		boolean[][] b = new boolean[a.length + 1][target + 1];
		
		b[0][0] = true;
		
		for(int i = 1; i < b.length; i++){
			b[i][0] = true; 
		}
		
		for(int i = 1; i < b[0].length; i++){
			b[0][i] = false;
		}
		
		for(int i = 1; i < b.length; i++){
			for(int j = 1; j < b[0].length; j++){
				if(j >= a[i - 1]){
					b[i][j] = b[i - 1][j] || b[i - 1][j - a[i - 1]];
				}else{
					b[i][j] = b[i - 1][j];
				}
			}
		}
		
		return b[b.length - 1][b[0].length - 1];
	}
	
	public static int maxSteal(int[] a){
		int[] m = new int[a.length];
		m[0] = a[0];
		m[1] = Math.max(a[0], a[1]);
		
		for(int i = 2; i < m.length; i++){
			m[i] = Math.max(m[i - 1], a[i] + m[i - 2]);
		}
		
		return m[m.length - 1];
	}
	
	public static int coinChange(int[] vals, int value){
		int[] m = new int[value + 1];
		m[0] = 0;
		
		for(int i = 1; i < m.length; i++){
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < vals.length; j++){
				if(i >= vals[j]){
					min = Math.min(min, m[i - vals[j]]);
				}
			}
			m[i] = min + 1;
		}
		
		return m[value];
	}
	
	public static int kadane(int[] a){
		int max_here = a[0];
		int max_sofar = a[0];
		
		for(int i = 1; i < a.length; i++){
			max_here = Math.max(max_here + a[i], a[i]);
			max_sofar = Math.max(max_sofar, max_here);
		}
		
		return max_sofar;
	}
	
	public static int kadane2d(int[][] a){
		int[] temp = new int[a[0].length];
		int res = 0;
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < temp.length; j++){
				temp[j] = 0;
			}
			for(int j = i; j < a[0].length; j++){
				for(int k = 0; k < temp.length; k++){
					temp[k] += a[j][k];					
				}
				res = Math.max(res, kadane(temp));
			}
		}
		
		return res;
	}
	
	public static String LCSubstring(String a, String b){
		int[][] m = new int[a.length() + 1][b.length() + 1];
		
		for(int i = 0; i < m.length; i++){
			m[i][0] = 0;
		}
		
		for(int i = 0; i < m[0].length; i++){
			m[0][i] = 0;
		}
		
		for(int i = 1; i < m.length; i++){
			for(int j = 1; j < m[0].length; j++){
				if(a.charAt(i) == b.charAt(j)){
					m[i][j] = m[i - 1][j - 1] + 1;
				}else{
					m[i][j] = Math.max(m[i - 1][j], m[i][j - 1]);
				}
			}
		}
		
		int x = a.length();
		int y = b.length();
		
		String s = "";
		
		while(x != 0 && y != 0){
			if(m[x][y] == m[x - 1][y]){
				x--;
			}else if(m[x][y] == m[x][y - 1]){
				y--;
			}else{
				s += a.charAt(x - 1);
				x--;
				y--;
			}
		}
		
		return new StringBuffer(s).reverse().toString();
	}
	
	public static String LCSubstr(String a, String b){
		int[][] m = new int[a.length() + 1][b.length() + 1];
		
		for(int i = 0; i < m.length; i++){
			m[i][0] = 0;
		}
		
		for(int i = 0; i < m[0].length; i++){
			m[0][i] = 0;
		}
		
		for(int i = 1; i < m.length; i++){
			for(int j = 1; j < m[0].length; j++){
				if(a.charAt(i) == b.charAt(j)){
					m[i][j] = m[i - 1][j - 1] + 1;
				}else{
					m[i][j] = 0;
				}
			}
		}
		
		int max = 0;
		int x = 0;
		int y = 0;
		
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m[0].length; j++){
				if(m[i][j] > max){
					max = m[i][j];
					x = i;
					y = j;
				}
			}
		}
		
		String res = "";
		while(m[x][y] != 0){
			res += a.charAt(x - 1);
			x--;
			y--;
		}
		
		return new StringBuffer(res).reverse().toString();
	}
	
	public static int[] minMax(int[] a){
		int min = a[0];
		int max = a[0];
		
		for(int i = (a.length%2 == 0) ? 0 : 1; i < a.length; i +=2){
			if(a[i] > a[i + 1]){
				if(a[i] > max){
					max = a[i];
				}
				if(a[i + 1] < min){
					min = a[i + 1];
				}
			}else{
				if(a[i + 1] > max){
					max = a[i + 1];
				}
				if(a[i] < min){
					min = a[i];
				}
			}
		}
		
		return new int[]{min, max};
	}
	
	public static int LIS(int [] a){
		int[] m = new int[a.length];
		m[0] = 1;
		for(int i = 1; i < m.length; i++){
			int max = 0;
			for(int j = i; j < m.length; j++){
				if(a[i] < a[j] && a[j] > max){
					max = a[j];
				}				
			}
			m[i] = max + 1;
		}
		
		int res = 0;
		for(int i = 0; i < m.length; i++){
			res = Math.max(res, m[i]);
		}
		
		return res;
	}
	
	public static int getMajorElem(int[] a){
		int counter = 0;
		int major = a[0];
		
		for(int i = 0; i < a.length; i++){
			if(counter == 0){
				major = a[i];
			}else if(major == a[i]){
				counter++;
			}else{
				counter--;
			}
		}
		
		counter = 0;
		for(int i : a){
			if(major == i){
				counter++;
			}
		}
		
		return (counter >= a.length/2) ? major : -1;
	}
	
	public static int findPivot(int[] a, int beg, int end){
		if(beg > end){
			return -1;
		}
		
		if(beg == end){
			return beg;
		}
		
		int mid = (beg + end)/2;
		
		if(a[mid] > a[mid + 1]){
			return mid;
		}
		
		if(a[beg] <= a[mid]){
			return findPivot(a, mid + 1, end);
		}else{
			return findPivot(a, beg, mid);
		}
	}
	
	public static void shift(int[] a, int n){
		n = n%a.length;
		reverse(a, 0, a.length - 1);
		reverse(a, 0, a.length - n - 1);
		reverse(a, a.length - n, a.length - 1);
	}
	
	public static void reverse(int[] a, int beg, int end){
		while(beg < end){
			swap(a, beg, end);
			beg++;
			end--;
		}
	}
	
	public static int[] sum3(int[] vals){
		int[] res = {-1, -1, -1};
		for(int i = 0; i < vals.length; i++){
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			for(int j = 0; j < vals.length; j++){
				if(i != j){
					if(map.containsKey(vals[i] - vals[j])){
						res[0] = i;
						res[1] = j;
						res[2] = map.get(vals[i] - vals[j]);
						return res;
					}else{
						map.put(vals[j], j);
					}
				}
			}
		}
		
		return res;
	}
	
	public static String toBinary(int n){
		int res = 0;
		int exp = 1;
		
		while(n > 0){
			res += exp * n%2;
			n /= 2;
			exp *= 10;
		}
		
		return res + "";
	}
	
	public static boolean fairFromBiased(){
		boolean b1 = biasedFromFair(0);
		boolean b2 = biasedFromFair(0);
		
		if(b1 && !b2){
			return true;
		}
		
		if(!b1 && b2){
			return false;
		}
		
		return fairFromBiased();
	}
	
	public static boolean biasedFromFair(int p){
		String binary = ""; // 1/p to binary
		boolean b = fairFlip();
		for(int i = 0; i < binary.length(); i++){
			if(b && binary.charAt(i) == '1'){
				continue;
			}
			
			if(!b && binary.charAt(i) == '0'){
				continue;
			}
			
			if(!b && binary.charAt(i) == '1'){
				return false;
			}
			
			if(b && binary.charAt(i) == '0'){
				return true;
			}
			
		}
		
		return true;
	}
	
	public static boolean fairFlip(){
		return new Random().nextBoolean();
	}
	
	public static void minPathRec(int[][] m, int x, int y, int[] res, int cur){
		if(x == m.length - 1 && y == m[0].length - 1){
			cur += m[x][y];
			res[0] = Math.min(res[0], cur);
			return;
		}
		
		if(x == m.length - 1){
			minPathRec(m, x, y + 1, res, cur + m[x][y]);
		}else if(y == m[0].length - 1){
			minPathRec(m, x + 1, y, res, cur + m[x][y]);			
		}else{
			minPathRec(m, x, y + 1, res, cur + m[x][y]);
			minPathRec(m, x + 1, y, res, cur + m[x][y]);
		}
	}
	
	public static ListNode reverseRec(ListNode head){
		if(head == null || head.next == null){
			return head;
		}
		
		ListNode next = head.next;
		head.next = null;
		ListNode res = reverseRec(next);
		next.next = head;
		
		return res;
	}
	
	public static Stack<Integer> sortStack(Stack<Integer> s){
		Stack<Integer> p = new Stack<Integer>();
		while(!s.isEmpty()){
			int temp = s.pop();
			while(!p.isEmpty() && p.peek() > temp){
				s.push(p.pop());
			}
			p.push(temp);
		}
		
		return p;
	}
	
	public static ListNode deleteDups(ListNode head){
		if(head == null || head.next == null){
			return head;
		}
		
		ListNode a = head;
		while(a != null){
			removeNode(a);
			a = a.next;
		}
		
		return head;
	}
	
	public static void removeNode(ListNode head){
		int val = head.val;
		ListNode p = head;
		ListNode c = head.next;
		while(c != null){
			while(c != null && c.val != val){
				p = c;
				c = c.next;
			}
			
			while(c != null && c.val == val){
				c = c.next;
			}
			
			p.next = c;
		}
	}
	
	static class ListNode{
		int val;
		ListNode next;
		ListNode(int val){
			this.val = val;
		}
	}
	
	public static int qSelect(int[] a, int beg, int end, int k){
		if(beg == end){
			return a[beg];
		}
		
		int pivot = partition1(a, beg, end);
		if(pivot == k){
			return a[k];
		}
		
		if(pivot < k){
			return qSelect(a, pivot + 1, end, k);
		}else{
			return qSelect(a, beg, pivot - 1, k);
		}
	}
	
	public static void qSort(int[] a, int beg, int end){
		if(beg >= end){
			return;
		}
		
		int pivot = partition2(a, beg, end);
		
		qSort(a, beg, pivot);
		qSort(a, pivot + 1, end);
	}
	
	public static int partition1(int[] a, int beg, int end){
		int val = a[end];
		int j = beg - 1;
		for(int i = beg; i < end; i++){
			if(a[i] < val){
				j++;
				swap(a, i, j);
			}
		}
		swap(a, end, j + 1);
		return j + 1;//j for qSort
	}
	
	public static int partition2(int[] a, int beg, int end){
		int i = beg - 1;
		int j = end + 1;
		int val = a[beg];
		while(true){
			i++;
			while(i <= end && a[i] < val){
				i++;
			}
			j--;
			while(j >= beg && a[j] > val){
				j--;
			}
			 	 	
			if(i < j){
				swap(a, i, j);
			}else{
				return j;
			}
		}
	}
	
	public static void swap(int[] a, int i, int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static void BFS(TreeNode root){
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while(!q.isEmpty()){
			TreeNode t = q.poll();
			System.out.print(t + " ");
			if(t.left != null){
				q.add(t.left);
			}
			
			if(t.right != null){
				q.add(t.right);
			}
		}
	}
	
	static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int val){
			this.val = val;
		}
	}
	
	public static int[] multAllExcPos(int[] a){
		int[] pre = new int[a.length];
		int[] af = new int[a.length];
		pre[0] = 1;
		af[af.length - 1] = 1;
		for(int i = 1; i < a.length; i++){
			pre[i] = pre[i - 1] * a[i - 1];
		}
		
		for(int i = af.length - 2; i >= 0; i--){
			af[i] = af[i + 1] * a[i + 1];
		}
		
		for(int i = 0; i < a.length; i++){
			a[i] = pre[i] * af[i];
		}
		
		return a;
	}
	
	public static String toExcel(int num){
		int base = 26;
		String res = "";
		while(num > 0){
			char c = (char) ('A' + num%base - 1); 
			res += c;
			num /= base;
		}
		return new StringBuffer(res).reverse().toString();
	}
	
	
	public static void relocate(int[] a){
		for(int i = 0; i < a.length; i++){
			a[i] += (a[a[i]]%a.length) * a.length;
		}
		
		for(int i = 0; i < a.length; i++){
			a[i] /= a.length;
		}
	}
	
	public static ArrayList<ArrayList<Integer>> getSS(int num){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if(num == 0){
			res.add(new ArrayList<Integer>());
			return res;
		}
		
		for(int i = 1; i <= num; i++){
			for(ArrayList<Integer> a : getSS(num - i)){
				a.add(i);
				res.add(a);
			}
		}
		
		return res;
	}
}
