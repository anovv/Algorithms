import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class YFastTrie {
	
	private XFastTrie xfast;
	private Map<Integer, AVLTree> reps;
	private int size;
	private int maxSize;
	private int minSize;
	
	public YFastTrie(int size){// size is the number of bits in the biggest integer in the set
		this.size = size;
		minSize = size/2;
		maxSize = size*2;
		xfast = new XFastTrie(size);
		reps = new HashMap<Integer, AVLTree>();
	}
	
	public static void main(String[] args){
		YFastTrie trie = new YFastTrie(3);
		trie.insert(1);
		trie.insert(2);
		trie.insert(3);
		trie.insert(4);
		int suc = trie.getSuccessorOrPredecessor(3, true);
		int pred = trie.getSuccessorOrPredecessor(3, false);
		
		System.out.println("Successor: " + suc + " Predecessor: " + pred);
	}
		
	public void printTrees(){
		for(Entry<Integer, AVLTree> e : reps.entrySet()){
			e.getValue().lvlByLvl();
		}
	}
	
	public void printTreesInorder(){
		for(Entry<Integer, AVLTree> e : reps.entrySet()){
			e.getValue().inorder();
		}
	}
	
	public int getRepsCount(){
		return reps.size();
	}
	
	public void insert(int val){
		int succ = xfast.getSuccessorOrPredecessor(val, true);
		if(succ != -1){
			AVLTree tree = reps.get(succ);
			if(tree != null){
				tree.insert(val);
				if(tree.getSize() > maxSize){
					AVLTree[] trees = AVLTree.split(tree);
					reps.remove(succ);
					xfast.delete(succ);
					if(trees[0] != null){
						int rep1 = trees[0].getRootValue();
						xfast.insert(rep1);
						reps.put(rep1, trees[0]);
					}
					
					if(trees[1] != null){
						int rep2 = trees[1].getRootValue();
						xfast.insert(rep2);
						reps.put(rep2, trees[1]);
					}
				}
				return;
			}
		}
		// we insert the biggest elem
		int pred = xfast.getSuccessorOrPredecessor(val, false);
		if(pred != -1){
			AVLTree tree = reps.get(pred);
			if(tree != null){
				tree.insert(val);
				if(tree.getSize() > maxSize){
					AVLTree[] trees = AVLTree.split(tree);
					reps.remove(pred);
					xfast.delete(pred);
					if(trees[0] != null){
						int rep1 = trees[0].getRootValue();
						xfast.insert(rep1);
						reps.put(rep1, trees[0]);
					}
					
					if(trees[1] != null){
						int rep2 = trees[1].getRootValue();
						xfast.insert(rep2);
						reps.put(rep2, trees[1]);
					}
				}
				return;
			}
		}
		
		//very first insert;
		AVLTree tree = new AVLTree();
		tree.insert(val);
		xfast.insert(val);
		reps.put(val, tree);
	}
	
	public void delete(int val){
		//TODO
	}
	
	public boolean contains(int val){
		if(xfast.contains(val)){
			return true;
		}
		
		int succ = xfast.getSuccessorOrPredecessor(val, true);
		if(succ != -1){
			AVLTree tree = reps.get(succ);
			if(tree != null && tree.contains(val)){
				return true;
			}
		}
		
		int pred = xfast.getSuccessorOrPredecessor(val, false);
		if(pred != -1){
			AVLTree tree = reps.get(pred);
			if(tree != null && tree.contains(val)){
				return true;
			}
		}
		
		return false;
	}
	
	public int getSuccessorOrPredecessor(int val, boolean successor){
		
		if(xfast.contains(val)){
			AVLTree tree = reps.get(val);
			if(tree != null){
				return tree.getSuccessorOrPredecessor(val, successor);
			}
		}
		
		int succ = xfast.getSuccessorOrPredecessor(val, true);
		int pred = xfast.getSuccessorOrPredecessor(val, false);
		
		if(succ == -1 && pred == -1){
			return -1;
		}
		
		if(succ == -1){
			AVLTree tree = reps.get(pred);
			return tree.getSuccessorOrPredecessor(val, successor);
		}

		if(pred == -1){
			AVLTree tree = reps.get(succ);
			return tree.getSuccessorOrPredecessor(val, successor);
		}
		
		AVLTree tree1 = reps.get(succ);
		AVLTree tree2 = reps.get(pred);
		
		if(successor){
			int succ1 = tree1.getSuccessorOrPredecessor(val, true);
			int succ2 = tree2.getSuccessorOrPredecessor(val, true);
			
			if(succ1 == -1){
				return succ2;
			}
			
			if(succ2 == -1){
				return succ1;
			}
			
			return Math.min(succ1, succ2);
		}else{
			int pred1 = tree1.getSuccessorOrPredecessor(val, false);
			int pred2 = tree2.getSuccessorOrPredecessor(val, false);
			return Math.max(pred1, pred2);
		}
	}	
}
