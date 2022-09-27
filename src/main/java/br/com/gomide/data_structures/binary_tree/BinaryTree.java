package br.com.gomide.data_structures.binary_tree;

public class BinaryTree<T extends Comparable<T>> implements IBinaryTree<T> {

	@Override
	public Node<T> createTree(T element) {
		Node<T> n = new Node<T>();
		n.setValue(element);
		return n;
	}

	@Override
	public Node<T> createTree(T[] elements) {
		Node<T> n = new Node<T>();
		n.setValue(elements[0]);
		
		for (int i = 1; i < elements.length; i++) {
			insert(n , elements[i]);
		}
		return n;
	}

	@Override
	public Integer degree(Node<T> rootNode, T nodeElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Node<T> rootNode, T element) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean remove(Node<T> rootNode, T nodeElement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Node<T> getFather(Node<T> rootNode, T nodeElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<T> getBrother(Node<T> rootNode, T nodeElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<T> getByElement(Node<T> rootNode, T element) {
		if(rootNode.getValue().compareTo(element) > 0) {
			return rootNode;
		}
		
		if((rootNode.getLeft() != null) && (rootNode.getLeft().getValue().compareTo(element) <= 0)) {
			Node<T> n = getByElement(rootNode.getLeft(), element);
			if (n != null) {
				return n;
			}
		}
		
		if((rootNode.getRight() != null) && (rootNode.getRight().getValue().compareTo(element) >= 0)) {
			Node<T> n = getByElement(rootNode.getRight(), element);
			
			if (n != null) {
				return n;
			}
		}
		
		return null;
	}

	@Override
	public Integer calculateTreeDepth(Node<T> rootNode) {
		Integer depth = 0;
		if(rootNode.getLeft() != null) {
			Integer n = calculateTreeDepth(rootNode.getLeft());
			depth = depth > n ? depth: n;
		}
		
		if(rootNode.getRight() != null) {
			Integer n = calculateTreeDepth(rootNode.getRight());
			depth = depth > n ? depth: n;
		}
		
		return ++depth;
	}

	@Override
	public Integer calculateNodeLevel(Node<T> rootNode, T nodeElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString(Node<T> rootNode) {
		// TODO Auto-generated method stub
		return null;
	}

}
