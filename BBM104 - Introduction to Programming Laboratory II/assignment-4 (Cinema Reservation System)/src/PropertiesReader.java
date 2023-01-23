import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * A class to read "properties.dat" file. Their properties are accessible by getter methods.
 */
public class PropertiesReader {

    // TODO Learn data types of fields. is it double or integer?

    private final String title;
    private final int maximumErrorWithoutGettingBlocked;
    private final double discountPercentage;
    private final int blockTime;

    public PropertiesReader(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);

            Properties properties = new Properties();
            properties.load(fileReader);

            maximumErrorWithoutGettingBlocked = Integer.parseInt(properties.getProperty("maximum-error-without-getting-blocked"));
            title = properties.getProperty("title");
            discountPercentage = Double.parseDouble(properties.getProperty("discount-percentage"));
            blockTime = Integer.parseInt(properties.getProperty("block-time"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTitle() {
        return title;
    }

    public int getMaximumErrorWithoutGettingBlocked() {
        return maximumErrorWithoutGettingBlocked;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public int getBlockTime() {
        return blockTime;
    }
}
