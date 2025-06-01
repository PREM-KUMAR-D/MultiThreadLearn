package com.prem;

import java.util.Arrays;

class Parallel extends Thread{
	int arr [] ;
	int start  ;
	int end;

	
	Parallel(int [] arr, int start , int end) {
		// TODO Auto-generated constructor stub
		this.arr = arr;
		this.start =start;
		this.end =end;
	}
	
	@Override
	public void run() {
		if(end -start > 16) {
			int mid = (start + end)/2;
			Parallel first = new Parallel(arr,start,mid);
			Parallel second = new Parallel(arr,mid +1, end);
			try {
				first.start();
				second.start();
				
				first.join();
				second.join();
				
				merge(start,end,mid);
			}catch(InterruptedException e) {
				
			}
			
		}else {
			Arrays.sort(arr,start,end);
			System.out.println(" The sorted array is : " + Arrays.toString(Arrays.copyOfRange(arr, start, end)));
		}
		
		
	}
	
	public void merge(int start, int end , int mid) {
	    int[] temp = new int[end - start + 1];
	    int i = start;     // pointer for left half
	    int j = mid + 1;   // pointer for right half
	    int k = 0;         // pointer for temp array

	    while (i <= mid && j <= end) {
	        if (arr[i] <= arr[j]) {
	            temp[k++] = arr[i++];
	        } else {
	            temp[k++] = arr[j++];
	        }
	    }

	    // Copy remaining elements from left half (if any)
	    while (i <= mid) {
	        temp[k++] = arr[i++];
	    }

	    // Copy remaining elements from right half (if any)
	    while (j <= end) {
	        temp[k++] = arr[j++];
	    }

	    // Copy merged elements back to original array
	    for (int m = 0; m < temp.length; m++) {
	        arr[start + m] = temp[m];
	    }
		
	}
	
}



public class ParrallelSortArr {
	
	public static int arr[] ;
	public static int [] generateArr(int limit) {
		
		int [] arr = new int[limit];
		
		for(int i=0;i<limit;i++) {
			arr[i] = (int) (Math.random() * 1000);	
		}
		return arr;
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		arr = generateArr(1_000_000);
		Parallel p = new Parallel(arr,0,arr.length-1);
		p.start();
		
		p.join();
		System.out.println("Final sorted array: " + Arrays.toString(arr));
		
	}

}
