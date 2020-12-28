package com.sab;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DirectorySearch {
    public static void main(String args[]) throws NoResultsFoundException {
        Trie trie = new Trie();

        List<String> contactList = Arrays.asList("arun", "arjun", "ajay", "akira", "amulya", "bhavani", "bhargavi",
                "varun", "virat", "vivek", "vijay", "vimal", "srikanth", "srinivas", "sravanthi", "sravan");

        trie.insertContacts(contactList);
        String proceedForNextSearch = "NO";
        do {
            System.out.println("Directory has contacts : " + contactList);
            System.out.println("Please enter the few characters to search for a contact ");
            Scanner in = new Scanner(System.in);
            String query = in.nextLine();
            trie.displayContacts(query);
            System.out.println("Do you want to continue the search for another contact? (YES/NO) : ");
            proceedForNextSearch = in.nextLine();
        } while ("YES".equalsIgnoreCase(proceedForNextSearch));
    }
}


class TrieNode {
    HashMap<Character, TrieNode> child;
    boolean isLast;

    public TrieNode() {
        child = new HashMap<Character, TrieNode>();
        for (char i = 'a'; i <= 'z'; i++)
            child.put(i, null);
        isLast = false;
    }
}

class Trie {
    TrieNode root;

    public void insertContacts(List<String> contactList) {
        root = new TrieNode();
        for (String contact: contactList) {
            insertContact(contact);
        }
    }

    public void insertContact(String s) {
        int length = s.length();
        TrieNode iterator = root;

        for (int i = 0; i < length; i++) {
            TrieNode nextNode = iterator.child.get(s.charAt(i));
            if (nextNode == null) {
                nextNode = new TrieNode();
                iterator.child.put(s.charAt(i), nextNode);
            }
            iterator = nextNode;
            if (i == length - 1)
                iterator.isLast = true;
        }
    }


    public void displayContactsUtil(TrieNode curNode,   String prefix) {
        if (curNode.isLast)
            System.out.println(prefix);

        for (char i = 'a'; i <= 'z'; i++) {
            TrieNode nextNode = curNode.child.get(i);
            if (nextNode != null) {
                displayContactsUtil(nextNode, prefix + i);
            }
        }
    }

    void displayContacts(String str) throws NoResultsFoundException {
        TrieNode prevNode = root;

        String prefix = "";
        int len = str.length();

        int index;
        for ( index = 0; index < len; index++) {
            prefix += str.charAt(index);
            char lastChar = prefix.charAt(index);
            TrieNode curNode = prevNode.child.get(lastChar);
            if (curNode == null) {
                index++;
                throw new NoResultsFoundException("No Results Found for " + prefix + "");
            }

            System.out.println("Suggestions based on "  + prefix + " are");
            displayContactsUtil(curNode, prefix);

            prevNode = curNode;
        }

        for (; index < len; index++) {
            prefix += str.charAt(index);
            throw new NoResultsFoundException("No Results Found for " + prefix + "");
        }
    }


}