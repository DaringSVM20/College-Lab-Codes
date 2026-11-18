//Test bench code for q2
`timescale 1ns/1ns
`include "q2.v"

module tb_q2();
    reg w0,w1,w2,w3,s0,s1;
    wire f;
    q2 ex1(w0,w1,w2,w3,s0,s1,f);
    initial
    begin
    
    $dumpfile("tb_q2.vcd");
    $dumpvars(0,tb_q2);
    
    w0=0; w1=1; w2=0; w3=1;
    
    s0=0; s1=0; #20;
    s0=1; s1=0; #20;
    s0=0; s1=1; #20;
    s0=1; s1=1; #20;

    w0=1; w1=0; w2=1; w3=0;

    s0=0; s1=0; #20;
    s0=1; s1=0; #20;
    s0=0; s1=1; #20;
    s0=1; s1=1; #20;
    
    $display("Test Complete!");
    end
endmodule
