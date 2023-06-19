import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class WebPage {
    private File target;
    private File[] images;
    private static final String tab = "    ";
    private File previous;
    private File next;

    public WebPage(File newTarget, File[] newImages){
        target = newTarget;
        images = newImages;
        previous = null;
        next = null;
    }

    public void setPrevious(File newPrev){
        previous = newPrev;
    }

    public void setNext(File newNext){
        next = newNext;
    }

    public File getTarget(){
        return target;
    }

    private String urlSafe(String input) throws Exception{
        return new URI(null, input, null).toASCIIString();
    }

    public void generate() throws Exception{
        String title = target.getName().split("[.]")[0];
        int holeNum = Integer.parseInt(title.split(" ")[1]);
        PrintWriter pw = new PrintWriter(target, StandardCharsets.UTF_8);
        pw.print("<!DOCTYPE html>\n<html>\n<head>\n<title>");
        pw.print(title);
        pw.print(" - ");
        pw.print(target.getParentFile().getName());
        pw.print("</title>\n<link rel=\"stylesheet\" href=\"../../gwyf.css\">\n");
        if(images.length > 1) pw.print("<script src=\"../../midline.js\"></script>\n");
        pw.print("</head>\n<body style=\"background-image: url('");
        pw.print(images[0].getName());
        pw.print("')\">\n");
        pw.print(tab + "<div id=\"blurPage\" class=\"fullSize\"></div>\n");
        if(images.length > 1){
            //Right now the following procedure is hardcoded to two images, but the boolean above isn't.
            //There is a remote possibility of supporting more than two images in the far future.
            //As of yet, that hasn't seemed particularaly necessary.
            pw.print(tab + "<img src=\"");
            pw.print(urlSafe(images[0].getName()));
            pw.print("\" id=\"leftHalfImage\">\n");
            pw.print(tab + "<img src=\"");
            pw.print(urlSafe(images[0].getName()));
            pw.print("\" id=\"leftHalfPower\">\n");
            pw.print(tab + "<img src=\"");
            pw.print(urlSafe(images[1].getName()));
            pw.print("\" id=\"rightHalfImage\">\n");
            pw.print(tab + "<img src=\"");
            pw.print(urlSafe(images[1].getName()));
            pw.print("\" id=\"rightHalfPower\">\n");
            pw.print(tab + "<div id=\"midLine\"></div>\n");
        } else {
            pw.print(tab + "<img src=\"");
            pw.print(urlSafe(images[0].getName()));
            pw.print("\" id=\"fullWidthImage\">\n");
        }
        pw.print(tab + "<a href=\"../../index.html\">\n");
        pw.print(tab + tab + "<img src=\"../../House.svg\" id=\"homeIcon\">\n");
        pw.print(tab + "</a>\n");
        if(previous != null){
            pw.print(tab + "<a href=\"");
            pw.print(urlSafe(previous.getName()));
            pw.print("\">\n");
            pw.print(tab + tab + "<img src=\"../../Left.svg\" id=\"leftIcon\">\n");
            pw.print(tab + "</a>\n");
        }
        if(next != null){
            pw.print(tab + "<a href=\"");
            pw.print(urlSafe(next.getName()));
            pw.print("\">\n");
            pw.print(tab + tab + "<img src=\"../../Right.svg\" id=\"rightIcon\">\n");
            pw.print(tab + "</a>\n");
        }
        pw.print(tab + "<div id=\"infoText\">");
        pw.print(target.getParentFile().getName());
        pw.print("<br>#");
        pw.print(holeNum);
        pw.print("</div>\n</body>\n</html>");
        pw.close();
    }
}
