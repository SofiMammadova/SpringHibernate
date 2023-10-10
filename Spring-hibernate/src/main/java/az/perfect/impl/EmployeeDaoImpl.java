package az.perfect.impl;

import az.perfect.config.AbstractSession;
import az.perfect.inter.EmployeeDaoInter;
import az.perfect.model.Employee;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository //chtobi rabotat s database'om
public class EmployeeDaoImpl extends AbstractSession implements EmployeeDaoInter {

    public EmployeeDaoImpl(SessionFactory sessionFactory) {//constructor dla super sessionFactory
        super(sessionFactory);
    }

   /* perenosim v ABstractSession class v Config'e
   dobavili extends AbstractSession v nash class
    private SessionFactory sessionFactory;
    */

    @Override
    @Transactional
    public void insert(Employee employee) {
       /* mi kajdiy raz budem vizivat getcurrentSession dla vizova tekushey sessii
       no chtobi kazhdiy raz ne vizivat ego, sozdaem v classe Config class
       //AbstractSession
       sessionFactory.getCurrentSession(). */
        getSession().save(employee);

    }

    @Override
    @Transactional
    public void update(Employee employee) {
    getSession().update(employee);
    }

    @Override
    @Transactional
    public void delete(int id) {
        getSession().delete(getEmployeeById(id));

    }

    @Override
    @Transactional
    public Employee getEmployeeById(int id) {
        return getSession().get(Employee.class, id);
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return getSession().createQuery("select e from Employee e").list();
    }
}
