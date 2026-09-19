package algorithms;

import metrics.Metrics;

import java.util.Random;
public class QuickSort {
    private static final Random random = new Random();

    public static void sort (int[] arr, Metrics metrics){
        if(arr == null || arr.length <= 1){
            return;
        }

        metrics.startTimer();
        quickSort(arr,0,arr.length - 1, 1, metrics);
        metrics.stopTimer();
    }

    private static void quickSort(int[] arr, int left, int right, int depth, Metrics metrics){
        metrics.updateDepth(depth);

        while (left < right){
            int[] pivots = partition(arr, left, right,metrics);
            int lt = pivots[0];
            int gt = pivots[1];

            if (lt - left < right - gt){
                quickSort(arr,left,lt,depth+1, metrics);
                left = gt;
            }
            else{
                quickSort(arr,gt,right,depth+1, metrics);
                right = lt;
            }
        }
    }

    private static int[] partition(int[] arr, int left, int right, Metrics metrics) {
        int pivotIndex = left + random.nextInt(right - left + 1);
        swap(arr, left, pivotIndex);
        int pivot = arr[left];

        int lt = left;
        int gt = right;
        int i = left + 1;

        while (i <= gt) {
            metrics.incrementComparisons();
            if (arr[i] < pivot) {
                swap(arr, lt++, i++);
            } else {
                metrics.incrementComparisons();
                if (arr[i] > pivot) {
                    swap(arr, i, gt--);
                } else {
                    i++;
                }
            }

        }
        return new int[]{lt - 1, gt + 1};
    }
    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }



}
