import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;


public class FB {
	
	public static boolean halfToSamePerson(int[] values){
		if(values.length == 0){
			return false;
		}
		
		if(values.length == 1){
			return true;
		}
		
		int counter = 0;
		int majority_element = 0;//belongs to the person with the most number of ccs
		for(int val : values){
			if(counter == 0){
				majority_element = val;
				counter++;
			}else if(isSamePerson(majority_element, val)){
				counter++;
			}else{
				counter--;
			}
		}
		int num = 0;
		
		for(int val : values){
			if(isSamePerson(val, majority_element)){
				num++;
			}
		}
		
		return num >= values.length/2;
	}
	
	public static boolean isSamePerson(int num1, int num2){
		return true;
	}
	
	public static int[] maxAndMinWithLeastNumOfComparisons(int[] values){// 3n/2 comparisons
		int min = values[0];
		int max = values[0];
		
		for(int i = (values.length%2 == 0) ? 0 : 1; i < values.length; i+=2){
			if(values[i] > values[i + 1]){
				min = Math.min(min, values[i + 1]);
				max = Math.max(max, values[i]);
			}else{
				max = Math.max(max, values[i + 1]);
				min = Math.min(min, values[i]);				
			}
		}
		int[] res = {max, min}; 
		return res;
	}
	
	public static int binarySearchIter(int[] vals, int key, int beg, int end){
		while(beg <= end){
			int mid = (beg + end)/2;
			if(vals[mid] == key){
				return mid;
			}else{
				if(vals[mid] < key){
					beg = mid + 1;
				}else{
					end = mid - 1;
				}
			}
		}
		return -1;
	}
	
	public static int[] rotateArray(int[] a, int shift){
		int len = a.length;
		int[] res = new int[len];
		for(int i = 0; i < len; i++){
			res[i] = a[(i + shift)%len];
		}		
		
		return res;
	}
	
	public static int findPivotInRotArr(int[] A){
		int beg = 0;
		int end = A.length - 1;
		
		while(beg < end){
			int mid = (beg + end)/2;
			if(A[mid] > A[mid + 1]){
				return mid;
			}
			if(A[beg] <= A[mid]){
				beg = mid + 1;
			}else{
				end = mid;
			}
		}		
		
		return -1;
	}
	
	public static int firstFailedBuild(ArrayList<Boolean> list){
		int beg = 0;
		int end = list.size() - 1;
		while(beg < end){
			int mid = end - (end - beg)/2;
			if(list.get(mid) == false && list.get(mid - 1) == true){
				return mid;
			}else{
				if(list.get(mid) == false){
					end = mid;
				}else{
					beg = mid;
				}
			}
		}
		return -1;
	}
	
	public static int[] sum3(int[] vals){
		int[] res = new int[3];
		for(int i = 0; i < vals.length; i++){
			int cur = vals[i];
			
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			for(int j = 0; j < vals.length; j++){
				if(i != j){
					if(map.containsKey(vals[j])){
						res[0] = i;
						res[1] = map.get(vals[j]);
						res[2] = j;
						
						return res;
					}else{
						map.put(cur - vals[j], j);
					}
				}
			}
		}
		res[0] = -1;
		res[1] = -1;
		res[2] = -1;	
		return res;
	}
	
	public static boolean isSubset(int[] a, int[] b){
		HashMap<Integer, Integer> mapA = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> mapB = new HashMap<Integer, Integer>();
		for(int i = 0; i < a.length; i++){
			if(mapA.containsKey(a[i])){
				int val = mapA.get(a[i]);
				mapA.put(a[i], val++);
			}else{
				mapA.put(a[i], 1);
			}
		}
		
		for(int i = 0; i < b.length; i++){
			if(mapB.containsKey(b[i])){
				int val = mapB.get(b[i]);
				mapB.put(b[i], ++val);
			}else{
				mapB.put(b[i], 1);
			}
		}
		boolean check = true;
		for(Entry<Integer, Integer> entry : mapB.entrySet()){
			int key = entry.getKey();
			int val = entry.getValue();
			check &= (mapA.containsKey(key) && mapA.get(key) >=val);
		}
		
		return check;
	}
	
	public int largestSubseqSum(int[] vals){
		int max_here = 0;
		int max_sofar = Integer.MIN_VALUE;
		for(int i = 0; i < vals.length; i++){
			max_here = Math.max(max_here + vals[i], 0);
			max_sofar = Math.max(max_sofar, max_here);
		}
		return max_sofar;
	}
	
	public static String lookAndSay(int n){
		if(n == 0){
			return "1";
		}
		String prev = "1";
		String next = "";
		
		for(int j = 0; j < n; j++){
			next = "";
			for(int i = 0; i < prev.length();){
				char c = prev.charAt(i);
				int counter = 0;
				while(i < prev.length() && prev.charAt(i) == c){
					counter++;
					i++;
				}
				next += counter + "" + c;
			} 
			
			prev = next;
		}
		
		return next;
		
	}
	
	static class ListNode{
		int val;
		ListNode next;
		ListNode(int val){
			this.val = val;
		}
	}
	
	public static void insertIntoCircularList(ListNode node, int val){
		ListNode cur = node;
		ListNode next = node.next;
		while(true){
			if(val >= cur.val && val <= next.val){
				ListNode in = new ListNode(val);
				cur.next = in;
				in.next = next;
				break;
			}
			if(val >= cur.val && val >= next.val && cur.val >= next.val || val <= cur.val && val <= next.val && cur.val >= next.val){
				ListNode in = new ListNode(val);
				cur.next = in;
				in.next = next;
				break;
			}
			cur = next;
			next = next.next;			
		}
	}
	
	static class TreeNode{
		int val;
		TreeNode right;
		TreeNode left;
		TreeNode(int val){
			this.val = val;
		}
	}
	
	public static void printTreeLvlByLvl(TreeNode root){
		int depth = getMaxDepth(root);
		for(int i = 0; i < depth; i++){
			printTreeLvl(root, i, 0);
			System.out.println(" || " + i + " level");
		}
	}
	
	public static void printTreeLvl(TreeNode root, int lvl, int k){
		if(k == lvl){
			System.out.print(root.val + " ");
			return;
		}
		
		if(root.left != null){
			printTreeLvl(root.left, lvl, k + 1);		
		}
		
		if(root.right != null){
			printTreeLvl(root.right, lvl, k + 1);
		}
	}
	
	public static int getMaxDepth(TreeNode root){
		if(root == null){
			return 0;
		}
		
		int left = getMaxDepth(root.left);
		int right = getMaxDepth(root.right);
		
		return Math.max(left, right) + 1;
	}
	
	public static int[] mergeArrays(int[] a, int[] b){
		int[] res = new int[a.length + b.length];
		int i = 0;
		int j = 0;
		int k = 0;
		while(i != a.length || j != b.length){
			if(i != a.length && j != b.length){
				if(a[i] < b[j]){
					res[k] = a[i];
					i++;
					k++;
				}else{
					res[k] = b[j];
					j++;
					k++;
				}
			}
			
			if(i == a.length){
				res[k] = b[j];
				j++;
				k++;
			}
			
			if(j == b.length){
				res[k] = a[i];
				i++;
				k++;
			}
		}
		
		return res;
	}
	
	public static ArrayList<Integer> getAllPrimes(int n){//resheto
		ArrayList<Integer> res = new ArrayList<Integer>();
		res.add(1);
		if(n < 2){
			return res;
		}
		HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		
		for(int i = 2; i <= n; i++){
			map.put(i, true);
		}
		int p = 2;
		for(int i = 2; i <= n; i++){			
			
			for(int j = p; j <= n; j += p){
				map.put(j, false);
			}			
			
			for(int j = p; j <= n; j++){
				if(map.get(j) == true){
					res.add(j);
					p = j;
					break;
				}
			}
		}
		
		return res;		
	}
	
	public static int binarySearchInCircArray(int[] a, int target){
		int beg = 0;
		int end = a.length - 1;
		while(beg <= end){
			int mid = end - (end - beg)/2;
			if(a[mid] == target){
				return mid;
			}else{
				if(a[beg] < a[mid]){
					if(target >= a[beg] && target < a[mid]){
						end = mid - 1; 								
					}else{
						beg = mid + 1;
					}
				}else{
					if(target >= a[mid] && target <= a[end]){
						beg = mid + 1;
					}else{
						end = mid - 1;
					}
				}
			}
		}
		return -1;
	}
	
	public static int getMax(int[] vals, int beg, int end){
		if(beg == end){
			return vals[beg];
		}
		if(beg == end - 1){
			return Math.max(vals[beg], vals[end]);
		}
		int mid = end - (end - beg)/2;
		return Math.max(getMax(vals, beg, mid), getMax(vals, mid + 1, end));
	}
	
	public static String binarySum(String a, String b){
		if(a.length() < b.length()){
			String temp = a;
			a = b;
			b = temp;
		}
		String res = "";
		int r = 0;
		for(int i = a.length() - 1; i >= 0; i--){
			if(i >= a.length() - b.length()){
				int temp = Character.getNumericValue(a.charAt(i)) + Character.getNumericValue(b.charAt(i - a.length() + b.length())) + r;
				if(temp < 2){
					res += temp;
					r = 0;
				}
				if(temp == 2){
					res += 0;
					r = 1;
				}
				if(temp == 3){
					res += 1;
					r = 1;
				}
			}else{
				int temp = Character.getNumericValue(a.charAt(i)) + r;
				if(temp < 2){
					res += temp;
					r = 0;
				}
				if(temp == 2){
					res += 0;
					r = 1;
				}
				if(temp == 3){
					res += 1;
					r = 1;
				}
			}			
		}
		if(r != 0){
			res += r;
		}
		
		return new StringBuffer(res).reverse().toString();
	} 
	
	
	public static ArrayList<String> permutateString(String s){
		ArrayList<String> res = new ArrayList<String>();
		permutateStringHelper(res, s.toCharArray(), 0);
		
		return res;
	}
	
	public static double pow(double x, int n){
		if(x == 0){
			return 0;
		}
		if(n == 0){
			return 1;
		}
		
		if(n > 0){
			for(int i = 0; i < n - 1; i++){
				x *= x;
			}
			return x;
		}else{
			for(int i = 0; i < Math.abs(n) - 1; i++){
				x *= x;
			}			
			return 1/x;
		}
	}

	public static void permutateStringHelper(ArrayList<String> ar, char[] c, int k){
		for(int i = k; i < c.length; i++){
			swap(c, i, k);
			permutateStringHelper(ar, c, k + 1);
			swap(c, i, k);
		}
		
		if(k == c.length - 1){
			String res = "";
			for(char i : c){
				res += i;
			}
			ar.add(res);
		}
	}
	
	public static void swap(char[] c, int i, int j){
		char temp = c[i];
		c[i] = c[j];
		c[j] = temp;
	}
	
	public static double power(double x, int n){
		if(n == 0){
			return 1;
		}
		
		double v = power(x, n/2);
		
		if(n%2 == 0){
			return v*v;
		}else{
			return v*v*x;
		}
	}
	
	public static ArrayList<String> getAnagrams(ArrayList<String> strings){
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		for(String s : strings){
			char[] c = s.toCharArray();
			Arrays.sort(c);
			String key = new String(c);
			if(map.containsKey(key)){
				map.get(key).add(s);
			}else{
				ArrayList<String> ar = new ArrayList<String>();
				ar.add(s);
				map.put(key, ar);
			}
		}
		ArrayList<String> res = new ArrayList<String>();
		for(Entry<String, ArrayList<String>> entry : map.entrySet()){
			if(entry.getValue().size() > 1){
				for(String s : entry.getValue()){
					res.add(s);
				}
			}
		}
		
		return res;
	}	
	
	public static ListNode reverseListReq(ListNode head){
		if(head == null || head.next == null){
			return head;
		}
		
		ListNode temp = head.next;
		head.next = null;
		ListNode res = reverseListReq(temp);
		temp.next = head;
		return res;
	}
	
	public static ListNode reverseList(ListNode head){
		if(head == null || head.next == null){
			return head;
		}
		ListNode prev = null;
		ListNode cur = head;
		ListNode next = head.next;
		while(cur != null){
			cur.next = prev;
			prev = cur;
			cur = next;
			if(next != null){
				next = next.next;
			}
		}
		return prev;
	}
	
	public static void printList(ListNode head){
		while(head != null){
			System.out.print(head.val + " ");
			head = head.next;
		}
	}
	
	public static String reverseWords(String sentence){
		String[] words = sentence.split(" ");
		for(int i = 0; i < words.length; i++){
			words[i] = reverseStringReq(words[i]);
		}		
				
		String res = "";
		for(String word : words){
			res += word + " ";
		}
		
		return res;
	}
	
	public static void reverseWordsInCharArray(char[] c){
		int beg = 0;
		int end = 0;
		while(end < c.length){
			while(end < c.length && c[end] != ' '){
				end++;
			}
			reverseCharArray(c, beg, end - 1);
			beg = end + 1;
			end = end + 1;
		}
	}
	
	public static boolean match(String a, String b, int n){
		return (LCSubstring(a, b) >= n);
	}
	
	public static int LCSubstring(String a, String b){
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
		
		int len = 0;
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m[0].length; j++){
				if(m[i][j] > len){
					len = m[i][j];
				}
			}
		}
		
		return len;
	}
	
	public static void reverseCharArray(char[] c, int beg, int end){
		int len = end - beg + 1;
		for(int i = 0; i < len/2; i++){
			char temp = c[i + beg];
			c[i + beg] = c[beg + len - 1 - i];
			c[beg + len - 1 - i] = temp;
		}
	}
	
	public static String reverseStringReq(String s){
		if(s.length() < 2){
			return s;
		}
		
		return reverseStringReq(s.substring(1)) + s.charAt(0);
	}
	
	public static int[] shift(int[] a, int shift){
		int[] b = new int[a.length];
		for(int i = 0; i < a.length; i++){
			b[i] = a[(i + shift)%a.length];
		}
		return b;
	}
	
	public static double medianOfTwoArrays(int[] a, int[] b){
		int n = a.length + b.length;
		double m = (double) getKth(a, 0, a.length, b, 0, b.length, n/2);
		if(n%2 == 0){
			m = (m + (double) getKth(a, 0, a.length, b, 0, b.length, n/2 - 1)) * 0.5;
		}
		
		return m;
	}
	
	public static int getKth(int[] a, int bega, int enda, int[] b, int begb, int endb, int k){
		if(bega == enda){
			return b[begb + k];
		}
		
		if(begb == endb){
			return a[bega + k];
		}
		
		//int mida = enda - (enda - bega)/2;
		//int midb = endb - (endb - begb)/2;
		
		int mida = (bega + enda)/2;
		int midb = (begb + endb)/2;
		
		int lena = mida - bega;
		int lenb = midb - begb;
		
		if(a[mida] <= b[midb]){
			if(k <= lena + lenb){
				return getKth(a, bega, enda, b, begb, midb, k);
			}else{
				return getKth(a, mida + 1, enda, b, begb, endb, k - lena - 1);
			}
		}else{
			if(k <= lena + lenb){
				return getKth(a, bega, mida, b, begb, endb, k);
			}else{
				return getKth(a, bega, enda, b, midb + 1, endb, k - lenb - 1);
			}
		}
	}

	public boolean biasedFlip() {
		String binaryProbabilityInString = "01010000011011001";    // binary decimal for 0.31416
      
		for (int i=0; i<binaryProbabilityInString.length(); i++) {
	        boolean isTrue = randomFlip();
	        if (isTrue && binaryProbabilityInString.charAt(i) == '1') {
	        	continue;
	        } else if (!isTrue && binaryProbabilityInString.charAt(i) == '0') {
	        	continue;
	        } else if (!isTrue && binaryProbabilityInString.charAt(i) == '1') {
	        	return true;    // when the probability of the toss is < 0.31416
	        } else if (isTrue && binaryProbabilityInString.charAt(i) == '0') {
	        	return false;    // when the probability of the toss is > 0.0.31416
	        }
       	}
		return true;
	}
	
	public boolean randomFlip(){
		
		return new Random().nextInt(2) == 1;
	}
	
	public static String toBinary(double n){
		String res = "";
		int length = 16;
		while(length >= 0){
			n *= 2.0;
			res += (n + "").split("\\.")[0];
			if(n > 1){
				n = n - 1.0;
			}
			length--;
		}
		
		return res;
	}
	
	public static String toBinary(int n){
		int res = 0;
		int exp = 1;
		
		while(n > 0){
			int r = n%2;
			n /= 2;
			res += exp*r;
			exp *= 10;
		}
		
		return res + "";
	}	
	
	public boolean fairFromBiased(){
		boolean b1 = biasedFlip();
		boolean b2 = biasedFlip();
		
		if(!b1 && b2){// p(1-p) prob
			return true;
		}
		
		if(b1 && !b2){//(1-p)p prob
			return false;
		}
		
		return fairFromBiased();
	}
	
	public static double divide(double a, double b){
		
		return Math.exp(Math.log(a) - Math.log(b));//TODO
	} 
	
	public static int[] findSwappedElems(int[] vals){
		int a = -1;
		int b = - 1;
		int i = 0;
		for(; i < vals.length - 1; i++){
			if(vals[i] > vals[i + 1]){
				a = vals[i];
				break;
			}
		}
		for(int j = i + 1; j < vals.length - 1; j++){
			if(vals[j] > vals[j + 1]){
				b = vals[j + 1];
				break;
			}
		}
		
		if(b == - 1){
			b = vals[i + 1];
		}
		
		return new int[]{a, b};
	}
	
	static class Point{
        int i;
        int j;
        Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
    public static boolean exist(char[][] board, String word) {
        ArrayList<Point> points = new ArrayList<Point>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == word.charAt(0)){
                    points.add(new Point(i, j));
                }
            }
        }
        for(Point p : points){
            boolean[][] b = new boolean[board.length + 2][board[0].length + 2];
            for(int i = 0; i < b.length; i++){
                for(int j = 0; j < b[0].length; j++){
                    b[i][j] = true;
                }
            }
            if(existFromThisCell(board, word, p.i, p.j, b, 0)){
                return true;
            }
        }
        return false;
    }
    
    public static boolean existFromThisCell(char[][] board, String word, int i, int j, boolean[][] visited, int index){
        if(i >= board.length || j >= board[0].length || i <= -1 || j <= -1){
            return index == word.length();
        }
        
        if(index == word.length()){
            return true;
        }
        
        boolean check = word.charAt(index) == board[i][j];
        
        visited[i + 1][j + 1] = false;
        if(check == false){
        	visited[i + 1][j + 1] = true;
        	return false;
        }
        boolean check1 = visited[i + 1 + 1][j + 1] && existFromThisCell(board, word, i + 1, j, visited, index + 1);
        visited[i + 1][j + 1] = true;
        boolean check2 = visited[i - 1 + 1][j + 1] && existFromThisCell(board, word, i - 1, j, visited, index + 1);
        visited[i + 1][j + 1] = true;
        boolean check3 = visited[i + 1][j + 1 + 1] && existFromThisCell(board, word, i, j + 1, visited, index + 1);
        visited[i + 1][j + 1] = true;
        boolean check4 = visited[i + 1][j - 1 + 1] && existFromThisCell(board, word, i, j - 1, visited, index + 1);
        visited[i + 1][j + 1] = true;
        return check&&(check1||check2||check3||check4);
        
    }
	

    
	public static void main(String[] args){
		//TreeNode root = new TreeNode(1);
		//root.left = new TreeNode(2);
		//root.right = new TreeNode(3);
		//root.left.left = new TreeNode(4);
		//root.left.right = new TreeNode(5);
		//System.out.println(maxAndMinWithLeastNumOfComparisons(values)[0]);
		//System.out.println(maxAndMinWithLeastNumOfComparisons(values)[1]);
		//System.out.println(binarySearchIter(values, 4, 0, 7));
		//values = rotateArray(values, 7);
		/*for(int i : values){
			System.out.print(i + " ");
		}
		System.out.println("");
		System.out.print(findPivotInRotArr(values));*/
		//ListNode node = new ListNode(1);
		//node.next = new ListNode(2);
		//node.next.next = new ListNode(3);
		//node.next.next.next = new ListNode(4);
		//char[] c = "abc defg hi j".toCharArray();
		//reverseWordsInCharArray(c);
		//System.out.print(c);
	}
}
