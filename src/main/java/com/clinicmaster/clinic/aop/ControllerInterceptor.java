//package com.clinicmaster.clinic.aop;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Aspect
//@Component
//@Slf4j
//public class ControllerInterceptor {
//
//    ThreadLocal<Map<String, Long>> count = new ThreadLocal<>();
//
//    /**
//     * 定义一个切入点. 解释下：
//     * <p>
//     * ~ 第一个 * 代表任意修饰符及任意返回值. ~ 第二个 * 定义在web包或者子包 ~ 第三个 * 任意方法 ~ .. 匹配任意数量的参数.
//     */
//    static final String pCutStr = "execution(* com.clinicmaster.clinic.controller.callPatient(..))";
//
//    @Pointcut(pCutStr)
//    public void logPoint() {
//
//    }
//
//    @Around("logPoint()")
//    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        if (count.get() == null) {
//            count.set(new HashMap<>(4));
//        }
//
//        Object result = joinPoint.proceed();
//        if (result == null) {
//            return null;
//        }
//
//        String tragetClassName = joinPoint.getSignature().getDeclaringTypeName();
//        String MethodName = joinPoint.getSignature().getName();
//
//        Object[] args = joinPoint.getArgs();// 参数
//        int argsSize = args.length;
//        String argsTypes = "";
//        String typeStr = joinPoint.getSignature().getDeclaringType().toString().split(" ")[0];
//        String returnType = joinPoint.getSignature().toString().split(" ")[0];
//
//        log.info("类/接口:" + tragetClassName + "(" + typeStr + ")");
//        log.info("方法:" + MethodName);
//        log.info("参数个数:" + argsSize);
//        log.info("返回类型:" + returnType);
//
//        if (argsSize > 0) {
//            // 拿到参数的类型
//            for (Object object : args) {
//                argsTypes += object.getClass().getTypeName() + " ";
//            }
//            log.info("参数类型：" + argsTypes);
//        }
//
//        if (count.get().containsKey(MethodName)) {
//            Long num = count.get().get(MethodName);
//            count.get().remove(MethodName);//先移除，在增加
//            count.get().put(MethodName, num + 1);
//
//        } else {
//            count.get().put(MethodName, 1L);
//        }
//
//        return result;
//    }
//}
