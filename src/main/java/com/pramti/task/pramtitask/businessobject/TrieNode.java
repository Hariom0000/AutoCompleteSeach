package com.pramti.task.pramtitask.businessobject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TrieNode {
	char data;
	LinkedList<TrieNode> children;
	TrieNode parent;
	boolean isEnd;

	public TrieNode(char c) {
		data = c;
		children = new LinkedList<>();
		isEnd = false;
	}

	public TrieNode getChild(char c) {
		if (children != null)
			for (TrieNode eachChild : children)
				if (eachChild.data == c)
					return eachChild;
		return null;
	}

	protected List<String> getWords(int autoCompleteLimit) {
		List<String> list = new ArrayList<>();
		if (isEnd) {
			list.add(toString());
		}

		if (children != null) {
			for (int i = 0; i < children.size(); i++) {
				if (children.get(i) != null) {
					List<String> childList = children.get(i).getWords(autoCompleteLimit);
					for (String str : childList) {
						if (list.size() < autoCompleteLimit) {
							list.add(str);
						} else {
							break;
						}
					}
					if(list.size() >= autoCompleteLimit){
						break;
					}
				}
			}
		}
		return list;
	}

	public String toString() {
		if (parent == null) {
			return "";
		} else {
			return parent.toString() + new String(new char[] { data });
		}
	}
}