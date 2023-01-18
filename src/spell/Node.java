package spell;

public class Node implements INode {

    private int count;

    int myArray[] = new int[26];


    @Override
    public int getValue() {

        count = myArray.length;

        return count;
    }

    @Override
    public void incrementValue() {
        count ++;
    }

    @Override
    public INode[] getChildren() {

        int newArray[] = myArray;

        return newArray;
        //return entire array
    }
}
