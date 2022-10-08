package br.com.gomide.data_structures.binary_tree;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryTree<T extends Comparable<T>> implements IBinaryTree<T> {

	@Override
	public Node<T> createTree(T element) {
		Node<T> n = new Node<T>(true);
		n.setValue(element);
		return n;
	}

	@Override
	public Node<T> createTree(T[] elements) {
		Node<T> n = createTree(elements[0]);

		for (int i = 1; i < elements.length; i++) {
			insert(n, elements[i]);
		}
		return n;
	}

	@Override
	public Integer degree(Node<T> rootNode, T nodeElement) {
		Integer tem = 0;
		if (rootNode.getValue().compareTo(nodeElement) == 0) {
			if (rootNode.getRight() != null)
				tem += 1;
			if (rootNode.getLeft() != null)
				tem += 1;

			return tem;
		} else {
			if (rootNode.getValue().compareTo(nodeElement) > 0) {
				if (rootNode.getLeft() != null)
					tem = degree(rootNode.getLeft(), nodeElement);
				else
					tem = null;
			}

			else if (rootNode.getValue().compareTo(nodeElement) < 0) {
				if (rootNode.getRight() != null)
					tem = degree(rootNode.getRight(), nodeElement);
				else
					tem = null;
			}
			return tem;

		}

	}

	@Override
	public void insert(Node<T> rootNode, T element) {
		if (rootNode.getValue().compareTo(element) > 0) {
			if (rootNode.getLeft() == null) {
				Node<T> n = new Node<T>();
				n.setValue(element);
				rootNode.setLeft(n);
			} else {
				insert(rootNode.getLeft(), element);
			}
		} else if (rootNode.getValue().compareTo(element) < 0) {
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
		if (rootNode == null)
			return false;

		Node<T> current = rootNode;
		Node<T> father = rootNode;
		boolean lef_kid = true;

		while (!(current.getValue().compareTo(nodeElement) == 0)) {
			father = current;
			if (current.getValue().compareTo(nodeElement) > 0) {
				current = current.getLeft();
				lef_kid = true;
			} else {
				current = current.getRight();
				lef_kid = false;
			}
			if (current == null)
				return false;
		}

		if (current.getLeft() == null && current.getRight() == null) {
			if (current == rootNode)
				rootNode = null;
			else if (lef_kid)
				father.setLeft(null);
			else
				father.setRight(null);
		} else if (current.getRight() == null) {
			if (current == rootNode)
				rootNode = current.getLeft();
			else if (lef_kid)
				father.setLeft(current.getLeft());
			else
				father.setRight(current.getRight());
		} else if (current.getLeft() == null) {
			if (current == rootNode)
				rootNode = current.getRight();
			else if (lef_kid)
				father.setLeft(current.getRight());
			else
				father.setRight(current.getRight());
		} else {
			Node<T> successor = successorNode(current);
			if (current == rootNode)
				rootNode = successor;
			else if (lef_kid)
				father.setLeft(successor);
			else
				father.setRight(successor);
			successor.setLeft(current.getLeft());
		}
		return true;
	}

	public Node<T> successorNode(Node<T> apaga) {
		Node<T> successorFather = apaga;
		Node<T> successor = apaga;
		Node<T> current = apaga.getRight();

		while (current != null) {
			successorFather = successor;
			successor = current;
			current = current.getLeft();
		}
		if (successor != apaga.getRight()) {
			successorFather.setLeft(successor.getRight());
			successor.setRight(apaga.getRight());
		}
		return successor;
	}

	public static void main(String[] args) {
		BinaryTree<Integer> binaryTreeOps = new BinaryTree<>();
		Integer[] elements = new Integer[] { 37, 20, 10, 30, 80, 100, 5, 180, 90 };

		Node<Integer> rootNode = binaryTreeOps.createTree(elements);

		assertEquals(binaryTreeOps.toString(rootNode),
				"root:37 (left:20 (left:10 (left:5 )right:30 )right:80 (right:100 (left:90 right:180 )))");

		binaryTreeOps.remove(rootNode, 180);
		assertEquals(binaryTreeOps.toString(rootNode),
				"root:37 (left:20 (left:10 (left:5 )right:30 )right:80 (right:100 (left:90 )))");

		binaryTreeOps.remove(rootNode, 80);
		assertEquals(binaryTreeOps.toString(rootNode),
				"root:37 (left:20 (left:10 (left:5 )right:30 )right:100 (left:90 ))");

		binaryTreeOps.remove(rootNode, 10);
		assertEquals(binaryTreeOps.toString(rootNode), "root:37 (left:20 (left:5 right:30 )right:100 (left:90 ))");

		binaryTreeOps.remove(rootNode, 20);
		assertEquals(binaryTreeOps.toString(rootNode), "root:37 (left:30 (left:5 )right:100 (left:90 ))");

//		binaryTreeOps.remove(rootNode, 37);
//		assertEquals(binaryTreeOps.toString(rootNode), "root:100 (left:90 (left:30 (left:5 )))");
	}

	@Override
	public Node<T> getFather(Node<T> rootNode, T nodeElement) {
		if (rootNode == null || rootNode.getValue() == nodeElement) {
			return null;
		}
		if ((rootNode.getLeft() != null) && (rootNode.getLeft().getValue() == nodeElement)
				|| (rootNode.getRight() != null) && (rootNode.getRight().getValue() == nodeElement)) {
			return rootNode;
		}

		if ((rootNode.getLeft() != null) && (rootNode.getValue().compareTo(nodeElement) > 0)) {
			Node<T> n = getFather(rootNode.getLeft(), nodeElement);

			if (n != null) {
				return n;
			}
		}
		if ((rootNode.getRight() != null) && rootNode.getValue().compareTo(nodeElement) < 0) {
			Node<T> n = getFather(rootNode.getRight(), nodeElement);

			if (n != null) {
				return n;
			}
		}

		return null;
	}

	@Override
	public Node<T> getBrother(Node<T> rootNode, T nodeElement) {
		Node<T> n0 = getFather(rootNode, nodeElement);
		Node<T> left = n0.getLeft();
		Node<T> right = n0.getRight();

		if ((left != null) && (right != null)) {
			if (left.getValue() == nodeElement) {
				return right;
			}
			if (right.getValue() == nodeElement) {
				return left;
			}
		}
		return null;
	}

	@Override
	public Node<T> getByElement(Node<T> rootNode, T element) {
		if (rootNode.getValue().compareTo(element) == 0) {
			return rootNode;
		}

		if ((rootNode.getLeft() != null) && (rootNode.getLeft().getValue().compareTo(element) >= 0)) {
			Node<T> n = getByElement(rootNode.getLeft(), element);
			if (n != null) {
				return n;
			}
		}

		if ((rootNode.getRight() != null) && (rootNode.getRight().getValue().compareTo(element) <= 0)) {
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

		if (left != null) {
			Integer n = calculateTreeDepth(rootNode.getLeft());
			depth = depth > n ? depth : n;
		}

		if (right != null) {
			Integer n = calculateTreeDepth(rootNode.getRight());
			depth = depth > n ? depth : n;
		}

		return (right == null) && (left == null) ? 0 : ++depth;
	}

	@Override
	public Integer calculateNodeLevel(Node<T> rootNode, T nodeElement) {
		Integer lvl = -1;
		if (rootNode == null || nodeElement == null) {
			return null;
		}
		if (rootNode.getValue() == nodeElement) {
			return 0;
		}
		Node<T> a = new Node<T>(nodeElement);
		while (a != null) {
			a = getFather(rootNode, a.getValue());
			lvl++;
		}
		return lvl;

	}

	@Override
	public String toString(Node<T> rootNode) {
		if (rootNode != null) {
			String result = "";
			String root = rootNode.isRoot() ? "root:" : "";
			result += root + rootNode.getValue().toString() + " ";

			Node<T> left = rootNode.getLeft();
			Node<T> right = rootNode.getRight();

			result = ((left != null) || (right != null)) ? result + "(" : result;

			if (left != null) {
				result += "left:" + toString(rootNode.getLeft());
			}

			if (right != null) {
				result += "right:" + toString(rootNode.getRight());
			}

			result = ((left != null) || (right != null)) ? result + ")" : result;

			return result;
		} else
			return null;
	}
}
