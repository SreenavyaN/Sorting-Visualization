/******************************************************************************
 *  Name:    Sreenavya N and Sikha Pentyala
 *  NetID:   sreevpk sikha
 *  Precept: TCSS 598 Project Group 8
 *
 *  Description:  To Visualize Sorting techniques
 ******************************************************************************/


package com.uwt.seminar.group8.Java;


import com.uwt.seminar.group8.Sorts.*;

public class SortingVisualizerMain {
	
	private static Thread sortingThread;

	public static VisualizeSorting frame;
	public static Integer[] toBeSorted;
	public static boolean isSorting = false;
	public static int sortDataCount = 100;
	public static int sleep = 20;
	public static int blockWidth;
	public static boolean stepped = false;
	
	public static void main(String[] args) {
		frame = new VisualizeSorting();
		resetArray();
		frame.setLocationRelativeTo(null);
	}
	
	public static void resetArray() {

		if (isSorting) return;
		toBeSorted = new Integer[sortDataCount];
		blockWidth = (int) Math.max(Math.floor(500/sortDataCount), 1);
		for(int i = 0; i<toBeSorted.length; i++){
			if (stepped) {
				toBeSorted[toBeSorted.length-1-i] = i;
			} else {
				toBeSorted[i] = (int) (sortDataCount*Math.random());
			}
		}
		frame.preDrawArray(toBeSorted);
	}
	
	public static void startSort(String type) {

		if (sortingThread == null || !isSorting) {

			resetArray();

			isSorting = true;

			switch (type) {
				case "Bubble":
					sortingThread = new Thread(new BubbleSort(toBeSorted, frame, false));
					break;

				case "Selection":
					sortingThread = new Thread(new SelectionSort(toBeSorted, frame, false));
					break;

				case "Insertion":
					sortingThread = new Thread(new InsertionSort(toBeSorted, frame, false));
					break;

				case "Quick":
					sortingThread = new Thread(new QuickSort(toBeSorted, frame, false));
					break;

				case "Merge":
					sortingThread = new Thread(new MergeSort());
					break;


				default:
					isSorting = false;
					return;
			}

			sortingThread.start();

		}
	}

}
