package OOFramework.ReadWriteData;

import java.io.IOException;

//https://dzone.com/articles/java-singletons-using-enum
public enum SavedData {
    INSTANCE;

    private final DataReader dataReader;
    private final DataWriter dataWriter;

    //place stuff to be saved and loaded here

    SavedData() {
        this.dataReader = new DataReader();
        this.dataWriter = new DataWriter();
    }

    public void Save() {
        try {
            dataWriter.Save();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Load() {
        try {
            dataReader.Load();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public DataReader getDataReader() {
        return dataReader;
    }

    public DataWriter getDataWriter() {
        return dataWriter;
    }
}
