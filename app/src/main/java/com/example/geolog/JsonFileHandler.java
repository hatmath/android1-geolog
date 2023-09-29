package com.example.geolog;

import android.content.Context;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFileHandler {

    public static void writeGeologInfoListToJson(Context context, ArrayList<GeologInfo> geologInfoList) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(context.getFilesDir(), "geolog_info_list.json");
            objectMapper.writeValue(file, geologInfoList);
            // Vous pouvez utiliser context.getExternalFilesDir() pour stocker le fichier dans un emplacement externe.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<GeologInfo> readGeologInfoListFromJson(Context context) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(context.getFilesDir(), "geolog_info_list.json");
            return objectMapper.readValue(file, new TypeReference<ArrayList<GeologInfo>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
