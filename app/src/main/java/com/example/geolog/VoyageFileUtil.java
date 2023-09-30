package com.example.geolog;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class VoyageFileUtil {
    private static final String FILENAME = "voyages.dat";

    public static void writeVoyages(Context context, List<Voyage> voyages) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Écrivez la liste complète d'objets Voyage dans le fichier
            objectOutputStream.writeObject(voyages);

            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Voyage> readVoyages(Context context) {
        List<Voyage> voyages = new ArrayList<>();

        try {
            FileInputStream fileInputStream = context.openFileInput(FILENAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            // Lisez la liste complète d'objets Voyage depuis le fichier
            Object object = objectInputStream.readObject();
            if (object instanceof List<?>) {
                List<?> objectList = (List<?>) object;
                for (Object item : objectList) {
                    if (item instanceof Voyage) {
                        voyages.add((Voyage) item);
                    }
                }
            }

            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return voyages;
    }
}

