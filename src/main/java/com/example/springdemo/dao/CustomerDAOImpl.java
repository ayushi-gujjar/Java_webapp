package com.example.springdemo.dao;

import com.example.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Customer> getCustomers() {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        // create a query
        Query<Customer> theQuery =
                currentSession.createQuery("from Customer order by lastName", Customer.class);
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
        // return the results
        return customers;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(theCustomer);
    }

    @Override
    public Customer getCustomer(int id) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        // now retrieve/read from database using the primary key
        Customer theCustomer = currentSession.get(Customer.class, id);
        return theCustomer;
    }

    @Override
    public void deleteCustomer(int id) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        // delete the object with pk
        Query query = currentSession.createQuery("delete from Customer where id=:customerId");
        query.setParameter("customerId", id);
        query.executeUpdate();
    }
}
