import java.util.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MyHashMapMain {
    public static void main(String[] args) {
        MyHashMap<Integer,String> map = new MyHashMap<>();
        map.put(1,"a");
        map.put(2,"b");
        map.put(3,"c");
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
        System.out.println(map.get(5));
        map.put(1,"aaa");
        System.out.println(map.get(1));
        map.remove(1);
        System.out.println(map.get(1));
    }
}
class MyHashMap<K,V>{
    private Node[] buckets;
    private int SIZE = 16;

    public  MyHashMap(){
        buckets = new Node[SIZE];
    }

    private static class Node<K,V> {
         K key;
         V value;
         Node<K,V> next;

        public Node(K key,V value,Node<K,V> node) {
            this.value = value;
            this.key = key;
            this.next = node;
        }
    }

    private int hash(K key){
        return key.hashCode() % SIZE;
    }

    public void put(K key, V value){
        if(key==null)
            return;
        int hash = hash(key);
        Node<K,V> newNode = new Node<K,V>(key,value,null);
        if(buckets[hash]==null){
            buckets[hash] = newNode;
        }
        else {
            Node<K,V> prev = null;
            Node<K,V> curr = buckets[hash];
            while(curr != null){
                if(curr.key.equals(key)){
                    if (prev ==null){
                        newNode.next = curr.next;
                        buckets[hash] = newNode;
                        return;
                    }
                    else {
                        newNode.next = curr.next;
                        prev.next = newNode;
                        return;
                    }
                }
                prev=curr;
                curr=curr.next;
            }
            prev.next=newNode;
        }
    }

    public V get(K key){
        int hash = hash(key);
        if(buckets[hash]==null){
            return null;
        }
        else {
            Node<K,V> temp = buckets[hash];
            while(temp!= null) {
                if (temp.key.equals(key)) {
                    return temp.value;
                }
                temp = temp.next;
            }
        }
        return null;
    }
    public void remove(K key){
        int hash=hash(key);

        if(buckets[hash] == null){
            return;
        }else{
            Node<K,V> prev = null;
            Node<K,V> curr = buckets[hash];

            while(curr != null){
                if(curr.key.equals(key)){
                    if(prev==null){
                        buckets[hash]=buckets[hash].next;
                        return;
                    }
                    else{
                        prev.next=curr.next;
                        return;
                    }
                }
                prev=curr;
                curr = curr.next;
            }
            return;
        }
    }
}
