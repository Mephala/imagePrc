package imgprc;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class SharpenImage {

	public static void main(String[] args) {
		try {
			System.out.println(System.getProperty("java.library.path"));
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			Mat source = Highgui.imread("digital_image_processing.jpg", Highgui.CV_LOAD_IMAGE_COLOR);
			Mat destination = new Mat(source.rows(), source.cols(), source.type());
			Imgproc.GaussianBlur(source, destination, new Size(0, 0), 10);
			Core.addWeighted(source, 1.5, destination, -0.5, 0, destination);
			Highgui.imwrite("sharp.jpg", destination);
		} catch (Exception e) {
		}
	}

}
