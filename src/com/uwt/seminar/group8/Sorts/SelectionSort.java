package com.uwt.seminar.group8.Sorts;


import com.uwt.seminar.group8.Java.SortingVisualizerMain;
import com.uwt.seminar.group8.Java.VisualizeSorting;
import com.uwt.seminar.group8.Java.VisualizeSorting;

public class SelectionSort implements Runnable{
	
	private Integer[] toBeSorted;
	private VisualizeSorting frame;
	private boolean fast;
	
	public SelectionSort(Integer[] toBeSorted, VisualizeSorting frame, boolean fast) {
		this.toBeSorted = toBeSorted;
		this.frame = frame;
		this.fast = fast;
	}
	
	public void run() {
			sortSel();
		SortingVisualizerMain.isSorting=false;
	}

	public void sortSel() {
		int temp = 0;
		int selected = 0;
		for(int i = 0; i<toBeSorted.length; i++){
			selected = i;
			for(int j = toBeSorted.length-1; j>i; j--){
				
				if (toBeSorted[j] <= toBeSorted[selected]){
					selected = j;
				}
				frame.reDrawArray(toBeSorted, selected, j-1);
				try {
					Thread.sleep(SortingVisualizerMain.sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
			temp = toBeSorted[i];
			toBeSorted[i] = toBeSorted[selected];
			toBeSorted[selected]= temp;
		}
		frame.reDrawArray(toBeSorted);
	}

}
