import java.io.*;

public class dm2h {
    BufferedReader in;
    PrintWriter out;

    StringList prog = new StringList();
    Queue turing = new Queue();
    HashStringList labels = new HashStringList();
    int[] reg = new int[26];

    class Node {
        String s;
        int value;
        Node next;

        public Node(String s, int value) {
            this.s = s;
            this.value =value;
        }
    }


    private final static int HASHCONST = 1007;

    private static int getHashCode(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans += 31*ans + s.charAt(i);
            ans %= HASHCONST;
        }
        return ans;
    }

    class HashStringList {
        private Node[] e;

        public void add(String s, int value) {
            int x = getHashCode(s);
            if (e[x] == null) {
                e[x] = new Node(s, value);
            }
            Node t = e[x];
            while (t.next != null) {
                t = t.next;
            }
            t.next = new Node(s, value);
        }

        public int find(String s) {
            int x = getHashCode(s);
            Node t = e[x];
            while (t != null) {
                if (t.s.equals(s)) {
                    return t.value;
                }
                t = t.next;
            }
            return -1;
        }

        public HashStringList() {
            e = new Node[HASHCONST];
        }
    }

    public void readProgram() {
        String s;
        int i = 0;
        while(true) {
            try {
                s = in.readLine();
                if (s.charAt(0) == ':') {
                    labels.add(s.substring(1), i);
                } else {
                    prog.add(s);
                    i++;
                }
            } catch  (Exception e){
                return;
            }
        }
    }

    public void solve() throws IOException {
        readProgram();
        String cur;
        int x, y;
        for(int i = 0; i < prog.size; i++) {
            cur =  prog.get(i);
            switch (cur.charAt(0)) {
                case '+' :
                    turing.enque((turing.deque() + turing.deque()) & 65535);
                    break;
                case '-' :
                    turing.enque((turing.deque() - turing.deque()) & 65535);
                    break;
                case '*' :
                    turing.enque((turing.deque() * turing.deque()) & 65535);
                    break;
                case '/' :
                    x = turing.deque();
                    y = turing.deque();
                    turing.enque(y == 0 ? 0 : (x / y) & 65535);
                    break;
                case '%' :
                    x = turing.deque();
                    y = turing.deque();
                    turing.enque(y == 0 ? 0 : (x % y) & 65535);
                    break;
                case '>' :
                    reg[cur.charAt(1) - 'a'] = turing.deque();
                    break;
                case '<' :
                    turing.enque(reg[cur.charAt(1) - 'a']);
                    break;
                case 'P' :
                    out.println(cur.length() == 1 ? turing.deque() : reg[cur.charAt(1) - 'a']);
                    break;
                case 'C' :
                    char c = (char) ((cur.length() == 1 ? turing.deque() : reg[cur.charAt(1) - 'a']) & 255);
                    out.print(c);
                    break;
                case 'J' :
                    i = labels.find(cur.substring(1)) - 1;
                    break;
                case 'Z' :
                    if (reg[cur.charAt(1) - 'a'] == 0) {
                        i = labels.find(cur.substring(2)) - 1;
                    }
                    break;
                case 'E' :
                    if (reg[cur.charAt(1) - 'a'] == reg[cur.charAt(2) - 'a']) {
                        i = labels.find(cur.substring(3)) - 1;
                    }
                    break;
                case 'G' :
                    if (reg[cur.charAt(1) - 'a'] > reg[cur.charAt(2) - 'a']) {
                        i = labels.find(cur.substring(3)) - 1;
                    }
                    break;
                default  : turing.enque(Integer.parseInt(cur));
            }
        }
    }

    public void run() {
        try {
            in = new BufferedReader(new FileReader(new File("input.txt")));
            out = new PrintWriter(new File("output.txt"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class StringList {
        String[] e;
        int size;

        public StringList() {
            e = new String[50];
            size = 0;
        }

        String get(int i) {
            return e[i];
        }

        void ensureCapacity(int cap) {
            if (e.length >= cap) {
                return;
            }
            String[] t = new String[2*cap];
            for (int i = 0; i < e.length; i++) {
                t[i] = e[i];
            }
            e = t;
        }

        void add(String s) {
            ensureCapacity(size + 1);
            e[size] = s;
            size++;
        }
    }

    class Queue {
        int[] e;
        int last, first, size;

        void ensureCapacity(int x) {
            if (x <= e.length) {
                return;
            }
            int[] t = new int[2 * x];
            for (int i = 0; i < 2*x; i++) {
                t[i] = e[(first + i) % e.length];
            }
            first = 0;
            last = size;
            e = t;
        }

        void enque(int x) {
            ensureCapacity(size + 1);
            e[last] = x;
            last = (last + 1) % e.length;
            size++;
        }

        int deque() {
            int x = e[first];
            first = (first + 1) % e.length;
            size--;
            return x;
        }

        public Queue() {
            e = new int[50];
            last = 0;
            first = 0;
            size = 0;
        }
    }

    public static void main(String[] arg) {
        new dm2h().run();
    }
}



