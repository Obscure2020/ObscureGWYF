import java.io.File;
import java.util.*;

class WebBuilder{
    private static void itemizeDirectories(File parent, ArrayList<WebPage> pages, ArrayList<WebPage> firsts) throws Exception{
        File[] dirs = Arrays.stream(parent.listFiles()).filter(f -> f.isDirectory()).toList().toArray(new File[0]);
        for(File folder : dirs){
            ArrayDeque<WebPage> result = new ArrayDeque<>(18);
            for(int hole=1; hole<=18; hole++){
                File output = new File(folder, "Hole " + hole + ".html");
                File guess = new File(folder, "Hole " + hole + ".webp");
                if(guess.exists()){
                    File[] images = {guess};
                    WebPage newPage = new WebPage(output, images);
                    WebPage tail = result.peekLast();
                    if(tail != null){
                        tail.setNext(output);
                        newPage.setPrevious(tail.getTarget());
                    }
                    result.addLast(newPage);
                    continue;
                }
                guess = new File(folder, "Hole " + hole + "-1.webp");
                if(guess.exists()){
                    File second = new File(folder, "Hole " + hole + "-2.webp");
                    if(!second.exists()){
                        StringBuilder sb = new StringBuilder("Currently itemizing \"");
                        sb.append(folder.getCanonicalPath());
                        sb.append("\".\n\"");
                        sb.append(guess.getName());
                        sb.append("\" exists, but \"");
                        sb.append(second.getName());
                        sb.append("\" does not. File naming error.");
                        throw new Exception(sb.toString());
                    }
                    File[] images = {guess, second};
                    WebPage newPage = new WebPage(output, images);
                    WebPage tail = result.peekLast();
                    if(tail != null){
                        tail.setNext(output);
                        newPage.setPrevious(tail.getTarget());
                    }
                    result.addLast(newPage);
                }
            }
            firsts.add(result.peekFirst());
            for(WebPage wp : result) pages.add(wp);
        }
        System.gc();
    }
    public static void main(String[] args) throws Exception{
        // URI root = new File(".").toURI();
        // System.out.println(root.toASCIIString());
        // File beans = new File("./Workshop/Graveyard craving/Hole 1.png");
        // System.out.println(root.relativize(beans.toURI()).toASCIIString());
        {
            System.out.println("Scanning folder structure...");
            ArrayDeque<File> scan = new ArrayDeque<>();
            ArrayList<File> trash = new ArrayList<>();
            File[] subs = new File(".").listFiles();
            for(File f : subs) scan.addLast(f);
            while(!scan.isEmpty()){
                File item = scan.removeFirst();
                if(item.getName().startsWith(".")) continue;
                if(item.isDirectory()){
                    subs = item.listFiles();
                    for(File f : subs) scan.addLast(f);
                    continue;
                }
                if(item.getName().toLowerCase().endsWith(".html")) trash.add(item);
            }
            if(!trash.isEmpty()) System.out.println("Deleting existing HTML files...");
            for(File f : trash) f.delete();
        }
        System.gc();
        System.out.println("Itemizing new WebPages...");
        ArrayList<WebPage> pages = new ArrayList<>();
        ArrayList<WebPage> firsts = new ArrayList<>();
        itemizeDirectories(new File("./Workshop"), pages, firsts);
        itemizeDirectories(new File("./Official"), pages, firsts);
        System.out.println("Generating new WebPages...");
        for(WebPage wp : pages) wp.generate();
        System.out.println("Done.");
    }
}