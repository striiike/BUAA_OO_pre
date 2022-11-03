import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Teacher> teachers = new ArrayList<>();
        TreeMap<String, Subject> subjects = new TreeMap<>();
        boolean TeaLogin = false;
        boolean StuLogin = false;
        boolean isAssistant = false;
        String chosenCourse = "This is initial Course";
        String logId = "This is initial ID";
        while (true) {
            String s = sc.nextLine();
            //
            //System.out.println(s + " ");
            //
            String[] strs = s.split("\\s+");
            String order = strs[0];
            switch (order) {
                case "QUIT" -> {
                    if (strs.length != 1) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    System.out.println("----- Good Bye! -----");
                    return;
                }
                case "register" -> {
                    if (strs.length != 7) {
                        System.out.println("arguments illegal");
                        break;
                    } else if (TeaLogin || StuLogin) {
                        System.out.println("already logged in");
                        break;
                    }
                    //学号判断
                    String id = strs[1];
                    boolean ifID = ifIDright(id);
                    if (!ifID) {
                        System.out.println("user id illegal");
                        break;
                    }
                    boolean findFlag = false;
                    if (id.length() == 5) {
                        int i;
                        for (i = 0; i < teachers.size(); i++) {
                            Teacher a = teachers.get(i);
                            if (a.id.equals(id)) {
                                findFlag = true;
                                break;
                            }
                        }
                    } else {
                        int i;
                        for (i = 0; i < students.size(); i++) {
                            Student a = students.get(i);
                            if (a.id.equals(id)) {
                                findFlag = true;
                                break;
                            }
                        }
                    }
                    if (findFlag) {
                        System.out.println("user id duplication");
                        break;
                    }
                    //姓名判断
                    String firstName = strs[2];
                    String lastName = strs[3];
                    if (!ifName(firstName, lastName)) {
                        System.out.println("user name illegal");
                        break;
                    }
                    //邮箱判断
                    String email = strs[4];
                    if (!ifEmail(email)) {
                        System.out.println("email address illegal");
                        break;
                    }
                    //密码判断
                    String password = strs[5];
                    String passwordSec = strs[6];
                    if (!ifPassword(password)) {
                        System.out.println("password illegal");
                        break;
                    }
                    if (!password.equals(passwordSec)) {
                        System.out.println("passwords inconsistent");
                        break;
                    }
                    //注册成功
                    if (id.length() == 5) {
                        Teacher a = new Teacher(id, firstName, lastName, email, password);
                        teachers.add(a);
                        System.out.println("register success");
                    } else {
                        Student a = new Student(id, firstName, lastName, email, password);
                        students.add(a);
                        System.out.println("register success");
                    }
                }
                case "login" -> {
                    if (strs.length != 3) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    String id = strs[1];
                    String password = strs[2];
                    if (TeaLogin || StuLogin) {
                        System.out.println("already logged in");
                        break;
                    }
                    if (!ifIDright(id)) {
                        System.out.println("user id illegal");
                        break;
                    }
                    if (id.length() == 5) {
                        boolean findFlag = false;
                        int i;
                        for (i = 0; i < teachers.size(); i++) {
                            Teacher a = teachers.get(i);
                            if (a.id.equals(id)) {
                                findFlag = true;
                                break;
                            }
                        }
                        if (!findFlag) {
                            System.out.println("user id not exist");
                            break;
                        }
                        Teacher a = teachers.get(i);
                        if (!a.password.equals(password)) {
                            System.out.println("wrong password");
                        } else {
                            System.out.println("Hello Professor " + a.lastName + "~");
                            TeaLogin = true;
                            logId = a.id;
                        }
                    } else {
                        {
                            boolean findFlag = false;
                            int i;
                            for (i = 0; i < students.size(); i++) {
                                Student a = students.get(i);
                                if (a.id.equals(id)) {
                                    findFlag = true;
                                    break;
                                }
                            }
                            if (!findFlag) {
                                System.out.println("user id not exist");
                                break;
                            }
                            Student a = students.get(i);
                            if (!a.password.equals(password)) {
                                System.out.println("wrong password");
                            } else {
                                System.out.println("Hello " + a.firstName + "~");
                                StuLogin = true;
                                logId = a.id;
                            }
                        }
                    }
                }
                case "printInfo" -> {
                    if (!TeaLogin && !StuLogin) {
                        System.out.println("login first");
                    }
                    if (StuLogin) {
                        if (strs.length == 2) {
                            System.out.println("permission denied");
                        } else if (strs.length > 2) {
                            System.out.println("arguments illegal");
                        } else {
                            int i;
                            for (i = 0; i < students.size(); i++) {
                                Student a = students.get(i);
                                if (a.id.equals(logId)) {
                                    break;
                                }
                            }
                            System.out.println(students.get(i));
                        }
                    } else if (TeaLogin) {
                        if (strs.length == 1) {
                            int i;
                            for (i = 0; i < teachers.size(); i++) {
                                Teacher a = teachers.get(i);
                                if (a.id.equals(logId)) {
                                    break;
                                }
                            }
                            System.out.println(teachers.get(i));
                            break;
                        } else if (strs.length > 2) {
                            System.out.println("arguments illegal");
                            break;
                        }
                        String searchId = strs[1];
                        if (!ifIDright(searchId)) {
                            System.out.println("user id illegal");
                            break;
                        }
                        boolean findFlag = false;
                        if (searchId.length() == 5) {
                            int i;
                            for (i = 0; i < teachers.size(); i++) {
                                Teacher a = teachers.get(i);
                                if (a.id.equals(searchId)) {
                                    findFlag = true;
                                    break;
                                }
                            }
                            if (!findFlag) {
                                System.out.println("user id not exist");
                                break;
                            }
                            System.out.println(teachers.get(i));
                        } else {
                            int i;
                            for (i = 0; i < students.size(); i++) {
                                Student a = students.get(i);
                                if (a.id.equals(searchId)) {
                                    findFlag = true;
                                    break;
                                }
                            }
                            if (!findFlag) {
                                System.out.println("user id not exist");
                                break;
                            }
                            System.out.println(students.get(i));
                        }
                    }
                }
                case "logout" -> {
                    if (strs.length != 1) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    if (TeaLogin || StuLogin) {
                        System.out.println("Bye~");
                        TeaLogin = false;
                        StuLogin = false;
                        isAssistant = false;
                        chosenCourse = null;
                    } else {
                        System.out.println("not logged in");
                    }
                }
                case "addCourse" -> {
                    if (strs.length != 3) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    if (!TeaLogin && !StuLogin) {
                        System.out.println("not logged in");
                        break;
                    }
                    if (!TeaLogin) {
                        System.out.println("permission denied");
                        break;
                    }
                    if (!isClassNum(strs[1])) {
                        System.out.println("course id illegal");
                        break;
                    }
                    if (subjects.get(strs[1]) != null) {
                        System.out.println("course id duplication");
                        break;
                    }
                    if (!isClassName(strs[2])) {
                        System.out.println("course name illegal");
                        break;
                    }
                    int num = findTeacher(teachers, logId);
                    Teacher tea = teachers.get(num);
                    Subject a = new Subject(strs[1], strs[2]);
                    a.teacherID.add(logId);
                    tea.haveSubject.add(strs[1]);
                    teachers.set(num, tea);
                    subjects.put(strs[1], a);
                    System.out.println("add course success");
                }
                case "removeCourse" -> {
                    if (strs.length != 2) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    if (!TeaLogin && !StuLogin) {
                        System.out.println("not logged in");
                        break;
                    }
                    if (!TeaLogin) {
                        System.out.println("permission denied");
                        break;
                    }
                    if (!isClassNum(strs[1])) {
                        System.out.println("course id illegal");
                        break;
                    }
                    int num = findTeacher(teachers, logId);
                    Teacher tea = teachers.get(num);
                    int num2 = findSubjectTea(tea, strs[1]);
                    if (num2 == -1) {
                        System.out.println("course id not exist");
                        break;
                    }
                    tea.haveSubject.remove(num2);
                    teachers.set(num, tea);
                    subjects.remove(strs[1]);
                    System.out.println("remove course success");
                }
                case "listCourse" -> {
                    if (strs.length != 1) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    if (!TeaLogin && !StuLogin) {
                        System.out.println("not logged in");
                        break;
                    }
                    if (!TeaLogin) {
                        System.out.println("permission denied");
                        break;
                    }
                    int num = findTeacher(teachers, logId);
                    Teacher tea = teachers.get(num);
                    if (tea.haveSubject.size() == 0) {
                        System.out.println("course not exist");
                        break;
                    }
                    TreeMap<String, Subject> Sub = new TreeMap<>();
                    for (String a : tea.haveSubject) {
                        Sub.put(a, subjects.get(a));
                    }
                    for (Map.Entry<String, Subject> e : Sub.entrySet()) {
                        System.out.println(e.getValue());//只得到Value值
                    }
                }
                case "selectCourse" -> {
                    if (strs.length != 2) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    if (!TeaLogin && !StuLogin) {
                        System.out.println("not logged in");
                        break;
                    }
                    if (!TeaLogin && !isAssistant) {
                        System.out.println("permission denied");
                        break;
                    }
                    if (!isClassNum(strs[1])) {
                        System.out.println("course id illegal");
                        break;
                    }
                    if (logId.length() == 5) {
                        int num = findTeacher(teachers, logId);
                        Teacher tea = teachers.get(num);
                        int num2 = findSubjectTea(tea, strs[1]);
                        if (num2 == -1) {
                            System.out.println("course id not exist");
                            break;
                        }
                        chosenCourse = strs[1];
                        System.out.println("select course success");
                    } else {
                        //助教端选择课程
                        int num = findStudent(students, logId);
                        Student stu = students.get(num);
                        ////判断是否存在
                        chosenCourse = strs[1];
                        System.out.println("select course success");
                    }
                    ///////
                }
                case "addAdmin" -> {
                    if (strs.length == 1) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    if (!TeaLogin && !StuLogin) {
                        System.out.println("not logged in");
                        break;
                    }
                    if (!TeaLogin) {
                        System.out.println("permission denied");
                        break;
                    }
                    if (chosenCourse == null) {
                        System.out.println("no course selected");
                        break;
                    }
                    boolean flag = false;
                    for (int i = 1; i < strs.length; i++) {
                        if (!ifIDright(strs[i])) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        System.out.println("user id illegal");
                        break;
                    }
                    for (int i = 1; i < strs.length; i++) {
                        int num;
                        if (strs[i].length() == 5) {
                            num = findTeacher(teachers, strs[i]);
                        } else {
                            num = findStudent(students, strs[i]);
                        }
                        if (num == -1) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        System.out.println("user id not exist");
                        break;
                    }
                    Subject a = subjects.get(chosenCourse);
                    for (int i = 1; i < strs.length; i++) {
                        boolean ad = false;
                        if (strs[i].length() == 5) {
                            for (String temp : a.teacherID) {
                                if (temp.equals(strs[i])) {
                                    ad = true;
                                    break;
                                }
                            }
                            if (!ad) {
                                a.teacherID.add(strs[i]);
                            }
                        } else {
                            for (String temp : a.assistantID) {
                                if (temp.equals(strs[i])) {
                                    ad = true;
                                    break;
                                }
                            }
                            if (!ad) {
                                a.assistantID.add(strs[i]);
                            }
                        }
                    }
                    subjects.replace(chosenCourse, a);
                    System.out.println("add admin success");
                }
                case "removeAdmin" -> {
                    if (strs.length != 2) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    if (!TeaLogin && !StuLogin) {
                        System.out.println("not logged in");
                        break;
                    }
                    if (!TeaLogin) {
                        System.out.println("permission denied");
                        break;
                    }
                    if (chosenCourse == null) {
                        System.out.println("no course selected");
                        break;
                    }
                    if (!ifIDright(strs[1])) {
                        System.out.println("user id illegal");
                        break;
                    }
                    Subject a = subjects.get(chosenCourse);
                    boolean flag = false;
                    int num = 0;
                    if (strs[1].length() == 5) {
                        for (String temp : a.teacherID) {
                            if (temp.equals(strs[1])) {
                                flag = true;
                                break;
                            }
                            num++;
                        }
                        if (!flag) {
                            System.out.println("user id not exist");
                            break;
                        }
                        a.teacherID.remove(num);
                    } else {
                        for (String temp : a.assistantID) {
                            if (temp.equals(strs[1])) {
                                flag = true;
                                break;
                            }
                            num++;
                        }
                        if (!flag) {
                            System.out.println("user id not exist");
                            break;
                        }
                        a.assistantID.remove(num);
                    }
                    subjects.replace(chosenCourse, a);
                    System.out.println("remove admin success");
                }
                case "listAdmin" -> {
                    if (strs.length != 1) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    if (!TeaLogin && !StuLogin) {
                        System.out.println("not logged in");
                        break;
                    }
                    if (!TeaLogin && !isAssistant) {
                        System.out.println("permission denied");
                        break;
                    }
                    if (chosenCourse != null && logId.length() != 5) {
                        Subject sub = subjects.get(chosenCourse);
                        if (!sub.assistantID.contains(logId)) {
                            System.out.println("permission denied");
                            break;
                        }
                    }
                    if (chosenCourse == null) {
                        System.out.println("no course selected");
                        break;
                    }
                    Subject a = subjects.get(chosenCourse);
                    ArrayList<String> tmp = new ArrayList<>();
                    tmp.addAll(a.assistantID);
                    tmp.addAll(a.teacherID);
                    Collections.sort(tmp);
                    for (String temp : tmp) {
                        if (temp.length() == 5) {
                            int num = findTeacher(teachers, temp);
                            Teacher tea = teachers.get(num);
                            System.out.println("[ID:" + temp + "] [Name:" + tea.lastName + " " + tea.firstName + "] [Type:Professor] [Email:" + tea.email + "]");
                        } else {
                            int num = findStudent(students, temp);
                            Student stu = students.get(num);
                            System.out.println("[ID:" + temp + "] [Name:" + stu.lastName + " " + stu.firstName + "] [Type:Assistant] [Email:" + stu.email + "]");
                        }
                    }
                }
                case "changeRole" -> {
                    if (strs.length != 1) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    if (!TeaLogin && !StuLogin) {
                        System.out.println("not logged in");
                        break;
                    }
                    boolean flag = false;
                    for (Map.Entry<String, Subject> e : subjects.entrySet()) {
                        if (e.getValue().assistantID.contains(logId)) {
                            flag = true;
                            break;
                        }
                    }
                    if (!StuLogin || !flag) {
                        System.out.println("permission denied");
                        break;
                    }
                    if (isAssistant) {
                        isAssistant = false;
                        System.out.println("change into Student success");
                    } else {
                        isAssistant = true;
                        System.out.println("change into Assistant success");
                    }
                }
                case "addWare" -> {
                    if (strs.length != 3) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    if (!TeaLogin && !StuLogin) {
                        System.out.println("not logged in");
                        break;
                    }
                    if (!TeaLogin) {
                        System.out.println("permission denied");
                        break;
                    }
                    if (chosenCourse != null && logId.length() != 5) {
                        Subject sub = subjects.get(chosenCourse);
                        if (!sub.assistantID.contains(logId)) {
                            System.out.println("permission denied");
                            break;
                        }
                    }
                    if (chosenCourse == null) {
                        System.out.println("no course selected");
                        break;
                    }
                    if (!isWareNum(chosenCourse, strs[1])) {
                        System.out.println("ware id illegal");
                        break;
                    }
                    Subject sub = subjects.get(chosenCourse);
                    if (sub.wares.get(strs[1]) != null) {
                        System.out.println("ware id duplication");
                        break;
                    }
                    if (!isWareName(strs[2])) {
                        System.out.println("ware name illegal");
                        break;
                    }
                    Ware ware = new Ware(strs[1], strs[2]);
                    sub.wares.put(strs[1], ware);
                    subjects.replace(chosenCourse, sub);
                    System.out.println("add ware success");
                }
                case "removeWare" -> {
                    if (strs.length != 2) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    if (!TeaLogin && !StuLogin) {
                        System.out.println("not logged in");
                        break;
                    }
                    if (!TeaLogin) {
                        System.out.println("permission denied");
                        break;
                    }
                    if (chosenCourse != null && logId.length() != 5) {
                        Subject sub = subjects.get(chosenCourse);
                        if (!sub.assistantID.contains(logId)) {
                            System.out.println("permission denied");
                            break;
                        }
                    }
                    if (chosenCourse == null) {
                        System.out.println("no course selected");
                        break;
                    }
                    if (!isWareNum(chosenCourse, strs[1])) {
                        System.out.println("ware id illegal");
                        break;
                    }
                    Subject sub = subjects.get(chosenCourse);
                    if (sub.wares.get(strs[1]) == null) {
                        System.out.println("ware id not exist");
                        break;
                    }
                    sub.wares.remove(strs[1]);
                    subjects.replace(chosenCourse, sub);
                    System.out.println("remove ware success");
                }
                case "listWare" -> {
                    if (strs.length != 1) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    if (!TeaLogin && !StuLogin) {
                        System.out.println("not logged in");
                        break;
                    }
                    if (!TeaLogin && !isAssistant) {
                        System.out.println("permission denied");
                        break;
                    }
                    if (chosenCourse != null && logId.length() != 5) {
                        Subject sub = subjects.get(chosenCourse);
                        if (!sub.assistantID.contains(logId)) {
                            System.out.println("permission denied");
                            break;
                        }
                    }
                    if (chosenCourse == null) {
                        System.out.println("no course selected");
                        break;
                    }
                    Subject sub = subjects.get(chosenCourse);
                    for (Map.Entry<String, Ware> e : sub.wares.entrySet()) {
                        System.out.println(e.getValue());//只得到Value值
                    }
                }
                case "addTask" -> {
                    if (strs.length != 5) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    if (!TeaLogin && !StuLogin) {
                        System.out.println("not logged in");
                        break;
                    }
                    if (!TeaLogin && !isAssistant) {
                        System.out.println("permission denied");
                        break;
                    }
                    if (chosenCourse != null && logId.length() != 5) {
                        Subject sub = subjects.get(chosenCourse);
                        if (!sub.assistantID.contains(logId)) {
                            System.out.println("permission denied");
                            break;
                        }
                    }
                    if (chosenCourse == null) {
                        System.out.println("no course selected");
                        break;
                    }
                    if (!isTaskNum(chosenCourse, strs[1])) {
                        System.out.println("task id illegal");
                        break;
                    }
                    Subject sub = subjects.get(chosenCourse);
                    if (sub.tasks.get(strs[1]) != null) {
                        System.out.println("task id duplication");
                        break;
                    }
                    if (!isTaskName(strs[2])) {
                        System.out.println("task name illegal");
                        break;
                    }
                    if (!isTaskTime(strs[3]) || !isTaskTime(strs[4]) || !taskTimeCompare(strs[3], strs[4])) {
                        System.out.println("task time illegal");
                        break;
                    }
                    Task ta = new Task(strs[1], strs[2], strs[3], strs[4]);
                    sub.tasks.put(strs[1], ta);
                    subjects.replace(chosenCourse, sub);
                    System.out.println("add task success");
                }
                case "removeTask" -> {
                    if (strs.length != 2) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    if (!TeaLogin && !StuLogin) {
                        System.out.println("not logged in");
                        break;
                    }
                    if (!TeaLogin && !isAssistant) {
                        System.out.println("permission denied");
                        break;
                    }
                    if (chosenCourse != null && logId.length() != 5) {
                        Subject sub = subjects.get(chosenCourse);
                        if (!sub.assistantID.contains(logId)) {
                            System.out.println("permission denied");
                            break;
                        }
                    }
                    if (chosenCourse == null) {
                        System.out.println("no course selected");
                        break;
                    }
                    if (!isTaskNum(chosenCourse, strs[1])) {
                        System.out.println("task id illegal");
                        break;
                    }
                    Subject sub = subjects.get(chosenCourse);
                    if (sub.tasks.get(strs[1]) == null) {
                        System.out.println("task id not exist");
                        break;
                    }
                    sub.tasks.remove(strs[1]);
                    System.out.println("remove task success");
                }
                case "listTask" -> {
                    if (strs.length != 1) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    if (!TeaLogin && !StuLogin) {
                        System.out.println("not logged in");
                        break;
                    }
                    if (!TeaLogin && !isAssistant) {
                        System.out.println("permission denied");
                        break;
                    }
                    if (chosenCourse != null && logId.length() != 5) {
                        Subject sub = subjects.get(chosenCourse);
                        if (!sub.assistantID.contains(logId)) {
                            System.out.println("permission denied");
                            break;
                        }
                    }
                    if (chosenCourse == null) {
                        System.out.println("no course selected");
                        break;
                    }
                    Subject sub = subjects.get(chosenCourse);
                    for (Map.Entry<String, Task> e : sub.tasks.entrySet()) {
                        System.out.println(e.getValue());//只得到Value值
                    }
                }
                case "addStudent" -> {
                    if (strs.length == 1) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    if (!TeaLogin && !StuLogin) {
                        System.out.println("not logged in");
                        break;
                    }
                    if (!TeaLogin && !isAssistant) {
                        System.out.println("permission denied");
                        break;
                    }
                    if (chosenCourse != null && logId.length() != 5) {
                        Subject sub = subjects.get(chosenCourse);
                        if (!sub.assistantID.contains(logId)) {
                            System.out.println("permission denied");
                            break;
                        }
                    }
                    if (chosenCourse == null) {
                        System.out.println("no course selected");
                        break;
                    }
                    boolean flag1 = false;
                    boolean flag2 = false;
                    boolean flag3 = false;
                    for (int i = 1; i < strs.length; i++) {
                        if (!ifIDright(strs[i])) {
                            flag1 = true;
                            break;
                        } else if (strs[i].length() == 5) {
                            flag2 = true;
                            break;
                        }
                        int num = findStudent(students, strs[i]);
                        if (num == -1) {
                            flag3 = true;
                            break;
                        }
                    }
                    if (flag1) {
                        System.out.println("user id illegal");
                        break;
                    } else if (flag2) {
                        System.out.println("I'm professor rather than student!");
                        break;
                    } else if (flag3) {
                        System.out.println("user id not exist");
                        break;
                    }
                    Subject a = subjects.get(chosenCourse);
                    for (int i = 1; i < strs.length; i++) {
                        boolean ad = false;
                        for (String temp : a.studentID) {
                            if (temp.equals(strs[i])) {
                                ad = true;
                                break;
                            }
                        }
                        if (!ad) {
                            a.studentID.add(strs[i]);
                        }
                    }
                    subjects.replace(chosenCourse, a);
                    System.out.println("add student success");
                }
                case "removeStudent" -> {
                    if (strs.length != 2) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    if (!TeaLogin && !StuLogin) {
                        System.out.println("not logged in");
                        break;
                    }
                    if (!TeaLogin && !isAssistant) {
                        System.out.println("permission denied");
                        break;
                    }
                    if (chosenCourse != null && logId.length() != 5) {
                        Subject sub = subjects.get(chosenCourse);
                        if (!sub.assistantID.contains(logId)) {
                            System.out.println("permission denied");
                            break;
                        }
                    }
                    if (chosenCourse == null) {
                        System.out.println("no course selected");
                        break;
                    }
                    if (!ifIDright(strs[1]) || strs[1].length() == 5) {
                        System.out.println("user id illegal");
                        break;
                    }
                    Subject a = subjects.get(chosenCourse);
                    boolean flag = false;
                    int num = 0;
                    for (String temp : a.studentID) {
                        if (temp.equals(strs[1])) {
                            flag = true;
                            break;
                        }
                        num++;
                    }
                    if (!flag) {
                        System.out.println("user id not exist");
                        break;
                    }
                    a.studentID.remove(num);
                    subjects.replace(chosenCourse, a);
                    System.out.println("remove student success");
                }
                case "listStudent" -> {
                    if (strs.length != 1) {
                        System.out.println("arguments illegal");
                        break;
                    }
                    if (!TeaLogin && !StuLogin) {
                        System.out.println("not logged in");
                        break;
                    }
                    if (!TeaLogin && !isAssistant) {
                        System.out.println("permission denied");
                        break;
                    }
                    if (chosenCourse != null && logId.length() != 5) {
                        Subject sub = subjects.get(chosenCourse);
                        if (!sub.assistantID.contains(logId)) {
                            System.out.println("permission denied");
                            break;
                        }
                    }
                    if (chosenCourse == null) {
                        System.out.println("no course selected");
                        break;
                    }
                    Subject a = subjects.get(chosenCourse);
                    Collections.sort(a.studentID);
                    for (String temp : a.studentID) {
                        int num = findStudent(students, temp);
                        Student stu = students.get(num);
                        System.out.println("[ID:" + temp + "] [Name:" + stu.lastName + " " + stu.firstName + "] [Email:" + stu.email + "]");
                    }
                }
                default -> System.out.println("command '" + order + "' not found");
            }
        }
    }

    public static boolean ifIDright(String st) {
        if (st.length() == 8) {
            boolean flag = st.matches("\\d+");
            if (!flag) return false;
            int year = Integer.parseInt(st.substring(0, 2));
            int collegeNum = Integer.parseInt(st.substring(2, 4));
            int classNum = Integer.parseInt(st.substring(4, 5));
            int num = Integer.parseInt(st.substring(5));
            return year >= 17 && year <= 22 && collegeNum >= 1 && collegeNum <= 43 && classNum >= 1 && classNum <= 6 && num >= 1 && num <= 999;
        } else if (st.length() == 9) {
            String front = st.substring(0, 2);
            if (front.equals("SY") || front.equals("ZY")) {
                boolean flag = st.matches("SY\\d+") || st.matches("ZY\\d+");
                if (!flag) return false;
                int year = Integer.parseInt(st.substring(2, 4));
                int collegeNum = Integer.parseInt(st.substring(4, 6));
                int classNum = Integer.parseInt(st.substring(6, 7));
                int num = Integer.parseInt(st.substring(7));
                return year >= 19 && year <= 22 && collegeNum >= 1 && collegeNum <= 43 && classNum >= 1 && classNum <= 6 && num >= 1 && num <= 99;
            } else if (front.equals("BY")) {
                boolean flag = st.matches("BY\\d+");
                if (!flag) return false;
                int year = Integer.parseInt(st.substring(2, 4));
                int collegeNum = Integer.parseInt(st.substring(4, 6));
                int classNum = Integer.parseInt(st.substring(6, 7));
                int num = Integer.parseInt(st.substring(7));
                return year >= 17 && year <= 22 && collegeNum >= 1 && collegeNum <= 43 && classNum >= 1 && classNum <= 6 && num >= 1 && num <= 99;
            } else return false;
        } else if (st.length() == 5) {
            int num = Integer.parseInt(st);
            return st.matches("\\d+") && num >= 1 && num <= 99999;
        } else return false;
    }

    public static boolean ifName(String firstName, String lastName) {
        return (firstName.matches("[A-Z][a-z]+") && lastName.matches("[A-Z][a-z]+")) && firstName.length() < 20 && lastName.length() < 20;
    }

    public static boolean ifEmail(String email) {
        return email.matches("\\w+@\\w+(\\.\\w+)+");
    }

    public static boolean ifPassword(String password) {
        return (password.matches("[a-z][a-zA-Z_0-9]+") || password.matches("[A-Z][a-zA-Z_0-9]]+")) && password.length() <= 16 && password.length() >= 8;
    }

    public static boolean isClassNum(String name) {
        int year = Integer.parseInt(name.substring(1, 3));
        int num = Integer.parseInt(name.substring(3));
        boolean len = name.length() == 5;
        return len && (name.matches("C\\d+")) && year >= 17 && year <= 22 && num >= 1;
    }

    public static boolean isClassName(String name) {
        return name.length() >= 6 && name.length() <= 16 && name.matches("[a-zA-Z_0-9]+");
    }

    public static boolean isWareNum(String chosenCourse, String name) {
        int num = Integer.parseInt(chosenCourse.substring(1));
        int num2 = Integer.parseInt(name.substring(1, 5));
        int num3 = Integer.parseInt(name.substring(5));
        return (name.length() == 7) && (name.matches("W\\d+")) && (num == num2) && num3 >= 1;
    }

    public static boolean isWareName(String name) {
        return name.length() >= 6 && name.length() <= 16 && name.matches("[a-zA-Z0-9_]+\\.[a-zA-Z0-9]+");
    }

    public static boolean isTaskNum(String chosenCourse, String name) {
        int num = Integer.parseInt(chosenCourse.substring(1));
        int num2 = Integer.parseInt(name.substring(1, 5));
        int num3 = Integer.parseInt(name.substring(5));
        return (name.length() == 7) && (name.matches("T\\d+")) && (num == num2) && num3 >= 1;
    }

    public static boolean isTaskName(String name) {
        return name.length() >= 6 && name.length() <= 16 && name.matches("[a-zA-Z0-9_]+\\.[a-zA-Z0-9]+");
    }

    public static boolean isTaskTime(String time) {
        boolean type = time.matches("\\d{4}-\\d{2}-\\d{2}-\\d{2}:\\d{2}:\\d{2}");
        int year = Integer.parseInt(time.substring(0, 4));
        int month = Integer.parseInt(time.substring(5, 7));
        int day = Integer.parseInt(time.substring(8, 10));
        int hour = Integer.parseInt(time.substring(11, 13));
        int min = Integer.parseInt(time.substring(14, 16));
        int sec = Integer.parseInt(time.substring(17));
        boolean rangeCorrect = (year >= 1900 && year <= 9999) && (month >= 1 && month <= 12) &&
                (day >= 1 && day <= 31) && (hour >= 0 && hour <= 23) && (min >= 0 && min <= 59)
                && (sec >= 0 && sec <= 59);
        boolean ifDayRight = true;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            ifDayRight = (day == 31);
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            ifDayRight = (day == 30);
        } else if (month == 2) {
            if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
                ifDayRight = (day == 29);
            } else {
                ifDayRight = (day == 28);
            }
        }
        return type && rangeCorrect && ifDayRight;
    }

    public static boolean taskTimeCompare(String time1, String time2) {
        int year1 = Integer.parseInt(time1.substring(0, 4));
        int month1 = Integer.parseInt(time1.substring(5, 7));
        int day1 = Integer.parseInt(time1.substring(8, 10));
        int hour1 = Integer.parseInt(time1.substring(11, 13));
        int min1 = Integer.parseInt(time1.substring(14, 16));
        int sec1 = Integer.parseInt(time1.substring(17));
        int year2 = Integer.parseInt(time2.substring(0, 4));
        int month2 = Integer.parseInt(time2.substring(5, 7));
        int day2 = Integer.parseInt(time2.substring(8, 10));
        int hour2 = Integer.parseInt(time2.substring(11, 13));
        int min2 = Integer.parseInt(time2.substring(14, 16));
        int sec2 = Integer.parseInt(time2.substring(17));
        if (year1 > year2) {
            return false;
        } else if (year1 < year2) {
            return true;
        } else {
            if (month1 > month2) {
                return false;
            } else if (month1 < month2) {
                return true;
            } else {
                if (day1 > day2) {
                    return false;
                } else if (day1 < day2) {
                    return true;
                } else {
                    if (hour1 > hour2) {
                        return false;
                    } else if (hour1 < hour2) {
                        return true;
                    } else {
                        if (min1 > min2) {
                            return false;
                        } else if (min1 < min2) {
                            return true;
                        } else {
                            return sec1 < sec2;
                        }
                    }
                }
            }
        }
    }

    public static int findTeacher(ArrayList<Teacher> list, String teacherID) {
        int i = 0;
        for (Teacher temp : list) {
            if (temp.id.equals(teacherID)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int findStudent(ArrayList<Student> list, String studentID) {
        int i = 0;
        for (Student temp : list) {
            if (temp.id.equals(studentID)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int findSubjectTea(Teacher tea, String classNum) {
        int i = 0;
        for (String temp : tea.haveSubject) {
            if (temp.equals(classNum)) {
                return i;
            }
            i++;
        }
        return -1;
    }
}

class Student {
    String id;
    String firstName;
    String lastName;
    String email;
    String password;

    public Student(String id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + "\n" +
                "ID: " + id + "\n" +
                "Type: Student" + "\n" +
                "Email: " + email
                ;
    }
}

class Teacher {
    String id;
    String firstName;
    String lastName;
    String email;
    String password;
    ArrayList<String> haveSubject = new ArrayList<>();

    public Teacher(String id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String toString() {
        return "Name: " + firstName + " " + lastName + "\n" +
                "ID: " + id + "\n" +
                "Type: Professor" + "\n" +
                "Email: " + email
                ;
    }
}

class Subject {
    String classNum;
    String className;
    ArrayList<String> teacherID = new ArrayList<>();
    ArrayList<String> assistantID = new ArrayList<>();
    ArrayList<String> studentID = new ArrayList<>();
    TreeMap<String, Ware> wares = new TreeMap<>();
    TreeMap<String, Task> tasks = new TreeMap<>();

    public Subject(String classNum, String className) {
        this.classNum = classNum;
        this.className = className;
    }

    public String toString() {
        return "[ID:" + classNum + "] [Name:" + className +
                "] [TeacherNum:" + teacherID.size() + "] [AssistantNum:" + assistantID.size() +
                "] [StudentNum:" + studentID.size() + "]";
    }
}

class Ware {
    String wareNum;
    String wareName;

    public Ware(String wareNum, String wareName) {
        this.wareNum = wareNum;
        this.wareName = wareName;
    }

    public String toString() {
        return "[ID:" + wareNum + "] [Name:" + wareName +
                "]";
    }
}

class Task {
    String taskNum;
    String taskName;
    String taskStartTime;
    String taskEndTime;
    ArrayList<String> homework = new ArrayList<>();

    public Task(String taskNum, String taskName, String taskStartTime, String taskEndTime) {
        this.taskNum = taskNum;
        this.taskName = taskName;
        this.taskStartTime = taskStartTime;
        this.taskEndTime = taskEndTime;
    }

    public String toString() {
        return "[ID:" + taskNum + "] [Name:" + taskName +
                "] [ReceiveNum:" + homework.size() + "] [StartTime:" + taskStartTime + "] [EndTime:" +
                taskEndTime + "]";
    }
}