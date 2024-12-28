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

class CeilingFan {
    public static final int HIGH = 3;
    public static final int MEDIUM = 2;
    public static final int LOW = 1;
    public static final int OFF = 0;

    String ceilingFan;
    int speed;

    public CeilingFan(String fan) {
        this.ceilingFan = fan;
        speed = OFF;
    }
    

    public void off() {
        System.out.println( ceilingFan + " fan is off");
        speed = OFF;
    }

    public void high() {
        speed = HIGH;
        System.out.println(ceilingFan + " ceiling fan is on high");
    }

    public void medium() {
        speed = MEDIUM;
        System.out.println(ceilingFan + " ceiling fan is on medium");
    }

    public void low() {
        speed = LOW;
        System.out.println(ceilingFan + " ceiling fan is on low");
    }

    public int getSpeed() {
        return this.speed;
    }
}

class CeilingFanHighCommand implements Command {
    CeilingFan ceilingFan;
    int prevSpeed;

    public CeilingFanHighCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.high();
    }

    @Override
    public void undo() {
        if(prevSpeed == CeilingFan.HIGH) {
            ceilingFan.high();
        } else if(prevSpeed == CeilingFan.MEDIUM) {
            ceilingFan.medium();
        } else if(prevSpeed == CeilingFan.LOW) {
            ceilingFan.low();
        } else if(prevSpeed == CeilingFan.OFF) {
            ceilingFan.off();
        }
    }
}

class CeilingFanMediumCommand implements Command {
    CeilingFan ceilingFan;
    int prevSpeed;

    public CeilingFanMediumCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.medium();
    }

    @Override
    public void undo() {
        if(prevSpeed == CeilingFan.HIGH) {
            ceilingFan.high();
        } else if(prevSpeed == CeilingFan.MEDIUM) {
            ceilingFan.medium();
        } else if(prevSpeed == CeilingFan.LOW) {
            ceilingFan.low();
        } else if(prevSpeed == CeilingFan.OFF) {
            ceilingFan.off();
        }
    }
}

class CeilingFanLowCommand implements Command {
    CeilingFan ceilingFan;
    int prevSpeed;

    public CeilingFanLowCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.low();
    }

    @Override
    public void undo() {
        if(prevSpeed == CeilingFan.HIGH) {
            ceilingFan.high();
        } else if(prevSpeed == CeilingFan.MEDIUM) {
            ceilingFan.medium();
        } else if(prevSpeed == CeilingFan.LOW) {
            ceilingFan.low();
        } else if(prevSpeed == CeilingFan.OFF) {
            ceilingFan.off();
        }
    }
}


class CeilingFanOffCommand implements Command {
    CeilingFan ceilingFan;
    int prevSpeed;

    public CeilingFanOffCommand(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.off();
    }

    public void undo() {
        if (prevSpeed == CeilingFan.HIGH) {
			ceilingFan.high();
		} else if (prevSpeed == CeilingFan.MEDIUM) {
			ceilingFan.medium();
		} else if (prevSpeed == CeilingFan.LOW) {
			ceilingFan.low();
		} else if (prevSpeed == CeilingFan.OFF) {
			ceilingFan.off();
		}
    }
}

public class Main5 {

    public static void main(String[] args) {
        
        RemoteControl remote = new RemoteControl();
    
        CeilingFan ceilingFan = new CeilingFan("Living Room");
     
        CeilingFanMediumCommand cFanMediumCommand = new CeilingFanMediumCommand(ceilingFan);
        CeilingFanHighCommand cFanHighCommand = new CeilingFanHighCommand(ceilingFan);
        CeilingFanOffCommand cFanOffCommand = new CeilingFanOffCommand(ceilingFan);

        remote.setCommand(0, cFanMediumCommand, cFanOffCommand);
        remote.setCommand(1, cFanHighCommand, cFanOffCommand);

        System.out.println(remote);

        remote.onButtonPressed(0);
        remote.offButtonPressed(0);
        remote.undoButtonWasPressed();

        remote.onButtonPressed(1);
        remote.undoButtonWasPressed();
    }

}