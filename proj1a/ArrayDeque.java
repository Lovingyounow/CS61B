public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;
    private static final double HIGHFACTOR = 0.75;
    private static final double LOWFACTOR = 0.25;
    private static final int INIT_CAPACITY = 8;

    public ArrayDeque() {
        items = (T[])new Object[INIT_CAPACITY];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    /**
     * ��ԭ�����е�Ԫ�����·������������У�ԭ�����һ��Ԫ�ط������±�Ϊ0��λ��
     * nextFirst�±�Ϊitems.length - 1
     * nextLast�±�ΪԪ����Ĵ�С
     * @param cpapcity
     */
    private void resizeHelper(int cpapcity) {
        T[] temp = (T[])new Object[cpapcity];
        int pos = this.nextFirst;
        int i;
        for (i = 0; i < this.size(); i++) {
            pos = (pos + 1) % this.items.length;
            temp[i] = this.items[pos];
        }
        items = temp;
        this.nextFirst = this.items.length - 1;
        this.nextLast = i;
    }

    /**
     * ��������õ�Ԫ�س��������鳤�� * ���װ�����ӣ������鳤������һ��
     */
    private void resizeAdd() {
        if (this.size() > this.items.length * HIGHFACTOR) {
            resizeHelper(this.items.length * 2);
        }
    }
    
    /**
     * ��������õ�Ԫ��С�������鳤�� * ��Сװ�����ӣ���������Сһ��
     * ��֤����Ĵ�С����Ϊ8
     */
    private void resizeRemove() {
        if (this.items.length > 8 && this.size() <= this.items.length * LOWFACTOR) {
            resizeHelper(this.items.length * 2);
        }
    }

    public void addFirst(T item) {
        this.items[nextFirst] = item;
        // ����nextFirst����һλ��
        nextFirst = (nextFirst - 1 + this.items.length) % this.items.length;
        size++;
        resizeAdd();
    }

    public void addLast(T item) {
        this.items[nextLast] = item;
        // ����nextLast����һλ��
        nextLast = (nextLast + 1) % this.items.length;
        size++;
        resizeAdd();
    }

    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int pos = this.nextFirst;
        for (int i = 0; i < this.size(); i++) {
            pos = (pos + 1) % this.items.length;
            System.out.print(this.items[pos] + " ");
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        // �����Ƴ���nextFirst��λ��
        nextFirst = (nextFirst + 1) % this.items.length;
        T t = this.items[nextFirst];
        size--;
        resizeRemove();
        return t;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        // �����Ƴ���nextLast��λ��
        nextLast = (nextLast - 1 + this.items.length) % this.items.length;
        T t = this.items[nextLast];
        size--;
        resizeRemove();
        return t;
    }

    public T get(int index) {
        if (index > this.size() || index < 0) {
            return null;
        }
        int pos = this.nextFirst;
        int i;
        for (i = 0; i <= index; i++) {
            pos = (pos + 1) % this.items.length;
        }
        return items[pos];
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        System.out.println(ad.size());
        ad.addFirst(1);
        ad.addLast(20);
        ad.addFirst(2);
        
        ad.addFirst(3);
        
        ad.addFirst(4);
        
        ad.addFirst(5);
        
        ad.addFirst(6);
        ad.addFirst(7);
        
        
        ad.addFirst(8);
        ad.addFirst(9);
        // ad.addFirst(10);
        // ad.addLast(333);
        System.out.println("output");
        ad.printDeque();
        System.out.println(ad.size());
        // System.out.println(ad.removeLast());
        System.out.println(ad.get(4));
        // System.out.println(ad.items.length);
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        ad.removeFirst();
        // ad.removeLast();
        // System.out.println();
        System.out.println(ad.size());
    }
}
