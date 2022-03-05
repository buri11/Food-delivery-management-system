package DataLayer;

import BusinessLayer.DeliveryService;

import java.io.*;

/**
 * The type Serializator.
 */
public class Serializator {
    /**
     * Instantiates a new Serializator.
     */
    public Serializator(){

    }

    /**
     * Serialize.
     *
     * @param ds       the ds
     * @param filename the filename
     */
    public void serialize(DeliveryService ds, String filename){


        FileOutputStream file = null;
        ObjectOutputStream obj = null;
        try {
             file = new FileOutputStream(filename);
             obj = new ObjectOutputStream(file);
             obj.writeObject(ds);
            obj.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserialize delivery service.
     *
     * @param fileName the file name
     * @return the delivery service
     */
    public DeliveryService deserialize(String fileName){
        DeliveryService ds = null;
        try {
            FileInputStream file = new FileInputStream(fileName);
            if(file.getChannel().size() != 0){
                ObjectInputStream obj = new ObjectInputStream(file);
                ds = (DeliveryService)obj.readObject();
                obj.close();
                file.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ds;
    }
}
