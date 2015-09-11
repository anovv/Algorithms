
public class LinkedListMergeSort {
	
	static class ListNode{
		int val;
		ListNode next;
		public ListNode(int val){
			this.val = val;
		}
	}
	
	public static ListNode mergeSort(ListNode head){
		if(head == null || head.next == null){
			return head;
		}
		
		ListNode[] s = split(head);
		
		ListNode l1 = s[0];
		ListNode l2 = s[1];
		
		return merge(mergeSort(l1), mergeSort(l2));
	}
	
	public static ListNode[] split(ListNode head){
		int len = 0;
		ListNode p = head;
		while(p != null){
			p = p.next;
			len++;
		}
		
		ListNode c = head;
		
		for(int i = 0; i < len - 2; i++){//FAIL!!!
			c = c.next;
		}
		
		ListNode temp = c.next;
		c.next = null;
		
		return new ListNode[]{head, temp};		
	}
	
	public static ListNode merge(ListNode a, ListNode b){
		if(a == null){
			return b;
		}
		
		if(b == null){
			return a;
		}
		
		ListNode res = null;
		if(a.val < b.val){
			res = a;
			res.next = merge(a.next, b);
		}else{
			res = b;
			res.next = merge(a, b.next);
		}
		
		return res;
	}
	
	public static void printList(ListNode head){
		while(head != null){
			System.out.print(head.val + " ");
			head = head.next;
		}
	}
	
	public static void main(String[] args){
		ListNode l = new ListNode(4);
		l.next = new ListNode(3);
		l.next.next = new ListNode(4);
		l.next.next.next = new ListNode(1);
		printList(mergeSort(l));
	}
}
