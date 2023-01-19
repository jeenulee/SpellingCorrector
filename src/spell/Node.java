package spell;
import java.util.Arrays;

public class Node implements INode {

    private int count;
    private Node myArray[] = new Node[26];

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
    }
}
