package com.atguigu.advanced.pojo;

/**
 * ClassName: Employee
 * Package: com.atguigu.advanced.pojo
 * Description:
 *
 * @Author wk
 * @Create 2024-08-01 20:40
 * @Version 1.0
 */
//类名就是数据库表的t_后面的单词全写
public class Employee {
    //emp_id
    private Integer empId;
    //emp_name
    private String empName;
    //emp_salary
    private Double empSalary;
    //emp_age
    private Integer empAge;

    public Employee() {
    }

    public Employee(Integer empId, String empName, Double empSalary, Integer empAge) {
        this.empId = empId;
        this.empName = empName;
        this.empSalary = empSalary;
        this.empAge = empAge;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Double getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(Double empSalary) {
        this.empSalary = empSalary;
    }

    public Integer getEmpAge() {
        return empAge;
    }

    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empSalary=" + empSalary +
                ", empAge=" + empAge +
                '}';
    }
}
