package LLD.DesignPatterns.Builder;

//Separates construction of complex objects from their representation
//Creates objects step by step
//Allows different representations of the same construction process
//
//Why use it: Simplifies creation of complex objects by providing a clear,
//readable way to construct them with many optional parameters. Particularly
//useful to avoid "telescoping constructor" problems (constructors with many parameters).

public class Pizza {
    private final String size;
    private final boolean cheese;
    private final boolean pepperoni;
    private final boolean mushroom;

    public Pizza(PizzaBuilder pizzaBuilder){
        this.size = pizzaBuilder.size;
        this.cheese = pizzaBuilder.cheese;
        this.pepperoni = pizzaBuilder.pepperoni;
        this.mushroom = pizzaBuilder.mushroom;
    }

    public static class PizzaBuilder{
        private final String size;
        private boolean cheese;
        private boolean pepperoni;
        private boolean mushroom;

        public PizzaBuilder(String size){
            this.size = size;
        }

        public PizzaBuilder cheese (boolean cheese){
            this.cheese = cheese;
            return this;
        }

        public PizzaBuilder pepperoni (boolean pepperoni){
            this.pepperoni = pepperoni;
            return this;
        }

        public PizzaBuilder mushroom(boolean mushroom){
            this.mushroom = mushroom;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }

    public static void main(String[] args) {
        Pizza pizza = new Pizza.PizzaBuilder("large")
                .cheese(true)
                .mushroom(true)
                .pepperoni(false)
                .build();

        System.out.println(pizza.size);
        System.out.println(pizza.cheese);
        System.out.println(pizza.pepperoni);
    }


}

