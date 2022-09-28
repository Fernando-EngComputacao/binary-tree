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
		if (rootNode.getValue().compareTo(element) > 0) {
			if(rootNode.getLeft() == null) {
				Node<T> n = new Node<T>();
				n.setValue(element);
				rootNode.setLeft(n);
			} else {
				insert(rootNode.getLeft(), element);
			}
		} else if(rootNode.getValue().compareTo(element) < 0) {
			if (rootNode.getRight() == null) {
				Node<T> n = new Node<T>();
				n.setValue(element);
				rootNode.setRight(n);
			} else {
				insert(rootNode.getRight(), element);
			}
		}
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
		if(rootNode.getValue().compareTo(element) == 0) {
			return rootNode;
		}
		
		if((rootNode.getLeft() != null) && (rootNode.getLeft().getValue().compareTo(element) >= 0)) {
			Node<T> n = getByElement(rootNode.getLeft(), element);
			if (n != null) {
				return n;
			}
		}
		
		if((rootNode.getRight() != null) && (rootNode.getRight().getValue().compareTo(element) <= 0)) {
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
		Node<T> left = rootNode.getLeft();
		Node<T> right = rootNode.getRight();
		
		if(left != null) {
			Integer n = calculateTreeDepth(rootNode.getLeft());
			depth = depth > n ? depth: n;
		}
		
		if(right != null) {
			Integer n = calculateTreeDepth(rootNode.getRight());
			depth = depth > n ? depth: n;
		}
		
		return (right == null) && (left == null) ? 0: ++depth;
	}

	@Override
	public Integer calculateNodeLevel(Node<T> rootNode, T nodeElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString(Node<T> rootNode) {
		String result = "";
		result += rootNode.getValue().toString() + " ";//TODO VERIFICAR QUANDO O VALUE É DE UM NO ROOT COM getFather()
		
		Node<T> left  = rootNode.getLeft();
		Node<T> right = rootNode.getRight();
		
		result = ((left != null) || (right != null)) ? result + "(": result;
		
		if(left != null) {
			result += "left:" + toString(rootNode.getLeft());
		}
		
		if(right != null) {
			result += "right:" + toString(rootNode.getRight());
		}
		
		result = ((left != null) || (right != null)) ? result + ")": result;

		
		return result;
	}

}
