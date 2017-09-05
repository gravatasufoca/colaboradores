package com.gravatasufoca.interceptor;

import com.gravatasufoca.factories.EntityManagerProducer;
import com.gravatasufoca.interfaces.Transacional;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;

@Interceptor
@Transacional
public class TransionalInterceptor implements Serializable {

    private static final long serialVersionUID = -1939469955361094666L;
    @Inject
    private EntityManagerProducer emProd;

    public EntityManager getEntityManager(){
        return emProd.criaEntityManager();
    }

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception{

        EntityTransaction transaction = getEntityManager().getTransaction();

        try {  
            if (!transaction.isActive()) {  
                transaction.begin(); 
            }  
            return ctx.proceed();  

        } catch (Exception e) {
            if (transaction != null) {  
                transaction.rollback();  
            }  
            throw e;  
        } finally {  
            if (transaction != null && transaction.isActive()) {
                transaction.commit();  
            }  
        }

    }

}