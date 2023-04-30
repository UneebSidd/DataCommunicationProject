import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    private Map catMap;
    private Cat cat;

    public Server() {
        this.catMap = new Map();
        this.catMap.addCat(new Cat("Cat1", 0, 0, "Black", "Fish"));
        this.catMap.addCat(new Cat("Cat2", 0, 0, "Gray", "Milk"));
        this.catMap.addCat(new Cat("Cat3", 0, 0, "Orange", "Meat"));
        this.catMap.addCat(new Cat("Cat4", 0, 2, "White", "Fish"));
        this.cat = new Cat("mano", 0,0,"Black", "any");
        this.catMap.setValue(1);
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server started on port 1234");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            // send reference to shared object to client
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.writeObject(catMap);
            out.flush();
            out.writeObject(cat);
            out.flush();

            // start client handler thread
            ClientHandler clientHandler = new ClientHandler(clientSocket, catMap);
            clientHandler.start();
        }
    }


    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
    }
}