import java.io.*;
import java.net.Socket;

public class ServerConnection implements Runnable{

    private Socket server;
    private ObjectInputStream in;

    public ServerConnection(Socket s) throws IOException, ClassNotFoundException {

        this.server = s;
        in = new ObjectInputStream(server.getInputStream());


    }

    @Override
    public void run() {

        Object serverResponse;
        try {
            while(true) {
                serverResponse = in.readObject();
                if(serverResponse == null)
                {
                    System.out.println("tadag");
                    break;
                }
                System.out.println("TAgsags");
                System.out.println("[Server]: " + serverResponse );
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}