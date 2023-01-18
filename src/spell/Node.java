package spell;
import java.util.Arrays;

public class Node implements INode {

    private int count;

    private INode myArray[] = new Node[26];


    @Override
    public int getValue() {

        return count;
    }

    @Override
    public void incrementValue() {
        count ++;
    }

    @Override
    public INode[] getChildren() {

    return myArray;
        //return entire array
    }
}
