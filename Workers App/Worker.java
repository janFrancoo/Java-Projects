
public class Worker {
    
    private String name, surname, department;
    private int salary, wid;

    public Worker(String name, String surname, String department, int salary, int wid) {
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.salary = salary;
        this.wid = wid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getId() {
        return salary;
    }

    public void setId(int salary) {
        this.salary = salary;
    }

    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }
    
}
