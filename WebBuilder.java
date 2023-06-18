import java.io.File;
import java.util.ArrayList;

class WebBuilder{
    public static void main(String[] args) throws Exception{
        // URI root = new File(".").toURI();
        // System.out.println(root.toASCIIString());
        // File beans = new File("./Workshop/Graveyard craving/Hole 1.png");
        // System.out.println(root.relativize(beans.toURI()).toASCIIString());
        ArrayList<WebPage> pages = new ArrayList<>();
        File folder = new File("./Workshop/Graveyard craving");
        for(int hole=1; hole<=18; hole++){
            File output = new File(folder, "Hole " + hole + ".html");
            File guess = new File(folder, "Hole " + hole + ".png");
            if(guess.exists()){
                File[] images = {guess};
                WebPage newPage = new WebPage(output, images);
                pages.add(newPage);
            }
            guess = new File(folder, "Hole " + hole + "-1.png");
            if(guess.exists()){
                File second = new File(folder, "Hole " + hole + "-2.png");
                if(!second.exists()){
                    StringBuilder sb = new StringBuilder("\"");
                    sb.append(guess.getName());
                    sb.append("\" exists, but \"");
                    sb.append(second.getName());
                    sb.append("\" does not. File naming error.");
                    throw new Exception(sb.toString());
                }
                File[] images = {guess, second};
                WebPage newPage = new WebPage(output, images);
                pages.add(newPage);
            }
        }
        for(WebPage wp : pages){
            wp.generate(null, null);
        }
    }
}