package imgprc;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EdgeDetection {

	public static void main(String[] args) throws IOException {
		File input = new File("Lenna.png");
		BufferedImage source = ImageIO.read(input);
		float ninth = 1.0f / 9.0f;
		float[] edgeKernel = { ninth, ninth, ninth, ninth, ninth, ninth, ninth, ninth, ninth };
		// float[] edgeKernel = { 0.0f, -1.0f, 0.0f, -1.0f, 4.0f, -1.0f, 0.0f, -1.0f, 0.0f };
		BufferedImageOp sharpen = new ConvolveOp(new Kernel(3, 3, edgeKernel), ConvolveOp.EDGE_NO_OP, null);
		BufferedImage destination = sharpen.filter(source, null);
		File ouptut = new File("Lenna3.png");
		ImageIO.write(destination, "png", ouptut);
		int limit = 10;
		for (int i = 0; i < limit; i++) {
			source = ImageIO.read(ouptut);
			destination = sharpen.filter(source, null);
			ouptut = new File("Lenna3.png");
			ImageIO.write(destination, "png", ouptut);
		}

		BufferedImage image = ImageIO.read(new File("Lenna3.png"));
		BufferedImage overlay = ImageIO.read(new File("Lenna3.png"));

		// create the new image, canvas size is the max. of both image sizes
		int w = Math.max(image.getWidth(), overlay.getWidth());
		int h = Math.max(image.getHeight(), overlay.getHeight());
		BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		// paint both images, preserving the alpha channels
		Graphics g = combined.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.drawImage(overlay, 10, 0, null);

		ImageIO.write(combined, "PNG", new File("Lenna3wm.png"));

	}
}
