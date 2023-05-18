import java.io.File;
import java.util.Arrays;

class WebBuilder{
    public static void main(String[] args) throws Exception{
        //URI test = new URI(null, "Candy Land", null);
        //System.out.println(test.toASCIIString());
        File rootDir = new File(".");
        File[] contents = Arrays.stream(rootDir.listFiles()).filter(f -> f.isDirectory()).toList().toArray(new File[0]);
        for(File f : contents){
            System.out.println(f.toString());
        }
    }
}