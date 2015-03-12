package fourquant.demo;

import net.imagej.ImgPlus;
import net.imglib2.type.numeric.RealType;

import org.scijava.ItemIO;
import org.scijava.command.Command;
import org.scijava.plugin.Menu;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/*
 * Notes for fragment plugins
 * 
 * just wrap the plugin in a plugin fragment of imagej.core
 */

@Plugin(menu = { @Menu(label = "DeveloperPlugins"), @Menu(label = "SSIM") }, description = "A simple plugin that calculates SSIM", headless = true, type = Command.class)
public class SSIMCalculator<T extends RealType<T>> implements Command {

	@Parameter(label = "SSIM", type = ItemIO.OUTPUT)
	private double ssim;

	@Parameter(label = "Comparison Image", type = ItemIO.INPUT)
	private ImgPlus<T> comparisonImage;

	@Parameter(label = "Gold Standard/Original Image", type = ItemIO.INPUT)
	private ImgPlus<T> goldStandard;

	@Parameter(label = "Gaussian Sigma")
	double sigma_gauss = 1.5;

	@Parameter(label = "Filter Width")
	int filter_width = 11;

	@Parameter(label = "K1")
	double K1 = 0.01;
	@Parameter(label = "K2")
	double K2 = 0.03;

	// @Parameter(type = ItemIO.INPUT)
	double lod[] = { 0.0378, -0.0238, -0.1106, 0.3774, 0.8527, 0.3774, -0.1106,
			-0.0238, 0.0378 };

	@Override
	public void run() {
		ssim = 0;
	}

}