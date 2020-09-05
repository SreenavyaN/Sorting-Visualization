package com.uwt.seminar.group8.Sorts;


import com.uwt.seminar.group8.Java.SortingVisualizerMain;
import com.uwt.seminar.group8.Java.VisualizeSorting;


public class QuickSort implements Runnable {

    private Integer[] toBeSorted;
    private VisualizeSorting frame;
    private boolean fast;

    public QuickSort(Integer[] toBeSorted, VisualizeSorting frame, boolean fast) {
        this.toBeSorted = toBeSorted;
        this.frame = frame;
        this.fast = fast;
    }

    public void run() {
        Integer[] toBeSorted = SortingVisualizerMain.toBeSorted;
        quickSort(toBeSorted, 0, toBeSorted.length - 1);
        frame.reDrawArray(toBeSorted);
        SortingVisualizerMain.isSorting=false;
    }

    public void quickSort(Integer[] toBeSorted, int begin, int end) {

        if (end - begin == 1) {
            if (toBeSorted[end] < toBeSorted[begin]) {
                int swapTemp = toBeSorted[begin];
                toBeSorted[begin] = toBeSorted[end];
                toBeSorted[end] = swapTemp;
            }

            return;
        }
        if (begin == end) {
            return;
        }
        int partitionIndex = partition(toBeSorted, begin, end);

        if (partitionIndex - begin > 1) {
            quickSort(toBeSorted, begin, partitionIndex - 1);
        }
        if (end - partitionIndex > 1) {
            quickSort(toBeSorted, partitionIndex + 1, end);
        }
        frame.reDrawArray(toBeSorted, partitionIndex, begin, end);
        try {
            Thread.sleep(SortingVisualizerMain.sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        //frame.reDrawArray(toBeSorted, end);
        int i = begin;
        int j = end - 1;
        while (i < j) {
            //frame.reDrawArray(toBeSorted, end, i, j);
            while ( i < j && arr[i] <= pivot) {
                i++;
            }
            while ( i < j && arr[j] >= pivot) {
                j--;
            }
            if (i < j) {
                //frame.reDrawArray(toBeSorted, end, i, j);
                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
                frame.reDrawArray(toBeSorted, end, i, j);
                try {
                    Thread.sleep(SortingVisualizerMain.sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
                j--;
            }
        }
        if (arr[i] < pivot) {
            i++;
        }
        int swapTemp = arr[i];
        arr[i] = arr[end];
        arr[end] = swapTemp;
        frame.reDrawArray(toBeSorted, end, i);
        try {
            Thread.sleep(SortingVisualizerMain.sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return i;
    }
}