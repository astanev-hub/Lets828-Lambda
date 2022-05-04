package domain;

public interface CheckPerson {
    //Quando uma interface define apenas um método, podemos chama-la de functional interface
    boolean test(Person p);
}
