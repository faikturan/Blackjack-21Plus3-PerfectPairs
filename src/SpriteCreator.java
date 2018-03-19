import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Rene Sommerfeld on 15.03.2018.
 */
public class SpriteCreator {

    public static void main(String[] args) {

        String[] paths = new String[] {
            "10.png", "bube.png", "dame.png", "koenig.png", "ass.png",
            "vogel.png", "pharao.png", "schakal.png", "rich_wilde.png", "buch.png"
        };
        int[] imgCount = new int[]{15, 15, 15, 15, 15, 8, 8, 8, 5, 10};

        BufferedImage result = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);

        int x = 0;
        int y = 0;
        int maxHeight = 0;
        int span = 5;
        for(int i = 0; i < paths.length; i++) {
            try {
                String currentPath = paths[i];
                BufferedImage currentImage = ImageIO.read(new File(currentPath));
                int width = currentImage.getWidth();
                int height = currentImage.getHeight();

                if(height > maxHeight) {
                    maxHeight = height;
                }

                for(int c = 0; c < imgCount[i]; c++) {

                    if(x + width + span > result.getWidth()) {
                        y += maxHeight + span;
                        x = 0;
                    }

                    for(int j = 0; j < width; j++) {
                        for(int k = 0; k < height; k++) {
                            int rgb = currentImage.getRGB(j, k);
                            result.setRGB(x + j, y + k, rgb);
                        }
                    }

                    x += width + span;
                }
                //to make every single symbol type apprearing on a single line
                x += result.getWidth();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        File outputFile = new File("result.png");
        try {
            ImageIO.write(result, "png", outputFile);
        } catch (IOException e1) {

        }


    }

}
