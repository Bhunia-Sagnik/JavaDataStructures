package DataStructures.trie;

public class Trie {
    public class WordNotFoundException extends Exception {
        public WordNotFoundException(String msg) {
            super(msg);
        }
    }

    static class Node {
        Node[] children; // keeps track of the child nodes
        boolean isEOW; // marks the end of word

        public Node() {
            children = new Node[95];
            isEOW = false;
        }
    }

    static Node root = new Node();

    private int indexOf(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return ch - 'A'; // A - Z: 0 - 25
        } else if (ch >= 'a' && ch <= 'z') {
            return 26 + ch - 'a'; // a - z: 26 - 51
        } else if (ch >= '0' && ch <= '9') {
            return 52 + ch - '0'; // 0 - 9: 52 - 61
        } else if (ch >= 32 && ch <= 47) { // Special characters from ASCII 32 to 47
            return 62 + (ch - 32); // ASCII 32 to 47: 62 - 77
        } else if (ch >= 58 && ch <= 64) { // Special characters from ASCII 58 to 64
            return 78 + (ch - 58); // ASCII 58 to 64: 78 - 84
        } else if (ch >= 91 && ch <= 96) { // Special characters from ASCII 91 to 96
            return 85 + (ch - 91); // ASCII 91 to 96: 85 - 90
        } else if (ch >= 123 && ch <= 126) { // Special characters from ASCII 123 to 126
            return 91 + (ch - 123); // ASCII 123 to 126: 91 - 94
        } else {
            return -1; // Character not in the defined ranges
        }
    }
    

    public void add(String word) {
        Node curr = root;

        for (int i = 0; i < word.length(); i++) {
            int idx = indexOf(word.charAt(i));

            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }

            curr = curr.children[idx];
        }

        curr.isEOW = true;
    }

    public boolean contains(String word) {
        Node curr = root;

        for (int i = 0; i < word.length(); i++) {
            int idx = indexOf(word.charAt(i));

            if (curr.children[idx] == null) {
                return false;
            }

            curr = curr.children[idx];
        }

        return curr != null && curr.isEOW;
    }

    public void remove(String word) throws WordNotFoundException {
        remove(root, word, 0);
    }

    private Node remove(Node curr, String word, int index) throws WordNotFoundException {
        if(curr == null) {
            throw new WordNotFoundException(word);
        }

        if(index == word.length()) {
            if(curr.isEOW) {
                curr.isEOW = false; // unmark the eow to logically remove it
            }
            else {
                throw new WordNotFoundException(word);
            }

            if(isEmpty(curr)) {
                curr = null;
            }

            return curr;
        }

        int idx = indexOf(word.charAt(index));
        curr.children[idx] = remove(curr.children[idx], word, index + 1);

        if(isEmpty(curr) && !curr.isEOW) {
            curr = null;
        }

        return curr;
    }

    private boolean isEmpty(Node node) {
        for (Node child : node.children) {
            if (child != null) {
                return false;
            }
        }
        return true;
    }

}
