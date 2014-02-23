package com.lzq.july.util;

import java.util.Arrays;

public class Trie<C extends CharSequence> {
	private int size = 0;

	protected INodeCreator creator = null;
	protected Node root = null;

	public Trie() {
	}

	public Trie(INodeCreator creator) {
		this.creator = creator;
	}

	protected Node createNewNode(Node parent, Character character,
			boolean isWord) {
		return new Node(parent, character, isWord);
	}

	public boolean add(C seq) {
		return (this.addSequence(seq) != null);
	}

	protected Node addSequence(C seq) {
		if (null == root) {
			if (null == this.creator) {
				root = createNewNode(null, null, false);
			} else {
				root = this.creator.createNewNode(null, null, false);
			}
		}
		int length = (seq.length() - 1);
		Node prev = root;
		for (int i = 0; i < length; i++) {
			Node n = null;
			Character c = seq.charAt(i);
			int index = prev.childIndex(c);
			if (index >= 0) {
				n = prev.getChild(index);
			} else {
				if (null == this.creator) {
					n = createNewNode(prev, c, false);
				} else {
					n = this.creator.createNewNode(prev, c, false);
				}
				prev.addChild(n);
			}
			prev = n;
		}

		Node n = null;
		Character c = seq.charAt(length);
		int index = prev.childIndex(c);
		if (index >= 0) {
			n = prev.getChild(index);
			if (!n.isWord) {
				n.character = c;
				n.isWord = true;
				size++;
				n.count++;
				return n;
			} else {
				n.count++;
				return null;
			}
		} else {
			if (null == this.creator) {
				n = createNewNode(prev, c, true);
				
			} else {
				n = this.creator.createNewNode(prev, c, true);
			}
			n.count++;
			prev.addChild(n);
			size++;
			return n;
		}
	}

	public boolean remove(C seq) {
		if (null == root) {
			return false;
		}

		Node prev = null;
		Node node = root;
		int length = (seq.length()) - 1;
		for (int i = 0; i <= length; i++) {
			char c = seq.charAt(i);
			int index = node.childIndex(c);
			if (index >= 0) {
				prev = node;
				node = node.getChild(index);
			} else {
				return false;
			}
		}
		if (node.childrenSize > 0) {
			node.isWord = false;
		} else {
			int index = prev.childIndex(node.character);
			prev.removeChild(index);
			while (prev != null && prev.isWord == false
					&& prev.childrenSize == 0) {
				if (prev.parent != null) {
					int idx = prev.parent.childIndex(prev.character);
					if (idx >= 0) {
						prev.parent.removeChild(idx);
					}
				}
				prev = prev.parent;
			}
		}
		size--;
		return true;
	}

	protected Node getNode(C seq) {
		if (root == null)
			return null;

		// Find the string in the trie
		Node n = root;
		int length = (seq.length() - 1);
		for (int i = 0; i <= length; i++) {
			char c = seq.charAt(i);
			int index = n.childIndex(c);
			if (index >= 0) {
				n = n.getChild(index);
			} else {
				// string does not exist in trie
				return null;
			}
		}

		return n;
	}

	public boolean contains(C seq) {
		Node n = this.getNode(seq);
		if (n == null || !n.isWord)
			return false;

		// If the node found in the trie does not have it's string
		// field defined then input string was not found
		return n.isWord;
	}

	public int getCount(C seq) {
		Node n = this.getNode(seq);
		if (n == null || !n.isWord) {
			return 0;
		}

		return n.count;
	}

	public int size() {
		return size;
	}

	protected static class Node {
		private static final int MINIMUM_SIZE = 2;

		protected Node[] children = new Node[MINIMUM_SIZE];
		protected int childrenSize = 0;
		protected Node parent = null;
		protected boolean isWord = false;
		protected Character character = null;
		protected int count = 0;

		protected Node(Node parent, Character character, boolean isWord) {
			this.parent = parent;
			this.isWord = isWord;
			this.character = character;
		}

		protected void addChild(Node node) {
			if (childrenSize >= children.length) {
				children = Arrays.copyOf(children,
						((children.length * 3) / 2) + 1);
			}
			children[childrenSize++] = node;
		}

		protected boolean removeChild(int index) {
			if (index >= childrenSize) {
				return false;
			}
			children[index] = null;
			childrenSize--;
			System.arraycopy(children, index + 1, children, index, childrenSize
					- index);
			if (childrenSize >= MINIMUM_SIZE
					&& childrenSize < children.length / 2) {
				children = Arrays.copyOf(children, childrenSize);
			}
			return true;
		}

		protected int childIndex(Character character) {
			for (int i = 0; i < childrenSize; i++) {
				Node node = children[i];
				if (character.equals(node.character)) {
					return i;
				}
			}
			return Integer.MIN_VALUE;
		}

		protected Node getChild(int index) {
			if (index >= childrenSize) {
				return null;
			}
			return children[index];
		}

		protected int getChildrenSize() {
			return this.childrenSize;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			if (isWord) {
				sb.append("Node=").append(isWord).append("\n");
			}
			for (int i = 0; i < childrenSize; i++) {
				Node c = children[i];
				sb.append(c.toString());
			}
			return sb.toString();
		}
	}

	protected static interface INodeCreator {
		public Node createNewNode(Node parent, Character character, boolean type);
	}
}
