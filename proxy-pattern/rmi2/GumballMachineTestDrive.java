
import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

interface State extends Serializable {
    void insertQuarter();
    void ejectQuarter();
    void turnCrank();
    void dispense();
    void refil();
}

interface GumBallMachineRemote extends Remote {
    int getCount() throws RemoteException;
    String getLocation() throws RemoteException;
    State getState() throws RemoteException;
}

class GumballMachine extends UnicastRemoteObject implements GumBallMachineRemote {
    State soldOutState;
    State noQuarterState;
    State hasQuarterState;
    State soldState;
    State winnerState;

    State state;
    int count = 0;
    String location;

    public GumballMachine(String location, int numberGumBalls) throws RemoteException {
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        winnerState = new WinnerState(this);
        
        this.location = location;
        this.count = numberGumBalls;
        if (numberGumBalls > 0) {
            state = noQuarterState;
        } else {
            state = soldOutState;
        }
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    void setState(State state) {
        this.state = state;
    }

    void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");
        if (count != 0) {
            count--;
        }
    }

    void refil(int refilCount) {
        this.count += refilCount;
        System.out.println("Gumball machine is refilled its new count is " + this.count);
        state.refil();
    } 

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public int getCount() {
        return this.count;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getWinnerState() {
        return winnerState;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("\nMighty Gumball, Inc.");
        result.append("\nJava-enabled Standing Gumball Model #2004\n");
        result.append("Inventory: " + count + " gumball");
        if (count != 1) {
            result.append("s");
        }
        result.append("\nMachine is ");
        if (state instanceof SoldOutState) {
            result.append("sold out");
        } else if (state instanceof NoQuarterState) {
            result.append("waiting for quarter");
        } else if (state instanceof HasQuarterState) {
            result.append("waiting for turn of crank");
        } else if (state instanceof SoldState) {
            result.append("delivering a gumball");
        }
        result.append("\n");
        return result.toString();
    }

}

class NoQuarterState implements State {
    private static final long serialVersionUID = 2L;

    transient GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("you inserted a quarter");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("you haven't inserted a quarter");
    }

    @Override
    public void turnCrank() {
        System.out.println("you turned, but there's no quarter");
    }

    @Override
    public void dispense() {
        System.out.println("you need to pay first");
    }

    @Override
    public void refil() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refil'");
    }
}

class SoldOutState implements State {

    private static final long serialVersionUID = 2L;

    transient GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("Oops, we are out of gum, you can't insert quarter");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("you can't eject, you haven't inserted a quarter");
    }

    @Override
    public void turnCrank() {
        System.out.println("you turned, but there's no gumballls");
    }

    @Override
    public void dispense() {
        System.out.println("no gumball dispensed");
    }

    @Override
    public void refil() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refil'");
    }
}

class HasQuarterState implements State {

    private static final long serialVersionUID = 2L;

    transient GumballMachine gumballMachine;

    Random randomWinner = new Random(System.currentTimeMillis());

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("you can't insert a quarter");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("quarter returned");
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        System.out.println("you turned crank...");
        int winner = randomWinner.nextInt(10);

        if (winner == 0 && gumballMachine.getCount() > 1) {
            gumballMachine.setState(gumballMachine.getWinnerState());
        } else {
            gumballMachine.setState(gumballMachine.getSoldState());
        }
    }

    @Override
    public void dispense() {
        System.out.println("No gumball dispensed");
    }

    @Override
    public void refil() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refil'");
    }
}

class SoldState implements State {

    private static final long serialVersionUID = 2L;

    transient GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("Please wait, we're already giving you a gumball");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Sorry, you already turned the crank");
    }

    @Override
    public void turnCrank() {
        System.out.println("turning twice doesn't get you another gumball");
    }

    @Override
    public void dispense() {
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() > 0) {
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        } else {
            System.out.println("Oops, out of gumball");
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }

    @Override
    public void refil() {
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }
}

class WinnerState implements State {

    private static final long serialVersionUID = 2L;

    transient GumballMachine gumballMachine;


    public WinnerState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("Error inserting quarter");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Error ejecting quarter");
    }

    @Override
    public void turnCrank() {
        System.out.println("Error turning crank");
    }

    @Override
    public void dispense() {
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() == 0) {
            gumballMachine.setState(gumballMachine.getSoldOutState());
        } else {
            gumballMachine.releaseBall();
            System.out.println("You are winner, you got two balls for your quarter");

            if (gumballMachine.getCount() > 0) {
                gumballMachine.setState(gumballMachine.getNoQuarterState());
            } else {
                System.out.println("Oops out of gumball");
                gumballMachine.setState(gumballMachine.getSoldOutState());
            }
        }
    }

    @Override
    public void refil() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refil'");
    }

}

public class GumballMachineTestDrive {
    public static void main(String[] args) {
        GumBallMachineRemote gumBallMachineRemote;
        int count;
        if (args.length < 2) {
            System.out.println("GumballMachine <name> <inventory>");
            System.exit(1);
        }

        try {
            count = Integer.parseInt(args[1]);
            gumBallMachineRemote = new GumballMachine(args[0], count);
            Naming.rebind(args[0], gumBallMachineRemote);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}