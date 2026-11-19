`timescale 1ns/1ns
`include "prienc4to2.v"

module prienctb;
    reg [3:0]in;
    wire [1:0]out;

    prienc4to2 dut (.in(in), .out(out));

    initial begin
        $dumpfile("prienctb.vcd");
        $dumpvars(0, prienctb);

        $monitor("in=%b -> out=%b", in, out);
        in=4'b0000; #10;
        in=4'b0001; #10;
        in=4'b0010; #10;
        in=4'b0100; #10;
        in=4'b1000; #10;
        in=4'b1010; #10;
        $finish;
    end

endmodule
