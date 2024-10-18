package org.hbrs.se1.ws24.exercises.uebung3.persistence;
import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "objects.ser";

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     * Look-up in Google for further help! Good source:
     * https://www.digitalocean.com/community/tutorials/objectoutputstream-java-write-object-file
     * (Last Access: Oct, 15th 2024)
     */
    public void save(List<E> member) throws PersistenceException {
        try {
            FileOutputStream fos = new FileOutputStream("Members.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(member);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException {
        // Some Coding hints ;-)

        ObjectInputStream ois = null;
        FileInputStream fis = null;
        List<E> newListe = null;
        //
        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        try {
            fis = new FileInputStream("Members.ser");

            // Tipp: Use a directory (ends with "/") to implement a negative test case ;-)
            ois = new ObjectInputStream(fis);

            // Reading and extracting the list (try .. catch ommitted here)
            Object obj = (Object) ois.readObject();

            if (obj instanceof List<?>) {
                newListe = (List) obj;
                return newListe;

                // and finally close the streams
            }
            return null;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
