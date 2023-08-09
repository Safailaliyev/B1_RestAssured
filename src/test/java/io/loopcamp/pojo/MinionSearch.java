package io.loopcamp.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
public class MinionSearch {
    //private List<Map<String, Objects>> content;
    private List<Minion> content;
    private int totalElement;
}
