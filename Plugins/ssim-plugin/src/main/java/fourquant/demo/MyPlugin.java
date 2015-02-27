package fourquant.demo;

import net.imagej.Dataset;
import net.imglib2.meta.ImgPlus;
import net.imglib2.ops.img.UnaryOperationAssignment;
import net.imglib2.ops.operation.real.unary.RealInvert;
import net.imglib2.type.numeric.RealType;

import org.scijava.ItemIO;
import org.scijava.command.Command;
import org.scijava.plugin.Menu;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.widget.NumberWidget;

@Plugin(menu = {@Menu(label = "DeveloperPlugins"), @Menu(label = "MyPlugin")},
        description = "tests various parameter types", headless = true, type = Command.class)
public class MyPlugin<T extends RealType<T>> implements Command {
        @Parameter(label = "Boolean", type = ItemIO.INPUT)
        private boolean oBoolean;

        @Parameter(label = "Byte")
        private byte oByte;

        @Parameter(label = "Double")
        private double oDouble = 10.0;

        @Parameter(label = "Float")
        private float oFloat;

        @Parameter(label = "Integer")
        private int oInt;

        @Parameter(label = "Long")
        private long oLong;

        @Parameter(label = "Short")
        private short oShort;

        @Parameter(label = "String")
        private String string;

        @Parameter(label = "multiple choice", choices = {"The", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"})
        private String choice;

        // @Parameter
        // private File file;

        // @Parameter
        // private ColorRGB color;

        @Parameter(label = "spinner", style = NumberWidget.SPINNER_STYLE, min = "0", max = "1000")
        private Integer spinnerNumber;

        @Parameter(label = "slider", style = NumberWidget.SPINNER_STYLE, min = "0", max = "1000", stepSize = "50")
        private Integer sliderNumber;

        @Parameter(label = "scroll bar", style = NumberWidget.SCROLL_BAR_STYLE, min = "0", max = "1000")
        private Integer scrollBarNumber;

        @Parameter(required = true, persist = false, type = ItemIO.BOTH)
        private Dataset dataset;

        @Parameter(description = "Demonstrates preview functionality by " + "displaying the given message in the ImageJ status bar.")
        private String message = "Type a status message here.";

        @SuppressWarnings("unused")
        @Parameter(type = ItemIO.OUTPUT)
        private String output;

        @SuppressWarnings("unchecked")
        @Override
        public void run() {
                final StringBuilder sb = new StringBuilder();

                append(sb, "ParameterTester results:");

                append(sb, "");
                append(sb, "-- Toggles --");
                append(sb, "\tBoolean = " + oBoolean);

                append(sb, "");
                append(sb, "-- Numeric --");
                append(sb, "\tByte = " + oByte);
                append(sb, "\tDouble = " + oDouble);
                append(sb, "\tFloat = " + oFloat);
                append(sb, "\tInteger = " + oInt);
                append(sb, "\tLong = " + oLong);
                append(sb, "\tShort = " + oShort);

                append(sb, "");
                append(sb, "-- Text --");
                append(sb, "\tString = " + string);

                append(sb, "");
                append(sb, "-- Choice --");
                append(sb, "\tchoice = " + choice);

                append(sb, "");
                append(sb, "-- Object --");
                append(sb, "\tDataset = " + dataset);

                // append(sb, "");
                // append(sb, "-- File --");
                // append(sb, "\tFile = " + file);
                //
                // append(sb, "");
                // append(sb, "-- Color --");
                // append(sb, "\tcolor = " + color);

                append(sb, "");
                append(sb, "-- Miscellaneous --");
                append(sb, "\tspinner = " + spinnerNumber);
                append(sb, "\tslider = " + sliderNumber);
                append(sb, "\tscroll bar = " + scrollBarNumber);
                append(sb, "\tmessage = " + message);

                output = sb.toString();

                // invert the image
                final ImgPlus<T> imgPlus = (ImgPlus<T>) dataset.getImgPlus();

                new UnaryOperationAssignment<T, T>(new RealInvert<T, T>(imgPlus.firstElement().getMinValue(), imgPlus.firstElement().getMaxValue()))
                                .compute(imgPlus, imgPlus);

        }

        private void append(final StringBuilder sb, final String s) {
                sb.append(s + "\n");
        }

}
