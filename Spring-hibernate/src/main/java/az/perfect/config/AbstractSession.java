package az.perfect.config;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component //automaticheski sozdaet
@RequiredArgsConstructor //chtobi ne sozdavat constructor, t.k napisali final

public abstract class AbstractSession {

    private final SessionFactory sessionFactory;

    public Session getSession(){ //hibernetden gelen Session'u sechirik
       return sessionFactory.getCurrentSession();
    } //sdelaem extends AbstractSession v EmployeeDaoImpl,chtobi kazhdiy raz ne vizivat getCurrentSession()
}
