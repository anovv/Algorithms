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


public class UltimateTest {

	public static void main(String[] args) {
		// damn son where'd ya find this
	}
	
	public static ListNode multiply(ListNode a, ListNode b){
		ListNode res = new ListNode(0);
		
		int exp = 0;
		while(b != null){
			ListNode m = mult(a, b);
			for(int i = 0; i < exp; i++){
				ListNode l = new ListNode(0);
				l.next = m;
				m = l;
			}
			res = add(res, m);
			exp++;
			b = b.next;			
		}
		
		return res;
				
	}
	
	public static ListNode mult(ListNode head, ListNode t){
		int val = t.val;
		ListNode res = new ListNode(0);
		ListNode l = res;
		res.next = new ListNode(0);
		
		int r = 0;
		
		while(head != null){
			int temp = val * head.val + r;
			if(temp > 9){
				r = temp/10;
				temp = temp%10;
			}else{
				r = 0;
			}
			
			res.next = new ListNode(temp);
			res = res.next;
			head = head.next;
		}
		
		if(r > 0){
			res.next = new ListNode(r);
		}
		
		return l.next;
	}
	
	public static ListNode add(ListNode l1, ListNode l2){
		int r = 0;
        ListNode res = new ListNode(0);
        ListNode l = res;
        while(l1 != null || l2 != null){
            if(l1 != null && l2 != null){
                int val = l1.val + l2.val + r;
                if(val > 9){
                    r = val/10;
                    val = val%10;
                }else{
                    r = 0;
                }
                res.next = new ListNode(val);
                res = res.next;
                l1 = l1.next;
                l2 = l2.next;
            }else if(l1 == null){
                int val = l2.val + r;
                if(val > 9){
                    r = val/10;
                    val = val%10;
                }else{
                    r = 0;
                }
                res.next = new ListNode(val);
                res = res.next;
                l2 = l2.next;
            }else if(l2 == null){
                int val = l1.val + r;
                if(val > 9){
                    r = val/10;
                    val = val%10;
                }else{
                    r = 0;
                }
                res.next = new ListNode(val);
                res = res.next;
                l1 = l1.next;
            }
        }
        if(r != 0){
            res.next = new ListNode(r);
        }
        
        return l.next;
	}
	
	public int longetsConsecSubseq(int[] a){
		Set<Integer> set = new HashSet<Integer>();
		for(int i : a){
			set.add(i);
		}
		
		int res = 0;
		
		for(int i : a){
			int len = 1;
			int left = i - 1;
			int right = i + 1;
			
			while(set.contains(left)){
				len++;
				set.remove(left);
				left--;
			}
			
			while(set.contains(right)){
				len++;
				set.remove(right);
				right--;
			}
			
			res = Math.max(res, len);
		}
		
		return res;
	}
		
	public static int maxRectangle(int[][] m){
		int[][] ones = new int[m.length][m[0].length];
		
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m[0].length; j++){
				ones[i][j] = 0;
			}
		}
		
		for(int i = 0; i < m.length; i++){
			ones[i][0] = (m[i][0] == 1) ? 1 : 0;
		}
		
		for(int i = 0; i < m.length; i++){
			for(int j = 1; j < m[0].length; j++){
				if(m[i][j] == 0){
					ones[i][j] = 0;
				}else{
					ones[i][j] = ones[i][j - 1] + 1;
				}
			}
		}
		
		int res = 0;
		
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m[0].length; j++){
				int k = i - 1;
				int temp = ones[i][j];
				int min = ones[i][j];
				int height = 1;
				while(k >= 0){
					if(ones[k][j] == 0){
						break;
					}else{
						height++;
						min = Math.min(min, ones[k][j]);
						temp = Math.max(temp, min * height);
						k--;
					}
				}
				res = Math.max(temp, res); 
			}
		}
		
		return res;
		
	}	
	
	public static void dutch(int[] a){
		int j = -1;
		int k = a.length;
		for(int i = 0; i < k;){
			if(a[i] == 1){
				j++;
				swap(a, i, j);
				i++;
			}else if(a[i] == 3){
				k--;
				swap(a, i, k);
			}else{
				i++;
			}
		}
	}
	
	public static void printLVLbLVL(TreeNode root){
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		int curLevel = 1;
		int nextLevel = 0;
		while(!q.isEmpty()){
			TreeNode cur = q.poll();
			curLevel--;
			System.out.print(cur.val + " ");
			if(curLevel == 0){
				System.out.println();
			}
			
			if(cur.left != null){
				q.add(cur.left);
				nextLevel++;
			}
			
			if(cur.right != null){
				q.add(cur.right);
				nextLevel++;
			}
			
			if(curLevel == 0){
				curLevel = nextLevel;
				nextLevel = 0;
			}
		}
	}
	
	public static ArrayList<ArrayList<Integer>> getSS(int num){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if(num == 0){
			res.add(new ArrayList<Integer>());
			return res;
		}
		
		for(int i = 1; i <= num; i++){
			for(ArrayList<Integer> t : getSS(num - i)){
				t.add(i);
				res.add(t);
			}
		}
		
		return res;
	}
	
	//TODO atof
	
	public static boolean anaStrStr(String a, String b){
		if(a.length() < b.length()){
			String temp = a;
			a = b;
			b = temp;
		}
		HashMap<Character, Integer> h_b = new HashMap<Character, Integer>();
		for(int i = 0; i < b.length(); i++){
			if(h_b.containsKey(b.charAt(i))){
				int count = h_b.get(b.charAt(i));
				h_b.put(b.charAt(i), count + 1);
			}else{
				h_b.put(b.charAt(i), 1);
			}
		}
		HashMap<Character, Integer> h_a;
		
		int i = 0;
		int j = b.length() - 1;
		
		while(j < a.length()){
			h_a = new HashMap<Character, Integer>();
			for(int k = i; k <= j; k++){
				if(h_a.containsKey(a.charAt(k))){
					int count = h_a.get(a.charAt(k));
					h_a.put(a.charAt(k), count + 1);
				}else{
					h_a.put(a.charAt(k), 1);
				}
			}
			boolean check = true;
			for(Map.Entry<Character, Integer> entry : h_a.entrySet()){
				char c = entry.getKey();
				int l = entry.getValue();
				check &= h_b.containsKey(c) && (h_b.get(c) == l);
			}
			if(check){
				return true;
			}
			i++;
			j++;
		}
		
		return false;
	}
		
	public static boolean isValidCoord(char[][] c, int i, int j){
		return !(i < 0 || i > c.length - 1 || j < 0 || j > c[0].length - 1);
	}
	
	public static boolean fWHelper(char[][] c, String word, int x, int y, boolean[][] nv){		
				
		boolean check = (word.length() > 0) && word.charAt(0) == c[x][y];
		nv[x][y] = false;
		if(!check){
			nv[x][y] = true;			
			return false;
		}
		
		if(check && word.length() == 1){
			nv[x][y] = true;	
			return true;
		}
		boolean check1 = isValidCoord(c, x, y + 1) && nv[x][y + 1] && fWHelper(c, word.substring(1), x, y + 1, nv);
		nv[x][y] = true;
		boolean check2 = isValidCoord(c, x + 1, y) && nv[x + 1][y] && fWHelper(c, word.substring(1), x + 1, y, nv);
		nv[x][y] = true;
		boolean check3 = isValidCoord(c, x, y - 1) && nv[x][y - 1] && fWHelper(c, word.substring(1), x, y - 1, nv);
		nv[x][y] = true;
		boolean check4 = isValidCoord(c, x - 1, y) && nv[x - 1][y] && fWHelper(c, word.substring(1), x - 1, y, nv);
		nv[x][y] = true;
		return check&&(check1||check2||check3||check4);
		
	}

	public static ArrayList<ArrayList<Integer>> zigaZaga(int[][] m){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		int i = 0;
		for(; i < m[0].length; i++){
			res.add(getDiag(m, i, 0, i));
		}
		
		for(int j = 1; j < m.length; j++){
			res.add(getDiag(m, m[0].length - 1, j, i));
			i++;
		}
		
		return res;
	}
	
	public static ArrayList<Integer> getDiag(int[][] m, int x, int y, int i){
		ArrayList<Integer> res = new ArrayList<Integer>();
		if(i%2 == 0){
			while(x < m.length && y < m[0].length && x >= 0 && y >= 0){
				res.add(m[x][y]);
				x--;
				y++;
			}
		}else{
			int a = x;
			int b = y;
			while(a < m.length && b < m[0].length && a >= 0 && b >= 0){
				a--;
				b++;
			}			
			a++;
			b--;			
			while(a < m.length && b < m[0].length && a >= 0 && b >= 0){
				res.add(m[a][b]);
				a++;
				b--;
			}
		}
		
		return res;
	}
	
	//(x + y*z)/z = y    provided x and y is less than z.
	//(x + y*z)%z = x    provided x and y is less than z.
	
	public void relocate(int[] a){// a[i] = a[a[i]];
		int len = a.length;
		
		for(int i = 0; i < len; i++){
			a[i] += a[a[i]]*len;
		}
		
		for(int i = 0; i < len; i++){
			a[i] /= len;
		}
	}
		
	public int[] multAllExcPos(int[] a){
		int[] pre = new int[a.length];
		int[] af = new int[a.length];
		pre[0] = 1;
		af[af.length - 1] = 1;
		for(int i = 1; i < pre.length; i++){
			pre[i] = pre[i - 1] * a[i - 1];
		}
		
		for(int i = af.length - 2; i >= 0; i--){
			af[i] = af[i + 1] * a[i + 1];
		}
		
		int[] res = new int[a.length];
		
		for(int i = 0; i < a.length; i++){
			res[i] = af[i] * pre[i];
		}
		
		return res;
	}
	
	public static String removeDups(String s){
		char[] c = s.toCharArray();
		for(int i = 0; i < c.length; i++){
			removeChar(c, i + 1, c.length - 1, c[i]);
		}
		int[] alph = new int[26];
		for(int i = 0; i < alph.length; i++){
			alph[i] = -1;
		}
		int len = 0;
		for(int i = 0; i < c.length; i++){
			int t = alph[c[i] - 'a'];
			if(t == -1){
				len++;
			}else{
				break;
			}
			alph[c[i] - 'a'] = 1;
		}
		String res = "";
		for(int i = 0; i < len; i++){
			res += c[i];
		}
		return res;
	}
	
	public static void removeChar(char[] c, int beg, int end, char target){
		int offset = 0;
		for(int i = beg; i + offset <= end; i++){
			c[i] = c[i + offset];
			if(c[i] == target){
				offset++;
				i--;
			}
		}
	}
	
	static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int val){
			this.val = val;
		}
	}
	
	public static void BFS(TreeNode root){
		if(root == null){
			return;
		}
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while(!q.isEmpty()){
			TreeNode t = q.poll();
			System.out.print(t.val + " ");
			if(t.left != null){
				q.add(t.left);
			}
			if(t.right != null){
				q.add(t.right);
			}
		}
	}
	
	public static TreeNode construct(int[] vals){
		if(vals.length == 0){
			return null;
		}
		int index = 0;
		TreeNode root = new TreeNode(vals[index++]);
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while(!q.isEmpty()){
			if(index == vals.length){
				return root;
			}
			
			TreeNode t = q.poll();
			if(t.left == null){
				t.left = new TreeNode(vals[index++]);
				q.add(t.left);
			}
			
			if(index == vals.length){
				return root;
			}
			
			if(t.right == null){
				t.right = new TreeNode(vals[index++]);
				q.add(t.right);
			}
		}
		return root;
	}
	
	
	public static int partition2(int[] vals, int beg, int end){
		int i = beg - 1;
		int j = end + 1;
		int val = vals[beg];
		while(true){
			i++;
			while(i <= end && vals[i] < val){
				i++;
			}
			j--;
			while(j >= beg && vals[j] > val){
				j--;
			}
			if(i < j){
				swap(vals, i, j);
			}else{
				return j;
			}
		}
	}
	
	public static int partition1(int[] vals, int beg, int end){
		int val = vals[end];
		int j = beg - 1;
		for(int i = beg; i < end; i++){
			if(vals[i] < val){
				j++;
				swap(vals, i, j);
			}
		}
		swap(vals, j + 1, end);
		return j + 1;//j for qSort
	}
	
	public void qSort(int[] a, int beg, int end){
		if(beg >= end){
			return;
		}
		int pivot = partition1(a, beg, end);
		qSort(a, beg, pivot);
		qSort(a, pivot + 1, end);
	}	
	
	public int qSelect(int[] a, int k, int beg, int end){
		if(beg > end){
			return -1;
		}
		
		if(beg == end){
			return a[beg];
		}
		
		int pivot = partition1(a, beg, end);
		
		if(pivot == k){
			return a[k];
		}
		
		if(pivot < k){
			return qSelect(a, k, pivot + 1, end);
		}else{
			return qSelect(a, k, beg, pivot - 1);			
		}		
	}
	
	public static int[] mergeSort(int[] a, int beg, int end){
		if(beg == end){
			return new int[]{a[beg]}; 
		}
		
		int mid = (beg + end)/2;
		
		return merge(mergeSort(a, beg, mid), mergeSort(a, mid + 1, end));		
	}
	
	public static int[] merge(int[] a, int[] b){
		int[] res = new int[a.length + b.length];
		
		int i = a.length - 1;
		int j = b.length - 1;
		int k = res.length - 1;
		
		while(i != - 1 || j != -1){
			if(i != -1 && j != - 1){
				if(a[i] > b[j]){
					res[k] = a[i];
					i--;
					k--;
				}else{
					res[k] = b[j];
					j--;
					k--;
				}
			}else if(i == -1){
				res[k] = b[j];
				j--;
				k--;
			}else{
				res[k] = a[i];
				i--;
				k--;
			}
		}
		
		return res;
	}
	
	public static void swap(int[] a, int i, int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
			
	public static void deleteDups(ListNode head){
		if(head == null || head.next == null){
			return;
		}
		ListNode a = head;
		while(a != null){
			ListNode p = a;
			ListNode c = a.next;
			while(c != null){
				int temp = a.val;
				while(c != null && c.val == temp){
					c = c.next;
				}
				p.next = c;
				p = c;
				if(c != null){
					c = c.next;
				}
			}
			a = a.next;
		}
	}
	
	public Stack<Integer> sortStack(Stack<Integer> s){
		Stack<Integer> r = new Stack<Integer>();
		while(!s.isEmpty()){
			int temp = s.pop();
			while(!r.isEmpty() && r.peek() > temp){
				s.push(r.pop());
			}
			r.push(temp);
		}
		
		return r;
	}
	
	static class ListNode{
		int val;
		ListNode next;
		public ListNode(int val){
			this.val = val;
		}		
	}
	
	public static void printList(ListNode head){
		while(head != null){
			System.out.print(head.val + " ");
			head = head.next;
		}
	}
	
	public static ListNode reverseIter(ListNode head){
		if(head == null || head.next == null){
			return head;
		}
		
		ListNode p = null;
		ListNode c = head;
		while(c != null){
			ListNode temp = c.next;
			c.next = p;
			p = c;
			c = temp;
		}
		
		return p;
	}
	
	public static ListNode reverseRec(ListNode head){
		if(head == null || head.next == null){
			return head;
		}
		
		ListNode temp = head.next;
		head.next = null;
		ListNode res = reverseRec(temp);
		temp.next = head;
		return res;
	}
	
	public static int minPathRec(int[][] matrix){
		int[] min = {Integer.MAX_VALUE};
		minPathHelper(matrix, 0, 0, min, 0);
		
		return min[0];
	}
	
	public static void minPathHelper(int[][] matrix, int x, int y, int[] min, int cur){
		if(x == matrix.length - 1 && y == matrix[0].length - 1){
			cur += matrix[x][y];
			min[0] = Math.min(min[0], cur);
			return;
		}else if(x == matrix.length - 1){
			minPathHelper(matrix, x, y + 1, min, cur + matrix[x][y]);
		}else if(y == matrix[0].length - 1){
			minPathHelper(matrix, x + 1, y, min, cur + matrix[x][y]);
		}else{
			minPathHelper(matrix, x + 1, y, min, cur + matrix[x][y]);
			minPathHelper(matrix, x, y + 1, min, cur + matrix[x][y]);
		}
		
	} 
	
	public boolean biasedFromFair(int p){
		String binary = "";// p to binary
		
		for(int i = 0; i < binary.length(); i++){
			boolean b = fairFlip();
			if(b && binary.charAt(i) == '1' || !b && binary.charAt(i) == '0' ){
				continue;
			}
			
			if(!b && binary.charAt(i) == '1'){
				return true;
			}
			
			if(b && binary.charAt(i) == '0'){
				return false;
			}
		}
		
		return true;
	}
	
	public boolean fairFlip(){
		//return new Random().nextInt(2) == 1;
		return new Random().nextBoolean();
	}
	
	public boolean fairFromBiased(){
		boolean b1 = biasedFlip(); 
		boolean b2 = biasedFlip();
		
		if(!b1 && b2){
			return true;
		}
		
		if(b1 && !b2){
			return false;
		}
		
		return fairFromBiased();
	}
	
	public boolean biasedFlip(){
		return new Random().nextInt(10) == 0;
	}
	
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
	
	public int firstFailedBuild(HashMap<Integer, Boolean> builds, int beg, int end){
		if(beg >= end){
			return -1;
		}
				
		int mid = (beg + end)/2;
		
		if(builds.get(mid) == true && builds.get(mid + 1) == false){
			return mid + 1;
		}
		
		if(builds.get(beg) == true && builds.get(mid) == true){
			return firstFailedBuild(builds, mid + 1, end);
		}else{
			return firstFailedBuild(builds, beg, mid);
		}
	}
	
	public static String reverseSentence(String sentence){
		char[] c = sentence.toCharArray();
		int i = 0;
		int j = 0;
		
		while(j < c.length){
			while(j < c.length && c[j] != ' '){
				j++;
			}
			
			reverseArray(c, i, j - 1);
			j++;
			i = j;			
		}
		
		return new String(c);
	}
	
	public static void reverseArray(char[] c, int beg, int end){
		int len = end - beg + 1;
		for(int i = 0; i < len/2; i++){
			char temp = c[beg + i];
			c[beg + i] = c[beg + len - 1 - i];
			c[beg + len - 1 - i] = temp;
		}
	}
	
	public int[] sum3(int[] vals){
		int[] res = {-1, -1, -1};
		for(int i = 0; i < vals.length; i++){
			HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
			for(int j = 0; j < vals.length; j++){
				if(i != j){
					if(h.containsKey(vals[i] - vals[j])){
						res[0] = h.get(vals[i] - vals[j]);
						res[1] = i;
						res[2] = j;
						
						return res;
					}else{
						h.put(vals[j], j);
					}
				}
			}
		}
		
		return res;
	}
	
	public int binarySearchIter(int[] vals, int target){
		int beg = 0;
		int end = vals.length - 1;
		
		while(beg <= end){
			int mid = end - (end - beg)/2;
			
			if(vals[mid] == target){
				return mid;
			}
			
			if(vals[mid] < target){
				beg = mid + 1;
			}else{
				end = mid - 1;
			}
		}
		
		return -1;
	}
	
	public int[] rotateArray(int[] array, int shift){
		int[] res = new int[array.length];
		for(int i = 0; i < array.length; i++){
			res[i] = array[(i + shift)%array.length];
		}
		
		return res;
	}
	
	public static int findPivotInRot(int[] array, int beg, int end){
		if(beg > end){
			return -1;
		}
		
		if(beg == end){
			return array[beg];
		}
		
		int mid = (beg + end)/2;
		
		if(array[mid] > array[mid + 1]){
			return array[mid];
		}
		
		if(array[beg] <= array[mid]){
			return findPivotInRot(array, mid + 1, end);
		}else{
			return findPivotInRot(array, beg, mid);
		}
	}
	
	public boolean halfBelongsToSamePerson(int[] vals){
		int counter = 0;
		int major = 0;
		for(int i = 0; i < vals.length; i++){
			if(counter == 0){
				major = vals[i];
			}else if(isSamePerson(major, vals[i])){
				counter++;
			}else{
				counter--;
			}
		}
		
		counter = 0;
		
		for(int i = 0; i < vals.length; i++){
			if(isSamePerson(major, vals[i])){
				counter++;
			}
		}
		
		return counter >= vals.length;
	}
	
	
	
	public boolean isSamePerson(int a, int b){
		return true;
	}

	public int LIS(int[] a){
		int[] m = new int[a.length];
		m[0] = 1;
		for(int i = 1; i < m.length; i++){
			int max = Integer.MIN_VALUE;
			for(int j = 0; j < i; j++){
				if(a[j] < a[i] && m[j] > max){
					max = m[j];
				}
			}
			m[i] = max + 1;			
		}
		
		int max = 0;
		
		for(int i : m){
			max = Math.max(max, i);
		}
		
		return max;
		//return m[m.length - 1];
	}
	
	public int[] maxMin(int[] vals){
		int min = vals[0];
		int max = vals[0];
		
		for(int i = (vals.length%2 == 0) ? 0 : 1; i < vals.length; i+=2){
			if(vals[i] > vals[i + 1]){
				min = Math.min(min, vals[i + 1]);
				max = Math.max(max, vals[i]);
			}else{
				min = Math.min(min, vals[i]);
				max = Math.max(max, vals[i + 1]);				
			}
		}
		
		return new int[]{min, max};
	}
	
	public String LCSubseq(String a, String b){
		int[][] m = new int[a.length() + 1][b.length() + 1];
		
		for(int i = 0; i < m.length; i++){
			m[i][0] = 0;
		}
		
		for(int i = 0; i < m[0].length; i++){
			m[0][i] = 0;
		}
		
		for(int i = 1; i < m.length; i++){
			for(int j = 1; j < m[0].length; j++){
				if(a.charAt(i - 1) == b.charAt(j - 1)){
					m[i][j] = m[i - 1][j - 1] + 1;
				}else{
					m[i][j] = Math.max(m[i][j - 1], m[i - 1][j]);
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
	
	public static int[] maxSumSubarray(int[] a){
		int temp_start = 0;
		int temp_end = 0;
		int temp_sum = 0;
		int sum = 0;
		int start = 0;
		int end = 0;
		
		for(int i = 0; i < a.length; i++){
			temp_sum += a[i];
			if(temp_sum < 0){
				temp_sum = 0;
				temp_start = i + 1;
				temp_end = i + 1;
			}else{
				temp_end = i;
				if(temp_sum > sum){
					sum = temp_sum;
					start = temp_start;
					end = temp_end;
				}				
			}
		}
		
		if(temp_sum > sum){
			sum = temp_sum;
			start = temp_start;
			end = temp_end;
		}		
		
		int[] res = new int[end - start + 1];
		
		for(int i = start; i <= end; i++){
			res[i - start] = a[i];
		}
		
		return res;
	}
	
	public static String LCSubString(String a, String b){
		int[][] m = new int[a.length() + 1][b.length() + 1];
		
		for(int i = 0; i < m.length; i++){
			m[i][0] = 0;
		}
		
		for(int i = 0; i < m[0].length; i++){
			m[0][i] = 0;
		}
		
		for(int i = 1; i < m.length; i++){
			for(int j = 1; j < m[0].length; j++){
				if(a.charAt(i - 1) == b.charAt(j - 1)){
					m[i][j] = m[i - 1][j - 1] + 1;
				}else{
					m[i][j] = 0;
				}
			}
		}
		
		int max = 0;
		int x = 0;
		int y = 0;
		
		for(int i = 1; i < m.length; i++){
			for(int j = 1; j < m[0].length; j++){
				if(m[i][j] > max){
					max = m[i][j];
					x = i;
					y = j;
				}
			}
		}
		
		String s = "";
		
		while(m[x][y] != 0){
			s += a.charAt(x - 1);
			x--;
			y--;
		}
		
		return new StringBuffer(s).reverse().toString();
		
	}
	
	public int coinsChange(int[] set, int value){
		int[] m = new int[value + 1];
		
		m[0] = 0;
		
		for(int i = 1; i < m.length; i++){
			int min = 0;
			for(int j = 0; j < set.length; j++){
				if(i - set[j] >= 0){
					min = Math.min(min, m[i - set[j]]);
				}
			}
			m[i] = min + 1;
		}
		
		return m[value];
	}
	
	public int kadane(int[] A){
		int max_here = 0;
		int max_sofar = 0;
		for(int i : A){
			max_here = Math.max(max_here + i, 0);
			max_sofar = Math.max(max_here, max_sofar);
		}
		
		return max_sofar;
	}
	
	public int kadane2d(int[][] a){
		int[] temp = new int[a[0].length];
		int max = 0;
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < temp.length; j++){
				temp[j] = 0;
			}
			
			for(int j = i; j < a.length; j++){
				for(int k = 0; k < temp.length; k++){
					temp[k] += a[j][k];
				}
				max = Math.max(max, kadane(temp));
			}
		}
		
		return max;
	}
	
	public int maxSteal(int[] vals){
		int[] m = new int[vals.length];
		m[0] = vals[0];
		m[1] = Math.max(vals[0], vals[1]);
		
		for(int i = 2; i < m.length; i++){
			m[i] = Math.max(m[i - 1], vals[i] + m[i - 2]);
		}
		
		return m[m.length - 1];
	}
	
	public void paths(int[][] g, String s, ArrayList<String> res, int x, int y){
		if(y == g.length - 1 && x == g[0].length - 1){
			s += g[x][y];
			res.add(s);
			return;
		}else if (y == g.length - 1){
			paths(g, s + g[x][y], res, x + 1, y);
		}else if(x == g[0].length - 1){
			paths(g, s + g[x][y], res, x, y + 1);
		}else{
			paths(g, s + g[x][y], res, x + 1, y);
			paths(g, s + g[x][y], res, x, y + 1);
		}
	}
	
	public boolean partitionProblem(int[] vals, int target){
		boolean[][] b = new boolean[vals.length + 1][target + 1];
		b[0][0] = true;
		
		for(int i = 1; i < b.length; i++){
			b[i][0] = true;
		}
		
		for(int i = 1; i < b[0].length; i++){
			b[0][i] = false;
		}
		
		for(int i = 1; i < b.length; i++){
			for(int j = 1; j < b[0].length; j++){
				if(j >= vals[i - 1]){
					b[i][j] = b[i - 1][j] || b[i - 1][j - vals[i - 1]];
				}else{
					b[i][j] = b[i - 1][j];
				}
			}
		}
		
		return b[b.length - 1][b[0].length - 1];
	}
	
	public int knapsack01(int[] values, int[] weights, int capacity){
		int[][] m = new int[values.length + 1][capacity + 1];
		
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
					m[i][j] = Math.max(m[i - 1][j], values[i - 1] + m[i - 1][j - weights[i - 1]]);
				}else{
					m[i][j] = m[i - 1][j];
				}
			}
		}
		
		return m[m.length - 1][m[0].length - 1];
	}
	
	public String reverseRec(String a){
		if(a.length() < 2){
			return a;
		}
		
		return reverseRec(a.substring(1)) + a.charAt(0);
	}
	
	public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> a){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		if(a.isEmpty()){
			res.add(new ArrayList<Integer>());
			return res;
		}
		
		int head = a.get(0);
		
		ArrayList<Integer> rest = new ArrayList<Integer>(a.subList(1, a.size())); 
		
		for(ArrayList<Integer> t: subsets(rest)){
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(head);
			temp.addAll(t);
			res.add(t);
			res.add(temp);
		}
		
		return res;
	}
	
	static class MyStack{
		Queue<Integer> in = new LinkedList<Integer>();
		Queue<Integer> out = new LinkedList<Integer>();
		
		void push(int elem){
			in.add(elem);
		}
		
		int pop(){
			while(in.size() != 1){
				out.add(in.poll());
			}
			int res = in.poll();
			Queue<Integer> temp = in;
			in = out;
			out = temp;
			return res;
		}
	}
	
	static class MyQueue{
		Stack<Integer> in = new Stack<Integer>();
		Stack<Integer> out = new Stack<Integer>();
		
		void add(int a){
			in.push(a);
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
	
	static class Singleton{
		private static Singleton instance = null;
		private Singleton(){
			
		}
		
		public static Singleton getInstance(){
			if(instance == null){
				instance = new Singleton();
			}
			return instance;
		}
	}	
	
	public static String reverseWords(String s) {
		String res = "";
        int i = s.length() - 1;
        int j = s.length() - 1;
                
        while(j >= 0){
            while(j >= 0 && s.charAt(j) == ' '){
                j--;
            }
        	if(j != s.length() - 1 && j != -1){
        		res += ' ';
        	}
            i = j;
            while(j >= 0 && s.charAt(j) != ' '){
                j--;
            } 
            if(j >= -1 && i >= 0){
                for(int k = j + 1; k <= i; k++){
                    res += s.charAt(k);
                }
            }
            i = j;
        }
                    
        return res;
    }
}
