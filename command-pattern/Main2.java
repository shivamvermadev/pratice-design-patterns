interface Command {
    void execute();
}

class Light {
    void on() {
        System.out.println("Light is on");
    }

    void off() {
        System.out.println("Light is off");
    }
}

class GarageDoor {
    void up() {
        System.out.println("Door is up");
    }

    void down() {
        System.out.println("Door is down");
    }

    void stop() {
        System.out.println("Door is stopped");
    }

    void lightOn() {
        System.out.println("Garage light is on");
    }

    void lightOff() {
        System.out.println("Garage light is off");
    }
}

class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }                                                                                        
}

class SimpleRemoteControl {
    Command slot;
    public SimpleRemoteControl() {}

    public void setCommand(Command command) {
        slot = command;
    }

    public void buttonWasPressed() {
        slot.execute();
    }
}

class GarageDoorOpenCommand implements Command {
    GarageDoor garageDoor;
    
    public GarageDoorOpenCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    public void execute() {
        garageDoor.up();
    }
}
    
    
    public class Main2 {
        public static void main(String[] args) {
            SimpleRemoteControl remote = new SimpleRemoteControl();

            Light light = new Light();
            LightOnCommand lightOnCommand = new LightOnCommand(light);

            remote.setCommand(lightOnCommand);
            remote.buttonWasPressed();

            GarageDoor garageDoor = new GarageDoor();
            GarageDoorOpenCommand garageDoorOpenCommand = new GarageDoorOpenCommand(garageDoor);
            remote.setCommand(garageDoorOpenCommand);
            remote.buttonWasPressed();
    }
}
