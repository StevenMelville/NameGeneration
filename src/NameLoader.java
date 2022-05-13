import java.io.File;
import java.util.*;

public class NameLoader {
    private ArrayList<String> names;

    NameLoader(){
        names = new ArrayList<>();
    }

    ArrayList<String> loadNames(){
        if(names.size() == 0){
            try {
                Scanner in = new Scanner(new File("names.txt"));
                String out = new String();

                while(in.hasNextLine()){
                    out = in.nextLine();
                    names.add(out);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return names;
    }
}
