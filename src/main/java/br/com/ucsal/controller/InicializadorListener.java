package br.com.ucsal.controller;

<<<<<<< HEAD
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import br.com.ucsal.annotations.Inject;
import br.com.ucsal.annotations.Rota;
import br.com.ucsal.annotations.Singleton;
import br.com.ucsal.commands.Command;
import br.com.ucsal.persistencia.PersistenciaFactory;
import br.com.ucsal.persistencia.ProdutoRepository;
import br.com.ucsal.service.ProdutoService;
=======
>>>>>>> ceb68ae84e6157a9094186ed145eebfc5d3be776
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class InicializadorListener implements ServletContextListener {
<<<<<<< HEAD

    String basePackage = "br.com.ucsal";
    private final String PREFIX = "/prova2/view";
    private final Map<String, Command> rotas = new HashMap<>();
    private static final Map<Class<?>, Object> INSTANCES_SINGLETON = new HashMap<>();
    private Map<String, Object> instances_commands = new HashMap<>();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            processarSingletons(basePackage);
            processarRotas(basePackage);
            injetarDependencias(basePackage);
            sce.getServletContext().setAttribute("command", rotas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processarRotas(String basePackage) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Reflections reflections = new Reflections(basePackage, Scanners.TypesAnnotated);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Rota.class);
        for (Class<?> classe : classes) {
            if (classe.isAnnotationPresent(Rota.class)) {
                Rota rotaClasse = classe.getAnnotation(Rota.class);
                for (int i = 0; i < rotaClasse.path().length; i++) {
                    Command commandInstance = (Command) getInstanceSingleton(classe);
                    String nomeClasse = getFiltroNomeClasseDeclarada(classe.getName());
                    instances_commands.put(nomeClasse, commandInstance);
                    String path = rotaClasse.path()[i].replaceAll("//", "/");
                    rotas.put(path, commandInstance);
                }
            }
        }
    }

    public Command getCommand(String path) {
        return rotas.get(path);
    }

    public String getFiltroNomeClasseDeclarada(String nomeClasse) {
        return nomeClasse.substring(nomeClasse.lastIndexOf(".") + 1);
    }

    public void processarSingletons(String basePackage) {
        Reflections reflections = new Reflections(basePackage, Scanners.TypesAnnotated);
        Set<Class<?>> singletonClasses = reflections.getTypesAnnotatedWith(Singleton.class);
        for (Class<?> clazz : singletonClasses) {
            getInstanceSingleton(clazz);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getInstanceSingleton(Class<T> clazz) {
        if (!clazz.isAnnotationPresent(Singleton.class)) {
            throw new IllegalArgumentException("A classe " + clazz.getName() + " não está anotada com @Singleton");
        }
        return (T) INSTANCES_SINGLETON.computeIfAbsent(clazz, InicializadorListener::createInstanceSingleton);
    }

    private static <T> T createInstanceSingleton(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível criar a instância da classe " + clazz.getName(), e);
        }
    }

    private void injetarDependencias(String basePackage) throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections(basePackage, Scanners.FieldsAnnotated);
        Set<Field> fields = reflections.getFieldsAnnotatedWith(Inject.class);
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> declaringClass = field.getDeclaringClass();
            String nomeClasse = declaringClass.getSimpleName();
            Object dependency = obterDependencia(field.getType());
            if (dependency != null) {
                if (Modifier.isStatic(field.getModifiers())) {
                    field.set(null, dependency);
                } else {
                    Object instance = instances_commands.get(nomeClasse);
                    field.set(instance, dependency);
                }
            }
        }
    }

    private Object obterDependencia(Class<?> tipo) {
        if (ProdutoService.class.isAssignableFrom(tipo)) {
            ProdutoRepository<?, ?> repository = PersistenciaFactory.getProdutoRepository(0);
            if (repository == null) {
                throw new IllegalStateException("ProdutoRepository não pode ser nulo");
            }
            return new ProdutoService(repository);
        }
        throw new IllegalArgumentException("Não foi possível resolver a dependência para o tipo: " + tipo.getName());
    }
}
=======
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Carregue suas classes ou inicialize recursos aqui
        System.out.println("Inicializando recursos na inicialização da aplicação");
    }


}
>>>>>>> ceb68ae84e6157a9094186ed145eebfc5d3be776
