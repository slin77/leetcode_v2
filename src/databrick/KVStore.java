package databrick;

public class KVStore {

    class RingBuffer {
        int[] arr;
        int head;
        int tail;
        public int size;

        public void insert(int timestamp) {
            if (size == arr.length) {
                removeTtl();
            }
            if (size == arr.length) {
                // not allowed
                return;
            };
            int idx = (head + 1) % arr.length;
            arr[idx] = timestamp;
            size++;
        }

        public void removeTtl() {
            int cur = 1000;
            if (arr[tail] - cur > 300) {
                tail = (tail + 1) % arr.length;
                size--;
            }
        }
    }


    class Node {
        int key;
        int value;
        Node next;
    }
    int size = 10001;
    Node[] arr = new Node[size];

    public KVStore() {
        arr = new Node[size];
    }

    public void put(int key, int value) {
        int idx = Integer.hashCode(key) % size;
        Node head = arr[idx];
        if (head == null) {
            head = new Node();
            head.key = key;
            head.value = value;
            arr[idx] = head;
            return;
        }
        Node n = head;
        while (n != null && n.key != key) {
            n = n.next;
        }
        if (n != null) {
            n.value = value;
        } else {
            Node newN = new Node();
            newN.key = key;
            newN.value = value;
            newN.next = head;
            arr[idx] = newN;
        }
    }

    public int get(int key) {
        int idx = Integer.hashCode(key) % size;
        Node n = arr[idx];
        while (n != null && n.key != key) {
            n = n.next;
        }
        if (n != null) {
            return n.value;
        }
        return -1;
    }

    public void remove(int key) {
        int idx = Integer.hashCode(key) % size;
        Node prev = null;
        Node n = arr[idx];
        if (n != null && n.key != key) {
            prev = n;
            n = n.next;
        }
        if (n == null) {
            return;
        }
        if (prev == null) {
            arr[idx] = n.next;
        } else {
            prev.next = n.next;
        }
    }
}
