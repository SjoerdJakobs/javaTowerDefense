package OOFramework.ReadWriteData;

import java.io.*;
import java.util.ArrayList;

import static OOFramework.Modules.ASSERT_MSG.ASSERT_MSG_TERMINATE;
import static OOFramework.Modules.CONSTANTS.STANDARD_SAVE_FILE_PATH;

public class DataWriter {

    private SavedData savedData;

    public DataWriter()
    {
        savedData = SavedData.INSTANCE;
    }

    public void Save() throws IOException, ClassNotFoundException
    {
        WriteToFile();
    }

    public void WriteToFile() throws IOException, ClassNotFoundException
    {
        File file = new File(STANDARD_SAVE_FILE_PATH);
        if(file.exists())
        {
            ASSERT_MSG_TERMINATE(!file.delete(),"UNABLE TO DELETE OLD FILE, "+this.getClass());
        }

        FileOutputStream fos   = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        ASSERT_MSG_TERMINATE(!file.canWrite(),"UNABLE TO WRITE TO FILE, "+this.getClass());

        /*
        ArrayList<TeacherData> teacherDatas = savedData.getTeacherData();
        ArrayList<StudentData> studentDatas = savedData.getStudentData();
        ArrayList<LessonData>   lessonDatas = savedData.getLessonData();
        ArrayList<GroupData>     groupDatas = savedData.getGroupData();

        for(TeacherData td : teacherDatas)
        {
            oos.writeObject(td);
        }
        for(StudentData sd : studentDatas)
        {
            oos.writeObject(sd);
        }
        for(LessonData ld : lessonDatas)
        {
            oos.writeObject(ld);
        }
        for(GroupData gd : groupDatas)
        {
            oos.writeObject(gd);
        }
        */

        //this null object lets the data writer know that it is the end of the save file
        oos.writeObject(null);
        oos.close();
    }
}