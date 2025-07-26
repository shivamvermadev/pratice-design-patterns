public class GumballMonitor {
    GumBallMachineRemote gumBallMachineRemote;

    public GumballMonitor(GumBallMachineRemote gumBallMachineRemote) {
        this.gumBallMachineRemote = gumBallMachineRemote;
    }

    public void report() {
        try {
            System.out.println("Gumball Machine: " + gumBallMachineRemote.getLocation());
            System.out.println("Current inventory: " + gumBallMachineRemote.getCount() + " gumballs");
            System.out.println("Current State: " + gumBallMachineRemote.getState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}