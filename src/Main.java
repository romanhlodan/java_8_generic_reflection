import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, FileNotFoundException {

 Person<Integer,String,Integer,Boolean> persons = new Person<>(1,"Pedro",21,false);
 Person<Integer,String,Integer,Boolean> integerStringIntegerBooleanPerson2 = new Person<>(8,"Petro",26,true);
 Person<Integer,String,Integer,Boolean> integerStringIntegerBooleanPerson3 = new Person<>(3,"Ira",18,false);
 Person<Integer,String,Integer,Boolean> integerStringIntegerBooleanPerson4 = new Person<>(4,"Masha",21,true);
 Person<Integer,String,Integer,Boolean> integerStringIntegerBooleanPerson5 = new Person<>(6,"Klara",24,false);

 Person<String, String,Integer,Integer> stringStringIntegerIntegerPerson1 = new Person<>("First","Ivan",25,1);
 Person<String, String,Integer,Integer> stringStringIntegerIntegerPerson2 = new Person<>("Second","Roman",24,1);
 Person<String, String,Integer,Integer> stringStringIntegerIntegerPerson3 = new Person<>("Third","Masha",20,0);
 Person<String, String,Integer,Integer> stringStringIntegerIntegerPerson4 = new Person<>("Fourth","Kolja",21,1);
 Person<String, String,Integer,Integer> stringStringIntegerIntegerPerson5 = new Person<>("Fifth","Olya",19,0);
 Person<String, String,Integer,Integer> stringStringIntegerIntegerPerson6 = new Person<>("Sixth","Petro",28,0);

        List<Person> personList = new ArrayList<>();
        personList.add(persons);
        personList.add(stringStringIntegerIntegerPerson3);
        personList.add(integerStringIntegerBooleanPerson2);
        personList.add(stringStringIntegerIntegerPerson4);
        personList.add(integerStringIntegerBooleanPerson4);
        personList.add(stringStringIntegerIntegerPerson1);
        personList.add(stringStringIntegerIntegerPerson2);
        personList.add(integerStringIntegerBooleanPerson5);
        personList.add(stringStringIntegerIntegerPerson5);
        personList.add(integerStringIntegerBooleanPerson3);
        personList.add(stringStringIntegerIntegerPerson6);


       List<Person> list = personList.stream()
                .sorted((o1, o2) -> (int) o1.getAge()- (int)o2.getAge())
                .filter(person -> {return person.getMarried().equals(1) || person.getMarried().equals(false);})
                .collect(Collectors.toList());
//        System.out.println(list);
        Iterator<Person> iterator = list.iterator();
        while (iterator.hasNext()) {
            Person next = iterator.next();
            if ((int)next.getAge() == 21){
                iterator.remove();
            }
        }
//        System.out.println(list);

        Class<? extends Person> personsClass = persons.getClass();
        Field[] declaredFields = personsClass.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
//            System.out.println(field);
//            System.out.println(field.get(persons));
        }

        Field name = personsClass.getDeclaredField("name");
        System.out.println(persons.getName());
        System.out.println(persons);
        name.setAccessible(true);
        name.set(persons,"Oleg");
        System.out.println(persons.getName());
        System.out.println(persons);

        handler(persons);
        Saver saver = new Saver();
        File file = new File(System.getProperty("user.home")+ File.separator+"test.txt");
        saver.save(file,new Person<>(69,"Roman",24,true));
    }

    public static void handler(Person person) throws IllegalAccessException{
        Class<? extends Person> aClass = person.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Marker.class)) {
                int o = (int)field.get(person);
                if (o <=0){
                    Marker annotation = field.getAnnotation(Marker.class);
                    String message1 = annotation.message1();
                    System.out.println(message1);
                }else {
                    Marker annotation = field.getAnnotation(Marker.class);
                    String message2 = annotation.message2();
                    System.out.println(message2);
                }
            }
        }

            }
}
