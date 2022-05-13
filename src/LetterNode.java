import java.util.ArrayList;

public class LetterNode {
    public ArrayList<LetterNode> children;
    public LetterNode parent;
    private char letter;
    private int count, startCount, childTotalIncrements;

    LetterNode(char letter){
        children = new ArrayList<>();
        parent = null;
        this.letter = letter;
        count = 0;
    }

    public char getLetter() {
        return letter;
    }

    public int getCount() {
        return count;
    }

    public int getStartCount() {
        return startCount;
    }

    public int getChildTotalIncrements() {
        return childTotalIncrements;
    }

    public void increment(){
        count++;
    }

    public void incrementStart(){
        startCount++;
    }

    public void insert(char newLetter){
        childTotalIncrements++;

        for(int i = 0; i < children.size(); i++){
            if(children.get(i).letter == newLetter){
                if(letter != newLetter) children.get(i).increment();
                return;
            }
        }

        children.add(new LetterNode(newLetter));
        children.get(children.size() - 1).increment();
        children.get(children.size() - 1).parent = this;
    }

    public void grandchild(char parentLetter, char newLetter){
        for(int i = 0; i < children.size(); i++){
            if(children.get(i).letter == parentLetter){
                children.get(i).insert(newLetter);
            }
        }
    }

    public String describeChildren(){
        String out = "";

        for(int i = 0; i < children.size(); i++){
            out += "\n     " + children.get(i).getLetter() + " " + children.get(i).getCount();
            out += children.get(i).describeChildren(1) ;
        }

        return out;
    }

    public String describeChildren(int indent){
        String out = "";
        String tab = "";

        for(int i = 0; i < indent + 1; i++){
            tab += "     ";
        }

        for(int i = 0; i < children.size(); i++){
            out += "\n" + tab + children.get(i).getLetter() + " " + children.get(i).getCount();
            out += children.get(i).describeChildren(indent + 1) ;
        }

        return out;
    }

}
