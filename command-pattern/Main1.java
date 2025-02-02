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

public class Main1 {
    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl();
        Light light = new Light();
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        Command lightOnCommand2 = () -> {
            light.on();
        };
        remote.setCommand(light::on);
        remote.buttonWasPressed();
    }
}