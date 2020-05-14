package OOFramework.ReadWriteData;

import java.util.ArrayList;

//https://dzone.com/articles/java-singletons-using-enum
public enum SavedData {
    INSTANCE;

    /*
    private ArrayList<StudentData> studentData = new ArrayList<StudentData>();
    private ArrayList<TeacherData> teacherData = new ArrayList<TeacherData>();
    private ArrayList<LessonData> lessonData = new ArrayList<LessonData>();
    private ArrayList<GroupData> groupData = new ArrayList<GroupData>();

    // Getters and Setters
    public ArrayList<StudentData> getStudentData() {
        return studentData;
    }
    public void setStudentData(ArrayList<StudentData> studentData) {
        this.studentData = studentData;
    }

    public ArrayList<TeacherData> getTeacherData() {
        return teacherData;
    }
    public void setTeacherData(ArrayList<TeacherData> teacherData) {
        this.teacherData = teacherData;
    }

    public ArrayList<LessonData> getLessonData() {
        return lessonData;
    }
    public void setLessonData(ArrayList<LessonData> lessonData) {
        this.lessonData = lessonData;
    }

    public ArrayList<GroupData> getGroupData() {
        return groupData;
    }
    public void setGroupData(ArrayList<GroupData> groupData) {
        this.groupData = groupData;
    }


    public ArrayList<LessonData> getTeacherLessons(TeacherData teacher){
        ArrayList<LessonData> lessonsTeacher = new ArrayList<>();
        if(!lessonData.isEmpty()){
            for(LessonData lesson : lessonData){
                if(lesson.getTeacher().getName().equals(teacher.getName())){
                    lessonsTeacher.add(lesson);
                }
            }
            return lessonsTeacher;
        }
        else {
            return null;
        }
    }

    public ArrayList<LessonData> getClassroomLessons(ClassRoom classRoom){
        ArrayList<LessonData> lessonsClassroom = new ArrayList<>();
        if(this.lessonData.isEmpty()){
            for(LessonData lesson : this.lessonData){
                if(lesson.getClassRoom().getRoomName() == classRoom.getRoomName()){
                    lessonsClassroom.add(lesson);
                }
            }
            return lessonsClassroom;
        }
        else {
            return null;
        }
    }

    public ArrayList<LessonData> getStudentGroupLessons(GroupData studentGroup){
        ArrayList<LessonData> lessonsStudentGroup = new ArrayList<>();
        if(!this.lessonData.isEmpty()){
            for(LessonData lesson : lessonData){
                if(lesson.getStudentGroup().getName().equals(studentGroup.getName())){
                    lessonsStudentGroup.add(lesson);
                }
            }
            return lessonsStudentGroup;
        }
        else {
            return null;
        }
    }*/
}
