
public class kebiao {
    private String name;
    private String classroom;
    private String time;

    public kebiao() {
    }

    public kebiao(String name, String classroom, String time) {
        this.name = name;
        this.time = time;
        this.classroom = classroom;
    }

    public String getName() {
        return name;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
