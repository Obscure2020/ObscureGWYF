import java.io.File;
import java.util.Arrays;

class WebBuilder{
    public static void main(String[] args) throws Exception{
        //URI test = new URI(null, file.toString(), null);
        //System.out.println(test.toASCIIString());
        File folder = new File("./Workshop/Graveyard craving");
        File[] images = Arrays.stream(folder.listFiles()).filter(f -> f.toString().toLowerCase().endsWith(".png")).toList().toArray(new File[0]);
        for(File f : images){
            System.out.println(f.getName().split("[.]")[0]);
        }
    }
}