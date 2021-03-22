public interface PriorityQueue
{
    void enqueue(int data, int priority);
    int dequeue();
    int getHighestPrior();
    boolean isEmpty();
    boolean isFull();
}