import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface genericMethodsInterface<T> {
    public default String viewList(ArrayList<T> g){
        String printItems = "";
        for (int i = 0; i < g.size(); i++) {
            printItems = printItems + (i + ")" + g.get(i).toString()) + "\n";
        }
        return  printItems;
    }

    public default void checkIndex(ArrayList<T> g, int itemNum)
    {
        if(itemNum > g.size() || itemNum < 0)
        {
            throw new IndexOutOfBoundsException();
        }
    }
    public default int countNumLinesInLoadFile(File fileName)
    {
        try {
            FileInputStream f = new FileInputStream(fileName);
            byte[] byteA = new byte[(int) fileName.length()];
            f.read(byteA);
            String info = new String(byteA);
            String[] infoArray = info.split("\r\n");
            return infoArray.length;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    default void removeAll(ArrayList<T> g)
    {
        g.clear();
    }
}
