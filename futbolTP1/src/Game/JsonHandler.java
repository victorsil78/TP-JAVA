package Game;

import java.util.List;

public interface JsonHandler {
    void persistData(List<Object> list);
    void jsonToList(List<Object> list);
}
