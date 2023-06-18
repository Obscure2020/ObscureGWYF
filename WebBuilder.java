import java.io.File;
import java.util.Arrays;

class WebBuilder{
    public static void main(String[] args) throws Exception{
        //URI test = new URI(null, file.toString(), null);
        //System.out.println(test.toASCIIString());
        File folder = new File("./Workshop/Graveyard craving");
        File[] images = Arrays.stream(folder.listFiles()).filter(f -> f.toString().toLowerCase().endsWith(".png")).toList().toArray(new File[0]);
        for(int hole=1; hole<=18; hole++){
            File singleGuess = new File(folder, "Hole " + hole + ".png");
            File doubleGuess = new File(folder, "Hole " + hole + "-1.png");
            if(singleGuess.exists()) System.out.println(singleGuess.getName());
            if(doubleGuess.exists()) System.out.println(doubleGuess.getName());
        }
    }
}