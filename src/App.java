import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class App {
    public static void main(String[] args) {
        try {
            // 1. Cargar la imagen de entrada
            BufferedImage image = ImageIO.read(new File("imagen.png"));
            System.out.println("Imagen cargada correctamente.");

            // 2. Convertir la imagen a escala de grises (Método A: Promedio)
            FingerPrintImage grayImageA = FingerPrintImage.convertToGrayscale(image, false);
            BufferedImage outputImageA = FingerPrintImage.toBufferedImage(grayImageA, false);
            ImageIO.write(outputImageA, "png", new File("imagen_grises_A.png"));
            System.out.println("Imagen en escala de grises (promedio) guardada.");

            // 3. Convertir la imagen a escala de grises (Método B: Ponderado)
            FingerPrintImage grayImageB = FingerPrintImage.convertToGrayscale(image, true);
            BufferedImage outputImageB = FingerPrintImage.toBufferedImage(grayImageB, false);
            ImageIO.write(outputImageB, "png", new File("imagen_grises_B.png"));
            System.out.println("Imagen en escala de grises (ponderado) guardada.");

        } catch (Exception e) {
            System.err.println("tienes que meter una imagen en la carpeta del proyecto");
            e.printStackTrace();
        }
    }
}
