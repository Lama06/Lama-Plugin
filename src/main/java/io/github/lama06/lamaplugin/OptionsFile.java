package io.github.lama06.lamaplugin;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

public class OptionsFile<T> {
    private final Class<T> type;
    private final File file;
    public T cachedOptions = null;

    public OptionsFile(Class<T> type, File file) {
        this.type = type;
        this.file = file;
    }

    public T load() {
        try {
            if(cachedOptions != null) {
                return cachedOptions;
            }

            if(!file.exists()) {
                file.createNewFile();
                Util.writeToFile(file, "{}");
            }

            String serializedJson = Util.readFromFile(file);

            Gson gson = new Gson();

            T json = gson.fromJson(serializedJson, type);

            cachedOptions = json;
            return json;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(T options) {
        try {
            cachedOptions = options;

            Gson gson = new Gson();

            String serializedJson = gson.toJson(options);

            if(!file.exists()) {
                file.createNewFile();
            }

            Util.writeToFile(file, serializedJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        save(cachedOptions);
    }
}
