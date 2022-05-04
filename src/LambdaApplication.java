import domain.CheckPerson;
import domain.CheckPersonWithInterface;
import domain.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class LambdaApplication {
    public static void main(String[] args) {

        Person p1 = new Person("P1", 10, LocalDate.of(2012, 8, 10), Person.Sex.MALE, "Rua do P1");
        Person p2 = new Person("P2", 27, LocalDate.of(1995, 8, 13), Person.Sex.MALE, "Rua do P2");
        Person p3 = new Person("P3", 30, LocalDate.of(1992, 1, 27), Person.Sex.FEMALE, "Rua do P3");

        List<Person> listPerson = new ArrayList<>();
        listPerson.add(p1);
        listPerson.add(p2);
        listPerson.add(p3);

        printPersonsOlderThan(listPerson, 28);

        //Olha o boilerplate!!
        printPersons(listPerson, new CheckPersonWithInterface());

        printPersons(listPerson, new CheckPerson() {
            @Override
            public boolean test(Person p) {
                return p.getGender() == Person.Sex.MALE &&
                        p.getAge() >= 18 &&
                        p.getAge() <= 29;
            }
        });

        //Argument-list   Arrow-token   body
        //(argument-list) -> {body}
        printPersons(listPerson, (Person p) -> p.getGender() == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 29
        );

        //Por que nessa versão não precisamos especificar o tipo?
        //Podemos especificar, mas não é necessário. O predicate já tem a assinatura com o tipo específico
        printPersonsWithPredicate(listPerson, p -> p.getGender() == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 29
        );

    }

    public static void printPersonsOlderThan(List<Person> roster, int age) {
        for (Person p : roster) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

    public static void printPersons(List<Person> roster, CheckPerson tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

}
