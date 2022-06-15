package Game;

import java.util.List;

public interface JsonHandler {
    void save(List<Object> list, String fileName);

    void jsonToList(List<Object> list, String fileName);
}
