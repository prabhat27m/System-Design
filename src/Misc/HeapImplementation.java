package Misc;

// Abstract base class for heap operations
abstract class Heap {
    protected int[] heap;
    protected int size;
    protected int capacity;

    public Heap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    // Abstract method to be implemented by Min and Max heaps
    protected abstract boolean compare(int a, int b);

    protected int parent(int index) {
        return (index - 1) / 2;
    }

    protected int leftChild(int index) {
        return 2 * index + 1;
    }

    protected int rightChild(int index) {
        return 2 * index + 2;
    }

    protected void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void insert(int value) {
        if (size >= capacity) {
            throw new IllegalStateException("Heap is full");
        }

        heap[size] = value;
        heapifyUp(size);
        size++;
    }

    public int peek() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap[0];
    }

    public int remove() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);

        return root;
    }

    protected void heapifyUp(int index) {
        while (index > 0 && compare(heap[parent(index)], heap[index])) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    protected void heapifyDown(int index) {
        int minMax = index;
        int left = leftChild(index);
        int right = rightChild(index);

        if (left < size && compare(heap[minMax], heap[left])) {
            minMax = left;
        }

        if (right < size && compare(heap[minMax], heap[right])) {
            minMax = right;
        }

        if (minMax != index) {
            swap(index, minMax);
            heapifyDown(minMax);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}

// Min Heap implementation
class MinHeap extends Heap {
    public MinHeap(int capacity) {
        super(capacity);
    }

    @Override
    protected boolean compare(int a, int b) {
        return a > b; // for min heap, parent should be smaller than child
    }
}

// Max Heap implementation
class MaxHeap extends Heap {
    public MaxHeap(int capacity) {
        super(capacity);
    }

    @Override
    protected boolean compare(int a, int b) {
        return a < b; // for max heap, parent should be larger than child
    }
}

// Main class with example usage
public class HeapImplementation {
    public static void main(String[] args) {
        // Min Heap Example
        System.out.println("Min Heap Demo:");
        MinHeap minHeap = new MinHeap(10);
        minHeap.insert(3);
        minHeap.insert(1);
        minHeap.insert(4);
        minHeap.insert(1);
        minHeap.insert(5);
        System.out.print("Min Heap: ");
        minHeap.printHeap();
        System.out.println("Min element: " + minHeap.peek());
        System.out.println("Removing elements:");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.remove() + " ");
        }
        System.out.println("\n");

        // Max Heap Example
        System.out.println("Max Heap Demo:");
        MaxHeap maxHeap = new MaxHeap(10);
        maxHeap.insert(3);
        maxHeap.insert(1);
        maxHeap.insert(4);
        maxHeap.insert(1);
        maxHeap.insert(5);
        System.out.print("Max Heap: ");
        maxHeap.printHeap();
        System.out.println("Max element: " + maxHeap.peek());
        System.out.println("Removing elements:");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.remove() + " ");
        }
    }
}