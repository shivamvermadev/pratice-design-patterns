interface Command {
    void execute();
    void undo();
}

class NoCommand implements Command {
    public void execute() {
        System.out.println("NoCommand");
    }

    public void undo() {}
}

class RemoteControl {
    Command[] onCommands;
    Command[] offCommands;
    Command undoCommand;

    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];

        Command noCommand = new NoCommand();

        for(int i=0;i<7;i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        this.onCommands[slot] = onCommand;
        this.offCommands[slot] = offCommand;
    }

    public void onButtonPressed(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    public void offButtonPressed(int slot) {
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }

    public void undoButtonWasPressed() {
        undoCommand.undo();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n-----------Remote Control ---------\n");
        for(int i=0;i<7;i++) {
            stringBuffer.append("Slot[" + i + "] " + onCommands[i].getClass().getName()
            + "\t" + offCommands[i].getClass().getName() + "\n");
        }

        return stringBuffer.toString();
    }
}

class Light {
    String light;
    public Light() {}
    public Light(String light) {
        this.light = light;
    }

    void on() {
        System.out.println(light + " Light is on");
    }

    void off() {
        System.out.println(light + " Light is off");
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
    
    public void undo() {
        light.off();
    }
}

class Stereo {
    int volume;

    public void on() {
        System.out.println("Stere is on");
    }

    public void setCD() {
        System.out.println("Setting CD");
    }

    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("Setting up Volume to : " + this.volume);
    }

    public void off() {
        System.out.println("Stereo is off");
    }
}

class CeilingFan {
    String ceilingFan;

    public CeilingFan(String fan) {
        this.ceilingFan = fan;
    }
    
    public void on() {
        System.out.println( ceilingFan + " fan is on");
    }

    public void off() {
        System.out.println( ceilingFan + " fan is off");
    }
}

class CeilingFanOnCommand implements Command {
    CeilingFan fan;

    public CeilingFanOnCommand(CeilingFan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.on();
    }

    @Override
    public void undo() {
        fan.off();
    }
}

class CeilingFanOffCommand implements Command {
    CeilingFan fan;

    public CeilingFanOffCommand(CeilingFan fan) {
        this.fan = fan;
    }

    public void execute() {
        fan.off();
    }

    public void undo() {
        fan.on();
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

class GarageDoorOpenCommand implements Command {
    GarageDoor garageDoor;
    
    public GarageDoorOpenCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    public void execute() {
        garageDoor.up();
        garageDoor.lightOn();
    }

    public void undo() {
        garageDoor.down();
    }
}

class GarageDoorCloseCommand implements Command {
    GarageDoor garageDoor;
    
    public GarageDoorCloseCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    public void execute() {
        garageDoor.lightOff();
        garageDoor.down();
    }

    public void undo() {
        garageDoor.up();
    }
}


class StereoOnwithCDCommand implements Command {
    Stereo stereo;
    public StereoOnwithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    public void execute() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(2);
    }

    public void undo() {
        stereo.off();
    }
}

class StereoOffCommand implements Command {
    Stereo stereo;

    public StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    public void execute() {
        stereo.off();
    }

    public void undo() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(2);
    }
}

class LightOffCommand implements Command {
    Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.off();
    }          
    
    public void undo() {
        light.on();
    }
}

public class Main4 {

    public static void main(String[] args) {
        
        RemoteControl remote = new RemoteControl();
    
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");
        CeilingFan ceilingFan = new CeilingFan("Living Room");
        GarageDoor garageDoor = new GarageDoor();
        Stereo stereo = new Stereo();
    
        LightOnCommand livingRoomLightOnCommand = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOffCommand = new LightOffCommand(livingRoomLight);
    
        LightOnCommand kitchenLightOnCommand = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOffCommand = new LightOffCommand(kitchenLight);
    
        CeilingFanOnCommand ceilingFanOnCommand = new CeilingFanOnCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOffCommand = new CeilingFanOffCommand(ceilingFan);
    
        GarageDoorOpenCommand garageDoorOpenCommand = new GarageDoorOpenCommand(garageDoor);
        GarageDoorCloseCommand garageDoorCloseCommand = new GarageDoorCloseCommand(garageDoor);
    
        StereoOnwithCDCommand stereoOnwithCDCommand = new StereoOnwithCDCommand(stereo);
        StereoOffCommand stereoOffCommand = new StereoOffCommand(stereo);

        remote.setCommand(0, livingRoomLightOnCommand, livingRoomLightOffCommand);
        remote.setCommand(1, kitchenLightOnCommand, kitchenLightOffCommand);
        remote.setCommand(2, ceilingFanOnCommand, ceilingFanOffCommand);
        remote.setCommand(3, stereoOnwithCDCommand, stereoOffCommand);

        System.out.println(remote);

        remote.onButtonPressed(0);
        remote.offButtonPressed(0);
        remote.undoButtonWasPressed();

        remote.onButtonPressed(1);
        remote.offButtonPressed(1);
        remote.undoButtonWasPressed();

        remote.onButtonPressed(2);
        remote.offButtonPressed(2);
        remote.undoButtonWasPressed();

        remote.onButtonPressed(3);
        remote.offButtonPressed(3);
        remote.undoButtonWasPressed();
    }

}