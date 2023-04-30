import java.io.Serializable;
import java.util.ArrayList;
public class Map implements Serializable {
    private int value;
    private ArrayList<Cat>[][] mapMatrix;

    public Map()
    {
        this.mapMatrix = new ArrayList[3][3];
        for (int i = 0; i < mapMatrix.length; i++) {
            for (int j = 0; j < mapMatrix[i].length; j++) {
                mapMatrix[i][j] = new ArrayList<Cat>();
            }
        }
    }



        public synchronized void setMapMatrix(ArrayList<Cat>[][] mapMatrix) {
        this.mapMatrix = mapMatrix;
    }

    public synchronized ArrayList<Cat>[][] getMapMatrix() {
        return mapMatrix;
    }

        public synchronized ArrayList<Cat> getCatsAtLocation(int x, int y) {
        return mapMatrix[x][y];
    }

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int newValue) {
        this.value = newValue;
    }

        public synchronized void setCatsAtLocation(int x, int y, ArrayList<Cat> cats) {
        mapMatrix[x][y] = cats;
    }

        public synchronized void addCat(Cat cat) {
        mapMatrix[cat.getX()][cat.getY()].add(cat);
    }

    public synchronized String toString() {
        StringBuilder temp = new StringBuilder();
        temp.append("Cat Map:\n");

        for (int i = 0; i < this.mapMatrix.length; ++i) {
            temp.append("| ");

            for (int j = 0; j < this.mapMatrix[i].length; ++j) {
                int numCats = this.mapMatrix[i][j].size();
                temp.append(numCats).append(" ");

//                if (numCats > 0) {
//                    temp.append("(").append(getCatNamesAtLocation(i, j)).append(")");
//                }
            }

            temp.append("|\n");
        }

        return temp.toString();
    }

}

//public class Map implements Serializable {
//    private ArrayList<Cat>[][] mapMatrix;
//
//    //make the 3* 3 arrayList object
//    public Map() {
//        this.mapMatrix = new ArrayList[3][3];
//        for (int i = 0; i < mapMatrix.length; i++) {
//            for (int j = 0; j < mapMatrix[i].length; j++) {
//                mapMatrix[i][j] = new ArrayList<Cat>();
//            }
//        }
//    }
//
//    public synchronized ArrayList<Cat> getCatsAtLocation(int x, int y) {
//        return mapMatrix[x][y];
//    }
//
//    public synchronized void setMapMatrix(ArrayList<Cat>[][] mapMatrix) {
//        this.mapMatrix = mapMatrix;
//    }
//
//    public synchronized ArrayList<Cat>[][] getMapMatrix() {
//        return mapMatrix;
//    }
//
//    public synchronized void addCat(Cat cat) {
//        mapMatrix[cat.getX()][cat.getY()].add(cat);
//    }
//
//    public synchronized void removeCat(Cat cat) {//dont need it , van do it in the matix
//        System.out.println("Removing cat: " + cat.getName());//for testing the cat is removed
//        mapMatrix[cat.getX()][cat.getY()].remove(cat);
//    }
//
//
//
//    public synchronized String getCatNamesAtLocation(int x, int y) {
//        ArrayList<Cat> cats = getCatsAtLocation(x, y);
//        StringBuilder catNames = new StringBuilder();
//        for (Cat cat : cats) {
//            catNames.append(cat.getName()).append(", ");
//        }
//        if (catNames.length() > 2) {
//            catNames.setLength(catNames.length() - 2);
//        }
//        return catNames.toString();
//    }
//
//    public synchronized void setCatsAtLocation(int x, int y, ArrayList<Cat> cats) {
//        mapMatrix[x][y] = cats;
//    }
//
//
//
//
//
////    public Map updateCatIsFed(String catName) {
////        for (int i = 0; i < mapMatrix.length; i++) {
////            for (int j = 0; j < mapMatrix[i].length; j++) {
////                for (Cat cat : mapMatrix[i][j]) {
////                    if (cat.getName().equals(catName)) {
////                        cat.setFed(true);
////                        if (getCatsAtLocation(cat.getX(), cat.getY()).size() == 1) {
////                            removeCat(cat);
////                        }
////                        return this;
////                    }
////                }
////            }
////        }
////        return this;
////    }
//
//
//
//
//    public synchronized String toString() {
//        StringBuilder temp = new StringBuilder();
//        temp.append("Cat Map:\n");
//
//        for (int i = 0; i < this.mapMatrix.length; ++i) {
//            temp.append("| ");
//
//            for (int j = 0; j < this.mapMatrix[i].length; ++j) {
//                int numCats = this.mapMatrix[i][j].size();
//                temp.append(numCats).append(" ");
//
////                if (numCats > 0) {
////                    temp.append("(").append(getCatNamesAtLocation(i, j)).append(")");
////                }
//            }
//
//            temp.append("|\n");
//        }
//
//        return temp.toString();
//    }
//
//
//
//}

