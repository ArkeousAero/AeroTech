import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtils
{
    public static void mkdir(String file)
    {
        new File(file).mkdir();
    }

    public static String loadFile(String file)
    {
        String value = "ERROR";
        try
        {
            FileInputStream in = new FileInputStream(file);
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("!@#$%^&*()_+");
            value = scanner.next();
            in.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        return value;
    }

    public static void saveFile(String file, String contents)
    {
        try
        {
            FileOutputStream out = new FileOutputStream(file);
            PrintWriter printer = new PrintWriter(out);
            printer.print(contents);
            printer.flush();
            printer.close();
            out.close();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
}
