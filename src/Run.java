import NetWork.Client;
import NetWork.Server;

public class Run {
    public static void main(String[] args){
        Client.main(args,1);
        Client.main(args,2);
        Server.main(args);

    }
}
