import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.print.PrinterException;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static String URL = "https://lenta.ru";
    public static void main(String[] args){
        try {
            Document document = Jsoup.connect(URL).get();
            Elements selectElements = document.select("img");
            selectElements.forEach(e->System.out.println(e.attr("src")));
            List<String> images = selectElements.stream().map(e->e.attr("src")).collect(Collectors.toList());
            for (int i = 0; i < images.size(); i++){
                String name = i + ".jpg";
                java.net.URL url = new URL(images.get(i));
                InputStream in = url.openStream();
                OutputStream out = new BufferedOutputStream(new FileOutputStream("imagesDownloads/" + name));
                for (int b; (b = in.read()) != -1;){
                    out.write(b);
                }
                out.close();
                in.close();
            }

        } catch (IOException e){
            System.out.println(e);
        }
    }
}
