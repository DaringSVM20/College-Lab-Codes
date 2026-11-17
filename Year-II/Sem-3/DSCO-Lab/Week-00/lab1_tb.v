`timescale 1ns/1ns  //first one is time setting, second one is precision setting
`include "lab1.v"

module lab1_tb();
reg x1,x2;
wire f;

lab1 ex1(x1,x2,y);
initial
begin

	$dumpfile("lab1_tb.vcd");
	$dumpvars(0, lab1_tb);
	
	x1=1'b0; x2=1'b0;
	#20;
	
	x1=1'b0; x2=1'b1;
	#20;
	
	x1=1'b1; x2=1'b0;
	#20;
	
	x1=1'b1; x2=1'b1;
	#20;
	
	$display("Test Complete");
	
end

endmodule
