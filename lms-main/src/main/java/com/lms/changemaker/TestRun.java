package com.lms.changemaker;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;

import com.lms.changemaker.entity.Student;
import com.lms.changemaker.serviceimpl.*;




public class TestRun {

	
	
	   @Autowired
	   StudentServiceImpl studentServiceImpl;
	 
	 
	

	public void findSecondLargest() {
		int arr[] = {46, 47, 94, 54, 52, 86, 36, 56, 89 };
        for (int i = 0; i < arr.length; i++)
        {
           for(int j=i+1;j<arr.length;j++) {
        	   if(arr[i]>arr[j]){
        		   int temp=arr[i];
            	   arr[i]=arr[j];
            	   arr[j]=temp;   
        	   }
        	   
           }
        }
        for (int i = 0; i < arr.length; i++)
        {
        	System.out.println("\nSorted array :" + arr[i]);
		}
        
        System.out.println("\nSecond Largest Sorted array :" + arr[arr.length-2]);

      
	}
	
	public  Map<String, Integer> countTotal(String st) {
		String stSplit[]=st.split("\\s+");
		Map<String, Integer> mapData=new HashMap<String, Integer>();
	   for (int i=0;i<stSplit.length;i++) {
				if(mapData.containsKey(stSplit[i])) {
					mapData.put(stSplit[i], mapData.get(stSplit[i])+1);	
				}else {
					mapData.put(stSplit[i], 1);
				}
				
	     }
	   return mapData;
	}

	
	
	
 public static void main(String args[]) {

	 
	   Student student=new Student();
	   student.setStudentToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZWJhLnNoYXlhbjEyMjU5QGdtYWlsLmNvbSIsImV4cCI6MTYzNzQwODk3MywiaWF0IjoxNjM3MzkwOTczfQ.bBNbAm8XguhiA3YwYhDrKefBaCxG-D36HWvsqpQDBivol5LpkNTtVZhhes-kCYcS2Y9wm4MBR2yhHeBVvy5bBQ");
	   student.setStudentId(3);
	   //new TestRun().studentServiceImpl.saveStudentToken(student);
	 
}

}
