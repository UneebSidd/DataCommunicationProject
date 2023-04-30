import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Connected to server: " + socket.getInetAddress().getHostAddress());

        // receive reference to shared object from server
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ArrayList<Cat> cats;
        Map sharedMap = (Map) in.readObject();
        System.out.println("Received map: " + sharedMap);

        // modify shared object
        Scanner input = new Scanner(System.in);
        int x;
        int y;
        while(true)
        {
            System.out.println("Enter x coordinate:");
             x = input.nextInt();
            System.out.println("Enter y coordinate:");
             y = input.nextInt();
            cats = sharedMap.getCatsAtLocation(x, y);

            if (cats.size() > 0)
            {
                System.out.println("Cats at location " + x + "," + y + ":");
                for (Cat cat : cats) {
                    System.out.println(cat);

                }
                break;

            }
            else
            {
                System.out.println("No cats at location " + x + "," + y);
            }
        }

        System.out.println("Enter the name of the cat you want to feed:");
        String catName = input.next();
        Cat catToRemove = null;
        for (Cat cat : cats) {

            if (cat.getName().equalsIgnoreCase(catName)) {
                cat.setFed(true);
                catToRemove = cat;
                System.out.println("Feeding Cat " + catToRemove);
                break;
            }
        }

        if (catToRemove != null) {
            cats.remove(catToRemove);
            System.out.println("cats array list now "+cats);
        }
        else
        {
            System.out.println("There is no cat with name " + catName + " at location : " + x + ", " + y);
        }


        sharedMap.setCatsAtLocation(x, y, cats);
        System.out.println("updated Map after removing fed cat from the un-fed cats at specified location  "+sharedMap);


        // send modified shared object back to server
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(sharedMap);
        out.flush();

        in.close();
        out.close();
        socket.close();
    }
}

//public class Client {
//
//    private String serverName = "localhost";
//    private int serverPort = 2023;
//    private Socket socket = null;
//    private ObjectInputStream ois = null;
//    private ObjectOutputStream oos = null;
//    private Scanner scanner = new Scanner(System.in);
//    private Map cMap = null;
//
//    public Client() {
//        try {
//            // connect to server and get map
//            socket = new Socket(serverName, serverPort);
//            System.out.println("Connected to server " + socket.getRemoteSocketAddress());
//            oos = new ObjectOutputStream(socket.getOutputStream());
//            ois = new ObjectInputStream(socket.getInputStream());
//            //oos.writeObject("getMap");
//            //oos.flush();
//            //get the map for input stream and cast it to Map
//            cMap = (Map) ois.readObject();
//            System.out.println("Received map: " + cMap);
//        } catch (Exception e) {
//            System.out.println("Error:13 " + e.getMessage());
//        }
//
//    }
//    //after we got the map , we ask user for the input x,y to slect the location in matrix
//
//    //get the cats in selected location
//    public void start() {
//        try {
//            ArrayList<Cat> cats;
//
//            System.out.println("Loading...");
//
//            int x;
//            int y;
//            // prompt user for input
//            while(true){
//                System.out.println("Enter x coordinate:");
//                x = scanner.nextInt();
//                System.out.println("Enter y coordinate:");
//                y = scanner.nextInt();
//
//                oos.writeObject("GETCATS " + x + "," + y);
//                oos.flush();
//
//                // server should get back to me with the cat arraylist, and i as a user read the list of cats from the server
//                cats = (ArrayList<Cat>) ois.readObject();
//
//                // display the list of cats from send back array list, and test if it empty.
//
//                if (cats.size() > 0) {
//                    System.out.println("Cats at location " + x + "," + y + ":");
//                    for (Cat cat : cats) {
//                        System.out.println(cat);
//
//                    }
//                    break;
//
//                } else {
//                    System.out.println("No cats at location " + x + "," + y);
//                }
//            }
//
//            // after user read the cat's info, prompt user to select a cat to feed
//            System.out.println("Enter the name of the cat you want to feed:");
//            String catName = scanner.next();
//
//            //will do isCatTaken first before setFed if we have extra time
//            Cat catToRemove = null;
//            for (Cat cat : cats) {
//
//                if (cat.getName().equalsIgnoreCase(catName)) {
//                    cat.setFed(true);
//                    catToRemove = cat;
//                    System.out.println("Feeding Cat " + catToRemove);
//                    break;
//                }
//            }
//
//            if (catToRemove != null) {
//                cats.remove(catToRemove);
//            }
//
//            System.out.println("cats array list now "+cats);
//
//            cMap.setCatsAtLocation(x, y, cats);
//            System.out.println("cMap after removing fed cat  "+cMap);
//
//            // remove the cat after the select the cat, (the statu setFed =ture)
//            Map updatedCMap=cMap; //= updateCatIsFed(catName);//remove and save as updat
//
//            // send the updated map back to the server
//            oos.writeObject("updatemapfromC");
//            oos.flush();
//            oos.writeObject(updatedCMap);
//            oos.flush();
//
//            //should get back the new updated map form server to verfy the server is updated.
//            // receive updated map from server
//
//            Object responseObj = ois.readObject();
//            if (responseObj instanceof String && responseObj.equals("UPDATEDSMap")) {
//                cMap = (Map) ois.readObject();
//                System.out.println("Received updated map from server: " + cMap);
//            }
//
//
//        } catch (Exception e) {
//            System.out.println("Error:14 " + e.getMessage());
//        } finally {
//            try {
//                // close the socket
//                socket.close();
//            } catch (IOException e) {
//                System.out.println("Error: 15" + e.getMessage());
//            }
//        }
//    }
//    //send request to server, and ask to get the location back,
//    public void requestCatsAtLocation(int x, int y) throws IOException, ClassNotFoundException {
//        // send the request to the server, let the server take care of the getcats if equal.
//        oos.writeObject("GETCATS " + x + "," + y);
//        oos.flush();
//
//        // server should get back to me with the cat arraylist, and i as a user read the list of cats from the server
//        ArrayList<Cat> cats = (ArrayList<Cat>) ois.readObject();
//
//        // display the list of cats from send back array list, and test if it empty.
//        if (cats.size() > 0) {
//            System.out.println("Cats at location " + x + "," + y + ":");
//            for (Cat cat : cats) {
//                System.out.println(cat);
//            }
//        } else {
//            System.out.println("No cats at location " + x + "," + y);
//        }
//    }
//
//    public static void main(String[] args) {
//        Client client = new Client();
//        client.start();
//    }
//}