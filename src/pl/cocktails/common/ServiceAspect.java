package pl.cocktails.common;

import javax.jdo.PersistenceManager;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

	@Around(value = "target(pl.cocktails.common.AbstractService)")
	public Object doInTransaction(ProceedingJoinPoint joinPoint){
		PersistenceManager manager = DataStoreManager.getManager().createPersistenceManager();
		((AbstractService)joinPoint.getTarget()).setPersistenceManager(manager);
		try {
			return joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}finally{
			manager.close();
		}
	}
	
}
