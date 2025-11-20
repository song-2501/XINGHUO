
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class 主界面 {
    public static void main(String[] args) {
        ArrayList<kebiao> list = new ArrayList<>();

        while (true) {
            System.out.println("1.添加课程");
            System.out.println("2.查看所有课程");
            System.out.println("3.查找课程");
            System.out.println("4.退出");
            Scanner sc = new Scanner(System.in);
            String choose = sc.next();
            switch (choose) {
                case "1" -> 添加课程(list);
                case "2" -> 查看所有课程(list);
                case "3" -> 查找课程(list);
                case "4" -> {
                    System.out.println("再见！");
                    System.exit(0);
                }
                default -> System.out.println("无此选项，重新输入");
            }
        }
    }

    public static void 添加课程(ArrayList<kebiao> list) {
        Scanner sc = null;
        try {
            sc = new Scanner(System.in);
            kebiao a = new kebiao();
            System.out.println("请输入课程名称");
            String name = sc.next();
            System.out.println("请输入上课时间");
            String time = sc.next();
            System.out.println("请输入教室");
            String classroom = sc.next();

            a.setClassroom(classroom);
            a.setName(name);
            a.setTime(time);
            list.add(a);
            System.out.println("课程添加成功");
        } catch (InputMismatchException e) {
            System.out.println("输入类型不匹配，请重新输入");
            sc.nextLine(); // 清空输入缓冲区
        }
    }

    public static void 查看所有课程(ArrayList<kebiao> list) {
        if (list.isEmpty()) {
            System.out.println("暂无课程信息");
            return;
        }

        System.out.println("课程名称" + "\t" + "上课时间" + "\t" + "教室");
        for (int i = 0; i < list.size(); i++) {
            kebiao ke = list.get(i);
            System.out.println(ke.getName() + "\t" + ke.getTime() + "\t" + ke.getClassroom());
        }
    }

    public static void 查找课程(ArrayList<kebiao> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入课程名称");
        String name = sc.next();
        boolean found = false;

        for (int i = 0; i < list.size(); i++) {
            kebiao ke = list.get(i);
            if (ke.getName().equals(name)) {
                System.out.println("课程名称" + "\t" + "上课时间" + "\t" + "教室");
                System.out.println(ke.getName() + "\t" + ke.getTime() + "\t" + ke.getClassroom());
                found = true;
                break; // 找到后可以退出循环
            }
        }

        if (!found) {
            System.out.println("无此课程");
        }
    }
}