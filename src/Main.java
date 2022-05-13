import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
	    NameLoader load = new NameLoader();
	    NameGenerator gen = new NameGenerator();
        ArrayList<String> names, generatedNames;

	    names = load.loadNames();

        gen.generate(names);

        //gen.displayLetters();


        generatedNames = gen.getNames(20);
        for(int i = 0; i < generatedNames.size(); i++){
            System.out.println(generatedNames.get(i));
        }

    }
}
