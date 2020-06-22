package OOFramework.ReadWriteData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static OOFramework.Modules.ASSERT_MSG.ASSERT_MSG_TERMINATE;
import static OOFramework.Modules.CONSTANTS.STANDARD_SAVE_FILE_PATH;

public class DataWriter {

    private final SavedData savedData;

    protected DataWriter() {
        savedData = SavedData.INSTANCE;
    }

    public void Save() throws IOException, ClassNotFoundException {
        WriteToFile();
    }

    public void WriteToFile() throws IOException, ClassNotFoundException {
        File file = new File(STANDARD_SAVE_FILE_PATH);
        if (file.exists()) {
            ASSERT_MSG_TERMINATE(!file.delete(), "UNABLE TO DELETE OLD FILE, " + this.getClass());
        }

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        ASSERT_MSG_TERMINATE(!file.canWrite(), "UNABLE TO WRITE TO FILE, " + this.getClass());


        //ArrayList<MyData> myDatas = savedData.getMyData();

        /*for(MyData md : myDatas)
        {
            oos.writeObject(md);
        }*/

        //this null object lets the data writer know that it is the end of the save file
        oos.writeObject(null);
        oos.close();
    }
}