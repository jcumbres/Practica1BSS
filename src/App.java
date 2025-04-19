import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class App {
    public static void main(String[] args) {
        try {
            // Verificar que se pasó el argumento de la imagen
            if (args.length < 1) {
                System.err.println("Uso: java App <nombre_imagen>");
                return;
            }

            // Crear carpeta de salida si no existe
            File outputDir = new File("Image_out");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
                System.out.println("Carpeta 'Image_out' creada.");
            }

            // Cargar la imagen desde el argumento
            File imageFile = new File(args[0]);
            if (!imageFile.exists()) {
                System.err.println("Error: No se encontró la imagen " + args[0]);
                return;
            }

            BufferedImage image = ImageIO.read(imageFile);
            System.out.println("Imagen cargada correctamente.");

            // Convertir a escala de grises - Método A
            FingerPrintImage grayImageA = FingerPrintImage.convertToGrayscale(image, false);
            BufferedImage outputImageA = FingerPrintImage.toBufferedImage(grayImageA, false);
            ImageIO.write(outputImageA, "png", new File("Image_out/imagen_grises_A.png"));
            System.out.println("Imagen en escala de grises (promedio) guardada.");

            // Convertir a escala de grises - Método B
            FingerPrintImage grayImageB = FingerPrintImage.convertToGrayscale(image, true);
            BufferedImage outputImageB = FingerPrintImage.toBufferedImage(grayImageB, false);
            ImageIO.write(outputImageB, "png", new File("Image_out/imagen_grises_B.png"));
            System.out.println("Imagen en escala de grises (ponderado) guardada.");

            // Aplicar histograma
            FingerPrintImage histoImageA = new FingerPrintImage(image.getWidth(), image.getHeight());
            FingerPrintImage histoImageB = new FingerPrintImage(image.getWidth(), image.getHeight());
            FingerPrintImage.histograma(grayImageA, histoImageA);
            FingerPrintImage.histograma(grayImageB, histoImageB);
            BufferedImage outputHistoA = FingerPrintImage.toBufferedImage(histoImageA, false);
            BufferedImage outputHistoB = FingerPrintImage.toBufferedImage(histoImageB, false);
            ImageIO.write(outputHistoA, "png", new File("Image_out/imagen_histograma_A.png"));
            ImageIO.write(outputHistoB, "png", new File("Image_out/imagen_histograma_B.png"));
            System.out.println("Imágenes con histograma guardadas.");

        } catch (Exception e) {
            System.err.println("Ocurrió un error al procesar la imagen.");
            e.printStackTrace();
        }
    }
}
