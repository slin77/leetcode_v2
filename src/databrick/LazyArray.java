package databrick;

import java.util.ArrayList;
import java.util.function.Function;

public class LazyArray<E> {

    ArrayList<Function<E, E>> fs;
    E[] arr;

    public LazyArray() {
        fs = new ArrayList<>();
    }

    public LazyArray<E> map(Function<E, E> f) {
        fs.add(f);
        return this;
    }

    public int indexOf(int ele) {
        for (int i = 0; i < arr.length; i++) {
            E e = arr[i];
            for (Function<E, E> f : fs) {
                e = f.apply(e);
            }
            if (e.equals(ele)) {
                return i;
            }
        }
        return -1;
    }


}
