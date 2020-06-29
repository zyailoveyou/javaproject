package test;

import java.util.ArrayList;

import javax.tools.DocumentationTool.Location;

public class main {
	
	
	public static void main(String[] args){
		
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		node1.next= node2;
		node2.next = node3;
		node3.next = node4;
	    System.out.println(node1.val);
	    System.out.println(node2.val);
	    
	    
	    ListNode head = swapPairs(node1);	    	    
	    System.out.println("good");
	    
	}
	
	
    public static ListNode swapPairs(ListNode head) {
        
    	ListNode tempreturnListNode = head.next;
    	
        ListNode shouldbe = ChangeOrder(head);
        if (shouldbe!=null) {
        	head.next = ReturnPairNode(shouldbe);  
		}
        else {
			head.next = null;
		}
        return tempreturnListNode;
        
    }
    
    
    
    public static ListNode swapnode(ListNode head){
        if(head == null)
        {        
            return null;
        }     
        if(head.next !=null)
        {
            return head.next;
        }
        else
        {
            return null;
        }
        
    }
    
    public static ListNode ReturnPairNode(ListNode node) {
    	  	
    	ListNode shouldbe = ChangeOrder(node);
    	if(shouldbe !=null) {
    		node.next = ReturnPairNode(shouldbe);
    	}   	
    	return swapnode(node);
    	
    }


	public static ListNode ChangeOrder(ListNode node) {
		
		
        ListNode NextPosition =swapnode(node);
        ListNode shouldbeListNode  = NextPosition.next;
        NextPosition.next = node;
        
        if (shouldbeListNode == null) {
        	node.next = null;
		    return null;		    
		}
        
        return shouldbeListNode;
	}
    
    

}