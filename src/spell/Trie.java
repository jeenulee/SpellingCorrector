package spell;
import java.lang.String;


//Dictionary
public class Trie implements ITrie {

    private Node root = new Node();    //creating node that marks the root node in trie
    private int wordCount = 0;         //keeping track of total words in trie
    private int nodeCount = 1;         //keeping track of node markers in trie

    @Override
    public void add(String word) {

        Node currNode = root;        //create marker that starts at the root
        //make lowercase
        word.toLowerCase();             //make everything lowercase

        for (int i = 0; i < word.length(); i++) {  //loop through the length of the word
            char yeet = word.charAt(i);
            int index = yeet - 'a';      //creating an index to keep track of where in the word we are

            if (currNode.getChildren()[index] == null) {
                currNode.getChildren()[index] = new Node();
                nodeCount ++;                               //if child node is null, then create a new node and increment nodeCount
            }
            currNode = (Node) currNode.getChildren()[index];  //this is the end of the word
        }
        if(currNode.getValue() == 0){
            wordCount ++;                   //if we arrive at the end of the word, and it has a value of 0, then we know it is a unique word
        }
        currNode.incrementValue();

    }

    @Override
    //changed from INode
    public INode find(String word) {

        Node currNode = root;    //start at the root
        word.toLowerCase();

        for (int i = 0; i < word.length(); i++) {
            char yeet = word.charAt(i);
            int index = yeet - 'a';       //loop through the entire word, create an index that keeps track of where in the word we at

            if (currNode.getChildren()[index] == null) {
                return null;
            }
            currNode = (Node) currNode.getChildren()[index];    //curr node will end up at the final node of the word
        }
        if(currNode.getValue() == 0 || currNode == null){
            return null;
        }

        return currNode;                //return where we at, which is a reference to the node that ends which implies the entire word.
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

        if (o == null) {     // if nothing there then false
            return false;
        }
        if (o == this) {     //if it is already exactly equal, then true and save time
            return true;
        }
        Trie dictionary = (Trie) o;        //creating the two trees which should be the same.

        if (dictionary.wordCount != this.wordCount) {
            return false;                               //if the word counts are not the same, then trees are different.
        }
        if (dictionary.nodeCount != this.nodeCount) {
            return false;                                  //if node counts are different, then trees are different.
        }
        return equals_Helper(this.root, dictionary.root);  //if everything above checks out, then we can start comparing actual nodes
    }

    private boolean equals_Helper(Node n1, Node n2) {     //node n1 is first node from first tree, n2 is first node from second tree.

        if (n1 != null) {
            if (n2 == null) {   // here we are comparing the first nodes of both of the different trees
                return false;   // if one has somethign and the other doesn't, clearly they aren't the same, return false.
            }
        }
        if (n1 == null) {
            if (n2 != null) {   // same as above
                return false;
            }
        }
        else {
            if (n1.getValue() != n2.getValue()) {
                return false;                   //here we are checking if the entire value of the nodes are the same, or the count
            }                                      //if not the same then we return false again.


            //here we are going through each of the trees simultaneuously and comparing the nodes at each respective position.
            //if not the same then false
            for (int i = 0; i < n1.getChildren().length; i++) {
                if (!equals_Helper((Node) n1.getChildren()[i], (Node) n2.getChildren()[i])) {
                    return false;
                }
            }

        }
        return true; ///if the both trees are gone through entriely and they are the same. Then true
    }
}






