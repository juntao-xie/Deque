public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    private class Node {
        Node prev;
        T item;
        Node next;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }
// sentinel.prev is the last, sentinel.next is the first
    //* Create a sentinel that points back to itself, acting as an empty linked-list deque */
    public LinkedListDeque() {
        sentinel = new Node(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item){
        Node node = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
        size += 1;
    }

    public void addLast(T item){
        Node node = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
        size += 1;
    }

    public T removeFirst(){
        if (isEmpty()) {
            return null;
        }
        T temp = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return temp;
    }

    public T removeLast(){
        if (isEmpty()) {
            return null;
        }
        T temp = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return temp;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node curr = sentinel.next;
        while (curr != sentinel) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public T get(int index){
        if (index > size) {
            return null;
        }
        int flag = 0;
        Node curr =  sentinel.next;
        while (flag < index){
            curr = curr.next;
            flag +=1;
        }
        return curr.item;
    }

    //recursive get method
    private T getRecursive(Node curr, int index) {
        if (index == 0) {
            return curr.item;
        } else {
            curr = curr.next;
            return getRecursive(curr, index - 1);
        }
    }

    public T getRecursive(int index){
        if (index > size) {
            return null;
        }else{
            return getRecursive(sentinel.next,index);
        }
    }


    public LinkedListDeque(LinkedListDeque other){
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }


    public static void main(String[] args) {
        LinkedListDeque<Integer> a = new LinkedListDeque<>();
        a.addFirst(1);

        System.out.println(a.get(0)); // Display the string.
    }


}