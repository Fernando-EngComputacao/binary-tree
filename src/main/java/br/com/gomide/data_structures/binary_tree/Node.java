package br.com.gomide.data_structures.binary_tree;

public class Node<T extends Comparable<?>> {

	private T value;
	private Node<T> left;
	private Node<T> right;
	private boolean isRoot;
	
	public Node() {}
	
	public Node(boolean isRoot) {
		this.isRoot = isRoot;
	}
	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}
	
	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
}
