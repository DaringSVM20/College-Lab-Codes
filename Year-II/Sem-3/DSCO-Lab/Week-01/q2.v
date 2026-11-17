module q2(a,b,c,d,f,g);
	input a,b,c,d;
	output f,g;
	wire o1;
	assign o1 = ~(a&b);
	assign f = o1^d^c;
	assign g = ~(b|c|d);
endmodule
