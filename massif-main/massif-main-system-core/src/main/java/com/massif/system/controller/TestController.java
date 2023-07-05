package com.massif.system.controller;

import com.massif.system.annotation.XAnnotation;
import com.massif.system.annotation.XException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author wangsiyao
 * @create 2023-06-12 8:59
 */

@RestController
public class TestController {

    @XAnnotation
    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println("hello");
        return "hello";
    }

    @XException
    @RequestMapping("/testExc")
    public String testExc() {
        System.out.println("ex");
        return "ex";
    }

    @PreAuthorize("hasAuthority('test:query')")
    @GetMapping("/query")
    public String query() {
        return "Access successful.";
    }


    public static void main(String[] args) {
        TheInterface theInterface = new TheInterface() {
            @Override
            public void method01() {
                System.out.println("abc....");
            }
        };
        theInterface.method01();
//        System.out.println(theInterface);

        TheInterface theInterface1 = () -> {
            System.out.println("abc123...");
        };
        theInterface1.method01();
//        System.out.println(theInterface1);
        List<String> list = new ArrayList<>();
        list.add (null);
        list.add (null);
        list.forEach(System.out::println);

        Map<String, String> map = new HashMap<>();
        map.put("6", "7");
        map.put("2", "5");
        map.put("8", "6");
        map.put("3", null);
        map.put(null, null);
        map.put(null, "2");
        map.put("8", "3");
//        map.entrySet().forEach(System.out::println);
        System.out.println(map);        // 这个 输出为 {null=2, 2=5, 3=null, 6=7, 8=3}     // 天啊，这个也可以输出值，因为HashMap重写的toString
    }

    @FunctionalInterface
    interface TheInterface {
        void method01();

        String toString();
    }

}
