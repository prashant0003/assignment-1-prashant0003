package linklist;

import node.Node;

public class LinkList<type> {
    private Node<type> head;
    private Node<type> tail;

    public void insertAtLast(type data) {
        Node<type> node = new Node<>(data);
        if (head == null) {
            head = tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
    }

    private int compareString(type a, type b) {
        String s1 = a.toString();
        String s2 = b.toString();
        int length;
        boolean equalLength = false;
        if (s1.length() > s2.length()) {
            length = s2.length();
        } else if (s1.length() < s2.length()) {
            length = s1.length();
        } else {
            length = s1.length();
            equalLength = true;
        }
        for (int i = 0; i < length; i++) {
            if (s1.charAt(i) > s2.charAt(i)) {
                return 1;
            } else if (s1.charAt(i) < s2.charAt(i)) {
                return -1;
            }
        }
        if (equalLength) {
            return 0;
        } else {
            if (length == s1.length()) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public boolean sortedInsert(type data) {
        Node<type> node = new Node<>(data);
        if (head == null) {
            insertAtLast(data);
            return true;
        }
        Node<type> temp = head;
        Node<type> temp1 = null;
        while (temp != null) {
            int n = compareString(data, temp.getData());
            if (n == -1) {
                if (temp1 == null) {
                    node.setNext(head);
                    head = node;
                } else {
                    temp1.setNext(node);
                    node.setNext(temp);
                }
                return true;
            } else if (n == 1) {
                temp1 = temp;
                temp = temp.getNext();
            } else {
                return false;
            }
        }
        insertAtLast(data);
        return true;
    }

    public Cursor connect() {
        return new Cursor();
    }

    public type delete(int index) {
        Node<type> temp = head;
        Node<type> temp1 = null;
        if (head == null) {
            return null;
        } else if (index == 0) {
            if (head.getNext() == null) {
                head = tail = null;
            } else {
                head = head.getNext();
            }
            return temp.getData();
        } else {
            for (int i = 0; i < index; i++) {
                if (temp != null) {
                    temp1 = temp;
                    temp = temp.getNext();
                } else {
                    return null;
                }
            }
            if (temp1 != null) {
                if (temp.getNext() == null) {
                    temp1.setNext(null);
                    tail = temp1;
                } else {
                    temp1.setNext(temp.getNext());
                }
                return temp.getData();
            } else {
                return null;
            }
        }
    }

    public class Cursor {
        private Node<type> cursor;

        private Cursor() {
            cursor = head;
        }

        public void setAtBeginning() {
            cursor = head;
        }

        public type data() {
            if (cursor != null) {
                return cursor.getData();
            }
            return null;
        }

        public boolean hasNext() {
            if (cursor != null) {
                return cursor.getNext() != null;
            }
            return false;
        }

        public void setAtNext() throws Exception {
            if (hasNext()) {
                cursor = cursor.getNext();
            } else throw new Exception("Index Out Of Bound");
        }

        public boolean isData() {
            return cursor != null;
        }
    }
}
