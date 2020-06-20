package OOFramework.ReadWriteData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static OOFramework.Modules.ASSERT_MSG.ASSERT_MSG;
import static OOFramework.Modules.ASSERT_MSG.ASSERT_MSG_TERMINATE;
import static OOFramework.Modules.CONSTANTS.STANDARD_SAVE_FILE_PATH;

public class DataReader {

    private SavedData savedData;
    private DataWriter dataWriter;

    protected DataReader() {
        savedData = SavedData.INSTANCE;
    }

    public void Load() throws IOException, ClassNotFoundException {
        ReadFile();
    }

    public void ReadFile() throws IOException, ClassNotFoundException {
        File file = new File(STANDARD_SAVE_FILE_PATH);

        if (!file.exists()) {
            dataWriter = new DataWriter();
            dataWriter.WriteToFile();
            ASSERT_MSG(!file.exists(), "FILE NOT FOUND AND UNABLE TO BE CREATED, " + this.getClass());
        } else if (!file.isFile()) {
            ASSERT_MSG_TERMINATE(!file.delete(), "INCORRECT FILE FOUND AND UNABLE TO CREATE A NEW FILE, " + this.getClass());
            dataWriter = new DataWriter();
            dataWriter.WriteToFile();
        }

        ASSERT_MSG_TERMINATE(!file.canRead(), "UNABLE TO READ FILE, " + this.getClass());

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Object readCase;

        /*
        ArrayList<MyData> myDatas = new ArrayList<MyData>();
        */

        do {
            readCase = (Object) ois.readObject();
            if (readCase != null) {
                /*if(readCase instanceof MyData)
                {
                    myDatas.add((MyData) readCase);
                }
                else
                {
                    ASSERT_MSG_TERMINATE(false,"LOADING AN UNKNOWN OBJECT"+this.getClass());
                }*/
            }
        } while (readCase != null);
        ois.close();

        /*
        savedData.setMyData(myDatas);
        */
    }
}