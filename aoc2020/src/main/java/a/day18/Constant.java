package a.day18;

import lombok.Data;

@Data
class Constant extends Node {
    Long n;

    public Constant(Long n) {
        this.n = n;
    }
}
