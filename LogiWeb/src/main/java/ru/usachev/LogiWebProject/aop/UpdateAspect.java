package ru.usachev.LogiWebProject.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.usachev.LogiWebProject.jms.Producer;

/**
 * Aspect class which sends message to Display app when something changes in DB
 */
@Component
@Aspect
public class UpdateAspect {

    @Autowired
    Producer producer;

    @Pointcut(value = "@annotation(ru.usachev.LogiWebProject.aop.UpdateAnnotation)")
    public void annotated(){}

    @AfterReturning(value = "annotated()")
    public void sendMessage(){
        producer.sendMessage();
    }
}
