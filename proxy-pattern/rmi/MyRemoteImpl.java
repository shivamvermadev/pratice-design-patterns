
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

interface MyRemote extends Remote {
    String sayHello() throws RemoteException;
}

class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

    private static final long serialVersionUID = 1L;


    public MyRemoteImpl() throws RemoteException {}

    @Override
    public String sayHello() {
        return "server says hello";
    }

    public static void main(String[] args) {
        System.out.println("hellow");
        try {
            MyRemote sevice = new MyRemoteImpl();
            Naming.bind("RemoteHello", sevice);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }   
}