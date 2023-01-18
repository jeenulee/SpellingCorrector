package spell;


//Dictionary
public class Trie implements ITrie {

    private Node root;
    private int wordCount;
    private int nodeCount;

    @Override
    public void add(String word) {

        INode currNode = root;

        for (int i = 0; i < word.length(); i++) {
            char yeet = word.charAt(i);
            int index = yeet - 'a';

            if (currNode.getChildren()[index] == null) {
                currNode.getChildren()[index] = new Node();
            }
            currNode = currNode.getChildren()[index];
        }

        wordCount++;
    }

    @Override
    public INode find(String word) {

        INode currNode = root;

        for (int i = 0; i < word.length(); i++) {
            char yeet = word.charAt(i);
            int index = yeet - 'a';

            if (currNode.getChildren()[index] == null) {
                return null;
            }
            currNode = currNode.getChildren()[index];
            //do I increment value here???? from node class???
        }
        nodeCount++;

        return currNode;

    }

    @Override
    public int getWordCount() {

//        for (int i = 0; i < wordCount; i++){
//            if(wordCount != 1){
//                return
//            }
//        }
        //any node that has counter != 1
        //return the count

        return wordCount;
    }

    @Override
    public int getNodeCount() {

        return nodeCount;
    }




    @Override
    public String toString(){

        INode TrieNode =

        StringBuilder curWord = new StringBuilder();
        StringBuilder output = new StringBuilder();

        toString_Helper(root, curWord, output);

        return output.toString();

    }

    //Trienode should probably be node or inode pretty sure?
    private void toString_Helper(TrieNode n, StringBuilder curWord,StringBuilder output){    //need to implement trienode somewhere, have to pass in word
                                                                    //string builder ^^ is for
        if(n.getValue() > 0){
            //append the node's word to the output

            output.append(curWord.toString());
            output.append("\n");
        }

        for (int i = 0; i < children.length; ++i){     //children.length should be 26 or length of the tree
            INode child = n.getChildren()[i];          //recurse over all non-null children
            if (child != null){

                char childLetter = (char)('a' + i);
                curWord.append(childLetter);


                toString_Helper(child, curWord, output);

                curWord.deleteCharAt(curWord.length() - 1);    // may not work? switched .length, used to be .size?
            }
        }
    }




    @Override
    public int hashCode(){


        //Combine the following values
        //1. wordCount
        //2. nodeCount
        //3. The index of each of the root node's non-null children

        return wordCount * nodeCount;
    }


    @Override
    public boolean equals(Object o) {

        //check if o == null?
        //is o == this?
        //do this and o have the same class?
        //if answer is no return false, if yes keep going
        //

        Trie dictionary = (Trie) o;

        //do this and ^dictionary^ have the same wordcount and nodecount???
    return true;  //fixmelater
//        return equals_Helper(this.root, dictionary.root);
    }
        private boolean equals_Helper(TrieNode n1, TrieNode n2){

            //compare n1 and n2 to see if they are the same
                //Do n1 and n2 have the same count? If not they rae not equal, return false
                //Do both nodes have non-null children in the exact same location of the arrays (indexes), if not false, yes continue

            //recurse on the children and compare the child subtrees


        return true;




        //not sure that should return true?
    }
}
