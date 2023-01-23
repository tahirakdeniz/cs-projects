import java.io.IOException;

/**
 * Class that extends FileCreator to write outputString to a file.
 * */
public class Output extends FileCreator {

    public Output(String filePath) {
        super(filePath);
    }

    public void write(String str){
        try {
            getFileWriter().write(str);
            getFileWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
