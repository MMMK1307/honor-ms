package AltStd;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Img {
    public static BufferedImage readImageFromPath(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch(Exception e) {
            return null;
        }
    }
}
