package test;

import annotation.Route;
import entities.HelloController;
import java.lang.reflect.Method;

public class MainTest {

    public static void main(String[] args) throws Exception {
        String url = "/say"; // Simule l'URL demandée

        // Ici on teste seulement HelloController
        Class<?> clazz = HelloController.class;

        // Vérifie la route sur la classe
        if (clazz.isAnnotationPresent(Route.class)) {
            Route classRoute = clazz.getAnnotation(Route.class);
            if (classRoute.value().equals(url)) {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                System.out.println("Classe correspondante : " + clazz.getSimpleName());
                return;
            }
        }

        // Vérifie la route sur chaque méthode
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Route.class)) {
                Route methodRoute = method.getAnnotation(Route.class);
                if (methodRoute.value().equals(url)) {
                    Object instance = clazz.getDeclaredConstructor().newInstance();
                    method.invoke(instance);
                    return;
                }
            }
        }

        System.out.println("Aucune route trouvée pour " + url);
    }
}
