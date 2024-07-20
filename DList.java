// Convert Singly Linked List to Doubly Linked List
public class DList<T>
{
    public class Node
    {
        public T data;
        public Node next;
        public Node previous;
        public Node(T t) {
            previous = null;
            next = null;
            data = t;
        }
    }
    Node tail;
    Node head;
    public DList() {
        tail = null;
        head = null;
        }  // default constructor
    // appends to tail of list
    void Append(T data)
    {
        Node pdata = new Node(data);
        if (head == null)
        {
            head = pdata;
            head.previous = null;
        }
        else
        {
            tail.next = pdata;
            pdata.previous = tail;
        }
        tail = pdata;
    }
    // prepends to head of list
    void Prepend(T data)
    {

        Node pdata = new Node(data);
        if (tail == null)
        {
            tail = pdata;
            tail.next = null;
        }
        else
        {
            head.previous = pdata;
            pdata.next = head;
        }
        head = pdata;
    }
    // inserts data after found data
    void InsertAfter(T find, T data)
    {
        if (IsEmpty()) {
            head = new Node(data);
            tail = head;
            return;
        }
        Node pdata = head;
        // case if the tail is where we want to InsertAfter
        if (tail.data.equals(find)) {
            Append(data);
            return;
        }

        boolean found = false;
        // iterate through and find the data
        while (pdata.next != null) {
            if (pdata.data.equals(find)){
                found = true;
                break;
            }
            pdata = pdata.next;
        }

        // inserting node
        if (found || head.data.equals(find)) {
                Node insertNode = new DList.Node(data);
                pdata.next.previous = insertNode;
                insertNode.next = pdata.next;
                pdata.next = insertNode;
                insertNode.previous = pdata;

        }

    }

    // inserts data before found data
    void InsertBefore(T find, T data)
    {
        if (IsEmpty()) {
            head = new Node(data);
            tail = head;
            return;
        }
        Node pdata = head;
        // case if the head is where we want to InsertBefore
        if (head.data.equals(find)) {
            Prepend(data);
            return;
        }

        boolean found = false;
        // iterate through and find the data
        while (pdata != null) {
            if (pdata.data.equals(find)){
                found = true;
                break;
            }
            pdata = pdata.next;
        }

        if (found) {
            Node insertNode = new Node(data);
            pdata.previous.next = insertNode;
            insertNode.previous = pdata.previous;
            pdata.previous = insertNode;
            insertNode.next = pdata;

        }
    }
    // finds data node and returns it
   Node Search(T data)
    {
        // checking if list is empty
        if (IsEmpty()) {
            return null;
        }

        DList.Node pdata = head;

        // iterate through and find the data
        while (pdata != null) {
            if (pdata.data.equals(data)){
                return pdata;
            }
            pdata = pdata.next;
        }

        return null;
    }

    // deletes a node from the list
    void Delete(T data)
    {
        // checking if list is empty
        if (IsEmpty()) {
            return;
        }
        // special case if deleting the tail or head

        if (tail.data == null || tail.data.equals(data)) {
            if (tail.previous != null) {
                tail = tail.previous;
                tail.next = null;
            } else {
                tail = null;
                head = null;
            }
            return;
        } else if (head.data == null || head.data.equals(data)) {
            if (head.next != null) {
                head = head.next;
                head.previous = null;
            } else {
                // If head.next is null, it means we're deleting the only node in the list
                head = null;
                tail = null;
            }
            return;
        }

        Node pdata = head;

        // iterate through and find the data
        boolean found = false;
        while (pdata != null) {
            if (pdata.data.equals(data)){
                found = true;
                break;
            }
            pdata = pdata.next;
        }

        // if data was not found
        if (!found)
            return;

        // deleting data
        pdata.previous.next = pdata.next;
        pdata.next.previous = pdata.previous;
    }
    // returns number of nodes in list
    int Count()
    {
        int count = 0;

        // checking if list is empty
        if (IsEmpty()) {
            return count;
        }

        DList.Node pdata = head;

        // iterate through
        while (pdata != null) {
            count++;
            pdata = pdata.next;
        }

        return count;
    }
    // returns true if list is empty
    boolean IsEmpty()
    {
        if (tail == null || head == null) {
            return true;
        }
        return false;  // return false if not empty
    }

    void Output()
    {
        if (IsEmpty())
            return;
        DList.Node rover = head;
        while (rover != null)
        {
            System.out.print( rover.data+"\t" );
            rover = rover.next;
        }
        System.out.println();
    }

   /*( void OutputRev() {
        Node start = tail;
        System.out.println("tail is "+tail.data);
        while (start != null) {
            System.out.print(start.data+"\t");
            start = start.previous;
        }
        System.out.println();
    }
    */

    public static void UnitTest(DList<Integer> list) {

        DList<Integer> listTwo = new DList<Integer>(); // for future test cases
        System.out.println("Prepend Test: With a full list");
        list.Prepend(12);
        list.Output();
        list.Delete(12);
        System.out.println("----------------");

        System.out.println("Prepend Test: With an empty list (line1: prepend 12, line2: prepend 20300");
        listTwo.Prepend(12);
        listTwo.Prepend(20300);
        listTwo.Output();
        listTwo.Delete(20300);
        listTwo.Delete(12);
        System.out.println("----------------");


        System.out.println("\n==============================================================");
        System.out.println("InsertAfter Test: after the tail");
        list.InsertAfter(9, -9);
        list.Output();
        list.Delete(-9);
        System.out.println("----------------");

        System.out.println("InsertAfter Test: after the head");
        list.InsertAfter(0, -12);
        list.Output();
        list.Delete(-12);
        System.out.println("----------------");

        System.out.println("InsertAfter Test: in the middle");
        list.InsertAfter(4, -4);
        list.Output();
        list.Delete(-4);
        System.out.println("----------------");

        System.out.println("InsertAfter Test: number not in the linked list");
        list.InsertAfter(100, -2000230);
        list.Output();
        System.out.println("----------------");

        System.out.println("InsertAfter Test: empty list");
        listTwo.InsertAfter(100, -2000230);
        listTwo.Output();
        listTwo.Delete(-2000230);
        System.out.println("----------------");

        System.out.println("\n==============================================================");
        System.out.println("InsertBefore Test: before the tail");
        list.InsertBefore(9, -9);
        list.Output();
        list.Delete(-9);
        System.out.println("----------------");

        System.out.println("InsertBefore Test: before the head");
        list.InsertBefore(0, -12);
        list.Output();
        list.Delete(-12);
        System.out.println("----------------");

        System.out.println("InsertBefore Test: before in the middle");
        list.InsertBefore(7, -7);
        list.Output();
        list.Delete(-7);
        System.out.println("----------------");

        System.out.println("InsertBefore Test:  number not in the linked list");
        list.InsertBefore(100, -7);
        list.Output();
        System.out.println("----------------");

        System.out.println("InsertBefore Test: an empty list");
        listTwo.InsertBefore(9, -9);
        listTwo.Output();
        listTwo.Delete(-9);
        System.out.println("----------------");


        System.out.println("\n==============================================================");
        System.out.println("Search Test: tail");
        System.out.println(list.Search(9));
        System.out.println("----------------");

        System.out.println("Search Test: head");
        System.out.println(list.Search(0));
        System.out.println("----------------");

        System.out.println("Search Test: middle node");
        System.out.println(list.Search(5));
        System.out.println("----------------");

        System.out.println("Search Test: number not in the list");
        System.out.println(list.Search(100));
        System.out.println("----------------");

        System.out.println("Search Test: empty list");
        System.out.println(listTwo.Search(100));
        System.out.println("----------------");


        System.out.println("\n==============================================================");
        System.out.println("Delete Test: the tail");
        list.Delete(9);
        list.Output();
        list.Append(9);
        System.out.println("----------------");

        System.out.println("Delete Test: deleting the head node");
        list.Delete(0);
        list.Output();
        list.Prepend(0);
        System.out.println("----------------");

        System.out.println("Delete Test: deleting one of the middle nodes");
        list.Delete(4);
        list.Output();
        list.InsertAfter(3, 4);
        System.out.println("----------------");

        System.out.println("Delete Test: a number not in the linked list");
        list.Delete(341278);
        list.Output();
        System.out.println("----------------");

        System.out.println("Delete Test: empty list");
        listTwo.Delete(341278);
        listTwo.Output();
        System.out.println("----------------");


        System.out.println("\n==============================================================");
        System.out.println("Count Test: with a full list");
        System.out.println(list.Count());
        System.out.println("----------------");

        System.out.println("Count Test: with an empty list");
        System.out.println(listTwo.Count());
        System.out.println("----------------");

        System.out.println("\n==============================================================");
        System.out.println("Is it empty test: with a full list");
        System.out.println(list.IsEmpty());
        System.out.println("----------------");

        System.out.println("Is it empty test: with an empty list");
        System.out.println(listTwo.IsEmpty());
        System.out.println("----------------");

    }

  /*  public static void Test_Append() {
        System.out.println("Testing Append");
        main.DList<Integer> dlist = new main.DList<>();

        int[] values = {1, 2, 3, 4, 5};

        for (int v: values) {
            dlist.Append(v);
            dlist.OutputRev();
            System.out.println("==============");
        }


    }
    */

    public static void main(String args[])
    {
        //Test_Append();
        // Random rand = new Random(); commenting this out for the purpose of testing certain functions
        int count = 10;
        DList<Integer> list = new DList<>();
        System.out.println("- DOUBLE LINKED LIST TESTING - \t Original ");
        for (int x = 0; x < count; x++)
        {
            //int rnumber = rand.nextInt(100) + 1;
            list.Append(x);
            System.out.print(x+"\t");
        }

        System.out.println();
        System.out.println("==============================================================");
        UnitTest(list);


    }
}
