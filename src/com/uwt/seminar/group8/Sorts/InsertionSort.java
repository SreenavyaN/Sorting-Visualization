package com.uwt.seminar.group8.Sorts;


import com.uwt.seminar.group8.Java.SortingVisualizerMain;
import com.uwt.seminar.group8.Java.VisualizeSorting;

public class InsertionSort implements Runnable{
	
	private Integer[] toBeSorted;
	private VisualizeSorting frame;
	private boolean fast;
	
	public InsertionSort(Integer[] toBeSorted, VisualizeSorting frame, boolean fast) {
		this.toBeSorted = toBeSorted;
		this.frame = frame;
		this.fast = fast;
	}
	
	public void run() {

			sortins();
		SortingVisualizerMain.isSorting=false;
	}
	
	public void sortins() {
		int temp = 0;
		int insert = 0;
		for(int i = 1; i<toBeSorted.length; i++){
			insert = i;
			for(int j = i-1; j>=0; j--){
				if (toBeSorted[i] < toBeSorted[j]){
					insert = j;
					if (j == 0){
						break;
					}
				}else{
					break;
				}
				frame.reDrawArray(toBeSorted, i, insert);
				try {
					Thread.sleep(SortingVisualizerMain.sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			temp = toBeSorted[i];
			for (int j = i; j>insert; j--){
				toBeSorted[j] = toBeSorted[j-1];
			}
			toBeSorted[insert] = temp;
		}
		frame.reDrawArray(toBeSorted);
	}
}
