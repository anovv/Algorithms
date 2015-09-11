
public class LinkedListCycle {
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
	
	public static boolean hasCycle(ListNode head) {
        if(head == null){
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        boolean res = false;
        //if(fast.next != null){
        while(fast!= null && fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast.val == slow.val){
            	res = true;
            	break;
            }
        }
        
        
        return res;
    }
	
	public static ListNode detectCycle(ListNode head) {
		if(head == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        
        ListNode res = null;
        
        while(fast != null && fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast.val == slow.val){
                slow = head;
                fast = fast.next;
                break;
            }
        }
        
        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
            if(fast.val == slow.val){
                res = slow;
                break;
            }
        }
        
        return res;
    }
	
	public static ListNode LLtoArray(int[] arr){
		ListNode head = new ListNode(arr[0]);
		ListNode p = head;
		for(int i = 1; i < arr.length; i++){
			p.next = new ListNode(arr[i]);
			p = p.next;
		} 
		
		return head;
	}
	
	public static ListNode test(ListNode head){
		ListNode l = head;
		l = null;
		return head;
		
	}
	
	public static ListNode deleteDuplicates(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode p = null;
        ListNode c = head;
        ListNode n = head.next;
        while(n != null){
            if(c != null && n != null && c.val == n.val){
                while(n != null && c.val == n.val){
                    c.next = n.next;
                    n = n.next;
                }
                if(p != null){
                    p.next = n;
                }else{
                    c = null;
                }
            }else{
                p = c;
                c = n;
                n = n.next;
            }
        }
        
        return head;
    }
	
	static ListNode merge(ListNode l1, ListNode l2){
		if(l1 == null){
			return l2;
		}
		
		if(l2 == null){
			return l1;
		}
		
		ListNode head = (l1.val > l2.val) ? l2 : l1;	
		
		if(l1.val > l2.val){
			ListNode temp = l2.next;			
			head.next = merge(l1, temp);
		}else{
			ListNode temp = l1.next;
			head.next = merge(temp, l2);
		}
		
		return head;
		
	}
	
	public static void main(String[] args){
		int[] a = {1, 3, 5, 7};
		int[] b = {2, 9};
    	ListNode l1 = LLtoArray(a);
    	ListNode l2 = LLtoArray(b);
		
    	printList(merge(l1, l2));
    	//printList(merge(t,v));    	
    }
}
