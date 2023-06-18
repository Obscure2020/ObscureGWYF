import java.io.File;
import java.util.Arrays;

public class WebPage {
    private File target;
    private File[] images;
    private static final String tab = "    ";

    public WebPage(File newTarget, File[] newImages){
        target = newTarget;
        images = newImages;
    }

    public void generate(File previous, File next){
        String title = target.getName().split("[.]")[0];
        int holeNum = Integer.parseInt(title.split(" ")[1]);
        System.out.println("{\"" + title + "\", " + holeNum + ", " + Arrays.toString(images) + "}");
    }
}
