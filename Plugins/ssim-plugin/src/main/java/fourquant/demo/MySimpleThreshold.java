package fourquant.demo;
import net.imagej.ImgPlus;
import net.imglib2.Cursor;
import net.imglib2.RandomAccess;
import net.imglib2.img.array.ArrayImgFactory;
import net.imglib2.type.logic.BitType;
import net.imglib2.type.numeric.RealType;

import org.scijava.ItemIO;
import org.scijava.command.Command;
import org.scijava.plugin.Menu;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

@Plugin(menu = { @Menu(label = "DeveloperPlugins"),
		@Menu(label = "MyThresholder") }, description = "Very simple thresholder", headless = true, type = Command.class)
public class MySimpleThreshold<T extends RealType<T>> implements Command {

        @Parameter(type = ItemIO.INPUT)
        private ImgPlus<T> input;

        @Parameter(type = ItemIO.INPUT, label = "My Threshold")
        private double manualThreshold = 50;

        @Parameter(type = ItemIO.OUTPUT)
        private ImgPlus<BitType> output;

        @Override
        public void run() {
                // create empty output image (arrayimg of type bittype)
                output = new ImgPlus<BitType>(new ArrayImgFactory<BitType>().create(input, new BitType()));

                // access the pixels of the output image
                final RandomAccess<BitType> outAccess = output.randomAccess();

                // cursor over input image
                final Cursor<T> inCursor = input.localizingCursor();
                
                System.out.println("HELLO");

                // iterate over pixels of in input image
                while (inCursor.hasNext()) {
                        inCursor.fwd();

                        // set outaccess on position of incursor
                        outAccess.setPosition(inCursor);

                        // set pixel value of output true, if pixel value of incoming image > manual threshold
                        outAccess.get().set(inCursor.get().getRealDouble() > manualThreshold);
                }
        }
}
