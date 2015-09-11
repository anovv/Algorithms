

public class LinkedListReorder2 {
	static class ListNode{
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
	public static void printList(ListNode head){
		while(head!=null){
			System.out.print(head.val + " ");
			head = head.next;
		}
	}
	
	public static ListNode reorderList(ListNode head) {
		if(head == null || head.next == null){
			return null;
		}
		int l = getLength(head);
		ListNode l1 = head;
		ListNode l2 = null;
		ListNode pointer = head;
		for (int i = 0; i < (l-1)/2; i++){
			pointer = pointer.next;
		}
		l2 = pointer.next;
		pointer.next = null;
		l2 = reverse(l2);
		return merge(l1, l2);
	}
	    
	public static ListNode reverse(ListNode head){
		ListNode cur = head;
		ListNode prev = null;
		while(cur != null){
			ListNode temp = cur.next;
			cur.next = prev;
			prev = cur;
			cur = temp;
		}
		return prev;
	}
	    
	public static int getLength(ListNode head){
		int length = 0;
		ListNode temp = head;
		while(temp != null){
			temp = temp.next;
			length++;
		}
		return length;
	}
	    
	public static ListNode merge(ListNode l1, ListNode l2){
		//int len1 = getLength(l1);
		//int len2 = getLength(l2);
		ListNode res = new ListNode(l1.val);
		l1 = l1.next;		
		ListNode l = res;
		int i = 0;
		while(l1 != null || l2 !=null){
			i++;
			if(i%2 == 0){
				if(l1 != null){
					res.next = new ListNode(l1.val);
					res = res.next;
					l1 = l1.next;
				}else{
					res.next = new ListNode(l2.val);
					res = res.next;
					l2 = l2.next;
				}
			}else{
				if(l2 != null){
					res.next = new ListNode(l2.val);
					res = res.next;
					l2 = l2.next;
				}else{
					res.next = new ListNode(l1.val);
					res = res.next;
					l1 = l1.next;
				}
			}
		}
		return l;
	}
	
}
