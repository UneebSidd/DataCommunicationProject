import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;


public class ClientHandler extends Thread {
        private Socket clientSocket;
        private Map sharedObject;

        public ClientHandler(Socket socket, Map map) {
            this.clientSocket = socket;
            this.sharedObject = map;
        }

        public void run() {
            try {
                // read shared object from client
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                Map clientSharedObject = (Map) in.readObject();


                // update shared object with client changes
                synchronized (sharedObject) {
                    sharedObject.setMapMatrix(clientSharedObject.getMapMatrix());
                    System.out.println("Saving changes...");
                    System.out.println("Updated map: " + sharedObject);
                }


                in.close();
                clientSocket.close();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error handling client: " + e.getMessage());
            }
        }
    }

