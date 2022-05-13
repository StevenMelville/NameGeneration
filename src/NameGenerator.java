import java.lang.reflect.Array;
import java.util.*;
import java.lang.Math;

public class NameGenerator {
    private LetterNode [] tree;
    private int nameCount, min, max;

    NameGenerator(){
        nameCount = 0;
        min = 2;
        max = 10;
    }

    public void generate(ArrayList<String> names){
        tree = new LetterNode[26];
        String name;
        char letterFirst, letterSecond, letterThird;

        nameCount = names.size();

        for(int i = 0; i < tree.length; i++){
            tree[i] = new LetterNode((char)(97 + i));
        }

        for(int i = 0; i < names.size(); i++){
            name = names.get(i);

            //if(name.length() > max) max = name.length();
            //if(name.length() < min) min = name.length();

            if(name.length() < 3) continue;

            letterFirst = name.charAt(0);
            letterSecond = name.charAt(1);
            letterThird = name.charAt(2);
            tree[(int)letterFirst - 97].increment();
            tree[(int)letterFirst - 97].incrementStart();
            tree[(int)letterFirst - 97].insert(letterSecond);
            tree[(int)letterFirst - 97].grandchild(letterSecond, letterThird);

            for(int j = 1; j < name.length() - 2; j++){
                letterFirst = name.charAt(j);
                letterSecond = name.charAt(j);
                letterThird = name.charAt(j);
                tree[(int)letterFirst - 97].increment();
                tree[(int)letterFirst - 97].insert(letterSecond);
                tree[(int)letterFirst - 97].grandchild(letterSecond, letterThird);
            }
        }


    }

    public ArrayList<String> getNames(int amount){
        ArrayList<String> generatedNames = new ArrayList<>();
        int rand, length, which, repetitionCount = 0;
        char letter = 'a', letterNext = 'a';
        String name;

        if(tree != null){
            for(int numNames = 0; numNames < amount; numNames++){
                rand = (int) (Math.random() * nameCount);
                length = ((int) (Math.random() * max)) + min;
                name = "";

                for(int i = 0; i < 26; i++){
                    rand -= tree[i].getStartCount();

                    if(rand <= 0){
                        letter = tree[i].getLetter();
                        break;
                    }
                }
                name += letter;



                for(int i = 1; i < length; i++){
                    which = (int)(letter) - 97;
                    rand = (int) (Math.random() * tree[which].getChildTotalIncrements());

                    for(int j = 0; j < tree[which].children.size(); j++){
                        rand -= tree[which].children.get(j).getCount();

                        if(rand <= 0){
                            letterNext = tree[which].children.get(j).getLetter();
                            break;
                        }
                    }


                    //if(letter == letterNext) repetitionCount++;
                    //else repetitionCount = 0;

                    while(letter == letterNext){
                        rand = (int) (Math.random() * tree[which].getChildTotalIncrements());

                        for(int j = 0; j < tree[which].children.size(); j++){
                            rand -= tree[which].children.get(j).getCount();

                            if(rand <= 0){
                                letterNext = tree[which].children.get(j).getLetter();
                                break;
                            }
                        }
                    }


                    letter = letterNext;
                    name += letter;
                }

                generatedNames.add(name);
            }
        }

        return generatedNames;
    }

    public void displayLetters(){
        if(tree != null) {
            for (int i = 0; i < tree.length; i++){
                System.out.println(((char)(i + 97)) + " " + tree[i].getCount() + " " + tree[i].describeChildren());
            }
        }
    }
}
