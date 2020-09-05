package com.uwt.seminar.group8.Sorts;

import com.uwt.seminar.group8.Java.SortingVisualizerMain;
import com.uwt.seminar.group8.Java.VisualizeSorting;

public class BubbleSort implements Runnable{
	
	private Integer[] toBeSorted;
	private VisualizeSorting frame;
	private boolean fast;
	
	public BubbleSort(Integer[] toBeSorted, VisualizeSorting frame, boolean fast) {
		this.toBeSorted = toBeSorted;
		this.frame = frame;
		this.fast = fast;
	}
	
	public void run() {
			sortbs();
		SortingVisualizerMain.isSorting=false;
	}

	public void sortbs() {
		int temp = 0;
		boolean swapped = false;
		for(int i = 0; i<toBeSorted.length-1; i++){
			swapped = false;
			for(int j = 1; j<toBeSorted.length-i; j++){
				if (toBeSorted[j-1]> toBeSorted[j]){
					temp = toBeSorted[j-1];
					toBeSorted[j-1] = toBeSorted[j];
					toBeSorted[j]= temp;
					swapped = true;
				}
				frame.reDrawArray(toBeSorted, j, j+1);
				try {
					Thread.sleep(SortingVisualizerMain.sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (!swapped) break;
		}
	}

}
