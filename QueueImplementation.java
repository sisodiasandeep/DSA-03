public class QueueImplementation implements PriorityQueue{

    int count = 0;
    int[] queue;
    int size;

    public QueueImplementation(int size) {
        this.size = size;
        queue = new int[2 * size];
    }

    @Override
    public void enqueue(int data, int priority) {
        if(!isFull()){
            queue[count++] = data;
            queue[count++] = priority;
        }else{
            System.out.println("Queue Full !!!");
        }

    }

    @Override
    public int dequeue() {

        if(!isEmpty()){
            int priority = 0;
            int pos = 0;

            for(int i = 1; i < count; i += 2){
                if(priority < queue[i]){
                    priority = queue[i];
                    pos = i;
                }
            }

            int item = queue[pos - 1];
            queue[pos - 1] = queue[count - 2];
            queue[pos] = queue[count - 1];
            count -= 2;
            return item;
        }else{
            System.out.println("Empty Queue");
            return -1;
        }
    }

    @Override
    public int getHighestPrior() {
        int max = 0;
        int res = 0;

        for(int i = 1; i < count; i +=2){
            if(queue[i] > max){
                max = queue[i];
                res = queue[i - 1];
            }
        }

        return res;
    }

    @Override
    public boolean isEmpty() {
        if(count == 0)
            return true;

        return false;
    }

    @Override
    public boolean isFull() {
        if(count == (2 * size))
            return true;

        return false;
    }

    public String getQueue(){
        String res = "";

        for(int i = 0; i < count; i += 2)
            res += queue[i] + "," + queue[i + 1] + "\n";

        return res;
    }

}