package Misc;

// Abstract handler class for cash dispensing
abstract class CashDispenser {
    private CashDispenser nextDispenser;

    // Set the next handler in the chain
    public void setNextDispenser(CashDispenser nextDispenser) {
        this.nextDispenser = nextDispenser;
    }

    // Process the request to dispense cash
    public void dispense(int amount) {
        if (amount >= getDenomination()) {
            int num = amount / getDenomination();
            int remainder = amount % getDenomination();

            System.out.println("Dispensing " + num + " x $" + getDenomination() + " notes");

            if (remainder != 0 && nextDispenser != null) {
                nextDispenser.dispense(remainder);
            } else if (remainder != 0) {
                System.out.println("Cannot dispense remaining amount: $" + remainder);
            }
        } else if (nextDispenser != null) {
            nextDispenser.dispense(amount);
        } else {
            System.out.println("Cannot dispense amount: $" + amount);
        }
    }

    // Get the denomination value this handler can dispense
    protected abstract int getDenomination();
}

// Concrete handler for $100 notes
class HundredDollarDispenser extends CashDispenser {
    @Override
    protected int getDenomination() {
        return 100;
    }
}

// Concrete handler for $50 notes
class FiftyDollarDispenser extends CashDispenser {
    @Override
    protected int getDenomination() {
        return 50;
    }
}

// Concrete handler for $20 notes
class TwentyDollarDispenser extends CashDispenser {
    @Override
    protected int getDenomination() {
        return 20;
    }
}

// Concrete handler for $10 notes
class TenDollarDispenser extends CashDispenser {
    @Override
    protected int getDenomination() {
        return 10;
    }
}

// Concrete handler for $5 notes
class FiveDollarDispenser extends CashDispenser {
    @Override
    protected int getDenomination() {
        return 5;
    }
}

// Concrete handler for $1 notes
class OneDollarDispenser extends CashDispenser {
    @Override
    protected int getDenomination() {
        return 1;
    }
}

// ATM class that creates and uses the cash dispenser chain
class ATM {
    private CashDispenser chain;

    public ATM() {
        // Create the chain of handlers
        this.chain = new HundredDollarDispenser();
        CashDispenser fiftyDispenser = new FiftyDollarDispenser();
        CashDispenser twentyDispenser = new TwentyDollarDispenser();
        CashDispenser tenDispenser = new TenDollarDispenser();
        CashDispenser fiveDispenser = new FiveDollarDispenser();
        CashDispenser oneDispenser = new OneDollarDispenser();

        // Set up the chain
        chain.setNextDispenser(fiftyDispenser);
        fiftyDispenser.setNextDispenser(twentyDispenser);
        twentyDispenser.setNextDispenser(tenDispenser);
        tenDispenser.setNextDispenser(fiveDispenser);
        fiveDispenser.setNextDispenser(oneDispenser);
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive amount.");
            return;
        }

        System.out.println("Withdrawing $" + amount);
        chain.dispense(amount);
        System.out.println("Transaction completed.");
    }
}

// Client code to demonstrate the ATM using Chain of Responsibility
public class ChainOfResponsibilityATM {
    public static void main(String[] args) {
        ATM atm = new ATM();

        System.out.println("=== ATM Cash Dispenser ===");

        // Test with different withdrawal amounts
        atm.withdraw(186);
        System.out.println();

        atm.withdraw(537);
        System.out.println();

        atm.withdraw(75);
        System.out.println();

        // Edge case
        atm.withdraw(0);
    }
}
