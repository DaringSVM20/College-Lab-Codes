module q1(a,b,c,d,f);
	input a,b,c,d;
	output f;
	and(o1,a,b);
	and(o2,a,b);
	or(o3,o1,c);
	or(o4,o2,c);
	not(o5,o3);
	and(o6,d,o5);
	or(o7,d,o4);
	and(f,o6,o7);
endmodule
