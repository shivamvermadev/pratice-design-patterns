
import java.rmi.Naming;

public class GumballMonitorTestDrive {
    public static void main(String[] args) {
        String[] location = {
            "machine1",
            "machine2",
            "machine3"
        };

        GumballMonitor[] monitors = new GumballMonitor[location.length];

        for (int i=0; i< location.length;i++) {
            try {
                System.out.println("Im here");
                GumBallMachineRemote machine = (GumBallMachineRemote) Naming.lookup("rmi://127.0.0.1/" + location[i]);
                System.out.println("machine " + machine);
                monitors[i] = new GumballMonitor(machine);
                System.out.println(monitors[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (int i=0;i<monitors.length;i++) {
            monitors[i].report();
        }
    }
}