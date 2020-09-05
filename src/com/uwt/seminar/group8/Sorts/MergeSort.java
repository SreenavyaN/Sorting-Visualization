package com.uwt.seminar.group8.Sorts;

import com.uwt.seminar.group8.Java.SortingVisualizerMain;

public class MergeSort implements Runnable{
	
	public void run() {
		Integer[] toBeSorted = SortingVisualizerMain.toBeSorted;
		inPlaceSort(toBeSorted);
		SortingVisualizerMain.isSorting=false;
	}
	public void inPlaceSort ( Integer[] x )
	   {  inPlaceSort (x, 0, x.length-1);  }

   private void inPlaceSort ( Integer[] x, int first, int last )
   {
      int mid, lt, rt;
      int tmp;

      if ( first >= last ) return;

      mid = (first + last) / 2;

      inPlaceSort (x, first, mid);
      inPlaceSort (x, mid+1, last);

      lt = first;  rt = mid+1;
      // One extra check:  can we SKIP the merge?
      if ( x[mid] <= x[rt])
         return;

      while (lt <= mid && rt <= last) {
          if (x[lt] <= x[rt])
              lt++;
          else {
              tmp = x[rt];
              for (int i = rt - lt; i > 0; i--) {
                  x[lt + i] = x[lt + i - 1];
              }
              x[lt] = tmp;
              lt++;
              mid++;
              rt++;
          }
          SortingVisualizerMain.frame.reDrawArray(x, mid, rt, lt);
          try {
              Thread.sleep(SortingVisualizerMain.sleep);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
   }
}
