package db.schulung.Schulung061123.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UpdateAspect {

    @Pointcut("target(shoppingCart)")
    void shoppingCartMethod(ShoppingCart shoppingCart) {
    }

    @After("shoppingCartMethod(shoppingCart)")
    void update(ShoppingCart shoppingCart) {
        shoppingCart.updateCountOfItems();
        shoppingCart.updateSumOfPrices();
    }

    @Around("execution(ShoppingCart *(..)) && args(item)")
    ShoppingCart checkItemValidity(
            ProceedingJoinPoint proceedingJoinPoint,
            Item item) throws Throwable {
        if (item.name.isEmpty()) {
            return (ShoppingCart) proceedingJoinPoint.getTarget();
        }
        return (ShoppingCart) proceedingJoinPoint.proceed();
    }

//    @Pointcut("execution(public * get*(..)) && args(item)")
//    void publicGetter(Item  item) {
//    }
//
//    @Before("publicGetter(item)")
//    void aspectBefore0(JoinPoint joinPoint, Item item) {
//
//    }
//
//    @Before("publicGetter(item)")
//    void aspectBefore1(JoinPoint joinPoint, Item item) {
//
//    }
}
