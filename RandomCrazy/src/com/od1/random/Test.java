package com.od1.random;

public class Test implements Comparable<Test>{

	
	
	
	private long id ;
	
	public static void main(String[] args) {
		Integer a = 134523;
		Integer b = 18798;
		System.out.println(a.compareTo(b));
		Test t1 = new Test( Integer.MAX_VALUE );
		Test t2 = new Test( Long.MAX_VALUE );
		System.out.println(t1.compareTo(t2));
	}

	public Test( long id ){
		this.id = id - 1000;
	}
	
	@Override
	public int compareTo(Test o) {
		return (int) ( ( (this.id%Integer.MAX_VALUE)-(o.id%Integer.MAX_VALUE)  ) %Integer.MAX_VALUE );
	}

}
