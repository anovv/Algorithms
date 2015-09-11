import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Sortings {
	
	public static void insertionSort(int[] a){
		for(int i = 1; i < a.length; i++){
			int key = a[i];
			int j = i - 1;
			while(j >= 0 && a[j] > key){
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = key;
		}
	}
	
	
	public static void bubbleSort(int[] a){
		for(int i = a.length - 1; i >=0 ; i--){
			int key = a[i];
			int j = i + 1;
			while(j < a.length && a[j] < key){
				a[j - 1] = a[j];
				j++;
			}
			a[j - 1] = key;
		}
	}
	
	public static void selectionSort(int[] a){
		for(int start = 0; start < a.length; start++){
			int min = Integer.MAX_VALUE;
			int index = 0;
			for(int i = start; i < a.length; i++){
				if(a[i] < min){
					min = a[i];
					index = i;
				}
			}
			for(int i = index; i > start; i--){
				a[i] = a[i - 1];
			}
			a[start] = min;
		}
	}
	
	public static void radixSort(int[] a){
		
	}
	
	/*public static void quickSort(int[] a, int beg, int end){
		if(beg >= end){
			return;
		}
		int pivot = dumbPartition(a, beg, end, beg);
		quickSort(a, beg, pivot);
		quickSort(a, pivot + 1, end);
	}
	
	public static int hoarePartition(int[] a, int beg, int end, int pivot){
		int i = beg - 1;
		int j = end + 1;
		int x = a[pivot];
		//int x = a[beg];
		while(true){
			i++;
			
			while(i < end && a[i] < x){
				i++;
			}
			
			j--;
			
			while(j > beg && a[j] > x){
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

	public static int[] mergeSort(int[] a){
		if(a.length <= 1){
			return a;
		}
		
		int beg = 0;
		int end = a.length - 1;
		
		int mid = (beg + end)/2;
		
		int[] left = new int[mid - beg + 1];
		int[] right = new int[end - mid];
		
		for(int i = beg; i <= mid; i++){
			left[i - beg] = a[i];
		}
		
		for(int i = mid + 1; i <= end; i++){
			right[i - mid - 1] = a[i];
		}
		//int mid = (a.length - 1)/2;
		//int[] left = Arrays.copyOfRange(a, 0, mid + 1);
		//int[] right = Arrays.copyOfRange(a, mid + 1, a.length);		
		
		return merge(mergeSort(left), mergeSort(right));
	}
	
	public static int[] merge(int[] a, int[] b){
		int[] res = new int[a.length + b.length];
		int i = a.length - 1;
		int j = b.length - 1;
		int k = res.length - 1;
		while(i != -1 || j != -1){
			if(i != -1 && j != -1){
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
			}else if(j == -1){
				res[k] = a[i];
				i--;
				k--;
			}
		}
		
		return res;
	}
	
	public static void realBubbleSort(int[] a){
		
	}
	
	
	public static int randPartition(int[] a, int beg, int end){
		Random r = new Random();
		int pivot = beg + r.nextInt(end - beg + 1);
		int x = a[pivot];
		System.out.println("x " + x);
		int i = beg - 1;
		int j = end + 1;
		
		while(true){
			i++;
			while(i < end && a[i] < x){
				i++;
			}
			j--;
			while(j > beg && a[j] > x){
				j--;
			}
			if(i < j){
				swap(a, i, j);
			}else{
				return j;
			}
		}		
	}
	
	public static void randQuickSort(int[] a, int beg, int end){
		if(beg >= end){
			return;
		}
		
		int pivot = randPartition(a, beg, end);
		randQuickSort(a, beg, pivot);
		randQuickSort(a, pivot + 1, end);
	}
	
	public static int quickSelect(int[] a, int beg, int end, int k){
		if(beg == end){
			return a[beg];
		}
		Random r = new Random();
		int p = beg + r.nextInt(end - beg + 1);
		
		int pivot = lomutoPartition(a, beg, end);
		if(k == pivot){
			return a[k];
		}
		if(k < pivot){
			return quickSelect(a, beg, pivot - 1, k);
		}else{
			return quickSelect(a, pivot + 1, end, k);
		}
	}
	
	public static void partition(int[] a, int val){
		int i = -1;
		int j = a.length;
		while(true){
			i++;
			while(i < a.length && a[i] < val){
				i++;
			}
			j--;
			while(j > - 1 && a[j] > val){
				j--;
			}
			if(i < j){
				swap(a, i, j);
			}else{
				return;
			}
		}
	}
	
	public static int dumbPartition(int[] a, int beg, int end, int pivot){
		ArrayList<Integer> less = new ArrayList<Integer>();
		ArrayList<Integer> more = new ArrayList<Integer>();
		Random r = new Random();
		//int pivot = beg + r.nextInt(end - beg + 1);
		//int pivot = beg;
		int x = a[pivot];
		for(int i = beg; i <= end; i++){
			if(a[i] < x){
				less.add(a[i]);
			}else{
				more.add(a[i]);
			}
		}
		ArrayList<Integer> total = new ArrayList<Integer>();
		total.addAll(less);
		total.addAll(more);
		for(int i = beg; i <= end; i++){
			a[i] = total.get(i - beg);
		}	
		
		return less.size() - 1;
	}
	
	public static int lomutoPartition(int[] a, int beg, int end){
		int j = beg - 1;
		int val = a[end];
		for(int i = beg; i < end; i++){
			if(a[i] < val){
				j++;
				swap(a, i, j);
			}
		}
		swap(a, j + 1, end);
		return j + 1;
	}
	
	*/
	
	public static void swap(int[] a, int i, int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;	
	}
	
	public static void quickSort(int[] a, int beg, int end){
		if(beg >= end){
			return;
		}
		int pivot = partition2(a, beg, end);
		quickSort(a, beg, pivot);
		quickSort(a, pivot + 1, end);
	}
	
	public static int partition1(int[] a, int beg, int end){
		int j = beg - 1;
		int val = a[end];
		for(int i = beg; i < end; i++){
			if(a[i] < val){
				j++;
				swap(a, i, j);
			}
		}
		swap(a, j + 1, end);
		return j + 1;// j for qSort
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
	
	public static int quickSelect(int[] a, int beg, int end, int k){
		if(beg == end){
			return a[beg];
		}
		
		int pivot = partition1(a, beg, end);
		if(pivot == k){
			return a[k];
		}
		if(k < pivot){
			return quickSelect(a, beg, pivot - 1, k);
		}else{
			return quickSelect(a, pivot + 1, end, k);
		}
	}
	
	public static int[] mergeSort(int[] a){
		if(a.length == 1){
			return a;
		}
		
		int beg = 0;
		int end = a.length - 1;
		int mid = (beg + end)/2;
		
		List<Integer> l1 = new ArrayList<Integer>();
		List<Integer> l2 = new ArrayList<Integer>();
		
		for(int i = beg; i <= mid; i++){
			l1.add(a[i]);
		}
		
		for(int i = mid + 1; i <= end; i++){
			l2.add(a[i]);
		}
		
		int[] i1 = new int[l1.size()];
		for(int i = 0; i < i1.length; i++){
			i1[i] = l1.get(i);
		}
		

		int[] i2 = new int[l2.size()];
		for(int i = 0; i < i2.length; i++){
			i2[i] = l2.get(i);
		}
		int[] a1 = mergeSort(i1);
		int[] a2 = mergeSort(i2);
		
		return merge(a1, a2);
		
	}
	
	public static int[] merge(int[] a1, int[] a2){
		int[] res = new int[a1.length + a2.length];
		int k = res.length - 1;
		int i = a1.length - 1;
		int j =  a2.length - 1;
		
		while(i >= 0 || j >= 0){
			if(i >= 0 && j >= 0){
				if(a1[i] > a2[j]){
					res[k] = a1[i];
					i--;
				}else{
					res[k] = a2[j];
					j--;
				}
			}else if(i == -1){
				res[k] = a2[j];
				j--;
			}else{
				res[k] = a1[i];
				i--;
			}
			k--;
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		
		int[] a = {1, 4, 5000, 3, 2, 99};
		
		int[] b = mergeSort(a);
		
		System.out.print(Arrays.toString(b));
		
		/*Random r = new Random();
		int len = 100000000;
		int[] a = new int[len];
		for(int i = 0; i < a.length; i++){
			a[i] = r.nextInt(20);
		}
		int[] b = new int[len];
		for(int i = 0; i < a.length; i++){
			b[i] = a[i];
		}
		long t0 = System.currentTimeMillis();
		quickSort(a, 0, a.length - 1);
		System.out.println("quicksort: " + (System.currentTimeMillis() - t0));
		long t1 = System.currentTimeMillis();
		mergeSort(a);
		System.out.println("mergesort: " + (System.currentTimeMillis() - t1));
		*/
		//int[] a = {4, 3, 2, 1, 5, 6, 8, 7, 9};
		//int[] b = a;
		//randQuickSort(a, 0, a.length - 1);
		//bubbleSort(a);
		//quickSort(a, 0, a.length - 1);
		//System.out.println(lomutoPartition(a, 0, a.length - 1));
		//System.out.println(myPartition(a, 0, a.length - 1));
		//System.out.println(hoarePartition(b, 0, b.length - 1, 3));
		//System.out.println(quickSelect(a, 0, a.length - 1, 2));
		
		//System.out.println(Arrays.toString(b));//System.out.println(Arrays.toString(mergeSort(a)));
		//System.out.println(Arrays.toString(Arrays.copyOfRange(a, 0, 1)));
	}
}
