package com.lms.changemaker.utilities;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class Utilities {

	
	
	public static int[] getRgbColor(){
		int ar[]=new int[3];

	    for(var i = 0; i < ar.length; i++) {
	    	ar[i]=(int) Math.floor(Math.random() * 255);	
	    }
	    
	    return ar;
	}
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
	{
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
}
