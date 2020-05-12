/**
 * IDSA Short Project 3
 * Team Members:
 * Adarsh Raghupati  NetID: axh190002
 * Keerti Keerti     NetID: kxk190012
 */
package axh190002;

import java.util.Random;

public class SP3MergeSort {
    public static Random random = new Random();

    public static int numTrials = 50;
    public static final int threshold = 30;

    public static void main(String[] args) {
        int n = 64000000  ;  int choice = 1;
        if(args.length > 0) { n = Integer.parseInt(args[0]); }
        if(args.length > 1) { choice = Integer.parseInt(args[1]); }
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = i;
        }
        int[] B = new int[arr.length];

        Timer timer = new Timer();
        switch(choice) {
            case 1: // Version Take 2
                for(int i=0; i<numTrials; i++) {
                    Shuffle.shuffle(arr);
                    mergeSort1(arr,B,0,arr.length-1);
                }
                break;
            case 2://Version Take 3
                for(int i=0; i<numTrials; i++) {
                    Shuffle.shuffle(arr);
                    System.arraycopy(arr,0,B,0,arr.length);
                    mergeSort2(arr,B,0,arr.length-1);
                }
                break;  // etc
            case 3://Version Take 4
                for(int i=0; i<numTrials; i++) {
                    Shuffle.shuffle(arr);
                    System.arraycopy(arr,0,B,0,arr.length);
                    mergeSort3(arr,B,0,arr.length-1);
                }
                break;  // etc
            case 4://Version Take 6
                for(int i=0; i<numTrials; i++) {
                    Shuffle.shuffle(arr);
                    System.arraycopy(arr,0,B,0,arr.length);
                    mergeSort4(arr,B);
                }
                break;  // etc
        }
        timer.end();
        timer.scale(numTrials);

        System.out.println("Choice: " + choice + "\n"+"n: " + n + "\n" + timer);
    }

    /**
     * Iterative merge sort combined with insertion sort. Insertion sort runs when sub array size is less than threshold
     * @param A
     * @param B
     */
    private static void mergeSort4(int[] A, int[] B) {
        int n = A.length;
        int[] inp = new int[n],t= new int[n];
        inp = A;
        for(int j = 0;j<n;j=j+threshold){
            //To handle ArrayOutOfBound
            if((j+threshold-1) >= n)
                insertionSort(A,j,n-1) ;
            else
                insertionSort(A,j,j+threshold-1);// here T = 2;
        }

        for(int i = threshold;i<n;i=i*2){
            for(int j=0;j<n;j=j+2*i) {
                //To handle ArrayOutOfBound and input size not a power of 2
                int mid = Math.min(j+i-1,n-1);
                int right_end = Math.min(j+2*i-1,n-1);
                merge2(B,A,j,mid,right_end);
            }
            t=A;
            A=B;
            B=t;
        }
        if(A!=inp)
            System.arraycopy(A,0,inp,0,inp.length);

    }

    /**
     *  Merge sort combined with insertion sort. Insertion sort runs when sub array size is less than threshold
     * @param arr
     * @param B
     * @param p
     * @param q
     */
    private static void mergeSort3(int[] arr, int[] B, int p, int q) {
        if(q-p < threshold){
            insertionSort(arr,p,q);
        }else if(p<q){
            int mid = p+(q-p)/2;
            mergeSort3(B,arr,p,mid);
            mergeSort3(B,arr,mid+1,q);
            merge2(arr,B,p,mid,q);
        }
    }

    /**
     * Runs insertion sort for the sub array
     * @param arr
     * @param p
     * @param q
     */
    public static void insertionSort(int[] arr,int p, int q) {
        int arrLength = q - p + 1;
        if (arrLength >= 2) {
            int i = p + 1;
            int temp, j;
            while (i <= q) {
                temp = arr[i];
                j = i - 1;
                while (j >= p && arr[j] > temp) {
                    arr[j + 1] = arr[j];
                    j = j - 1;
                }
                arr[j + 1] = temp;
                i++;
            }
        }
    }

    /**
     * Merge sort take 2
     * @param arr
     * @param B
     * @param p
     * @param q
     */
    public static void mergeSort1(int[] arr,int[] B,int p, int q) {
        if(p<q){
            int mid = p+(q-p)/2;
            mergeSort1(B,arr,p,mid);
            mergeSort1(B,arr,mid+1,q);
            merge(arr,B,p,mid,q);
        }
    }
    public static void merge(int[] arr, int[] B, int p, int mid, int q){
        System.arraycopy(arr,p,B,p,q-p+1);
        int i=p,j=mid+1,k=p;
        while(i<=mid && j<=q){
            if(B[i]<=B[j])
                arr[k++] = B[i++];
            else
                arr[k++] = B[j++];
        }
        while(i<=mid) arr[k++] = B[i++];
        while(j<=q) arr[k++] = B[j++];
    }

    /**
     * Merge sort take 3.
     * @param arr
     * @param B
     * @param p
     * @param q
     */
    public static void mergeSort2(int[] arr,int[] B,int p, int q) {
        if(p<q){
            int mid = p+(q-p)/2;
            mergeSort2(B,arr,p,mid);
            mergeSort2(B,arr,mid+1,q);
            merge2(arr,B,p,mid,q);
        }

    }

    public static void merge2(int[] arr, int[] B, int p, int mid, int q){
        int i=p,j=mid+1,k=p;
        while(i<=mid && j<=q){
            if(B[i]<=B[j])
                arr[k++] = B[i++];
            else
                arr[k++] = B[j++];
        }
        while(i<=mid) arr[k++] = B[i++];
        while(j<=q) arr[k++] = B[j++];
    }


    /** Timer class for roughly calculating running time of programs
     *  @author
     *  Usage:  Timer timer = new Timer();
     *          timer.start();
     *          timer.end();
     *          System.out.println(timer);  // output statistics
     */

    public static class Timer {
        long startTime, endTime, elapsedTime, memAvailable, memUsed;
        boolean ready;

        public Timer() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public void start() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public Timer end() {
            endTime = System.currentTimeMillis();
            elapsedTime = endTime-startTime;
            memAvailable = Runtime.getRuntime().totalMemory();
            memUsed = memAvailable - Runtime.getRuntime().freeMemory();
            ready = true;
            return this;
        }

        public long duration() { if(!ready) { end(); }  return elapsedTime; }

        public long memory()   { if(!ready) { end(); }  return memUsed; }

        public void scale(int num) { elapsedTime /= num; }

        public String toString() {
            if(!ready) { end(); }
            return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed/1048576) + " MB / " + (memAvailable/1048576) + " MB.";
        }
    }

    /** @author rbk : based on algorithm described in a book
     */


    /* Shuffle the elements of an array arr[from..to] randomly */
    public static class Shuffle {

        public static void shuffle(int[] arr) {
            shuffle(arr, 0, arr.length-1);
        }

        public static<T> void shuffle(T[] arr) {
            shuffle(arr, 0, arr.length-1);
        }

        public static void shuffle(int[] arr, int from, int to) {
            int n = to - from  + 1;
            //System.out.println("Shuffle: "+Arrays.toString(arr));
            for(int i=1; i<n; i++) {
                int j = random.nextInt(i);
                //System.out.println("i: "+i+" j: "+j+" i+from: "+(i+from)+" j+from: "+(to-j));
                swap(arr, i+from, to-j);
            }
            //System.out.println("After Shuffle: "+Arrays.toString(arr));
        }

        public static<T> void shuffle(T[] arr, int from, int to) {
            int n = to - from  + 1;
            Random random = new Random();
            for(int i=1; i<n; i++) {
                int j = random.nextInt(i);
                swap(arr, i+from, j+from);
            }
        }

        static void swap(int[] arr, int x, int y) {
            int tmp = arr[x];
            arr[x] = arr[y];
            arr[y] = tmp;
        }

        static<T> void swap(T[] arr, int x, int y) {
            T tmp = arr[x];
            arr[x] = arr[y];
            arr[y] = tmp;
        }

        public static<T> void printArray(T[] arr, String message) {
            printArray(arr, 0, arr.length-1, message);
        }

        public static<T> void printArray(T[] arr, int from, int to, String message) {
            System.out.print(message);
            for(int i=from; i<=to; i++) {
                System.out.print(" " + arr[i]);
            }
            System.out.println();
        }
    }
}

