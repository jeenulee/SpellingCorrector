package spell;
import java.lang.String;


//Dictionary
public class Trie implements ITrie {

    private Node root = new Node();
    private int wordCount = 0;
    private int nodeCount = 1;

    @Override
    public void add(String word) {

        Node currNode = root;
        //make lowercase
        word.toLowerCase();

        for (int i = 0; i < word.length(); i++) {
            char yeet = word.charAt(i);
            int index = yeet - 'a';

            if (currNode.getChildren()[index] == null) {
                currNode.getChildren()[index] = new Node();
                nodeCount ++;
            }
            currNode = (Node) currNode.getChildren()[index];
        }
        if(currNode.getValue() == 0){
            wordCount ++;
        }
        currNode.incrementValue();


    }

    @Override
    //changed from INode
    public INode find(String word) {

        Node currNode = root;
        word.toLowerCase();

        for (int i = 0; i < word.length(); i++) {
            char yeet = word.charAt(i);
            int index = yeet - 'a';

            if (currNode.getChildren()[index] == null) {
                return null;
            }
            currNode = (Node) currNode.getChildren()[index];

        }
        if(currNode.getValue() == 0 || currNode == null){
            return null;
        }

        return currNode;
    }

    @Override
    public int getWordCount() {

        return wordCount;
    }

    @Override
    public int getNodeCount() {

        return nodeCount;
    }

    @Override
    public String toString() {

        StringBuilder curWord = new StringBuilder();
        StringBuilder output = new StringBuilder();

        toString_Helper(root, curWord, output);

        return output.toString();

    }


    private void toString_Helper(Node n, StringBuilder curWord, StringBuilder output) {

        if (n.getValue() > 0) {
            //append the node's word to the output
            output.append(curWord.toString());
            output.append("\n");
        }

        for (int i = 0; i < 26; ++i) {     //children.length should be 26 or length of the tree
            Node child = (Node) n.getChildren()[i];          //recurse over all non-null children
            if (child != null) {

                char childLetter = (char) ('a' + i);
                curWord.append(childLetter);

                toString_Helper(child, curWord, output);

                curWord.deleteCharAt(curWord.length() - 1);    // may not work? switched .length, used to be .size?
            }
        }
    }

    /*
    *
    *
    *
    *
    *
    * */




    @Override
    public int hashCode() {
        int index = 0;

        for (int i = 0; i < root.getValue(); i++) {
            if (root.getChildren() != null) {
                index++;
            }
        }
        //Combine the following values

        //1. wordCount
        //2. nodeCount
        //3. The index of each of the root node's non-null children


        return index + wordCount + nodeCount; // SHOULD PROBABLY FIX -- high probs not correct
    }


    @Override
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        Trie dictionary = (Trie) o;

        if (dictionary.wordCount != this.wordCount) {
            return false;
        }
        if (dictionary.nodeCount != this.nodeCount) {
            return false;
        }
        return equals_Helper(this.root, dictionary.root);

    }

    private boolean equals_Helper(Node n1, Node n2) {

        if (n1 != null) {
            if (n2 == null) {
                return false;
            }
        }
        if (n1 == null) {
            if (n2 != null) {
                return false;
            }
        }
        else {
            if (n1.getValue() != n2.getValue()) {
                return false;
            }

            for (int i = 0; i < n1.getChildren().length; i++) {
                if (!equals_Helper((Node) n1.getChildren()[i], (Node) n2.getChildren()[i])) {
                    return false;
                }
            }

        }
        return true;
    }
}

            //compare n1 and n2 to see if they are the same
                //Do n1 and n2 have the same count? If not they rae not equal, return false
                //Do both nodes have non-null children in the exact same location of the arrays (indexes), if not false, yes continue

            //recurse on the children and compare the child subtrees




