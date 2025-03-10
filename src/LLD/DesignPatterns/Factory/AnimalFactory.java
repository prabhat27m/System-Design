package LLD.DesignPatterns.Factory;

// Creates objects without specifying exact class to create
// Uses a separate method/class to handle object creation
// Subclasses can override to change the type of objects created

// Why use it: Encapsulates object creation logic,
//allowing systems to be independent of how their objects are created.
//Useful when a class cannot anticipate the type of objects it


interface Animal{
    void makeSound();
}

class Cat implements Animal{
    @Override
    public void makeSound(){
        System.out.println("Meow!");
    }
}

class Dog implements Animal{
    @Override
    public void makeSound(){
        System.out.println("Dog!!");
    }
}
public class AnimalFactory {

    public Animal createAnimal(String type){
        if(type.equalsIgnoreCase("cat")){
            return new Cat();
        }else if(type.equalsIgnoreCase("dog")){
            return new Dog();
        }

        throw new IllegalArgumentException("Unknown animal type");
    }


    public static void main(String[] args) {
        AnimalFactory animalFactory = new AnimalFactory();

        Animal dog = animalFactory.createAnimal("dog");

        dog.makeSound();
    }

}

