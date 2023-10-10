package az.perfect;

import az.perfect.config.SpringConfig;
import az.perfect.inter.EmployeeDaoInter;
import az.perfect.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;

public class HibernateApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        EmployeeDaoInter edao = context.getBean(EmployeeDaoInter.class);

       /* Employee emp = new Employee(4, "Nazrin", "Hasanova", 25, 900);
        edao.insert(emp); */


        System.out.println(edao.getEmployeeById(1));


      //  System.out.println(edao.getAllEmployees());
    }

}
