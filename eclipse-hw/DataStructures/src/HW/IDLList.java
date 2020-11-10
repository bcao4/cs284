//Brandon Cao
//CS284B
//I pledge my honor that I have abided by the Stevens Honor System

package HW;

import java.util.ArrayList;

public class IDLList<E> {

    private class Node<E> {
        // Data fields
        private E data;
        private Node<E> next;
        private Node<E> prev;

        // Constructors

        /* Creates a node holding elem */
        Node(E elem) {
            data = elem;
            next = null;
            prev = null;
        }

        /* Creates a node holding elem, with next as next and prev as prev */
        Node(E elem, Node<E> prev, Node<E> next) {
            this.data = elem;
            this.prev = prev;
            this.next = next;
        }
    }

    // Data fields
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;

    /* Creates an empty double linked list */
    public IDLList() {
        head = null;
        tail = null;
        size = 0;
        indices = new ArrayList<Node<E>>();
    }

    /* Adds elem at position index, counting from wherever head is */
    public boolean add(int index, E elem) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        } else if (head == null || index == 0) {
            this.add(elem);
        } else if (index == size) {
            this.append(elem);
        } else {
            Node<E> temp;
            temp = new Node<E>(elem, indices.get(index-1), indices.get(index));
            indices.get(index-1).next = temp;
            indices.get(index).prev = temp;
            indices.add(index, temp);
            size++;
        }
        return true;
    }

    /* Adds elem at head */
    public boolean add(E elem) {
        if (head == null) {
            head = new Node<E>(elem);
            tail = head;
        } else {
            Node<E> current = head;
            head = new Node<E>(elem, null, current);
            current.prev = head;
        }
        indices.add(0, head);
        size++;
        return true;
    }

    /* Adds elem as the new last element of the list */
    public boolean append (E elem){
        if (head == null) {
            this.add(elem);
        } else {
            Node<E> current = head;

            while (current.next!=null) {
                current=current.next;
            }
            current.next = new Node<E>(elem, tail, null);
            tail = current.next;
            size++;
            indices.add(size - 1, tail);
        }
        return true;
    }

    /* returns the object at position index from the head. */
    public E get (int index) {
        if (head == null || index < 0 || index > size - 1) { //empty list
            throw new IndexOutOfBoundsException();
        } else {
        return indices.get(index).data;
        }
    }

    /* returns the object at the head */
    public E getHead() {
        if (size == 0) { //empty list
            throw new IllegalArgumentException();
        }
        return head.data;
    }

    /* returns the object at the tail */
    public E getLast() {
        if (size == 0) { //empty list
            throw new IllegalArgumentException();
        }
        return tail.data;
    }

    /* returns the list size */
    public int size() {
        return size;
    }


    /* removes and returns the element at the head */
	public E remove() {
		if (head == null) {
			throw new IllegalArgumentException();
		} else if (size == 1){
				head = null;
				tail = null;
				return null;
		} else {
				E temp = head.data;
				head = head.next;
				head.prev = null;
				size--;
				indices.remove(0);
				return temp;
			}
		}
	

    /* removes and returns the element at the tail */
    public E removeLast() {
        if (head == null) {
            throw new IllegalArgumentException();
        } else {
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                E temp = tail.data;
                tail = tail.prev;
                tail.next = null;                
                size--;
                indices.remove(size);
                return temp;
            }
        }
        return null;
    }

    /* removes and returns the element at the index index */
    public E removeAt(int index) {
        Node<E> temp = null;
        if  (head == null || index > size || index < 0) {
            throw new IllegalArgumentException();
        } else if (index == 0) {
            return remove();
        } else if (index == size -1) {
            return removeLast();
        } else {
            temp = indices.get(index);
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
            temp.prev = null;
            temp.next = null;
            size--;
            indices.remove(index);
            return temp.data;

        }

    }

    /* removes the first occurrence of elem in the list and returns true.
    Return false if elem was not in the list. */
    public boolean remove (E elem) {
        if (head == null) {
            return false;
        } else {
            E temp;
            for (int i = 0; i < size; i++) {
                temp = get(i);
                if (temp == elem) {
                    removeAt(i);
                    return true;
                }
            }
            return false;
        }
    }

    /* presents a string representation of the list */
    public String toString() {
        StringBuilder str = new StringBuilder("(");
        Node<E> current = head;
        if (current != null) {
            while (current.next != null) {
                str.append(current.data.toString());
                str.append(", ");
                current = current.next;
            }
            str.append(current.data.toString());
        }
        str.append(")");
        return str.toString();
    }


}