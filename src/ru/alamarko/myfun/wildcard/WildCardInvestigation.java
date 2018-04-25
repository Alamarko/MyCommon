package ru.alamarko.myfun.wildcard;

import java.util.ArrayList;
import java.util.Collection;

public class WildCardInvestigation {

	public static void main(String[] args)
	{
		IFace<Object> imFace = new IFace<Object>()
		{			
			public void faceThis(Object t)
			{
												
			}
			
		};
		Collection<String> cs = new ArrayList<>();
		String str = writeAll(cs, imFace);
		

	}
	
	public static <T> T writeAll(Collection<T> coll, IFace<? super T> pFaceImp)
	{
	    T last = null;
	    for (T t : coll) {
	        last = t;
	        pFaceImp.faceThis(last);
	    }
	    return last;
	}

}
